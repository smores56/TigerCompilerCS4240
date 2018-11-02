import java.util.Stack;

public class VariableSymbol {
    public String name;
    public Stack<String> types;

    public VariableSymbol(String name, String type) {
        this.name = name;
        this.types = new Stack();
        this.types.push(type);
    }
}

