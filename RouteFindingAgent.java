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

	private Scanner sc;
	private ArrayList<Integer> route = new ArrayList<>();
	// private ArrayList<String> actionSequence = new
	// ArrayList<String>(Arrays.asList("n", "nw", "w", "sw", "s", "se", "e", "ne"));

	public RouteFindingAgent(int initialState, int goalState, String strat, DirectedGraph environment) {
		this.goalState = goalState;
		this.environment = environment;
		this.initialState = initialState;
		this.currentState = this.initialState;

		// run search strategy
		if (strat.toLowerCase().equals("bestfirstsearch")) {
			strategy = new BestFirstSearch(maxNumDirection, initialState, environment.getDirections(initialState));
		} else if (strat.toLowerCase().equals("astarsearch")) {
			strategy = new AStarSearch(maxNumDirection, initialState, environment.getDirections(initialState));
		}
	}

	public boolean goalCheck() {
		if (currentState == goalState) {
			return true;
		} else {
			return false;
		}
	}

	public void runStrategy() {
		sc = new Scanner(System.in);
		while (!this.goalCheck()) {
			System.out.println("currentState: " + currentState);
			route.add(currentState);
			int pinapuntaSa = strategy.traverse(environment.getDirections(currentState)); // calls extendFringe()
			// environment.getDirections(currentState) : 3 ==> [0, 2, 4, 5, 6, 7, -1, -1]
			this.move(pinapuntaSa);
			System.out.println("Continue? yes/no");
			String s = sc.nextLine();
			if (!s.equalsIgnoreCase("yes")) {
				sc.close();
				break;
			}
		}
		// strategy.printRoute();
		System.out.println("You have reached your destination: " + currentState + " (route taken: " + route + ")");
		return;
	}

	private void move(int newState) {
		int oldLocation = this.currentState;
		this.previousState = oldLocation;
		this.currentState = newState;
	}
}