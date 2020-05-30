import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AbstractSearch {
	
	static int numberOfExpandedNodes;
	
	static Node initialState;
	static Node currNode;
	static Node solution;
	
	static int heuristic;
	
	static LinkedList<Node> queue;
	static PriorityQueue<Node> pQueue;
	
	DepthFirstSearch DF;
	BreadthFirstSearch BF;
	UniformCostSearch UC;
	IterativeDeepeningSearch ID;
	GreedySearch GR;
	AStarSearch AS;
	
	boolean done;
	
	public String strategy;
		
	public EndGame problem;
	HashSet<String> repeatedStates;
	
	public AbstractSearch() {
		numberOfExpandedNodes = 0;
		
		repeatedStates = new HashSet<String>();
		queue = new LinkedList<>();
		pQueue = new PriorityQueue<>();
		heuristic = -1;
		
		strategy = "";
		
	}

	public Node solve(EndGame problem, String strategy) { //returns a node that will be used to find the path from the root to the goal
		
		this.strategy = strategy;
		this.problem = problem;
		
		
		//initializing the search algorithm depending on the strategy input
		if(strategy == "DF") {
			DF = new DepthFirstSearch();	
		}
		else if(strategy == "BF") {
			BF = new BreadthFirstSearch();
		}
		else if(strategy == "UC") {
			UC = new UniformCostSearch();
		}
		else if(strategy == "ID") {
			ID = new IterativeDeepeningSearch();
		}
		else if(strategy == "GR1" || strategy == "GR2") {
			GR = new GreedySearch(strategy);
		}
		else if(strategy == "AS1" || strategy == "AS2") {
			AS = new AStarSearch(strategy);
		}
		
		
		//the initial state is created with the initial warrior positions, stones positions and iron man position
		State initState = new State(problem.warriorsArray, problem.stonesArray, problem.ironManArray);
		
		
		//create the root node which takes the initial state
		initialState = new Node(initState); 
		
		problem.setInitialState(initialState);
		
		
		//use a LinkedList for DF, ID and BF and a PriorityQueue for UC, GR and AS
		if(strategy == "DF" || strategy == "ID" || strategy == "BF")
			queue.add(initialState); //add initial state to the queue
		else 
			pQueue.add(initialState); //add initial state to the priority queue
				
		
		//determine which heuristic function is to be used if the search strategy is either greedy or A*
		if(strategy == "GR1" || strategy == "AS1") {
			heuristic = 1;
			initialState.setHeuristic(1, problem);
		}
		else if(strategy == "GR2" || strategy == "AS2") {
			heuristic = 2;
			initialState.setHeuristic(2, problem);
		}
		
		
		//run the search algorithm
		if(strategy == "DF") {
			solution = DF.depthFirstSearch(queue, problem);					
		}
		else if(strategy == "BF") {
			solution = BF.breadthFirstSearch(queue, problem);
		}
		else if(strategy == "UC") {
			solution = UC.uniformCostSearch(pQueue, problem);
		}
		else if(strategy == "ID") {
			solution = ID.iterativeDeepeningSearch(queue, problem);
		}
		else if(strategy == "GR1" || strategy == "GR2") {
			solution = GR.greedySearch(pQueue, problem);
		}
		else if(strategy == "AS1" || strategy == "AS2") {
			solution = AS.aStarSearch(pQueue, problem);
		}
			
		return solution;
	}
	
	public ArrayList<Node> expand(Node currNode, EndGame problem){ //expands the node
		ArrayList<Node> expandedNodes = new ArrayList<Node>();
		
		ArrayList<String> operations = problem.getOperations(currNode.getState()); //get all possible operations for the current node
		
		//for each operation, get the next state, create its node, and add it to the expanded nodes if it is not repeated
		for(int i = 0 ; i < operations.size() ; i++) { 
			State nextState = problem.getNextState(currNode.getState(), operations.get(i));
			
			Node newNode;
			if(strategy.equals("GR1") || strategy.equals("GR2"))
				newNode = new GreedyNode(nextState, currNode, operations.get(i), problem.getStepCost(operations.get(i), nextState)); //create a GreedyNode for greedy search
			else if(strategy.equals("AS1") || strategy.equals("AS2"))
				newNode = new AStarNode(nextState, currNode, operations.get(i), problem.getStepCost(operations.get(i), nextState)); //create an AStarNode for A* search
			else
				newNode = new Node(nextState, currNode, operations.get(i), problem.getStepCost(operations.get(i), nextState)); //create a Node for any other search
			
			//check if the state is NOT available in the hash set of visited states, and if it is not, add it to the expanded nodes
			if(!this.repeatedStates.contains(newNode.getState().toString())){
				expandedNodes.add(newNode);
			}
		}
		
		return expandedNodes;
	}
	
}
