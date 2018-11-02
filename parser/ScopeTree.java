import java.util.ArrayList;
import java.util.stream.Collectors;

public class ScopeTree {

    public ScopeNode head;
    public ScopeNode current;

    public ScopeTree() {
        this.head = new ScopeNode(null);
        this.current = this.head;
    }

    public ScopeNode addChildToCurrent() {
        ScopeNode child = new ScopeNode(this.current);
        this.current.addScope(child);
        this.current = child;
        return this.current;
    }

    public ScopeNode popToParent() {
        this.current = this.current.parent;
        return this.current;
    }

    public ScopeNode enterLetScope() {
        ScopeNode child = new ScopeNode(this.current);
        this.current.addScope(child);
        this.current = child;
        return this.current;
    }

    public VariableSymbol get_var(String name) {
        ScopeNode node = this.current;
        while (node != null) {
            VariableSymbol var = node.get_var(name);
            if (var != null) {
                return var;
            } else {
                node = node.parent;
            }
        }

        return null;
    }

    public void add_var(String name, String type) {
        if (this.current.get_var(name) != null) {
            throw new RuntimeException("Duplicate variable added");
        } else {
            this.current.variables.add(new VariableSymbol(name, type));
        }
    }

    public TypeSymbol get_type(String name) {
        ScopeNode node = this.current;
        while (node != null) {
            TypeSymbol type = node.get_type(name);
            if (type != null) {
                return type;
            } else {
                node = node.parent;
            }
        }

        return null;
    }

    public void add_type(String name, String structure) {
        if (this.current.get_type(name) != null) {
            throw new RuntimeException("Duplicate type added");
        } else {
            this.current.types.add(new TypeSymbol(name, structure));
        }
    }

    public FunctionSymbol get_function(String name) {
        ScopeNode node = this.current;
        while (node != null) {
            FunctionSymbol func = node.get_function(name);
            if (func != null) {
                return func;
            } else {
                node = node.parent;
            }
        }

        return null;
    }

    public void add_function(String name, ArrayList<Tuple<String, String>> args, String return_type) {
        if (this.current.get_function(name) != null) {
            throw new RuntimeException("Duplicate function added");
        } else {
            ArrayList<VariableSymbol> args2 = args
                .stream()
                .map(e -> new VariableSymbol(e.left, e.right))
                .collect(Collectors.toCollection(() -> new ArrayList()));
            for (VariableSymbol arg : args2) {
                if (this.get_type(arg.type) == null) {
                    throw new RuntimeException("Unknown type used in function");
                }
            }
            this.current.functions.add(new FunctionSymbol(name, args2, return_type));
        }
    }
}
