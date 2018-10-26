import java.util.*;

public class maxflow{
    static int adjacencyMatrix[][];
    static ArrayList<Integer> path = new ArrayList<Integer>();
    static ArrayList<Integer> temp = new ArrayList<Integer>();
    static int capacityMatrix[][];
    static int flowMatrix[][];
    static int restMatrix[][];
    static int v;
    static int s;
    static int t;
    static int e;

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

  static ArrayList<Integer> BFS(int matrix[][], int s, int t){
    LinkedList<ArrayList<Integer>> queue = new LinkedList<ArrayList<Integer>>();

    LinkedList<Integer> q = new LinkedList<Integer>();
    boolean visited[] = new boolean[matrix.length]; // Nodes we have visited
    visited[s]=true;
    q.add(s);

    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(s);
    queue.add(list);
    //System.out.println(queue.peek()); // Printing error
    while(q.size() != 0){
      int current = q.poll();

      ArrayList<Integer> path = new ArrayList<Integer>();
      path = queue.pop();

      // Iterate through each neighbours to current node
      for(int neighbour = 0; neighbour < matrix[current].length; neighbour++){
          if (matrix[current][neighbour] != 0) {
            if (!visited[neighbour]) {
              // Add next neighbour to the queue
              q.add(neighbour);
              visited[neighbour]=true;

              ArrayList<Integer> new_path = new ArrayList<Integer>();
              new_path.addAll(path);
              new_path.add(neighbour);
              queue.add(new_path);
              //System.out.println(queue.peek()); // Printing error
            }
          }
      }
    if (visited[t]) {
        break;
      }
    }
    if (visited[t]) {
      path = queue.getLast();
      return path;
    }
    else {
      return new ArrayList<Integer>();
    }
  }

  static void print_matrix(int matrix[][]) {
    for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
    }
  }

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    v = io.getInt();
    s = io.getInt();
    t = io.getInt();
    e = io.getInt();
    int temp1 = v +1;
    capacityMatrix = new int[temp1][temp1];
    restMatrix = new int[temp1][temp1];
    flowMatrix = new int[temp1][temp1];
    adjacencyMatrix = new int[temp1][temp1];
    for(int i = 0; i<e;i++){
      int a = io.getInt();
      int b = io.getInt();
      int c = io.getInt();
      capacityMatrix[a][b]=c;
      adjacencyMatrix[a][b]=1;
      flowMatrix[a][b]=0;
      flowMatrix[b][a]=0;
      restMatrix[a][b]=capacityMatrix[a][b];
      restMatrix[b][a]=capacityMatrix[b][a];
    }
    print_matrix(flowMatrix);
    //System.out.println(BFS(matrix, 0, 5));
    //static int capacityMatrix[][];
    //static int flowMatrix[][];
    //static int restMatrix[][];

    while((temp = BFS(adjacencyMatrix, s, t)).size() != 0){
      System.out.println("temp: "+temp);
      int minCap = capacityMatrix[temp.get(1)][temp.get(2)];
      for(int i = 3; i < temp.size();i++){
        int a = temp.get(i);
        int b = temp.get(i+1);
        int cap = capacityMatrix[a][b];
        if(cap < minCap){
          minCap = cap;
        }
      }
      for(int i = 0; i < temp.size()-1;i++){
        int a = temp.get(i);
        int b = temp.get(i+1);
        flowMatrix[a][b] = flowMatrix[a][b]+minCap;
        flowMatrix[b][a] = -flowMatrix[a][b];
        restMatrix[a][b] = capacityMatrix[a][b]-flowMatrix[a][b];
        restMatrix[b][a] = capacityMatrix[b][a] - flowMatrix[b][a];
      }
      adjacencyMatrix = restMatrix;
      System.out.println("--------------------------------------------------");
      print_matrix(restMatrix);
    };

    /*
    int minCap = capacityMatrix[temp.get(1)][temp.get(2)];
    for(int i = 3; i < temp.length-1;i++){
      int a = temp[i];
      int b = temp[i+1];
      int cap = capacityMatrix[a][b];
      if(cap < minCap){
        minCap = cap;
      }
    }
    for(int i = 1; i < temp.length-1;i++){
      int a = temp[i];
      int b = temp[i+1];
      flowMatrix[a][b] = flowMatrix[a][b]+minCap;
      flowMatrix[b][a] = -flowMatrix[a][b];
      restMatrix[a][b] = capacityMatrix[a][b]-flowMatrix[a][b];
      restMatrix[b][a] = capacityMatrix[b][a] - flowMatrix[b][a];
    }
    */



  }
}
