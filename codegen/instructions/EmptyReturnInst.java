package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// return: "return", "return, , ,"
public class EmptyReturnInst implements Instruction {

    public EmptyReturnInst(String[] args) {}

    public String type() {
        return "empty_return";
    }

    public List<String> params() {
        return new ArrayList<>();
    }

    public Set<String> var_use() {
        return new HashSet<>();
    }

    public Set<String> var_def() {
        return new HashSet<String>();
    }
}
