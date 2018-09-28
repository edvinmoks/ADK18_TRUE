/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

  public static List<String> readWordList(BufferedReader input) throws IOException {
    LinkedList<String> list = new LinkedList<String>();
    while (true) {
      String s = input.readLine();
      if (s.equals("#"))
        break;
      list.add(s);
    }
    return list;
  }
	// Set up the matrix
	public static void setup(int matrix[][]) {
			matrix[0][0] = 0;
			for( int i = 1; i < 40; i++ ) {
				matrix[0][i] = i;
				matrix[i][0] = i;
			}
	}

  public static void main(String args[]) throws IOException {
		int matrix[][] = new int[40][40]; // The only matrix we calculate
		setup(matrix); // Trying to set up the matrix once
    //    long t1 = System.currentTimeMillis();
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
    // Säkrast att specificera att UTF-8 ska användas, för vissa system har annan
    // standardinställning för teckenkodningen.
    List<String> wordList = readWordList(stdin); // Read in the dictionary
    String word;
    while ((word = stdin.readLine()) != null) { // Read in every test case
      ClosestWords closestWords = new ClosestWords(word, wordList, matrix);
      System.out.print(word + " (" + closestWords.getMinDistance() + ")");
      for (String w : closestWords.getClosestWords()) // Print out every word from the list
        System.out.print(" " + w);
      System.out.println();
    }
    //    long tottime = (System.currentTimeMillis() - t1);
    //    System.out.println("CPU time: " + tottime + " ms");

  }
}
