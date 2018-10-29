// Generated from ../grammar/Tiger.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TigerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TigerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TigerParser#tiger_program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTiger_program(TigerParser.Tiger_programContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#declaration_segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration_segment(TigerParser.Declaration_segmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#type_declaration_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_declaration_list(TigerParser.Type_declaration_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#var_declaration_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_declaration_list(TigerParser.Var_declaration_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#funct_declaration_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunct_declaration_list(TigerParser.Funct_declaration_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#type_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_declaration(TigerParser.Type_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(TigerParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#type_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_id(TigerParser.Type_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#field_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_list(TigerParser.Field_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#var_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_declaration(TigerParser.Var_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#id_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_list(TigerParser.Id_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#id_list_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_list_tail(TigerParser.Id_list_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#optional_init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptional_init(TigerParser.Optional_initContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#funct_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunct_declaration(TigerParser.Funct_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#param_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_list(TigerParser.Param_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#param_list_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_list_tail(TigerParser.Param_list_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#ret_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRet_type(TigerParser.Ret_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(TigerParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#stat_seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_seq(TigerParser.Stat_seqContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#stat_seq_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_seq_tail(TigerParser.Stat_seq_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(TigerParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#stat_tail_a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_tail_a(TigerParser.Stat_tail_aContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#stat_tail_b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_tail_b(TigerParser.Stat_tail_bContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#assign_or_func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_or_func(TigerParser.Assign_or_funcContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#aof_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAof_tail(TigerParser.Aof_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(TigerParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSign(TigerParser.SignContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#constant_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant_tail(TigerParser.Constant_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(TigerParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#logic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic_expr(TigerParser.Logic_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#cond_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond_expr(TigerParser.Cond_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(TigerParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(TigerParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(TigerParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#atom_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_tail(TigerParser.Atom_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list(TigerParser.Expr_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#expr_list_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_tail(TigerParser.Expr_list_tailContext ctx);
}