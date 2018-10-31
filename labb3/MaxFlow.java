import java.util.*;

public class MaxFlow{
    LinkedList<Integer>[] adjacencyGraph;
    int[][] capacityGraph;
    int[][] flowGraph;
    int[][] restGraph;
    private int sink;
    private int source;
    private int numberOfNodes;
    private int numberOfEdges;

    public MaxFlow(LinkedList<Integer>[] graph, int source, int sink, int edges) {
      this.adjacencyGraph = graph;
      this.numberOfNodes = graph.length;
      this.source = source;
      this.sink = sink;
      numberOfEdges = edges;
      //System.out.println(capacityGraph.length); // Error message
      restGraph = new int[numberOfNodes][numberOfNodes];
      flowGraph = new int[numberOfNodes][numberOfNodes];
      capacityGraph = new int[numberOfNodes][numberOfNodes];
      //adjacencyGraph = new int[numberOfNodes][numberOfNodes];
      int index = 0;
      for ( LinkedList<Integer> node : adjacencyGraph ) {
        Iterator it = node.iterator();
        //System.out.println(node); // Error
        while ( it.hasNext() ) {
            int element = (int) it.next();
            restGraph[index][element] = 1;
            restGraph[element][index] = 1;
            capacityGraph[index][element] = 1;
        }
        index++;
      }
      //System.out.println("Creation was successful."); // Error message
    }

    public ArrayList<Integer> BFS(LinkedList<Integer>[] graph, int s, int t){
      LinkedList<ArrayList<Integer>> queue = new LinkedList<ArrayList<Integer>>();
      ArrayList<Integer> path;

      LinkedList<Integer> q = new LinkedList<Integer>();
      boolean visited[] = new boolean[graph.length]; // Nodes we have visited
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
          Iterator it = graph[current].iterator();
          while ( it.hasNext() ) {
            int neighbour = (int) it.next();
            if ( !visited[neighbour] ) {
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

    public int[][] calculateMaxFlow() {
      ArrayList<Integer> walk = new ArrayList<Integer>();
      while((walk = BFS(adjacencyGraph, source, sink)).size() != 0){
        //System.out.println(walk); // Error message
        int minCap = capacityGraph[walk.get(0)][walk.get(1)];
        for(int i = 1; i < walk.size() - 1;i++){
          int a = walk.get(i);
          int b = walk.get(i+1);
          int cap = restGraph[a][b];
          if(cap < minCap){
            minCap = cap;
          }
        }

        for(int i = 0; i < walk.size()-1;i++){
          int a = walk.get(i);
          int b = walk.get(i+1);
          flowGraph[a][b] = flowGraph[a][b]+minCap;
          flowGraph[b][a] = -flowGraph[a][b];
          restGraph[a][b] = capacityGraph[a][b]-flowGraph[a][b];
          restGraph[b][a] = capacityGraph[b][a] - flowGraph[b][a];
          adjacencyGraph[a].remove((Object) b);
          adjacencyGraph[b].add(a);
        }
        //adjacencyGraph = restGraph;
      }
      return flowGraph;
    }

    public void print_matrix(String matrix) {
      if ( matrix.equals("rest") ) {
        for (int i = 0; i < numberOfNodes; i++) {
          for (int j = 0; j < numberOfNodes; j++) {
              System.out.print(restGraph[i][j] + " ");
        }
        System.out.println();
        }
      } else if( matrix.equals("flow") ){
        for (int i = 0; i < numberOfNodes; i++) {
          for (int j = 0; j < numberOfNodes; j++) {
              System.out.print(flowGraph[i][j] + " ");
        }
        System.out.println();
        }
      } else {
        for (int i = 0; i < numberOfNodes; i++) {
          for (int j = 0; j < numberOfNodes; j++) {
              System.out.print(capacityGraph[i][j] + " ");
        }
        System.out.println();
        }
      }
    }

/*
flowGraph[a].add( flowGraph[a].get(b)+minCap );
flowGraph[b].add( -1 * (flowGraph[a].get(b)) );
restGraph[a].add( capacityGraph[a].get(b)-flowGraph[a].get(b) );
restGraph[b].add( capacityGraph[b].get(a) - flowGraph[b].get(a) );
*/
    public void printout_maxflow() {
      int maxFlow = 0;
      for ( int i = 0; i < flowGraph.length; i++ ) {
        if (flowGraph[flowGraph.length-1][i] != 0)
          maxFlow = maxFlow + flowGraph[flowGraph.length - 1][i];
      }
      maxFlow = maxFlow * -1;
      System.out.println(numberOfNodes); // Hard coded
      source = source + 1;
      sink = sink + 1;
      System.out.println(source + " " + sink + " " + maxFlow);
      System.out.println(numberOfEdges); // Hard coded
      for ( int i = 0; i < flowGraph.length; i++ ) {
        for ( int j = 0; j < flowGraph.length; j++ ) {
          if ( flowGraph[i][j] > 0 ){
              int flow = flowGraph[i][j];
              int from = i + 1;
              int to = j + 1;
              System.out.println(from + " " + to + " " + flow);
          }
        }
      }
    }

}
