import java.util.*;
import java.io.File;

public class RouteFindingAgent {
	//max number of directions for which an agent may use to traverse the environment
    private static final int maxNumDirection = 8;

	private DirectedGraph environment;
	private Strategy strategy;
	private int initialState;
	private int goalState;
	private int currentState;
	private int previousState;
	private ArrayList<Integer> visitedLocations = new ArrayList<Integer>();
	// private ArrayList<String> actionSequence = new ArrayList<String>(Arrays.asList("n", "nw", "w", "sw", "s", "se", "e", "ne"));

	public RouteFindingAgent( int initialState, int goalState, String strat, DirectedGraph environment )
	{		
		this.goalState = goalState;
		this.environment = environment;
		this.initialState = initialState;
		this.currentState = this.initialState;
		this.visitedLocations.add( initialState );

		//run search strategy
		if (strat.toLowerCase().equals("bfs"))
		{
			strategy = new BFSStrategy( maxNumDirection, initialState, environment.getDirections( initialState ) );
		}
		else
		{
			strategy = new IDSStrategy( maxNumDirection, initialState, environment.getDirections( initialState ) );
		}
	}

	public boolean goalCheck()
	{
		if (currentState == goalState)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void runStrategy()
	{
		while (!this.goalCheck()) {
			int pinapuntaSa = strategy.traverse( environment.getDirections( currentState ) );
			System.out.println(currentState + " ==> " + environment.getDirections( currentState ));
			this.move( pinapuntaSa );
		}
		strategy.printRoute();
		return;
	}

	private void move( int newState )
	{	
		int oldLocation = this.currentState;
		this.previousState = oldLocation;
		this.currentState = newState;
		this.visitedLocations.add( newState );
	}
}