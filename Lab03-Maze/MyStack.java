import java.util.*;

public class MyStack implements StackADT{ 
   private Square[] stack;
   private int size;
   
   public MyStack() {
      stack = new Square[2];
      size = 0;
   }
   
   public MyStack(int cap) {
      stack = new Square[cap];
      size = 0;
   }
   
   public boolean isEmpty() {
      if(size == 0) {
      return true;
      }
      return false;
   }
   
   public Square peek() {
      if (size == 0) {
         throw new EmptyStackException();
      } else {
         return stack[size-1];
      }
   }
   
   public Square pop(){
      if (size == 0) {
         throw new EmptyStackException();
      } else {
         Square answer = stack[size-1];
         stack[size-1] = null;
         size--;
         return answer;
      }
   }
   
   public void push(Square item) {
      if (size == stack.length) {
         doubleCapacity();
      }
      stack[size] = item;
      size++;
   }
   
   private void doubleCapacity() {
      Square[] dlist = new Square[stack.length*2];
      for (int i = 0; i < stack.length; i ++) {
         dlist[i]=stack[i];
      }
      stack = dlist;
   }
   
   public String toString() {
       String answer = "[";
      for (int i = 0; i < size; i++) {
         if (stack[i]!=null) {
            if (i!=size-1) {
               answer+=stack[i].toString()+", ";
            } else {
               answer+=stack[i].toString();
            }
         } 
      }
      answer+="]";
      return answer;
   } 
   
   public void clear() {
   
   }
   
   public int size(){
      return size;
   }
   
}