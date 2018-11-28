package codegen.instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// div: "a / b", "div, a, b, t1"
public class DivInst extends BinopInst implements Instruction {

    public DivInst(String[] args) {
        super(args);
    }

    public String type() {
        return "div";
    }

    public Instruction with_registers(HashMap<String, String> var_reg_map) {
        List<String> params = this.params();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (var_reg_map.containsKey(param)) {
                params.set(i, var_reg_map.get(param));
            }
        }
        return new DivInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("%s := %s / %s;", this.dest, this.left, this.right);
    }

    @Override
    public String toString() {
        return String.format("div, %s, %s, %s", this.left, this.right, this.dest);
    }
}
