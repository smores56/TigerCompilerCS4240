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
        this.text.add(".text");
        this.name = name;
        this.ints = ints;
        this.floats = floats;
        this.intsSet = new HashSet<String>();
        this.floatsSet = new HashSet<String>();
        this.arrays = new HashMap<String, String>();
        this.variable_locations = this.variable_locations(instructions);
        this.initialize_vars(ints, floats);
    }

    private void initialize_vars(String[] ints, String[] floats) {
        for (String i: ints) {
            if (this.variable_locations.containsKey(i) || newString.contains("[")) {
                String newString = i;
                if(newString.contains("[")) {
                    newString = newString.split("\\[", 0)[0];
                    this.arrays.put(i, newString);
                }
                if(is_register(newString)) {
                    this.intsSet.add(this.name + "__" + newString.substring(1, newString.length()));
                } else {
                    this.intsSet.add(this.name + "__" + newString);

                }
            }
        }
        for(String i: floats) {
            if (this.variable_locations.containsKey(i) || newString.contains("[")) {
                String newString = i;
                if(newString.contains("[")) {
                    newString = newString.split("\\[", 0)[0];
                    this.arrays.put(i, newString);
                }
                if(is_register(i)) {
                    this.floatsSet.add(this.name + "__" + i.substring(1, i.length()));
                } else {
                    this.floatsSet.add(this.name + "__" + i);
                }
            }
        }
    }

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
            this.text.add(1, String.format("sw %s, %d($sp)", reg, offset));
            offset -= 4;
        }
        this.text.add(1, String.format("subi $sp, $sp, %d", this.totalStackSize - func.args().size() * 4);
    }

    private void teardown(FunctionIR func) {
        this.text.add(String.format("\taddi $sp, $sp, %d", this.totalStackSize - func.args().size() * 4));
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
                    this.text.add(String.format("\tli %s, %s", andparam0, params.get(1)));
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
                    this.text.add(String.format("\tli %s, %s", orparam0, params.get(1)));
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
                    this.text.add(String.format("\tli %s, %s", addparam0, params.get(1)));
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
                    this.text.add(String.format("\tli %s, %s", subparam0, params.get(1)));
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
                    this.text.add(String.format("\tli %s, %s", multparam0, params.get(1)));
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
                    this.text.add(String.format("\tli %s, %s", divparam0, params.get(1)));
                }
                this.text.add(String.format("\tdiv %s, %s",divparam0, divparam1));
                this.text.add(String.format("\tmflo %s", params.get(2)));
                return;
            case "store":
                int offset = this.var_offset(params.get(0));
                this.text.add(String.format("\tsw %s, %d($sp)", params.get(1), offset));
                return;
            case "load":
                int offset = this.var_offset(params.get(1));
                this.text.add(String.format("\tlw %s, %d($sp)", params.get(0), offset));
                return;
            case "array_load":
                this.text.add(String.format("\tlw %s, %d($sp)", "$t7", this.var_offset(params.get(0))));
                this.text.add(String.format("\tli %s, %s", "$t6", params.get(1)));
                this.text.add(String.format("\tadd %s, %s, %s", "$t6", "$t6","$t6"));
                this.text.add(String.format("\tadd %s, %s, %s", "$t6", "$t6","$t6"));
                this.text.add(String.format("\tadd %s, %s, %s", "$t5", "$t6","$t7"));
                this.text.add(String.format("\tlw %s, %d(%s)", params.get(0), "$zero", "$t5"));
                return;
            case "array_store":
                this.text.add(String.format("\tlw %s, %d($sp)", "$t7", this.var_offset(params.get(1))));
                this.text.add(String.format("\tli %s, %s", "$t6", params.get(2)));
                this.text.add(String.format("\tadd %s, %s, %s", "$t6", "$t6","$t6"));
                this.text.add(String.format("\tadd %s, %s, %s", "$t6", "$t6","$t6"));
                this.text.add(String.format("\tadd %s, %s, %s", "$t5", "$t6","$t7"));
                this.text.add(String.format("\tsw %s, 0(%s)", params.get(0), "$t5"));
                return;
            case "array_assign":
                this.text.add("\tli $t8, " + params.get(2));
                for(int i = 0; i < parse_int(params.get(1)); i = i + 4) {
                    this.text.add(String.format("\tsw $t8, %d($sp)", -(i + this.var_location(params.get(0)))));
                }
                return;
            case "brneq":
                this.text.add(String.format("\tbeq %s, %s, %s", params.get(0),  params.get(1),  params.get(2)));
                return;
            case "breq":
<<<<<<< HEAD
                // this.text.add(String.format("\tsub %s, %s, %s", params.get(0), params.get(1), "$t7"));
                // String breglabel = hash_code(params.get(0));
                this.text.add(String.format("\tbne %s, %s, %s", params.get(0), params.get(1), params.get(2)));
=======
                this.text.add(String.format("\tsub %s, %s, %s", "$t7", params.get(0), params.get(1)));
                String breglabel = hash_code(params.get(0));
                this.text.add(String.format("\tbeq %s, %s, %s", "$t7",  "$zero",  breglabel));
