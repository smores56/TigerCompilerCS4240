import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Tiger {

    public static void main(String[] args) throws Exception {
        ANTLRFileStream str = new ANTLRFileStream(args[0]);
        TigerLexer lex = new TigerLexer(str);
        CommonTokenStream tok = new CommonTokenStream(lex);

        TigerParser parser = new TigerParser(tok);
        ParseTree tree = parser.tiger_program();

        MyVisitor visitor = new MyVisitor();
        visitor.visit(tree);
    }
}


class MyVisitor extends TigerBaseVisitor<String> {
    private SymbolTable symbol_table;
    private Stack<String> scope_stack;
    private Stack<Scope> scopes;

    public void emit(String s){
      System.out.println(s);
    }

    public MyVisitor() {
        this.symbol_table = new SymbolTable();
        this.symbol_table.add_scope("main");
        this.scope_stack = new Stack();
        this.scope_stack.push("main");
        this.scopes = new Stack();
        this.scopes.push(new Scope("main"));
    }

	@Override
    public String visitTiger_program(TigerParser.Tiger_programContext ctx) {
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
		String s = visitChildren(ctx);
    //emit(s);
    emit(this.scopes.peek().string());
    return s;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitType_declaration_list(TigerParser.Type_declaration_listContext ctx) {
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
    String name = ctx.getChild(1).getText();
    String count = "0";
    String type = ctx.getChild(3).getText();
    if(ctx.getChild(3).getText().contains("[")){
      count = ((TigerParser.TypeContext)ctx.getChild(3)).INT().getText();
      type = ((TigerParser.TypeContext)ctx.getChild(3)).type_id().getText();
    }
    this.scopes.peek().addType(name, type, count);
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitType(TigerParser.TypeContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitType_id(TigerParser.Type_idContext ctx) {
		return visitChildren(ctx);
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitField_list(TigerParser.Field_listContext ctx) {
		return visitChildren(ctx);
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitVar_declaration(TigerParser.Var_declarationContext ctx) {
    for(String s: ctx.getChild(1).getText().split(",")){
      this.scopes.peek().addVariable(s,ctx.getChild(3).getText());
      try{
        this.scopes.peek().addAssignment(s,ctx.getChild(4).getText().split("=")[1]);
      }
      catch(Exception e){}
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
	public String visitId_list(TigerParser.Id_listContext ctx) {
		return visitChildren(ctx);
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitId_list_tail(TigerParser.Id_list_tailContext ctx) {
		return visitChildren(ctx);
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitOptional_init(TigerParser.Optional_initContext ctx) {
		return visitChildren(ctx);
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitFunct_declaration(TigerParser.Funct_declarationContext ctx) {
		return visitChildren(ctx);
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitParam_list(TigerParser.Param_listContext ctx) {
		return visitChildren(ctx);
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitParam_list_tail(TigerParser.Param_list_tailContext ctx) {
		return visitChildren(ctx);
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitRet_type(TigerParser.Ret_typeContext ctx) {
		return visitChildren(ctx);
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitParam(TigerParser.ParamContext ctx) {
		return visitChildren(ctx);
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitStat_seq(TigerParser.Stat_seqContext ctx) {
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
        //

        String first_node = ctx.getChild(0).getText();

        if (first_node.equals("while")) {
            visitChildren(ctx.getChild(1));
            visitChildren(ctx.getChild(3));
        } else if (first_node.equals("for")) {
            String var = ctx.getChild(1).getText();
            String type = visitChildren(ctx.getChild(3));
            String type2 = visitChildren(ctx.getChild(5));
            // TODO: assert type.equals(type2) ???
            // TODO: assert (type.equals("int") || type.equals("float")) ???
            this.symbol_table.add_var_to_scope(var, type, this.scope_stack.peek());
            visitChildren(ctx.getChild(7));
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
                    String expr_type = visitChildren(ctx.getChild(1));
                    // assert return_type.equals(expr_type);
                }
            }
        } else if (first_node.equals("let")) {
            visitChildren(ctx.getChild(1));
            visitChildren(ctx.getChild(3));
        } else {
            visitChildren(ctx.getChild(0));
        }
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

        String expr_type = visitChildren(ctx.getChild(1));
        // assert expr_type.equals("bool");
        visitChildren(ctx.getChild(3));
        visitChildren(ctx.getChild(4)));
	}

    /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public String visitStat_tail_b(TigerParser.Stat_tail_bContext ctx) {
        // stat_tail_b : ENDIF SEMI | ELSE stat_seq ENDIF SEMI ;

        if (ctx.getChild(0).getText().equals("else")) {
            visitChildren(ctx.getChild(1));
        }
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

        visitChildren(ctx);
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
            String expr_type = visitChildren(ctx.getChild(4));
            String index_type = visitChildren(ctx.getChild(1));
            // assert index_type.equals("int");
            // assert var_type.concat("[");
            String var_inner_type = var_type.split("[")[0];
            // assert var_inner_type.equals(expr_type);
        } else if (first_node.equals(".")) {
            String expr_type = visitChildren(ctx.getChild(4));
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
            String[] expr_types = visitChildren(ctx.getChild(1)).split(";");
            ArrayList<String> expected_types = this.symbol_table.get_function_arg_types(var);
            // assert expr_types.length() == expected_types.length();
            for (int i = 0; i < expr_types.length(); i++) {
                // assert expected_types[i].equals(expr_types[i]);
            }
        } else {
            String expr_type = visitChildren(ctx.getChild(1));
            // assert expr_type.equals(var_type);
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
            return visitChildren(ctx.getChild(0));
        } else {
            for (int i = 0; i < num_children; i = i + 2) {
                String type = visitChildren(ctx.getChild(i));
                // assert type.equals("int");
            }
            return "int";g
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
            return visitChildren(ctx.getChild(0));
        } else {
            String last_type = visitChildren(ctx.getChild(0));
            for (int i = 2; i < num_children; i = i + 2) {
                String type = visitChildren(ctx.getChild(i));

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
		return visitChildren(ctx);
	}

}
