package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// mult: "a * b", "mult, a, b, t1"
public class MultInst extends BinopInst implements Instruction {

    public MultInst(String[] args) {
        super(args);
    }

    public String type() {
        return "mult";
    }

    public String debug() {
        return String.format("%s := %s * %s;", this.dest, this.left, this.right);
    }
}
