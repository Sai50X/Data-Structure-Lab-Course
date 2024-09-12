import java.util.*;

public class Square {
   final static int EMPTY = 0;
   final static int WALL = 1;
   final static int START = 2;
   final static int END = 3;
   private int type;
   private int row; 
   private int col;
   private char status;
    final static char WORKING = 'o';
    final static char EXPLORED = '.';
    final static char ON_EXIT_PATH = 'X';
    final static char UNKNOWN = '_';
   
   public Square(int row, int col, int type) {
      this.row = row;
      this.col = col;
      this.type = type;
   }
   
   public boolean equals(Object obj) {
      Square compare = (Square) obj;
      if(this.row == compare.row && this.col == compare.col) {
         return true;
      }
      return false;
   }
   
   public int getRow() {
      return row;
   }
   
   public int getCol(){
      return col;
   }
   
   public int getType(){
      return type;
   }
   
   public char getStatus() {
      return status;
   }
   
   public void setStatus(char c) {
      status = c;
   }
   
   public void reset() {
      if (type == 0) {
         status = '_';
      }
   }
   
   public String toString() {
      if (type == 0) {
         return status+"";
      } else if (type == 1) {
         return "#";
      } else if (type == 2) {
         return "S";
      } else if (type == 3) {
         return "E";
      }
      return "_";
   }
   
}