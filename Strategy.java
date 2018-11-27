import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public abstract class Strategy {

	protected ArrayList<Integer> fringe = new ArrayList<Integer>();
	protected ArrayList<Integer> route = new ArrayList<Integer>();
	protected ArrayList<String> directionsFringe = new ArrayList<String>();
	protected ArrayList<String> movementSequence = new ArrayList<String>();
	protected int currentParentIndex;
	protected int currentChildIndex;
	protected static int maxNumChildren;
	protected static final ArrayList<String> directions = new ArrayList<String>(
			Arrays.asList("north", "north west", "west", "south west", "south", "south east", "east", "north east"));
	public ArrayList<Integer> sldValues = new ArrayList<>();

	public Strategy(int maxNumDirections, int initialState, ArrayList<Integer> initialFringe) {
		this.currentParentIndex = 0;
		this.currentChildIndex = 0;
		this.maxNumChildren = maxNumDirections;
		this.fringe.add(initialState);
		this.directionsFringe.add("origin");

		this.sldValues.add(360); // island 0
		this.sldValues.add(368); // island 1
		this.sldValues.add(249); // island 2
		this.sldValues.add(122); // island 3
		this.sldValues.add(253); // island 4
		this.sldValues.add(126); // island 5
		this.sldValues.add(41); // island 6
		this.sldValues.add(0); // island 7
	}

	public abstract int process(ArrayList<Integer> fringe);

	public abstract int traverse(ArrayList<Integer> childNodes);

	protected int getIndexSLD(int val) {
		int result = 99999;
		for (int i = 0; i < sldValues.size(); i++) {
			if (sldValues.get(i) == val) {
				result = i;
				break;
			}
		}
		return result;
	}

	// function for pushing these children into the fringe
	protected void extendFringe(ArrayList<Integer> expansionNodes) {
		for (int i = 0; i < expansionNodes.size(); i++) {
			if (expansionNodes.get(i) == -1) {
				// System.out.println("Skippping since -1");
				// System.out.print(" ");
			} else {
				System.out.println("Adding: " + expansionNodes.get(i));
				this.fringe.add(expansionNodes.get(i));
			}
		}

		// identical to the fringe, but is pushing directions instead of indices
		for (int i = 0; i < directions.size(); i++) {
			this.directionsFringe.add(directions.get(i));
		}
		return;
	}

	protected ArrayList<Integer> getFringe() {
		return this.fringe;
	}

	private int getChildIndex(int parentIndex, int rank) {
		// rank is from left to right (1 to 8 only; 1 to left-most, 8 to right-most);
		return (maxNumChildren * parentIndex) + rank;
	}

	private int getParentIndex(int child) {
		int parent = (child - 1) / this.maxNumChildren;
		return parent;
	}

	private void setNewParent(int parentIndex) {
		this.currentChildIndex = parentIndex;
		return;
	}

	private int getLastNonLeafNodeIndedx() {
		return (fringe.size() - 2) / this.maxNumChildren;
	}

	// helper function for recursively getting the parent and storing them into a
	// route and movementSequence
	private int trackSequence(int childIndex) {
		int parent = this.getParentIndex(childIndex);
		this.route.add(0, this.fringe.get(childIndex));
		this.movementSequence.add(0, this.directionsFringe.get(childIndex));

		if (childIndex == parent) {
			return parent;
		}
		return this.trackSequence(parent);
	}

	public void printRoute() {
		this.trackSequence(this.currentChildIndex);
		System.out.println("\nSolution:\n" + this.route + "\n" + this.movementSequence);
		return;
	}
}