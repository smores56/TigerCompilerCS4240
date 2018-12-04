import instructions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// The only things that can accept labels are load, store, array_assign, and assign


public class MIPSGenerator {
    private int stackPointer;
    private int framePointer;
    private List<String> data;
    private List<String> text;
    private HashSet<String> intsSet;
    private HashSet<String> floatsSet;
    private String[] ints;
    private String[] floats;
    private HashMap<String, String> arrays;
    
    private String name;

    

    public MIPSGenerator(String[] ints, String[] floats, String name) {
        this.stackPointer = 0;
        this.framePointer = 0;
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
        this.initialize_vars(ints, floats);
    }

    private void initialize_vars(String[] ints, String[] floats) {
        for(String i: ints) {
            if(!i.equals("")) {
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
            if(!i.equals("")) {
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

    public List<String> get_data(List<InstRegallocPair> instructions) {
        for(String intDecl: this.ints) {
            if(!intDecl.equals("")) {
                if(intDecl.matches(".*(\\[\\d*])+")){
                    int size = 0;
                    String arr_name = this.arrays.get(intDecl);
                    String sizes = intDecl.replaceFirst("^" + arr_name, "");                
                    for(String amount: sizes.split("\\[")) {
                        if(!amount.equals("")) {
                            String one = amount.replace("]", "");
                            Integer amountInt = Integer.parseInt(one);
                            if(size == 0) {
                                size++;
                            }
                            size = size * 32 * amountInt;
                        }

                    } 
                    this.data.add(String.format("\t%s: .space %s", hash_code(arr_name), size));
                } else {
                    this.data.add(String.format("\t%s: .space %s", hash_code(intDecl) , 32)); // how big is an int?
                }
            }
        }


        for(String floatDecl: this.floatsSet) {
            if(!floatDecl.equals("")) {
                if(floatDecl.matches(".*(\\[\\d*])+")){
                    int size = 0;
                    String arr_name = this.arrays.get(floatDecl);
                    String sizes = floatDecl.replaceFirst("^" + arr_name, "");                
                    for(String amount: sizes.split("\\[")) {
                        if(!amount.equals("")) {
                            String one = amount.replace("]", "");
                            Integer amountInt = Integer.parseInt(one);
                            if(size == 0) {
                                size++;
                            }
                            size = size * 32 * amountInt;
                        }

                    } 
                    this.data.add(String.format("\t%s: .space %s", hash_code(arr_name), size));
                } else {
                    this.data.add(String.format("\t%s: .space %s", hash_code(floatDecl), 32));
                }
            }
        }
        
        return this.data;
    }

    public List<String> get_text() {
        return this.text;
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

    // private boolean is_float(InstRegallocPair inst) {

    //     for(Map<K,V>.Entry<String, String> entry : inst.get_regalloc().entrySet()) {
    //         if(floatsSet.contains(hash_code(entry.getKey()))) {
    //             return true;
    //         }
    //         if(intsSet.contains(hash_code(entry.getKey()))) {
    //             return false;
    //         }
    //     }        

    //     return false;
    // }

    private Integer parse_int(String s) {
        try {
            Integer i = Integer.parseInt(s);
            return i;
        } catch (NumberFormatException e){
            return null;
        }
    }



    public void translate(InstRegallocPair inst) {
        String instructionType = inst.get_inst().type();
        List<String> params = inst.get_inst().params();
        String name_of_label;
        switch(instructionType) {
            case "label":
                this.text.add(String.format("%s:", params.get(0)));
                return;
            case "assign":
                if(is_register(params.get(1))) {
                    // if the value is stored in a register, then load from one register to the next with add r1, r2, $zero
                    // if(!is_float(inst)) {
                        // if its an int, use normal add instruction
                        this.text.add(String.format("\tadd %s, %s, %s", params.get(0), params.get(1), "$zero"));
                    // } else {
                        // if float, first, store the first register into a float register, and store $zero into a float register. Use add.s
                        // this.text.add(String.format("\tli.s %s, %s", "$f0", params.get(1)));
                        // this.text.add(String.format("\tli.s %s, %s", "$f1", "$zero"));
                        // this.text.add(String.format("\tadd.s %s, %s, %s", params.get(0), "$f0", "$f1"));
                    // }
                } else {
                    // if the value is an immediate value
                    // if(parse_int(params.get(1)) != null) {
                        // If the value is an int, add immediate with addi
                        this.text.add(String.format("\taddi %s, %s, %s", params.get(0), "$zero", params.get(1)));
                    // } else {
                    //     // If the value is a float, first load int 
                    //     this.text.add(String.format("\tli.s %s, %s", params.get(0), params.get(1)));
                    // }
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
                String storelabel = hash_code(params.get(0));
                this.text.add(String.format("\tsw %s, %s(%s)", params.get(1), storelabel, "$zero"));
                return;
            case "load":
                String loadlabel = hash_code(params.get(0));
                this.text.add(String.format("\tlw %s, %s(%s)", params.get(1), loadlabel, "$zero"));
                return;
            case "array_load":
                this.text.add(String.format("\tla %s, %s", "$t7", params.get(1)));
                this.text.add(String.format("\tli %s, %s", "$t6", params.get(2)));
                this.text.add(String.format("\tadd %s, %s, %s", "$t6", "$t6","$t6"));
                this.text.add(String.format("\tadd %s, %s, %s", "$t6", "$t6","$t6"));
                this.text.add(String.format("\tadd %s, %s, %s", "$t5", "$t6","$t7"));
                this.text.add(String.format("\tlw %s, %s(%s)", params.get(0), "$zero", "$t5"));
                return;
            case "array_store":
                this.text.add(String.format("\tla %s, %s", "$t7", params.get(1)));
                this.text.add(String.format("\tli %s, %s", "$t6", params.get(2)));
                this.text.add(String.format("\tadd %s, %s, %s", "$t6", "$t6","$t6"));
                this.text.add(String.format("\tadd %s, %s, %s", "$t6", "$t6","$t6"));
                this.text.add(String.format("\tadd %s, %s, %s", "$t5", "$t6","$t7"));
                this.text.add(String.format("\tsw %s, %s(%s)", params.get(0), "$zero","$t5"));
                return;
            case "array_assign":
                this.text.add("\tli $t8, " + params.get(2));
                for(int i = 0; i < parse_int(params.get(1)); i = i + 4) {
                    this.text.add("\tsw $t8, -" + i + "(" + hash_code(params.get(0)) + ")");
                }
                return;
            case "brneq":
                this.text.add(String.format("\tbeq %s, %s, %s", params.get(0),  params.get(1),  params.get(2)));
                return;
            case "breg":
                this.text.add(String.format("\tsub %s, %s, %s", params.get(0), params.get(1), "$t7"));
                String breglabel = hash_code(params.get(0));
                this.text.add(String.format("\tbeq %s, %s, %s", "$t7",  "$zero",  breglabel));
                return;
            case "brlt":
                this.text.add(String.format("\tsub %s, %s, %s", params.get(0), params.get(1), "$t7"));
                String brltlabel = hash_code(params.get(0));
                this.text.add(String.format("\tbgez %s, %s", "$t7",  brltlabel));
                return;
            case "brgt":
                this.text.add(String.format("\tsub %s, %s, %s", params.get(1), params.get(0), "$t7"));
                String brgtlabel = hash_code(params.get(0));
                this.text.add(String.format("\tbgez %s, %s", "$t7",  brgtlabel));
                return;
            case "brleq":
                this.text.add(String.format("\tsub %s, %s, %s", params.get(0), params.get(1), "$t7"));
                String brleqlabel = hash_code(params.get(0));
                this.text.add(String.format("\tbgtz %s, %s", "$t7", brleqlabel));
                return;
            case "brgeq":
                this.text.add(String.format("\tsub %s, %s, %s", params.get(1), params.get(0), "$t7"));
                String brgeqlabel = hash_code(params.get(0));
                this.text.add(String.format("\tbgtz %s, %s", "$t7", brgeqlabel));
                return;
            case "call":
                if(params.get(0).equals("printi")) {
                    this.print(params.get(1), true);
                } else if(params.get(0).equals("printf")) {
                    this.print(params.get(1), false);
                } else {
                    System.out.println("Get the rest of the function working! " + inst.toString());

                    // for(int i = 0; i < Math.min(params.size(), 4); i++) { // This is super hacky
                    //     if(params.get(i).charAt(0) == '$') {
                    //         name_of_label = this.name + "__" + params.get(i).substring(1, params.get(i).length());
                    //     } else {
                    //         name_of_label = this.name + "__" + params.get(i);
                    //     }
                    //     if(this.intsSet.contains(name_of_label) || this.floatsSet.contains(name_of_label)){
                    //         this.text.add(String.format("\tlw %s, %s(%s)", "$a" + i, name_of_label, "$zero"));
                    //     } else {
                    //         this.text.add(String.format("\tlw %s, %s(%s)", "$a" + i, params.get(i), "$zero"));
                    //     }
                    // }
                    // this.text.add(String.format("\tadd %s, %s, %s", params.get(0), "$v0", "$zero"));
                }
                return;
            case "goto":
                this.text.add(String.format("\tj %s", params.get(0)));
                return;
            case "empty_return":
                this.text.add(String.format("\tj $ra"));
                return;
            case "return":
                this.text.add(String.format("\tadd %s, %s, %s", "$v0", params.get(0), "$zero"));
                this.text.add(String.format("\tj $ra"));
                return;
            default:
                System.out.println(String.format("Handle %s, %s", inst.get_inst().type(), inst.toString()));
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

}