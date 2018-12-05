import instructions.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class FunctionIR {
    private String name;
    private String return_type;
    private TreeMap<String, String> args;
    private String[] ints;
    private String[] floats;
    private Instruction[] instructions;
    private ArrayList<List<String>> text;


    public FunctionIR(String name, String return_type,
            TreeMap<String, String> args, String[] ints, String[] floats, Instruction[] instructions) {
        this.name = name;
        this.return_type = return_type;
        this.args = args;
        this.ints = ints;
        this.floats = floats;
        this.instructions = instructions;
        this.text = new ArrayList<List<String>>(3);
    }

    public void run(String file_base, List<FunctionIR> funcs) {
        List<String> registers = Arrays.asList("$s0", "$s1", "$s2", "$s3", "$s4", "$s5");
        System.out.println("Method 1 of 3, Naive Register Allocation:");
        System.out.println("Generating instructions...");
        List<InstRegallocPair> naive_instructions = this.naive_regalloc(registers);
        String file_name1 = String.format("../output/%s-%s.naive.ir", file_base, this.name);
        System.out.println("Done. Saving to \"" + file_name1 + "\"...");
        this.save_to_file(naive_instructions, file_name1);
        MIPSGenerator naiveMips = new MIPSGenerator(this, this.ints, this.floats, this.name, naive_instructions);
        this.translate_to_mips(funcs, naive_instructions, file_name1, naiveMips);
        this.text.add(naiveMips.text);
        System.out.println("Done.");

        System.out.println("Method 2 of 3, CFG + Intra-Block Allocation:");
        System.out.println("Generating instructions...");
        List<InstRegallocPair> block_instructions = this.intrablock_regalloc(registers);
        String file_name2 = String.format("../output/%s-%s.block.ir", file_base, this.name);
        System.out.println("Done. Saving to \"" + file_name2 + "\"...");
        this.save_to_file(block_instructions, file_name2);
        MIPSGenerator blockMips = new MIPSGenerator(this, this.ints, this.floats, this.name, block_instructions);
        this.translate_to_mips(funcs, block_instructions, file_name2, blockMips);
        this.text.add(blockMips.text);
        System.out.println("Done.");

        System.out.println("Method 3 of 3, Global Map-Coloring Allocation:");
        System.out.println("Generating instructions...");
        List<InstRegallocPair> colored_instructions = this.map_coloring_regalloc(registers);
        String file_name3 = String.format("../output/%s-%s.full.ir", file_base, this.name);
        System.out.println("Done. Saving to \"" + file_name3 + "\"...");
        this.save_to_file(colored_instructions, file_name3);
        MIPSGenerator colorMips = new MIPSGenerator(this, this.ints, this.floats, this.name, colored_instructions);
        this.translate_to_mips(funcs, colored_instructions, file_name2, colorMips);
        this.text.add(colorMips.text);
        System.out.println("Done.");
    }

    public List<InstRegallocPair> naive_regalloc(List<String> registers) {
        ArrayList<InstRegallocPair> naive_instructions = new ArrayList<>();
        for (Instruction inst : this.instructions) {
            int reg_index = 0;
            Set<String> using = inst.var_use();
            Set<String> defining = inst.var_def();
            HashMap<String, String> used_regs = new HashMap<>();
            for (String use : using) {
                naive_instructions.add(new InstRegallocPair(
                    new LoadInst(new String[]{registers.get(reg_index), use}), null));
                used_regs.put(use, registers.get(reg_index));
                reg_index++;
            }
            ArrayList<Instruction> store_insts = new ArrayList<>();
            for (String def : defining) {
                store_insts.add(new StoreInst(new String[]{def, registers.get(reg_index)}));
                used_regs.put(def, registers.get(reg_index));
                reg_index++;
            }
            naive_instructions.add(new InstRegallocPair(inst.with_registers(used_regs), used_regs));
            for (Instruction store_inst : store_insts) {
                naive_instructions.add(new InstRegallocPair(store_inst, null));
            }
        }

        return naive_instructions;
    }

    public List<InstRegallocPair> intrablock_regalloc(List<String> registers) {
        ControlFlow cf = new ControlFlow(this.instructions);
        ArrayList<InstRegallocPair> instructions = new ArrayList<>();
        List<Codeblock> blocks = cf.get_blocks();
        for (int i = 0; i < blocks.size(); i++) {
            Codeblock block = blocks.get(i);
            Integer[] flows = cf.get_flows(i);
            block.propagate_liveness();
            HashMap<String, Integer> liveranges = block.calc_liveranges();
            HashSet<String> all_vars = new HashSet<>(liveranges.keySet());
            List<String> regs = new ArrayList<>(registers);

            HashMap<String, String> var_reg_map = new HashMap<>();
            String max_var = null;
            int max = 0;
            while (liveranges.size() > 0 && regs.size() > 0) {
                for (String var : liveranges.keySet()) {
                    if (liveranges.get(var) > max) {
                        max = liveranges.get(var);
                        max_var = var;
                    }
                }
                liveranges.remove(max_var);
                var_reg_map.put(max_var, regs.remove(0));
                max = 0;
            }

            HashSet<String> all_uses = new HashSet<>();
            for (Instruction inst : block.get_lines()) {
                all_uses.addAll(inst.var_use());
            }
            for (String use : all_uses) {
                if (var_reg_map.containsKey(use)) {
                    instructions.add(new InstRegallocPair(
                        new LoadInst(new String[]{var_reg_map.get(use), use}), null));
                }
            }

            boolean jump_at_end = flows.length != 1 || flows[0] != i + 1;
            if (jump_at_end) {
                for (int j = 0; j < block.size() - 1; j++) {
                    instructions.addAll(this.intrablock_instruction_gen(
                        block.get_line(j), registers, var_reg_map));
                }
            } else {
                for (int j = 0; j < block.size(); j++) {
                    instructions.addAll(this.intrablock_instruction_gen(
                        block.get_line(j), registers, var_reg_map));
                }
            }

            HashSet<String> all_defs = new HashSet<>();
            for (Instruction inst : block.get_lines()) {
                all_defs.addAll(inst.var_def());
            }
            for (String def : all_defs) {
                if (var_reg_map.containsKey(def)) {
                    instructions.add(new InstRegallocPair(
                        new StoreInst(new String[]{def, var_reg_map.get(def)}), null));
                }
            }
            if (jump_at_end) {
                instructions.addAll(this.intrablock_instruction_gen(
                    block.get_line(block.size() - 1), registers, var_reg_map));
            }
        }

        return instructions;
    }

    private List<InstRegallocPair> intrablock_instruction_gen(Instruction inst,
            List<String> registers, HashMap<String, String> var_reg_map) {
        List<InstRegallocPair> instructions = new ArrayList<>();
        List<String> regs = new ArrayList<>(registers);
        for (String var : inst.vars_in_inst()) {
            if (var_reg_map.containsKey(var)) {
                regs.remove(var_reg_map.get(var));
            }
        }

        HashMap<String, String> regalloc = new HashMap<>();
        ArrayList<StoreInst> store_insts = new ArrayList<>();
        ArrayList<StoreInst> outer_store_insts = new ArrayList<>();

        Set<String> using = inst.var_use();
        for (String use : using) {
            if (var_reg_map.containsKey(use)) {
                regalloc.put(use, var_reg_map.get(use));
            } else {
                String register = regs.remove(0);
                regalloc.put(use, register);
                for (String var : var_reg_map.keySet()) {
                    if (var_reg_map.get(var).equals(register)) {
                        StoreInst store = new StoreInst(new String[]{var, register});
                        outer_store_insts.add(store);
                        instructions.add(new InstRegallocPair(store, null));
                        break;
                    }
                }
                instructions.add(new InstRegallocPair(new LoadInst(new String[]{register, use}), null));
            }
        }

        Set<String> defining = inst.var_def();
        for (String def : defining) {
            if (var_reg_map.containsKey(def)) {
                regalloc.put(def, var_reg_map.get(def));
            } else {
                String register = regs.remove(0);
                regalloc.put(def, register);
                for (String var : var_reg_map.keySet()) {
                    if (var_reg_map.get(var).equals(register)) {
                        StoreInst store = new StoreInst(new String[]{var, register});
                        outer_store_insts.add(store);
                        instructions.add(new InstRegallocPair(store, null));
                        break;
                    }
                }
                store_insts.add(new StoreInst(new String[]{def, register}));
            }
        }

        instructions.add(new InstRegallocPair(inst.with_registers(regalloc), regalloc));
        for (StoreInst store : store_insts) {
            instructions.add(new InstRegallocPair(store, null));
        }
        for (StoreInst store : outer_store_insts) {
            instructions.add(new InstRegallocPair(store.reverse(), null));
        }

        return instructions;
    }

    public List<InstRegallocPair> map_coloring_regalloc(List<String> registers) {
        ControlFlow cf = new ControlFlow(this.instructions);
        cf.calculate_livenesses();
        HashMap<String, Integer> spill_costs = cf.calculate_spill_costs();
        LiveRangeGraph lrg = new LiveRangeGraph(cf.get_blocks());
        HashMap<String, String> coloring = lrg.color_graph(registers, spill_costs);

        List<InstRegallocPair> instructions = new ArrayList<>();
        for (String var : cf.get_blocks().get(0).get_liveness(0)) {
            if (coloring.containsKey(var)) {
                instructions.add(
                    new InstRegallocPair(new LoadInst(new String[]{coloring.get(var), var}), null));
            }
        }

        for (Codeblock block : cf.get_blocks()) {
            Instruction[] lines = block.get_lines();
            for (int i = 0; i < lines.length; i++) {
                // if after instruction A, there are 2 variables X and Y in registers r0 and r1
                // that need to be live after A and A needs one register for variable Z, then we
                // generate "store r0 into X", then "load Z into r0", then run A, then
                // "store r0 into Z", and then generate "load X into r0".

                // we do this when A requires a spill (i.e. when not all variables in A are colored)
                // if A has m using variables not in registers and n defining variables not in
                // registers, then A needs m + n registers. If there are r registers and the live
                // variables after A have p variables in registers, then if r - p >= m + n, A
                // can simply generate m load instructions and n store instructions with the
                // unused registers. However, if r - p < m + n, then as many as necessary of the
                // p registers being used by live variables after A are used stored before A and
                // its respective load and store instructions, and then reloaded after that.

                Instruction inst = lines[i];
                HashMap<String, String> var_reg_map = new HashMap<>();
                Set<String> using = inst.var_use();
                Set<String> using_copy = new HashSet<>(using);
                for (String use : using_copy) {
                    if (coloring.containsKey(use)) {
                        var_reg_map.put(use, coloring.get(use));
                        using.remove(use);
                    }
                }
                Set<String> defining = inst.var_def();
                Set<String> defining_copy = new HashSet<>(defining);
                for (String def : defining_copy) {
                    if (coloring.containsKey(def)) {
                        var_reg_map.put(def, coloring.get(def));
                        defining.remove(def);
                    }
                }

                if (using.size() == 0 && defining.size() == 0) {
                    instructions.add(new InstRegallocPair(inst.with_registers(coloring), coloring));
                } else {
                    HashSet<String> live_after = block.get_liveness(i + 1);
                    Set<String> live_after_copy = new HashSet<>(live_after);
                    for (String live : live_after_copy) {
                        if (!coloring.containsKey(live)) {
                            live_after.remove(live);
                        }
                    }

                    List<StoreInst> outer_store_insts = new ArrayList<>();
                    HashSet<String> usable_regs = new HashSet<>(registers);
                    usable_regs.removeAll(live_after);
                    Iterator<String> live_after_iter = live_after.iterator();
                    int extra_regs_needed = (using.size() + defining.size()) -
                        (registers.size() - live_after.size());
                    for (int j = 0; j < extra_regs_needed; j++) {
                        String var = live_after_iter.next();
                        StoreInst store = new StoreInst(new String[]{var, coloring.get(var)});
                        outer_store_insts.add(store);
                        instructions.add(new InstRegallocPair(store, null));
                    }

                    Iterator<String> regs = usable_regs.iterator();
                    for (String use : using) {
                        String reg = regs.next();
                        var_reg_map.put(use, reg);
                        instructions.add(new InstRegallocPair(new LoadInst(new String[]{reg, use}), null));
                    }
                    List<StoreInst> store_insts = new ArrayList<>();
                    for (String def : defining) {
                        String reg = regs.next();
                        var_reg_map.put(def, reg);
                        instructions.add(new InstRegallocPair(new StoreInst(new String[]{def, reg}), null));
                    }
                    instructions.add(new InstRegallocPair(inst.with_registers(var_reg_map), var_reg_map));
                    for (Instruction store : store_insts) {
                        instructions.add(new InstRegallocPair(store, null));
                    }

                    for (StoreInst store : outer_store_insts) {
                        instructions.add(new InstRegallocPair(store.reverse(), null));
                    }
                }
            }
        }

        return instructions;
    }

    public void save_to_file(List<InstRegallocPair> instructions, String file_name) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file_name));
            for (InstRegallocPair pair : instructions) {
                bw.write(pair.get_inst().toString() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void translate_to_mips(List<FunctionIR> funcs, List<InstRegallocPair> instructions, String file_name, MIPSGenerator mips) {
        List<InstRegallocPair> init_moved = this.move_init_calls(instructions);

        if(!init_moved.get(0).get_inst().type().equals("label")) {
            mips.text.add(this.name + ":");
        } else if(init_moved.get(0).get_inst().type().equals("label")) {
            LabelInst label = (LabelInst) init_moved.get(0).get_inst();
            if(!label.get_name().equals(this.name)) {
                mips.text.add(this.name + ":");
            }
        }
        for(InstRegallocPair inst: init_moved) {
            mips.translate(funcs, inst);
        }
        mips.add_stack_setup(this);
        // mips.add_stack_setup(this, instructions);
    }

    public List<InstRegallocPair> move_init_calls(List<InstRegallocPair> instructions) {
        int location_of_function = 0;
        boolean function_label_exists = false;

        int location_of_first_label = 0;
        boolean got_first_label = false;

        for(InstRegallocPair pair: instructions) {
            if(pair.get_inst().type().equals("label")) {
                got_first_label = true;
                LabelInst label = (LabelInst) pair.get_inst();
                if(label.get_name().equals(this.name)) {
                    function_label_exists = true;
                    break;
                }
            }
            if(!got_first_label) {
                location_of_first_label++;
            }
            location_of_function++;
        }

        List<InstRegallocPair> init = null;
        List<InstRegallocPair> rest = null;
        if(got_first_label && function_label_exists && location_of_function != 0) {
            int size_of_init = location_of_first_label;
            init = instructions.subList(0, size_of_init);
            rest = instructions.subList(size_of_init, instructions.size());
            rest.addAll((location_of_function - size_of_init) + 1, init);
        }
        if(rest == null) {
            rest = instructions;
        }
        return rest;
    }

    public TreeMap<String, String> args() {
        return this.args;
    }

    public String name() {
        return this.name;
    }

    public ArrayList<List<String>> getText() {
        return this.text;
    }
}
