import java.util.*;
import java.io.*;

public class Maze {

   private Square[][] grid;
   private Square start;
   private Square end;
   private int rows;
   private int columns;
   
   public Maze(){
      
   }
   
   public boolean loadMaze (String fileName) {
      Scanner input = null;
      try {
          input = new Scanner(new File(fileName));
         
      } catch (FileNotFoundException e) {
         System.out.println("File not found!");
         return false;
      }
      rows = input.nextInt();
         columns = input.nextInt();
         grid = new Square[rows][columns];
         for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
               int placeh = input.nextInt();
               grid[r][c] = new Square(r,c,placeh);
               if (placeh == 2) {
                  start = grid[r][c];
               }
               if(placeh == 3) {
                  end = grid[r][c];
               }
         }
      }
      return true;
   }
   
   public List<Square> getNeighbors(Square s) {
      List<Square> list = new ArrayList<>();
      if (s.getRow()-1>=0) {
         list.add(grid[s.getRow()-1][s.getCol()]);
      }
      if (s.getCol()+1<grid[0].length) {
         list.add(grid[s.getRow()][s.getCol()+1]);
      }
      if (s.getRow()+1<grid.length) {
         list.add(grid[s.getRow()+1][s.getCol()]);
      }
      if (s.getCol()-1>=0) {
         list.add(grid[s.getRow()][s.getCol()-1]);
      }
      
      return list;
   }
   
   public Square getStart() {
      return start;
   }
   
   public Square getExit() {
      return end;
   }
   
   public void reset() {
       for (int r = 0; r < rows; r++) {
            for (int c = 0; c< columns; c++) {
               grid[r][c].reset();
            }
       }
   }
   
   public String toString() {
      String s = "";
      for (int r = 0; r < rows; r++) {
            for (int c = 0; c< columns; c++) {
               s += grid[r][c].toString() + " ";
            }
            s += "\n";
       }
       return s;
   }
   
}