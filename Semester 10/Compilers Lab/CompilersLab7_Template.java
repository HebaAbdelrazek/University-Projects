
public class T18_37_5660_Heba_Abdelrazek {

	/*
	 * Please update the file/class name, and the following comment
	 */

	// T18_37_5660_Heba_Abdelrazek

	static class CFG {
		String grammar;

		/**
		 * Creates an instance of the CFG class. This should parse a string
		 * representation of the grammar and set your internal CFG attributes
		 *
		 * @param grammar A string representation of a CFG
		 */
		public CFG(String grammar) {
			this.grammar = grammar;
		}

		/**
		 * Generates the parsing table for this context free grammar. This should set
		 * your internal parsing table attributes
		 *
		 * @return A string representation of the parsing table
		 */
		public String table() {
			return null;
		}

		/**
		 * Parses the input string using the parsing table
		 *
		 * @param s The string to parse using the parsing table
		 * @return A string representation of a left most derivation
		 */
		public String parse(String s) {
			return null;
		}
	}

	public static void main(String[] args) {

		/*
		 * Please make sure that this EXACT code works. This means that the method
		 * and class names are case sensitive
		 */

		String grammar = "S,iST,e;T,cS,a";
		String input1 = "iiac";
		String input2 = "iia";
		CFG g = new CFG(grammar);
		System.out.println(g.table());
		System.out.println(g.parse(input1));
		System.out.println(g.parse(input2));
	}

}
