import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
    this is the Controller component
 */

public class Life extends JFrame implements ActionListener
{
   private static final long serialVersionUID = 1L;
   private LifeView view;
   private LifeModel model;
   private JButton runButton, pauseButton, resumeButton, resetButton, colorButton;

	/** construct a randomized starting grid */
   public Life()
   {
      this(null);
   }
	
	/** construct a grid using the info in text file */
   public Life(String fileName) 
   {
      super("Conway's Life");
   
   	// build the buttons
      JPanel controlPanel = 
         	new JPanel(new FlowLayout(FlowLayout.CENTER));
      runButton = new JButton("Run");
      runButton.addActionListener(this);
      runButton.setEnabled(true);
      controlPanel.add(runButton);
      pauseButton = new JButton("Pause");
      pauseButton.addActionListener(this);
      pauseButton.setEnabled(false);
      controlPanel.add(pauseButton);
      resumeButton = new JButton("Resume");
      resumeButton.addActionListener(this);
      resumeButton.setEnabled(false);
      controlPanel.add(resumeButton);
      resetButton = new JButton("Reset");
      resetButton.addActionListener(this);
      resetButton.setEnabled(false);
      controlPanel.add(resetButton);
      colorButton = new JButton("Randomize Color");
      colorButton.addActionListener(this);
      colorButton.setEnabled(true);
      controlPanel.add(colorButton);
   
   	// build the view
      view = new LifeView();
      view.setBackground(Color.white);
   
   	// put buttons, view together
      Container c = getContentPane();
      c.add(controlPanel, BorderLayout.NORTH);
      c.add(view, BorderLayout.CENTER);
   
   	// build the model
      model = new LifeModel(view, fileName);
   }

   public void actionPerformed(ActionEvent e)
   {
      JButton b = (JButton)e.getSource();
      if ( b == runButton )
      {
         model.run();
         runButton.setEnabled(false);
         pauseButton.setEnabled(true);
         resumeButton.setEnabled(false);
         resetButton.setEnabled(true);
      }
      else if ( b == pauseButton )
      {
         model.pause();
         runButton.setEnabled(false);
         pauseButton.setEnabled(false);
         resumeButton.setEnabled(true);
         resetButton.setEnabled(true);
      }
      else if ( b == resumeButton )
      {
         model.resume();
         runButton.setEnabled(false);
         pauseButton.setEnabled(true);
         resumeButton.setEnabled(false);
         resetButton.setEnabled(true);
      }
      else if ( b == resetButton ) 
      {
         model.reset();
         runButton.setEnabled(true);
         pauseButton.setEnabled(false);
         resumeButton.setEnabled(false);
         resetButton.setEnabled(false);
      } else if ( b == colorButton ) {
         model.randomizeColor();
         colorButton.setEnabled(true);
      }
   }
	
   public static void main(String[] args) 
   {
      Life conway = new Life(); //parameterize to start w/ a particular file
   	
      conway.addWindowListener(
         new WindowAdapter()
         {
            public void windowClosing(WindowEvent e)
            {
               System.exit(0);
            }
         });
      conway.setSize(570, 640);
   	//conway.show(); //deprecated, added call below
      conway.setVisible(true);
   }
}