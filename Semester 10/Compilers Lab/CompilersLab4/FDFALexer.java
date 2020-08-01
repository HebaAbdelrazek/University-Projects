// Generated from FDFA.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FDFALexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		State0=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"State0"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "State0"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public FDFALexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FDFA.g4"; }

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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0:
			State0_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void State0_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			System.out.print("01");
			break;
		case 1:
			System.out.print("10");
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\3_\b\1\4\2\t\2\3"+
		"\2\7\2\7\n\2\f\2\16\2\n\13\2\3\2\6\2\r\n\2\r\2\16\2\16\3\2\3\2\6\2\23"+
		"\n\2\r\2\16\2\24\6\2\27\n\2\r\2\16\2\30\3\2\6\2\34\n\2\r\2\16\2\35\3\2"+
		"\7\2!\n\2\f\2\16\2$\13\2\3\2\6\2\'\n\2\r\2\16\2(\5\2+\n\2\3\2\3\2\7\2"+
		"/\n\2\f\2\16\2\62\13\2\3\2\6\2\65\n\2\r\2\16\2\66\3\2\3\2\6\2;\n\2\r\2"+
		"\16\2<\6\2?\n\2\r\2\16\2@\3\2\6\2D\n\2\r\2\16\2E\3\2\3\2\7\2J\n\2\f\2"+
		"\16\2M\13\2\3\2\6\2P\n\2\r\2\16\2Q\3\2\5\2U\n\2\3\2\3\2\6\2Y\n\2\r\2\16"+
		"\2Z\3\2\5\2^\n\2\2\2\3\3\3\3\2\2\2r\2\3\3\2\2\2\3]\3\2\2\2\5\7\7\62\2"+
		"\2\6\5\3\2\2\2\7\n\3\2\2\2\b\6\3\2\2\2\b\t\3\2\2\2\t\f\3\2\2\2\n\b\3\2"+
		"\2\2\13\r\7\63\2\2\f\13\3\2\2\2\r\16\3\2\2\2\16\f\3\2\2\2\16\17\3\2\2"+
		"\2\17\20\3\2\2\2\20\22\7\62\2\2\21\23\7\62\2\2\22\21\3\2\2\2\23\24\3\2"+
		"\2\2\24\22\3\2\2\2\24\25\3\2\2\2\25\27\3\2\2\2\26\b\3\2\2\2\27\30\3\2"+
		"\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\33\3\2\2\2\32\34\7\63\2\2\33\32\3"+
		"\2\2\2\34\35\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36+\3\2\2\2\37!\7\62"+
		"\2\2 \37\3\2\2\2!$\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#&\3\2\2\2$\"\3\2\2\2"+
		"%\'\7\63\2\2&%\3\2\2\2\'(\3\2\2\2(&\3\2\2\2()\3\2\2\2)+\3\2\2\2*\26\3"+
		"\2\2\2*\"\3\2\2\2+,\3\2\2\2,^\b\2\2\2-/\7\62\2\2.-\3\2\2\2/\62\3\2\2\2"+
		"\60.\3\2\2\2\60\61\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\63\65\7\63\2\2"+
		"\64\63\3\2\2\2\65\66\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\678\3\2\2\28"+
		":\7\62\2\29;\7\62\2\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2=?\3\2\2"+
		"\2>\60\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2BD\7\63\2\2CB\3"+
		"\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FG\3\2\2\2GU\7\62\2\2HJ\7\62\2\2I"+
		"H\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LO\3\2\2\2MK\3\2\2\2NP\7\63\2\2"+
		"ON\3\2\2\2PQ\3\2\2\2QO\3\2\2\2QR\3\2\2\2RS\3\2\2\2SU\7\62\2\2T>\3\2\2"+
		"\2TK\3\2\2\2UV\3\2\2\2V^\b\2\3\2WY\7\62\2\2XW\3\2\2\2YZ\3\2\2\2ZX\3\2"+
		"\2\2Z[\3\2\2\2[^\3\2\2\2\\^\7\f\2\2]*\3\2\2\2]T\3\2\2\2]X\3\2\2\2]\\\3"+
		"\2\2\2^\4\3\2\2\2\25\2\b\16\24\30\35\"(*\60\66<@EKQTZ]\4\3\2\2\3\2\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}