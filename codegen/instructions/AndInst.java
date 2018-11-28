package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// and: "a & b", "and, a, b, t1"
public class AndInst extends BinopInst implements Instruction {

    public AndInst(String[] args) {
        super(args);
    }

    public String type() {
        return "and";
    }

    public Instruction with_registers(HashMap<String, String> var_reg_map) {
        List<String> params = this.params();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (var_reg_map.containsKey(param)) {
                params.set(i, var_reg_map.get(param));
            }
        }
        return new AndInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("%s := %s & %s;", this.dest, this.left, this.right);
    }

    @Override
    public String toString() {
        return String.format("and, %s, %s, %s", this.left, this.right, this.dest);
    }
}
