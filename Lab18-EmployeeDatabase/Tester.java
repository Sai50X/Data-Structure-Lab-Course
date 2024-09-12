import java.io.*;
import java.util.*;

public class Tester {

   /*  PLEASE READ
      
  There is more than 1 way to implement all of this, so don't worry 
  about your code looking different that other students.  I won't put 
  emphasis on efficiency. (Closed hashing are inefficient by nature!)

  At minimum:
   You should have an Employee class
   and an EmployeeDatabase class (with inner class of Entry)
   
   I will look at your EmployeeDatabase class to see if you 
   have correctly implemented both Linear probing and Quadratic probing.      
   
   Linear probing means that you first get your hashCode.  If that spot in
   your array is taken, just increment to the next one until you find
   an empty spot. 
   
   If you reach the end of the array when doing this, wrap back up to the top.
   
   Quadratic probing means that you first get your hashCode.  If that spot
   in your array is taken, you use a quadratic equation to figure out
   where to go next.
   int spot = hashCode(key); //try the obvious/requested spot
   if it's unavailable, try
   spot = hashCode(key) + 1 + 1*1; // h(k) + i + i^2, where i is 1
   if that's unavailable, try
   spot = hashCode(key) + 2 + 2*2; // h(k) + i + i^2, where i is 2 
   
   Other tips:
   I start with an array size of 11 and simply resizeAndRehash 
   anytime the array get more than half full.  If there are 11 spots, and 10
   of them are alreay taken, we are VERY likely to have a collision.  Therefore,
   I resizeAndRehash when I get half full to ensure plenty of room for data to land.


      When done, you can try to improve efficiency by calling resizeAndRehash 
      earlier (but not too frequently) or by keeping capacity at prime numbers.  
      Students previously found a study that says prime capacities arrays produce
      fewer collisions.
   
   I have not yet coded a full performance test, and probably won't.  You
   peeps who are bored are welcome to try it though.       
   
   */


   public static void main (String[] args) {            
      
      simpleTest(false); //simple test with linear probing
      simpleTest(true);  //simple test with quadratic probing
      
      biggerTest(false); //large test (reads data file) using linear probing
      biggerTest(true); //large test (reads data file) using quadratic probing
      
      //Optional
      //performanceTest();                     
   }     
   
   
   public static void simpleTest(boolean useQuad) {
      System.out.print("SIMPLE TEST - ");
      System.out.println(useQuad ? "using quadratic probing" : "using linear probing");
      EmployeeDatabase ed = new EmployeeDatabase(useQuad);
      ed.put(123, new Employee("Fred Java"));
      ed.put(234, new Employee("Jim Halpert"));
      ed.put(133, new Employee("Pam Beasley"));
      ed.put(224, new Employee("Stanley Hudson"));
      ed.put(224, new Employee("Creed")); //overwrite stanley with creed
   
      System.out.println(ed.get(133)); //pam
      System.out.println(ed.get(224)); //creed
      System.out.println(ed.get(123)); //fred
      System.out.println(ed.get(234)); //jim
      System.out.println(ed.get(999)); //null
      System.out.println();
   }
   
   
   public static void biggerTest(boolean useQuad) {
      System.out.print("BIGGER TEST - ");
      System.out.println(useQuad ? "using quadratic probing" : "using linear probing");
      EmployeeDatabase ed = new EmployeeDatabase(useQuad);
      
      try {
         Scanner file = new Scanner(new File("Large Data Set.txt"));
         while (file.hasNextInt()) {
            int id = file.nextInt();
            String name = file.nextLine().trim();
            ed.put(id, new Employee(name));
         }  
      } catch (IOException e) { System.out.println("Something went wrong in file read."); }
      
      System.out.println(ed.get(10000)); // James Butt
      System.out.println(ed.get(99999)); // Patsy Lerper 
      System.out.println(ed.get(59659)); // Scott Zellers
      System.out.println(ed.get(87919)); // Kent Grishaber  
      System.out.println();
   }
   
   
   public static void performanceTest() { 
      //code this part (if you want) like it says in EmployeeDatabase.docx
   }
   
}