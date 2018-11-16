package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// return: "return a", "return, a, ,"
public class ReturnInst implements Instruction {
    private String ret_val;

    public ReturnInst(String[] args) {
        this.ret_val = args[1];
    }

    public String type() {
        return "return";
    }

    public List<String> params() {
        return Arrays.asList(this.ret_val);
    }

    public Set<String> var_use() {
        try {
            double d = Double.parseDouble(this.ret_val);
        } catch(NumberFormatException e) {
            return new HashSet<>(Arrays.asList(this.ret_val));
        }
        return new HashSet<>();
    }

    public Set<String> var_def() {
        return new HashSet<String>();
    }
}