>>>>>>> b4cb5c7e3554680203736a5c048af818e94e17f8
                return;
            case "brlt":
                this.text.add(String.format("\tsub %s, %s, %s", params.get(0), params.get(1), "$t7"));
                // String breglabel = hash_code(params.get(0));
                this.text.add(String.format("\tblez %s, %s", "$t7", params.get(2)));
                return;
            case "brgt":
                this.text.add(String.format("\tsub %s, %s, %s", params.get(0), params.get(1), "$t7"));
                // String brgtlabel = hash_code(params.get(0));
                this.text.add(String.format("\tbgez %s, %s", "$t7",  params.get(2)));
                return;
            case "brleq":
                this.text.add(String.format("\tsub %s, %s, %s", params.get(0), params.get(1), "$t7"));
                // String brleqlabel = hash_code(params.get(0));
                this.text.add(String.format("\tbltz %s, %s", "$t7", params.get(2)));
                return;
            case "brgeq":
                this.text.add(String.format("\tsub %s, %s, %s", params.get(0), params.get(1), "$t7"));
                // String brgeqlabel = hash_code(params.get(0));
                this.text.add(String.format("\tbgtz %s, %s", "$t7", params.get(2)));
                return;
            case "call":
                if(params.get(0).equals("printi")) {
                    this.print(params.get(1), true);
                } else if(params.get(0).equals("printf")) {
                    this.print(params.get(1), false);
                } else {
                    FunctionIR func = null;
                    for (FunctionIR f : funcs) {
                        if (f.name().equals(params.get(0))) {
                            func = f;
                            break;
                        }
                    }
                    TreeMap<String, String> args = func.args();
                    this.text.add(String.format("\taddi $sp, $sp, %d", args.size() * 4));
                    int offset = -args.size();
                    for (String arg : arg.keySet()) {
                        String arg_reg = inst.get_regalloc().get(arg);
                        this.text.add(String.format("\tsw %s, %d($sp)", arg_reg, offset));
                        offset += 4;
                    }
                    this.text.add(String.format("\tjal %s", func.name()));
                    this.text.add(String.format("\tsubi $sp, $sp, %d", args.size() * 4));
                }
                return;
            case "callr":
                FunctionIR func = null;
                for (FunctionIR f : funcs) {
                    if (f.name().equals(params.get(0))) {
                        func = f;
                        break;
                    }
                }
                TreeMap<String, String> args = func.args();
                this.text.add(String.format("\taddi $sp, $sp, %d", args.size() * 4));
                int offset = -args.size();
                for (String arg : arg.keySet()) {
                    String arg_reg = inst.get_regalloc().get(arg);
                    this.text.add(String.format("\tsw %s, %d($sp)", arg_reg, offset));
                    offset += 4;
                }
                this.text.add(String.format("\tjal %s", func.name()));
                this.text.add(String.format("\tsubi $sp, $sp, %d", args.size() * 4));
                this.text.add(String.format("\taddi %s, $v0, 0", params.get(0)));
                return;
            case "goto":
                this.text.add(String.format("\tj %s", params.get(0)));
                return;
            case "empty_return":
                FunctionIR func = null;
                for (FunctionIR f : funcs) {
                    if (f.name().equals(params.get(0))) {
                        func = f;
                        break;
                    }
                }
                this.teardown(func);
                this.text.add(String.format("\tj $ra"));
                return;
            case "return":
                this.text.add(String.format("\tadd $v0, %s, %s", params.get(0), "$zero"));
                FunctionIR func = null;
                for (FunctionIR f : funcs) {
                    if (f.name().equals(params.get(0))) {
                        func = f;
                        break;
                    }
                }
                this.teardown(func);
                this.text.add(String.format("\tj $ra"));
                return;
            default:
                System.out.println(String.format("Handle %s, %s", inst.get_inst().type(), inst.toString())); // TODO: remove this
                return;
        }
    }

    private String hash_code(String label) {
        String name_of_label;
        if(is_register(label)) {
            name_of_label = this.name + "__" + label.substring(1, label.length());
        } else {
            name_of_label = this.name + "__" + label;
        }

        if(this.intsSet.contains(name_of_label) || this.floatsSet.contains(name_of_label)) {
            return name_of_label;
        }
        return label;
    }

    public void print(String register_with_argument, boolean integer) {
        if(integer) {
            this.text.add("\tli $v0, 1");
            this.text.add(String.format("\tmv $a0, %s", register_with_argument));
        } else {
            this.text.add("\tli $v0, 2");
            this.text.add(String.format("\tmv $f12, %s", register_with_argument));
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
                arrays.put(i.split("[")[0], Integer.parseInt(i.split("[")[1].split("]")[0]));
            }
        }
        for (String f : this.floats) {
            if (f.contains("[")) {
                arrays.put(f.split("[")[0], Integer.parseInt(f.split("[")[1].split("]")[0]));
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
            stack_vars.put(arg, offset);
            offset -= 4;
        }
        offset -= 4 * regs.size();
        for (String var : stack_vars) {
            stack_vars.put(var, offset);
            offset -= 4;
        }
        for (String arr_name : arrays) {
            stack_vars.put(arr_name, offset - arrays.get(arr_name) * 4);
            offset -= arrays.get(arr_name) * 4;
        }

        return var_locations;
    }

    public int var_offset(String var) {
        return this.variable_locations.get(var);
    }
}
