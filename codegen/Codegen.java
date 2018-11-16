import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

import instructions.*;


public class Codegen {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Correct usage: Codegen <IR filename>");
        } else {
            Instruction[] instructions = (Instruction[]) Codegen
                .read_file(args[0])
                .stream()
                .map(l -> Codegen.parse_instruction(l))
                .collect(Collectors.toList())
                .toArray();

            ControlFlow cf = new ControlFlow(instructions);
            cf.calculate_livenesses();
            cf.print_debug();
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
                new String[]{inst.substring(0, inst.length() - 2)});
        } else {
            String[] args = (String[]) Arrays
                .stream(inst.split(",\\w*"))
                .filter(i -> i.length() > 0)
                .collect(Collectors.toList())
                .toArray();

            switch (args[0]) {
                case "add":
                    return (Instruction) new AddInst(args);
                case "and":
                    return (Instruction) new AndInst(args);
                case "assign":
                    if (args.length == 3) {
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
                    if (args.length == 1) {
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
                    throw new RuntimeException("Invalid instruction provided");
            }
        }
    }
}
