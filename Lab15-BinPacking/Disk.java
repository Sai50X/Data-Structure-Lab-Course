import java.util.*;

public class Disk implements Comparable<Disk> {

   private ArrayList<Integer> files;
   private int size;
   private int ID;
   
   public Disk (int num) {
      files = new ArrayList<Integer>();
      size = 1000000;
      ID = num;
   }
   
   public void add (int file) {
      files.add(file);
      size = size - file;
   }
   
   public int getSize() {
      return size;
   }
   
   public int compareTo(Disk d) {
      if (this.size == d.size) {
         return 0;
      }
      if (this.size > d.size) {
         return -1;
      } else if (this.size < d.size) {
         return 1;
      }
      return -1;
   }
   
   public String toString () {
      String answer = ID + " " + size + ": ";

      for (int i = 0; i < files.size(); i++) {
            answer += (files.get(i) + " ");
      }
      answer += "\n";
      return answer;
   }

}