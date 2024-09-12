import java.util.*;

public class EmployeeDatabase {

   private class Entry {
   
      public int ID;
      
      public Employee employee;
      
      public Entry(int id, Employee e) {
         ID = id;
         employee = e;
      }
      
      public String toString() {
         return employee.getName();
      }
   
   }

   private Entry[] entries;
   private boolean probing;
   private int size;
   
   
   public EmployeeDatabase(boolean b) {
      probing = b;
      entries = new Entry[10];
      size = 0;
   }
   
   public int hashCode(int key) {
      return key % entries.length;
   }
   
   public void put (int key, Employee value) {
      
      if (entries[hashCode(key)] == null) {
         entries[hashCode(key)] = new Entry(key, value);
      } else {
         if (probing == true) { //quadratic
            int i = 1;
            if (entries[(hashCode(key))%entries.length].ID == key) {
                  entries[(hashCode(key))%entries.length] = new Entry(key, value);
                  return;
               }
            while (entries[(hashCode(key) + (i*i))%entries.length]!=null) {
               if (entries[(hashCode(key) + (i*i))%entries.length].ID == key) {
                  entries[(hashCode(key) + (i*i))%entries.length] = new Entry(key, value);
                  return;
               }
               i++;
            }
            entries[(hashCode(key) + (i*i))%entries.length] = new Entry(key, value);
         } else { //linear
            int i = hashCode(key);
            if (entries[(i )%entries.length].ID == key) {
                  entries[(i )%entries.length] = new Entry(key, value);
                  return;
            }
            while (entries[(i + 1)%entries.length]!=null) {
               if (entries[(i + 1)%entries.length].ID == key) {
                  entries[(i + 1)%entries.length] = new Entry(key, value);
                  return;
               }
               i++;
            }
            entries[(i + 1)%entries.length] = new Entry(key, value);
         }
      }
      size++;
      if (((double)size/entries.length) > .25) {
         resizeAndRehash();
      }
   }
   
   public void resizeAndRehash() {
      Entry[] temp = entries;
      entries = new Entry[temp.length*2];
      for (int i = 0; i < temp.length; i++) {
         if (temp[i] == null) {
            continue;
         } else {
            put(temp[i].ID, temp[i].employee);
         }
      }
      
   }
   
   public Entry get (int key) {
      if (entries[hashCode(key)]==null) {
         return null;
      }
      if (entries[hashCode(key)].ID == key) {
         return entries[hashCode(key)];
      } else {
         if (probing == true) { //quadratic
            int i = 1;
            while (entries[(hashCode(key) + (i*i))%entries.length]!=null) {
               i++;
            }
            return entries[(hashCode(key) + (i*i))%entries.length];
         } else { //linear
            int i = hashCode(key);
            while (entries[(i + 1)%entries.length]!=null) {
               i++;
            }
            return entries[(i + 1)%entries.length];
         }
      }
   }

}