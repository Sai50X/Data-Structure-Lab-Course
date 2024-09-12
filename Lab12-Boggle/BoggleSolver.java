import java.util.*;
import java.io.*;

public class BoggleSolver
{
   private Set<String> valid;
   private Set<String> dictionary;
   
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
   public BoggleSolver(String dictionaryName)  {
      dictionary = new HashSet<>();
      Scanner input = null;
      try {
         input = new Scanner(new File(dictionaryName));
         while (input.hasNext()) {
            dictionary.add(input.nextLine());
         }
      } catch (IOException e) {
         System.out.println("File not found.");
      }
      
      
      
      valid = new HashSet<String>();
       
              
   }

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
   public Iterable<String> getAllValidWords(BoggleBoard board)
   {
      valid = new HashSet<String>(); 
      for (int r = 0; r < board.rows();  r++) {
         for (int c = 0; c < board.cols(); c++) {
            boolean[][] visited = new boolean[board.rows()][board.cols()];
            helper(board, r, c, "", visited);
                        
         }
      }
      return valid;
   }
   
   public void helper (BoggleBoard board, int r, int c, String word, boolean[][] visited) {
      if (visited[r][c] == true) { //checking if already visited
         return;
      }
      if(board.getLetter(r, c)=='Q'){
         word+= "QU";} // adding new letter/s to words
      else{
         word+= board.getLetter(r, c); // adding new letter/s to words
      }
      if (dictionary.contains(word.toUpperCase()) && !valid.contains(word)) { // adding only valid words
         valid.add(word);
      }
      visited[r][c] = true; //marking current spot as visited
      if (r+1 < board.rows()) {
         helper(board, r+1,c, word, visited);
      }
      if (r-1 >= 0) {
         helper(board, r-1,c, word, visited);
      }
      if (c+1 < board.cols()) {
         helper(board, r,c+1, word, visited);
      }
      if (c-1 >= 0) {
         helper(board, r,c-1, word, visited);
      }
      if (c-1 >= 0 && r-1 >= 0) {
         helper(board, r-1,c-1, word, visited);
      }
      if (c-1 >= 0 && r+1 < board.rows()) {
         helper(board, r+1,c-1, word, visited);
      }
      if (c+1 < board.cols() && r+1 < board.rows()) {
         helper(board, r+1,c+1, word, visited);
      }
      if (c+1 < board.cols() && r-1 >= 0) {
         helper(board, r-1,c+1, word, visited);
      }
      visited[r][c] = false;
      
   }

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
   public int scoreOf(String word)
   {
      int length = word.length();
      if (length <= 2) {
         return 0;
      } else if (word.length() <= 4) {
         return 1;
      } else if (word.length() == 5) {
         return 2;
      } else if (word.length() == 6) {
         return 3;
      } else if (word.length() == 7) {
         return 5;
      } else if (word.length() >= 8) {
         return 11;
      }
   
      return 0;
   }

   public static void main(String[] args) throws FileNotFoundException {
      System.out.println("WORKING");
   
      final String PATH   = "./data/";
      BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
      BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");
   
      int totalPoints = 0;
   
      for (String s : solver.getAllValidWords(board)) {
         System.out.println(s + ", points = " + solver.scoreOf(s));
         totalPoints += solver.scoreOf(s);
      }
   
      System.out.println("Score = " + totalPoints); //should print 84
   
   	//new BoggleGame(4, 4);
   }

}
