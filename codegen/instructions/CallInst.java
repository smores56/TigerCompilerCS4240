package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// call: "foo(x)", "call, foo, x"
public class CallInst implements Instruction {
    private String func;
    private String[] args;

    public CallInst(String[] args) {
        this.func = args[1];
        this.args = Arrays.copyOfRange(args, 2, args.length - 1);
    }

    public String type() {
        return "call";
    }

    public List<String> params() {
        ArrayList<String> params = new ArrayList<>();
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
        return new HashSet<String>();
    }

    public String debug() {
        return String.format("%s(%s);", this.func, String.join(", ", this.args));
    }
}
