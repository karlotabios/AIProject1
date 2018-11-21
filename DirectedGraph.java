import java.util.*;
import java.io.File;

public class DirectedGraph {

    // max number of directions for which an agent may use to traverse the environment
    private static final int maxNumDirection = 8;

    // an island's weight is non existent so it's NULL
    private static final int emptyLocationWeight = -1;

    // this is the list of islands denoted as it's adjacency list (that's why it's a list of lists kasi kada island may mga island na katabi so that's how we describe/name them); the index is the island's name itself (ex. island '0' is 'islandWeights.get(0)')
    // this will only contain as many elements as there are islands, but they are represented as lists of whatever island they are connected to (hence list type)
    protected ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>();

    // this is the island weights as a list; the index is the island's name itself (ex. island '0' is 'islandWeights.get(0)')
    // this will only contain as many elements as there are islands (because each island has only 1 integer weight)
    protected ArrayList<Integer> islandWeights = new ArrayList<Integer>();

    // this is the island for -1 islands; also known as the non existent island (it is connected to 8 non existent islands); it's used as a buffer only, and there's no use going to it
    protected ArrayList<Integer> emptyLocation = new ArrayList<Integer>(Arrays.asList(-1, -1, -1, -1, -1, -1, -1, -1));

    public DirectedGraph(Scanner sc) 
    {
        int islandNum = sc.nextInt();
        int scIterator = 0;
        int adjacencyListIterator = 0;
        while (sc.hasNext()) {
            if (scIterator < islandNum) {
                //ito yung island weights
                int weight = sc.nextInt();
                islandWeights.add(weight);
                scIterator++;
            } else {
                //ito na yung island
                ArrayList<Integer> adjacentLocations = new ArrayList<Integer>();
                adjacencyList.add(adjacentLocations);
                for (int m = 0; m < maxNumDirection; ++m) {
                    int adjacent = sc.nextInt();
                    adjacencyList.get(adjacencyListIterator).add(adjacent);
                }
                adjacencyListIterator++;
            }
        }

        sc.close();

        //map out connected locations
        System.out.println("Map configuration:");
        for (int m = 0; m < adjacencyList.size(); m++) {
            System.out.println("[" + m + "] => " + adjacencyList.get(m));
        }

        //map out the weights
        System.out.println("Weight configuration:");
        for (int m = 0; m < islandWeights.size(); m++) {
            System.out.println("[" + m + "] => " + islandWeights.get(m));
        }

        System.out.println("\n");
    }

    public ArrayList<Integer> getDirections( int location )
    {
        if (location > -1)
        {
            return adjacencyList.get(location);
        }
        else
        {
            return emptyLocation;
        }
    }

    public ArrayList<Integer> getIslandWeights( int location )
    {
        if (location > -1)
        {
            ArrayList<Integer> tempAdjacencyList = adjacencyList.get(location);
            ArrayList<Integer> tempWeightList = new ArrayList<Integer>();
            for(int w = 0; w < maxNumDirection; ++w) {
                int location = tempAdjacencyList.get(w);
                if (location != -1) {
                    tempWeightList.add(islandWeights.get(location));
                }
                else {
                    tempWeightList.add(emptyLocationWeight);
                }
            }
            return tempWeightList;
        }
        else
        {
            return emptyLocation;
        }
    }
}