import java.util.*;
import java.io.File;

public class IslandHopper {

	// launcher for AI project1
	public static void main(String[] args) throws Exception {
		// read input from console
		Scanner sc;
		File file = null;
		String strategy = null;
		int numLocations = 0;
		int initialState = 0;
		int goalState = 0;

		if ((args.length % 2 != 0) || (args.length == 0)) {
			System.out.println("Missing parameter values; check your parameters");
			errorMessage();
			return;
		}

		System.out.println("argument length: " + args.length);

		for (int i = 0; i < args.length; i += 2) {
			String input = args[i];
			String inputValue = args[i + 1];
			switch (input) {
			case "--strategy":
				System.out.println("--'strategy' parameter value'" + inputValue + "'\n");
				strategy = inputValue;
				break;
			case "--file":
				System.out.println("--'file' parameter value'" + inputValue + "'\n");
				file = new File(inputValue);
				break;
			default:
				System.out.println("Ignoring unknown parameter '" + input + "'\n");
				return;
			}
		}

		if (file == null) {
			System.out.println("no parameter for 'file'");
			errorMessage();
			return;
		}

		sc = new Scanner(file);

		// first line is strategy
		if (strategy != null) {
			sc.next();
		} else {
			strategy = sc.next();
		}

		if ((!strategy.toLowerCase().equals("bfs")) && (!strategy.toLowerCase().equals("ids"))) {
			System.out.println("'strategy' parameter not BFS or IDS\ninputted: '" + strategy + "'\n");
			errorMessage();
			return;
		}

		// three numbers after strategy is identified are nitial location/island, goal
		// island
		initialState = sc.nextInt();
		goalState = sc.nextInt();

		// generate the environment
		DirectedGraph graph = new DirectedGraph(sc);

		// generate 1 agent
		RouteFindingAgent agent = new RouteFindingAgent(initialState, goalState, strategy, graph);

		// run the solution from agent's strategy
		// ArrayList<String> solution = new ArrayList<String>();
		int solution = agent.runStrategy();
		System.out.println("SOLUTION: " + solution);
		return;
	}

	private static void errorMessage() {
		System.out.println(
				"GenerateTree \nUsage: \n\tParamater/s: \n\t--strategy  -->  Type of strategy for generating the fringe\n\t\t'BFS', 'IDS'\n\t--default  -->  Use input.txt for strategy and directed graph values\n\t--file  -->  (Required) Use a specified .txt file for strategy and directed graph values");
		return;
	}
}
