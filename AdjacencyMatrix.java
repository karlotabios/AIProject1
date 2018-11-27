import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

// from: https://www.sanfoundry.com/java-program-represent-graph-adjacency-matrix/

public class AdjacencyMatrix {
    private int[][] matrix;

    public AdjacencyMatrix(int mapSize) {
        matrix = new int[mapSize + 1][mapSize + 1];
    }

    public void main() {
        try {
            // ask for the file name
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter file name of edge list (must be in the same directory): ");
            File file = new File(sc.nextLine());
            sc.close();
            sc = new Scanner(file);
            int from = 0, to = 0, weight = 0;
            AdjacencyMatrix am = new AdjacencyMatrix(8);
            while (sc.hasNext()) {
                // format is <from> <to> <weight>
                from = sc.nextInt();
                to = sc.nextInt();
                weight = sc.nextInt();

                am.makeEdge(from, to, weight);
            }
            sc.close();
            System.out.println("Adjacency matrix is: ");
            System.out.print("  ");
            for (int i = 0; i < 8; i++)
                System.out.print(i + " ");
            System.out.println();

            for (int i = 0; i < 8; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < 8; j++) {
                    System.out.print(am.getEdge(i, j) + " ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Something went wrong");
            return;
        }

    }

    protected void makeEdge(int from, int to, int weight) {
        matrix[from][to] = weight;
    }

    protected int getEdge(int from, int to) {
        return matrix[from][to];
    }
}