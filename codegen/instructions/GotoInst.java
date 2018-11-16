package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// goto: "break", "goto, after_loop, ,"
public class GotoInst implements Instruction {
    private String dest;

    public GotoInst(String[] args) {
        this.dest = args[1];
    }

    public String type() {
        return "goto";
    }

    public List<String> params() {
        return Arrays.asList(this.dest);
    }

    public Set<String> var_use() {
        return new HashSet<>();
    }

    public Set<String> var_def() {
        return new HashSet<>();
    }

    public String get_dest() {
        return this.dest;
    }

    public String debug() {
        return String.format("goto %s;", this.dest);
    }
}
