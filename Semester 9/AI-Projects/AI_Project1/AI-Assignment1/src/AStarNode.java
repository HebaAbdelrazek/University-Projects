
public class AStarNode extends Node{

	public AStarNode(State state) {
		super(state);
	}
	
	public AStarNode(State state, Node parent, String operator, int stepCost) {
		super(state, parent, operator, stepCost);
	}
	
	@Override
	public int compareTo(Object o) { //node is comparable using the summation of its path cost and heuristic cost
		return this.hCost+this.pathCost >= (((Node)o).hCost+((Node)o).pathCost) ? 1:-1;
	}

}
