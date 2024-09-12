import java.util.*;

public class Runner {
   public static void main(String args[]) {
      GameTree tree = new GameTree("zelda.txt");
      System.out.print(tree.toString());
   }
}