import java.util.*;
import java.io.File;

public class RouteFindingAgent {
	//max number of directions for which an agent may use to traverse the environment
    private static final int maxNumDirection = 8;

	private DirectedGraph environment;
	private Strategy strategy;
	private String stratType;
	private int initialState;
	private int goalState;
	private int currentState;
	private int previousState;
	// private ArrayList<String> actionSequence = new ArrayList<String>(Arrays.asList("n", "nw", "w", "sw", "s", "se", "e", "ne"));

	public RouteFindingAgent( int initialState, int goalState, String strat, DirectedGraph environment )
	{		
		this.goalState = goalState;
		this.environment = environment;
		this.initialState = initialState;
		this.currentState = this.initialState;
		this.stratType = strat.toLowerCase();

		//run search strategy
		if (strat.toLowerCase().equals("bfs"))
		{
			strategy = new BFSStrategy( maxNumDirection, initialState, environment.getDirections( initialState ) );
		}
		// else if (strat.toLowerCase().equals("bfs"))
		// {
		// 	strategy = new IDSStrategy( maxNumDirection, initialState, environment.getDirections( initialState ) );
		// }
		else
		{
			strategy = new GreedyStrategy( maxNumDirection, initialState, environment.getDirections( initialState ) );
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
		if ((this.stratType.equals("bfs")) || (this.stratType.equals("ids"))) {
			while (!this.goalCheck()) {
				//alamin mo kung saan ka papapuntahin based on kung saan ka ngayon at sa kung ano sabi sa yo ng strategy mo
				int pinapuntaSa = strategy.traverse( environment.getDirections( currentState ) );

				//sabihin mo kung saan ka ngayon at kung saan ka pwede pumunta
				System.out.println(currentState + " ==> " + environment.getDirections( currentState ));

				//punta ka na sa kung saan ka pinapapunta
				this.move( pinapuntaSa );
			}
		}
		else {
			while (!this.goalCheck()) {
				//alamin mo kung saan ka papapuntahin based on kung saan ka ngayon at kung ano yung weights nung mga island at sa kung ano sabi sa yo ng strategy mo
				int pinapuntaSa = strategy.traverse( environment.getDirections( currentState ), environment.getIslandWeights( currentState ) );

				//sabihin mo kung saan ka ngayon at kung saan ka pwede pumunta
				System.out.println(currentState + " == Adjacent Islands ==> " + environment.getDirections( currentState ));

				//sabihin mo kung saan ka ngayon at kung saan ka pwede pumunta
				System.out.println(currentState + " == Island Weights ==> " + environment.getIslandWeights( currentState ));

				//punta ka na sa kung saan ka pinapapunta
				this.move( pinapuntaSa );
			}
		}
		strategy.printRoute();
		return;
	}

	private void move( int newState )
	{	
		int oldLocation = this.currentState;
		this.previousState = oldLocation;
		this.currentState = newState;
	}
}