import java.util.*;
import java.io.*;

public class HuffmanTree {

   private int[] data;
   private PriorityQueue<Node> tree;
   private Node root;
   private HashMap<Character, String> map;
   
     
   public HuffmanTree(int[] count) {
      data = count;
      tree = new PriorityQueue<Node>();;
      for (int i = 0; i < count.length; i++) {
         if (data[i] == 0) {
            continue;
         }
         Node temp = new Node(i, data[i]);
         tree.offer(temp);
      }
      
      tree.offer(new Node(256, 1));
      while (tree.size()>1) {
         Node temp1 = tree.poll();
         Node temp2 = tree.poll();
         Node root = new Node(temp1.count + temp2.count);
         root.right = temp1;
         root.left = temp2;
         tree.offer(root);
      }
      root = tree.peek();
      
   }
   
   public HuffmanTree(String codeFile) {
      root = new Node(0, 0);
      Node current = root;
      BufferedReader input = null;
      try {
      input = new BufferedReader(new FileReader(codeFile));
      String letter = input.readLine();
      while (letter != null) {
         String bit = input.readLine();
         int value = Integer.valueOf(letter);
         char[] bits = bit.toCharArray();
         for (int i = 0; i < bits.length; i ++) {
            if (bits[i]  == '0') {
               Node child = current.left;
               if (i + 1 == bits.length) {
                  current.left = new Node(value, 0);
                  current = root;
               } else if (current.left == null) {
                  current.left = new Node(0, 0);
                  current = current.left;
               } else {
                  current = current.left;
               }
            } else {
               Node child = current.right;
               if (i + 1 == bits.length) {
                  current.right = new Node(value, 0);
                  current = root;
               } else if (current.right == null) {
                  current.right = new Node(0, 0);
                  current = current.right;
               } else {
                  current = current.right;
               }
            }
          }
          letter = input.readLine();
        }
      } catch (IOException E) {
         System.out.println("File not found: " + codeFile);
      }
      
   }
   
   public void printTree() {
      TreePrinter.printTree(root);
   }
   
   public void write(String fileName) {
      String outputFileName = fileName;
      PrintWriter diskFile = null;
      try { 
         diskFile = new PrintWriter(new File(outputFileName)); 
      }
      catch (IOException io) { 
         System.out.println("Could not create file: " + outputFileName); 
      }
      map = new HashMap<Character, String>();
      writeHelper(root, "", diskFile);
      diskFile.close();
   }
   
   
   private void writeHelper(Node n, String bit, PrintWriter diskFile) {
      String answer = "";
      if (n == null) {
         return;
      } 
      if (n.left == null && n.right == null ) {
         map.put(n.letter, bit);
         diskFile.println("" + (int)n.letter);
         diskFile.println(bit);
         bit = "";
         
      }
        writeHelper(n.left, bit + "0", diskFile);
        writeHelper(n.right, bit + "1", diskFile);
      
   }
   
   public void decode(BitInputStream in, String outFile) {
      try {
         Node current = root;
         PrintWriter writer = new PrintWriter(outFile);
         int temp = in.readBit();
         while (temp != -1) {
            if (temp == 0) {
               current = current.left;
            } else {
               current = current.right;
            }
            if (current.right == null && current.left == null) {
               if (current.letter == ((char)256)) {
                  break;
               } else {
                  writer.write(current.letter);
                  current = root;
               }
            }
            temp = in.readBit();
         }
         in.close();
         writer.close();
      } catch (IOException E) {
         System.out.println("Can't write to file!");
      }
   }
   
   public HashMap<Character,String> getMap() {
      return map;
   }
}