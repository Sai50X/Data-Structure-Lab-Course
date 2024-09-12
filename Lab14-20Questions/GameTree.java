import java.io.*;
import java.util.Scanner;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */
public class GameTree
{
	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */
    
   private gameNode root;
   private gameNode currentNode;
   private String name;
    
   private class gameNode {
      public String val;
      public gameNode yes, no;
     
      public gameNode(String val) {
         this.val = val;
         yes = no = null;
      }
     
      @Override
        public String toString() { 
         return this.val;
      }
   }
     
   public GameTree(String fileName)
   {
      Scanner input = null;
      try {
         File file = new File(fileName);
         input = new Scanner(file);
         root = new gameNode(input.nextLine());
         helper(input, root, true);
         helper(input, root, false);
         currentNode = root;
         name = fileName;
      } catch (FileNotFoundException e) {
         System.out.println("File not found!");
      }
   }
   
   private void helper(Scanner input, gameNode node, boolean isYes) {
      if (!input.hasNextLine()) {
         return;
      }
      String temp = input.nextLine();
      
      if (temp.contains("?")) {
         if (isYes == true) {
            node.yes = new gameNode(temp);
            helper(input, node.yes, true);
            helper(input, node.yes, false);
         } else {
            node.no = new gameNode(temp);
            helper(input, node.no, true);
            helper(input, node.no, false);
         }
      } else {
         if (isYes) {
            node.yes = new gameNode(temp);
         } else {
            node.no = new gameNode(temp);
         }
      }
   }

	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */
   public void add(String newQ, String newA)
   {
      String previous = currentNode.val;
      currentNode.val = newQ;
      currentNode.yes = new gameNode(newA);
      currentNode.no = new gameNode(previous);
   }

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
   public boolean foundAnswer()
   {
      if (currentNode.val.contains("?") == false) {
         return true;
      }
   
      return false; 
   }

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
   public String getCurrent()
   {
   	//TODO
      
      return currentNode.val; //replace
   }

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
   public void playerSelected(Choice yesOrNo)
   {
      if (yesOrNo == Choice.Yes) {
         currentNode = currentNode.yes;
      } else {
         currentNode = currentNode.no;
      }
   }

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
   public void reStart()
   {
      currentNode = root;
   }

   @Override
   public String toString()
   {
   	
      return sHelper("", root);
   }
   
   private String sHelper(String level, gameNode node) {
      String s = "";
      if (node == null) {
         return "";
      } else {
         s+=sHelper(level + "- ", node.no);
         s+=(level + node.val+"\n");
         s+=sHelper(level + "- ", node.yes);
      }
      return s;
   }

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
   public void saveGame()
   {
      String outputFileName = name;
      PrintWriter diskFile = null;
      try { 
         diskFile = new PrintWriter(new File(outputFileName)); 
      }
      catch (IOException io) { 
         System.out.println("Could not create file: " + outputFileName); 
      }
      saveHelper(diskFile, root);
      diskFile.close();
   
   }
   
   private void saveHelper(PrintWriter diskFile, gameNode current) {
      if (current == null) {
         return;
      } else {
         diskFile.println(current.val);
         saveHelper(diskFile, current.yes);
         saveHelper(diskFile, current.no);
      }
   }
   
}
