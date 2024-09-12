import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

   private static int SIZE = 60;
   private LifeCell[][] grid;   	
   private LifeView myView;
   private Timer timer;
   private boolean col = false;
   private boolean start = false;
   private int counter = 0;

	/** Construct a new model using a particular file */
   public LifeModel(LifeView view, String fileName) 
   {       
      int r, c;
      grid = new LifeCell[SIZE][SIZE];
      for ( r = 0; r < SIZE; r++ )
         for ( c = 0; c < SIZE; c++ )
            grid[r][c] = new LifeCell();
   
      if ( fileName == null ) //use random population
      {                                           
         for ( r = 0; r < SIZE; r++ )
         {
            for ( c = 0; c < SIZE; c++ )
            {
               if ( Math.random() > 0.85) //15% chance of a cell starting alive
                  grid[r][c].setAliveNow(true);
            }
         }
      }
      else
      {                 
         Scanner input = null;
         try {      
            input = new Scanner(new File(fileName));
         } catch (IOException e) { System.out.println("bad filename"); }
         int numInitialCells = input.nextInt();
         for (int count=0; count<numInitialCells; count++)
         {
            r = input.nextInt();
            c = input.nextInt();
            grid[r][c].setAliveNow(true);
         }
         input.close();
      }
   
      myView = view;
      myView.updateView(grid, false, start);
   
   }

	/** Constructor a randomized model */
   public LifeModel(LifeView view) throws IOException
   {
      this(view, null);
   }

	/** pause the simulation (the pause button in the GUI */
   public void pause()
   {
      timer.stop();
   }
	
	/** resume the simulation (the pause button in the GUI */
   public void resume()
   {
      timer.restart();
   }
	
	/** run the simulation (the pause button in the GUI */
   public void run()
   {
      timer = new Timer(50, this);
      timer.setCoalesce(true);
      timer.start();
   }

	/** called each time timer fires */
   public void actionPerformed(ActionEvent e)
   {
      oneGeneration();
      if (col == true) {
      myView.updateView(grid, true, start);
      col = false;
      } else {
      myView.updateView(grid, false, start);
      }
      
   }
   
   public void reset() {
      LifeModel lm = new LifeModel(myView,null);
      for (int r = 0; r < grid.length; r++) {
         for (int c = 0; c < grid[0].length; c++) {
            grid[r][c] = lm.grid[r][c];
         }
      }
      col = false;
      
      pause();
}
   
   public void randomizeColor() {
      counter++;
      if (counter%2!=0) {
         start = false;
      } else {
         start = true;
      }
      myView.updateView(grid, true, start);
      col = true;
   }

	/** main logic method for updating the state of the grid / simulation */
   private void oneGeneration()
   {
      
   	for(int r = 0; r < grid.length; r++) {
         for(int c = 0; c < grid[0].length; c++) {
            int occupied = 0;
            if (c-1>0) {
               if (grid[r][c-1].isAliveNow()==true) {
                  occupied++;
               }
            }
            if (c+1<grid[r].length) {
               if (grid[r][c+1].isAliveNow()==true) {
                  occupied++;
               }
            }
            if (r-1>0) {
               if (grid[r-1][c].isAliveNow()==true) {
                  occupied++;
               }
            }
            if (r+1<grid.length) {
               if (grid[r+1][c].isAliveNow()==true) {
                  occupied++;
               }
            }
            if (c-1>0&&r-1>0) {
               if (grid[r-1][c-1].isAliveNow()==true) {
                  occupied++;
               }
            }
            if (c-1>0&&r+1<grid.length) {
               if (grid[r+1][c-1].isAliveNow()==true) {
                  occupied++;
               }
            }
            if(c+1<grid.length&&r-1>0) {
               if (grid[r-1][c+1].isAliveNow()==true) {
                  occupied++;
               }
            }
            if(c+1<grid.length&&r+1<grid.length) {
               if (grid[r+1][c+1].isAliveNow()==true) {
                  occupied++;
               }
            }
             if (grid[r][c].isAliveNow() == false && occupied == 3) {
                  grid[r][c].setAliveNext(true);
               } else if (grid[r][c].isAliveNow() == true && (occupied == 3 || occupied == 2)) {
                  grid[r][c].setAliveNext(true);
               } else {
                  grid[r][c].setAliveNext(false);
               }  
            }
         }
      
      
      for(int r = 0; r < grid.length; r++) {
         for(int c = 0; c < grid[0].length; c++) {
            if (grid[r][c].isAliveNext()==true) {
               grid[r][c].setAliveNow(true);
            } else {
               grid[r][c].setAliveNow(false);
            }
         }
      } 
   }
}

