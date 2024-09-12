import java.util.*;
import java.io.*;

public class Scooby {

  
   
   public static void main (String[] args) {
      helper("scooby.dat");
   }
   
   public static void helper (String name) {
      Scanner input = null;
      try {
         input = new Scanner(new File(name));
      } catch (Exception e) {
         System.out.println("File not found!");
      }
      int times = input.nextInt();
      input.nextLine();
      
      for (int i = 0; i < times; i++) {
         HashMap<String, ArrayList<String>> list = new HashMap<String, ArrayList<String>>();
         String[] connections = input.nextLine().split(" ");
         for (int u = 0; u < connections.length; u++) {
            String one = connections[u].charAt(0)+"";
            String two = connections[u].charAt(1)+"";
            if (!list.containsKey(one)) {
               list.put(one, new ArrayList<String>());
            }
            if (list.get(one).contains(two)!=true) {
                  list.get(one).add(two);
            }
               if (!list.containsKey(two)) {
               list.put(two, new ArrayList<String>());
            }
            if (list.get(two).contains(one)!=true) {
                  list.get(two).add(one);
             }         
            }
            HashSet<String> visited = new HashSet<String>();
            String word = input.nextLine();
            String start = word.charAt(0) + "";
            String end = word.charAt(1) + "";
            boolean answer = false;
            if (list.containsKey(start) && list.containsKey(end)) {
               answer = findPath(start, end, start, visited, list);
            }
            if (answer == true) {
               System.out.println("yes");
            } else {
               System.out.println("no");
            }
      }
      
   }
   
   public static boolean findPath(String start, String end, String current, HashSet<String> visited, HashMap<String, ArrayList<String>> list) {
      if (current.equals(end)) {
         return true;
      }
      ArrayList<String> neighbors = list.get(current);
      for (int i = neighbors.size()-1; i >= 0; i--) {
         if (visited.contains(neighbors.get(i))) {
            neighbors.remove(i);
         }
      }
      boolean ret = false;
      if (neighbors.isEmpty()) {
         return false;
      }
         for (int i = 0; i < neighbors.size(); i++) {
            visited.add(current);
            ret = ret || findPath(start, end, neighbors.get(i), visited, list);
         }
      
      return ret;
      
   }

}