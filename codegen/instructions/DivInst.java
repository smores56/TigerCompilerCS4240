package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// div: "a / b", "div, a, b, t1"
public class DivInst extends BinopInst implements Instruction {

    public DivInst(String[] args) {
        super(args);
    }

    public String type() {
        return "div";
    }

    public String debug() {
        return String.format("%s := %s / %s;", this.dest, this.left, this.right);
    }
}
