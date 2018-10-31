/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */
import java.util.*;

public class BipRed {
    Kattio io;
    MaxFlow mf;
    LinkedList<Integer>[] adjacencyList;

    int x;
    int y;
    int e;
    int matrixSize;
    LinkedList<Integer>[] readBipartiteGraph() {
      // Läs antal hörn och kanter

      ArrayList<Integer> inner = new ArrayList<Integer>();
    	x = io.getInt();
    	y = io.getInt();
    	e = io.getInt();
      adjacencyList = new LinkedList[x+y+2];
      int totalVertex = x + y + 2;
      for(int i = 0; i < totalVertex; i++){
        adjacencyList[i] = new LinkedList<Integer>();
      }
    	// Läs in kanterna
    	for (int i = 0; i < e; i++) {
    	    int a = io.getInt();
    	    int b = io.getInt();
          adjacencyList[a].add(b);
    	}

      for ( int fromSourceToX = 1; fromSourceToX <= x; fromSourceToX++ )
        adjacencyList[0].add(fromSourceToX);
      for ( int fromYtoSink = x+1; fromYtoSink < totalVertex-1; fromYtoSink++ )
        adjacencyList[fromYtoSink].add(totalVertex-1);
      //System.out.println(adjList.get(2));
      return adjacencyList;

    }

    void writeFlowGraph() {
/*
    	int v = adjacencyMatrix.length-1, s = 0, t = v - 1;

    	// Skriv ut antal hörn och kanter samt källa och sänka
      int temp = t + 1;
    	io.println(v);
    	io.println(s + 1 + " " + temp);
    	io.println(x+y+e);
      // Create the edges between source and X nodes and Y nodes to sink.
      for (int i = 1; i <= x; i++) {
        adjacencyMatrix[s][i] = 1;
      }
      for (int i = y; i < t; i++) {
        adjacencyMatrix[i][t] = 1;
      }
      // Iterate through the whole array to find where all the edges are. Indices denote nodes.
      // We add 1 to i and j so to match the node value.
      int xNode = 0;
      int yNode = 0;
    	for (int i = 0; i < adjacencyMatrix.length; i++) {
        for (int j = 0; j < adjacencyMatrix.length; j++) {
            if (adjacencyMatrix[i][j] == 1) {
              xNode = i + 1;
              yNode = j + 1;
              io.println(xNode + " " + yNode + " " + "1");
            }
        }

    	}

    	// Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
    	io.flush();
*/
    	// Debugutskrift
    	//System.err.println("Skickade iväg flödesgrafen");
    }

  void writeBipMatchSolution(int[][] matrix, int flow) {
  	// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
  	// (Antal hörn, källa och sänka borde vara samma som  i grafen vi
  	// skickade iväg)

    io.println(x + " " + y);
    io.println(flow);

    for ( int i = 1; i < x + y + 1; i++ ) {
      for ( int j = 1; j < x + y + 1; j++) {
        if ( matrix[i][j] > 0) {
            io.println(i + " " + j + " " + matrix[i][j]);
        }
      }
    }
  }

  void writeSolution(int flowMatrix[][], int source, int sink, int numberOfEdges) {

    int maxFlow = 0;
    for ( int i = 0; i < flowMatrix.length; i++ ) {
      if (flowMatrix[flowMatrix.length-1][i] != 0)
        maxFlow = maxFlow + flowMatrix[flowMatrix.length - 1][i];
    }
    maxFlow = maxFlow * -1;
    io.println(flowMatrix.length-1); // Hard coded
    source = source + 1;
    sink = sink + 1;
    io.println(source + " " + sink + " " + maxFlow);
    io.println(numberOfEdges); // Hard coded
    for ( int i = 0; i < flowMatrix.length; i++ ) {
      for ( int j = 0; j < flowMatrix.length; j++ ) {
        if ( flowMatrix[i][j] > 0 ){
            int flow = flowMatrix[i][j];
            int from = i + 1;
            int to = j + 1;
            io.println(from + " " + to + " " + flow);
        }
      }
    }
  }
/*
	// Skriv ut antal hörn och storleken på matchningen
	io.println(x + " " + y);
	io.println(maxMatch);

	for (int i = 0; i < maxMatch; ++i) {
	    int a = 5, b = 2323;
	    // Kant mellan a och b ingår i vår matchningslösning
	    io.println(a + " " + b);
	}
*/



  BipRed() {
    	io = new Kattio(System.in, System.out);

    	LinkedList<Integer>[] adjList = readBipartiteGraph();
      //System.out.println(adjList);
      mf = new MaxFlow(adjList, 0, x+y+1, e + x + y);

      int flowM[][] = mf.calculateMaxFlow();

      //mf.print_matrix("flow");
      writeBipMatchSolution(flowM, mf.getMaximumFlow());
    	//writeSolution(flowM, 0, x + y + 1, e + x + y);

    	//readMaxFlowSolution();

    	//writeBipMatchSolution();

    	// debugutskrift
    	//System.err.println("Bipred avslutar\n");

    	// Kom ihåg att stänga ner Kattio-klassen
    	io.close();
  }
}
