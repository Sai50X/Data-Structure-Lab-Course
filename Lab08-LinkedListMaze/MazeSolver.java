public abstract class MazeSolver {

   private Maze maze;
   private String solutionStatus = "not yet solved";  

   public MazeSolver(Maze maze) { 
      this.maze = maze;         
   }

   public abstract void makeEmpty();

   public abstract boolean isEmpty();

   public abstract void add(Square s);

   public abstract Square next();

   public boolean isSolved() {
      return solutionStatus.equals("solved");
   }

   public void step() {
   //1
      if (isEmpty()) { 
         solutionStatus = "unsolvable";
         return;         
      }
         //2
      Square next = next();
      if (next.equals(maze.getExit())) {
         solutionStatus = "solved";
         return;
      }
      for (Square option : maze.getNeighbors(next)) 
         if (option.getType() != Square.WALL && option.getStatus() == '_') {
            option.setStatus('o');
            add(option);
            if (option.getPrevious() == null) {
               option.setPrevious(next);
            }
            
         }
      next.setStatus('.');               
   }

   public String getPath() {
      String answer = "";
      if (!isSolved()) {
         return "Maze is not solved yet!";
      }
      if (solutionStatus.equals("unsolvable")) {
         return "Maze is unsolvable!";
      } else {
         Square current = this.maze.getExit();
         answer += ("[" + current.getRow() +", " + current.getCol()+"]");
         while (current.getPrevious()!=null) {
            current = current.getPrevious();
            answer = ("[" + current.getRow() +", " + current.getCol()+"], ") + answer;
         }
      }
      return answer;
   }

   public void solve() {
      while (! isSolved())
         step();
   }

}