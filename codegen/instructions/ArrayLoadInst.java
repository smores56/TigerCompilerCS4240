package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// array_load: "a := arr[0]", "array_load, a, arr, 0"
public class ArrayLoadInst implements Instruction {
    private String dest;
    private String arr_name;
    private String index;

    public ArrayLoadInst(String[] args) {
        this.dest = args[1];
        this.arr_name = args[2];
        this.index = args[3];
    }

    public String type() {
        return "array_load";
    }

    public List<String> params() {
        return Arrays.asList(this.dest, this.arr_name, this.index);
    }

    public Set<String> var_use() {
        if (this.index.matches("-?\\d+(\\.\\d+)?")) {
            return new HashSet<>(Arrays.asList(this.arr_name, this.index));
        } else {
            return new HashSet<>(Arrays.asList(this.arr_name));
        }
    }

    public Set<String> var_def() {
        return new HashSet<>(Arrays.asList(this.dest));
    }

    public String debug() {
        return String.format("%s := %s[%s];", this.dest, this.arr_name, this.index);
    }
}
