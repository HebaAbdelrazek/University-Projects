// Generated from FDFA2.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FDFA2Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		State0=1, State1=2, State2=3, State3=4;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"State0", "State1", "State2", "State3"
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
			null, "State0", "State1", "State2", "State3"
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


	public FDFA2Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FDFA2.g4"; }

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
		case 1:
			State1_action((RuleContext)_localctx, actionIndex);
			break;
		case 2:
			State2_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void State1_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			System.out.print("01");
			break;
		}
	}
	private void State2_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			System.out.print("10");
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\6/\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\3\2\6\2\r\n\2\r\2\16\2\16\3\2\3\2\3\2\5\2\24\n"+
		"\2\3\3\6\3\27\n\3\r\3\16\3\30\3\3\3\3\3\3\5\3\36\n\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\5\4&\n\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5.\n\5\2\2\6\3\3\5\4\7\5\t\6"+
		"\3\2\2\2\64\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\3\23\3\2\2"+
		"\2\5\35\3\2\2\2\7%\3\2\2\2\t-\3\2\2\2\13\r\7\62\2\2\f\13\3\2\2\2\r\16"+
		"\3\2\2\2\16\f\3\2\2\2\16\17\3\2\2\2\17\20\3\2\2\2\20\24\5\3\2\2\21\22"+
		"\7\63\2\2\22\24\5\5\3\2\23\f\3\2\2\2\23\21\3\2\2\2\24\4\3\2\2\2\25\27"+
		"\7\63\2\2\26\25\3\2\2\2\27\30\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\32"+
		"\3\2\2\2\32\36\5\5\3\2\33\34\7\62\2\2\34\36\5\7\4\2\35\26\3\2\2\2\35\33"+
		"\3\2\2\2\36\37\3\2\2\2\37 \b\3\2\2 \6\3\2\2\2!\"\7\62\2\2\"&\5\3\2\2#"+
		"$\7\63\2\2$&\5\t\5\2%!\3\2\2\2%#\3\2\2\2&\'\3\2\2\2\'(\b\4\3\2(\b\3\2"+
		"\2\2)*\7\62\2\2*.\5\t\5\2+,\7\63\2\2,.\5\t\5\2-)\3\2\2\2-+\3\2\2\2.\n"+
		"\3\2\2\2\t\2\16\23\30\35%-\4\3\3\2\3\4\3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}