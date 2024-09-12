import java.util.*;
import java.io.*;

public class Play {

   public static void main (String[] args) {
      helper("play.dat");
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
         String[] nodes = input.nextLine().split(" ");
         int number = Integer.valueOf(nodes[0]);
         for (int u = 0; u < number; u++) {
            list.put(u+1+"", new ArrayList<String>());
         }
         
         String[] connections = input.nextLine().split(" ");
         while (connections.length > 1) {
            
            String one = connections[0];
            String two = connections[1];
            
            if (list.get(one).contains(two)!=true) {
                  list.get(one).add(two);
            }
            
             
            connections = input.nextLine().split(" ");
         }
         int count = 0;
         Stack<String> temp = new Stack<String>();
         HashSet<String> visited = new HashSet<String>();
         temp.push(connections[0]);
         visited.add(connections[0]);
         while (!temp.isEmpty()) {
            String current = temp.pop();
            count++;
            for (int u = 0; u < list.get(current).size(); u++) {
               if (visited.contains(list.get(current).get(u)) != true) {
                  temp.push(list.get(current).get(u));
                  visited.add(list.get(current).get(u));
               }
            }
         }
         System.out.println(count);
      
      }
   }
}