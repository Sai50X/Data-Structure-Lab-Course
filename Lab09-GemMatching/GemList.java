import java.util.*;
public class GemList<T> 
{
   private class Node 
{
    private Gem  gem;
    private Node next;
    
    public Node(Gem g) {
      gem = g;
      next = null;
    }
}
   
   private int size;
   private Node head;
   private Node tail;
   
   public GemList() {
      head = null;
      tail = null;
      size = 0;
   }

	public static void main(String [] args)
	{
   
   
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);
		
		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);
		
		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);
		
		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);
		
		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);
		
		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);		
	}	
   
   public int size() {
      return size;
   }
   
   public void draw (double y) {
      Node temp = head;
      int pos = 0;
      while(temp!=null) {
         temp.gem.draw(GemGame.indexToX(pos),y);
         temp = temp.next;
         pos++;
      }
   }
   
   public String toString() {
      Node temp = head;
      String answer = "";
      while(temp!=null) {
         if (temp.next == null) {
            answer += temp.gem.getType();
            break;
         }
         answer += temp.gem.getType() + "-> ";
         temp = temp.next;
      }
      return answer;
   }
   
   public void insertBefore(Gem g, int index) {
      Node temp = head;
      Node previous = null;
      int pos = 0;
      if (head == null) {
            head = new Node(g);
            tail = head;
            size++;
            return;
         }
      while(temp!=null) {
         if(index == 0) {
            Node tempHead = head;
            head.gem = g;
            head.next = tempHead;
            size++;
            return;
         } else if (index >= size) {
            tail.next = new Node(g);
            tail = tail.next;
            size++;
            return;
         } else if (pos == index) {
            previous.next = new Node(g);
            previous.next.next = temp;
            size++;
            return;
         }
         previous = temp;
         temp = temp.next;
         pos++;
      }
   }
   
   public int score() {
      Node temp = head;
      int score = 0;
      Stack<Node> w = new Stack<Node>();
      if (temp==null) {
         return 0;
      } else if (temp.next == null) {
         return temp.gem.getPoints();
      }
      while(temp!=null) {
         if (w.isEmpty()) {
            w.push(temp);
         } else if (w.peek().gem.getType() == temp.gem.getType()) {
            w.push(temp);
         } else {
            int count = 0;
            int length = 0;
            while (!w.isEmpty()) {
               count += w.pop().gem.getPoints();
               length++;
            }
            score += (count * length);
            w.push(temp);
         }
         temp = temp.next;
         if(temp == null) {
            int count = 0;
            int length = 0;
            while (!w.isEmpty()) {
               count += w.pop().gem.getPoints();
               length++;
            }
            score += (count * length);
         }
      }
      return score;
   }
}
