import java.util.*;
import java.io.*;

public class WorstFit {

   private PriorityQueue<Disk> pq;
   private double totalSize;
   private int count;
   
   public WorstFit() {
      totalSize = 0;
      count = 0;
      pq = new PriorityQueue<>();
      
   }
   
   public void fillDisks(String name) {
      try {
         File file = new File(name);
         Scanner input = new Scanner(file);
         pq.offer(new Disk(count));
         while (input.hasNextInt()) {
            int num = input.nextInt();
            Disk d = pq.peek();
            if (num > d.getSize()) {
               count++;
               Disk d1 = new Disk(count);
               d1.add(num);
               pq.offer(d1);
               
               totalSize = totalSize + num;
            } else {
               Disk d1 = pq.poll();
               d1.add(num);
               pq.offer(d1);
               
               totalSize = totalSize + num;
         }
      }
      } catch (Exception e) {
         System.out.println("File not found!");
      }
   }
   
   public void fillDisksDecreasing(String name) {
       try {
         File file = new File(name);
         Scanner input = new Scanner(file);
         PriorityQueue<Integer> orderFiles = new PriorityQueue<>(Collections.reverseOrder());
         while (input.hasNextInt()) {
            orderFiles.offer(input.nextInt());
         }
         pq.offer(new Disk(count));
         while (!orderFiles.isEmpty()) {
            int num = orderFiles.poll();
            Disk d = pq.peek();
            if (num > d.getSize()) {
               count++;
               Disk d1 = new Disk(count);
               d1.add(num);
               pq.offer(d1);
               totalSize = totalSize + num;
            } else {
               Disk d1 = pq.poll();
               d1.add(num);
               pq.offer(d1);
               
               totalSize = totalSize + num;
         }
      }
      } catch (Exception e) {
         System.out.println("File not found!");
      }
   }
   
   public String toString() {
      String answer = "";
      answer += ("Total size = " + totalSize + " GB\n");
      answer += ("Disks req'd = " + count + "\n");
      while (!pq.isEmpty()) {
         answer += pq.poll().toString();
      }
      return answer;
   }
   
   public static void main (String[] args) {
      WorstFit wf = new WorstFit();
      wf.fillDisksDecreasing("input20.txt");
      System.out.print(wf);
   }
   
   

}