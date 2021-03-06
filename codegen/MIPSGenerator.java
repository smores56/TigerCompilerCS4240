// The only things that can accept labels are load, store, array_assign, and assign

import instructions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class MIPSGenerator {
    private int stackPointer;
    private int topOfStack;
    private int framePointer;
    private int totalStackSize;
    private List<String> data;
    public List<String> text;
    private HashSet<String> intsSet;
    private HashSet<String> floatsSet;
    private String[] ints;
    private String[] floats;
    private HashMap<String, String> arrays;
    private String name;
    private HashMap<String, Integer> variable_locations;

    public MIPSGenerator(FunctionIR func, String[] ints, String[] floats, String name, List<InstRegallocPair> instructions) {
        this.stackPointer = 0;
        this.framePointer = 0;
        this.totalStackSize = 0;
        this.data = new ArrayList<>();
        this.data.add(".data");
        this.text = new ArrayList<>();
        this.name = name;
        this.ints = ints;
        this.floats = floats;
        this.intsSet = new HashSet<String>();
        this.floatsSet = new HashSet<String>();
        this.arrays = new HashMap<String, String>();
        this.variable_locations = this.variable_locations(func, instructions);
        // this.initialize_vars(ints, floats);
    }
    //
    // private void initialize_vars(String[] ints, String[] floats) {
    //     for (String i: ints) {
    //         if (this.variable_locations.containsKey(i) || i.contains("[")) {
    //             String newString = i;
    //             if(newString.contains("[")) {
    //                 newString = newString.split("\\[", 0)[0];
    //                 this.arrays.put(i, newString);
    //             }
    //             if(is_register(newString)) {
    //                 this.intsSet.add(this.name + "__" + newString.substring(1, newString.length()));
    //             } else {
    //                 this.intsSet.add(this.name + "__" + newString);
    //
    //             }
    //         }
    //     }
    //     for(String f : floats) {
    //         if (this.variable_locations.containsKey(f) || f.contains("[")) {
    //             String newString = f;
    //             if(newString.contains("[")) {
    //                 newString = newString.split("\\[", 0)[0];
    //                 this.arrays.put(f, newString);
    //             }
    //             if(is_register(f)) {
    //                 this.floatsSet.add(this.name + "__" + f.substring(1, f.length()));
    //             } else {
    //                 this.floatsSet.add(this.name + "__" + f);
    //             }
    //         }
    //     }
    // }

    private boolean is_register(String s) {
        return s.charAt(0) == '$';
    }

    private Float parse_float(String s) {
        try {
            Float f = Float.parseFloat(s);
            return f;
        } catch (NumberFormatException e){
            return null;
        }
    }

    public void add_stack_setup(FunctionIR func) {
        List<String> regs = Arrays.asList("$s0", "$s1", "$s2", "$s3", "$s4", "$s5");
        int offset = 0;
        for (String reg : regs) {
            this.text.add(1, String.format("\tsw %s, %d($sp)", reg, offset));
            offset -= 4;
        }
        this.text.add(1, String.format("\taddi $sp, $sp, %d", -(this.totalStackSize - func.args().size() * 4)));
    }

    private void teardown(FunctionIR func) {
        int args_size = 0;
        if (func != null) {
            args_size = func.args().size() * 4;
        }
        this.text.add(String.format("\taddi $sp, $sp, %d", this.totalStackSize - args_size));
        int offset = 0;
        List<String> regs = Arrays.asList("$s0", "$s1", "$s2", "$s3", "$s4", "$s5");
        for (String reg : regs) {
            this.text.add(String.format("\tlw %s, %d($sp)", reg, offset));
            offset -= 4;
        }
    }

    private Integer parse_int(String s) {
        try {
            Integer i = Integer.parseInt(s);
            return i;
        } catch (NumberFormatException e){
            return null;
        }
    }

    public void translate(List<FunctionIR> funcs, InstRegallocPair inst) {
        String instructionType = inst.get_inst().type();
        List<String> params = inst.get_inst().params();
        String name_of_label;
        int offset = 0;
        switch(instructionType) {
            case "label":
                this.text.add(String.format("%s:", params.get(0)));
                return;
            case "assign":
                if(is_register(params.get(1))) {
                    this.text.add(String.format("\tadd %s, %s, %s", params.get(0), params.get(1), "$zero"));
                } else {
                    this.text.add(String.format("\taddi %s, %s, %s", params.get(0), "$zero", params.get(1)));
                }
                return;
            case "and":
                String andparam0 = params.get(0);
                String andparam1 = params.get(1);
                if(!is_register(params.get(1))) {
                    andparam1 = "$t7";
                    this.text.add(String.format("\tli %s, %s", andparam1, params.get(1)));
                }

                if(!is_register(params.get(0))) {
                    andparam0 = "$t6";
                    this.text.add(String.format("\tli %s, %s", andparam0, params.get(0)));
                }

                this.text.add(String.format("\tand %s, %s, %s", params.get(2), andparam0, andparam1));
                return;
            case "or":
                String orparam0 = params.get(0);
                String orparam1 = params.get(1);
                if(!is_register(params.get(1))) {
                    orparam1 = "$t7";
                    this.text.add(String.format("\tli %s, %s", orparam1, params.get(1)));
                }

                if(!is_register(params.get(0))) {
                    orparam0 = "$t6";
                    this.text.add(String.format("\tli %s, %s", orparam0, params.get(0)));
                }
                this.text.add(String.format("\tor %s, %s, %s", params.get(2), orparam0, orparam1));
                return;
            case "add":
                String addparam0 = params.get(0);
                String addparam1 = params.get(1);
                if(!is_register(params.get(1))) {
                    addparam1 = "$t7";
                    this.text.add(String.format("\tli %s, %s", addparam1, params.get(1)));
                }

                if(!is_register(params.get(0))) {
                    addparam0 = "$t6";
                    this.text.add(String.format("\tli %s, %s", addparam0, params.get(0)));
                }
                this.text.add(String.format("\tadd %s, %s, %s", params.get(2), addparam0, addparam1));
                return;
            case "sub":
                String subparam0 = params.get(0);
                String subparam1 = params.get(1);
                if(!is_register(params.get(1))) {
                    subparam1 = "$t7";
                    this.text.add(String.format("\tli %s, %s", subparam1, params.get(1)));
                }

                if(!is_register(params.get(0))) {
                    subparam0 = "$t6";
                    this.text.add(String.format("\tli %s, %s", subparam0, params.get(0)));
                }
                this.text.add(String.format("\tsub %s, %s, %s", params.get(2), subparam0, subparam1));
                return;
            case "mult":
                String multparam0 = params.get(0);
                String multparam1 = params.get(1);
                if(!is_register(params.get(1))) {
                    multparam1 = "$t7";
                    this.text.add(String.format("\tli %s, %s", multparam1, params.get(1)));
                }

                if(!is_register(params.get(0))) {
                    multparam0 = "$t6";
                    this.text.add(String.format("\tli %s, %s", multparam0, params.get(0)));
                }


                this.text.add(String.format("\tmult %s, %s",multparam0, multparam1));
                this.text.add(String.format("\tmflo %s", params.get(2)));
                return;
            case "div":
                String divparam0 = params.get(0);
                String divparam1 = params.get(1);
                if(!is_register(params.get(1))) {
                    divparam1 = "$t7";
                    this.text.add(String.format("\tli %s, %s", divparam1, params.get(1)));
                }
                if(!is_register(params.get(0))) {
                    divparam0 = "$t6";
                    this.text.add(String.format("\tli %s, %s", divparam0, params.get(0)));
                }
                this.text.add(String.format("\tdiv %s, %s",divparam0, divparam1));
                this.text.add(String.format("\tmflo %s", params.get(2)));
                return;
            case "store":
                offset = this.var_offset(params.get(0));
                this.text.add(String.format("\tsw %s, %d($sp)", params.get(1), offset));
                return;
            case "load":
                offset = this.var_offset(params.get(1));
                this.text.add(String.format("\tlw %s, %d($sp)", params.get(0), offset));
                return;
            case "array_load":
                if (parse_int(params.get(2)) != null) {
                    this.text.add(String.format("\tli $t6, %s", params.get(2)));
                } else {
                    this.text.add(String.format("\taddi $t6, %s, 0", params.get(2)));
                }
                this.text.add("\tadd $t6, $t6, $t6");
                this.text.add("\tadd $t6, $t6, $t6");
                this.text.add(String.format("\taddi $t6, $t6, %d", this.var_offset(params.get(1))));
                this.text.add(String.format("\tlw %s, $t6($sp)", params.get(0)));
                return;
            case "array_store":
                if (parse_int(params.get(1)) != null) {
                    this.text.add(String.format("\tli $t6, %s", params.get(1)));
                } else {
                    this.text.add(String.format("\taddi $t6, %s, 0", params.get(1)));
                }
                this.text.add("\tadd $t6, $t6, $t6");
                this.text.add("\tadd $t6, $t6, $t6");

                this.text.add(String.format("\taddi $t6, $t6, %d", this.var_offset(params.get(0))));
                if (parse_float(params.get(2)) != null) {
                    this.text.add(String.format("\tli $t5, %s", params.get(2)));
                    this.text.add("\tsw $t5, $t6($sp)");
                } else {
                    this.text.add(String.format("\tsw %s, $t6($sp)", params.get(2)));
                }
                return;
            case "array_assign":
                this.text.add("\tli $t8, " + params.get(2));
                offset = 0;
                for(int i = 0; i < parse_int(params.get(1)); i++) {
                    this.text.add(String.format("\tsw $t8, %d($sp)", (this.var_offset(params.get(0)) + i * 4)));
                }
                return;
            case "brneq":
                String brneq0 = params.get(0);
                String brneq1 = params.get(1);
                if (!is_register(brneq0)) {
                    brneq0 = "$t7";
                    this.text.add(String.format("\tli %s, %s", brneq0, params.get(0)));
                }
                if (!is_register(brneq1)) {
                    brneq1 = "$t6";
                    this.text.add(String.format("\tli %s, %s", brneq1, params.get(1)));
                }
                this.text.add(String.format("\tbeq %s, %s, %s", brneq0, brneq1, params.get(2)));
                return;
            case "breq":
                String breq0 = params.get(0);
                String breq1 = params.get(1);
                if (!is_register(breq0)) {
                    breq0 = "$t7";
                    this.text.add(String.format("\tli %s, %s", breq0, params.get(0)));
                }
                if (!is_register(breq1)) {
                    breq1 = "$t6";
                    this.text.add(String.format("\tli %s, %s", breq1, params.get(1)));
                }
                this.text.add(String.format("\tbne %s, %s, %s", breq0, breq1, params.get(2)));
                return;
            case "brlt":
                String brlt0 = params.get(0);
                String brlt1 = params.get(1);
                if (!is_register(brlt0)) {
                    brlt0 = "$t7";
                    this.text.add(String.format("\tli %s, %s", brlt0, params.get(0)));
                }
                if (!is_register(brlt1)) {
                    brlt1 = "$t6";
                    this.text.add(String.format("\tli %s, %s", brlt1, params.get(1)));
                }
                this.text.add(String.format("\tsub %s, %s, %s", brlt0, brlt1, "$t7"));
                this.text.add(String.format("\tblez %s, %s", "$t7", params.get(2)));
                return;
            case "brgt":
                String brgt0 = params.get(0);
                String brgt1 = params.get(1);
                if (!is_register(brgt0)) {
                    brgt0 = "$t7";
                    this.text.add(String.format("\tli %s, %s", brgt0, params.get(0)));
                }
                if (!is_register(brgt1)) {
                    brgt1 = "$t6";
                    this.text.add(String.format("\tli %s, %s", brgt1, params.get(1)));
                }
                this.text.add(String.format("\tsub %s, %s, %s", brgt0, brgt1, "$t7"));
                this.text.add(String.format("\tbgez %s, %s", "$t7",  params.get(2)));
                return;
            case "brleq":
                String brleq0 = params.get(0);
                String brleq1 = params.get(1);
                if (!is_register(brleq0)) {
                    brleq0 = "$t7";
                    this.text.add(String.format("\tli %s, %s", brleq0, params.get(0)));
                }
                if (!is_register(brleq1)) {
                    brleq1 = "$t6";
                    this.text.add(String.format("\tli %s, %s", brleq1, params.get(1)));
                }
                this.text.add(String.format("\tsub %s, %s, %s", brleq0, brleq1, "$t7"));
                this.text.add(String.format("\tbltz %s, %s", "$t7", params.get(2)));
                return;
            case "brgeq":
                String brgeq0 = params.get(0);
                String brgeq1 = params.get(1);
                if (!is_register(brgeq0)) {
                    brgeq0 = "$t7";
                    this.text.add(String.format("\tli %s, %s", brgeq0, params.get(0)));
                }
                if (!is_register(brgeq1)) {
                    brgeq1 = "$t6";
                    this.text.add(String.format("\tli %s, %s", brgeq1, params.get(1)));
                }
                this.text.add(String.format("\tsub %s, %s, %s", brgeq0, brgeq1, "$t7"));
                this.text.add(String.format("\tbgtz %s, %s", "$t7", params.get(2)));
                return;
            case "call":
                if(params.get(0).equals("printi")) {
                    this.print(params.get(1), true);
                } else if(params.get(0).equals("printf")) {
                    this.print(params.get(1), false);
                } else {
                    this.text.add(String.format("\taddi $sp, $sp, %d", (params.size() - 1) * 4));
                    offset = (params.size() - 2) * -4;
                    for (String arg : params.subList(1, params.size())) {
                        String reg2 = arg;
                        if (parse_int(arg) != null) {
                            this.text.add(String.format("\taddi $t1, $zero, %s", arg));
                            reg2 = "$t1";
                        }
                        this.text.add(String.format("\tsw %s, %d($sp)", reg2, offset));
                        offset += 4;
                    }
                    this.text.add(String.format("\tjal %s", params.get(0)));
                    this.text.add(String.format("\taddi $sp, $sp, %d", (params.size() - 1) * -4));
                }
                return;
            case "callr":
                this.text.add(String.format("\taddi $sp, $sp, %d", (params.size() - 2) * 4));
                offset = (params.size() - 2) * -4;
                for (String arg : params.subList(2, params.size())) {
                    String reg1 = arg;
                    if (parse_int(arg) != null) {
                        this.text.add(String.format("\taddi $t1, $zero, %s", arg));
                        reg1 = "$t1";
                    }
                    this.text.add(String.format("\tsw %s, %d($sp)", reg1, offset));
                    offset += 4;
                }
                this.text.add(String.format("\tjal %s", params.get(1)));
                this.text.add(String.format("\taddi $sp, $sp, %d", (params.size() - 2) * -4));
                this.text.add(String.format("\taddi %s, $v0, 0", params.get(0)));
                return;
            case "goto":
                this.text.add(String.format("\tj %s", params.get(0)));
                return;
            case "empty_return":
                this.teardown(null);
                this.text.add(String.format("\tj $ra"));
                return;
            case "return":
                this.text.add(String.format("\tadd $v0, %s, %s", params.get(0), "$zero"));
                this.teardown(null);
                this.text.add(String.format("\tj $ra"));
                return;
            default:
                System.out.println(String.format("Handle %s, %s", inst.get_inst().type(), inst.toString())); // TODO: remove this
                return;
        }
    }

    public void print(String register_with_argument, boolean integer) {
        if(integer) {
            this.text.add("\tli $v0, 1");
            if (parse_int(register_with_argument) != null) {
                this.text.add(String.format("\taddi $a0, $zero, %s", register_with_argument));
            } else {
                this.text.add(String.format("\tadd $a0, %s, $zero", register_with_argument));
            }
        } else {
            this.text.add("\tli $v0, 2");
            if (parse_float(register_with_argument) != null) {
                this.text.add(String.format("\taddi $a0, $zero, %s", register_with_argument));
            } else {
                this.text.add(String.format("\tadd $a0, %s, $zero", register_with_argument));
            }
        }
        this.text.add("\tsyscall");
    }

    public HashMap<String, Integer> variable_locations(FunctionIR func, List<InstRegallocPair> instructions) {
        List<String> regs = Arrays.asList("$s0", "$s1", "$s2", "$s3", "$s4", "$s5");
        HashSet<String> stack_vars = new HashSet<>();
        for (InstRegallocPair pair : instructions) {
            Instruction inst = pair.get_inst();
            HashMap<String, String> var_reg_map = pair.get_regalloc();
            if (inst instanceof LoadInst) {
                stack_vars.add(inst.params().get(1));
            } else if (inst instanceof StoreInst) {
                stack_vars.add(inst.params().get(0));
            }
        }
        HashMap<String, Integer> arrays = new HashMap<>();
        for (String i : this.ints) {
            if (i.contains("[")) {
                arrays.put(i.split("\\[")[0], Integer.parseInt(i.split("\\[")[1].split("\\]")[0]));
            }
        }
        for (String f : this.floats) {
            if (f.contains("[")) {
                arrays.put(f.split("\\[")[0], Integer.parseInt(f.split("\\[")[1].split("\\]")[0]));
            }
        }
        int total_array_size = 0;
        for (int arr_size : arrays.values()) {
            total_array_size += arr_size;
        }

        HashMap<String, Integer> var_locations = new HashMap<>();
        this.totalStackSize = 4 * (regs.size() + stack_vars.size() + func.args().size() + total_array_size);
        int offset = this.totalStackSize;
        for (String arg : func.args().keySet()) {
            var_locations.put(arg, offset);
            offset -= 4;
        }
        offset -= 4 * regs.size();
        for (String var : stack_vars) {
            var_locations.put(var, offset);
            offset -= 4;
        }
        for (String arr_name : arrays.keySet()) {
            var_locations.put(arr_name, offset - arrays.get(arr_name) * 4);
            offset -= arrays.get(arr_name) * 4;
        }

        System.out.println(var_locations);
        System.out.println(this.totalStackSize);

        return var_locations;
    }

    public int var_offset(String var) {
        return this.variable_locations.get(var);
    }
}
