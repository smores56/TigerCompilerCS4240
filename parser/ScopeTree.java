import java.util.ArrayList;

public class ScopeTree {

    public ScopeNode head;
    public ScopeNode current;

    public ScopeTree(ArrayList<VariableSymbol> variables, ArrayList<FunctionSymbol> function, ArrayList<TypeSymbol> types) {
        this.head = new ScopeNode(null, variables, function, types);
        this.current = this.head;
    }

    public ScopeNode addChildToCurrent(ArrayList<VariableSymbol> variables, ArrayList<FunctionSymbol> function, ArrayList<TypeSymbol> types) {
        ScopeNode child = new ScopeNode(this.current, variables, function, types);
        this.current.addScope(child);
        this.current = child;
        return this.current;
    }

    public ScopeNode popToParent() {
        this.current = this.current.parent;
        return this.current;
    }

}