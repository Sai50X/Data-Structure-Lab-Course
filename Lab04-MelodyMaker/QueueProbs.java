import java.util.*;

public class QueueProbs {

   public QueueProbs(){
   
   }
   
   public Queue<Integer> evenFirst(Queue<Integer> nums) {
      Queue<Integer> answer = nums;
      Queue<Integer> back = new LinkedList<>();
      for (int i = 0; i < nums.size(); i++) {
         int temp = answer.poll();
         if(temp%2==0) {
            answer.offer(temp);
         } else {
            back.offer(temp);
         }
      }
      while (!back.isEmpty()) {
         answer.offer(back.poll());
      }
      return answer;
   }
   
   public Stack<Integer> getPrimes(int n) {
      Queue<Integer> answer = new LinkedList<>();
      for (int i = 2; i <= n; i++) {
         answer.offer(i);
      }
      Stack<Integer> primes = new Stack<>();
      Queue<Integer> answer2 = new LinkedList<>();
      while (!answer.isEmpty()) {
         primes.push(answer.peek());
         while (!answer.isEmpty()) {
            int temp = answer.poll();
            if (temp%primes.peek()!=0) {
               answer2.offer(temp);
            }
         }
         answer = answer2;
         answer2 = new LinkedList<>();
      }
      return primes;
   }  

}