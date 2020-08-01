import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

public class T18_37_5660_Heba_Abdelrazek {

	/*
	 * Please update the file/class name, and the following comment
	 */

	// T18_37_5660_Heba_Abdelrazek

	static class CFG {
		String grammar;
		
		static ArrayList<ArrayList<String>> grammarArray;
		static int oldGrammarSize = 0;
		static ArrayList<ArrayList<String>> firstArray;
		static ArrayList<ArrayList<String>> followArray;
		static ArrayList<ArrayList<String>> tableArray;
		static ArrayList<String> terminalArray;
		static String firstString;
		static String followString;

		/**
		 * Creates an instance of the CFG class. This should parse a string
		 * representation of the grammar and set your internal CFG attributes
		 * 
		 * @param grammar A string representation of a CFG
		 */
		public CFG(String grammar) {
			this.grammar = grammar;
			constructCFG(grammar);
		}

		public static void constructCFG(String input) {	
//			Extract all rules to an array of rules
			String [] rules = input.split(";"); 
			oldGrammarSize = rules.length;
			grammarArray = new ArrayList<ArrayList<String>>();
			
//			Extract each rule to a an ArrayList of its own
			for(int k = 0; k < oldGrammarSize; k++) { 
				ArrayList<String> rule = new ArrayList<String>(Arrays.asList(rules[k].split(",")));
				grammarArray.add(rule);
			}
//			System.out.println("");
//			System.out.println("Grammar ArrayList of ArrayLists: ");
//			toString3(grammarArray);
//			System.out.println("");
			
			terminalArray = new ArrayList<String>();
			for(int i = 0; i < input.length(); i++) {
				if(Character.isLowerCase(input.charAt(i))) {
					terminalArray.add(input.substring(i,i+1));
				}	
			}
			Collections.sort(terminalArray);
			terminalArray = (ArrayList<String>) terminalArray.stream().distinct().collect(Collectors.toList());
			terminalArray.add("$");
			terminalArray.remove("e");
			
		}
		
		/**
		 * Generates the parsing table for this context free grammar. This should set
		 * your internal parsing table attributes
		 * 
		 * @return A string representation of the parsing table
		 */
		public String table() {
			First(); 
			Follow();
			tableArray = new ArrayList<ArrayList<String>>();
	
			for(int i = 0 ; i < grammarArray.size() ; i++) {
				ArrayList<String> currentRule = grammarArray.get(i);// currentRule = [ 'S' , 'iST' , 'e' ]
				String variable = currentRule.get(0);
				for(int j = 1 ; j < currentRule.size() ; j++) { //loop on current rule
					String alpha  = currentRule.get(j); 	//alpha = 'iST' 
					//loop on all terminals and $
					for(int k = 0; k < terminalArray.size(); k++) {
						String terminal = terminalArray.get(k);
						//first array of the currentRule here of S
						if( (totalFirst(alpha).contains(terminal)) || ((followArray.get(i).contains(terminal)) && (totalFirst(alpha).contains("e"))) ) 
						{
							ArrayList<String> entry = new ArrayList<String>();
							entry.add(variable);
							entry.add(terminal);
							entry.add(alpha);
							tableArray.add(entry);		
						}
					}
				}			
			}
			//turn the tableArray to desired table String
			ArrayList<String> temp = new ArrayList<String>();
			for(int m = 0; m < tableArray.size(); m++) {	
					String entryString = String.join(",", tableArray.get(m));
					temp.add(entryString);
			}
			String tempString = String.join(";", temp);
//			toString3(tableArray);
			return tempString;
		}

