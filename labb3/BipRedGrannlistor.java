public class BipRed {
    Kattio io;
    MaxFlow mf;
    int adjacencyMatrix[][];

    int x;
    int y;
    int e;
    int matrixSize;
    LinkedList<ArrayList<Integer>> readBipartiteGraph() {
      // Läs antal hörn och kanter
      LinkedList<ArrayList<Integer>> adjList = new LinkedList<ArrayList<Integer>>();
      ArrayList<Integer> inner = new ArrayList<Integer>();
    	x = io.getInt();
    	y = io.getInt();
    	e = io.getInt();
      int totalVertex = x + y + 2;
      for(int i = 0; i<totalVertex;i++){
        adjList.add(new ArrayList<Integer>());
      }


      matrixSize = x+y+2;
      adjacencyMatrix = new int[matrixSize][matrixSize];
    	// Läs in kanterna
    	for (int i = 0; i < e; i++) {
    	    int a = io.getInt();
    	    int b = io.getInt();
          adjList.get(a).add(b);
          adjacencyMatrix[a][b] = 1;
    	}

      for ( int fromSourceToX = 1; fromSourceToX <= x; fromSourceToX++ )
        adjList.get(0).add(fromSourceToX);
      for ( int fromYtoSink = x+1; fromYtoSink < totalVertex-1; fromYtoSink++ )
        adjList.get(fromYtoSink).add(totalVertex-1);
      System.out.println(adjList.get(2));
      return adjList;

    }
