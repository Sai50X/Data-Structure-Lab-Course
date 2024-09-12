import java.util.*;

public class MazeSolverStack extends MazeSolver{

   private MyStack worklist;

   public MazeSolverStack(Maze maze) {
      super(maze);
   }
   @Override
   public void add(Square s) 
   {
      worklist.push(s);
   }
   @Override
   public boolean isEmpty() {
      return worklist.isEmpty();
   }
   @Override
   public void makeEmpty() {
      worklist = new MyStack();
   }
   @Override
   public Square next () {
      return worklist.pop();
   }

   
}