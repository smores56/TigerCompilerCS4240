package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// callr: "a := foo(x, y, z)", "callr, a, foo, x, y, z"
public class CallrInst implements Instruction {
    private String dest;
    private String func;
    private String[] args;

    public CallrInst(String[] args) {
        this.dest = args[1];
        this.func = args[2];
        this.args = Arrays.copyOfRange(args, 3, args.length - 1);
    }

    public String type() {
        return "callr";
    }

    public List<String> params() {
        ArrayList<String> params = new ArrayList<>();
        params.add(this.dest);
        params.add(this.func);
        for (String arg : this.args) {
            params.add(arg);
        }
        return params;
    }

    public Set<String> var_use() {
        HashSet<String> uses = new HashSet<>();
        for (String arg : args) {
            try {
                double d = Double.parseDouble(arg);
            } catch(NumberFormatException e) {
                uses.add(arg);
            }
        }
        return uses;
    }

    public Set<String> var_def() {
        return new HashSet<String>(Arrays.asList(this.dest));
    }
}
