package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// brleq: "if (a > b) then", "brleq, a, b, after_if_part"
public class BrleqInst extends BinopInst implements Instruction {

    public BrleqInst(String[] args) {
        super(args);
    }

    public String type() {
        return "brleq";
    }
}
