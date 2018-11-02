import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;


public class SymbolTable {

    private HashMap<String, ScopeTree> scopes;
    private String current_scope;

    public SymbolTable() {
        this.scopes = new HashMap();
        this.scopes.put("main", new ScopeTree());
        this.current_scope = "main";
    }

    public boolean contains_scope(String name) {
        return this.scopes.containsKey(name);
    }

    public void add_new_scope(String name) {
        if (this.contains_scope(name)) {
            throw new RuntimeException("Duplicate scope added to the symbol table");
        } else {
            this.scopes.put(name, new ScopeTree());
        }
    }

    public void set_scope(String name) {
        if (!this.contains_scope(name)) {
            throw new RuntimeException("Scope not recognized");
        } else {
            this.current_scope = name;
        }
    }

    public String current_scope() {
        return this.current_scope;
    }

    public void enter_let_scope() {
        this.scopes.get(this.current_scope).enterLetScope();
    }

    public void exit_let_scope() {
        ScopeTree scope = this.scopes.get(this.current_scope);
        if (scope.current == scope.head) {
            throw new RuntimeException("not currently in a let scope");
        } else {
            scope.popToParent();
        }
    }

    private VariableSymbol get_var(String name) {
        return this.scopes.get(this.current_scope).get_var(name);
    }

    public boolean var_exists(String name) {
        return this.get_var(name) != null;
    }

    public String var_type(String name) {
        VariableSymbol var = this.get_var(name);
        if (var == null) {
            throw new RuntimeException("variable not found");
        } else {
            return var.type;
        }
    }

    public void add_var(String name, String type) {
        this.scopes.get(this.current_scope).add_var(name, type);
    }

    public FunctionSymbol get_function(String name) {
        return this.scopes.get(this.current_scope).get_function(name);
    }

    public String get_function_return_type(String name) {
        FunctionSymbol fs = this.get_function(name);
        if (fs == null) {
            throw new RuntimeException("Can't find given function");
        } else {
            return fs.return_type;
        }
    }

    public String get_scope_return_type() {
        FunctionSymbol fs = this.get_function(this.current_scope);
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
        this.scopes.get(this.current_scope).add_function(name, args, return_type);
    }

    public TypeSymbol get_type(String name) {
        return this.scopes.get(this.current_scope).get_type(name);
    }

    public boolean valid_type(String name) {
        return this.get_type(name) != null;
    }

    public void add_type(String name, String structure) {
        this.scopes.get(this.current_scope).add_type(name, structure);
    }

    // public void add_constant(String const_val, String type) {
    //     TypeSymbol type_s = this.find_type(type);
    //     if (type_s == null) {
    //         throw new RuntimeException("type not recognized");
    //     } else {
    //         this.constant_table.put(const_val, type_s);
    //     }
    // }
    //
    // public TypeSymbol get_type_of_constant(String const_val) {
    //     return this.constant_table.get(const_val);
    // }
}
