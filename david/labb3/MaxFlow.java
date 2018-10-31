import java.util.*;

public class MaxFlow{
    private int adjacencyMatrix[][];
    private int capacityMatrix[][];
    private int flowMatrix[][];
    private int restMatrix[][];
    private int sink;
    private int source;
    private int matrixSize;
    private int numberOfEdges;

    public MaxFlow(int matrix[][], int source, int sink, int edges) {
      this.adjacencyMatrix = matrix;
      this.matrixSize = matrix.length;
      this.source = source;
      this.sink = sink;
      numberOfEdges = edges;
      capacityMatrix = adjacencyMatrix;
      restMatrix = new int[matrixSize][matrixSize];
      flowMatrix = new int[matrixSize][matrixSize];
      //adjacencyMatrix = new int[matrixSize][matrixSize];
      for(int a = 0; a < matrixSize; a++){
        for( int b = 0; b < matrixSize; b++ ){
          flowMatrix[a][b]=0;
          flowMatrix[b][a]=0;
          restMatrix[a][b]=capacityMatrix[a][b];
          restMatrix[b][a]=capacityMatrix[b][a];
        }
      }
      System.out.println("Creation was successful."); // Error message
    }

    public ArrayList<Integer> BFS(int matrix[][], int s, int t){
      LinkedList<ArrayList<Integer>> queue = new LinkedList<ArrayList<Integer>>();
      ArrayList<Integer> path;

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

        path = queue.pop();

        // Iterate through neighbours to current node
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

    public void print_matrix(String matrix) {
      if ( matrix.equals("rest") ) {
        for (int i = 0; i < matrixSize; i++) {
          for (int j = 0; j < matrixSize; j++) {
              System.out.print(restMatrix[i][j] + " ");
        }
        System.out.println();
        }
      } else if( matrix.equals("flow") ){
        for (int i = 0; i < matrixSize; i++) {
          for (int j = 0; j < matrixSize; j++) {
              System.out.print(flowMatrix[i][j] + " ");
        }
        System.out.println();
        }
      } else if ( matrix.equals("cap") ){
        for (int i = 0; i < matrixSize; i++) {
          for (int j = 0; j < matrixSize; j++) {
              System.out.print(capacityMatrix[i][j] + " ");
        }
        System.out.println();
        }
      } else {
        for (int i = 0; i < matrixSize; i++) {
          for (int j = 0; j < matrixSize; j++) {
              System.out.print(adjacencyMatrix[i][j] + " ");
        }
        System.out.println();
        }
      }
    }

    public int[][] calculateMaxFlow() {
      ArrayList<Integer> walk = new ArrayList<Integer>();
      while((walk = BFS(adjacencyMatrix, source, sink)).size() != 0){

        int minCap = capacityMatrix[walk.get(0)][walk.get(1)];
        for(int i = 1; i < walk.size() - 1;i++){
          int a = walk.get(i);
          int b = walk.get(i+1);
          int cap = restMatrix[a][b];
          if(cap < minCap){
            minCap = cap;
          }
        }

        for(int i = 0; i < walk.size()-1;i++){
          int a = walk.get(i);
          int b = walk.get(i+1);
          flowMatrix[a][b] = flowMatrix[a][b]+minCap;
          flowMatrix[b][a] = -flowMatrix[a][b];
          restMatrix[a][b] = capacityMatrix[a][b]-flowMatrix[a][b];
          restMatrix[b][a] = capacityMatrix[b][a] - flowMatrix[b][a];
        }
        adjacencyMatrix = restMatrix;
      }
      return flowMatrix;
    }

    public void printout_maxflow() {
      int maxFlow = 0;
      for ( int i = 0; i < flowMatrix.length; i++ ) {
        if (flowMatrix[flowMatrix.length-1][i] != 0)
          maxFlow = maxFlow + flowMatrix[flowMatrix.length - 1][i];
      }
      maxFlow = maxFlow * -1;
      System.out.println(matrixSize); // Hard coded
      source = source + 1;
      sink = sink + 1;
      System.out.println(source + " " + sink + " " + maxFlow);
      System.out.println(numberOfEdges); // Hard coded
      for ( int i = 0; i < flowMatrix.length; i++ ) {
        for ( int j = 0; j < flowMatrix.length; j++ ) {
          if ( flowMatrix[i][j] > 0 ){
              int flow = flowMatrix[i][j];
              int from = i + 1;
              int to = j + 1;
              System.out.println(from + " " + to + " " + flow);
          }
        }
      }
    }
}
