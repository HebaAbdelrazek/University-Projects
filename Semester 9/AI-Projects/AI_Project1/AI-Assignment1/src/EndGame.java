import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class EndGame implements Problem{
	public Node initialState; //the initial state of the problem

	String grid;
	
	public int rows;
	public int columns;
	
	public int[][] warriorsArray; //2D array that carries the initial x and y coordinates of all warriors
	public int[][] stonesArray; //2D array that carries the initial x and y coordinates of all stones
	
	public int[] thanosArray; //array that carries thanos's x and y coordinates
	public int[] ironManArray; //array that carries the initial x and y coordinates of iron man
	
	public EndGame(String grid) {
		this.grid = grid;
		
		String[] gridTemp = grid.split(";");
		
		String[] rowsAndColumns = gridTemp[0].split(",");
		this.rows = Integer.parseInt(rowsAndColumns[0]);
		this.columns = Integer.parseInt(rowsAndColumns[1]);
		
		String[] ironMan = gridTemp[1].split(",");
				
		ironManArray = new int[2];
		ironManArray[0] = Integer.parseInt(ironMan[0]); //add iron man x coordinate
		ironManArray[1] = Integer.parseInt(ironMan[1]); //add iron man y coordinate
		
		String[] thanos = gridTemp[2].split(",");
		
		thanosArray = new int[2];
		thanosArray[0] = Integer.parseInt(thanos[0]); //add thanos x coordinate
		thanosArray[1] = Integer.parseInt(thanos[1]); //add thanos y coordinate
		
		String[] stones = gridTemp[3].split(",");
		
		stonesArray = new int[6][2];
		
		//adding the stones' coordinates to the stonesArray
		int countStones = 0;
		for(int i = 0 ; i < stones.length-1 ; i+=2) { 
			stonesArray[countStones][0] = Integer.parseInt(stones[i]);
			stonesArray[countStones][1] = Integer.parseInt(stones[i+1]);
			countStones++;
		}	
		
		String[] warriors = gridTemp[4].split(",");
		
		warriorsArray = new int[warriors.length/2][2];
		
		//adding the warriors' coordinates to the warriorsArray
		int countWarriors = 0;
		for(int i = 0 ; i < warriors.length-1 ; i+=2) {
			warriorsArray[countWarriors][0] = Integer.parseInt(warriors[i]);
			warriorsArray[countWarriors][1] = Integer.parseInt(warriors[i+1]);
			countWarriors++;
		}	
		
	}
	
	public void setInitialState(Node initialState) { //sets the initial state of the problem
		this.initialState = initialState;
	}
	
	public Node getInitialState() { //returns the initial state of the problem
		return initialState;
	}

	@Override
	public boolean isGoal(Node node) { //returns true if the node is the goal node (operator is snap)
		if(!node.isRootNode())
			return node.getOperator().equals("snap");
		return false;
	}

	@Override
	public ArrayList<String> getOperations(State state) { //returns an array list of operators for any state
		ArrayList<String> operations = new ArrayList<String>();
		
		boolean warriorFound = false;
		boolean snap = false;
		
		int[] currPosition = new int[2];
		currPosition[0] = state.currPosition[0];
		currPosition[1] = state.currPosition[1];
		
		//checking for adjacent warriors
		if(currPosition[0] > 0) {
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0]-1;
			newPosition[1] = currPosition[1];
			
			if(State.contains(state.warriors, newPosition))
				operations.add("kill");
		}
		else if(currPosition[0] < rows-1) {
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0]+1;
			newPosition[1] = currPosition[1];
			
			if(State.contains(state.warriors, newPosition))
				operations.add("kill");
		}
		else if(currPosition[1] > 0) {
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0];
			newPosition[1] = currPosition[1]-1;
			
			if(State.contains(state.warriors, newPosition))
				operations.add("kill");
		}
		else if(currPosition[1] < columns-1) {
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0];
			newPosition[1] = currPosition[1]+1;
			
			if(State.contains(state.warriors, newPosition))
				operations.add("kill");
		}
		
		//moving into any cell that does not contain neither a warrior nor thanos
		if(currPosition[0] > 0 && !(this.thanosArray[0] == currPosition[0]-1 && this.thanosArray[1] == currPosition[1])) {
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0]-1;
			newPosition[1] = currPosition[1];
			
			if(!(State.contains(state.warriors, newPosition)))
				operations.add("up");
		}
		
		if(currPosition[0] < rows-1 && !(this.thanosArray[0] == currPosition[0]+1 && this.thanosArray[1] == currPosition[1])) {
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0]+1;
			newPosition[1] = currPosition[1];
			
			if(!(State.contains(state.warriors, newPosition)))
				operations.add("down");
		}
		
		if(currPosition[1] > 0 && !(this.thanosArray[0] == currPosition[0] && this.thanosArray[1] == currPosition[1]-1)) {
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0];
			newPosition[1] = currPosition[1]-1;
			
			if(!(State.contains(state.warriors, newPosition)))
				operations.add("left");
		}
		
		if(currPosition[1] < columns-1 && !(this.thanosArray[0] == currPosition[0] && this.thanosArray[1] == currPosition[1]+1)) {
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0];
			newPosition[1] = currPosition[1]+1;
			
			if(!(State.contains(state.warriors, newPosition)))
				operations.add("right");
		}
		
		//iron man can only move into thanos' cell if thanos is adjacent to him, his damage is lower than 100 and he has collected all 6 stones
		if(currPosition[0] > 0 && this.thanosArray[0] == currPosition[0]-1 && this.thanosArray[1] == currPosition[1] && state.damage < 100 && state.stonesCollected == 6) 
			operations.add("up");
		
		else if(currPosition[0] < rows-1 && this.thanosArray[0] == currPosition[0]+1 && this.thanosArray[1] == currPosition[1] && state.damage < 100 && state.stonesCollected == 6) 
			operations.add("down");
		
		else if(currPosition[1] > 0 && this.thanosArray[0] == currPosition[0] && this.thanosArray[1] == currPosition[1]-1 && state.damage < 100 && state.stonesCollected == 6) 
			operations.add("left");
		
		else if(currPosition[1] < columns-1 && this.thanosArray[0] == currPosition[0] && this.thanosArray[1] == currPosition[1]+1 && state.damage < 100 && state.stonesCollected == 6) 
			operations.add("right");
		
		
		//iron man can collect a stone only if he is in the same cell as the stone
		if(State.contains(state.stones, currPosition)) {
			operations.add("collect");
		}
		
		//iron man can snap only if he is in the same cell as thanos
		if(currPosition[0] == this.thanosArray[0] && currPosition[1] == this.thanosArray[1]) {
			operations.add("snap"); 
		}
			
		return operations;
	}

	@Override
	public State getNextState(State state, String action) { //returns the next state for any state and an operator
		
		State nextState = null;
		
		int[] currPosition = new int[2];
		currPosition[0] = state.currPosition[0];
		currPosition[1] = state.currPosition[1];
		
		if(action.equals("left")) { 
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0];
			newPosition[1] = currPosition[1]-1;
			
			//iron man's updated position is passed to the next state
			nextState = new State(state.warriors, state.stones, newPosition);
		}
		else if(action.equals("right")) { 
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0];
			newPosition[1] = currPosition[1]+1;
			
			//iron man's updated position is passed to the next state
			nextState = new State(state.warriors, state.stones, newPosition);
		}
		else if(action.equals("up")) { 
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0]-1;
			newPosition[1] = currPosition[1];
			
			//iron man's updated position is passed to the next state
			nextState = new State(state.warriors, state.stones, newPosition);
		}
		else if(action.equals("down")) { 
			int[] newPosition = new int[2];
			newPosition[0] = currPosition[0]+1;
			newPosition[1] = currPosition[1];
			
			//iron man's updated position is passed to the next state
			nextState = new State(state.warriors, state.stones, newPosition);
		}
		else if(action.equals("kill")) { 
			
			int[][] newWarriors = new int[state.warriors.length][2];
			
			for(int i = 0 ; i < state.warriors.length ; i++) {
				newWarriors[i][0] = state.warriors[i][0];
				newWarriors[i][1] = state.warriors[i][1];
			}
			
			//creating an array list that will contain the positions of all adjacent cells to iron man
			ArrayList<ArrayList<Integer>> adjacentCells = new ArrayList<ArrayList<Integer>>();
			
			if(currPosition[0] > 0) {
				ArrayList<Integer> tmp = new ArrayList<>();
				tmp.add(currPosition[0]-1);
				tmp.add(currPosition[1]);
				
				adjacentCells.add(tmp);
			}
			if(currPosition[0] < rows-1) {
				ArrayList<Integer> tmp = new ArrayList<>();
				tmp.add(currPosition[0]+1);
				tmp.add(currPosition[1]);
				
				adjacentCells.add(tmp);
			}
			if(currPosition[1] > 0) {
				ArrayList<Integer> tmp = new ArrayList<>();
				tmp.add(currPosition[0]);
				tmp.add(currPosition[1]-1);
				
				adjacentCells.add(tmp);
			}
			if(currPosition[1] < columns-1) {
				ArrayList<Integer> tmp = new ArrayList<>();
				tmp.add(currPosition[0]);
				tmp.add(currPosition[1]+1);
				
				adjacentCells.add(tmp);
			}
			
			//checking which of the adjacent cells to iron man carry warriors 
			for(int i = 0 ; i < adjacentCells.size() ; i++) {
				int[] tmp = new int[2];
				tmp[0] = adjacentCells.get(i).get(0);
				tmp[1] = adjacentCells.get(i).get(1);
				
				//set the x and y values of the killed warriors to -1 and -1
				if(state.contains(state.warriors, tmp)) {
					for(int j = 0 ; j < state.warriors.length ; j++) {
						if(state.warriors[j][0] == tmp[0] && state.warriors[j][1] == tmp[1]) {
							newWarriors[j][0] = -1;
							newWarriors[j][1] = -1;
						}
					}
				}
			}
			
			//pass the updated array of warriors to the next state
			//iron man position will not change
			nextState = new State(newWarriors, state.stones, currPosition);
		}
		else if(action.equals("collect")) { 
			int[][] newStones = new int[6][2];
			
			for(int i = 0 ; i < state.stones.length ; i++) {
				newStones[i][0] = state.stones[i][0];
				newStones[i][1] = state.stones[i][1];
			}
			
			//set the x and y values of the collected stone to -1 and -1
			for(int i = 0 ; i < state.stones.length ; i++) {
				if(state.stones[i][0] == currPosition[0] && state.stones[i][1] == currPosition[1]) {
					newStones[i][0] = -1;
					newStones[i][1] = -1;
				}
			}
			
			//pass the updated array of stones to the next state
			//iron man position will not change
			nextState = new State(state.warriors, newStones, currPosition);
		}
		else if(action.equals("snap")) {
			return state; //state will not change on "snap"
		}
		
		return nextState;
	}

	@Override
	public int getStepCost(String action, State nextState) { //returns the cost of moving from one state to the next following a certain action
		int cost = 0;
		
		int[] nextPos = new int[2];
		nextPos[0] = nextState.currPosition[0];
		nextPos[1] = nextState.currPosition[1];
		
		//creating an array list of all adjacent cells to iron man
		ArrayList<ArrayList<Integer>> adjacentCells = new ArrayList<ArrayList<Integer>>();
		
		if(nextPos[0] > 0) {
			ArrayList<Integer> tmp = new ArrayList<>();
			tmp.add(nextPos[0]-1);
			tmp.add(nextPos[1]);
			
			adjacentCells.add(tmp);
		}
		if(nextPos[0] < rows-1) {
			ArrayList<Integer> tmp = new ArrayList<>();
			tmp.add(nextPos[0]+1);
			tmp.add(nextPos[1]);
			
			adjacentCells.add(tmp);
		}
		if(nextPos[1] > 0) {
			ArrayList<Integer> tmp = new ArrayList<>();
			tmp.add(nextPos[0]);
			tmp.add(nextPos[1]-1);
			
			adjacentCells.add(tmp);
		}
		if(nextPos[1] < columns-1) {
			ArrayList<Integer> tmp = new ArrayList<>();
			tmp.add(nextPos[0]);
			tmp.add(nextPos[1]+1);
			
			adjacentCells.add(tmp);
		}
		
		//actions that involve moving to another cell
		if(action.equals("left") || action.equals("right") || action.equals("up") || action.equals("down")) { 
			for(int i = 0 ; i < adjacentCells.size() ; i++) {
				int[] tmp = new int[2];
				tmp[0] = adjacentCells.get(i).get(0);
				tmp[1] = adjacentCells.get(i).get(1);
				
				//check if the new cell is adjacent to any warriors
				if(nextState.contains(nextState.warriors, tmp)) {
					for(int j = 0 ; j < nextState.warriors.length ; j++) {
						if(nextState.warriors[j][0] == tmp[0] && nextState.warriors[j][1] == tmp[1]) {
							cost+=1; //damage increases by 1 point for every adjacent warrior to iron man
						}
					}
				}
				else if(tmp[0] == thanosArray[0] && tmp[1] == thanosArray[1])
					cost+=5; //damage increases by 5 points if iron man is in an adjacent cell to thanos
			}
			if(nextPos[0] == thanosArray[0] && nextPos[1] == thanosArray[1])
				cost+=5; //damage increases by 5 points if iron man is in the same cell as thanos
		}
		else { 
			
			if(action.equals("kill")) {
				for(int i = 0 ; i < adjacentCells.size() ; i++) {
					int[] tmp = new int[2];
					tmp[0] = adjacentCells.get(i).get(0);
					tmp[1] = adjacentCells.get(i).get(1);
					
					//checks how many warriors are adjacent to iron man
					if(nextState.contains(nextState.warriors, tmp)) {
						for(int j = 0 ; j < nextState.warriors.length ; j++) {
							if(nextState.warriors[j][0] == tmp[0] && nextState.warriors[j][1] == tmp[1]) {
								cost+=2; //damage increases by 2 points for every warrior killed
							}
						}
					}
					else if(tmp[0] == thanosArray[0] && tmp[1] == thanosArray[1])
						cost+=5; //damage increases by 5 points if iron man is in an adjacent cell to thanos
				}
				if(nextPos[0] == thanosArray[0] && nextPos[1] == thanosArray[1])
					cost+=5; //damage increases by 5 points if iron man is in the same cell as thanos
			}
			else if(action.equals("collect")){
				cost+=3; //damage increases by 3 points for collecting a stone
				
				for(int i = 0 ; i < adjacentCells.size() ; i++) {
					int[] tmp = new int[2];
					tmp[0] = adjacentCells.get(i).get(0);
					tmp[1] = adjacentCells.get(i).get(1);
					
					//check if the new cell is adjacent to any warriors
					if(nextState.contains(nextState.warriors, tmp)) {
						for(int j = 0 ; j < nextState.warriors.length ; j++) {
							if(nextState.warriors[j][0] == tmp[0] && nextState.warriors[j][1] == tmp[1]) {
								cost+=1;
							}
						}
					}
					else if(tmp[0] == thanosArray[0] && tmp[1] == thanosArray[1])
						cost+=5; //damage increases by 5 points if iron man is in an adjacent cell to thanos
				}
			}
		}
		
		return cost;
	}
	
//	public boolean nodeVisited(Node prevNode, Node currNode) { //handles repeated states
//			
//		boolean move = false;
//		
//		if(prevNode.getState().currPosition[0] == currNode.getState().currPosition[0]
//				&& prevNode.getState().currPosition[1] == currNode.getState().currPosition[1]){
//			if(prevNode.getState().warriorsEquals(currNode.getState())
//					&& prevNode.getState().stonesEquals(currNode.getState()))
//				return true;
//				
//		}
//		
//		return false;
//	}
	
}
