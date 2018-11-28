package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

abstract public class BinopInst {
    protected String left;
    protected String right;
    protected String dest;

    public BinopInst(String[] args) {
        this.left = args[0];
        this.right = args[1];
        this.dest = args[2];
    }

    public List<String> params() {
        return Arrays.asList(this.left, this.right, this.dest);
    }

    public Set<String> var_use() {
        HashSet<String> uses = new HashSet<>();
        for (String var : new String[]{this.left, this.right}) {
            if (!var.matches("-?\\d+(\\.\\d+)?")) {
                uses.add(var);
            }
        }
        return uses;
    }

    public Set<String> var_def() {
        return new HashSet<>(Arrays.asList(this.dest));
    }
}
