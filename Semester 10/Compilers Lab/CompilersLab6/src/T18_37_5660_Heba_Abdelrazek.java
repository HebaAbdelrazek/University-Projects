import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class T18_37_5660_Heba_Abdelrazek{
	 
	public static void main(String[] args) {
		String input = "S,ScT,T;T,aSb,iaLb,e;L,SdL,S"; 
		CFG cfg = new CFG(input);
		String firstEncoding = cfg.First(); 
		String followEncoding = cfg.Follow();
		System.out.println("First: " + firstEncoding); 
		System.out.println("Follow: " + followEncoding);
	}
}

class CFG{
	
	static ArrayList<ArrayList<String>> grammar;
	static int oldGrammarSize = 0;
	static ArrayList<ArrayList<String>> firstArray;
	static ArrayList<ArrayList<String>> followArray;
	
	public CFG(String input) {
		
		constructCFG(input);
	}
	
	public static void constructCFG(String input) {	
//		Extract all rules to an array of rules
		String [] rules = input.split(";"); 
		oldGrammarSize = rules.length;
		grammar = new ArrayList<ArrayList<String>>();
		
//		Extract each rule to a an ArrayList of its own
		for(int k = 0; k < oldGrammarSize; k++) { 
			ArrayList<String> rule = new ArrayList<String>(Arrays.asList(rules[k].split(",")));
			grammar.add(rule);
		}
//		System.out.println("");
//		System.out.println("Grammar ArrayList of ArrayLists: ");
//		toString3(grammar);
//		System.out.println("");
	}
	
	
	public String First() {
		
		firstArray = new ArrayList<ArrayList<String>>();
		String firstString = ""; 
		
		for(int m = 0; m < grammar.size(); m++) {
			ArrayList<String> empty = new ArrayList<String>();
			firstArray.add(empty);
		}  	//[ [''],[''],[''] ]

		boolean change = true;
		while(change) {
			change = false;
			
			//Loop on grammar rules
			for(int i = 0 ; i < grammar.size() ; i++) {
				ArrayList<String> currentRule = grammar.get(i);	 // currentRule = [ 'S' , 'ScT' , 'T' ]
			
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
//						toString3(firstArray);
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
//								toString3(firstArray);
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
//		toString3(firstArray);
		
//		Convert array to output string
		ArrayList<String> tempOutputArray = new ArrayList<String>();
		for(int p = 0; p < grammar.size(); p++) {	
			String tempOutput2 = String.join("", firstArray.get(p));
			String tempOutput1 = grammar.get(p).get(0);
			String tempOutput = tempOutput1.concat(","+tempOutput2);
			tempOutputArray.add(tempOutput);	
		}
		firstString = String.join(";",tempOutputArray );
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
			for(int i = 0 ; i< grammar.size() ; i++) {
				if(grammar.get(i).get(0).equals(element)) {
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
			for(int i = 0 ; i< grammar.size() ; i++) {
				if(grammar.get(i).get(0).equals(element)) {
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
		
		for(int m = 0; m < grammar.size(); m++) {
			ArrayList<String> empty = new ArrayList<String>();
			followArray.add(empty);
		}  	
		followArray.get(0).add("$"); //[ ['$'],[''],[''] ]
	
		boolean change = true;
		while(change) {
			change = false;
		
			for(int i = 0 ; i < grammar.size() ; i++) {
				ArrayList<String> currentRule = grammar.get(i);	 // currentRule = [ 'S' , 'ScT' , 'T' ]
			
				for(int j = 1 ; j < currentRule.size() ; j++) { //loop on current rule
					String element  = currentRule.get(j); // ScT
					
					for(int k = 0; k < element.length() - 1 ;k++) 
					{ 	//S c T , A−→αBβ
						if(Character.isUpperCase(element.charAt(k))) 
						{ 	//Variable
							
							String ourVariable = element.substring(k,k+1);
							String beta = element.substring(k+1);
						
							int index = 0;
							for(int z = 0 ; z< grammar.size() ; z++) {
								if(grammar.get(z).get(0).equals(ourVariable)) {
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
//								toString3(followArray);
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
//									toString3(followArray);
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
							for(int h = 0 ; h< grammar.size() ; h++) {
								if(grammar.get(h).get(0).equals(lastVariable)) {
									index2 = h; //index of the variable in our grammar
								}
							}
							Collections.sort(newFollows);
							followArray.set(index2, (ArrayList<String>) newFollows.stream().distinct().collect(Collectors.toList()));
//							toString3(followArray);
								change = true;
						}	
					}	
				}
			}
		}
		
		
//		toString3(followArray);
		
//		Convert array to output string
		ArrayList<String> tempOutputArray = new ArrayList<String>();
		for(int q = 0; q < grammar.size(); q++) {	
			
			if(followArray.get(q).contains("$")) {
				followArray.get(q).remove("$");
				followArray.get(q).add("$");
			}
			
			String tempOutput2 = String.join("", followArray.get(q));
			String tempOutput1 = grammar.get(q).get(0);
			String tempOutput = tempOutput1.concat(","+tempOutput2);
			tempOutputArray.add(tempOutput);	
		}
		followOutput = String.join(";",tempOutputArray );
		return followOutput;
	}	
	
	public static ArrayList<String> totalFirst(String input){
		ArrayList<String> result = new ArrayList<String>();
		boolean halt = false;
		for(int i = 0; i< input.length() && !halt; i++) { 
			if(Character.isUpperCase(input.charAt(i))) //variable
			{
				String Variable = input.substring(i,i+1);
				int index = 0;
				for(int h = 0 ; h < grammar.size() ; h++) {
					if(grammar.get(h).get(0).equals(Variable)) {
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
	

}