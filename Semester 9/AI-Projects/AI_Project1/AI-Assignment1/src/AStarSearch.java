import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStarSearch extends AbstractSearch{
	
	public AStarSearch(String strategy) {
		this.strategy = strategy;
	}
	
	public Node aStarSearch(PriorityQueue pQueue, EndGame problem){
		done = false;
		while(!done) {
			if(pQueue.isEmpty()) { //exit the loop if the priority queue goes empty (no solution)
//				System.out.println("FAILURE 1: there is no solution to this problem");
				done = true;
			}
			else { //if the queue is not empty, remove the node according to the search algorithm used
				Node currNode = (Node) pQueue.poll(); //remove the node at the front of the priority queue (the node with the lowest path cost)
				
				if(problem.isGoal(currNode)) { //apply the goal test and return the node if the test succeeds
					System.out.println("SUCCESS: goal reached");
					solution = currNode;
					done = true;
				}
				else { //if it fails the goal test, expand the node further
					numberOfExpandedNodes+=1;
					ArrayList<Node> expandedNodes = this.expand(currNode, problem);
					this.repeatedStates.add(currNode.getState().toString());
					
					for(int i = 0 ; i < expandedNodes.size() ; i++) {	
						expandedNodes.get(i).setHeuristic(heuristic, problem);
						pQueue.add(expandedNodes.get(i)); //add expanded nodes to the priority queue (nodes are inserted in order of their path cost + heuristic cost)
					}
				}
				
			}
		}
		
		return solution;
	}
}
