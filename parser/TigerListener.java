// Generated from ../grammar/Tiger.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TigerParser}.
 */
public interface TigerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TigerParser#tiger_program}.
	 * @param ctx the parse tree
	 */
	void enterTiger_program(TigerParser.Tiger_programContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#tiger_program}.
	 * @param ctx the parse tree
	 */
	void exitTiger_program(TigerParser.Tiger_programContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#declaration_segment}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration_segment(TigerParser.Declaration_segmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#declaration_segment}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration_segment(TigerParser.Declaration_segmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterType_declaration_list(TigerParser.Type_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitType_declaration_list(TigerParser.Type_declaration_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#var_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterVar_declaration_list(TigerParser.Var_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#var_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitVar_declaration_list(TigerParser.Var_declaration_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#funct_declaration_list}.
	 * @param ctx the parse tree
	 */
	void enterFunct_declaration_list(TigerParser.Funct_declaration_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#funct_declaration_list}.
	 * @param ctx the parse tree
	 */
	void exitFunct_declaration_list(TigerParser.Funct_declaration_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void enterType_declaration(TigerParser.Type_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void exitType_declaration(TigerParser.Type_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(TigerParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(TigerParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type_id}.
	 * @param ctx the parse tree
	 */
	void enterType_id(TigerParser.Type_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type_id}.
	 * @param ctx the parse tree
	 */
	void exitType_id(TigerParser.Type_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#field_list}.
	 * @param ctx the parse tree
	 */
	void enterField_list(TigerParser.Field_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#field_list}.
	 * @param ctx the parse tree
	 */
	void exitField_list(TigerParser.Field_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVar_declaration(TigerParser.Var_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVar_declaration(TigerParser.Var_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#id_list}.
	 * @param ctx the parse tree
	 */
	void enterId_list(TigerParser.Id_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#id_list}.
	 * @param ctx the parse tree
	 */
	void exitId_list(TigerParser.Id_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#id_list_tail}.
	 * @param ctx the parse tree
	 */
	void enterId_list_tail(TigerParser.Id_list_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#id_list_tail}.
	 * @param ctx the parse tree
	 */
	void exitId_list_tail(TigerParser.Id_list_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#optional_init}.
	 * @param ctx the parse tree
	 */
	void enterOptional_init(TigerParser.Optional_initContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#optional_init}.
	 * @param ctx the parse tree
	 */
	void exitOptional_init(TigerParser.Optional_initContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#funct_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunct_declaration(TigerParser.Funct_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#funct_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunct_declaration(TigerParser.Funct_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#param_list}.
	 * @param ctx the parse tree
	 */
	void enterParam_list(TigerParser.Param_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#param_list}.
	 * @param ctx the parse tree
	 */
	void exitParam_list(TigerParser.Param_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#param_list_tail}.
	 * @param ctx the parse tree
	 */
	void enterParam_list_tail(TigerParser.Param_list_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#param_list_tail}.
	 * @param ctx the parse tree
	 */
	void exitParam_list_tail(TigerParser.Param_list_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#ret_type}.
	 * @param ctx the parse tree
	 */
	void enterRet_type(TigerParser.Ret_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#ret_type}.
	 * @param ctx the parse tree
	 */
	void exitRet_type(TigerParser.Ret_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(TigerParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(TigerParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_seq}.
	 * @param ctx the parse tree
	 */
	void enterStat_seq(TigerParser.Stat_seqContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_seq}.
	 * @param ctx the parse tree
	 */
	void exitStat_seq(TigerParser.Stat_seqContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_seq_tail}.
	 * @param ctx the parse tree
	 */
	void enterStat_seq_tail(TigerParser.Stat_seq_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_seq_tail}.
	 * @param ctx the parse tree
	 */
	void exitStat_seq_tail(TigerParser.Stat_seq_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(TigerParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(TigerParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_tail_a}.
	 * @param ctx the parse tree
	 */
	void enterStat_tail_a(TigerParser.Stat_tail_aContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_tail_a}.
	 * @param ctx the parse tree
	 */
	void exitStat_tail_a(TigerParser.Stat_tail_aContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#stat_tail_b}.
	 * @param ctx the parse tree
	 */
	void enterStat_tail_b(TigerParser.Stat_tail_bContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#stat_tail_b}.
	 * @param ctx the parse tree
	 */
	void exitStat_tail_b(TigerParser.Stat_tail_bContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#assign_or_func}.
	 * @param ctx the parse tree
	 */
	void enterAssign_or_func(TigerParser.Assign_or_funcContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#assign_or_func}.
	 * @param ctx the parse tree
	 */
	void exitAssign_or_func(TigerParser.Assign_or_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#aof_tail}.
	 * @param ctx the parse tree
	 */
	void enterAof_tail(TigerParser.Aof_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#aof_tail}.
	 * @param ctx the parse tree
	 */
	void exitAof_tail(TigerParser.Aof_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(TigerParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(TigerParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#sign}.
	 * @param ctx the parse tree
	 */
	void enterSign(TigerParser.SignContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#sign}.
	 * @param ctx the parse tree
	 */
	void exitSign(TigerParser.SignContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#constant_tail}.
	 * @param ctx the parse tree
	 */
	void enterConstant_tail(TigerParser.Constant_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#constant_tail}.
	 * @param ctx the parse tree
	 */
	void exitConstant_tail(TigerParser.Constant_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(TigerParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(TigerParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#logic_expr}.
	 * @param ctx the parse tree
	 */
	void enterLogic_expr(TigerParser.Logic_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#logic_expr}.
	 * @param ctx the parse tree
	 */
	void exitLogic_expr(TigerParser.Logic_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#cond_expr}.
	 * @param ctx the parse tree
	 */
	void enterCond_expr(TigerParser.Cond_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#cond_expr}.
	 * @param ctx the parse tree
	 */
	void exitCond_expr(TigerParser.Cond_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(TigerParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(TigerParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(TigerParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(TigerParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#factor_tail}.
	 * @param ctx the parse tree
	 */
	void enterFactor_tail(TigerParser.Factor_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#factor_tail}.
	 * @param ctx the parse tree
	 */
	void exitFactor_tail(TigerParser.Factor_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(TigerParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(TigerParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#atom_tail}.
	 * @param ctx the parse tree
	 */
	void enterAtom_tail(TigerParser.Atom_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#atom_tail}.
	 * @param ctx the parse tree
	 */
	void exitAtom_tail(TigerParser.Atom_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(TigerParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(TigerParser.Expr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_tail(TigerParser.Expr_list_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_tail(TigerParser.Expr_list_tailContext ctx);
}