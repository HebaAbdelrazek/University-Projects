
public class GreedyNode extends Node{

	public GreedyNode(State state) {
		super(state);
	}
	
	public GreedyNode(State state, Node parent, String operator, int stepCost) {
		super(state, parent, operator, stepCost);
	}
	
	@Override
	public int compareTo(Object o) { //node is comparable using its heuristic cost
		return this.hCost >= ((Node)o).hCost ? 1:-1;
	}
	
}
