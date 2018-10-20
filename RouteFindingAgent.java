import java.util.*;
import java.io.File;

public class RouteFindingAgent {
	// max number of directions for which an agent may use to traverse the
	// environment
	private static final int maxNumDirection = 8;

	private DirectedGraph environment;
	private Strategy strategy;
	private int initialState;
	private int goalState;
	private int currentState;
	private int previousState;
	private ArrayList<Integer> visitedLocations;
	// private ArrayList<String> actionSequence = new
	// ArrayList<String>(Arrays.asList("n", "nw", "w", "sw", "s", "se", "e", "ne"));

	public RouteFindingAgent(int initialState, int goalState, String strat, DirectedGraph environment) {
		this.goalState = goalState;
		this.environment = environment;
		this.visitedLocations = new ArrayList<Integer>();
		this.initialState = initialState;
		move(initialState);
		// System.out.println("Initial State: " + initialState);

		// run search strategy
		if (strat.toLowerCase().equals("bfs")) {
			// strategy = new BFSStrategy(maxNumDirection, initialState,
			// environment.getDirections(initialState));
			strategy = new Strategy(maxNumDirection, initialState, environment.getDirections(initialState),
					environment);
		} else {
			// strategy = new IDSStrategy(maxNumDirection, initialState,
			// environment.getDirections(initialState));
			System.out.println("IDS not yet working, come again soon.");
		}
	}

	public boolean goalCheck() {
		if (currentState == goalState) {
			return true;
		} else {
			return false;
		}
	}

	// public ArrayList<String> runStrategy() {
	// while (!this.goalCheck()) {
	// this.move(strategy.traverse(environment.getDirections(currentState)));
	// }
	// }

	public int runStrategy() {
		System.out.println("Current State (before first strategy iteration): " + currentState);
		while (!this.goalCheck()) {
			currentState = this.move(strategy.traverseWrapper());
			// System.out.println("CurrentState (RFA.java): " + currentState);
		}
		return currentState;
	}

	private int move(int newState) {
		if ((newState > -1) && (!visitedLocations.contains(newState))) {
			int oldLocation = currentState;
			this.previousState = oldLocation;
			this.currentState = newState;
			visitedLocations.add(newState);
			// System.out.println("Visited locations: " + visitedLocations);
		}
		// else {

		// }
		return this.currentState;
	}
}