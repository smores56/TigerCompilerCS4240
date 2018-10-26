import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import javafx.util.Pair;

public class SymbolTable {

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

    public class VariableSymbol {
        public String name;
        public String type;

        public VariableSymbol(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }

    public class TypeSymbol {
        public String name;
        public String structure;

        public TypeSymbol(String name, String structure) {
            this.name = name;
            this.structure = structure;
        }
    }

    private static ArrayList<FunctionSymbol> function_table = new HashMap();
    private static ArrayList<TypeSymbol> type_table = new ArrayList(
        {new TypeSymbol("int", "int"), new TypeSymbol("float", "float")});
    private static HashMap<String, ArrayList<VariableSymbol>> scoped_var_tables = new HashMap();
    private static HashMap<String, TypeSymbol> constant_table = new HashMap();

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

    public static VariableSymbol get_var_from_scope(ArrayList<VariableSymbol> scope, String var_name) {
        return Arrays
            .stream(scope)
            .filter(v -> v.name.equals(var_name))
            .findFirst()
            .orElse(null);
    }

    public static boolean scope_contains_var(String scope_name, String var_name) {
        ArrayList<VariableSymbol> scope = SymbolTable.scoped_var_tables.get(scope_name);
        if (scope == null) {
            throw new RuntimeException("scope not recognized");
        } else {
            return get_var_from_scope(scope, var_name) == null;
        }
    }

    public static String var_type_in_scope(String var_name, String scope_name) {
        ArrayList<VariableSymbol> scope = SymbolTable.scoped_var_tables.get(scope_name);
        if (scope == null) {
            throw new RuntimeException("scope not recognized");
        } else {
            VariableSymbol var = SymbolTable.get_var_from_scope(scope, var_name);
            if (var == null) {
                throw new RuntimeException("variable doesn't exist in scope");
            } else {
                return var.type;
            }
        }
    }

    public static void add_var_to_scope(String var_name, String var_type, String scope_name) {
        ArrayList<VariableSymbol> scope = SymbolTable.scoped_var_tables.get(scope_name);
        if (scope == null) {
            throw new RuntimeException("scope not recognized");
        } else if (SymbolTable.scope_has_var(scope, var_name)) {
            throw new RuntimeException("variable already exists");
        } else {
            scope.put(new VariableSymbol(var_name, var_type));
        }
    }

    public static FunctionSymbol get_function(String name) {
        return Arrays
            .stream(SymbolTable.function_table)
            .filter(f -> f.name.equals(name))
            .findFirst()
            .orElse(null);
    }

    public static boolean function_exists(String name) {
        return SymbolTable.get_function(name) == null;
    }

    public static void add_function(String name, ArrayList<Pair<String, String>> args, String return_type) {
        if SymbolTable.function_exists(name) {
            throw new RuntimeException("Duplicate function added to symbol table");
        } else {
            ArrayList<VariableSymbol> args = Arrays
                .stream(args)
                .map(e -> new VariableSymbol(e.getLeft(), e.getRight()))
                .collect(Collectors.toCollection(() -> ArrayList()));
            for (VariableSymbol arg : args) {
                if (!SymbolTable.valid_type(arg.type)) {
                    throw new RuntimeException("Unknown type used in function");
                }
            }
            SymbolTable.function_table.append(new FunctionSymbol(name, args, return_type));
        }
    }

    public static TypeSymbol find_type(String type_name) {
        return Arrays
            .stream(SymbolTable.type_table)
            .filter(f -> f.name.equals(name))
            .findFirst()
            .orElse(null);
    }

    public static boolean valid_type(String type_name) {
        return SymbolTable.find_type(type_name) == null;
    }

    public static void add_type(String type_name, String structure) {
        if (SymbolTable.valid_type(type_name)) {
            throw new RuntimeException("Duplicate type added");
        } else {
            SymbolTable.type_table.append(new TypeSymbol(type_name, structure));
        }
    }

    public static void add_constant(String const_val, String type) {
        TypeSymbol type_s = SymbolTable.find_type(type);
        if (type_s == null) {
            throw new RuntimeException("type not recognized");
        } else {
            SymbolTable.constant_table.put(const_val, type_s);
        }
    }

    public static TypeSymbol get_type_of_constant(String const_val) {
        return SymbolTable.constant_table.get(const_val);
    }
}
