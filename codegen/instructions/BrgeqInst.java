package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// brgeq: "if (a < b) then", "brgeq, a, b, after_if_part"
public class BrgeqInst extends BinopInst implements Instruction {

    public BrgeqInst(String[] args) {
        super(args);
    }

    public String type() {
        return "brgeq";
    }
}
