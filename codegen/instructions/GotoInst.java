package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// goto: "break", "goto, after_loop, ,"
public class GotoInst implements Instruction {
    private String dest;

    public GotoInst(String[] args) {
        this.dest = args[0];
    }

    public String type() {
        return "goto";
    }

    public List<String> params() {
        return Arrays.asList(this.dest);
    }

    public Set<String> var_use() {
        return new HashSet<>();
    }

    public Set<String> var_def() {
        return new HashSet<>();
    }

    public String get_dest() {
        return this.dest;
    }

    public Instruction with_registers(HashMap<String, String> var_reg_map) {
        List<String> params = this.params();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (var_reg_map.containsKey(param)) {
                params.set(i, var_reg_map.get(param));
            }
        }
        return new GotoInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("goto %s;", this.dest);
    }

    @Override
    public String toString() {
        return String.format("goto, %s, ,", this.dest);
    }
}
