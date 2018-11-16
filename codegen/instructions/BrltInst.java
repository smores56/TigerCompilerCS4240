package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// brlt: "if (a >= b) then", "brlt, a, b, after_if_part"
public class BrltInst extends BinopInst implements Instruction {

    public BrltInst(String[] args) {
        super(args);
    }

    public String type() {
        return "brlt";
    }
}
