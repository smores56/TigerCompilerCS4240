package backend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.Arrays;
import codegen.instructions.*;

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

    public boolean update_block_liveness(Set<String> liveness) {
        HashSet<String> block_liveness = this.livenesses[this.livenesses.length - 1];
        boolean different = false;
        for (String l : liveness) {
            different = different || !block_liveness.contains(l);
        }

        if (different) {
            block_liveness.addAll(liveness);
        }

        return different;
    }

    public boolean propagate_liveness() {
        boolean different = false;

        for (int i = this.livenesses.length - 1; i > 0; i--) {
            Instruction inst = this.instructions[i - 1];
            HashSet<String> out = this.livenesses[i];
            HashSet<String> in = new HashSet<>(out);
            in.removeAll(inst.var_def());
            in.addAll(inst.var_use());

            different = different || !in.equals(this.livenesses[i - 1]);

            this.livenesses[i - 1] = in;
        }

        return different;
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

    public HashMap<String, Integer> calc_liveranges() {
        HashMap<String, Integer> liveranges = new HashMap<>();
        for (HashSet<String> liveness : this.livenesses) {
            for (String var : liveness) {
                liveranges.put(var, liveranges.getOrDefault(var, 0) + 1);
            }
        }
        return liveranges;
    }
}
