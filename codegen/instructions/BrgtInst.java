package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// brgt: "if (a <= b) then", "brgt, a, b, after_if_part"
public class BrgtInst extends BranchInst implements Instruction {

    public BrgtInst(String[] args) {
        super(args);
    }

    public String type() {
        return "brgt";
    }

    public Instruction with_registers(HashMap<String, String> var_reg_map) {
        List<String> params = this.params();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (var_reg_map.containsKey(param)) {
                params.set(i, var_reg_map.get(param));
            }
        }
        return new BrgtInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("if (%s <= %s) goto %s;", this.left, this.right, this.dest);
    }

    @Override
    public String toString() {
        return String.format("brgt, %s, %s, %s",  this.left, this.right, this.dest);
    }
}
