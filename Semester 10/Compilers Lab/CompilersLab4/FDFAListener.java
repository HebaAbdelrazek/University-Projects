// Generated from FDFA.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FDFAParser}.
 */
public interface FDFAListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FDFAParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(FDFAParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link FDFAParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(FDFAParser.ProgContext ctx);
}