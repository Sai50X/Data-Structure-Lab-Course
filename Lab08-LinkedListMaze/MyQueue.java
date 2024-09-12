import java.util.*;

public class MyQueue<T> implements QueueADT<T>{
   private MyGenericLinkedList<T> queue;
   
   public MyQueue() {
      queue = new MyGenericLinkedList();
   }
   
   public boolean isEmpty() {
      return queue.isEmpty();
   }
   
   public void offer(T item) {
      queue.add(item);
   }
   
   public T poll() {
      return queue.remove(0);
   }
   
   public T peek() {
      return queue.get(0);
   }
   
   public int size() {
      return queue.size();
   }
   
   public void clear() {
      queue = new MyGenericLinkedList();
   }
   
}