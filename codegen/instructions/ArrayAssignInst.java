package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// assign: "var X : ArrayInt := 10 (size 100)", "assign, X, 100, 10"
public class ArrayAssignInst implements Instruction {
    private String arr_name;
    private int arr_size;
    private String arr_val;

    public ArrayAssignInst(String[] args) {
        this.arr_name = args[1];
        this.arr_size = Integer.parseInt(args[2]);
        this.arr_val = args[3];
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
        return new HashSet<String>(Arrays.asList(this.arr_name));
    }
}
