package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// breq: "if (a <> b) then", "breq, a, b, after_if_part"
public class BreqInst extends BinopInst implements Instruction {

    public BreqInst(String[] args) {
        super(args);
    }

    public String type() {
        return "breq";
    }

    public String debug() {
        return String.format("if (%s <> %s) goto %s;", this.left, this.right, this.dest);
    }
}
