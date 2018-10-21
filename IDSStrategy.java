import java.util.*;
import java.io.File;

public class IDSStrategy extends Strategy {

	private static final ArrayList<String> fringeBuffer = new ArrayList<String>(Arrays.asList( -1, -1, -1, -1, -1, -1, -1, -1 ));
	protected ArrayList<Integer> visitedLocationIndex = new ArrayList<Integer>();
	protected ArrayList<Integer> visitedLocation = new ArrayList<Integer>();
	protected int depthIteration = 3;
	protected int currentDepth = 0;
	// protected int counter;

	public IDSStrategy( int maxNumDirections, int initialState, ArrayList<Integer> initialFringe )
	{
		super( maxNumDirections, initialState, initialFringe );
		visitedLocationIndex.add( initialState );
		counter = 0;
	}

	public int traverse( ArrayList<Integer> childNodes )
	{
		this.extendFringe( childNodes );
		int nextNode;

		for (int i = this.maxNumDirections - 1; i >= 0; i--)
		{
			nextNode = fringe.get( this.getChildIndex( currentParentIndex, i ) );
			if ( !this.visitedLocation.contains( nextNode ) )
			{
				this.visitedLocation.add( nextNode );
				break;
			}
			nextNode = null;
		}

		if (nextNode == null)
		{
			int backtrackIndex = this.visitedLocation.size() - 2;
			if (backtrackIndex < 0) 
			{
				backtrackIndex = 0;
			}
			return this.visitedLocation.get( backtrackIndex );
		}
		else 
		{
			return nextNode;
		}
		
		if ( nextNode == -1 )
		{
			this.extendFringe( fringeBuffer );
		}

		int eligibleChildNodeIndex = this.getChildIndex( currentParentIndex, (counter % maxNumDirections) );

		this.visitedLocationIndex.add( eligibleChildNodeIndex );

		currentDepth++;

		if ( currentDepth ) 
		{
			counter++;
		}
		return fringe.get( ++currentChildIndex );
	}
}