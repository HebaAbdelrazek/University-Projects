import java.util.ArrayList;

public interface Problem { //derives the different components of a problem definition
	
	public Object getInitialState();
    public boolean isGoal(Node node);
    public ArrayList<String> getOperations(State state);
    public State getNextState(State state, String action);
    public int getStepCost(String action, State nextState);
    
}
