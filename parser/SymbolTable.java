import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {

    private class FunctionSymbol {
        public String name;
        public ArrayList<VariableSymbol> args;
        public String return_type;

        public FunctionSymbol(String name, ArrayList<VariableSymbol> args, String return_type) {
            this.name = name;
            this.args = args;
            this.return_type = return_type;
        }
    }

    private class VariableSymbol {
        public String name;
        public String type;

        public VariableSymbol(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }

    private 

    private static ArrayList<FunctionSymbol> function_table = new HashMap();
    private static ArrayList<TypeSymbol> type_table = new ArrayList(["int", "float"]);
    private static HashMap<String, ArrayList<VariableSymbol>> scoped_var_tables = new HashMap();

    public static boolean contains_scope(String scope_name) {
        return SymbolTable.scoped_var_tables.containsKey(scope_name);
    }

    public static void add_scope(String scope_name) {
        if (SymbolTable.scoped_var_tables.containsKey(scope_name)) {
            throw new RuntimeException("Duplicate scope added to the symbol table");
        } else {
            SymbolTable.scoped_var_tables.put(scope_name, new ArrayList());
        }
    }

    public static void scope_contains_var(String scope_name, String var_name) {
        ArrayList<VariableSymbol> scope = SymbolTable.scoped_var_tables.get(scope_name);
        if (scope == null) {
            throw new RuntimeException("scope not recognized");
        } else {
            return Arrays
                .stream(scope)
                .filter(v -> v.name.equals(var_name))
                .findFirst()
                .orElse(null) == null;
        }
    }

    public static void add_var_to_scope(String var_name, String var_type, String scope_name) {
        ArrayList<VariableSymbol> scope = SymbolTable.scoped_var_tables.get(scope_name);
        if (scope == null) {
            throw new RuntimeException("scope not recognized");
        } else if (Arrays
                .stream(scope)
                .filter(v -> v.name.equals(var_name))
                .findFirst()
                .orElse(null) == null) {
            throw new RuntimeException("variable already exists");
        } else {
            scope.put(new VariableSymbol(var_name, var_type));
        }
    }

    public static void add_function()
}
