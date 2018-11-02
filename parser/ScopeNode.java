import java.util.ArrayList;

public class ScopeNode {

    public ScopeNode parent;
    public ArrayList<ScopeNode> children;
    public ArrayList<VariableSymbol> variables;
    public ArrayList<FunctionSymbol> functions;
    public ArrayList<TypeSymbol> types;

    public ScopeNode(ScopeNode parent) {
        this.parent = parent;
        this.children = new ArrayList();

        this.variables = new ArrayList();
        this.types = new ArrayList<TypeSymbol>() {{
            add(new TypeSymbol("int", "int"));
            add(new TypeSymbol("float", "float"));
        }};
        this.functions = new ArrayList<FunctionSymbol>() {{
            add(new FunctionSymbol("printi", new ArrayList<VariableSymbol>() {{
                add(new VariableSymbol("i", "int"));
            }}, null));
            add(new FunctionSymbol("flush", null, null));
            add(new FunctionSymbol("not", new ArrayList<VariableSymbol>() {{
                add(new VariableSymbol("i", "int"));
            }}, "int"));
            add(new FunctionSymbol("exit", new ArrayList<VariableSymbol>() {{
                add(new VariableSymbol("i", "int"));
            }}, null));
        }};
    }


    public void addScope(ScopeNode child) {
        this.children.add(child);
    }

    public VariableSymbol get_var(String name) {
        return variables
            .stream()
            .filter(v -> v.name.equals(name))
            .findFirst()
            .orElse(null);
    }

    public TypeSymbol get_type(String name) {
        return types
            .stream()
            .filter(t -> t.name.equals(name))
            .findFirst()
            .orElse(null);
    }

    public FunctionSymbol get_function(String name) {
        return functions
            .stream()
            .filter(f -> f.name.equals(name))
            .findFirst()
            .orElse(null);
    }
}
