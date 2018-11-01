import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


class SemanticAnalysisVisitor extends TigerBaseVisitor<String> {

    private SymbolTable symbol_table;
    private Stack<String> scope_stack;

    public SemanticAnalysisVisitor() {
        this.symbol_table = new SymbolTable();
        this.symbol_table.add_scope("main");
        this.scope_stack = new Stack();
        this.scope_stack.push("main");
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
            assert symbol_table.valid_type(first_node);
            return first_node;
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

        for (String var : vars) {
            this.symbol_table.add_var_to_scope(var, type, this.scope_stack.peek());
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
            return String.format(",%s%s", ctx.getChild(0).getText(), visitChildren(ctx));
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
            return "";
        } else {
            String constant = ctx.getChild(1).getText();
            if (constant.contains(".")) {
                this.symbol_table.add_constant(constant, "float");
                return "float";
            } else {
                this.symbol_table.add_constant(constant, "int");
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
        }
        String return_type = visit(ctx.getChild(5));
        this.symbol_table.add_function(name, args, return_type);

        this.scope_stack.push(name);
		String result = visit(ctx.getChild(7));
        this.scope_stack.pop();

        return result;
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
            String param = ctx.getChild(0).getText();
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
            String param = ctx.getChild(1).getText();
            String other_params = visit(ctx.getChild(2));
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
	public String visitRet_type(TigerParser.Ret_typeContext ctx) {
        // ret_type : type
        //          | /* epsilon */ ;

        String type = ctx.getText();
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

        return ctx.getText();
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
            visit(ctx.getChild(1));
            visit(ctx.getChild(3));
        } else if (first_node.equals("for")) {
            String var = ctx.getChild(1).getText();
            String type = visit(ctx.getChild(3));
            String type2 = visit(ctx.getChild(5));
            // TODO: assert type.equals(type2) ???
            // TODO: assert (type.equals("int") || type.equals("float")) ???
            visit(ctx.getChild(7));
        } else if (first_node.equals("break")) {
            // do nothing?
        } else if (first_node.equals("return")) {
            String scope = this.scope_stack.peek();
            if (scope.equals("main")) {
                throw new RuntimeException("Can't return from main");
            } else {
                String return_type = this.symbol_table.get_function_return_type(scope);
                if (return_type == null) {
                    throw new RuntimeException("Can't return from this function");
                } else {
                    String expr_type = visit(ctx.getChild(1));
                    // assert return_type.equals(expr_type);
                }
            }
        } else if (first_node.equals("let")) {
            visit(ctx.getChild(1));
            visit(ctx.getChild(3));
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
        // assert expr_type.equals("int");
        visit(ctx.getChild(3));
        visit(ctx.getChild(4));

        return "";
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
        // assign_or_func : ID aof_tail ;

        return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitAof_tail(TigerParser.Aof_tailContext ctx) {
        // aof_tail : LBRACKET expr RBRACKET ASSIGN expr SEMI
        //          | DOT ID ASSIGN expr SEMI
        //          | ASSIGN expr SEMI
        //          | LPARENS expr_list RPARENS SEMI ;

        String var = ctx.getParent().getChild(0).getText();
        String var_type = this.symbol_table.var_type_in_scope(var, this.scope_stack.peek());
        String first_node = ctx.getChild(0).getText();

        if (first_node.equals("[")) {
            String expr_type = visit(ctx.getChild(4));
            String index_type = visit(ctx.getChild(1));
            // assert index_type.equals("int");
            // assert var_type.concat("[");
            String var_inner_type = var_type.split("[")[0];
            // assert var_inner_type.equals(expr_type);
        } else if (first_node.equals(".")) {
            String expr_type = visit(ctx.getChild(4));
            String field = ctx.getChild(1).getText();
            // assert var_type.startsWith("record");
            String[] inner_types = var_type.substring(7, var_type.length() - 2).split(",");
            String field_type_pair = Arrays.stream(inner_types)
                .filter(t -> t.startsWith(field))
                .findFirst()
                .orElse(null);
            // assert field_type_pair != null;
            String field_type = field_type_pair.split(":")[1];
            // assert field_type.equals(expr_type);
        } else if (first_node.equals("(")) {
            String[] expr_types = visit(ctx.getChild(1)).split(";");
            ArrayList<String> expected_types = this.symbol_table.get_function_arg_types(var);
            // assert expr_types.length() == expected_types.length();
            for (int i = 0; i < expr_types.length; i++) {
                // assert expected_types[i].equals(expr_types[i]);
            }
        } else {
            String expr_type = visit(ctx.getChild(1));
            // assert expr_type.equals(var_type);
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
	public String visitConstant(TigerParser.ConstantContext ctx) {
        // constant : sign constant_tail ;

        String constant = ctx.getText();
        if (constant.contains(".")) {
            this.symbol_table.add_constant(constant, "float");
            return "float";
        } else {
            this.symbol_table.add_constant(constant, "int");
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
                // assert type.equals("int");
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
            String last_type = visit(ctx.getChild(0));
            for (int i = 2; i < num_children; i = i + 2) {
                String type = visit(ctx.getChild(i));

                // assert type.equals("int");
            }
            return "bool";
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

    	return visitChildren(ctx);
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

        return visitChildren(ctx);
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

    	return visitChildren(ctx);
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

    	return visitChildren(ctx);
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

    	return visitChildren(ctx);
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

		return visitChildren(ctx);
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

    	return visitChildren(ctx);
	}

}
