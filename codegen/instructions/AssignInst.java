package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// assign: "a := b", "assign, a, b"
public class AssignInst implements Instruction {
    private String lvalue;
    private String rvalue;

    public AssignInst(String[] args) {
        this.lvalue = args[1];
        this.rvalue = args[2];
    }

    public String type() {
        return "assign";
    }

    public List<String> params() {
        return Arrays.asList(this.lvalue, this.rvalue);
    }

    public Set<String> var_use() {
        try {
            double d = Double.parseDouble(this.rvalue);
        } catch(NumberFormatException e) {
            return new HashSet<>(Arrays.asList(this.rvalue));
        }
        return new HashSet<>();
    }

    public Set<String> var_def() {
        return new HashSet<>(Arrays.asList(this.lvalue));
    }
}
