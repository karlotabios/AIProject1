import java.util.*;
import java.io.File;

public class BestFirstSearch extends Strategy {
	int mapSize;

	public BestFirstSearch(int maxNumDirections, int initialState, ArrayList<Integer> initialFringe) {
		super(maxNumDirections, initialState, initialFringe);
		mapSize = maxNumDirections;
	}

	public int traverse(ArrayList<Integer> childNodes) {
		this.extendFringe(childNodes);
		fringe.remove(0);
		// do selection of min here, store selection to a variable, then return that
		// value
		int targetIndex = getIndexSLD(process(fringe));
		System.out.println("Fringe: " + fringe);
		System.out.println("targetIndex: " + targetIndex + " (" + process(fringe) + ")");
		fringe.clear();
		fringe.add(targetIndex);
		return targetIndex;
	}

	private int process(ArrayList<Integer> fringe) {
		// outputs the minimum value (not index) from the SLD heuristic values of the
		// current fringe.
		ArrayList<Integer> sldCopy = new ArrayList<>();
		for (int i = 0; i < fringe.size(); i++) {
			sldCopy.add(sldValues.get(fringe.get(i)));
		}
		System.out.println("SLD fringe values: " + sldCopy);
		int currMin = selectMin(sldCopy);
		return currMin;
	}

	private int selectMin(ArrayList<Integer> l) {
		int min = l.get(0);
		for (int i = 1; i < l.size(); i++) {
			if (min > l.get(i)) {
				min = l.get(i);
			}
		}
		return min;
	}

}

// Straight line distances to Island 7:
// Island 0: 360
// Island 1: 368
// Island 2: 249
// Island 3: 122
// Island 4: 253
// Island 5: 126
// Island 6: 41
// Island 7: 0