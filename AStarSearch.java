import java.util.*;
import java.util.Map.Entry;
import java.io.File;

public class AStarSearch extends Strategy {
	int mapSize;
	protected int[][] matrix;

	public AStarSearch(int maxNumDirections, int initialState, ArrayList<Integer> initialFringe) {
		super(maxNumDirections, initialState, initialFringe);
		mapSize = maxNumDirections;
		matrix = new int[maxNumDirections + 1][maxNumDirections + 1];
		initializeMatrix(maxNumDirections);
	}

	private void initializeMatrix(int mapSize) {
		try {
			File file = new File("weights.txt");
			Scanner scanner = new Scanner(file);
			int from = 0, to = 0, weight = 0;
			while (scanner.hasNext()) {
				// format is <from> <to> <weight>
				from = scanner.nextInt();
				to = scanner.nextInt();
				weight = scanner.nextInt();

				makeEdge(from, to, weight);
			}
			scanner.close();
			System.out.println("Adjacency matrix is: ");
			System.out.print("  ");
			for (int i = 0; i < 8; i++)
				System.out.print(i + " ");
			System.out.println();

			for (int i = 0; i < 8; i++) {
				System.out.print(i + " ");
				for (int j = 0; j < 8; j++) {
					System.out.print(getEdge(i, j) + " ");
				}
				System.out.println();
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Something went wrong. Exiting...");
			return;
		}
	}

	private void makeEdge(int from, int to, int weight) {
		matrix[from][to] = weight;
	}

	private int getEdge(int from, int to) {
		return matrix[from][to];
	}

	public int traverse(ArrayList<Integer> childNodes) {
		this.extendFringe(childNodes);
		int origin = fringe.remove(0);
		int targetIndex = process(fringe, origin);
		System.out.println("Fringe: " + fringe);
		fringe.clear();
		fringe.add(targetIndex);
		return targetIndex;
	}

	private int process(ArrayList<Integer> fringe, int origin) {
		// outputs the INDEX of minimum value from the SLD heuristic values (g(n))
		// and the path cost (h(n)) of the current fringe.
		ArrayList<Integer> computed = new ArrayList<>(); // contains g(n) and h(n)
		HashMap<Integer, Integer> map = new HashMap<>();
		int sld = 0, weight = 0, totalCost = 0;
		for (int i = 0; i < fringe.size(); i++) {
			sld = sldValues.get(fringe.get(i));
			weight = matrix[origin][fringe.get(i)];
			totalCost = sld + weight;
			map.put(fringe.get(i), totalCost);
			computed.add(totalCost);
		}
		System.out.println("HashMap: " + map);
		int currMin = selectMin(computed);
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == currMin) {
				currMin = entry.getKey();
				break;
			}
		}
		System.out.println("currMin index (target): " + currMin);
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