		/**
		 * Parses the input string using the parsing table
		 * 
		 * @param s The string to parse using the parsing table
		 * @return A string representation of a left most derivation
		 */
		public String parse(String s) {
		
			ArrayList<String> parseArray = new ArrayList<String>();
			parseArray.add("S");
			Stack<String> stack = new Stack<String>();
			stack.push("$");
			s = s.concat("$");
			stack.push("S");
			String top = stack.peek();
			String head = s.substring(0,1);
			String found = "";
			String derivation = "";
			
			while(!top.equals("$")) { 
				
				if(top.equals(head)) {
					stack.pop();
					found = found.concat(head);
					s = s.substring(1);
					head = s.substring(0,1);
				}
				else if(terminalArray.contains(top)) {
					parseArray.add("ERROR");
					break;
					//error
				}
				else if(queryTable(top,head).length()==0) {
					parseArray.add("ERROR");
					break;
					//error
				}
				else
				{
					//Do a derivation and print a step
					String newRule = queryTable(top,head);
					stack.pop();
					if(!newRule.equals("e")) 
					{
						for(int i = newRule.length()-1; i >= 0  ;i--) {
							stack.push(newRule.substring(i,i+1));
						}
//						parseArray.add(newRule);
					}
					Stack<String> tempstack = (Stack<String>) stack.clone();
					String tempString = "";
					while(!tempstack.peek().equals("$")){
						tempString = tempString.concat(tempstack.peek());
//						System.out.print(tempstack.peek());
						tempstack.pop();
					}
//					System.out.println(" ");
					derivation = found + tempString;
					parseArray.add(derivation);
//					System.out.println(derivation);
				}	
				top = stack.peek();				
			}
//			toString2(parseArray);
			return String.join(",", parseArray);
		}
		
		public String queryTable(String variable, String terminal) {
			String result = "";
			for(int i = 0; i < tableArray.size(); i++) {
					if(tableArray.get(i).get(0).equals(variable) && tableArray.get(i).get(1).equals(terminal))
					{
						result = tableArray.get(i).get(2);
					}
			}
			return result;
		}
		
		
		
		public static void toString3(ArrayList<ArrayList<String>> array) { 
			System.out.print("[ ");
			for(int i = 0; i< array.size(); i++) {
				toString2(array.get(i));
				if(i!=array.size()-1)
					System.out.print(" , ");
			}
			System.out.println(" ]");
		}
		
		public static void toString2(ArrayList<String> array) { 
			System.out.print("[ '");
			for(int i = 0; i< array.size(); i++) {
				System.out.print(array.get(i));
				if(i!=array.size()-1)
					System.out.print("' , '");
			}
			System.out.print("' ]");
		}
		
		public static void toString(String [] array) { 
			System.out.print("[ '");
			for(int i = 0; i< array.length; i++) {
				System.out.print(array[i]);
				if(i!=array.length-1)
					System.out.print("' , '");
			}
			System.out.println("' ]");
		}
		
		
		
		public String First() {
			firstArray = new ArrayList<ArrayList<String>>();
			String firstString = ""; 
			
			for(int m = 0; m < grammarArray.size(); m++) {
				ArrayList<String> empty = new ArrayList<String>();
				firstArray.add(empty);
			} 
			boolean change = true;
			while(change) {
				change = false;
				//Loop on grammar rules
				for(int i = 0 ; i < grammarArray.size() ; i++) {
					ArrayList<String> currentRule = grammarArray.get(i);	 // currentRule = [ 'S' , 'ScT' , 'T' ]
				
					for(int j = 1 ; j < currentRule.size() ; j++) { //loop on current rule
						String element  = currentRule.get(j); 	//element = 'ScT' 
						
						//Check Epsilon
						boolean epsilonFound = true;
						for(int g = 0 ; g < element.length() && epsilonFound ; g++) {
							
							if(!(checkFirst(element.substring(g,g+1)).contains("e"))) {
								epsilonFound = false;
							}
						}
						if(epsilonFound && !(checkFirst(currentRule.get(0)).contains("e") )) {
							firstArray.get(i).add("e");
							change = true;
						}
						
						for(int p = 0 ; p < element.length(); p++) 
						{ 	//element = "ScT"
							
							boolean epsilonFound2 = true;
							for(int f = 0 ; f < p && epsilonFound2 ; f++)
							{
								if(!(checkFirst(element.substring(f,f+1)).contains("e"))) {
									epsilonFound2 = false;
								}
							}
							
							if((p == 0) || epsilonFound2) 
							{
								ArrayList<String> tempArray = (ArrayList<String>) checkFirst(element.substring(p,p+1)).clone();
								tempArray.remove("e"); //First(Bi) − {ε}
								
								if(!checkFirst(currentRule.get(0)).containsAll(tempArray)) 
								{ 	
									ArrayList<String> newFirsts = new ArrayList<String>();
									newFirsts = checkFirst(currentRule.get(0));
									newFirsts.addAll(tempArray);
									firstArray.set(i, (ArrayList<String>) newFirsts.stream().distinct().collect(Collectors.toList()));
//									toString3(firstArray);
									change = true;
								}	
							}	
						}	
					}	
				}	
			}
			for(int n = 0 ; n < firstArray.size(); n++ ) {
				Collections.sort(firstArray.get(n));	
			}
//			toString3(firstArray);
			
//			Convert array to output string
			ArrayList<String> tempOutputArray = new ArrayList<String>();
			for(int p = 0; p < grammarArray.size(); p++) {	
				String tempOutput2 = String.join("", firstArray.get(p));
				String tempOutput1 = grammarArray.get(p).get(0);
				String tempOutput = tempOutput1.concat(","+tempOutput2);
				tempOutputArray.add(tempOutput);	
			}
			firstString = String.join(";",tempOutputArray );
			
			this.firstString = firstString;
			return firstString;
		}
		
