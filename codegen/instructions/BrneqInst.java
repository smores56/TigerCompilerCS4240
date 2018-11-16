package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// brneq: "if (a = b) then", "brneq, a, b, after_if_part"
public class BrneqInst extends BinopInst implements Instruction {

    public BrneqInst(String[] args) {
        super(args);
    }

    public String type() {
        return "brneq";
    }

    public String debug() {
        return String.format("if (%s = %s) goto %s;", this.left, this.right, this.dest);
    }
}
