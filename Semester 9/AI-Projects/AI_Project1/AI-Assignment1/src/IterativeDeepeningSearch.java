import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class IterativeDeepeningSearch extends AbstractSearch{

	private DepthFirstSearch DF = new DepthFirstSearch();
	public static ArrayList<String> Solution = null;
	
	public Node iterativeDeepeningSearch(LinkedList<Node> queue, EndGame problem) {
		
		int depthCurrent = 0;
		Node solution = null;
	
		while(solution == null) 
		{
			//apply depth limited search with the current depth and then increment it
			solution = depthLimitedSearch(queue, problem, depthCurrent++); 

			//clear the hash set of repeated states except for the initial state to start a new depth limited search
			repeatedStates.clear();	
			repeatedStates.add((initialState.getState()).toString());
				
			//clear queue of nodes except for the initial state to start a new depth limited search 
			queue.clear();
			queue.add(initialState);
			
			//if a solution is found, return it and exit the loop
			if(solution != null)
				return solution;
		
		}
		return solution;
		
	}

	public Node depthLimitedSearch(LinkedList<Node> queue, EndGame problem, int limit){
		
		solution = null;
		done = false;
		while(!done) {
			if(queue.isEmpty()) { //exit the loop if the queue goes empty (no solution)
//				System.out.println("FAILURE 1: there is no solution to this problem");
				done = true;
			}
			else { //if the queue is not empty, remove the node according to the search algorithm used
				currNode = queue.remove(queue.size()-1); //remove node from the end of the queue
				
				if(problem.isGoal(currNode)) { //apply the goal test and return the node if the test succeeds
					System.out.println("SUCCESS: goal reached");
					solution = currNode;
					done = true;
				}
				else { //if it fails the goal test, expand the node further
					if(currNode.getDepth() < limit) { //the node will only be expanded if it is within the depth limit
						numberOfExpandedNodes+=1;
						ArrayList<Node> expandedNodes = this.expand(currNode, problem);
						this.repeatedStates.add(currNode.getState().toString());
						
						for(int i = 0 ; i < expandedNodes.size() ; i++) {	
							queue.add(expandedNodes.get(i)); //add expanded nodes to the queue
						}
					}
					else {
						return solution;
					}
				}
			}
		}
		
		return solution;
	}

}
