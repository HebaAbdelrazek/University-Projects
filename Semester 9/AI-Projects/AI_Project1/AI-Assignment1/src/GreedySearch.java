import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class GreedySearch extends AbstractSearch{
	
	public GreedySearch(String strategy) {
		this.strategy = strategy;
	}
	
	public Node greedySearch(PriorityQueue pQueue, EndGame problem){
		done = false;
		while(!done) {
			if(pQueue.isEmpty()) { //return failure if the queue is empty
				System.out.println("FAILURE 1: there is no solution to this problem");
				done = true;
			}
			else { //if the queue is not empty, remove the node according to the search algorithm used
				Node currNode = (Node) pQueue.poll(); //remove node with the lowest cost
				
//				System.out.println(currNode.hCost);
//				System.out.println(currNode.getOperator());
//				System.out.println();
				
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
						pQueue.add(expandedNodes.get(i)); //add expanded nodes to the queue
					}
				}
				
			}
		}
		
		return solution;
	}
}
