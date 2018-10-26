import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


public class Tiger {


    public static void main(String[] args) throws Exception {
        ANTLRFileStream str = new ANTLRFileStream(args[0]);
        TigerLexer lex = new TigerLexer(str);
        CommonTokenStream tok = new CommonTokenStream(lex);

        TigerParser parser = new TigerParser(tok);
        ParseTree tree = parser.tiger_program();

        System.out.println(new MyVisitor().visit(tree));
    }


}


class MyVisitor extends TigerBaseVisitor<String>{
	@Override 
    public String visitTiger_program(TigerParser.Tiger_programContext ctx) {
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
		return visitChildren(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public String visitStat_tail_a(TigerParser.Stat_tail_aContext ctx) {
		System.out.println(ctx.toString());
		return visitChildren(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public String visitStat_tail_b(TigerParser.Stat_tail_bContext ctx) {
		System.out.println(ctx.toString());
		return visitChildren(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public String visitAssign_or_func(TigerParser.Assign_or_funcContext ctx) {
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
		return visitChildren(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public String visitConstant(TigerParser.ConstantContext ctx) {
		System.out.println(ctx.toString());
		return visitChildren(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public String visitSign(TigerParser.SignContext ctx) {
		System.out.println(ctx.toString());
		return visitChildren(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public String visitConstant_tail(TigerParser.Constant_tailContext ctx) {
		System.out.println(ctx.toString());
		return visitChildren(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public String visitExpr(TigerParser.ExprContext ctx) {
		System.out.println(ctx.toString());
		return visitChildren(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public String visitLogic_expr(TigerParser.Logic_exprContext ctx) {
		System.out.println(ctx.toString());
		return visitChildren(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public String visitCond_expr(TigerParser.Cond_exprContext ctx) {
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
		return visitChildren(ctx);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override 
	public String visitFactor_tail(TigerParser.Factor_tailContext ctx) {
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
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
		System.out.println(ctx.toString());
		return visitChildren(ctx);
	}

}