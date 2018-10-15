import java.util.*;

public class DirectedGraph {
    public static void main(String[] args) {
        int n = 8;
        
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        adj[0].add(1); adj[0].add(2); adj[0].add(3); adj[0].add(4);
        adj[1].add(0); adj[1].add(4);
        adj[2].add(0); adj[2].add(3); adj[2].add(6);
        adj[3].add(0); adj[3].add(2); adj[3].add(4); adj[3].add(5); adj[3].add(6);adj[3].add(7);
        adj[4].add(0); adj[4].add(1); adj[4].add(3); adj[4].add(5);
        adj[5].add(3); adj[5].add(4); adj[5].add(7);
        adj[6].add(2); adj[6].add(3); adj[6].add(7);
        adj[7].add(3); adj[7].add(5); adj[7].add(6);

        for (int i = 0; i < n; i++) {
            System.out.println("[" + i + "] => " + adj[i]);
        }

        breadthFirstSearch(adj, 3);
    }
    
    public static void breadthFirstSearch(ArrayList<Integer>[] list, int target) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].get(0) == target) {
                System.out.println("Found at: [" + i + "] => " + list[i]);
                return;
            }
        }
    }
}
