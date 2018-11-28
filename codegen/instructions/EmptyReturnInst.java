package codegen.instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// return: "return", "return, , ,"
public class EmptyReturnInst implements Instruction {

    public EmptyReturnInst(String[] args) {}

    public String type() {
        return "empty_return";
    }

    public List<String> params() {
        return new ArrayList<>();
    }

    public Set<String> var_use() {
        return new HashSet<>();
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
        return new EmptyReturnInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return "return;";
    }

    @Override
    public String toString() {
        return "return, , ,";
    }
}
