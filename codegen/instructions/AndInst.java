package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// and: "a & b", "and, a, b, t1"
public class AndInst extends BinopInst implements Instruction {

    public AndInst(String[] args) {
        super(args);
    }

    public String type() {
        return "and";
    }
}
