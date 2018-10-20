import java.util.*;

public class DirectedGraph {
    public static void main(String[] args) {
        int n = 8;
        
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        adj[0].add(0); adj[0].add(1); adj[0].add(2); adj[0].add(3); adj[0].add(4);
        adj[1].add(1); adj[1].add(0); adj[1].add(4);
        adj[2].add(2); adj[2].add(0); adj[2].add(3); adj[2].add(6);
        adj[3].add(3); adj[3].add(0); adj[3].add(2); adj[3].add(4); adj[3].add(5); adj[3].add(6);adj[3].add(7);
        adj[4].add(4); adj[4].add(0); adj[4].add(1); adj[4].add(3); adj[4].add(5);
        adj[5].add(5); adj[5].add(3); adj[5].add(4); adj[5].add(7);
        adj[6].add(6); adj[6].add(2); adj[6].add(3); adj[6].add(7);
        adj[7].add(7); adj[7].add(3); adj[7].add(5); adj[7].add(6);

        for (int i = 0; i < n; i++) {
            System.out.println("[" + i + "] => " + adj[i].get(0));
        }
        breadthFirstSearch(adj, adj[0], 4);
    }
    
    public static void breadthFirstSearch(ArrayList<Integer>[] map, ArrayList<Integer> initial, int goal) {
        // for (int i = 0; i < map.length; i++) {
        //     if (map[i].get(0) == goal) {
        //         System.out.println("BFS Found at: [" + i + "] => " + map[i]);
        //         return;
        //     }
        // }
        Queue<Integer> q = new LinkedList<>();
        boolean found = false;
        
        // load initial position to queue
        q.add(initial.get(0));
        // check initial position
        if (q.peek() == goal) {
            found = true;
            System.out.println("Goal state found at " + initial.get(0));
            return;
        }
        // else check children
        else {
            q.remove();
            for (int i = 1; i < initial.size(); i++) {
                q.add(initial.get(i));
            }
            for (int i = 0; i < q.size(); i++) {
                if (q.peek() == goal) {
                    found = true;
                    System.out.println("Goal state found: " + q.peek());
                    break;
                }
                else {
                    System.out.println("Not goal state: " + q.peek());
                    q.remove();
                }
            }
        }
        
    }

    // public static void 

}
