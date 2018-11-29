import instructions.Instruction;
import java.util.HashMap;

public class InstRegallocPair {
    private Instruction inst;
    private HashMap<String, String> var_reg_map;

    public InstRegallocPair(Instruction inst, HashMap<String, String> var_reg_map) {
        this.inst = inst;
        this.var_reg_map = var_reg_map;
    }

    public Instruction get_inst() {
        return this.inst;
    }

    public HashMap<String, String> get_regalloc() {
        return this.var_reg_map;
    }
}
