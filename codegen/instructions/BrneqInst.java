package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// brneq: "if (a = b) then", "brneq, a, b, after_if_part"
public class BrneqInst extends BranchInst implements Instruction {

    public BrneqInst(String[] args) {
        super(args);
    }

    public String type() {
        return "brneq";
    }

    public Instruction with_registers(HashMap<String, String> var_reg_map) {
        List<String> params = this.params();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (var_reg_map.containsKey(param)) {
                params.set(i, var_reg_map.get(param));
            }
        }
        return new BrneqInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("if (%s = %s) goto %s;", this.left, this.right, this.dest);
    }

    @Override
    public String toString() {
        return String.format("brneq, %s, %s, %s",  this.left, this.right, this.dest);
    }
}
