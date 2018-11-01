import java.util.*;
public class BipRed2 {
    Kattio io;
    LinkedList<Integer>[] graph;
    int x;
    int y;
    int e;
    int numberOfVertices;
    int numberOfEdges;
    ArrayList<Edge> flowEdges;
    int maxMatch = 0;
    int s, t;

    void readBipartiteGraph() {
    	x = io.getInt();
    	y = io.getInt();
    	e = io.getInt();
      numberOfVertices = x + y + 2;
      graph = new LinkedList[numberOfVertices];
      flowEdges = new ArrayList<>();
      for ( int i = 0; i < numberOfVertices; i++ ) {
        graph[i] = new LinkedList<>();
      }
    	for (int i = 0; i < e; i++) {
    	    int a = io.getInt();
    	    int b = io.getInt();
          graph[a].add(b);
          numberOfEdges++;
    	}
      // Connect x-nodes to source. The edge should have the direction source --> x.
      for ( int sourceToX = 1; sourceToX <= x; sourceToX++ ) {
        numberOfEdges++;
        graph[0].add(sourceToX);
      }

      // Connect y-nodes to sink. The edge should have the direction y --> sink
      for ( int yToSink = x + 1; yToSink < numberOfVertices - 1; yToSink++ ) {
        numberOfEdges++;
        graph[yToSink].add(numberOfVertices - 1);
      }
    }



    void writeFlowGraph() {

      io.println(numberOfVertices);
      int source = 1, sink = graph.length;
      io.println(source + " " + sink);
      io.println(numberOfEdges);

      // Iterate through the whole array to find where all the edges are. Indices denote nodes.
      // We add 1 to i and j so to match the node value.

      for ( int node = 0; node < numberOfVertices; node++ ) {
        Iterator it = graph[node].iterator();
        while ( it.hasNext() ) {
          int xNode = node + 1;
          int yNode = (int) it.next() + 1;
          io.println(xNode + " " + yNode + " " + 1); // Hardcoded capacity.
        }
      }

      io.flush();
    	// Debugutskrift
    }

    void readMaxFlowSolution() {
    	int v = io.getInt();
      s = io.getInt();
    	t = io.getInt();
    	int totflow = io.getInt();
    	int edge = io.getInt();
    	for (int i = 0; i < edge; ++i) {
    	    int a = io.getInt();
    	    int b = io.getInt();
    	    int f = io.getInt();
          Edge opp = new Edge(b, a, f, 0, null);
          Edge current = new Edge(a, b, f, 0, opp);
          opp.opposite = current;
    	    flowEdges.add(current);
          flowEdges.add(opp);
    	}
      //io.println(flowEdges.size());
    }

    void writeBipMatchSolution() {
      int maxMatch = 0;
      for ( Edge e : flowEdges ) {
        if ( e.from != 1 && e.to != graph.length && e.to > e.from ) {
          maxMatch++;
        }
      }

    	io.println(x + " " + y);
    	io.println(maxMatch);
      for ( Edge e : flowEdges ) {
        if ( e.from != 1 && e.to != graph.length && e.to > e.from ) {
          int a = e.from - 1;
          int b = e.to - 1;
          io.println(a + " " + b);
        }
      }

    }


    BipRed2() {
    	io = new Kattio(System.in, System.out);

    	readBipartiteGraph();

    	writeFlowGraph();

      readMaxFlowSolution();

    	writeBipMatchSolution();

    	// debugutskrift
    	//System.err.println("Bipred avslutar\n");

    	io.close();
    }

    public static void main(String args[]) {
      new BipRed2();
    }
}
