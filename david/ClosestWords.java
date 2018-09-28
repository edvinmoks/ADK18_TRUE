/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;
	int matrix[][];
  int closestDistance = -1;

	int min(int w1len, int w2len) {
		if ( w1len < w2len )
				return w1len;
		else
				return w2len;
	}
	/*
	 * Calculate the number of common characters between two words
	 */
	int pDistance (String w1, String w2) {
			int p = 0;
			int min = min(w1.length(),w2.length());
			//Check where character is different 
			for ( int i = 0; i < min; i++ ) {
				if ( w1.charAt(i) != w2.charAt(i) )
						break;
				p++;
			}
			return p;
	}

  int partDist( String w1, String w2, int w1len, int w2len, int start) {
			int addLetter, deleteLetter, res, cost;
			// Calculation
			for ( int i = start+1; i <= w1len; i++ ) { // Start from start
				for ( int j = 1; j <= w2len; j++ ) {
					if ( w1.charAt(i-1) == w2.charAt(j-1) )
						cost = 0;
					else
						cost = 1;
					res = matrix[i-1][j-1] + cost;
					addLetter = matrix[i][j-1] + 1;
					deleteLetter = matrix[i-1][j] + 1;
					if ( addLetter < res ) { matrix[i][j] = addLetter; } 
					else if ( deleteLetter < res ) { matrix[i][j] = deleteLetter; } 
					else { matrix[i][j] = res; }
				}
			}
			return matrix[w1len][w2len];
  }

  int Distance(String w1, String w2, int start) {
    return partDist(w1, w2, w1.length(), w2.length(), start);
  }

  public ClosestWords(String w, List<String> wordList, int matrix[][]) {
		this.matrix = matrix; // Recieving a matrix.
		String prev = ""; // The previous word of Ordlistan
    for (String s : wordList) {
			int p = pDistance(prev, s); // Check the p distance of two words from Ordlista
			prev = s; // Set the current word to previous because we want to keep it for next iter.
      int dist = Distance(s, w, p); // Starting calculating the at row p in partDist
      // System.out.println("d(" + w + "," + s + ")=" + dist);
      if (dist < closestDistance || closestDistance == -1) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      }
      else if (dist == closestDistance)
        closestWords.add(s);
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
