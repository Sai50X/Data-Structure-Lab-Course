import java.util.*;
import java.io.*;

public class BinaryMaze {

   public static class Location {
      public int row;
      public int col;
      public int distance;
      
      public Location (int row, int col, int distance) {
         this.row = row;
         this.col = col;
         this.distance = distance;
      }
   
   }
   
   public static void main (String[] args) {
      helper("maze.txt");
   }
   
   public static void helper (String name) {
       Scanner input = null;
      try {
         input = new Scanner(new File(name));
      } catch (Exception e) {
         System.out.println("File not found!");
      }
      String[] rAndC = input.nextLine().split(" ");
      int rows = Integer.valueOf(rAndC[0]);
      int cols = Integer.valueOf(rAndC[1]);
      int[][] grid = new int[rows][cols];
      boolean[][] visited = new boolean[rows][cols];
      String[] connections = input.nextLine().split(" ");
      int count = 0;
      while(connections.length > 2) {
         for (int i = 0; i < connections.length; i++) {
            grid[count][i] = Integer.valueOf(connections[i]);
            visited[count][i] = false;
         }
         connections = input.nextLine().split(" ");
         count++;
      }
      
      int startR = Integer.valueOf(connections[0]);
      int startC = Integer.valueOf(connections[1]);
      connections = input.nextLine().split(" ");
      int endR = Integer.valueOf(connections[0]);
      int endC = Integer.valueOf(connections[1]);
      Queue<Location> temp = new LinkedList<>();
      temp.offer(new Location(startR, startC, 0));
      visited[startR][startC] = true;
      int track = 0;
      
      while (!temp.isEmpty()) {
         Location current = temp.poll();
         if (current.row == endR && current.col == endC) {
            System.out.println(current.distance);
            break;
         }
         Location[] paths = new Location[]{new Location(current.row - 1, current.col, 0), new Location(current.row + 1, current.col, 0), new Location(current.row, current.col - 1, 0), new Location(current.row, current.col+1, 0)};
         for (int u = 0; u < paths.length; u++) {
            if (paths[u].row >= 0 && paths[u].row <= rows && paths[u].col >= 0 && paths[u].col <= cols) {
               if (grid[paths[u].row][paths[u].col] == 1 && visited[paths[u].row][paths[u].col] == false) {
                  Location add = new Location(paths[u].row, paths[u].col, current.distance + 1);
                  temp.offer(add);
                  visited[paths[u].row][paths[u].col] = true;
               }
            }
         }
      } 
   }
}