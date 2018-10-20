import java.util.*;
import java.io.File;

public class RouteFindingAgent {
	// max number of directions for which an agent may use to traverse the
	// environment
	private static final int maxNumDirection = 8;

	private DirectedGraph environment = new DirectedGraph(sc);
	private Strategy strategy = new Strategy();
	private int initialState;
	private int goalState;
	private int currentState;
	private int previousState;
	private ArrayList<Integer> visitedLocations = new ArrayList<Integer>();
	// private ArrayList<String> actionSequence = new
	// ArrayList<String>(Arrays.asList("n", "nw", "w", "sw", "s", "se", "e", "ne"));

	public RouteFindingAgent(int initialState, int goalState, String strat, DirectedGraph environment) {
		this.goalState = goalState;
		this.environment = environment;

		this.initialState = initialState;
		move(initialState);

		// run search strategy
		if (strat.toLowerCase().equals("bfs")) {
			strategy = new BFSSTrategy(maxNumDirection, initialState, environment.getDirections(initialState));
		} else {
			strategy = new IDSStrategy(maxNumDirection, initialState, environment.getDirections(initialState));
		}
	}

	public boolean goalCheck() {
		if (currentState == goalState) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<String> runStrategy() {
		while (!this.goalCheck()) {
			this.move(strategy.traverse(environment.getDirections(currentState)));
		}
	}

	private void move(int newState) {
		if ((newState > -1) && (!visitedLocations.contains(newState))) {
			int oldLocation = currentState;
			this.previousState = oldLocation;
			this.currentState = newState;
			visitedLocations.add(newState);
		} else {

		}
	}
}