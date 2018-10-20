import java.util.*;
import java.io.File;

public class DirectedGraph {

    // max number of directions for which an agent may use to traverse the
    // environment
    private static final int maxNumDirection = 8;

    protected ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>();
    protected ArrayList<Integer> emptyLocation = new ArrayList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1));

    public DirectedGraph(Scanner sc) {
        int i = 0;
        while (sc.hasNext()) {
            ArrayList<Integer> adjacentLocations = new ArrayList<Integer>();
            adjacencyList.add(adjacentLocations);
            for (int m = 0; m < maxNumDirection; ++m) {
                int adjacent = sc.nextInt();
                adjacencyList.get(i).add(adjacent);
            }
            i++;
        }

        sc.close();

        // map out connected locations
        System.out.println("adjacencyList.size(): " + adjacencyList.size());
        for (int m = 0; m < adjacencyList.size(); m++) {
            System.out.println("[" + m + "] => " + adjacencyList.get(m));
        }
    }

    public ArrayList<Integer> getDirections(int location) {
        if (location > -1) {
            // System.out.println("adjacencyList.get(location): " +
            // adjacencyList.get(location));
            return adjacencyList.get(location);
        } else {
            return emptyLocation;
        }
    }

}