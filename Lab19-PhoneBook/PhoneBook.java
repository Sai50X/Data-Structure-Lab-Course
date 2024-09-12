import java.util.*;

public class PhoneBook implements IMap {

    private Entry[] entries;
    private int size;
    private double loadFactor;
   
    private class Entry {
   
      public Person p;
      
      public PhoneNumber num;
      
      public Entry next;
      
      public Entry(Person p, PhoneNumber num) {
         this.p = p;
         this.num = num;
      }
      
      public String toString() {
         return p.getName() + " " + num.getNum();
      }
   
   }
   
   public PhoneBook () {
      entries = new Entry[10];
      size  = 0;
      loadFactor = 1;
   }
   
   public PhoneBook(int length) {
      entries = new Entry[length];
      size  = 0;
      loadFactor = 1;
   }
   
   public int getPos(int code) {
      return (code % entries.length);
   }
   
   public PhoneNumber put (Person key, PhoneNumber value) {
      if ((double)(size/entries.length) > loadFactor) {
         resizeAndRehash();
      }
      int pos = getPos(key.hashCode());
      PhoneNumber answer = null;
      if (entries[pos] == null) {
         entries[pos] = new Entry(key, value);
         size++;
         return null;
      } else {
         if (entries[pos].p.equals(key)) {
            answer = new PhoneNumber(entries[pos].num.getNum());
            entries[pos].num.setNum(value.getNum());
            return answer;
         }
         Entry temp = entries[pos];
         while (temp.next != null) {
            
            if (temp.next.p.equals(key)) {
               answer = new PhoneNumber(temp.next.num.getNum());
               temp.next.num.setNum(value.getNum());
               return answer;
            }
            temp = temp.next;
         }
         temp.next = new Entry(key, value);
         size++;
      }  
      return null;
   }
   
   private void resizeAndRehash() {
      
      Entry[] temp = entries;
      entries = new Entry[entries.length*2];
      int sizeCheck = size;
      for (int i = 0; i < temp.length; i++) {
         if (temp[i] == null) {
            continue;
         } else {
            entries[getPos(temp[i].p.hashCode())] = temp[i];
            Entry add = temp[i];
            while (add.next != null) {
               put(add.next.p, add.next.num);
               add = add.next;
            }
            
         }
      }
      size = sizeCheck;
   }
   
   public PhoneNumber get (Person key) {
      int pos = getPos(key.hashCode());
      if (entries[pos]==null) {
         return null;
      }
      if (entries[pos].p.equals(key)) {
         return entries[pos].num;
      } else {
         Entry temp = entries[pos];
         while (temp.next != null) {
            if (temp.next.p.equals(key)) {
               return temp.next.num;
            }
            temp = temp.next;
         }
      }
      return null;
   }
   
   public PhoneNumber remove (Person key) {
      int pos = getPos(key.hashCode());
      PhoneNumber answer = null;
      if (entries[pos]==null) {
         return null;
      }
      if (entries[pos].p.equals(key)) {
         answer = entries[pos].num;
         entries[pos] = entries[pos].next;
         size--;
         return answer;
      } else {
         Entry temp = entries[pos];
         while (temp.next != null) {
            if (temp.next.p.equals(key)) {
               answer = temp.next.num;
               temp.next = temp.next.next;
               size--;
               return answer;
            }
            temp = temp.next;
         }
      }
      return null;
   }
   
   public int size() {
      return size;
   }
   
   public void setLimit(double percent) {
      loadFactor = percent;
   }
   
   public void printHashTable() {
      for (int i = 0; i < entries.length; i++) {
         System.out.print("Index "+i+ " >> ");
         if (entries[i] == null) {
            System.out.println("Empty");
            continue;
         } else {
            System.out.print(entries[i]);
         }
         Entry temp = entries[i];
         while(temp.next != null) {
            System.out.print(" >> " + temp.next);
            temp = temp.next;
         }
         System.out.println("");
      }
   }

}