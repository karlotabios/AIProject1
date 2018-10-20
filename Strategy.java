import java.util.*;
import java.io.File;

public abstract class Strategy {

	protected ArrayList<Integer> fringe = new ArrayList<Integer>();
	protected ArrayList<Integer> route = new ArrayList<Integer>();
	protected int currentParentIndex;
	protected int currentChildIndex;
	private static int maxNumChildren;

	public Strategy( int maxNumDirections, int initialState, ArrayList<Integer> initialFringe )
	{
		this.currentParentIndex = 0;
		this.currentChildIndex = 0;
		this.maxNumChildren = maxNumDirections;
		this.fringe.add( initialState );
		// extendFringe( initialFringe );
	}

	public abstract int traverse( ArrayList<Integer> childNodes );

    protected void extendFringe( ArrayList<Integer> additionalFringe )
	{
    	for(int i = 0; i < additionalFringe.size(); i++)
		{
			fringe.add(additionalFringe.get(i));
		}
		return;
    }

    private int getChild( int parentIndex, int rank ) 
    {
        //rank is from left to right (1 to 8 only; 1 to left-most, 8 to right-most);
        return ( maxNumChildren * parentIndex ) + rank;
    }

    private int getParentIndex( int child ) 
    {
    	int temp = (child - 1) / maxNumChildren;
        return temp;
    }

    private void setNewParent( int parentIndex ) 
    {
    	currentChildIndex = parentIndex;
    	return;
    }

    private int trackSequence( int childIndex )
    {
    	int parent = this.getParentIndex( childIndex );
    	route.add(0, fringe.get( childIndex ) );
    	if ( childIndex == parent )
    	{
    		return this.getParentIndex( childIndex );
    	}
    	return this.trackSequence( this.getParentIndex( childIndex ) );
    }

    public ArrayList<Integer> getRoute( int currentState )
    {
    	this.trackSequence( currentChildIndex );
    	System.out.println( "\n" );
    	return route;
    }

	// private void populateFringe()
	// {
	// 	if ( currentParentIndex != currentNodeIndex ) 
	// 	{
	// 		this.extendFringe( childNodes );
	// 	}
	// }
}