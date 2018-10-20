import java.util.*;
import java.io.File;

public class Strategy {

	// protected ArrayList<Integer> fringe = new ArrayList<Integer>();
	Queue<Integer> fringe = new LinkedList<>();
	Queue<Integer> fringeVisited = new LinkedList<>();
	DirectedGraph environment;
	protected int currentParentIndex;
	protected int currentChildIndex;
	protected int numInvalidLocations;
	protected int maxNumChildren;

	public Strategy(int maxNumDirections, int initialState, ArrayList<Integer> initialFringe, DirectedGraph dg) {
		this.environment = dg;
		this.maxNumChildren = maxNumDirections;
		this.currentParentIndex = initialState;
		for (int i = 0; i < initialFringe.size(); i++) {
			if (initialFringe.get(i) >= 0) {
				this.fringe.add(initialFringe.get(i));
			}
		}
		this.numInvalidLocations = 0;
	}

	public Integer traverseWrapper() {
		if (fringe.size() == 0) {
			// refill main queue
			System.out.println("Visited queue before refilling main queue: " + fringeVisited);
			while (fringeVisited.size() > 0) {
				for (int i = 0; i < 8; i++) {
					// sorry for the O(n^2) complexity
					if (this.environment.getDirections(fringeVisited.peek()).get(i) >= 0) {
						fringe.add(this.environment.getDirections(fringeVisited.peek()).get(i));
					}
				}
				fringeVisited.remove(); // remove head for every iteration
			}
		}
		return traverse(fringe);
	}

	private Integer traverse(Queue<Integer> fringe) {
		// iterate through fringe items
		if (fringe.size() > 0) {
			System.out.println("Current queue: " + fringe);
			Integer check = this.fringe.remove();
			System.out.println("CurrentState: " + check);
			fringeVisited.add(check);
			return check;
		} else {
			// fringe empty, need to fill it up again
			System.out.println("Main queue is empty.");
			System.out.println("Visited queue is: " + fringeVisited);
			return null;
		}

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