import java.util.*;
import java.io.File;

public class BFSStrategy extends Strategy {

	public BFSStrategy( int maxNumDirections, int initialState, ArrayList<Integer> initialFringe )
	{
		super( maxNumDirections, initialState, initialFringe );
	}

	public int traverse( ArrayList<Integer> childNodes )
	{
		this.extendFringe( childNodes );

		return fringe.get( ++currentChildIndex );
	}
}