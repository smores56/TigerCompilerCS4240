// Generated from ./Tiger.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TigerParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, COMMENT=2, VAR=3, MAIN=4, SEMI=5, COMMA=6, EQUALS=7, OF=8, ARRAY=9, 
		RECORD=10, END=11, LBRACKET=12, RBRACKET=13, COLON=14, ASSIGN=15, FUNCTION=16, 
		LPARENS=17, RPARENS=18, BEGIN=19, IF=20, ENDIF=21, THEN=22, ELSE=23, WHILE=24, 
		DO=25, ENDDO=26, FOR=27, TO=28, BREAK=29, RETURN=30, LET=31, IN=32, DOT=33, 
		POWER=34, PLUS=35, MINUS=36, MULT=37, DIV=38, NEQ=39, LESS=40, GREATER=41, 
		LTEQ=42, GTEQ=43, AND=44, OR=45, TYPE=46, INT_KW=47, FLOAT_KW=48, ID=49, 
		INT=50, FLOAT=51;
	public static final int
		RULE_tiger_program = 0, RULE_declaration_segment = 1, RULE_type_declaration_list = 2, 
		RULE_var_declaration_list = 3, RULE_funct_declaration_list = 4, RULE_type_declaration = 5, 
		RULE_type = 6, RULE_type_id = 7, RULE_field_list = 8, RULE_var_declaration = 9, 
		RULE_id_list = 10, RULE_id_list_tail = 11, RULE_optional_init = 12, RULE_funct_declaration = 13, 
		RULE_param_list = 14, RULE_param_list_tail = 15, RULE_ret_type = 16, RULE_param = 17, 
		RULE_stat_seq = 18, RULE_stat_seq_tail = 19, RULE_stat = 20, RULE_stat_tail_a = 21, 
		RULE_stat_tail_b = 22, RULE_assign_or_func = 23, RULE_aof_tail_a = 24, 
		RULE_aof_tail_b = 25, RULE_assign = 26, RULE_constant = 27, RULE_sign = 28, 
		RULE_constant_tail = 29, RULE_expr = 30, RULE_logic_expr = 31, RULE_cond_expr = 32, 
		RULE_term = 33, RULE_factor = 34, RULE_atom = 35, RULE_atom_tail = 36, 
		RULE_expr_list = 37, RULE_expr_list_tail = 38;
	public static final String[] ruleNames = {
		"tiger_program", "declaration_segment", "type_declaration_list", "var_declaration_list", 
		"funct_declaration_list", "type_declaration", "type", "type_id", "field_list", 
		"var_declaration", "id_list", "id_list_tail", "optional_init", "funct_declaration", 
		"param_list", "param_list_tail", "ret_type", "param", "stat_seq", "stat_seq_tail", 
		"stat", "stat_tail_a", "stat_tail_b", "assign_or_func", "aof_tail_a", 
		"aof_tail_b", "assign", "constant", "sign", "constant_tail", "expr", "logic_expr", 
		"cond_expr", "term", "factor", "atom", "atom_tail", "expr_list", "expr_list_tail"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'var'", "'main'", "';'", "','", "'='", "'of'", "'array'", 
		"'record'", "'end'", "'['", "']'", "':'", "':='", "'function'", "'('", 
		"')'", "'begin'", "'if'", "'endif'", "'then'", "'else'", "'while'", "'do'", 
		"'enddo'", "'for'", "'to'", "'break'", "'return'", "'let'", "'in'", "'.'", 
		"'**'", "'+'", "'-'", "'*'", "'/'", "'<>'", "'<'", "'>'", "'<='", "'>='", 
		"'&'", "'|'", "'type'", "'int'", "'float'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "COMMENT", "VAR", "MAIN", "SEMI", "COMMA", "EQUALS", "OF", 
		"ARRAY", "RECORD", "END", "LBRACKET", "RBRACKET", "COLON", "ASSIGN", "FUNCTION", 
		"LPARENS", "RPARENS", "BEGIN", "IF", "ENDIF", "THEN", "ELSE", "WHILE", 
		"DO", "ENDDO", "FOR", "TO", "BREAK", "RETURN", "LET", "IN", "DOT", "POWER", 
		"PLUS", "MINUS", "MULT", "DIV", "NEQ", "LESS", "GREATER", "LTEQ", "GTEQ", 
		"AND", "OR", "TYPE", "INT_KW", "FLOAT_KW", "ID", "INT", "FLOAT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Tiger.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TigerParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Tiger_programContext extends ParserRuleContext {
		public TerminalNode MAIN() { return getToken(TigerParser.MAIN, 0); }
		public TerminalNode LET() { return getToken(TigerParser.LET, 0); }
		public Declaration_segmentContext declaration_segment() {
			return getRuleContext(Declaration_segmentContext.class,0);
		}
		public TerminalNode IN() { return getToken(TigerParser.IN, 0); }
		public TerminalNode BEGIN() { return getToken(TigerParser.BEGIN, 0); }
		public Stat_seqContext stat_seq() {
			return getRuleContext(Stat_seqContext.class,0);
		}
		public TerminalNode END() { return getToken(TigerParser.END, 0); }
		public TerminalNode EOF() { return getToken(TigerParser.EOF, 0); }
		public Tiger_programContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tiger_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitTiger_program(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tiger_programContext tiger_program() throws RecognitionException {
		Tiger_programContext _localctx = new Tiger_programContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_tiger_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(MAIN);
			setState(79);
			match(LET);
			setState(80);
			declaration_segment();
			setState(81);
			match(IN);
			setState(82);
			match(BEGIN);
			setState(83);
			stat_seq();
			setState(84);
			match(END);
			setState(85);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Declaration_segmentContext extends ParserRuleContext {
		public Type_declaration_listContext type_declaration_list() {
			return getRuleContext(Type_declaration_listContext.class,0);
		}
		public Var_declaration_listContext var_declaration_list() {
			return getRuleContext(Var_declaration_listContext.class,0);
		}
		public Funct_declaration_listContext funct_declaration_list() {
			return getRuleContext(Funct_declaration_listContext.class,0);
		}
		public Declaration_segmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_segment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitDeclaration_segment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Declaration_segmentContext declaration_segment() throws RecognitionException {
		Declaration_segmentContext _localctx = new Declaration_segmentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration_segment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			type_declaration_list();
			setState(88);
			var_declaration_list();
			setState(89);
			funct_declaration_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_declaration_listContext extends ParserRuleContext {
		public Type_declarationContext type_declaration() {
			return getRuleContext(Type_declarationContext.class,0);
		}
		public Type_declaration_listContext type_declaration_list() {
			return getRuleContext(Type_declaration_listContext.class,0);
		}
		public Type_declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_declaration_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitType_declaration_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_declaration_listContext type_declaration_list() throws RecognitionException {
		Type_declaration_listContext _localctx = new Type_declaration_listContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type_declaration_list);
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				type_declaration();
				setState(92);
				type_declaration_list();
				}
				break;
			case VAR:
			case FUNCTION:
			case IN:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_declaration_listContext extends ParserRuleContext {
		public Var_declarationContext var_declaration() {
			return getRuleContext(Var_declarationContext.class,0);
		}
		public Var_declaration_listContext var_declaration_list() {
			return getRuleContext(Var_declaration_listContext.class,0);
		}
		public Var_declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_declaration_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitVar_declaration_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_declaration_listContext var_declaration_list() throws RecognitionException {
		Var_declaration_listContext _localctx = new Var_declaration_listContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_var_declaration_list);
		try {
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				var_declaration();
				setState(98);
				var_declaration_list();
				}
				break;
			case FUNCTION:
			case IN:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Funct_declaration_listContext extends ParserRuleContext {
		public Funct_declarationContext funct_declaration() {
			return getRuleContext(Funct_declarationContext.class,0);
		}
		public Funct_declaration_listContext funct_declaration_list() {
			return getRuleContext(Funct_declaration_listContext.class,0);
		}
		public Funct_declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funct_declaration_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitFunct_declaration_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Funct_declaration_listContext funct_declaration_list() throws RecognitionException {
		Funct_declaration_listContext _localctx = new Funct_declaration_listContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_funct_declaration_list);
		try {
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				funct_declaration();
				setState(104);
				funct_declaration_list();
				}
				break;
			case IN:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_declarationContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(TigerParser.TYPE, 0); }
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public TerminalNode EQUALS() { return getToken(TigerParser.EQUALS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(TigerParser.SEMI, 0); }
		public Type_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_declaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitType_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_declarationContext type_declaration() throws RecognitionException {
		Type_declarationContext _localctx = new Type_declarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(TYPE);
			setState(110);
			match(ID);
			setState(111);
			match(EQUALS);
			setState(112);
			type();
			setState(113);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public Type_idContext type_id() {
			return getRuleContext(Type_idContext.class,0);
		}
		public TerminalNode ARRAY() { return getToken(TigerParser.ARRAY, 0); }
		public TerminalNode LBRACKET() { return getToken(TigerParser.LBRACKET, 0); }
		public TerminalNode INT() { return getToken(TigerParser.INT, 0); }
		public TerminalNode RBRACKET() { return getToken(TigerParser.RBRACKET, 0); }
		public TerminalNode OF() { return getToken(TigerParser.OF, 0); }
		public TerminalNode RECORD() { return getToken(TigerParser.RECORD, 0); }
		public Field_listContext field_list() {
			return getRuleContext(Field_listContext.class,0);
		}
		public TerminalNode END() { return getToken(TigerParser.END, 0); }
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		try {
			setState(127);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_KW:
			case FLOAT_KW:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				type_id();
				}
				break;
			case ARRAY:
				enterOuterAlt(_localctx, 2);
				{
				setState(116);
				match(ARRAY);
				setState(117);
				match(LBRACKET);
				setState(118);
				match(INT);
				setState(119);
				match(RBRACKET);
				setState(120);
				match(OF);
				setState(121);
				type_id();
				}
				break;
			case RECORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				match(RECORD);
				setState(123);
				field_list();
				setState(124);
				match(END);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(126);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_idContext extends ParserRuleContext {
		public TerminalNode INT_KW() { return getToken(TigerParser.INT_KW, 0); }
		public TerminalNode FLOAT_KW() { return getToken(TigerParser.FLOAT_KW, 0); }
		public Type_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_id; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitType_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_idContext type_id() throws RecognitionException {
		Type_idContext _localctx = new Type_idContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_la = _input.LA(1);
			if ( !(_la==INT_KW || _la==FLOAT_KW) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_listContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public TerminalNode COLON() { return getToken(TigerParser.COLON, 0); }
		public Type_idContext type_id() {
			return getRuleContext(Type_idContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(TigerParser.SEMI, 0); }
		public Field_listContext field_list() {
			return getRuleContext(Field_listContext.class,0);
		}
		public Field_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitField_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Field_listContext field_list() throws RecognitionException {
		Field_listContext _localctx = new Field_listContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_field_list);
		try {
			setState(138);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				match(ID);
				setState(132);
				match(COLON);
				setState(133);
				type_id();
				setState(134);
				match(SEMI);
				setState(135);
				field_list();
				}
				break;
			case END:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_declarationContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(TigerParser.VAR, 0); }
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TigerParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Optional_initContext optional_init() {
			return getRuleContext(Optional_initContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(TigerParser.SEMI, 0); }
		public Var_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_declaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitVar_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_declarationContext var_declaration() throws RecognitionException {
		Var_declarationContext _localctx = new Var_declarationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_var_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(VAR);
			setState(141);
			id_list();
			setState(142);
			match(COLON);
			setState(143);
			type();
			setState(144);
			optional_init();
			setState(145);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_listContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public Id_list_tailContext id_list_tail() {
			return getRuleContext(Id_list_tailContext.class,0);
		}
		public Id_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitId_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Id_listContext id_list() throws RecognitionException {
		Id_listContext _localctx = new Id_listContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_id_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(ID);
			setState(148);
			id_list_tail();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_list_tailContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(TigerParser.COMMA, 0); }
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public Id_list_tailContext id_list_tail() {
			return getRuleContext(Id_list_tailContext.class,0);
		}
		public Id_list_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_list_tail; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitId_list_tail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Id_list_tailContext id_list_tail() throws RecognitionException {
		Id_list_tailContext _localctx = new Id_list_tailContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_id_list_tail);
		try {
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				match(COMMA);
				setState(151);
				match(ID);
				setState(152);
				id_list_tail();
				}
				break;
			case COLON:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Optional_initContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(TigerParser.ASSIGN, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public Optional_initContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optional_init; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitOptional_init(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Optional_initContext optional_init() throws RecognitionException {
		Optional_initContext _localctx = new Optional_initContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_optional_init);
		try {
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				match(ASSIGN);
				setState(157);
				constant();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Funct_declarationContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(TigerParser.FUNCTION, 0); }
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public TerminalNode LPARENS() { return getToken(TigerParser.LPARENS, 0); }
		public Param_listContext param_list() {
			return getRuleContext(Param_listContext.class,0);
		}
		public TerminalNode RPARENS() { return getToken(TigerParser.RPARENS, 0); }
		public Ret_typeContext ret_type() {
			return getRuleContext(Ret_typeContext.class,0);
		}
		public TerminalNode BEGIN() { return getToken(TigerParser.BEGIN, 0); }
		public Stat_seqContext stat_seq() {
			return getRuleContext(Stat_seqContext.class,0);
		}
		public TerminalNode END() { return getToken(TigerParser.END, 0); }
		public TerminalNode SEMI() { return getToken(TigerParser.SEMI, 0); }
		public Funct_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funct_declaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitFunct_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Funct_declarationContext funct_declaration() throws RecognitionException {
		Funct_declarationContext _localctx = new Funct_declarationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_funct_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(FUNCTION);
			setState(162);
			match(ID);
			setState(163);
			match(LPARENS);
			setState(164);
			param_list();
			setState(165);
			match(RPARENS);
			setState(166);
			ret_type();
			setState(167);
			match(BEGIN);
			setState(168);
			stat_seq();
			setState(169);
			match(END);
			setState(170);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_listContext extends ParserRuleContext {
		public ParamContext param() {
			return getRuleContext(ParamContext.class,0);
		}
		public Param_list_tailContext param_list_tail() {
			return getRuleContext(Param_list_tailContext.class,0);
		}
		public Param_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitParam_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Param_listContext param_list() throws RecognitionException {
		Param_listContext _localctx = new Param_listContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_param_list);
		try {
			setState(176);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				param();
				setState(173);
				param_list_tail();
				}
				break;
			case RPARENS:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_list_tailContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(TigerParser.COMMA, 0); }
		public ParamContext param() {
			return getRuleContext(ParamContext.class,0);
		}
		public Param_list_tailContext param_list_tail() {
			return getRuleContext(Param_list_tailContext.class,0);
		}
		public Param_list_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_list_tail; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitParam_list_tail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Param_list_tailContext param_list_tail() throws RecognitionException {
		Param_list_tailContext _localctx = new Param_list_tailContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_param_list_tail);
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				match(COMMA);
				setState(179);
				param();
				setState(180);
				param_list_tail();
				}
				break;
			case RPARENS:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ret_typeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Ret_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ret_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitRet_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ret_typeContext ret_type() throws RecognitionException {
		Ret_typeContext _localctx = new Ret_typeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ret_type);
		try {
			setState(187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ARRAY:
			case RECORD:
			case INT_KW:
			case FLOAT_KW:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				type();
				}
				break;
			case BEGIN:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public TerminalNode COLON() { return getToken(TigerParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(ID);
			setState(190);
			match(COLON);
			setState(191);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Stat_seqContext extends ParserRuleContext {
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public Stat_seq_tailContext stat_seq_tail() {
			return getRuleContext(Stat_seq_tailContext.class,0);
		}
		public Stat_seqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat_seq; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitStat_seq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stat_seqContext stat_seq() throws RecognitionException {
		Stat_seqContext _localctx = new Stat_seqContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_stat_seq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			stat();
			setState(194);
			stat_seq_tail();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Stat_seq_tailContext extends ParserRuleContext {
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public Stat_seq_tailContext stat_seq_tail() {
			return getRuleContext(Stat_seq_tailContext.class,0);
		}
		public Stat_seq_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat_seq_tail; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitStat_seq_tail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stat_seq_tailContext stat_seq_tail() throws RecognitionException {
		Stat_seq_tailContext _localctx = new Stat_seq_tailContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_stat_seq_tail);
		try {
			setState(200);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
			case WHILE:
			case FOR:
			case BREAK:
			case RETURN:
			case LET:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				stat();
				setState(197);
				stat_seq_tail();
				}
				break;
			case END:
			case ENDIF:
			case ELSE:
			case ENDDO:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public Assign_or_funcContext assign_or_func() {
			return getRuleContext(Assign_or_funcContext.class,0);
		}
		public Stat_tail_aContext stat_tail_a() {
			return getRuleContext(Stat_tail_aContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(TigerParser.WHILE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DO() { return getToken(TigerParser.DO, 0); }
		public Stat_seqContext stat_seq() {
			return getRuleContext(Stat_seqContext.class,0);
		}
		public TerminalNode ENDDO() { return getToken(TigerParser.ENDDO, 0); }
		public TerminalNode SEMI() { return getToken(TigerParser.SEMI, 0); }
		public TerminalNode FOR() { return getToken(TigerParser.FOR, 0); }
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(TigerParser.ASSIGN, 0); }
		public TerminalNode TO() { return getToken(TigerParser.TO, 0); }
		public TerminalNode BREAK() { return getToken(TigerParser.BREAK, 0); }
		public TerminalNode RETURN() { return getToken(TigerParser.RETURN, 0); }
		public TerminalNode LET() { return getToken(TigerParser.LET, 0); }
		public Declaration_segmentContext declaration_segment() {
			return getRuleContext(Declaration_segmentContext.class,0);
		}
		public TerminalNode IN() { return getToken(TigerParser.IN, 0); }
		public TerminalNode END() { return getToken(TigerParser.END, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_stat);
		try {
			setState(234);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				assign_or_func();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				stat_tail_a();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(204);
				match(WHILE);
				setState(205);
				expr();
				setState(206);
				match(DO);
				setState(207);
				stat_seq();
				setState(208);
				match(ENDDO);
				setState(209);
				match(SEMI);
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 4);
				{
				setState(211);
				match(FOR);
				setState(212);
				match(ID);
				setState(213);
				match(ASSIGN);
				setState(214);
				expr();
				setState(215);
				match(TO);
				setState(216);
				expr();
				setState(217);
				match(DO);
				setState(218);
				stat_seq();
				setState(219);
				match(ENDDO);
				setState(220);
				match(SEMI);
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 5);
				{
				setState(222);
				match(BREAK);
				setState(223);
				match(SEMI);
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 6);
				{
				setState(224);
				match(RETURN);
				setState(225);
				expr();
				setState(226);
				match(SEMI);
				}
				break;
			case LET:
				enterOuterAlt(_localctx, 7);
				{
				setState(228);
				match(LET);
				setState(229);
				declaration_segment();
				setState(230);
				match(IN);
				setState(231);
				stat_seq();
				setState(232);
				match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Stat_tail_aContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(TigerParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(TigerParser.THEN, 0); }
		public Stat_seqContext stat_seq() {
			return getRuleContext(Stat_seqContext.class,0);
		}
		public Stat_tail_bContext stat_tail_b() {
			return getRuleContext(Stat_tail_bContext.class,0);
		}
		public Stat_tail_aContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat_tail_a; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitStat_tail_a(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stat_tail_aContext stat_tail_a() throws RecognitionException {
		Stat_tail_aContext _localctx = new Stat_tail_aContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_stat_tail_a);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(IF);
			setState(237);
			expr();
			setState(238);
			match(THEN);
			setState(239);
			stat_seq();
			setState(240);
			stat_tail_b();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Stat_tail_bContext extends ParserRuleContext {
		public TerminalNode ENDIF() { return getToken(TigerParser.ENDIF, 0); }
		public TerminalNode SEMI() { return getToken(TigerParser.SEMI, 0); }
		public TerminalNode ELSE() { return getToken(TigerParser.ELSE, 0); }
		public Stat_seqContext stat_seq() {
			return getRuleContext(Stat_seqContext.class,0);
		}
		public Stat_tail_bContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat_tail_b; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitStat_tail_b(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stat_tail_bContext stat_tail_b() throws RecognitionException {
		Stat_tail_bContext _localctx = new Stat_tail_bContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_stat_tail_b);
		try {
			setState(249);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ENDIF:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				match(ENDIF);
				setState(243);
				match(SEMI);
				}
				break;
			case ELSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(244);
				match(ELSE);
				setState(245);
				stat_seq();
				setState(246);
				match(ENDIF);
				setState(247);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assign_or_funcContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public Aof_tail_aContext aof_tail_a() {
			return getRuleContext(Aof_tail_aContext.class,0);
		}
		public Assign_or_funcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_or_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitAssign_or_func(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_or_funcContext assign_or_func() throws RecognitionException {
		Assign_or_funcContext _localctx = new Assign_or_funcContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_assign_or_func);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(ID);
			setState(252);
			aof_tail_a();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aof_tail_aContext extends ParserRuleContext {
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public Aof_tail_bContext aof_tail_b() {
			return getRuleContext(Aof_tail_bContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(TigerParser.SEMI, 0); }
		public TerminalNode LPARENS() { return getToken(TigerParser.LPARENS, 0); }
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public TerminalNode RPARENS() { return getToken(TigerParser.RPARENS, 0); }
		public Aof_tail_aContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aof_tail_a; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitAof_tail_a(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aof_tail_aContext aof_tail_a() throws RecognitionException {
		Aof_tail_aContext _localctx = new Aof_tail_aContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_aof_tail_a);
		try {
			setState(264);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACKET:
			case ASSIGN:
			case DOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				assign();
				setState(255);
				aof_tail_b();
				setState(256);
				expr();
				setState(257);
				match(SEMI);
				}
				break;
			case LPARENS:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				match(LPARENS);
				setState(260);
				expr_list();
				setState(261);
				match(RPARENS);
				setState(262);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aof_tail_bContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public Aof_tail_bContext aof_tail_b() {
			return getRuleContext(Aof_tail_bContext.class,0);
		}
		public Aof_tail_bContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aof_tail_b; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitAof_tail_b(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aof_tail_bContext aof_tail_b() throws RecognitionException {
		Aof_tail_bContext _localctx = new Aof_tail_bContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_aof_tail_b);
		try {
			setState(271);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				match(ID);
				setState(267);
				assign();
				setState(268);
				aof_tail_b();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(TigerParser.LBRACKET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(TigerParser.RBRACKET, 0); }
		public TerminalNode ASSIGN() { return getToken(TigerParser.ASSIGN, 0); }
		public TerminalNode DOT() { return getToken(TigerParser.DOT, 0); }
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_assign);
		try {
			setState(282);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACKET:
				enterOuterAlt(_localctx, 1);
				{
				setState(273);
				match(LBRACKET);
				setState(274);
				expr();
				setState(275);
				match(RBRACKET);
				setState(276);
				match(ASSIGN);
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(278);
				match(DOT);
				setState(279);
				match(ID);
				setState(280);
				match(ASSIGN);
				}
				break;
			case ASSIGN:
				enterOuterAlt(_localctx, 3);
				{
				setState(281);
				match(ASSIGN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public Constant_tailContext constant_tail() {
			return getRuleContext(Constant_tailContext.class,0);
		}
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			sign();
			setState(285);
			constant_tail();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SignContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(TigerParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(TigerParser.MINUS, 0); }
		public SignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sign; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitSign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignContext sign() throws RecognitionException {
		SignContext _localctx = new SignContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_sign);
		try {
			setState(290);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(287);
				match(PLUS);
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(288);
				match(MINUS);
				}
				break;
			case INT:
			case FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constant_tailContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(TigerParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(TigerParser.FLOAT, 0); }
		public Constant_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant_tail; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitConstant_tail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Constant_tailContext constant_tail() throws RecognitionException {
		Constant_tailContext _localctx = new Constant_tailContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_constant_tail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<Logic_exprContext> logic_expr() {
			return getRuleContexts(Logic_exprContext.class);
		}
		public Logic_exprContext logic_expr(int i) {
			return getRuleContext(Logic_exprContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(TigerParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(TigerParser.AND, i);
		}
		public List<TerminalNode> OR() { return getTokens(TigerParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(TigerParser.OR, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			logic_expr();
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==OR) {
				{
				{
				setState(295);
				_la = _input.LA(1);
				if ( !(_la==AND || _la==OR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(296);
				logic_expr();
				}
				}
				setState(301);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logic_exprContext extends ParserRuleContext {
		public List<Cond_exprContext> cond_expr() {
			return getRuleContexts(Cond_exprContext.class);
		}
		public Cond_exprContext cond_expr(int i) {
			return getRuleContext(Cond_exprContext.class,i);
		}
		public TerminalNode EQUALS() { return getToken(TigerParser.EQUALS, 0); }
		public TerminalNode NEQ() { return getToken(TigerParser.NEQ, 0); }
		public TerminalNode LESS() { return getToken(TigerParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(TigerParser.GREATER, 0); }
		public TerminalNode LTEQ() { return getToken(TigerParser.LTEQ, 0); }
		public TerminalNode GTEQ() { return getToken(TigerParser.GTEQ, 0); }
		public Logic_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitLogic_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logic_exprContext logic_expr() throws RecognitionException {
		Logic_exprContext _localctx = new Logic_exprContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_logic_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			cond_expr();
			setState(305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUALS) | (1L << NEQ) | (1L << LESS) | (1L << GREATER) | (1L << LTEQ) | (1L << GTEQ))) != 0)) {
				{
				setState(303);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUALS) | (1L << NEQ) | (1L << LESS) | (1L << GREATER) | (1L << LTEQ) | (1L << GTEQ))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(304);
				cond_expr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cond_exprContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(TigerParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(TigerParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(TigerParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(TigerParser.MINUS, i);
		}
		public Cond_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitCond_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cond_exprContext cond_expr() throws RecognitionException {
		Cond_exprContext _localctx = new Cond_exprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_cond_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			term();
			setState(312);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(308);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(309);
				term();
				}
				}
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> MULT() { return getTokens(TigerParser.MULT); }
		public TerminalNode MULT(int i) {
			return getToken(TigerParser.MULT, i);
		}
		public List<TerminalNode> DIV() { return getTokens(TigerParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(TigerParser.DIV, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			factor();
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MULT || _la==DIV) {
				{
				{
				setState(316);
				_la = _input.LA(1);
				if ( !(_la==MULT || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(317);
				factor();
				}
				}
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<TerminalNode> POWER() { return getTokens(TigerParser.POWER); }
		public TerminalNode POWER(int i) {
			return getToken(TigerParser.POWER, i);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			atom();
			setState(328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==POWER) {
				{
				{
				setState(324);
				match(POWER);
				setState(325);
				atom();
				}
				}
				setState(330);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TerminalNode LPARENS() { return getToken(TigerParser.LPARENS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPARENS() { return getToken(TigerParser.RPARENS, 0); }
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public Atom_tailContext atom_tail() {
			return getRuleContext(Atom_tailContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_atom);
		try {
			setState(338);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case INT:
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				constant();
				}
				break;
			case LPARENS:
				enterOuterAlt(_localctx, 2);
				{
				setState(332);
				match(LPARENS);
				setState(333);
				expr();
				setState(334);
				match(RPARENS);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(336);
				match(ID);
				setState(337);
				atom_tail();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Atom_tailContext extends ParserRuleContext {
		public TerminalNode LPARENS() { return getToken(TigerParser.LPARENS, 0); }
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public TerminalNode RPARENS() { return getToken(TigerParser.RPARENS, 0); }
		public TerminalNode LBRACKET() { return getToken(TigerParser.LBRACKET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(TigerParser.RBRACKET, 0); }
		public TerminalNode DOT() { return getToken(TigerParser.DOT, 0); }
		public TerminalNode ID() { return getToken(TigerParser.ID, 0); }
		public Atom_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom_tail; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitAtom_tail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atom_tailContext atom_tail() throws RecognitionException {
		Atom_tailContext _localctx = new Atom_tailContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_atom_tail);
		try {
			setState(351);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPARENS:
				enterOuterAlt(_localctx, 1);
				{
				setState(340);
				match(LPARENS);
				setState(341);
				expr_list();
				setState(342);
				match(RPARENS);
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(344);
				match(LBRACKET);
				setState(345);
				expr();
				setState(346);
				match(RBRACKET);
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(348);
				match(DOT);
				setState(349);
				match(ID);
				}
				break;
			case SEMI:
			case COMMA:
			case EQUALS:
			case RBRACKET:
			case RPARENS:
			case THEN:
			case DO:
			case TO:
			case POWER:
			case PLUS:
			case MINUS:
			case MULT:
			case DIV:
			case NEQ:
			case LESS:
			case GREATER:
			case LTEQ:
			case GTEQ:
			case AND:
			case OR:
				enterOuterAlt(_localctx, 4);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_listContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_list_tailContext expr_list_tail() {
			return getRuleContext(Expr_list_tailContext.class,0);
		}
		public Expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitExpr_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_listContext expr_list() throws RecognitionException {
		Expr_listContext _localctx = new Expr_listContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_expr_list);
		try {
			setState(357);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPARENS:
			case PLUS:
			case MINUS:
			case ID:
			case INT:
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(353);
				expr();
				setState(354);
				expr_list_tail();
				}
				break;
			case RPARENS:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_list_tailContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(TigerParser.COMMA, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_list_tailContext expr_list_tail() {
			return getRuleContext(Expr_list_tailContext.class,0);
		}
		public Expr_list_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list_tail; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TigerVisitor ) return ((TigerVisitor<? extends T>)visitor).visitExpr_list_tail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_list_tailContext expr_list_tail() throws RecognitionException {
		Expr_list_tailContext _localctx = new Expr_list_tailContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_expr_list_tail);
		try {
			setState(364);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(359);
				match(COMMA);
				setState(360);
				expr();
				setState(361);
				expr_list_tail();
				}
				break;
			case RPARENS:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\65\u0171\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4b\n\4\3\5\3\5\3\5"+
		"\3\5\5\5h\n\5\3\6\3\6\3\6\3\6\5\6n\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0082\n\b\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\5\n\u008d\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u009d\n\r\3\16\3\16\3\16\5\16\u00a2\n"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\5\20\u00b3\n\20\3\21\3\21\3\21\3\21\3\21\5\21\u00ba\n\21\3\22"+
		"\3\22\5\22\u00be\n\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\5\25\u00cb\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00ed\n\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00fc\n\30\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u010b"+
		"\n\32\3\33\3\33\3\33\3\33\3\33\5\33\u0112\n\33\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\5\34\u011d\n\34\3\35\3\35\3\35\3\36\3\36\3\36\5\36"+
		"\u0125\n\36\3\37\3\37\3 \3 \3 \7 \u012c\n \f \16 \u012f\13 \3!\3!\3!\5"+
		"!\u0134\n!\3\"\3\"\3\"\7\"\u0139\n\"\f\"\16\"\u013c\13\"\3#\3#\3#\7#\u0141"+
		"\n#\f#\16#\u0144\13#\3$\3$\3$\7$\u0149\n$\f$\16$\u014c\13$\3%\3%\3%\3"+
		"%\3%\3%\3%\5%\u0155\n%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\5&\u0162\n&\3"+
		"\'\3\'\3\'\3\'\5\'\u0168\n\'\3(\3(\3(\3(\3(\5(\u016f\n(\3(\2\2)\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLN\2\b"+
		"\3\2\61\62\3\2\64\65\3\2./\4\2\t\t)-\3\2%&\3\2\'(\2\u016f\2P\3\2\2\2\4"+
		"Y\3\2\2\2\6a\3\2\2\2\bg\3\2\2\2\nm\3\2\2\2\fo\3\2\2\2\16\u0081\3\2\2\2"+
		"\20\u0083\3\2\2\2\22\u008c\3\2\2\2\24\u008e\3\2\2\2\26\u0095\3\2\2\2\30"+
		"\u009c\3\2\2\2\32\u00a1\3\2\2\2\34\u00a3\3\2\2\2\36\u00b2\3\2\2\2 \u00b9"+
		"\3\2\2\2\"\u00bd\3\2\2\2$\u00bf\3\2\2\2&\u00c3\3\2\2\2(\u00ca\3\2\2\2"+
		"*\u00ec\3\2\2\2,\u00ee\3\2\2\2.\u00fb\3\2\2\2\60\u00fd\3\2\2\2\62\u010a"+
		"\3\2\2\2\64\u0111\3\2\2\2\66\u011c\3\2\2\28\u011e\3\2\2\2:\u0124\3\2\2"+
		"\2<\u0126\3\2\2\2>\u0128\3\2\2\2@\u0130\3\2\2\2B\u0135\3\2\2\2D\u013d"+
		"\3\2\2\2F\u0145\3\2\2\2H\u0154\3\2\2\2J\u0161\3\2\2\2L\u0167\3\2\2\2N"+
		"\u016e\3\2\2\2PQ\7\6\2\2QR\7!\2\2RS\5\4\3\2ST\7\"\2\2TU\7\25\2\2UV\5&"+
		"\24\2VW\7\r\2\2WX\7\2\2\3X\3\3\2\2\2YZ\5\6\4\2Z[\5\b\5\2[\\\5\n\6\2\\"+
		"\5\3\2\2\2]^\5\f\7\2^_\5\6\4\2_b\3\2\2\2`b\3\2\2\2a]\3\2\2\2a`\3\2\2\2"+
		"b\7\3\2\2\2cd\5\24\13\2de\5\b\5\2eh\3\2\2\2fh\3\2\2\2gc\3\2\2\2gf\3\2"+
		"\2\2h\t\3\2\2\2ij\5\34\17\2jk\5\n\6\2kn\3\2\2\2ln\3\2\2\2mi\3\2\2\2ml"+
		"\3\2\2\2n\13\3\2\2\2op\7\60\2\2pq\7\63\2\2qr\7\t\2\2rs\5\16\b\2st\7\7"+
		"\2\2t\r\3\2\2\2u\u0082\5\20\t\2vw\7\13\2\2wx\7\16\2\2xy\7\64\2\2yz\7\17"+
		"\2\2z{\7\n\2\2{\u0082\5\20\t\2|}\7\f\2\2}~\5\22\n\2~\177\7\r\2\2\177\u0082"+
		"\3\2\2\2\u0080\u0082\7\63\2\2\u0081u\3\2\2\2\u0081v\3\2\2\2\u0081|\3\2"+
		"\2\2\u0081\u0080\3\2\2\2\u0082\17\3\2\2\2\u0083\u0084\t\2\2\2\u0084\21"+
		"\3\2\2\2\u0085\u0086\7\63\2\2\u0086\u0087\7\20\2\2\u0087\u0088\5\20\t"+
		"\2\u0088\u0089\7\7\2\2\u0089\u008a\5\22\n\2\u008a\u008d\3\2\2\2\u008b"+
		"\u008d\3\2\2\2\u008c\u0085\3\2\2\2\u008c\u008b\3\2\2\2\u008d\23\3\2\2"+
		"\2\u008e\u008f\7\5\2\2\u008f\u0090\5\26\f\2\u0090\u0091\7\20\2\2\u0091"+
		"\u0092\5\16\b\2\u0092\u0093\5\32\16\2\u0093\u0094\7\7\2\2\u0094\25\3\2"+
		"\2\2\u0095\u0096\7\63\2\2\u0096\u0097\5\30\r\2\u0097\27\3\2\2\2\u0098"+
		"\u0099\7\b\2\2\u0099\u009a\7\63\2\2\u009a\u009d\5\30\r\2\u009b\u009d\3"+
		"\2\2\2\u009c\u0098\3\2\2\2\u009c\u009b\3\2\2\2\u009d\31\3\2\2\2\u009e"+
		"\u009f\7\21\2\2\u009f\u00a2\58\35\2\u00a0\u00a2\3\2\2\2\u00a1\u009e\3"+
		"\2\2\2\u00a1\u00a0\3\2\2\2\u00a2\33\3\2\2\2\u00a3\u00a4\7\22\2\2\u00a4"+
		"\u00a5\7\63\2\2\u00a5\u00a6\7\23\2\2\u00a6\u00a7\5\36\20\2\u00a7\u00a8"+
		"\7\24\2\2\u00a8\u00a9\5\"\22\2\u00a9\u00aa\7\25\2\2\u00aa\u00ab\5&\24"+
		"\2\u00ab\u00ac\7\r\2\2\u00ac\u00ad\7\7\2\2\u00ad\35\3\2\2\2\u00ae\u00af"+
		"\5$\23\2\u00af\u00b0\5 \21\2\u00b0\u00b3\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2"+
		"\u00ae\3\2\2\2\u00b2\u00b1\3\2\2\2\u00b3\37\3\2\2\2\u00b4\u00b5\7\b\2"+
		"\2\u00b5\u00b6\5$\23\2\u00b6\u00b7\5 \21\2\u00b7\u00ba\3\2\2\2\u00b8\u00ba"+
		"\3\2\2\2\u00b9\u00b4\3\2\2\2\u00b9\u00b8\3\2\2\2\u00ba!\3\2\2\2\u00bb"+
		"\u00be\5\16\b\2\u00bc\u00be\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00bc\3"+
		"\2\2\2\u00be#\3\2\2\2\u00bf\u00c0\7\63\2\2\u00c0\u00c1\7\20\2\2\u00c1"+
		"\u00c2\5\16\b\2\u00c2%\3\2\2\2\u00c3\u00c4\5*\26\2\u00c4\u00c5\5(\25\2"+
		"\u00c5\'\3\2\2\2\u00c6\u00c7\5*\26\2\u00c7\u00c8\5(\25\2\u00c8\u00cb\3"+
		"\2\2\2\u00c9\u00cb\3\2\2\2\u00ca\u00c6\3\2\2\2\u00ca\u00c9\3\2\2\2\u00cb"+
		")\3\2\2\2\u00cc\u00ed\5\60\31\2\u00cd\u00ed\5,\27\2\u00ce\u00cf\7\32\2"+
		"\2\u00cf\u00d0\5> \2\u00d0\u00d1\7\33\2\2\u00d1\u00d2\5&\24\2\u00d2\u00d3"+
		"\7\34\2\2\u00d3\u00d4\7\7\2\2\u00d4\u00ed\3\2\2\2\u00d5\u00d6\7\35\2\2"+
		"\u00d6\u00d7\7\63\2\2\u00d7\u00d8\7\21\2\2\u00d8\u00d9\5> \2\u00d9\u00da"+
		"\7\36\2\2\u00da\u00db\5> \2\u00db\u00dc\7\33\2\2\u00dc\u00dd\5&\24\2\u00dd"+
		"\u00de\7\34\2\2\u00de\u00df\7\7\2\2\u00df\u00ed\3\2\2\2\u00e0\u00e1\7"+
		"\37\2\2\u00e1\u00ed\7\7\2\2\u00e2\u00e3\7 \2\2\u00e3\u00e4\5> \2\u00e4"+
		"\u00e5\7\7\2\2\u00e5\u00ed\3\2\2\2\u00e6\u00e7\7!\2\2\u00e7\u00e8\5\4"+
		"\3\2\u00e8\u00e9\7\"\2\2\u00e9\u00ea\5&\24\2\u00ea\u00eb\7\r\2\2\u00eb"+
		"\u00ed\3\2\2\2\u00ec\u00cc\3\2\2\2\u00ec\u00cd\3\2\2\2\u00ec\u00ce\3\2"+
		"\2\2\u00ec\u00d5\3\2\2\2\u00ec\u00e0\3\2\2\2\u00ec\u00e2\3\2\2\2\u00ec"+
		"\u00e6\3\2\2\2\u00ed+\3\2\2\2\u00ee\u00ef\7\26\2\2\u00ef\u00f0\5> \2\u00f0"+
		"\u00f1\7\30\2\2\u00f1\u00f2\5&\24\2\u00f2\u00f3\5.\30\2\u00f3-\3\2\2\2"+
		"\u00f4\u00f5\7\27\2\2\u00f5\u00fc\7\7\2\2\u00f6\u00f7\7\31\2\2\u00f7\u00f8"+
		"\5&\24\2\u00f8\u00f9\7\27\2\2\u00f9\u00fa\7\7\2\2\u00fa\u00fc\3\2\2\2"+
		"\u00fb\u00f4\3\2\2\2\u00fb\u00f6\3\2\2\2\u00fc/\3\2\2\2\u00fd\u00fe\7"+
		"\63\2\2\u00fe\u00ff\5\62\32\2\u00ff\61\3\2\2\2\u0100\u0101\5\66\34\2\u0101"+
		"\u0102\5\64\33\2\u0102\u0103\5> \2\u0103\u0104\7\7\2\2\u0104\u010b\3\2"+
		"\2\2\u0105\u0106\7\23\2\2\u0106\u0107\5L\'\2\u0107\u0108\7\24\2\2\u0108"+
		"\u0109\7\7\2\2\u0109\u010b\3\2\2\2\u010a\u0100\3\2\2\2\u010a\u0105\3\2"+
		"\2\2\u010b\63\3\2\2\2\u010c\u010d\7\63\2\2\u010d\u010e\5\66\34\2\u010e"+
		"\u010f\5\64\33\2\u010f\u0112\3\2\2\2\u0110\u0112\3\2\2\2\u0111\u010c\3"+
		"\2\2\2\u0111\u0110\3\2\2\2\u0112\65\3\2\2\2\u0113\u0114\7\16\2\2\u0114"+
		"\u0115\5> \2\u0115\u0116\7\17\2\2\u0116\u0117\7\21\2\2\u0117\u011d\3\2"+
		"\2\2\u0118\u0119\7#\2\2\u0119\u011a\7\63\2\2\u011a\u011d\7\21\2\2\u011b"+
		"\u011d\7\21\2\2\u011c\u0113\3\2\2\2\u011c\u0118\3\2\2\2\u011c\u011b\3"+
		"\2\2\2\u011d\67\3\2\2\2\u011e\u011f\5:\36\2\u011f\u0120\5<\37\2\u0120"+
		"9\3\2\2\2\u0121\u0125\7%\2\2\u0122\u0125\7&\2\2\u0123\u0125\3\2\2\2\u0124"+
		"\u0121\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0123\3\2\2\2\u0125;\3\2\2\2"+
		"\u0126\u0127\t\3\2\2\u0127=\3\2\2\2\u0128\u012d\5@!\2\u0129\u012a\t\4"+
		"\2\2\u012a\u012c\5@!\2\u012b\u0129\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b"+
		"\3\2\2\2\u012d\u012e\3\2\2\2\u012e?\3\2\2\2\u012f\u012d\3\2\2\2\u0130"+
		"\u0133\5B\"\2\u0131\u0132\t\5\2\2\u0132\u0134\5B\"\2\u0133\u0131\3\2\2"+
		"\2\u0133\u0134\3\2\2\2\u0134A\3\2\2\2\u0135\u013a\5D#\2\u0136\u0137\t"+
		"\6\2\2\u0137\u0139\5D#\2\u0138\u0136\3\2\2\2\u0139\u013c\3\2\2\2\u013a"+
		"\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013bC\3\2\2\2\u013c\u013a\3\2\2\2"+
		"\u013d\u0142\5F$\2\u013e\u013f\t\7\2\2\u013f\u0141\5F$\2\u0140\u013e\3"+
		"\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143"+
		"E\3\2\2\2\u0144\u0142\3\2\2\2\u0145\u014a\5H%\2\u0146\u0147\7$\2\2\u0147"+
		"\u0149\5H%\2\u0148\u0146\3\2\2\2\u0149\u014c\3\2\2\2\u014a\u0148\3\2\2"+
		"\2\u014a\u014b\3\2\2\2\u014bG\3\2\2\2\u014c\u014a\3\2\2\2\u014d\u0155"+
		"\58\35\2\u014e\u014f\7\23\2\2\u014f\u0150\5> \2\u0150\u0151\7\24\2\2\u0151"+
		"\u0155\3\2\2\2\u0152\u0153\7\63\2\2\u0153\u0155\5J&\2\u0154\u014d\3\2"+
		"\2\2\u0154\u014e\3\2\2\2\u0154\u0152\3\2\2\2\u0155I\3\2\2\2\u0156\u0157"+
		"\7\23\2\2\u0157\u0158\5L\'\2\u0158\u0159\7\24\2\2\u0159\u0162\3\2\2\2"+
		"\u015a\u015b\7\16\2\2\u015b\u015c\5> \2\u015c\u015d\7\17\2\2\u015d\u0162"+
		"\3\2\2\2\u015e\u015f\7#\2\2\u015f\u0162\7\63\2\2\u0160\u0162\3\2\2\2\u0161"+
		"\u0156\3\2\2\2\u0161\u015a\3\2\2\2\u0161\u015e\3\2\2\2\u0161\u0160\3\2"+
		"\2\2\u0162K\3\2\2\2\u0163\u0164\5> \2\u0164\u0165\5N(\2\u0165\u0168\3"+
		"\2\2\2\u0166\u0168\3\2\2\2\u0167\u0163\3\2\2\2\u0167\u0166\3\2\2\2\u0168"+
		"M\3\2\2\2\u0169\u016a\7\b\2\2\u016a\u016b\5> \2\u016b\u016c\5N(\2\u016c"+
		"\u016f\3\2\2\2\u016d\u016f\3\2\2\2\u016e\u0169\3\2\2\2\u016e\u016d\3\2"+
		"\2\2\u016fO\3\2\2\2\34agm\u0081\u008c\u009c\u00a1\u00b2\u00b9\u00bd\u00ca"+
		"\u00ec\u00fb\u010a\u0111\u011c\u0124\u012d\u0133\u013a\u0142\u014a\u0154"+
		"\u0161\u0167\u016e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}