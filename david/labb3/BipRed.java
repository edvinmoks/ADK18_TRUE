/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */

public class BipRed {
    Kattio io;
    MaxFlow mf;
    int adjacencyMatrix[][];
    int x;
    int y;
    int e;
    int matrixSize;
    int[][] readBipartiteGraph() {
      // Läs antal hörn och kanter
    	x = io.getInt();
    	y = io.getInt();
    	e = io.getInt();
      matrixSize = x+y+2;
      adjacencyMatrix = new int[matrixSize][matrixSize];
    	// Läs in kanterna
    	for (int i = 0; i < e; i++) {
    	    int a = io.getInt();
    	    int b = io.getInt();
          adjacencyMatrix[a][b] = 1;
    	}
      for ( int fromSourceToX = 0; fromSourceToX <= x; fromSourceToX++ )
        adjacencyMatrix[0][fromSourceToX] = 1;
      for ( int fromYtoSink = y; fromYtoSink < matrixSize; fromYtoSink++ )
        adjacencyMatrix[fromYtoSink][matrixSize - 1] = 1;
      return adjacencyMatrix;
    }



    void writeFlowGraph() {
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

    	// Debugutskrift
    	//System.err.println("Skickade iväg flödesgrafen");
    }


  void writeBipMatchSolution(int[][] matrix) {
  	// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
  	// (Antal hörn, källa och sänka borde vara samma som  i grafen vi
  	// skickade iväg)
    int numberOfEdges = 0;
    for ( int i = 1; i < matrixSize - 1; i++ ) {
      for ( int j = 1; j < matrixSize - 1; j++) {
        if ( matrix[i][j] > 0 ) {
            numberOfEdges++;
            break;
        }
      }
    }

    io.println(x + " " + y);
    io.println(numberOfEdges);

    for ( int i = 1; i < matrixSize - 1; i++ ) {
      for ( int j = 1; j < matrixSize - 1; j++) {
        if ( matrix[i][j] > 0) {
            io.println(i + " " + j);
            break;
        }
      }
    }
  }


    void writeBipMatchSolution() {
	int x = 17, y = 4711, maxMatch = 0;

	// Skriv ut antal hörn och storleken på matchningen
	io.println(x + " " + y);
	io.println(maxMatch);

	for (int i = 0; i < maxMatch; ++i) {
	    int a = 5, b = 2323;
	    // Kant mellan a och b ingår i vår matchningslösning
	    io.println(a + " " + b);
	}

    }


  BipRed() {
    	io = new Kattio(System.in, System.out);

    	int[][] matrix = readBipartiteGraph();

      mf = new MaxFlow(matrix, 0, matrixSize - 1, e + x + y);

      int flowM[][] = mf.calculateMaxFlow();
      //mf.print_matrix("rest");
      //mf.printout_maxflow();
    	writeBipMatchSolution(flowM);

    	//readMaxFlowSolution();

    	//writeBipMatchSolution();

    	// debugutskrift
    	//System.err.println("Bipred avslutar\n");

    	// Kom ihåg att stänga ner Kattio-klassen
    	io.close();
  }
}
