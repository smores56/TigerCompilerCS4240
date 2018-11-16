package instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// add: "a + b", "add, a, b, t1"
public class AddInst extends BinopInst implements Instruction {

    public AddInst(String[] args) {
        super(args);
    }

    public String type() {
        return "add";
    }
}
