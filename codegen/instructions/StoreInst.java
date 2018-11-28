package instructions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// store: "x := r1", "store, x, r1"
public class StoreInst implements Instruction {
    private String dest;
    private String source;

    public StoreInst(String[] args) {
        this.dest = args[0];
        this.source = args[1];
    }

    public String type() {
        return "store";
    }

    public List<String> params() {
        return Arrays.asList(this.dest, this.source);
    }

    public Set<String> var_use() {
        return new HashSet<String>();
    }

    public Set<String> var_def() {
        return new HashSet<String>();
    }

    public Instruction with_registers(HashMap<String, String> var_reg_map) {
        List<String> params = this.params();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (var_reg_map.containsKey(param)) {
                params.set(i, var_reg_map.get(param));
            }
        }
        return new StoreInst(params.toArray(new String[params.size()]));
    }

    public LoadInst reverse() {
        return new LoadInst(new String[]{this.dest, this.source});
    }

    public String debug() {
        return String.format("store %s into %s;", this.source, this.dest);
    }

    @Override
    public String toString() {
        return String.format("store, %s, %s", this.dest, this.source);
    }
}
