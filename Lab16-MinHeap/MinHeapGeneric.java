import java.util.*;

public class MinHeapGeneric<T extends Comparable<T>>{

   private T[] heap;
   private int size;
   private static final int DEFAULT_CAPACITY = 8;
   
   public MinHeapGeneric() {
      this(DEFAULT_CAPACITY);
   }
   
   public MinHeapGeneric(int capacity) {
      size = 0;
      this.heap = (T[]) new Comparable[capacity];
   }
   
   public MinHeapGeneric(T... nums) {
      size = nums.length;
      this.heap = (T[]) new Comparable[nums.length+1];
      buildHeap(nums);
   }
   
   public void buildHeap(T... nums) {
      for (int i = 0; i < nums.length; i++) {
         heap[i+1] = nums[i];
      }
      for (int i = (nums.length/2)+1; i > 0; i--) {
         siftDown(i);
      }
   }
   
   public int getSize() {
      return size;
   }
   
   public boolean isEmpty() {
      if (size == 0) {
         return true;
      }
      return false;
   }
   
   public T peekMinimum() {
      return heap[1];
   }
   
   public int getLeftChildIndex(int index) {
      return index*2;
   }
   
   public int getRightChildIndex(int index) {
      return (index*2)+1;
   }
   
   public int getParentIndex(int index) {
      return index/2;
   }
   
   private void doubleCapacity() {
      T[] temp = (T[]) new Comparable[heap.length*2];
      for (int i = 1; i < heap.length; i++) {
         temp[i] = heap[i];
      }
      heap = temp;
   }
   
   public void insert(T value) {
      if (heap.length == size+1) {
         doubleCapacity();
      }
      heap[size+1] = value;
      bubbleUp(size+1);
      size++;
   }
   
   private void bubbleUp(int index) {
      if (index == 1) {
         return;
      }
      if (heap[index].compareTo(heap[index/2]) > 0) {
         return;
      } else {
         T temp = heap[index/2];
         heap[index/2] = heap[index];
         heap[index] = temp;
         bubbleUp(index/2);
      }
   }
   
   public T popMinimum() {
      if (size == 1) {
         T temp = heap[1];
         heap[1] = null;
         size--;
         return temp;
      }
      T answer = heap[1];
      heap[1] = heap[size];
      heap[size]=null;
      size--;
      siftDown(1);
      return answer;
   }
   
   private void siftDown(int index) {
      
      if (index*2 > size || heap[index*2] == null) {
         return;
      }
      int swapIndex = 0;
      if (index*2+1 > size) {
         swapIndex = index*2;
      } else if (heap[index*2].compareTo(heap[index*2+1]) < 0) {
         swapIndex = index*2;
      } else {
         swapIndex = index*2+1;
      }
      
      
      if (heap[index].compareTo(heap[swapIndex]) > 0)
      {
            T temp = heap[swapIndex];
            heap[swapIndex] = heap[index];
            heap[index] = temp;
            siftDown(swapIndex);
      }   
 }
   
   	@Override
	public String toString()
	{
		String output = "";

		for (int i = 1; i <= getSize(); i++) 
			output += heap[i] + ", ";

		if (output.indexOf(",") > -1)   
         		return output.substring(0, output.lastIndexOf(",")); //lazily truncate last comma
      	return output;

	}

	/** method borrowed with minor modifications from the internet somewhere, for printing */
	public void display() {
		int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
		String dots = "...............................";
		System.out.println(dots + dots);      
		while (j <= this.getSize())
		{
			if (column == 0)                 
				for (int k = 0; k < nBlanks; k++)
					System.out.print(' ');

			System.out.print((heap[j] == null)? "" : heap[j]);
			
			if (++column == itemsPerRow) {
				nBlanks /= 2;                 
				itemsPerRow *= 2;             
				column = 0;                   
				System.out.println();         
			}
			else                             
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(' ');
			
			j++;
		}
		System.out.println("\n" + dots + dots); 
	}


}