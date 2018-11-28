package codegen.instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// return: "return a", "return, a, ,"
public class ReturnInst implements Instruction {
    private String ret_val;

    public ReturnInst(String[] args) {
        this.ret_val = args[0];
    }

    public String type() {
        return "return";
    }

    public List<String> params() {
        return Arrays.asList(this.ret_val);
    }

    public Set<String> var_use() {
        if (!this.ret_val.matches("-?\\d+(\\.\\d+)?")) {
            return new HashSet<>(Arrays.asList(this.ret_val));
        } else {
            return new HashSet<>();
        }
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
        return new ReturnInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("return %s;", this.ret_val);
    }

    @Override
    public String toString() {
        return String.format("return, %s, ,", this.ret_val);
    }
}
