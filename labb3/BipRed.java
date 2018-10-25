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
    int[] edgeList;
    int x;
    int y;
    int e;
    void readBipartiteGraph() {
	// Läs antal hörn och kanter
	x = io.getInt();//info/adk18/labb3/exempelprogram
	y = io.getInt();
	e = io.getInt();

	edgeList = new int[2*e];
	// Läs in kanterna
	for (int i = 0; i < e*2; i+=2) {
	    int a = io.getInt();
	    int b = io.getInt();
      edgeList[i]=a+1;
      edgeList[i+1]=b+1;
	}
    }


    void writeFlowGraph() {
	int v = 23, s = 1, t = 2;
	v = x + y + 2;
  e = e + x + y;
  t = x + y + 2;

  int temp = 0;
	// Skriv ut antal hörn och kanter samt källa och sänka
	io.println(v);
	io.println(s + " " + t);
	io.println(e);
	for (int i = 0; i < edgeList.length; i+=2) {
    	    // Kant från a till b med kapacitet c
      if (edgeList[i] != temp)
        io.println(s + " " + edgeList[i] + " " + "1");
      temp = edgeList[i];
	}
  for (int i = 0; i < edgeList.length; i+=2) {
    io.println(edgeList[i] + " " + edgeList[i+1] + " " + "1");
  }
  temp = 0;
  for (int i = 1; i < edgeList.length; i+=2) {
    	    // Kant från a till b med kapacitet c
      if (edgeList[i] != temp)
        io.println(edgeList[i] + " " + t + " " + "1");
      temp = edgeList[i];
	}

	// Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
	io.flush();

	// Debugutskrift
//	System.err.println("Skickade iväg flödesgrafen");
    }


    void readMaxFlowSolution() {///info/adk18/labb3/exempelprogram
	// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
	// (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
	// skickade iväg)
	int v = io.getInt();
	int s = io.getInt();
	int t = io.getInt();
	int totflow = io.getInt();
	int e = io.getInt();

	for (int i = 0; i < e; ++i) {
	    // Flöde f från a till b
	    int a = io.getInt();
	    int b = io.getInt();
	    int f = io.getInt();//info/adk18/labb3/exempelprogram
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

	readBipartiteGraph();

	writeFlowGraph();

	readMaxFlowSolution();

	writeBipMatchSolution();

	// debugutskrift
//	System.err.println("Bipred avslutar\n");

	// Kom ihåg att stänga ner Kattio-klassen
	io.close();
    }

    public static void main(String args[]) {
	new BipRed();
    }
}
