import java.awt.Color;

public class RecursiveDrawings {

   public static final int CANVAS_SIZE = 800;

   //WRITE YOUR RECURSIVE METHODS HERE
   
   public static void drawSnowflake(int size, double x, double y) {
      if (size < 5) {
         return;
      } else {
         for (int i = 0; i < 6; i ++) {
            double newX = size*Math.cos(i*(2*Math.PI/6));
            double newY = size*Math.sin(i*(2*Math.PI/6));
            StdDraw.line(x + newX, y + newY, x, y);
            drawSnowflake(size/3, x + newX, y + newY);
         }
         
      }  
      
   }
   
   public static void drawBlizzard() {
      int num = (int) ((Math.random()*20)+1.0);
      for (int i = 0; i < num; i++) {
         double x = Math.random() * CANVAS_SIZE;
         double y = Math.random() * CANVAS_SIZE;
         int size = (int)(Math.random() * 50);
         int luck = (int) (Math.random()*13);
         if (luck == 0) {
         StdDraw.setPenColor(StdDraw.RED);
         } else if (luck == 1) {
         StdDraw.setPenColor(StdDraw.GREEN);
         } else if (luck == 2) {
         StdDraw.setPenColor(StdDraw.BLUE);
         } else if (luck == 3) {
         StdDraw.setPenColor(StdDraw.YELLOW);
         } else if (luck == 4) {
         StdDraw.setPenColor(StdDraw.CYAN);
         } else if (luck == 5) {
         StdDraw.setPenColor(StdDraw.MAGENTA);
         } else if (luck == 6) {
         StdDraw.setPenColor(StdDraw.ORANGE);
         } else if (luck == 7) {
         StdDraw.setPenColor(StdDraw.PINK);
         } else  {
         StdDraw.setPenColor(StdDraw.GRAY);
         } 
         drawSnowflake(size, x, y);
      }
   }
   
   public static void drawSierpinski(int size, int x, int y) {
      if (size > 1) {
         StdDraw.line(x, y, size + x, y);
         StdDraw.line(x + size, y, x, y + size);
         StdDraw.line(x, size + y, x, y);
         drawSierpinski(size/2, x + size/2, y);
         drawSierpinski(size/2, x , y + size/2);
         drawSierpinski(size/2, x, y);
      }
   }
     

   public static void main ( String[] args )
   {
      //Setting up drawing canvas
      StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE); 
      StdDraw.setXscale(0, CANVAS_SIZE);        //Point (0, 0) is located in the top left corner of the canvas
      StdDraw.setYscale(CANVAS_SIZE, 0);           
      
      /*
        Double buffering allows for the entire image to be generated in a second buffer, which is only displayed when
        the entire image is done.  You can uncomment the line below to enable double buffer and your image will display
        faster.  (The completed image will pop in when 100% done instead of displaying each line as it is drawn.)
      */
      StdDraw.enableDoubleBuffering(); 
      
      //Call your snowflake method here
      StdDraw.setPenColor(StdDraw.BLUE);
       drawSnowflake(CANVAS_SIZE/4, CANVAS_SIZE/2, CANVAS_SIZE/2);
      
                  
      //Put your blizzard code here  (Create random snowflakes in a loop.)
      drawBlizzard();
      
      
      //Call your Sierpinski method here
      StdDraw.setPenColor(StdDraw.RED);
      drawSierpinski(CANVAS_SIZE, 0, 0);
      
      
            
      //Display final image
      StdDraw.show(); //This has to be called last (if you are using double buffering)
   }
}
