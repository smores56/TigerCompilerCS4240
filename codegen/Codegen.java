import instructions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Codegen {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Correct usage: Codegen <IR filename>");
        } else {
            ArrayList<String> lines = Codegen.read_file(args[0]);
            if (lines.size() == 0) {
                throw new RuntimeException("empty file");
            }

            ArrayList<FunctionIR> functions = new ArrayList<>();
            String line = lines.remove(0);
            while (line != null) {
                if (line.startsWith("#start_function")) {
                    String name = line.split(" ")[1];
                    String func_def = lines.remove(0);
                    String return_type = func_def.split(" ")[0];
                    TreeMap<String, Integer> args = new TreeMap<>();
                    for (String arg_type_pair : func_def.split("(")[1].split(")")[0].split(", *")) {
                        args.put(arg_type_pair.split(" ")[0], args.put(arg_type_pair.split(" ")[1]);
                    }
                    String ints_line = lines.remove(0);
                    String[] ints = ints_line.substring(9, ints_line.length()).trim().split(", *");
                    String floats_line = lines.remove(0);
                    String[] floats = floats_line.substring(11, floats_line.length()).trim().split(", *");

                    ArrayList<Instruction> instructions = new ArrayList<>();
                    line = lines.remove(0);
                    while (!line.startsWith("#end_function")) {
                        Instruction inst = Codegen.parse_instruction(line);
                        instructions.add(inst);
                        line = lines.remove(0);
                    }

                    functions.add(new FunctionIR(name, return_type, args, ints, floats,
                        instructions.toArray(new Instruction[instructions.size()])));
                } else {
                    line = lines.size() == 0 ? null : lines.remove(0);
                }
            }

            ArrayList<String> naive = new ArrayList<>();
            ArrayList<String> block = new ArrayList<>();
            ArrayList<String> color = new ArrayList<>();


            String name = Paths.get(args[0]).getFileName().toString().split("\\.")[0];
            for (FunctionIR f : functions) {
                System.out.println(String.format("\nRunning analysis for function \"%s\":", f.name()));
                System.out.println("------------------------------");
                f.run(name, functions);
                ArrayList<List<String>> temp = f.getText();
                naive.addAll(naive.size(), temp.get(0));
                block.addAll(naive.size(), temp.get(1));
                color.addAll(naive.size(), temp.get(2));
                System.out.println("------------------------------\n");
            }

        }
    }

    private static ArrayList<String> read_file(String file_path) {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(file_path));
            ArrayList<String> listOfLines = new ArrayList<>();

            String line = bufReader.readLine();
            while (line != null) {
                listOfLines.add(line);
                line = bufReader.readLine();
            }
            bufReader.close();

            return listOfLines;
        } catch (Exception e) {
            throw new RuntimeException("Couldn't open the specified IR file");
        }
    }

    public static Instruction parse_instruction(String instruction) {
        String inst = instruction.trim();
        if (inst.charAt(inst.length() - 1) == ':') {
            return new LabelInst(
                new String[]{inst.substring(0, inst.length() - 1)});
        } else {
            String[] first_split = inst.split(", *", 2);
            String type = first_split[0];
            String[] args = first_split[1].split(", *");

            switch (type) {
                case "add":
                    return (Instruction) new AddInst(args);
                case "and":
                    return (Instruction) new AndInst(args);
                case "assign":
                    if (args.length == 2) {
                        return (Instruction) new AssignInst(args);
                    } else {
                        return (Instruction) new ArrayAssignInst(args);
                    }
                case "array_load":
                    return (Instruction) new ArrayLoadInst(args);
                case "array_store":
                    return (Instruction) new ArrayStoreInst(args);
                case "breq":
                    return (Instruction) new BreqInst(args);
                case "brneq":
                    return (Instruction) new BrneqInst(args);
                case "brgt":
                    return (Instruction) new BrgtInst(args);
                case "brlt":
                    return (Instruction) new BrltInst(args);
                case "brgeq":
                    return (Instruction) new BrgeqInst(args);
                case "brleq":
                    return (Instruction) new BrleqInst(args);
                case "call":
                    return (Instruction) new CallInst(args);
                case "callr":
                    return (Instruction) new CallrInst(args);
                case "div":
                    return (Instruction) new DivInst(args);
                case "return":
                    if (args.length == 0) {
                        return (Instruction) new EmptyReturnInst(args);
                    } else {
                        return (Instruction) new ReturnInst(args);
                    }
                case "goto":
                    return (Instruction) new GotoInst(args);
                case "mult":
                    return (Instruction) new MultInst(args);
                case "or":
                    return (Instruction) new OrInst(args);
                case "sub":
                    return (Instruction) new SubInst(args);
                default:
                    throw new RuntimeException("Invalid instruction provided: " + instruction);
            }
        }
    }
}
