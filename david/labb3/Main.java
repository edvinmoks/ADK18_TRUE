import java.util.*;
public class Main {
  public static void main(String[] args) {

    //BipRed reader = new BipRed();
    Kattio io = new Kattio(System.in, System.out);
    int vertices = io.getInt();
    LinkedList<Integer>[] graph = new LinkedList[vertices];
    ArrayList<Edge> edges = new ArrayList<>();
    int source = io.getInt();
    int sink = io.getInt() - 1;
    int numberOfEdges = io.getInt();
    System.out.println(sink);
    for ( int i = 0; i < vertices; i++ ) {
      graph[i] = new LinkedList<>();
    }
    for ( int j = 0; j < numberOfEdges; j++ ) {
      int from = io.getInt();
      int to = io.getInt();
      int cap = io.getInt();
      graph[from].add(to);
      Edge opp = new Edge(to, from, 0, cap, null);
      Edge current = new Edge(from, to, 0, cap, opp);
      opp.opposite = current;
      edges.add(current);
      edges.add(opp);
    }
    //System.out.println(graph.length);
    int index = 0;
    // for ( LinkedList<Integer> node : graph ) {
    //   Iterator it = node.iterator();
    //   while ( it.hasNext() ) {
    //   System.out.println(index + " " + it.next());
    //   }
    //   index++;
    // }
    MaxFlow2 flowMachine = new MaxFlow2(graph, source, sink, numberOfEdges, edges);
    System.out.println(flowMachine.BFS(graph, source, sink));
    //ArrayList<Edge> flowEdges = flowMachine.calculateMaxFlow();
    //// System.out.println(flowEdges.size());
    //for ( Edge e : flowEdges ) {
    //  System.out.println(e.from + " " + e.to + " " + e.flow);
    //}
  }
}
