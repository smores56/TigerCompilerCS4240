package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// sub: "a - b", "sub, a, b, t1"
public class SubInst extends BinopInst implements Instruction {

    public SubInst(String[] args) {
        super(args);
    }

    public String type() {
        return "sub";
    }
}
