import java.util.*;

public class Runner {

   public static void main (String args[]) {
      StackProbs problem = new StackProbs();
      int[] nums = {7, 23, -7, 0, 22, -8, 4, 5};
      System.out.print(problem.bracketBalance("([()[]()])()"));
   } 
   
   public static Stack<Integer> makeStack(int[] nums) {
	   Stack<Integer> stack = new Stack<>();
	   for (int num : nums)
		   stack.push(num);
	   return stack;
   }

}