package instructions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// label: "label1:", "label1:"
public class LabelInst implements Instruction {
    private String name;

    public LabelInst(String[] args) {
        this.name = args[0];
    }

    public String type() {
        return "label";
    }

    public List<String> params() {
        return new ArrayList<>();
    }

    public Set<String> var_use() {
        return new HashSet<>();
    }

    public Set<String> var_def() {
        return new HashSet<>();
    }

    public String get_name() {
        return this.name;
    }
}
