import java.util.ArrayList;


public class FunctionSymbol {
    public String name;
    public ArrayList<VariableSymbol> args;
    public String return_type;

    public FunctionSymbol(String name, ArrayList<VariableSymbol> args, String return_type) {
        this.name = name;
        this.args = args;
        this.return_type = return_type;
    }
}
