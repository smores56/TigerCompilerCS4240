import instructions.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class LiveRangeGraph {
    private HashMap<String, HashSet<String>> edges;
    private ArrayList<HashSet<String>> livenesses;

    public LiveRangeGraph(ArrayList<Codeblock> blocks) {
        this.livenesses = new ArrayList<>();
        for (Codeblock block : blocks) {
            for (HashSet<String> liveness : block.get_livenesses()) {
                this.livenesses.add(liveness);
            }
        }
        this.update_edges();
    }

    public void update_edges() {
        this.edges = new HashMap<>();
        HashSet<String> all_vars = new HashSet<>();
        for (HashSet<String> liveness : this.livenesses) {
            all_vars.addAll(liveness);
        }

        for (String var : all_vars) {
            this.edges.put(var, new HashSet<>());
        }
        for (HashSet<String> liveness : this.livenesses) {
            for (String var : all_vars) {
                if (liveness.contains(var)) {
                    HashSet<String> without = new HashSet<>(liveness);
                    without.remove(var);
                    this.edges.get(var).addAll(without);
                }
            }
        }
    }

    private HashMap<String, Integer> get_node_degrees() {
        HashMap<String, Integer> degrees = new HashMap<>();
        for (String var : this.edges.keySet()) {
            degrees.put(var, this.edges.get(var).size());
        }

        return degrees;
    }

    private void remove_var_from_graph(String var) {
        this.edges.remove(var);
        for (HashSet<String> edge : this.edges.values()) {
            edge.remove(var);
        }
    }

    public HashMap<String, String> color_graph(List<String> registers, HashMap<String, Integer> spill_costs) {
        HashMap<String, HashSet<String>> old_edges = new HashMap<>();
        for (String var : this.edges.keySet()) {
            old_edges.put(var, new HashSet<>(this.edges.get(var)));
        }

        HashMap<String, Integer> degrees = this.get_node_degrees();
        Stack<String> removed_nodes = new Stack<>();
        boolean changed = true;
        while (changed) {
            changed = false;
            for (String var : degrees.keySet()) {
                if (degrees.get(var) < registers.size()) {
                    removed_nodes.push(var);
                    this.remove_var_from_graph(var);
                    degrees.remove(var);
                    changed = true;
                    break;
                }
            }
        }

        while (this.edges.size() > 0) {
            String max_cost_var = null;
            for (String var : this.edges.keySet()) {
                if (max_cost_var == null || spill_costs.get(var) > spill_costs.get(max_cost_var)) {
                    max_cost_var = var;
                }
                degrees.remove(var);
                removed_nodes.push(var);
            }
        }

        this.edges = old_edges;
        HashMap<String, String> coloring = new HashMap<>();
        while (!removed_nodes.empty()) {
            String var = removed_nodes.pop();
            HashSet<String> edge = this.edges.getOrDefault(var, new HashSet<>());
            HashSet<String> neighboring_colors = new HashSet<>();
            for (String dest : edge) {
                if (coloring.containsKey(dest)) {
                    neighboring_colors.add(coloring.get(dest));
                }
            }
            HashSet<String> allowed_colors = new HashSet<>(registers);
            allowed_colors.removeAll(neighboring_colors);
            if (allowed_colors.size() > 0) {
                coloring.put(var, allowed_colors.iterator().next());
            }
        }

        return coloring;
    }
}
