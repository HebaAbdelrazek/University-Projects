import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable{ //a class that represents a node with all of its information

	private State state;
	private Node parent;
	private String operator;
	public int pathCost;
	public int depth;
	public int hCost;

	public Node(State state) { //if it's the root node
		this.state = state;
		this.pathCost = 0;
		this.depth = 0;
		this.parent = null;
		this.hCost = -1;
		
		this.state.damage = 0;

	}

	public Node(State state, Node parent, String operator, int stepCost) { //if it's not the root node
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		this.pathCost = parent.pathCost + stepCost;
		this.depth = 0;
		this.hCost = -1;

		//the depth of a node is equal to the number of ancestors it has
		Node node = parent;
		while (node != null) { 
			node = node.getParent();
			depth++;
		}
		
		this.state.damage = this.pathCost;
	}

	public State getState() {
		return state;
	}

	public Node getParent() {
		return parent;
	}

	public String getOperator() {
		return operator;
	}

	public int getPathCost() {
		return pathCost;
	}

	public int getDepth() {
		return depth;
	}

	public int getHeuristic() {
		return hCost;
	}

	public void setHeuristic(int strategy, EndGame problem) { //strategy is either 1 or 2 (depending which function is to be used)
		int hCost = 0;

		 int[] currPosition = new int[2];
		 currPosition[0] = this.state.currPosition[0];
		 currPosition[1] = this.state.currPosition[1];
		
		 int stonesCollected = this.state.stonesCollected;
		 int[][] stonesRemaining;
		 
		 //adding all remaining stones to the stonesRemaining array
		 stonesRemaining = new int[6-stonesCollected][2];

		 int c = 0;
		 for(int i = 0 ; i < this.state.stones.length ; i++) {
			 if(this.state.stones[i][0] != -1 && this.state.stones[i][0] != this.state.currPosition[0] && this.state.stones[i][1] != this.state.currPosition[1]) {
				 stonesRemaining[c][0] = this.state.stones[i][0];
				 stonesRemaining[c][1] = this.state.stones[i][1];
				 c++;
			 }
		 }
		
		 if(strategy == 1) { //the heuristic cost is the damage to be applied by the remaining stones and the damage to be applied by Thanos
			 
			 //thanos' cost if iron man is in a cell that is neither adjacent to thanos nor the same cell as thanos is 10
			 int thanosCost = 10; 
		
			 //thanos' cost if iron man is in a cell adjacent to thanos is 5
			 if(currPosition[0] > 0 && problem.thanosArray[0] == currPosition[0]-1 && problem.thanosArray[1] == currPosition[1]) 
				thanosCost=5;
			 else if(currPosition[0] < problem.rows-1 && problem.thanosArray[0] == currPosition[0]+1 && problem.thanosArray[1] == currPosition[1]) 
				thanosCost=5;
			 else if(currPosition[1] > 0 && problem.thanosArray[0] == currPosition[0] && problem.thanosArray[1] == currPosition[1]-1) 
				thanosCost=5;
			 else if(currPosition[1] < problem.columns-1 && problem.thanosArray[0] == currPosition[0] && problem.thanosArray[1] == currPosition[1]+1)
				 thanosCost=5;
				 
			//thanos' cost if iron man is in the same cell as thanos (goal state) is 0 
			if(!isRootNode()) { 
				 if(operator.equals("snap"))  //ironman is in thanos cell
					 thanosCost = 0;
			 }
			
			//heuristic cost is the sum of the damage applied by the remaining stones and thanos' cost
			//therefore, when iron man is in the same cell as thanos (meaning he collected all 6 stones), heuristic cost will be 0 
			 hCost= (6-stonesCollected)*3 + thanosCost;
		
		 }
		 else if(strategy == 2) { //the heuristic cost is the damage to be applied by the remaining stones, the damage applied by the warriors adjacent to the stones and the damage to be applied by Thanos
			 
			//thanos' cost if iron man is in a cell that is neither adjacent to thanos nor the same cell as thanos is 10
			 int thanosCost = 10;
			
			 //counting the number of warriors that are adjacent to any of the remaining stones
			 int numberOfAdjacentWarriors = 0;
			 for(int i = 0 ; i < stonesRemaining.length ; i++) {
				 if(this.state.stones[i][0] != -1) {
					 if(this.state.stones[i][0] > 0) {
						 int[] pos = new int[2];
						 pos[0] = this.state.stones[i][0]-1;
						 pos[1] = this.state.stones[i][1];
						 
						 if(this.state.contains(this.state.warriors, pos))
							 numberOfAdjacentWarriors++;
					 }
					 if(this.state.stones[i][0] < problem.rows-1) {
						 int[] pos = new int[2];
						 pos[0] = this.state.stones[i][0]+1;
						 pos[1] = this.state.stones[i][1];
						 
						 if(this.state.contains(this.state.warriors, pos))
							 numberOfAdjacentWarriors++;
					 }
					 if(this.state.stones[i][1] > 0) {
						 int[] pos = new int[2];
						 pos[0] = this.state.stones[i][0];
						 pos[1] = this.state.stones[i][1]-1;
						 
						 if(this.state.contains(this.state.warriors, pos))
							 numberOfAdjacentWarriors++;
					 }
					 if(this.state.stones[i][1] < problem.columns-1) {
						 int[] pos = new int[2];
						 pos[0] = this.state.stones[i][0];
						 pos[1] = this.state.stones[i][1]+1;
						 
						 if(this.state.contains(this.state.warriors, pos))
							 numberOfAdjacentWarriors++;
					 }
				 }
			 }
			 
			//thanos' cost if iron man is in a cell adjacent to thanos is 5
			 if(currPosition[0] > 0 && problem.thanosArray[0] == currPosition[0]-1 && problem.thanosArray[1] == currPosition[1]) 
					thanosCost=5;
				 else if(currPosition[0] < problem.rows-1 && problem.thanosArray[0] == currPosition[0]+1 && problem.thanosArray[1] == currPosition[1]) 
					thanosCost=5;
				 else if(currPosition[1] > 0 && problem.thanosArray[0] == currPosition[0] && problem.thanosArray[1] == currPosition[1]-1) 
					thanosCost=5;
				 else if(currPosition[1] < problem.columns-1 && problem.thanosArray[0] == currPosition[0] && problem.thanosArray[1] == currPosition[1]+1)
					 thanosCost=5;
			 
			//thanos' cost if iron man is in the same cell as thanos (goal state) is 0 
			if(!isRootNode()) {		 
				 if(operator.equals("snap")) 
					 thanosCost = 0;
			 }
			
			//heuristic cost is the sum of the damage applied by the remaining stones, the damage applied by the warriors adjacent to the remaining stones and thanos' cost
			//therefore, when iron man is in the same cell as thanos, heuristic cost will be 0
			 hCost= (6-stonesCollected)*3 + numberOfAdjacentWarriors + thanosCost;
		 }

		this.hCost = hCost;
	}

	public boolean isRootNode() { //if the node does not have a parent, then it is the root node
		return parent == null;
	}

	public ArrayList<Node> getPathFromRoot() {
		ArrayList<Node> path = new ArrayList<Node>();
		Node current = this;

		//we go through the path from the bottom up until the root node
		while (!current.isRootNode()) {
			path.add(0, current); //add node to the start of the list
			current = current.getParent();
		}

		return path;
	}

	@Override
	public String toString() {
		String s = this.getState().toString();
		if (this.operator == null)
			return s + "f";
		s += (this.operator.equals("snap")) ? "t" : "f";
		return s;
	}

	@Override
	public int compareTo(Object o) {
		return this.pathCost >= ((Node)o).pathCost ? 1:-1;
	}
}
