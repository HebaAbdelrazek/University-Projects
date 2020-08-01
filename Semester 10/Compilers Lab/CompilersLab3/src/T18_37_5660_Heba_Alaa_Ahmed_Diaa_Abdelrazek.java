//T18_37_5660_Heba_Alaa_Ahmed_Diaa_Abdelrazek
public class FDFA {

	public static String [] acceptedStates;
	public static String [][] states;
	
	public static String initialState = "0"; //initialState
	public static String currentState = initialState;
	public static String lastAcceptedState;
	
	public static int Left = 0; 
	public static int Right = 0; //moves only to right
	
	public static String output = "";
	
	public static Boolean accepted = false;
	
	public FDFA(String fdfa) { 
		
		String statesString = fdfa.split("#")[0];
		String [] statesArray = statesString.split(";"); // ["0,0,1,00","1,2,1,01","2,0,3,10","3,3,3,11"]
		
		this.states = new String[statesArray.length][4];
		
		for(int i = 0; i< statesArray.length; i++) {
			states[i] = statesArray[i].split(",");
		}
		//states = [[0,0,1,00],[1,2,1,01],[2,0,3,10],[3,3,3,11]]
//		toString2(states);
		
		String acceptedString = fdfa.split("#")[1];
		this.acceptedStates = acceptedString.split(","); //acceptedStates = [0,1,2]
		
	}
	
	
	
	public static String run(String testString) { //10 11100 outputs 10 00
		
		int StringLength = testString.length();
//		System.out.println("String Length: " + StringLength);
		
		Left = 0;
		Right = 0;
		currentState = initialState;
		
		for(int i = 0; i < StringLength; i++) 
		{
					
			String currentChar = testString.substring(i,i+1); //1	
			currentState = states[Integer.parseInt(currentState)][Integer.parseInt(currentChar) + 1];
//			System.out.println("Current State: " + currentState);
			Left+=1;
			for(int j = 0; j < acceptedStates.length;j++)
			{
				if(acceptedStates[j].equals(currentState)) 
				{
					lastAcceptedState = currentState;
					Right+=1;
				}
			}
			
//			System.out.println("Last Accepted State: " + lastAcceptedState);
//			System.out.println("Right: " + Right);
//			System.out.println("Left: " + Left);
		}
//		System.out.println("Substring: " + testString.substring(Right));
		
//		System.out.println("Supposed Output: "+(states[Integer.parseInt(lastAcceptedState)][3]));

		output = output.concat((states[Integer.parseInt(lastAcceptedState)][3]) + "");
//		System.out.println("Output: " + output);
//		System.out.println(" ");
		
		if(Right == StringLength) //Accepted!
		{ 
//			System.out.println("meow");
			accepted = true;
//			output = output.concat((states[Integer.parseInt(lastAcceptedState)][3]));
			return output;	
		}
		else if(Left == StringLength && lastAcceptedState.equals(currentState))
		{
//			System.out.println("meow");
			accepted = true;
			return output;	
		}
		else 
		{
			run(testString.substring(Right));
		}
		
		if(!accepted) {
			return "Error";
		}
		else {
			return output;
		}
			
	}
	

	public static void toString(String [] array) {
		System.out.print("[");
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i]); 
			if(i!=array.length-1) {
				System.out.print(",");
			}
		}
		System.out.println("]");
		
	}
	
	public static void toString2(String [][] array) {
		System.out.print("[");
		for(int i = 0; i < array.length; i++) {
			System.out.print("[");
			for(int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]); 
				if(j!=array[i].length-1) {
					System.out.print(",");
				}
			}
			System.out.print("]");
			if(i!=array.length-1) {
				System.out.print(",");
			}
		}
		System.out.println("]");
		
		
	}
	public static void main(String[] args) {
		
		//FDFA fdfa = new FDFA("0,1,0,00;1,1,2,01;2,3,2,10;3,3,3,11#1,2");
		FDFA fdfa = new FDFA("0,0,1,00;1,2,1,01;2,0,3,10;3,3,3,11#0,1,2");
		
		//toString(acceptedStates);
		
		//System.out.println(run("1011100"));
		System.out.println(run("011"));
		//toString2(states);
	}
}
