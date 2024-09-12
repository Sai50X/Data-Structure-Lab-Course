import static java.lang.System.*;
import java.util.*;

public class RecursionPractice {

 

/**
*  Use this main method for running tests.
*/
   public static void main(String[] args) {
       countToBy(10, 2);
   }
   
   public static double sumReciprocals(int n){
      if (n == 0) {
         return 0;
      } else {
         return (1.0/n) + sumReciprocals(n-1);
      }
   }
   
   public static int productOfEvens(int n) {
      if (n == 1) {
         return 2;
      } else {
         return (n*2) * productOfEvens(n-1);
      }
   }
   
   public static void doubleUp(Stack<Integer> nums) {
      int num = nums.pop();
      if (nums.size()>0) {
         doubleUp(nums);
      }
      nums.push(num);
      nums.push(num);
   } 
   
   public static void countToBy(int n, int m) {
      int num = n;
      if (n-m>0) {
         countToBy(n-m,m);
      }
      if (n-m<=0) {
         System.out.print(num);
      } else {
         System.out.print(", " + num);
      }
      
   }
   
   public static int matchingDigits(int a, int b) {
      int num1 = a%10;
      int num2 = b%10;
      if ((a == 0 || b == 0) ) {
         if (num1 == num2) {
            return 1;
         } else {
            return 0;
         } 
      } else if (num1 == num2) {
         return 1 + matchingDigits(a/10, b/10);
      } else {
         return matchingDigits(a/10, b/10);
      }
   }
   
   public static void printThis(int n) {
      if (n == 1) {
         System.out.print("*");
      } else if (n == 2) {
         System.out.print("**");
      } else {
         System.out.print("<");
         printThis(n-2);
         System.out.print(">");
      }
   }
   
   public static void printNums2(int n) {
      if (n == 1) {
         System.out.print("1 ");
      } else if (n == 2) {
         System.out.print("1 1 ");
      } else {
         System.out.print((int)Math.ceil(n/2.0) + " ");
         printNums2(n-2);
         System.out.print((int)Math.ceil(n/2.0) + " ");
      }
   }


}