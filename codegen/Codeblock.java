import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.Arrays;
import instructions.*;

public class Codeblock {
    private Instruction[] instructions;
    private HashSet<String>[] livenesses;

    public Codeblock(Instruction[] instructions) {
        this.instructions = instructions;
        this.livenesses = new HashSet[instructions.length + 1];
        for (int i = 0; i < this.livenesses.length; i++) {
            this.livenesses[i] = new HashSet<>();
        }
    }

    public Set<String> get_block_liveness() {
        return this.livenesses[0];
    }

    public void update_block_liveness(Set<String> liveness) {
        this.livenesses[this.livenesses.length - 1].addAll(liveness);
    }

    public void propagate_liveness() {
        for (int i = this.livenesses.length - 1; i > 0; i--) {
            Instruction inst = this.instructions[i - 1];
            HashSet<String> liveness = this.livenesses[i];
            liveness.removeAll(inst.var_def());
            liveness.addAll(inst.var_use());
        }
    }

    public Instruction get_line(int i) {
        if (i < 0 || i >= this.instructions.length) {
            throw new IndexOutOfBoundsException("Line out of bounds");
        } else {
            return this.instructions[i];
        }
    }

    public HashSet<String> get_liveness(int i) {
        if (i < 0 || i >= this.livenesses.length) {
            throw new IndexOutOfBoundsException("Liveness out of bounds");
        } else {
            return this.livenesses[i];
        }
    }

    public int size() {
        return this.instructions.length;
    }

    public HashSet<String>[] get_livenesses() {
        return this.livenesses;
    }

    public Instruction[] get_lines() {
        return this.instructions;
    }
}
