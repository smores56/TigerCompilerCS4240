package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// array_load: "a := arr[0]", "array_load, a, arr, 0"
public class ArrayLoadInst implements Instruction {
    private String dest;
    private String arr_name;
    private String index;

    public ArrayLoadInst(String[] args) {
        this.dest = args[0];
        this.arr_name = args[1];
        this.index = args[2];
    }

    public String type() {
        return "array_load";
    }

    public List<String> params() {
        return Arrays.asList(this.dest, this.arr_name, this.index);
    }

    public Set<String> var_use() {
        if (!this.index.matches("-?\\d+(\\.\\d+)?")) {
            // return new HashSet<>(Arrays.asList(this.arr_name, this.index));
            return new HashSet<>(Arrays.asList(this.index));
        } else {
            // return new HashSet<>(Arrays.asList(this.arr_name));
            return new HashSet<>();
        }
    }

    public Set<String> var_def() {
        return new HashSet<>(Arrays.asList(this.dest));
    }

    public Instruction with_registers(HashMap<String, String> var_reg_map) {
        List<String> params = this.params();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            if (var_reg_map.containsKey(param)) {
                params.set(i, var_reg_map.get(param));
            }
        }
        return new ArrayLoadInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("%s := %s[%s];", this.dest, this.arr_name, this.index);
    }

    @Override
    public String toString() {
        return String.format("array_load, %s, %s, %s", this.dest, this.arr_name, this.index);
    }
}
