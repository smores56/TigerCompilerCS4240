import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

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

    private List<FunctionSymbol> function_table = new ArrayList<>();
    private List<TypeSymbol> type_table = new ArrayList<TypeSymbol>() {{
        add(new TypeSymbol("int", "int"));
        add(new TypeSymbol("float", "float"));
    }};
    private HashMap<String, ArrayList<VariableSymbol>> scoped_var_tables = new HashMap<>();
    private HashMap<String, TypeSymbol> constant_table = new HashMap<>();

    public boolean contains_scope(String scope_name) {
        return this.scoped_var_tables.containsKey(scope_name);
    }

    public void add_scope(String scope_name) {
        if (this.scoped_var_tables.containsKey(scope_name)) {
            throw new RuntimeException("Duplicate scope added to the symbol table");
        } else {
            this.scoped_var_tables.put(scope_name, new ArrayList());
        }
    }

    public VariableSymbol get_var_from_scope(ArrayList<VariableSymbol> scope, String var_name) {
        return scope
            .stream()
            .filter(v -> v.name.equals(var_name))
            .findFirst()
            .orElse(null);
    }

    public boolean scope_contains_var(String scope_name, String var_name) {
        ArrayList<VariableSymbol> scope = this.scoped_var_tables.get(scope_name);
        if (scope == null) {
            throw new RuntimeException("scope not recognized");
        } else {
            return get_var_from_scope(scope, var_name) != null;
        }
    }

    public String var_type_in_scope(String var_name, String scope_name) {
        ArrayList<VariableSymbol> scope = this.scoped_var_tables.get(scope_name);
        if (scope == null) {
            throw new RuntimeException("scope not recognized");
        } else {
            VariableSymbol var = this.get_var_from_scope(scope, var_name);
            if (var == null) {
                throw new RuntimeException("variable doesn't exist in scope");
            } else {
                return var.type;
            }
        }
    }

    public void add_var_to_scope(String var_name, String var_type, String scope_name) {
        ArrayList<VariableSymbol> scope = this.scoped_var_tables.get(scope_name);
        if (scope == null) {
            throw new RuntimeException("scope not recognized");
        } else if (this.get_var_from_scope(scope, var_name) != null) {
            throw new RuntimeException(
                String.format("Variable already exists: %s of type '%s'", var_name, var_type));
        } else {
            scope.add(new VariableSymbol(var_name, var_type));
        }
    }

    public FunctionSymbol get_function(String name) {
        return this.function_table
            .stream()
            .filter(f -> f.name.equals(name))
            .findFirst()
            .orElse(null);
    }

    public String get_function_return_type(String name) {
        FunctionSymbol fs = this.get_function(name);
        if (fs == null) {
            throw new RuntimeException("Can't find given function");
        } else {
            return fs.return_type;
        }
    }

    public ArrayList<String> get_function_arg_types(String name) {
        FunctionSymbol fs = this.get_function(name);
        if (fs == null) {
            throw new RuntimeException("Can't find given function");
        } else {
            return fs.args
                .stream()
                .map(t -> t.name)
                .collect(Collectors.toCollection(() -> new ArrayList<String>()));
        }
    }

    public boolean function_exists(String name) {
        return this.get_function(name) != null;
    }

    public void add_function(String name, ArrayList<Tuple<String, String>> args, String return_type) {
        if (this.function_exists(name)) {
            throw new RuntimeException("Duplicate function added to symbol table");
        } else {
            ArrayList<VariableSymbol> args2 = args
                .stream()
                .map(e -> new VariableSymbol(e.left, e.right))
                .collect(Collectors.toCollection(() -> new ArrayList()));
            for (VariableSymbol arg : args2) {
                if (!this.valid_type(arg.type)) {
                    throw new RuntimeException("Unknown type used in function");
                }
            }
            this.function_table.add(new FunctionSymbol(name, args2, return_type));
        }
    }

    public TypeSymbol find_type(String type_name) {
        return this.type_table
            .stream()
            .filter(f -> f.name.equals(type_name))
            .findFirst()
            .orElse(null);
    }

    public boolean valid_type(String type_name) {
        return this.find_type(type_name) != null;
    }

    public void add_type(String type_name, String structure) {
        if (this.valid_type(type_name)) {
            throw new RuntimeException(
                String.format("Duplicate type added: %s with structure '%s'", type_name, structure));
        } else {
            this.type_table.add(new TypeSymbol(type_name, structure));
        }
    }

    public void add_constant(String const_val, String type) {
        TypeSymbol type_s = this.find_type(type);
        if (type_s == null) {
            throw new RuntimeException("type not recognized");
        } else {
            this.constant_table.put(const_val, type_s);
        }
    }

    public TypeSymbol get_type_of_constant(String const_val) {
        return this.constant_table.get(const_val);
    }
}
