 import java.util.*;
 public class Node implements Comparable<Node>{
      
      public char letter;
      public int count;
      public Node left;
      public Node right;
      
      public Node (int frequency) {
         count = frequency;
      }
      public Node (int value, int frequency) {
         letter = (char) value;
         count = frequency;
      }
      
      public int compareTo(Node n) {
         if (this.count > n.count) {
            return 1;
         } else if (this.count < n.count) {
            return -1;
         }
         return 0;
      }
      
      public String toString () {
         return "";
      }
      
   }
