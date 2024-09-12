import java.util.*;

public class Melody { 

   private Queue<Note> notes;
   
   public Melody(Queue<Note> song) {
      notes = new LinkedList<>(song);
   }
   
   public String toString() {
      String s = "";
      Queue<Note> temp = new LinkedList<>(notes);

      while (!temp.isEmpty()) {
         Note next = temp.poll();
         s+=(next.toString() + "\n");
      }
      return s;
   }
   
   public Queue<Note> getNotes() {
      return notes;
   }
   
   public double getTotalDuration() {      
      double total = 0.0;

      Queue<Note> temp = new LinkedList<>(notes);
      Queue<Note> temp2 = new LinkedList<>();
      while (!temp.isEmpty()) {
         Note next = temp.poll();
           if (next.isRepeat() && temp2.isEmpty()) {
              temp2.offer(next);
              total += next.getDuration();
           }  else if (!temp2.isEmpty() && next.isRepeat()) {
              temp2.offer(next);
              total += next.getDuration();
              while (!temp2.isEmpty()) {
                 total += (temp2.poll()).getDuration();
              }
           } else if (!temp2.isEmpty()) {
              temp2.offer(next);
              total += next.getDuration();
           }  else {
              total += next.getDuration();
           }
      }
      return total;
   }
   
   public void changeTempo(double factor) {
      Queue<Note> temp = new LinkedList<>(notes);
      Queue<Note> finalTemp = new LinkedList<>();
      while (!temp.isEmpty()) {
         Note temp2 = temp.poll();
         temp2.setDuration( (temp2.getDuration()*factor));
         finalTemp.offer(temp2);
      }
      notes = new LinkedList<>(finalTemp);                    
   }                    
                       
   public void reverse() {
      Stack<Note> rev = new Stack<>();
      Queue<Note> temp = new LinkedList<>(notes);
       while (!temp.isEmpty()) {
         rev.push(temp.poll());
      }
       while (!rev.isEmpty()) {
         temp.offer(rev.pop());
      }
      notes = new LinkedList<>(temp);
   }
   
   public void append (Melody other) {
      Queue<Note> newList = other.getNotes();
      while (!newList.isEmpty()) {
         notes.offer(newList.poll());
      }
   }
   
   public void play() {
      Queue<Note> temp = new LinkedList<>(notes);
      Queue<Note> temp2 = new LinkedList<>();
      while (!temp.isEmpty()) {
         Note next = temp.poll();
         if (next.isRepeat()&&temp2.isEmpty()) {
            temp2.offer(next);
            next.play();
         } else if (!temp2.isEmpty() && next.isRepeat()) {
            temp2.offer(next);
            next.play();
            while (!temp2.isEmpty()) {
               temp2.poll().play();
            }
         } else if (!temp2.isEmpty()) {
            temp2.offer(next);
            next.play();
         } else {
            next.play();
         }
      }
   }

}