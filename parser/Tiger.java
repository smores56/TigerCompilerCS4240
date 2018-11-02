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

        //SemanticAnalysisVisitor analyzer = new SemanticAnalysisVisitor();
        //analyzer.visit(tree);

        IRGenVisitor visitor = new IRGenVisitor(new SymbolTable());//analyzer.get_symbol_table());
        visitor.visit(tree);
    }
}


class IRGenVisitor extends TigerBaseVisitor<String> {
    private SymbolTable symbol_table;
    private Stack<Scope> scopes;

    public void emit(String s){
  System.out.println(s);
}

    private boolean isLeaf(ParserRuleContext ctx){
        for(int i = 0; i < ctx.getChildCount(); i++){
            if(ctx.getChild(i).getText() != ""){
                return false;
            }
        }
        return true;
    }

    public IRGenVisitor(SymbolTable symbol_table)  {
        this.symbol_table = symbol_table;
        this.scopes = new Stack();
        this.scopes.push(new Scope("main","void"));
    }

    @Override
    public String visitTiger_program(TigerParser.Tiger_programContext ctx) {
        String s =  visitChildren(ctx);
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
    public String visitDeclaration_segment(TigerParser.Declaration_segmentContext ctx) {
        String s = visitChildren(ctx);
    //emit(s);
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
        if (ctx.getChild(3).getText().contains("[")) {
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
    Scope newFunc;
    if(ctx.ret_type().getText() != ""){
    newFunc = new Scope(ctx.getChild(1).getText(), ctx.ret_type().getText());
  }else{
    newFunc = new Scope(ctx.getChild(1).getText(), "void");
  }
    this.scopes.push(newFunc);
        String t =  visitChildren(ctx);
    emit(this.scopes.pop().string());
    return t;
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
    this.scopes.peek().addParam(ctx.getChild(0).getText(), ctx.getChild(2).getText());
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
        String dReg = "";
        String sReg = visit(ctx.getChild(0));
        if(ctx.getChildCount() > 1){
        for(int i = 1; i < ctx.getChildCount() - 1; i += 2){
            String c = visit(ctx.getChild(i+1));
        dReg = this.scopes.peek().newReg();
            String op = (ctx.getChild(i).getText() == "-") ? "sub" : "add";
            this.scopes.peek().addOp(op+", "+sReg+", "+c+", "+dReg);
            sReg = dReg;
        }
    }
    else{
        String c = visit(ctx.getChild(0));
    dReg = this.scopes.peek().newReg();
        this.scopes.peek().addOp("assign, "+dReg+", "+c);
    }
    return dReg;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitTerm(TigerParser.TermContext ctx) {
        String dReg = "";
        String sReg = visit(ctx.getChild(0));
        if(ctx.getChildCount() > 1){
        for(int i = 1; i < ctx.getChildCount() - 1; i += 2){
            String c = visit(ctx.getChild(i+1));
        dReg = this.scopes.peek().newReg();
            String op = (ctx.getChild(i).getText() == "*") ? "mult" : "div";
            this.scopes.peek().addOp(op+", "+sReg+", "+c+", "+dReg);
            sReg = dReg;
        }
    }
    else{
        String c = visit(ctx.getChild(0));
    dReg = this.scopes.peek().newReg();
        this.scopes.peek().addOp("assign, "+dReg+", "+c);
    }
    return dReg;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitFactor(TigerParser.FactorContext ctx) {
        String dReg = "";
        String sReg = visit(ctx.getChild(0));
        if(ctx.getChildCount() > 1){
        for(int i = 1; i < ctx.getChildCount() - 1; i += 2){
            String c = visit(ctx.getChild(i+1));
        dReg = this.scopes.peek().newReg();
            String op = "pow";
            this.scopes.peek().addOp(op+", "+sReg+", "+c+", "+dReg);
            sReg = dReg;
        }
    }
    else{
        String c = visit(ctx.getChild(0));
    dReg = this.scopes.peek().newReg();
        this.scopes.peek().addOp("assign, "+dReg+", "+c);
    }
    return dReg;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitAtom(TigerParser.AtomContext ctx) {
        if(ctx.constant() != null){
        return visitChildren(ctx);
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
