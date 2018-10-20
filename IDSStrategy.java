import java.util.*;
import java.io.File;

public class IDSStrategy extends Strategy {

	protected ArrayList<Integer> visitedStack; = new ArrayList<Integer>();

	public IDSStrategy( int maxNumDirections, int initialState, ArrayList<Integer> initialFringe )
	{
		super( maxNumDirections, initialState, initialFringe );
	}

	public int traverse( ArrayList<Integer> childNodes )
	{
		this.extendFringe( childNodes );

		return fringe.get( ++currentChildIndex );
	}
}