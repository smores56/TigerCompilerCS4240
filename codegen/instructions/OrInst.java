package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// or: "a | b", "or, a, b, t1"
public class OrInst extends BinopInst implements Instruction {

    public OrInst(String[] args) {
        super(args);
    }

    public String type() {
        return "or";
    }
}
