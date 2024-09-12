import java.util.*;

public class StackProbs {

   public StackProbs() {
   
   }
   
   public Stack<Integer> doubleUp(Stack<Integer> nums) {
      Stack<Integer> answer = new Stack<>();
      Stack<Integer> answerFR = new Stack<>();
      while (! nums.isEmpty()) {
         int num = nums.pop();
         answer.push(num);
         answer.push(num);
      }
      while(! answer.isEmpty()) {
         answerFR.push(answer.pop());
      }
      return answerFR;
   }
   
   public Stack<Integer> posAndNeg(Stack<Integer> nums) {
      Stack<Integer> answer = new Stack<>();
      ArrayList<Integer> pos = new ArrayList<>();
      ArrayList<Integer> neg = new ArrayList<>();
      while(nums.isEmpty()==false) {
         if(nums.peek()>=0) {
            pos.add(nums.pop());
         } else {
            neg.add(nums.pop());
         }
      }
      for ( int i = 0; i < neg.size(); i++) {
         answer.push(neg.get(i));
      }
      for ( int i = 0; i < pos.size(); i++) {
         answer.push(pos.get(i));
      }
      return answer;
   }
   
   public Stack<Integer> shiftByN(Stack<Integer> nums, int n) {
      Stack<Integer> answer = new Stack<>();
      Stack<Integer> temp = new Stack<>();
      while (nums.size()>n) {
         temp.push(nums.pop());
      }
      while (! temp.isEmpty()) {
         answer.push(temp.pop());
      }
      while (! nums.isEmpty()) {
         temp.push(nums.pop());
      }
      while (! temp.isEmpty()) {
         answer.push(temp.pop());
      }
      return answer;
   }
   
   public String reverseVowels(String str) {
      Stack<Character> vowels = new Stack<>();
      for ( int i = 0; i < str.length(); i++) {
         if (str.charAt(i)=='a'||str.charAt(i)=='e'||str.charAt(i)=='i'||str.charAt(i)=='o'||str.charAt(i)=='u') {
            vowels.push(str.charAt(i));
         }
      }
      char[] chars = str.toCharArray();
      for (int i = 0; i < chars.length; i ++) {
         if (str.charAt(i)=='a'||str.charAt(i)=='e'||str.charAt(i)=='i'||str.charAt(i)=='o'||str.charAt(i)=='u') {
            chars[i] = vowels.pop();
         }
      }
      String answer = "";
      for (int i = 0; i < chars.length; i ++) {
         answer+=chars[i];
      }
      return answer;
      
   }
   
   public boolean bracketBalance(String s) {
      Stack<Character> brackets = new Stack<>();
      
      for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '['|| s.charAt(i) =='('){
         brackets.push(s.charAt(i));
      } else if (s.charAt(i) ==']'|| s.charAt(i) ==')') {
         if(brackets.isEmpty()) {
            return false;
         }
         brackets.pop();
      }
      }
      
      if (brackets.isEmpty()) {
         return true;
      }
      return false;
   }
   
   
   
}