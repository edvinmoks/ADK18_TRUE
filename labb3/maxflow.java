import java.util.*;

public class maxflow{
    int x;
    int y;
    int s;
    int t;
    int e;
    int v;
    int adjacencyMatrix[][];
    static ArrayList<Integer> path = new ArrayList<Integer>();
    public maxflow(int adjacencyMatrix[][]){
      this.adjacencyMatrix = adjacencyMatrix;
    }
    public class Edge {
      int to;
      int flow = 0;
      int capacity;

      Edge(int to, int capacity){
        this.to = to;
        this.flow = flow;
        this.capacity = capacity;
      }
    }

  static boolean BFS(int matrix[][], int s, int t){
    LinkedList<Integer> q = new LinkedList<Integer>();
    boolean visited[] = new boolean[matrix.length]; // Nodes we have visited
    visited[s]=true;
    q.add(s);

    while(q.size() != 0){
      int current = q.poll();
      // Iterate through each neighbours to current node
      for(int neighbour = 0; neighbour < matrix[current].length; neighbour++){
          if (matrix[current][neighbour] == 1) {
            if (!visited[neighbour]) {
              // Add next neighbour to the queue
              q.add(neighbour);
              visited[neighbour]=true;
            }
          }
      }
      if (visited[t]) {break;}
    }
    int currentNode = 0;
    path.add(currentNode);
    while (currentNode != t) {
      for(int n = 0; n < matrix.length; n++) {
        if (matrix[currentNode][n] == 1) {
          currentNode = n;
          path.add(currentNode);
          break;
        }
      }
    }
    return visited[t];
  }

  public static void main(String[] args) {
    int matrix[][] = {
      {0, 1, 1, 0, 0, 0},
      {0, 0, 0, 1, 1, 0},
      {0, 0, 0, 0, 1, 0},
      {0, 0, 0, 0, 0, 1},
      {0, 0, 0, 0, 0, 1},
      {0, 0, 0, 0, 0, 0}
    };
    System.out.println(BFS(matrix, 0, 5));
    System.out.println(path);
  }
}
