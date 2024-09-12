import java.util.*;

abstract class MazeSolver {
   
   private String solveStatus = "";
   private Maze m;
   
   public MazeSolver (Maze maze) {
      m = maze;
      makeEmpty();
      add(m.getStart());
   }
   
   abstract void makeEmpty(); 
   
   
   
   abstract boolean isEmpty();
   
   
   
   abstract void add(Square s); 
   
   
   
   abstract Square next(); 
      
   
   
   public String getPath() {
      return solveStatus;
   }
   
   public boolean isSolved() {
      if (solveStatus.equals("solved") || solveStatus.equals("unsolvable")) {
         return true;
      }
      return false;
   }
   
   public void step() {
      
         
         if (isEmpty()) {
            solveStatus = "unsolvable";
            return;
         } else {
           Square check = next();
           List<Square> check1 = m.getNeighbors(check);
           if (check.getType() == 3) {
               solveStatus = "solved";
               check.setStatus(Square.ON_EXIT_PATH);
               return;
           } else {
               solveStatus = "not solved yet";
               if(check.getType()!=2) {
                  check.setStatus(Square.EXPLORED);
               }
               while (!check1.isEmpty()) {
                  if (check1.get(0).getType()!=1) {
                     if (check1.get(0).getType()==3) {
                        add(check1.get(0));
                        check1.get(0).setStatus(Square.ON_EXIT_PATH);
                        check.setStatus(Square.EXPLORED);
                        check1.remove(0);
                        solveStatus = "solved";
                        return;
                     } else if (check1.get(0).getStatus() == Square.UNKNOWN) {
                        add(check1.get(0));
                        check1.get(0).setStatus(Square.WORKING);
                        check.setStatus(Square.EXPLORED);
                     }
                  }
                  check1.remove(0);
               }
           }      
               
          }
     }
     
      public void solve() {
         while (!isSolved()) {
            step();
         }
      }
}