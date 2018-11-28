package codegen.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface Instruction {

    public String type();

    public List<String> params();

    public Set<String> var_use();

    public Set<String> var_def();

    public Instruction with_registers(HashMap<String, String> var_reg_map);

    default HashSet<String> vars_in_inst() {
        HashSet<String> vars_in_inst = new HashSet<>(this.var_use());
        vars_in_inst.addAll(this.var_def());
        return vars_in_inst;
    }

    default String debug() {
        return String.format("%s: %s", this.type(), String.join(", ", this.params()));
    }
}
