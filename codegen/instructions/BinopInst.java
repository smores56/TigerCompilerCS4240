package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract public class BinopInst {
    private String left;
    private String right;
    private String dest;

    public BinopInst(String[] args) {
        this.left = args[1];
        this.right = args[2];
        this.dest = args[3];
    }

    public List<String> params() {
        return Arrays.asList(this.left, this.right, this.dest);
    }

    public Set<String> var_use() {
        HashSet<String> uses = new HashSet<>();
        for (String var : new String[]{this.left, this.right}) {
            try {
                double d = Double.parseDouble(var);
            } catch(NumberFormatException e) {
                uses.add(var);
            }
        }
        return uses;
    }

    public Set<String> var_def() {
        return new HashSet<>(Arrays.asList(this.dest));
    }
}
