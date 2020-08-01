import java.util.ArrayList;
import java.util.Arrays;

public class T18_37_5660_Heba_Abdelrazek {

	static ArrayList<ArrayList<String>> grammar;
	static int oldGrammarSize = 0;
	
	public static String LRE(String input) {
		String output = "";
		
//		Extract all rules to an array of rules
		String [] rules = input.split(";"); 
		oldGrammarSize = rules.length;
		
//		System.out.print("Grammar Rules: ");
//		toString(rules);
//		System.out.println("");
		
		grammar = new ArrayList<ArrayList<String>>();
		
//		Extract each rule to a an ArrayList of its own
		for(int k = 0; k < oldGrammarSize; k++) { 
			ArrayList<String> rule = new ArrayList<String>(Arrays.asList(rules[k].split(",")));
			
//			System.out.print("Rule " + (k+1) +" ArrayList: ");
//			toString2(rule);
//			System.out.println(" ");
			
			grammar.add(rule);
		}
		
//		System.out.println("");
//		System.out.println("Original Grammar ArrayList of ArrayLists: ");
//		toString3(grammar);
		
		for(int i = 0; i < oldGrammarSize; i++) {
			
//			i is the index for the Variables, here in example input #1, i = 0 currentRule is S, i = 1 is T, i = 2 is L
//			Loop on each rule replace values with the previous rules
			ArrayList<String> currentRule = grammar.get(i);
			for(int j = 0; j < i; j++) {
				
				ArrayList<String> tempArray = new ArrayList<String>();
				tempArray.add(currentRule.get(0));
				
				for(int n = 1; n < currentRule.size(); n++) { 
					
//					Check if this rule has left variables heads of previous rules
					if( currentRule.get(n).substring(0,1).equals( grammar.get(j).get(0)))
					{ 
//						If yes, substitute this Variable with all the other rules and put them in a tempArray which will replace the current rule	
						for(int b = 1; b < grammar.get(j).size(); b++) 
						{
							if(currentRule.get(n).length() > 1) 
							{ 	
								tempArray.add(grammar.get(j).get(b).concat(currentRule.get(n).substring(1))); // L goes to TS'dL and TS'
							}
							else 
							{
								if(currentRule.get(n).length() == 1)
								{ 
									tempArray.add(grammar.get(j).get(b));
								}
							}	
						}
					}
					else {
						tempArray.add(currentRule.get(n));
					}
				}
				currentRule = tempArray;
				grammar.remove(i);
				grammar.add(i, tempArray);	
			}
//			After substitutions for the rule at i, eliminate direct left recursions if exists
			directElimination(currentRule, i);	
		}
//		Now Grammar is fully updated
//		System.out.println("");
//		System.out.println("Final Grammar ArrayList of ArrayLists: ");
//		toString3(grammar);
		
//		Convert grammar to output string
		ArrayList<String> tempOutputArray = new ArrayList<String>();
		for(int p = 0; p < grammar.size(); p++) {	
			String tempOutput = String.join(",", grammar.get(p));
			tempOutputArray.add(tempOutput);	
		}
		
		output = String.join(";",tempOutputArray );
		return output;	
	}
	
	
	
	
	public static void directElimination(ArrayList<String> rule, int indexOfRule) {
		
//		Check if this rule has direct left recursion if yes remove it from grammar and add the new 2 rules
		boolean leftRecursive = false;
		String head = rule.get(0); 		//head variable of this rule
		
		for(int i = 1; i< rule.size(); i++) {
			if(head.equals(rule.get(i).substring(0, 1))) {
				leftRecursive = true;
			}
		}
		
		if(!leftRecursive) { 
//			If it's not left recursive, don't change anything leave it in grammar  
			return;
		}
		
		else { 
//			Generate 2 rules, remove the old rule, add these to the grammar 
			ArrayList<String> newRule1 =new ArrayList<String>(); 
			ArrayList<String> newRule2 =new ArrayList<String>(); 
			newRule1.add(head);
			String newHead = head.concat("'");
			newRule2.add(newHead);
			
			for(int m = 1; m < rule.size(); m++) {
				if(head.equals(rule.get(m).substring(0, 1))) {
					newRule2.add((rule.get(m).substring(1).concat(newHead)));
				}
				else {
					newRule1.add(rule.get(m).concat(newHead));
				}
			}
			newRule2.add("");
			
//			toString2(newRule1);
//			System.out.println("");
//			toString2(newRule2);
			
			grammar.remove(rule);
			grammar.add(indexOfRule, newRule1);
			grammar.add(newRule2);
			
//			System.out.println("");
//			System.out.println("Grammar ArrayList of ArrayLists after: ");
//			toString3(grammar);
		}
	}  
	
	
	
	
	public static void toString3(ArrayList<ArrayList<String>> array) { 
//		Print arraylist of arraylists of grammar
		System.out.print("[ ");
		for(int i = 0; i< array.size(); i++) {
			toString2(array.get(i));
			
			if(i!=array.size()-1)
				System.out.print(" , ");
		}
		System.out.println(" ]");
	}
	
	
	
	public static void toString2(ArrayList<String> array) { 
//		Print rule arraylist
		System.out.print("[ '");
		for(int i = 0; i< array.size(); i++) {
			System.out.print(array.get(i));
			if(i!=array.size()-1)
				System.out.print("' , '");
		}
		System.out.print("' ]");
	}
	
	
	
	public static void toString(String [] array) { 
//		Print rules array []
		System.out.print("[ '");
		for(int i = 0; i< array.length; i++) {
			System.out.print(array[i]);
			if(i!=array.length-1)
				System.out.print("' , '");
		}
		System.out.println("' ]");
	}
	
	
	public static void main(String[] args) { 
//		Those are the input strings for testing provided by TA Nardeen on 37-MET
//		Epsilon is handled as "" it shows in as a comma like your test results

	    String input = "S,ScT,T;T,aSb,iaLb,i;L,SdL,S"; //1
//	    String input = "S,Sa,b";  					   //2
//	    String input = "S,Sab,cd";					   //3
//	    String input = "S,SuS,SS,Ss,lSr,a";			   //4
//	    String input = "S,SuT,T;T,TF,F;F,Fs,P;P,a,b";  //5
//	    String input = "S,z,To;T,o,Sz";				   //6
//	    String input = "S,lLr,a;L,LbS,S";			   //7
//	    String input = "S,BC,C;B,Bb,b;C,SC,a";		   //8

	    String output = LRE(input);
	    System.out.print(output);
	    
	}
}
