package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// brgt: "if (a <= b) then", "brgt, a, b, after_if_part"
public class BrgtInst extends BinopInst implements Instruction {

    public BrgtInst(String[] args) {
        super(args);
    }

    public String type() {
        return "brgt";
    }
}
