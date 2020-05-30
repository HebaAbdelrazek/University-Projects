import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class BreadthFirstSearch extends AbstractSearch{
	
	public Node breadthFirstSearch(LinkedList<Node> queue, EndGame problem) {
		
		done = false;
		while(!done) {
			if(queue.isEmpty()) { //exit the loop if the queue goes empty (no solution)
				//System.out.println("FAILURE 1: there is no solution to this problem");
				done = true;
			}
			else { //if the queue is not empty, remove the node according to the search algorithm used
				Node currNode = queue.remove(0); //remove node from the end of the queue
				
				if(problem.isGoal(currNode)) { //apply the goal test and return the node if the test succeeds
					System.out.println("SUCCESS: goal reached");
					done = true;
					return currNode;
				}
				else { //if it fails the goal test, expand the node further
					numberOfExpandedNodes+=1;
					ArrayList<Node> expandedNodes = this.expand(currNode, problem);
					this.repeatedStates.add(currNode.getState().toString());
					
					for(int i = 0 ; i < expandedNodes.size() ; i++) {	
						queue.add(expandedNodes.get(i)); //add expanded nodes to the queue
					}
				}
				
			}
		}
		
		return solution;
	}

}
