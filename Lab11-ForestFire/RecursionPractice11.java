import java.util.*;

public class RecursionPractice11 {
   public static void printBinary(int digits) {
      printBinaryHelper(digits, "");
   }
   
   private static void printBinaryHelper(int digits, String result) {
      if (result.length() == digits) {
         System.out.print(result + " ");
      } else {
         printBinaryHelper(digits, result + "0");
         printBinaryHelper(digits, result + "1");
      }
   }
   
   public static void climbStairs(int steps) {
      helperClimber(steps, "");
   }
   
   private static void helperClimber(int steps, String result) {
      if (steps<=0) {
         System.out.println(result.substring(0, result.length()-2));
      } else {
         helperClimber(steps-1, result + "1, ");
         if (steps - 2 >=0) {
            helperClimber(steps-2, result + "2, ");
         }
      }
   }
   
   public static void campsite(int x, int y) {
      helperCamp(x, y, "");
   }
   
   private static void helperCamp(int x, int y, String result) {
      if (x==0 && y == 0) {
         System.out.println(result);
      } else {
         if (x-1>=0) {
            helperCamp(x-1, y, result + "E ");
         }
         if (y-1>=0) {
            helperCamp(x, y-1, result + "N ");
         }
         if (x-1>=0 && y-1>=0) {
            helperCamp(x-1, y-1, result + "NE ");
         }
      }
   }
   
   public static int getMax(List<Integer> nums, int limit) {
      return helperMax(nums, limit, 0, 0);
   }
   
   private static int helperMax(List<Integer> nums, int limit, int sum, int index) {
      if (index >= nums.size()) {
         return sum;
      } else {
            int sum1 = helperMax(nums, limit, sum + nums.get(index), index + 1);
            int sum2 = helperMax(nums, limit, sum, index + 1);
            if (sum1 <= limit && sum2 <= limit) {
               if (sum1 > sum2) {
                  return sum1;
               } else {
                  return sum2;
               }
            } else {
               if (sum1 <= limit) {
                  return sum1;
               }
               if (sum2 <= limit) {
                  return sum2;
               }
            }
      }
      return 0;
   }
   
    public static int makeChange(int amount) {
      return makeChangeHelper(amount, 0, 25);
    }
    
    private static int makeChangeHelper(int amount, int sum, int largest) {
      if (sum > amount) {
         return 0;
      } else if (sum == amount) {
         return 1;
      }
      
         int total = 0;
         
         if (largest == 25) {
            total+= makeChangeHelper(amount, sum +25, 25);
         }
         if (largest >= 10) {
            total+= makeChangeHelper(amount, sum +10, 10);
         }
         if (largest >= 5) {
            total+= makeChangeHelper(amount, sum +5, 5);
         }
         total+= makeChangeHelper(amount, sum +1, 1);
         
         
         return total;
                  
      }
      
    public static void makeChangeAndPrint(int amount) {
      makeChangePrintHelper(amount, 0, 25, 0, 0, 0, 0);
    }
    
    private static void makeChangePrintHelper(int amount, int sum, int largest, int p, int n, int d, int q) {
      if (sum > amount) {
         return;
      } else if (sum == amount) {
         System.out.println("["+p+", "+n+", "+d+", "+q+"]");
      }
      
         
         if (largest == 25) {
            makeChangePrintHelper(amount, sum +25, 25, p, n, d, q+1);
         }
         if (largest >= 10) {
            makeChangePrintHelper(amount, sum +10, 10, p, n, d+1, q);
         }
         if (largest >= 5) {
            makeChangePrintHelper(amount, sum +5, 5, p, n+1, d, q);
         }
         makeChangePrintHelper(amount, sum +1, 1, p+1, n, d, q);
                  
      }
      
      public static String longestCommonSub(String a, String b) {
         if (a.equals("") || b.equals("")) {
            return "";
         } else {
            if (a.substring(0,1).equals(b.substring(0,1))) {
               return a.substring(0,1) + longestCommonSub(a.substring(1, a.length()),b.substring(1, b.length())); 
            }
            String one = longestCommonSub(a.substring(1, a.length()), b);
            String two = longestCommonSub(a, b.substring(1, b.length()));
            
            if (one.length()>two.length()) {
               return one;
            } else {
               return two;
            }
            
         }
      }
      
      
    
   
   public static void main (String[] args) {
      ArrayList<Integer> nums = new ArrayList<>(Arrays. asList(10, 25, 9, 1, 5));
      System.out.print(longestCommonSub("ABCDEFG", "BGCEHAF"));
   }
}