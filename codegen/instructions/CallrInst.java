package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// callr: "a := foo(x, y, z)", "callr, a, foo, x, y, z"
public class CallrInst implements Instruction {
    private String dest;
    private String func;
    private String[] args;

    public CallrInst(String[] args) {
        this.dest = args[0];
        this.func = args[1];
        this.args = Arrays.copyOfRange(args, 2, args.length);
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
            if (!arg.matches("-?\\d+(\\.\\d+)?")) {
                uses.add(arg);
            }
        }
        return uses;
    }

    public Set<String> var_def() {
        return new HashSet<String>(Arrays.asList(this.dest));
    }

    public Instruction with_registers(HashMap<String, String> var_reg_map) {
        List<String> params = this.params();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (var_reg_map.containsKey(param)) {
                params.set(i, var_reg_map.get(param));
            }
        }
        return new CallrInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("%s := %s(%s);", this.dest, this.func, String.join(", ", this.args));
    }

    @Override
    public String toString() {
        return String.format("callr, %s, %s, %s", this.dest, this.func, String.join(", ", this.args));
    }
}
