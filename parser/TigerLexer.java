// Generated from Tiger.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TigerLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "COMMENT", "VAR", "MAIN", "SEMI", "COMMA", "EQUALS", "OF", "ARRAY", 
		"RECORD", "END", "LBRACKET", "RBRACKET", "COLON", "ASSIGN", "FUNCTION", 
		"LPARENS", "RPARENS", "BEGIN", "IF", "ENDIF", "THEN", "ELSE", "WHILE", 
		"DO", "ENDDO", "FOR", "TO", "BREAK", "RETURN", "LET", "IN", "DOT", "POWER", 
		"PLUS", "MINUS", "MULT", "DIV", "NEQ", "LESS", "GREATER", "LTEQ", "GTEQ", 
		"AND", "OR", "TYPE", "INT_KW", "FLOAT_KW", "ID", "INT", "FLOAT"
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


	public TigerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Tiger.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\65\u013e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\3\2\6\2k\n\2\r\2\16\2l\3\2\3\2\3\3\3\3\3\3\3\3\7\3u\n\3\f\3\16\3"+
		"x\13\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3"+
		"\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3"+
		"\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3#\3$\3$\3"+
		"%\3%\3&\3&\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3.\3."+
		"\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\62"+
		"\3\62\7\62\u012a\n\62\f\62\16\62\u012d\13\62\3\63\6\63\u0130\n\63\r\63"+
		"\16\63\u0131\3\64\6\64\u0135\n\64\r\64\16\64\u0136\3\64\3\64\6\64\u013b"+
		"\n\64\r\64\16\64\u013c\2\2\65\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61"+
		"a\62c\63e\64g\65\3\2\6\4\2\13\f\"\"\7\2\13\f\"\"\62;C\\c|\4\2C\\c|\6\2"+
		"\62;C\\aac|\2\u0143\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2"+
		"\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q"+
		"\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2"+
		"\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\3j\3\2\2\2"+
		"\5p\3\2\2\2\7~\3\2\2\2\t\u0082\3\2\2\2\13\u0087\3\2\2\2\r\u0089\3\2\2"+
		"\2\17\u008b\3\2\2\2\21\u008d\3\2\2\2\23\u0090\3\2\2\2\25\u0096\3\2\2\2"+
		"\27\u009d\3\2\2\2\31\u00a1\3\2\2\2\33\u00a3\3\2\2\2\35\u00a5\3\2\2\2\37"+
		"\u00a7\3\2\2\2!\u00aa\3\2\2\2#\u00b3\3\2\2\2%\u00b5\3\2\2\2\'\u00b7\3"+
		"\2\2\2)\u00bd\3\2\2\2+\u00c0\3\2\2\2-\u00c6\3\2\2\2/\u00cb\3\2\2\2\61"+
		"\u00d0\3\2\2\2\63\u00d6\3\2\2\2\65\u00d9\3\2\2\2\67\u00df\3\2\2\29\u00e3"+
		"\3\2\2\2;\u00e6\3\2\2\2=\u00ec\3\2\2\2?\u00f3\3\2\2\2A\u00f7\3\2\2\2C"+
		"\u00fa\3\2\2\2E\u00fc\3\2\2\2G\u00ff\3\2\2\2I\u0101\3\2\2\2K\u0103\3\2"+
		"\2\2M\u0105\3\2\2\2O\u0107\3\2\2\2Q\u010a\3\2\2\2S\u010c\3\2\2\2U\u010e"+
		"\3\2\2\2W\u0111\3\2\2\2Y\u0114\3\2\2\2[\u0116\3\2\2\2]\u0118\3\2\2\2_"+
		"\u011d\3\2\2\2a\u0121\3\2\2\2c\u0127\3\2\2\2e\u012f\3\2\2\2g\u0134\3\2"+
		"\2\2ik\t\2\2\2ji\3\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2\2\2mn\3\2\2\2no\b\2"+
		"\2\2o\4\3\2\2\2pq\7\61\2\2qr\7,\2\2rv\3\2\2\2su\t\3\2\2ts\3\2\2\2ux\3"+
		"\2\2\2vt\3\2\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7,\2\2z{\7\61\2\2{|\3"+
		"\2\2\2|}\b\3\2\2}\6\3\2\2\2~\177\7x\2\2\177\u0080\7c\2\2\u0080\u0081\7"+
		"t\2\2\u0081\b\3\2\2\2\u0082\u0083\7o\2\2\u0083\u0084\7c\2\2\u0084\u0085"+
		"\7k\2\2\u0085\u0086\7p\2\2\u0086\n\3\2\2\2\u0087\u0088\7=\2\2\u0088\f"+
		"\3\2\2\2\u0089\u008a\7.\2\2\u008a\16\3\2\2\2\u008b\u008c\7?\2\2\u008c"+
		"\20\3\2\2\2\u008d\u008e\7q\2\2\u008e\u008f\7h\2\2\u008f\22\3\2\2\2\u0090"+
		"\u0091\7c\2\2\u0091\u0092\7t\2\2\u0092\u0093\7t\2\2\u0093\u0094\7c\2\2"+
		"\u0094\u0095\7{\2\2\u0095\24\3\2\2\2\u0096\u0097\7t\2\2\u0097\u0098\7"+
		"g\2\2\u0098\u0099\7e\2\2\u0099\u009a\7q\2\2\u009a\u009b\7t\2\2\u009b\u009c"+
		"\7f\2\2\u009c\26\3\2\2\2\u009d\u009e\7g\2\2\u009e\u009f\7p\2\2\u009f\u00a0"+
		"\7f\2\2\u00a0\30\3\2\2\2\u00a1\u00a2\7]\2\2\u00a2\32\3\2\2\2\u00a3\u00a4"+
		"\7_\2\2\u00a4\34\3\2\2\2\u00a5\u00a6\7<\2\2\u00a6\36\3\2\2\2\u00a7\u00a8"+
		"\7<\2\2\u00a8\u00a9\7?\2\2\u00a9 \3\2\2\2\u00aa\u00ab\7h\2\2\u00ab\u00ac"+
		"\7w\2\2\u00ac\u00ad\7p\2\2\u00ad\u00ae\7e\2\2\u00ae\u00af\7v\2\2\u00af"+
		"\u00b0\7k\2\2\u00b0\u00b1\7q\2\2\u00b1\u00b2\7p\2\2\u00b2\"\3\2\2\2\u00b3"+
		"\u00b4\7*\2\2\u00b4$\3\2\2\2\u00b5\u00b6\7+\2\2\u00b6&\3\2\2\2\u00b7\u00b8"+
		"\7d\2\2\u00b8\u00b9\7g\2\2\u00b9\u00ba\7i\2\2\u00ba\u00bb\7k\2\2\u00bb"+
		"\u00bc\7p\2\2\u00bc(\3\2\2\2\u00bd\u00be\7k\2\2\u00be\u00bf\7h\2\2\u00bf"+
		"*\3\2\2\2\u00c0\u00c1\7g\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\7f\2\2\u00c3"+
		"\u00c4\7k\2\2\u00c4\u00c5\7h\2\2\u00c5,\3\2\2\2\u00c6\u00c7\7v\2\2\u00c7"+
		"\u00c8\7j\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7p\2\2\u00ca.\3\2\2\2\u00cb"+
		"\u00cc\7g\2\2\u00cc\u00cd\7n\2\2\u00cd\u00ce\7u\2\2\u00ce\u00cf\7g\2\2"+
		"\u00cf\60\3\2\2\2\u00d0\u00d1\7y\2\2\u00d1\u00d2\7j\2\2\u00d2\u00d3\7"+
		"k\2\2\u00d3\u00d4\7n\2\2\u00d4\u00d5\7g\2\2\u00d5\62\3\2\2\2\u00d6\u00d7"+
		"\7f\2\2\u00d7\u00d8\7q\2\2\u00d8\64\3\2\2\2\u00d9\u00da\7g\2\2\u00da\u00db"+
		"\7p\2\2\u00db\u00dc\7f\2\2\u00dc\u00dd\7f\2\2\u00dd\u00de\7q\2\2\u00de"+
		"\66\3\2\2\2\u00df\u00e0\7h\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e2\7t\2\2\u00e2"+
		"8\3\2\2\2\u00e3\u00e4\7v\2\2\u00e4\u00e5\7q\2\2\u00e5:\3\2\2\2\u00e6\u00e7"+
		"\7d\2\2\u00e7\u00e8\7t\2\2\u00e8\u00e9\7g\2\2\u00e9\u00ea\7c\2\2\u00ea"+
		"\u00eb\7m\2\2\u00eb<\3\2\2\2\u00ec\u00ed\7t\2\2\u00ed\u00ee\7g\2\2\u00ee"+
		"\u00ef\7v\2\2\u00ef\u00f0\7w\2\2\u00f0\u00f1\7t\2\2\u00f1\u00f2\7p\2\2"+
		"\u00f2>\3\2\2\2\u00f3\u00f4\7n\2\2\u00f4\u00f5\7g\2\2\u00f5\u00f6\7v\2"+
		"\2\u00f6@\3\2\2\2\u00f7\u00f8\7k\2\2\u00f8\u00f9\7p\2\2\u00f9B\3\2\2\2"+
		"\u00fa\u00fb\7\60\2\2\u00fbD\3\2\2\2\u00fc\u00fd\7,\2\2\u00fd\u00fe\7"+
		",\2\2\u00feF\3\2\2\2\u00ff\u0100\7-\2\2\u0100H\3\2\2\2\u0101\u0102\7/"+
		"\2\2\u0102J\3\2\2\2\u0103\u0104\7,\2\2\u0104L\3\2\2\2\u0105\u0106\7\61"+
		"\2\2\u0106N\3\2\2\2\u0107\u0108\7>\2\2\u0108\u0109\7@\2\2\u0109P\3\2\2"+
		"\2\u010a\u010b\7>\2\2\u010bR\3\2\2\2\u010c\u010d\7@\2\2\u010dT\3\2\2\2"+
		"\u010e\u010f\7>\2\2\u010f\u0110\7?\2\2\u0110V\3\2\2\2\u0111\u0112\7@\2"+
		"\2\u0112\u0113\7?\2\2\u0113X\3\2\2\2\u0114\u0115\7(\2\2\u0115Z\3\2\2\2"+
		"\u0116\u0117\7~\2\2\u0117\\\3\2\2\2\u0118\u0119\7v\2\2\u0119\u011a\7{"+
		"\2\2\u011a\u011b\7r\2\2\u011b\u011c\7g\2\2\u011c^\3\2\2\2\u011d\u011e"+
		"\7k\2\2\u011e\u011f\7p\2\2\u011f\u0120\7v\2\2\u0120`\3\2\2\2\u0121\u0122"+
		"\7h\2\2\u0122\u0123\7n\2\2\u0123\u0124\7q\2\2\u0124\u0125\7c\2\2\u0125"+
		"\u0126\7v\2\2\u0126b\3\2\2\2\u0127\u012b\t\4\2\2\u0128\u012a\t\5\2\2\u0129"+
		"\u0128\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b\u012c\3\2"+
		"\2\2\u012cd\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u0130\4\62;\2\u012f\u012e"+
		"\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132"+
		"f\3\2\2\2\u0133\u0135\4\62;\2\u0134\u0133\3\2\2\2\u0135\u0136\3\2\2\2"+
		"\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u013a"+
		"\7\60\2\2\u0139\u013b\4\62;\2\u013a\u0139\3\2\2\2\u013b\u013c\3\2\2\2"+
		"\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013dh\3\2\2\2\t\2lv\u012b\u0131"+
		"\u0136\u013c\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}