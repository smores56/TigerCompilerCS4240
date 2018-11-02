import java.util.ArrayList;

public class ScopeNode {

    public ScopeNode parent;
    public ArrayList<ScopeNode> children;
    public ArrayList<VariableSymbol> variables;
    public ArrayList<FunctionSymbol> functions;
    public ArrayList<TypeSymbol> types;

    public ScopeNode(ScopeNode parent, ArrayList<ScopeNode> children, ArrayList<VariableSymbol> variables, ArrayList<FunctionSymbol> function, ArrayList<TypeSymbol> types) {
        this.parent = parent;
        this.children = children;
        this.variables = variables;
        this.functions = functions;
        this.types = types;
    }


    public ScopeNode(ScopeNode parent, ArrayList<VariableSymbol> variables, ArrayList<FunctionSymbol> function, ArrayList<TypeSymbol> types) {
        this.parent = parent;
        this.children = new ArrayList<ScopeNode>();
        this.variables = variables;
        this.functions = functions;
        this.types = types;
    }


    public void addScope(ScopeNode child) {
        this.children.add(child);
    }

}