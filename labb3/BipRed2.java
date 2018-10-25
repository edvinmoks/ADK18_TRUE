/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */

public class BipRed2 {
    Kattio io;
    int adjacencyMatrix[][];
    int x;
    int y;
    int e;
    void readBipartiteGraph() {
      // Läs antal hörn och kanter
    	x = io.getInt();
    	y = io.getInt();
    	e = io.getInt();
      adjacencyMatrix = new int[2*e][2*e];
    	// Läs in kanterna
    	for (int i = 0; i < e; i++) {
    	    int a = io.getInt();
    	    int b = io.getInt();
          adjacencyMatrix[a][b] = 1;
    	}
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


    void readMaxFlowSolution() {
	// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
	// (Antal hörn, källa och sänka borde vara samma som  i grafen vi
	// skickade iväg)
	int v = io.getInt();
	int s = io.getInt();
	int t = io.getInt();
	int totflow = io.getInt();
	int e = io.getInt();
  io.println(x + " " + y);
  io.println(e);
	for (int i = 0; i < e; ++i) {
	    // Flöde f från a till b
	    int a = io.getInt();
	    int b = io.getInt();
	    int f = io.getInt();
      if(a != s && b != t){
        a--;
        b--;
        io.println(a + " " + b);
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


    BipRed2() {
	io = new Kattio(System.in, System.out);

	readBipartiteGraph();

	writeFlowGraph();

	readMaxFlowSolution();

	//writeBipMatchSolution();

	// debugutskrift
	//System.err.println("Bipred avslutar\n");

	// Kom ihåg att stänga ner Kattio-klassen
	io.close();
    }

    public static void main(String args[]) {
	new BipRed2();
    }
}
