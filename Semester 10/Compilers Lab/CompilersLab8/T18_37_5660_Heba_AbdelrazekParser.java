// Generated from T18_37_5660_Heba_Abdelrazek.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class T18_37_5660_Heba_AbdelrazekParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, WS=4;
	public static final int
		RULE_start = 0, RULE_operationS = 1, RULE_operationL = 2, RULE_operationR = 3, 
		RULE_operationB = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "operationS", "operationL", "operationR", "operationB"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'0'", "'1'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "WS"
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

	@Override
	public String getGrammarFileName() { return "T18_37_5660_Heba_Abdelrazek.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public T18_37_5660_Heba_AbdelrazekParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public OperationSContext operationS;
		public OperationSContext operationS() {
			return getRuleContext(OperationSContext.class,0);
		}
		public TerminalNode EOF() { return getToken(T18_37_5660_Heba_AbdelrazekParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof T18_37_5660_Heba_AbdelrazekListener ) ((T18_37_5660_Heba_AbdelrazekListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof T18_37_5660_Heba_AbdelrazekListener ) ((T18_37_5660_Heba_AbdelrazekListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			((StartContext)_localctx).operationS = operationS();
			setState(11);
			match(EOF);
			System.out.println(((StartContext)_localctx).operationS.val);
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

	public static class OperationSContext extends ParserRuleContext {
		public double val;
		public OperationLContext operationL;
		public OperationRContext operationR;
		public OperationLContext operationL() {
			return getRuleContext(OperationLContext.class,0);
		}
		public OperationRContext operationR() {
			return getRuleContext(OperationRContext.class,0);
		}
		public OperationSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof T18_37_5660_Heba_AbdelrazekListener ) ((T18_37_5660_Heba_AbdelrazekListener)listener).enterOperationS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof T18_37_5660_Heba_AbdelrazekListener ) ((T18_37_5660_Heba_AbdelrazekListener)listener).exitOperationS(this);
		}
	}

	public final OperationSContext operationS() throws RecognitionException {
		OperationSContext _localctx = new OperationSContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_operationS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			((OperationSContext)_localctx).operationL = operationL();
			setState(15);
			match(T__0);
			setState(16);
			((OperationSContext)_localctx).operationR = operationR();
			((OperationSContext)_localctx).val =  (((OperationSContext)_localctx).operationL.val + ((OperationSContext)_localctx).operationR.val);
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

	public static class OperationLContext extends ParserRuleContext {
		public double val;
		public int n;
		public OperationBContext operationB;
		public OperationLContext operationL1;
		public OperationBContext operationB() {
			return getRuleContext(OperationBContext.class,0);
		}
		public OperationLContext operationL() {
			return getRuleContext(OperationLContext.class,0);
		}
		public OperationLContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationL; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof T18_37_5660_Heba_AbdelrazekListener ) ((T18_37_5660_Heba_AbdelrazekListener)listener).enterOperationL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof T18_37_5660_Heba_AbdelrazekListener ) ((T18_37_5660_Heba_AbdelrazekListener)listener).exitOperationL(this);
		}
	}

	public final OperationLContext operationL() throws RecognitionException {
		OperationLContext _localctx = new OperationLContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_operationL);
		try {
			setState(28);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(19);
				((OperationLContext)_localctx).operationB = operationB();
				setState(20);
				((OperationLContext)_localctx).operationL1 = operationL();
				((OperationLContext)_localctx).n =  ((OperationLContext)_localctx).operationL1.n+1;
				((OperationLContext)_localctx).val = ((((OperationLContext)_localctx).operationB.val*Math.pow(2,_localctx.n))+((OperationLContext)_localctx).operationL1.val);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(24);
				((OperationLContext)_localctx).operationB = operationB();
				((OperationLContext)_localctx).n = 0;
				((OperationLContext)_localctx).val = ((OperationLContext)_localctx).operationB.val;
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

	public static class OperationRContext extends ParserRuleContext {
		public double val;
		public OperationBContext operationB;
		public OperationRContext operationR1;
		public OperationBContext operationB() {
			return getRuleContext(OperationBContext.class,0);
		}
		public OperationRContext operationR() {
			return getRuleContext(OperationRContext.class,0);
		}
		public OperationRContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationR; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof T18_37_5660_Heba_AbdelrazekListener ) ((T18_37_5660_Heba_AbdelrazekListener)listener).enterOperationR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof T18_37_5660_Heba_AbdelrazekListener ) ((T18_37_5660_Heba_AbdelrazekListener)listener).exitOperationR(this);
		}
	}

	public final OperationRContext operationR() throws RecognitionException {
		OperationRContext _localctx = new OperationRContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_operationR);
		try {
			setState(37);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				((OperationRContext)_localctx).operationB = operationB();
				setState(31);
				((OperationRContext)_localctx).operationR1 = operationR();
				((OperationRContext)_localctx).val = ((((OperationRContext)_localctx).operationR1.val*0.5)+(((OperationRContext)_localctx).operationB.val*0.5));
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				((OperationRContext)_localctx).operationB = operationB();
				((OperationRContext)_localctx).val = ((OperationRContext)_localctx).operationB.val*0.5;
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

	public static class OperationBContext extends ParserRuleContext {
		public int val;
		public OperationBContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationB; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof T18_37_5660_Heba_AbdelrazekListener ) ((T18_37_5660_Heba_AbdelrazekListener)listener).enterOperationB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof T18_37_5660_Heba_AbdelrazekListener ) ((T18_37_5660_Heba_AbdelrazekListener)listener).exitOperationB(this);
		}
	}

	public final OperationBContext operationB() throws RecognitionException {
		OperationBContext _localctx = new OperationBContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_operationB);
		try {
			setState(43);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				match(T__1);
				((OperationBContext)_localctx).val = 0;
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				match(T__2);
				((OperationBContext)_localctx).val = 1;
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\6\60\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\37\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\5\5(\n\5\3\6\3\6\3\6\3\6\5\6.\n\6\3\6\2\2\7\2\4\6\b\n\2\2\2-\2\f\3\2"+
		"\2\2\4\20\3\2\2\2\6\36\3\2\2\2\b\'\3\2\2\2\n-\3\2\2\2\f\r\5\4\3\2\r\16"+
		"\7\2\2\3\16\17\b\2\1\2\17\3\3\2\2\2\20\21\5\6\4\2\21\22\7\3\2\2\22\23"+
		"\5\b\5\2\23\24\b\3\1\2\24\5\3\2\2\2\25\26\5\n\6\2\26\27\5\6\4\2\27\30"+
		"\b\4\1\2\30\31\b\4\1\2\31\37\3\2\2\2\32\33\5\n\6\2\33\34\b\4\1\2\34\35"+
		"\b\4\1\2\35\37\3\2\2\2\36\25\3\2\2\2\36\32\3\2\2\2\37\7\3\2\2\2 !\5\n"+
		"\6\2!\"\5\b\5\2\"#\b\5\1\2#(\3\2\2\2$%\5\n\6\2%&\b\5\1\2&(\3\2\2\2\' "+
		"\3\2\2\2\'$\3\2\2\2(\t\3\2\2\2)*\7\4\2\2*.\b\6\1\2+,\7\5\2\2,.\b\6\1\2"+
		"-)\3\2\2\2-+\3\2\2\2.\13\3\2\2\2\5\36\'-";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}