package codegen.instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// assign: "a := b", "assign, a, b"
public class AssignInst implements Instruction {
    private String lvalue;
    private String rvalue;

    public AssignInst(String[] args) {
        this.lvalue = args[0];
        this.rvalue = args[1];
    }

    public String type() {
        return "assign";
    }

    public List<String> params() {
        return Arrays.asList(this.lvalue, this.rvalue);
    }

    public Set<String> var_use() {
        if (!this.rvalue.matches("-?\\d+(\\.\\d+)?")) {
            return new HashSet<>(Arrays.asList(this.rvalue));
        } else {
            return new HashSet<>();
        }
    }

    public Set<String> var_def() {
        return new HashSet<>(Arrays.asList(this.lvalue));
    }

    public Instruction with_registers(HashMap<String, String> var_reg_map) {
        List<String> params = this.params();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (var_reg_map.containsKey(param)) {
                params.set(i, var_reg_map.get(param));
            }
        }
        return new AssignInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("%s := %s;", this.lvalue, this.rvalue);
    }

    @Override
    public String toString() {
        return String.format("assign, %s, %s", this.lvalue, this.rvalue);
    }
}
