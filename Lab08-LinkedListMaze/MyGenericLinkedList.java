import java.util.*;
import java.io.*;

public class MyGenericLinkedList<T> {
   private ListNode head;
   private ListNode tail;
   private int size;
   
   private class ListNode 
   {
		T val; 
		ListNode next;

		public ListNode(T val) { 
         this.val = val; 
      }

      @Override
		public String toString() { 
         return "" + this.val;
      }
	}
   
   public String toString() {
      String answer = "[";
      ListNode temp = head;
      while(temp!=null){
         if (temp.next!=null) {
            answer+=temp.val+", ";
         } else {
            answer+=temp.val + "";
         }
         temp = temp.next;
      }
      answer+="]";
      return answer;
   }
   
   public MyGenericLinkedList() {
      head = null;
      tail = null;
      size = 0;
   }
   
   public MyGenericLinkedList(T val) {
      head = new ListNode(val);
      tail = head;
      size = 1;
   }
   
   public void add(T newVal) {
      if (head == null) {
         head = new ListNode(newVal);
         tail = head;
         size++;
         return;
      }
      tail.next = new ListNode(newVal);
      tail = tail.next;
      size++;
   }
   
   public boolean contains (T target) {
      boolean answer = false;
      ListNode temp = head;
      if (head.val == target) {
         return true;
      }
      while (temp.next!=null) {
         if (temp.next.val == target) {
            answer = true;
         }
         temp = temp.next;
      }
      return answer;
   }
   
   public T get(int index) throws IndexOutOfBoundsException {
      int answer = 0;
      ListNode temp = head;
      if (answer == index && temp!= null) {
         return temp.val;
      }
      if (index == size-1) {
         return tail.val;
      }
      while (temp.next!=null) {
         answer++;
         if (answer == index) {
            return temp.next.val;
         }
         temp = temp.next;
      }
      throw new IndexOutOfBoundsException("Number out of bounds");
   }
   
   public int indexOf(T target) {
      int answer = 0;
      ListNode temp = head;
      if (head.val == target) {
         return answer;
      }
      if (tail.val == target) {
         return size-1;
      }
      while (temp.next!=null) {
         answer++;
         if (temp.next.val == target) {
            return answer;
         }
         temp = temp.next;
      }
      return -1;
   }
   
   public void set(T newVal, int index) throws IndexOutOfBoundsException {
         int pos = 0;
         ListNode temp = head;
         while (temp!=null) {
            
            if (index == pos){
               temp.val = newVal;
               return;
            }
            temp = temp.next;
            pos++;
         }
         throw new IndexOutOfBoundsException("Number out of bounds");
      }
   
   public int size() {
      return size;
   }
   
   public int sizeRecursive(ListNode current) {
      if (current.next==null) {
         return 1;
      }
      return 1 + sizeRecursive(current.next);
   }
   
   public int sizeRecursive() {
      return sizeRecursive(head);
   }
   
   public boolean isEmpty() {
      if (head == null) {
         return true;
      }
      return false;
   }
   
   public T remove (int index) {
         int pos = 0;
         ListNode previous = null;
         ListNode temp = head;
         while (temp!=null) {
            
            if (index == pos){
               if (pos != 0) {
                  if (pos == size-1) {
                     T answer = tail.val;
                     tail = previous;
                     tail.next = null;
                     size--;
                     return answer;
                  } else {
                     previous.next = temp.next;
                     size--;
                     return temp.val;
                  }
               }
               else {
                  head = temp.next;
                  size--;
                  return temp.val;
               }
            }
            previous = temp;
            temp = temp.next;
            pos++;
         }
         throw new IndexOutOfBoundsException("Number out of bounds");
      }
      
      public void add(T newVal, int index) {
         int pos = 0;
         ListNode previous = null;
         ListNode temp = head;
         if (head == null) {
            head = new ListNode(newVal);
            tail = head;
            size++;
            return;
         }
         if (index == size) {
            tail.next = new ListNode(newVal);
            tail = tail.next;
            size++;
            return;
         }
         
         while (temp!=null) {
            
            if (index == pos){
               if (pos != 0) {
                  previous.next = new ListNode(newVal);
                  previous.next.next = temp;
                  size++;
                  return;
               }
               else {
                  head = new ListNode(newVal);
                  head.next = temp;
                  size++;
                  return;
               }
            }
            previous = temp;
            temp = temp.next;
            pos++;
         }
         throw new IndexOutOfBoundsException("Number out of bounds");
      }
}