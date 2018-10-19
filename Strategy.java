import java.util.*;
import java.io.File;

public abstract class Strategy {

	protected ArrayList<Integer> fringe = new ArrayList<Integer>();
	protected int currentParentIndex;
	protected int currentChildIndex;
	protected int numInvalidLocations;
	private static final maxNumChildren;

	public Strategy( int maxNumDirections, int initialState, int initialFringe )
	{
		this.maxNumChildren = maxNumDirections;
		this.currentParentIndex = initialState;
		for(int i = 0; i < initialFringe.size(); i++)
		{
			this.fringe.add(initialFringe.get(i));
		}
		this.numInvalidLocations = 0;
	}

	public abstract void traverse();

    private static int getChild( int parent, int rank ) 
    {
        //rank is from left to right (1 to 8 only; 1 to left-most, 8 to right-most);
        return (maxNumChildren * parent) + rank;
    }

    private static int getParentIndex( int child ) 
    {
        return child / maxNumChildren;
    }

    private static void setNewParent( int parent ) 
    {
    	this.currentChildIndex = parent;
    }
}