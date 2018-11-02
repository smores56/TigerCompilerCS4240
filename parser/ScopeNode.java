import java.util.ArrayList;

public class ScopeNode {

    public ScopeNode parent;
    public ArrayList<ScopeNode> children;
    public VariableSymbol variables;
    public FunctionSymbol functions;
    public TypeSymbol types;

    public ScopeNode(ScopeNode parent, ArrayList<ScopeNode> children, VariableSymbol variables, FunctionSymbol function, TypeSymbol types) {
        this.parent = parent;
        this.children = children;
        this.variables = variables;
        this.functions = functions;
        this.types = types;
    }


    public ScopeNode(ScopeNode parent, VariableSymbol variables, FunctionSymbol function, TypeSymbol types) {
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