		public static ArrayList<String> checkFirst(String element) {
			
			ArrayList<String> firstArrayTemp = new ArrayList<String>();
			
			if(element.toLowerCase().equals(element)) { //terminal
				firstArrayTemp.add(element);
				return firstArrayTemp;
			}
			else {
				int index;
				for(int i = 0 ; i< grammarArray.size() ; i++) {
					if(grammarArray.get(i).get(0).equals(element)) {
						index = i;
						firstArrayTemp = firstArray.get(index);	
						return firstArrayTemp;
					}
				}	
			}
			return firstArrayTemp;
			
		}
		
		public static ArrayList<String> checkFollow(String element) {
			
			ArrayList<String> followArrayTemp = new ArrayList<String>();
			
			if(element.toLowerCase().equals(element)) { //terminal
				followArrayTemp.add(element);
				return followArrayTemp;
			}
			else {
				int index;
				for(int i = 0 ; i< grammarArray.size() ; i++) {
					if(grammarArray.get(i).get(0).equals(element)) {
						index = i;
						followArrayTemp = followArray.get(index);	
						return followArrayTemp;
					}
				}	
			}
			return followArrayTemp;
			
		}	
		
		public String Follow() {
			String followOutput = ""; 
			
			followArray = new ArrayList<ArrayList<String>>(); 
			
			for(int m = 0; m < grammarArray.size(); m++) {
				ArrayList<String> empty = new ArrayList<String>();
				followArray.add(empty);
			}  	
			followArray.get(0).add("$"); //[ ['$'],[''],[''] ]
		
			boolean change = true;
			while(change) {
				change = false;
			
				for(int i = 0 ; i < grammarArray.size() ; i++) {
					ArrayList<String> currentRule = grammarArray.get(i);	 // currentRule = [ 'S' , 'ScT' , 'T' ]
				
					for(int j = 1 ; j < currentRule.size() ; j++) { //loop on current rule
						String element  = currentRule.get(j); // ScT
						
						for(int k = 0; k < element.length() - 1 ;k++) 
						{ 	//S c T , A−→αBβ
							if(Character.isUpperCase(element.charAt(k))) 
							{ 	//Variable
								
								String ourVariable = element.substring(k,k+1);
								String beta = element.substring(k+1);
							
								int index = 0;
								for(int z = 0 ; z< grammarArray.size() ; z++) {
									if(grammarArray.get(z).get(0).equals(ourVariable)) {
										index = z; //index of the variable in our grammar
									}
								}
								
								//Case 1: Variable followed by smth, add the first(smth) to it
								ArrayList<String> tempArray = (ArrayList<String>) totalFirst(beta).clone();
								tempArray.remove("e"); //First(B) − {ε}
								if(!checkFollow(ourVariable).containsAll(tempArray)) 
								{ 	
									ArrayList<String> newFollows = new ArrayList<String>();
									newFollows = checkFollow(ourVariable);
									newFollows.addAll(tempArray);
							
									Collections.sort(newFollows);
									followArray.set(index, (ArrayList<String>) newFollows.stream().distinct().collect(Collectors.toList()));
//									toString3(followArray);
									change = true;
								}
								
								//Case 2: Variable followed by Variable that contains e in its firsts
								if(totalFirst(beta).contains("e"))  {
									
									if(!checkFollow(ourVariable).containsAll(checkFollow(currentRule.get(0)))) {
										
										ArrayList<String> newFollows = new ArrayList<String>();
										newFollows = checkFollow(ourVariable);
										newFollows.addAll(checkFollow(currentRule.get(0)));
										
										Collections.sort(newFollows);
										followArray.set(index, (ArrayList<String>) newFollows.stream().distinct().collect(Collectors.toList()));
//										toString3(followArray);
										change = true;
									}	
								}	
							}	
						}
						
						if(Character.isUpperCase(element.charAt(element.length() - 1))) 
						{
							String lastVariable = element.substring(element.length() - 1);
							//Case 3: Variable is the last position of rule (or only)
							if(!checkFollow(lastVariable).containsAll(checkFollow(currentRule.get(0)))) 
							{
								
								ArrayList<String> newFollows = new ArrayList<String>();
								newFollows = checkFollow(lastVariable);
								newFollows.addAll(checkFollow(currentRule.get(0)));
								
								int index2 = 0;
								for(int h = 0 ; h< grammarArray.size() ; h++) {
									if(grammarArray.get(h).get(0).equals(lastVariable)) {
										index2 = h; //index of the variable in our grammar
									}
								}
								Collections.sort(newFollows);
								followArray.set(index2, (ArrayList<String>) newFollows.stream().distinct().collect(Collectors.toList()));
//								toString3(followArray);
									change = true;
							}	
						}	
					}
				}
			}
			
			
//			toString3(followArray);
			
//			Convert array to output string
			ArrayList<String> tempOutputArray = new ArrayList<String>();
			for(int q = 0; q < grammarArray.size(); q++) {	
				
				if(followArray.get(q).contains("$")) {
					followArray.get(q).remove("$");
					followArray.get(q).add("$");
				}
				
				String tempOutput2 = String.join("", followArray.get(q));
				String tempOutput1 = grammarArray.get(q).get(0);
				String tempOutput = tempOutput1.concat(","+tempOutput2);
				tempOutputArray.add(tempOutput);	
			}
			followOutput = String.join(";",tempOutputArray );
			this.followString = followOutput;
			return followOutput;
		}	
		
