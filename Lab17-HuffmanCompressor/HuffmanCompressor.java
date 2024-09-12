import java.util.*;
import java.io.*;

public class HuffmanCompressor {

   public static int[] getFrequency(String fileName) {
      int[] frequency = new int[256];
      BufferedReader input = null;
      try { 
         input = new BufferedReader(new FileReader(fileName)); 
      } catch (IOException e) { 
         System.out.println("Could not find file:" + fileName); 
      }
      try {
    	   int charValue;
         while (input.ready()) {     
     		   charValue = input.read();
            frequency[(int)charValue] = frequency[(int)charValue] + 1;            
         } 
      } catch (IOException e) { 
         System.out.println("Error reading the file" + e); 
      }
      return frequency;
   }
   
   public static void compress (String originalFile, String shortFile , String codeFile) {
      HuffmanTree hf = new HuffmanTree(getFrequency(originalFile));
      hf.write(codeFile);
      HashMap<Character, String> map = hf.getMap();      
      BufferedReader input = null;
      BitOutputStream writer = null;
      try { 
         input = new BufferedReader(new FileReader(originalFile));
         writer = new BitOutputStream(shortFile); 
      }
      catch (IOException e) { 
         System.out.println("File not found: " + originalFile); 
      }        
      try {
    	   int charValue;
         while (input.ready()) {
            charValue = input.read();
            String bit = map.get((char)charValue);
            char [] bits = bit.toCharArray(); 
            for(int i = 0; i < bits.length; i++){
               writer.writeBit(bits[i] - 48);
            }         
         }
         String bit = map.get((char)256); 
         char[] sudo = bit.toCharArray();
         for(int i = 0; i < sudo.length; i++) {
            writer.writeBit(sudo[i] - 48);
         }
         writer.close();
           
      } catch (IOException e) { 
         System.out.println("File not found: " + originalFile); 
      }
      
      
   }
   
   public static void expand(String shortFile, String codeFile, String newFile) {
      HuffmanTree hf = new HuffmanTree(codeFile);
      BitInputStream in = new BitInputStream(shortFile);
      hf.decode(in, newFile);
      in.close();
   }
   
}