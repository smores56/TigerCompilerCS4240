import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.lang.Math;
import instructions.*;

public class ControlFlow {
    private ArrayList<Codeblock> blocks;
    private HashMap<Integer, Integer[]> flows;

    public ControlFlow(Instruction[] instructions) {
        HashMap<String, Integer> labels = this.label_indices(instructions);
        ArrayList<Integer> leader_indices = this.leader_indices(instructions, labels);
        leader_indices.add(instructions.length);

        this.blocks = new ArrayList<>();
        for (int i = 0; i < leader_indices.size() - 1; i++) {
            Instruction[] block_instructions = Arrays.copyOfRange(
                instructions, leader_indices.get(i), leader_indices.get(i + 1));
            this.blocks.add(new Codeblock(block_instructions));
        }

        this.flows = new HashMap<>();
        for (int j = 0; j < this.blocks.size(); j++) {
            Codeblock block = this.blocks.get(j);
            Instruction inst = block.get_line(block.size() - 1);

            if (inst instanceof BranchInst) {
                String label = ((BranchInst) inst).get_dest();
                int index = this.block_index_from_label(leader_indices, labels.get(label));
                this.flows.put(j, new Integer[]{index, j + 1});
            } else if (inst instanceof GotoInst) {
                String label = ((GotoInst) inst).get_dest();
                int index = this.block_index_from_label(leader_indices, labels.get(label));
                this.flows.put(j, new Integer[]{index});
            } else if (j < this.blocks.size() - 1) {
                this.flows.put(j, new Integer[]{j + 1});
            }
        }
    }

    private ArrayList<Integer> leader_indices(Instruction[] instructions, HashMap<String, Integer> labels) {
        ArrayList<Integer> indices = new ArrayList<>();
        indices.add(0);

        for (int i = 0; i < instructions.length - 1; i++) {
            Instruction inst = instructions[i];
            if (inst instanceof BranchInst) {
                String label = ((BranchInst) inst).get_dest();
                indices.add(labels.get(label));
                indices.add(i + 1);
            } else if (inst instanceof GotoInst) {
                String label = ((GotoInst) inst).get_dest();
                indices.add(labels.get(label));
                indices.add(i + 1);
            } else if (inst instanceof ReturnInst || inst instanceof EmptyReturnInst) {
                indices.add(i + 1);
            }
        }

        indices.sort((a, b) -> a - b);
        return new ArrayList<>(indices.stream().distinct().collect(Collectors.toList()));
    }

    private HashMap<String, Integer> label_indices(Instruction[] instructions) {
        HashMap<String, Integer> labels = new HashMap<>();
        for (int i = 0; i < instructions.length; i++) {
            Instruction inst = instructions[i];
            if (inst instanceof LabelInst) {
                labels.put(((LabelInst) inst).get_name(), i);
            }
        }

        return labels;
    }

    private int block_index_from_label(ArrayList<Integer> leader_indices, int label_index) {
        for (int i = 0; i < leader_indices.size(); i++) {
            if (label_index >= leader_indices.get(i)) {
                return i;
            }
        }

        throw new IndexOutOfBoundsException("Couldn't find block");
    }

    public void calculate_livenesses() {
        boolean updated = true;

        while (updated) {
            updated = false;
            for (Codeblock block : this.blocks) {
                updated = updated || block.propagate_liveness();
            }
            for (int block_index : this.flows.keySet()) {
                Integer[] flows_to = this.flows.get(block_index);
                for (int f : flows_to) {
                    Set<String> liveness = this.blocks.get(f).get_block_liveness();
                    updated = updated || this.blocks.get(block_index).update_block_liveness(liveness);
                }
            }
        }
    }

    public void print_debug() {
        for (int i = 0; i < this.blocks.size(); i++) {
            System.out.println(String.format("Block %d of %d:", i + 1, this.blocks.size()));
            Codeblock block = this.blocks.get(i);
            for (int j = 0; j < block.size(); j++) {
                System.out.println(String.format("-   live: %s", String.join(", ", block.get_liveness(j))));
                System.out.println(String.format("- %s", block.get_line(j).debug()));
            }
            System.out.println(String.format("-   live: %s", String.join(", ", block.get_liveness(block.size()))));
            String flows_to = String.join(", ", Arrays
                .stream(this.flows.getOrDefault(i, new Integer[]{}))
                .map(x -> new Integer(x + 1).toString())
                .collect(Collectors.toList()));
            System.out.println(String.format("-- flows to: %s", flows_to));
        }
    }

    public ArrayList<Codeblock> get_blocks() {
        return this.blocks;
    }

    public HashMap<String, Integer> calculate_spill_costs() {
        HashSet<String> all_vars = new HashSet<>();
        for (Codeblock block : this.blocks) {
            for (HashSet<String> liveness : block.get_livenesses()) {
                all_vars.addAll(liveness);
            }
        }
        HashMap<String, Integer> costs = new HashMap<>();
        for (String var : all_vars) {
            costs.put(var, 0);
        }

        HashSet<Integer> loops = new HashSet<>();
        for (int i = 0; i < this.blocks.size(); i++) {
            Codeblock block = this.blocks.get(i);
            for (int f : this.flows.get(i)) {
                if (f != i + 1) {
                    loops.add(f);
                }
            }

            for (Instruction inst : block.get_lines()) {
                for (String var : inst.vars_in_inst()) {
                    costs.put(var, costs.get(var) + Math.pow(loops.size(), 10));
                }
            }

            if (loop.containsKey(i)) {
                loops.remove(i);
            }
        }

        return costs;
    }
}
