package codegen.instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// call: "foo(x)", "call, foo, x"
public class CallInst implements Instruction {
    private String func;
    private String[] args;

    public CallInst(String[] args) {
        this.func = args[0];
        this.args = Arrays.copyOfRange(args, 1, args.length);
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
            if (!arg.matches("-?\\d+(\\.\\d+)?")) {
                uses.add(arg);
            }
        }
        return uses;
    }

    public Set<String> var_def() {
        return new HashSet<String>();
    }

    public Instruction with_registers(HashMap<String, String> var_reg_map) {
        List<String> params = this.params();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (var_reg_map.containsKey(param)) {
                params.set(i, var_reg_map.get(param));
            }
        }
        return new CallInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("%s(%s);", this.func, String.join(", ", this.args));
    }

    @Override
    public String toString() {
        return String.format("call, %s, %s",  this.func, String.join(", ", this.args));
    }
}
