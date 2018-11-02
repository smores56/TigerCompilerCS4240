import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.HashMap;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


class SemanticAnalysisVisitor extends TigerBaseVisitor<String> {

    private SymbolTable symbol_table;

    public SemanticAnalysisVisitor() {
        this.symbol_table = new SymbolTable();
    }

    public SymbolTable get_symbol_table() {
        return this.symbol_table;
    }

	@Override
    public String visitTiger_program(TigerParser.Tiger_programContext ctx) {
        // tiger_program : MAIN LET declaration_segment IN BEGIN stat_seq END EOF ;

        return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitDeclaration_segment(TigerParser.Declaration_segmentContext ctx) {
        // declaration_segment : type_declaration_list var_declaration_list funct_declaration_list ;

        return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitType_declaration_list(TigerParser.Type_declaration_listContext ctx) {
        // type_declaration_list : type_declaration type_declaration_list
        //                       | /* epsilon */ ;

        return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitVar_declaration_list(TigerParser.Var_declaration_listContext ctx) {
        // var_declaration_list : var_declaration var_declaration_list
        //                      | /* epsilon */ ;

        return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitFunct_declaration_list(TigerParser.Funct_declaration_listContext ctx) {
        // funct_declaration_list : funct_declaration funct_declaration_list
        //                        | /* epsilon */ ;

        return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitType_declaration(TigerParser.Type_declarationContext ctx) {
        // type_declaration : TYPE ID EQUALS type SEMI ;

        String name = ctx.getChild(1).getText();
        String structure = visit(ctx.getChild(3));
        this.symbol_table.add_type(name, structure);

        return "";
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitType(TigerParser.TypeContext ctx) {
        // type : type_id
        //      | ARRAY LBRACKET INT RBRACKET OF type_id
        //      | RECORD field_list END
        //      | ID ;

        String first_node = ctx.getChild(0).getText();
        if (first_node.equals("int") || first_node.equals("float")) {
            return first_node;
        } else if (first_node.equals("array")) {
            String type = ctx.getChild(5).getText();
            String length = ctx.getChild(2).getText();
            return String.format("%s[%s]", type, length);
        } else if (first_node.equals("record")) {
            return String.format("record(%s)", visit(ctx.getChild(1)));
        } else {
            if (!symbol_table.valid_type(first_node)) {
                throw new RuntimeException("Type not valid: " + first_node);
            } else {
                return first_node;
            }
        }
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitType_id(TigerParser.Type_idContext ctx) {
        // type_id : INT_KW
        //         | FLOAT_KW ;

    	return ctx.getChild(0).getText();
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitField_list(TigerParser.Field_listContext ctx) {
        // field_list : ID COLON type_id SEMI field_list
        //            | /* epsilon */ ;

        if (ctx.getChildCount() == 0) {
            return "";
        } else {
            String name = ctx.getChild(0).getText();
            String type = ctx.getChild(2).getText();
            String other_fields = visit(ctx.getChild(4));
            if (other_fields.length() == 0) {
                return String.format("%s:%s", name, type);
            } else {
                return String.format("%s:%s,%s", name, type, other_fields);
            }
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitVar_declaration(TigerParser.Var_declarationContext ctx) {
        // var_declaration : VAR id_list COLON type optional_init SEMI ;

        String[] vars = visit(ctx.getChild(1)).split(",");
        String type = visit(ctx.getChild(3));
        String optional_init_type = visit(ctx.getChild(4));

        if (optional_init_type != null && !optional_init_type.equals(type)) {
            throw new RuntimeException("Init type does not equal variable type");
        } else {
            for (String var : vars) {
                this.symbol_table.add_var(var, type);
            }

            return "";
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitId_list(TigerParser.Id_listContext ctx) {
        // id_list : ID id_list_tail ;

        return String.format("%s%s", ctx.getChild(0).getText(), visitChildren(ctx));
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitId_list_tail(TigerParser.Id_list_tailContext ctx) {
        // id_list_tail :  COMMA ID id_list_tail
        //              | /* epsilon */ ;

        if (ctx.getChildCount() == 0) {
            return "";
        } else {
            return String.format(",%s%s", ctx.getChild(1).getText(), visit(ctx.getChild(2)));
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitOptional_init(TigerParser.Optional_initContext ctx) {
        // optional_init : ASSIGN constant
        //               | /* epsilon */ ;

        if (ctx.getChildCount() == 0) {
            return null;
        } else {
            String constant = ctx.getChild(1).getText();
            if (constant.contains(".")) {
                // this.symbol_table.add_constant(constant, "float");
                return "float";
            } else {
                // this.symbol_table.add_constant(constant, "int");
                return "int";
            }
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitFunct_declaration(TigerParser.Funct_declarationContext ctx) {
        // funct_declaration : FUNCTION ID LPARENS param_list RPARENS ret_type BEGIN stat_seq END SEMI ;

        String name = ctx.getChild(1).getText();
        ArrayList<Tuple<String, String>> args = new ArrayList();
        for (String pair : visit(ctx.getChild(3)).split(",")) {
            String[] split = pair.split(":");
            args.add(new Tuple<String, String>(split[0], split[1]));
            if (!this.symbol_table.valid_type(split[1])) {
                throw new RuntimeException("invalid type in function definition");
            }
        }
        String return_type = visit(ctx.getChild(5));
        if (return_type != null && !this.symbol_table.valid_type(return_type)) {
            throw new RuntimeException("invalid type in function definition");
        }
        this.symbol_table.add_function(name, args, return_type);

        String old_scope = this.symbol_table.current_scope();
        this.symbol_table.add_new_scope(name);
        this.symbol_table.set_scope(name);
		visit(ctx.getChild(7));
        this.symbol_table.set_scope(old_scope);

        return "";
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitParam_list(TigerParser.Param_listContext ctx) {
        // param_list : param param_list_tail
        //            | /* epsilon */ ;

        if (ctx.getChildCount() == 0) {
            return "";
        } else {
            String param = visit(ctx.getChild(0));
            String other_params = visit(ctx.getChild(1));
            return String.format("%s%s", param, other_params);
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitParam_list_tail(TigerParser.Param_list_tailContext ctx) {
        // param_list_tail : COMMA param param_list_tail
        //                 | /* epsilon */ ;

        if (ctx.getChildCount() == 0) {
            return "";
        } else {
            String param = visit(ctx.getChild(1));
            String other_params = visit(ctx.getChild(2));
            return String.format(",%s%s", param, other_params);
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitRet_type(TigerParser.Ret_typeContext ctx) {
        // ret_type : type
        //          | /* epsilon */ ;

        String type = visit(ctx.getChild(0));
        return type.length() > 0 ? type : null;
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitParam(TigerParser.ParamContext ctx) {
        // param : ID COLON type ;

        return String.format("%s:%s", ctx.getChild(0).getText(), visit(ctx.getChild(2)));
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitStat_seq(TigerParser.Stat_seqContext ctx) {
        // stat_seq : stat stat_seq_tail ;

		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitStat_seq_tail(TigerParser.Stat_seq_tailContext ctx) {
        // stat_seq_tail : stat stat_seq_tail
        //               | /* epsilon */ ;

    	return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitStat(TigerParser.StatContext ctx) {
        // stat : assign_or_func
        //      | stat_tail_a
        //      | WHILE expr DO stat_seq ENDDO SEMI
        //      | FOR ID ASSIGN expr TO expr DO stat_seq ENDDO SEMI
        //      | BREAK SEMI
        //      | RETURN expr SEMI
        //      | LET declaration_segment IN stat_seq END ;

        String first_node = ctx.getChild(0).getText();

        if (first_node.equals("while")) {
            String cond_type = visit(ctx.getChild(1));
            if (!cond_type.equals("int")) {
                throw new RuntimeException("conditiona expressions in while loops must be of type int");
            } else {
                visit(ctx.getChild(3));
            }
        } else if (first_node.equals("for")) {
            String var = ctx.getChild(1).getText();
            String var_type = this.symbol_table.var_type(var);
            String type = visit(ctx.getChild(3));
            String type2 = visit(ctx.getChild(5));
            if (!var_type.equals(type)) {
                throw new RuntimeException("var in for loop doesn't match the bounds types");
            } else if (!type.equals(type2)) {
                throw new RuntimeException("types in for loop bounds don't match");
            } else if (!type.equals("int") && !type.equals("float")) {
                throw new RuntimeException("for loop must iterate with floats or ints");
            } else {
                visit(ctx.getChild(7));
            }
        } else if (first_node.equals("break")) {
            // do nothing
        } else if (first_node.equals("return")) {
            String scope = this.symbol_table.current_scope();
            if (scope.equals("main")) {
                throw new RuntimeException("Can't return from main");
            } else {
                String return_type = this.symbol_table.get_scope_return_type();
                if (return_type == null) {
                    throw new RuntimeException("Can't return from this function");
                } else {
                    String expr_type = visit(ctx.getChild(1));
                    if (!return_type.equals(expr_type)) {
                        throw new RuntimeException("Return value type doesn't match function return type");
                    }
                }
            }
        } else if (first_node.equals("let")) {
            this.symbol_table.enter_let_scope();
            visit(ctx.getChild(1));
            visit(ctx.getChild(3));
            this.symbol_table.exit_let_scope();
        } else {
            visit(ctx.getChild(0));
        }

        return "";
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitStat_tail_a(TigerParser.Stat_tail_aContext ctx) {
        // stat_tail_a : IF expr THEN stat_seq stat_tail_b ;

        String expr_type = visit(ctx.getChild(1));
        if (!expr_type.equals("int")) {
            throw new RuntimeException("conditionals must be of type int");
        } else {
            visit(ctx.getChild(3));
            visit(ctx.getChild(4));

            return "";
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitStat_tail_b(TigerParser.Stat_tail_bContext ctx) {
        // stat_tail_b : ENDIF SEMI
        //             | ELSE stat_seq ENDIF SEMI ;

        if (ctx.getChild(0).getText().equals("else")) {
            visit(ctx.getChild(1));
        }

        return "";
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitAssign_or_func(TigerParser.Assign_or_funcContext ctx) {
        // assign_or_func : ID aof_tail_a ;

        return visitChildren(ctx);
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
    public String visitAof_tail_a(TigerParser.Aof_tail_aContext ctx) {
        // aof_tail_a : assign aof_tail_b expr SEMI
        //            | LPARENS expr_list RPARENS SEMI ;

        String var = ctx.getParent().getChild(0).getText();
        String first_node = ctx.getChild(0).getText();

        if (first_node.equals("(")) {
            String[] expr_types = visit(ctx.getChild(1)).split(";");
            ArrayList<String> expected_types = this.symbol_table.get_function_arg_types(var);
            if (expr_types.length != expected_types.size()) {
                throw new RuntimeException("incorrect number of parameters supplied during function call");
            }
            for (int i = 0; i < expr_types.length; i++) {
                if (!expected_types.get(i).equals(expr_types[i])) {
                    throw new RuntimeException("an argument was the wrong type for the function call");
                }
            }
        } else {
            String expr_type = visit(ctx.getChild(2));
            String[] assigns = String.format(
                "%s%s%s", var, visit(ctx.getChild(0)), visit(ctx.getChild(1))).split(";");
            for (String assign : assigns) {
                if (assign.contains("[")) {
                    String var_type = this.symbol_table.var_type(assign.split("[")[0]);
                    String index_type = assign.split("[")[1].split("]")[0];
                    if (!index_type.equals("int")) {
                        throw new RuntimeException("indexing must be done with ints");
                    }
                    String var_inner_type = var_type.split("[")[0];
                    if (!var_type.contains("[")) {
                        throw new RuntimeException("indexing attempted on a non-array variable");
                    } else if (!var_inner_type.equals(expr_type)) {
                        throw new RuntimeException("array inner type doesn't match expression type");
                    }
                } else if (assign.contains(".")) {
                    String var_type = this.symbol_table.var_type(assign.split(".")[0]);
                    String field = assign.split(".")[1];
                    if (!var_type.startsWith("record")) {
                        throw new RuntimeException("can't get field from non-record type");
                    }
                    String[] inner_types = var_type.substring(7, var_type.length() - 2).split(",");
                    String field_type_pair = Arrays.stream(inner_types)
                        .filter(t -> t.startsWith(field))
                        .findFirst()
                        .orElse(null);
                    if (field_type_pair == null) {
                        throw new RuntimeException("couldn't find field in record type");
                    }
                    String field_type = field_type_pair.split(":")[1];
                    if (!field_type.equals(expr_type)) {
                        throw new RuntimeException("expression type didn't match record's field type");
                    }
                } else {
                    String var_type = this.symbol_table.var_type(assign);
                    if (!expr_type.equals(var_type)) {
                        throw new RuntimeException("the variable type didn't match the expression type");
                    }
                }
            }
        }

        return visitChildren(ctx);
    }

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
    public String visitAof_tail_b(TigerParser.Aof_tail_bContext ctx) {
        // aof_tail_b : ID assign aof_tail_b
        //            | /* epsilon */ ;

        if (ctx.getChildCount() > 0) {
            return String.format(
                ";%s%s%s", ctx.getChild(0).getText(), visit(ctx.getChild(1)), visit(ctx.getChild(2)));
        } else {
            return "";
        }
    }

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
    public String visitAssign(TigerParser.AssignContext ctx) {
        // assign : LBRACKET expr RBRACKET ASSIGN
        //        | DOT ID ASSIGN
        //        | ASSIGN ;

        String first_node = ctx.getChild(0).getText();
        if (first_node.equals("[")) {
            return String.format("[%s]", visit(ctx.getChild(1)));
        } else if (first_node.equals(".")) {
            return String.format(".%s", ctx.getChild(1).getText());
        } else {
            return "";
        }
    }

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitConstant(TigerParser.ConstantContext ctx) {
        // constant : sign constant_tail ;

        return ctx.getText();
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitSign(TigerParser.SignContext ctx) {
        // sign : PLUS | MINUS | /* epsilon */ ;

		return ctx.getText();
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitConstant_tail(TigerParser.Constant_tailContext ctx) {
        // constant_tail : INT | FLOAT ;

        return ctx.getText();
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitExpr(TigerParser.ExprContext ctx) {
        // expr : logic_expr ((AND|OR) logic_expr)* ;

        int num_children = ctx.getChildCount();
        if (num_children == 1) {
            return visit(ctx.getChild(0));
        } else {
            for (int i = 0; i < num_children; i = i + 2) {
                String type = visit(ctx.getChild(i));
                if (!type.equals("int")) {
                    throw new RuntimeException("logical expressions must be of type int");
                }
            }
            return "int";
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitLogic_expr(TigerParser.Logic_exprContext ctx) {
        // logic_expr : cond_expr ((EQUALS|NEQ|LESS|GREATER|LTEQ|GTEQ) cond_expr)? ;

        int num_children = ctx.getChildCount();
        if (num_children == 1) {
            return visit(ctx.getChild(0));
        } else {
            String type1 = visit(ctx.getChild(0));
            String type2 = visit(ctx.getChild(2));
            if (!type1.equals(type2)) {
                throw new RuntimeException("expression types must be equal during comparison");
            } else if (!type1.equals("int") && !type1.equals("float")) {
                throw new RuntimeException("expression types must be ints or floats during comparison");
            }
            return type1;
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitCond_expr(TigerParser.Cond_exprContext ctx) {
        // cond_expr : term ((PLUS|MINUS) term)* ;

        int num_children = ctx.getChildCount();
        if (num_children == 1) {
            return visit(ctx.getChild(0));
        } else {
            String last_type = visit(ctx.getChild(0));
            if (!last_type.equals("int") && !last_type.equals("float")) {
                throw new RuntimeException("expression types must be ints or floats during adding/subtracting");
            }
            for (int i = 2; i < num_children; i += 2) {
                String current_type = visit(ctx.getChild(i));
                if (!current_type.equals("int") && !current_type.equals("float")) {
                    throw new RuntimeException("expression types must be ints or floats during adding/subtracting");
                }

                if (last_type.equals("float") || current_type.equals("float")) {
                    last_type.equals("float");
                }
            }

            return last_type;
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitTerm(TigerParser.TermContext ctx) {
        // term : factor ((MULT|DIV) factor)* ;

        int num_children = ctx.getChildCount();
        if (num_children == 1) {
            return visit(ctx.getChild(0));
        } else {
            String last_type = visit(ctx.getChild(0));
            if (!last_type.equals("int") && !last_type.equals("float")) {
                throw new RuntimeException("expression types must be ints or floats during mult/div");
            }
            for (int i = 2; i < num_children; i += 2) {
                String current_type = visit(ctx.getChild(i));
                if (!current_type.equals("int") && !current_type.equals("float")) {
                    throw new RuntimeException("expression types must be ints or floats during mult/div");
                }

                if (last_type.equals("float") || current_type.equals("float")) {
                    last_type.equals("float");
                }
            }

            return last_type;
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitFactor(TigerParser.FactorContext ctx) {
        // factor : atom (POWER atom)* ;

        int num_children = ctx.getChildCount();
        if (num_children == 1) {
            return visit(ctx.getChild(0));
        } else {
            String last_type = visit(ctx.getChild(num_children - 1));
            if (!last_type.equals("int") && !last_type.equals("float")) {
                throw new RuntimeException("expression types must be ints or floats during exponentiation");
            }
            for (int i = num_children - 3; i >= 0; i -= 2) {
                String current_type = visit(ctx.getChild(i));
                if (!current_type.equals("int") && !current_type.equals("float")) {
                    throw new RuntimeException("expression types must be ints or floats during exponentiation");
                }

                if (last_type.equals("float") || current_type.equals("float")) {
                    last_type.equals("float");
                }
            }

            return last_type;
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitAtom(TigerParser.AtomContext ctx) {
        // atom : constant
        //      | LPARENS expr RPARENS
        //      | ID atom_tail ;

        String left_token = ctx.getChild(0).getText();
        if (left_token.equals("(")) {
            return visit(ctx.getChild(1));
        } else if (ctx.getChildCount() == 1) {
            return ctx.getChild(0).getText().contains(".") ? "float" : "int";
        } else {
            return visit(ctx.getChild(1));
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitAtom_tail(TigerParser.Atom_tailContext ctx) {
        // atom_tail : LPARENS expr_list RPARENS
        //           | LBRACKET expr RBRACKET
        //           | DOT ID
        //           | /* epsilon */ ;

        String var = ctx.getParent().getChild(0).getText();

        if (ctx.getChildCount() > 0) {
            String first_node = ctx.getChild(0).getText();
            if (first_node.equals("[")) {
                String var_type = this.symbol_table.var_type(var);
                String index_type = visit(ctx.getChild(1));
                if (!index_type.equals("int")) {
                    throw new RuntimeException("indexing must be done with ints");
                }
                String var_inner_type = var_type.split("[")[0];
                if (!var_type.contains("[")) {
                    throw new RuntimeException("indexing attempted on a non-array variable");
                } else {
                    return var_inner_type;
                }
            } else if (first_node.equals(".")) {
                String var_type = this.symbol_table.var_type(var);
                String field = ctx.getChild(1).getText();
                if (!var_type.startsWith("record")) {
                    throw new RuntimeException("can't get field from non-record type");
                }
                String[] inner_types = var_type.substring(7, var_type.length() - 2).split(",");
                String field_type_pair = Arrays.stream(inner_types)
                    .filter(t -> t.startsWith(field))
                    .findFirst()
                    .orElse(null);
                if (field_type_pair == null) {
                    throw new RuntimeException("couldn't find field in record type");
                } else {
                    return field_type_pair.split(":")[1]; // field type
                }
            } else { // (first_node.equals("("))
                String[] expr_types = visit(ctx.getChild(1)).split(";");
                ArrayList<String> expected_types = this.symbol_table.get_function_arg_types(var);
                if (expr_types.length != expected_types.size()) {
                    throw new RuntimeException("incorrect number of parameters supplied during function call");
                }
                for (int i = 0; i < expr_types.length; i++) {
                    if (!expected_types.get(i).equals(expr_types[i])) {
                        throw new RuntimeException("an argument was the wrong type for the function call");
                    }
                }
                return this.symbol_table.get_function_return_type(var);
            }
        } else {
            return this.symbol_table.var_type(var);
        }
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitExpr_list(TigerParser.Expr_listContext ctx) {
        // expr_list : expr expr_list_tail
        //           | /* epsilon */ ;

        if (ctx.getChildCount() == 0) {
            return "";
        } else {
            return String.format("%s%s", visit(ctx.getChild(0)), visit(ctx.getChild(1)));
        }
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitExpr_list_tail(TigerParser.Expr_list_tailContext ctx) {
        // expr_list_tail : COMMA expr expr_list_tail
        //                | /* epsilon */ ;

        if (ctx.getChildCount() == 0) {
            return "";
        } else {
            return String.format(";%s%s", visit(ctx.getChild(1)), visit(ctx.getChild(2)));
        }
	}

}
