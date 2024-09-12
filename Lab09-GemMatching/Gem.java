import java.awt.Font;
import java.util.*;
import java.awt.Color;

enum GemType {
    GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited
}


public class Gem 
{	
   private GemType type;
   private int points;
	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;
		
		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);
		
		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
   
   public Gem() {
      int random = (int) Math.floor(Math.random()*3);
      if (random == 0) {
         type = GemType.GREEN;
      } else if (random == 1) {
         type = GemType.BLUE;
      } else {
         type = GemType.ORANGE;
      }
      int[] nums = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
      random = (int) Math.floor(Math.random()*11);
      points = nums[random];
   }
   
   public Gem (GemType type, int points) {
      this.type = type;
      this.points = points;
   }
   
   public String toString() {
      return type + " " + points + ", " + type +", " + points;
   }
   
   public GemType getType() {
      return type;
   }
   
   public int getPoints() {
      return points;
   }
   
   public void draw(double x, double y) {
      String color = "";
      if (type == GemType.GREEN) {
         color = "gem_green.png";
      } else if (type == GemType.BLUE) {
         color = "gem_blue.png";
      } else {
         color = "gem_orange.png";
      }
      StdDraw.picture(x, y, color);
      String point = "" +points;
      StdDraw.setFont(new Font("SansSerif", Font.BOLD, 14));
      StdDraw.setPenColor(Color.WHITE);
      StdDraw.text(x, y, point);
   }
}
