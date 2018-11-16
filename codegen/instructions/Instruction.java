package instructions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Instruction {

    public String type();

    public List<String> params();

    public Set<String> var_use();

    public Set<String> var_def();

    default String debug() {
        return String.format("%s: %s", this.type(), String.join(", ", this.params()));
    }
}
