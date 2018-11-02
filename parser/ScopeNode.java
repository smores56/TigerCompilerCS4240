import java.util.ArrayList;

public class ScopeNode {

    private ScopeNode parent;
    private ArrayList<ScopeNode> children;
    private VariableSymbol variables;
    private FunctionSymbol functions;
    private TypeSymbol types;

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


    public addScope(ScopeNode child) {
        this.children.add(child);
    }

}