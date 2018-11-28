package codegen.instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
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
        return Arrays.asList(this.name);
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

    public Instruction with_registers(HashMap<String, String> var_reg_map) {
        Set<String> use_def = this.var_use();
        use_def.addAll(this.var_def());
        List<String> params = this.params();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (var_reg_map.containsKey(param)) {
                params.set(i, var_reg_map.get(param));
            }
        }
        return new LabelInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("%s:", this.name);
    }

    @Override
    public String toString() {
        return String.format("%s:", this.name);
    }
}
