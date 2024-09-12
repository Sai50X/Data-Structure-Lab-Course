import java.util.*;
import java.io.*;

public class WordLadder {

   private Queue<Stack<String>> list;
   private Set<String> dictionary, used;
   private String goal;
   private String start;
   private Stack<String> answer;

   public WordLadder () throws FileNotFoundException {
      list = new LinkedList<>();
      dictionary = new HashSet<>();
      used = new HashSet<>();
      goal = "";
      Scanner input = null;
      answer = new Stack<>();
      input = new Scanner(new File("dictionary.txt"));
      while (input.hasNext()) {
         dictionary.add(input.nextLine().toLowerCase());
      }
   }
   
   public void setWords (String one, String two) {
      list = new LinkedList<>();
      used = new HashSet<>();
      start = one;
      goal = two;
      answer = new Stack<>();
      Stack<String> primary = new Stack<>();
      primary.push(one);
      list.offer(primary);
      
   }
 
    public void getSimilar(String s) {
     while (!list.isEmpty()) {
        Stack<String> temp = list.poll();
        if (goal.equals(temp.peek())) {
           answer = temp;
           return;
        }
        used.add(temp.peek());
        String check = temp.peek();
        char[] word = temp.peek().toCharArray();
        
         String tempWord;
        for (int i = 0; i < check.length(); i++) {
               String one = check.substring(0, i);
               String two = check.substring(i+1); 
               for (char ch = 'a'; ch <= 'z'; ch++) {
                  tempWord = one + ch + two;
                  if (dictionary.contains(tempWord) && (used.contains(tempWord) == false)) {
                     used.add(tempWord);
                     Stack<String> stackCopy = (Stack<String>) temp.clone();
                     stackCopy.push(tempWord);                         
                     list.offer(stackCopy);
                  } 
               }
            } 
      }        
   }         
   
   public String toString() {
      if (dictionary.contains(start) && dictionary.contains(goal) && start.length()==goal.length()) {
         getSimilar(start);
      } else {
         return "No ladder found between " + start + " and " + goal;
      }
      if (!answer.isEmpty()) {
      String s = "Found a ladder! >>> [";
         Stack<String> forReverse = new Stack<>();
         while (!answer.isEmpty()) {
            forReverse.push(answer.pop());
         }
         s += forReverse.pop();
         while (!forReverse.isEmpty()) {
            s += ", " + forReverse.pop();
         }
         s += "]";
         return s;
      } else {
         return "No ladder found between " + start + " and " + goal;
      }
   }
   

}