import java.util.ArrayList;

public class State { //a class that represents the state of the node at any point in time 
	
	public int damage;
	public int stonesCollected;
	
	public int[][] warriors;
	public int[][] stones; 
	
	public int[] currPosition; //current cell
	
	public State(int[][] warriorsArray, int[][] stonesArray, int[] ironManArray) { //the remaining warriors' positions, remaining stones' positions and new iron man position is passed to the current node from its parent
		
		this.stones = new int[6][2];
		
		for(int i = 0 ; i < stonesArray.length ; i++) {
			this.stones[i][0] = stonesArray[i][0];
			this.stones[i][1] = stonesArray[i][1];
		}
		
		this.warriors = new int[warriorsArray.length][2];
		
		for(int i = 0 ; i < warriorsArray.length ; i++) {
			this.warriors[i][0] = warriorsArray[i][0];
			this.warriors[i][1] = warriorsArray[i][1];
		}
		
		this.currPosition = new int[2];
		this.currPosition[0] = ironManArray[0];
		this.currPosition[1] = ironManArray[1];
		
		this.stonesCollected = 0;
		for(int i = 0 ; i < this.stones.length ; i++) {
			if(this.stones[i][0] == -1)
				this.stonesCollected++;
		}
	}
	
	public static boolean contains(int[][] array, int[] element) { //a method that checks whether the 2D array in the first argument contains the 1D array in the second argument
		for(int i = 0 ; i < array.length ; i++) {
			if(array[i][0] == element[0] && array[i][1] == element[1])
				return true;
		}
		return false;
	}
	
//	public boolean warriorsEquals(State state) {
//		for(int i = 0 ; i < this.warriors.length ; i++) {
//			if(this.warriors[i][0] != state.warriors[i][0] || this.warriors[i][1] != state.warriors[i][1])
//				return false;
//		}
//		return true;
//	}
//	
//	public boolean stonesEquals(State state) {
//		for(int i = 0 ; i < this.stones.length ; i++) {
//			if(this.stones[i][0] != state.stones[i][0] || this.stones[i][1] != state.stones[i][1])
//				return false;
//		}
//		return true;
//	}
//	
//	@Override
//	public boolean equals(Object o) {
//		State state = (State) o;
//		if(this.stonesEquals(state) && this.warriorsEquals(state) && this.currPosition[0] == state.currPosition[0] && this.currPosition[1] == state.currPosition[1])
//			return true;
//		
//		return false;
//	}
	
	@Override
	public String toString() {
		String s = "" + currPosition[0] + "," + currPosition[1] + ":";
		for(int i = 0; i < stones.length; i++) {
			s += stones[i][0] + "," + stones[i][1];
			s += (i < stones.length - 1)? ",":";";
		}
		for(int i = 0; i < warriors.length; i++) {
			s += warriors[i][0] + "," + warriors[i][1];
			s += (i < warriors.length - 1)? ",":";";
		}
		
		return s;
	}
}
