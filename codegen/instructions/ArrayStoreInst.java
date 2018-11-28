package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// array_store: "arr[0] := a", "array_store, arr, 0, a"
public class ArrayStoreInst implements Instruction {
    private String arr_name;
    private String index;
    private String rvalue;

    public ArrayStoreInst(String[] args) {
        this.arr_name = args[0];
        this.index = args[1];
        this.rvalue = args[2];
    }

    public String type() {
        return "array_store";
    }

    public List<String> params() {
        return Arrays.asList(this.arr_name, this.index, this.rvalue);
    }

    public Set<String> var_use() {
        HashSet<String> uses = new HashSet<>();
        for (String var : new String[]{this.index, this.rvalue}) {
            if (!var.matches("-?\\d+(\\.\\d+)?")) {
                uses.add(var);
            }
        }

        return uses;
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
        return new ArrayStoreInst(params.toArray(new String[params.size()]));
    }

    public String debug() {
        return String.format("%s[%s] := %s;", this.arr_name, this.index, this.rvalue);
    }

    @Override
    public String toString() {
        return String.format("array_store, %s, %s, %s", this.arr_name, this.index, this.rvalue);
    }
}
