import java.util.*;
import java.io.File;

public class Strategy {

	// protected ArrayList<Integer> fringe = new ArrayList<Integer>();
	Queue<Integer> fringe = new LinkedList<>();
	protected int currentParentIndex;
	protected int currentChildIndex;
	protected int numInvalidLocations;
	protected int maxNumChildren;

	public Strategy(int maxNumDirections, int initialState, ArrayList<Integer> initialFringe) {
		this.maxNumChildren = maxNumDirections;
		this.currentParentIndex = initialState;
		for (int i = 0; i < initialFringe.size(); i++) {
			this.fringe.add(initialFringe.get(i));
		}
		this.numInvalidLocations = 0;
	}

	public Integer traverse() {
		// get first item off of fringe
		Integer check = this.fringe.remove();
		return check;
	}

	private int getChild(int parent, int rank) {
		// rank is from left to right (1 to 8 only; 1 to left-most, 8 to right-most);
		return (maxNumChildren * parent) + rank;
	}

	private int getParentIndex(int child) {
		return child / maxNumChildren;
	}

	private void setNewParent(int parent) {
		this.currentChildIndex = parent;
	}
}