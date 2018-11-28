package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// assign: "var X : ArrayInt := 10 (size 100)", "assign, X, 100, 10"
public class ArrayAssignInst implements Instruction {
    private String arr_name;
    private int arr_size;
    private String arr_val;

    public ArrayAssignInst(String[] args) {
        this.arr_name = args[0];
        this.arr_size = Integer.parseInt(args[1]);
        this.arr_val = args[2];
    }

    public String type() {
        return "array_assign";
    }

    public List<String> params() {
        return Arrays.asList(this.arr_name, Integer.toString(this.arr_size), this.arr_val);
    }

    public Set<String> var_use() {
        return new HashSet<>();
    }

    public Set<String> var_def() {
        // return new HashSet<String>(Arrays.asList(this.arr_name));
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
        return new ArrayAssignInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("%s[] (size %s) := %s;", this.arr_name, this.arr_size, this.arr_val);
    }

    @Override
    public String toString() {
        return String.format("assign, %s, %s, %s", this.arr_name, this.arr_size, this.arr_val);
    }
}
