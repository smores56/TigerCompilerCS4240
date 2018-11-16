package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// array_store: "arr[0] := a", "array_store, arr, 0, a"
public class ArrayStoreInst implements Instruction {
    private String arr_name;
    private int index;
    private String rvalue;

    public ArrayStoreInst(String[] args) {
        this.arr_name = args[1];
        this.index = Integer.parseInt(args[2]);
        this.rvalue = args[3];
    }

    public String type() {
        return "array_store";
    }

    public List<String> params() {
        return Arrays.asList(this.arr_name, Integer.toString(this.index), this.rvalue);
    }

    public Set<String> var_use() {
        try {
            double d = Double.parseDouble(this.rvalue);
        } catch(NumberFormatException e) {
            return new HashSet<>(Arrays.asList(this.rvalue));
        }
        return new HashSet<>();
    }

    public Set<String> var_def() {
        return new HashSet<String>(Arrays.asList(this.arr_name));
    }
}