		public static ArrayList<String> totalFirst(String input){
			ArrayList<String> result = new ArrayList<String>();
			boolean halt = false;
			
			if(input.equals("e")) {
				result.add("e");
				return result;	
			}
			
			for(int i = 0; i< input.length() && !halt; i++) { 
				if(Character.isUpperCase(input.charAt(i))) //variable
				{
					String Variable = input.substring(i,i+1);
					int index = 0;
					for(int h = 0 ; h < grammarArray.size() ; h++) {
						if(grammarArray.get(h).get(0).equals(Variable)) {
							index = h; //index of the variable in our grammar
						}
					}
					if(!firstArray.get(index).contains("e")) {
						halt = true;
					}
					result.addAll(checkFirst(Variable));
				}
				else { 	//terminal
					halt = true;
					result.add(input.substring(i,i+1)); 
				}
			}
			Collections.sort(result);
			result = (ArrayList<String>) result.stream().distinct().collect(Collectors.toList());
			if(halt) {
				result.remove("e");
			}
			return result;
		}
				

	}

	public static void main(String[] args) {

		/*
		 * Please make sure that this EXACT code works. This means that the method
		 * and class names are case sensitive
		 */
		
		String grammar = "S,iST,e;T,cS,a";
		String input1 = "iiac";// S, iST, iiSTT, iiTT, iiaT, iiacS, iiac
		String input2 = "iia";
		CFG g = new CFG(grammar); //S, iST, iiSTT, iiTT, iiaT, ERROR
		System.out.println(g.table());  //S,a,e;S,c,e;S,i,iST;S,$,e;T,a,a;T,c,cS
		System.out.println(g.parse(input1));
		System.out.println(g.parse(input2));
		
		
//		System.out.println("First: " + g.firstString); 
//		System.out.println("Follow: " + g.followString);
	}

}
