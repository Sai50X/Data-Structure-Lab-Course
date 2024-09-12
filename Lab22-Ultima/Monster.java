import java.awt.Font;
import java.awt.Color;
import java.util.*;
/**
 * The class that describes a monster in the Ultima game
 * 
 * @author Keith Vertanen
 * @author Michele Van Dyne - added commenting
 * @author YOUR NAME HERE!! - added monster movement logic
 */
public class Monster implements Runnable {

   public enum MonsterType {INVALID, SKELETON, ZOMBIE, BAT, GORK, TORNADO, EYE, LASER};
   public enum MoveType {RANDOM, STILL, AGGRO, STRAIGHT};  

   private MonsterType type;                       // type of monster
   private MoveType    moveType;                   // how does this monster move right now
   private int         x;                          // x location of monster
   private int         y;                          // y location of monster
   private int         sleepMs;                    // delay between times monster moves
   private int         hp;                         // hit points - damage sustained
   private int         attackDamage;               // damage monster causes
   private int         aggroRadius;                // how far the enemy can sense the avatar
   private World       world;                      // the world the monster moves about in
   private Stats       timer;
   private int         xChange;
   private int         yChange;                      // elapsed time for showing damage;
   
   /**
    * Construct a new monster
    * @param world     - the world the monster moves about in
    * @param code      - the string code that distinguishes types of monsters
    * @param x         - the x position of the monster
    * @param y         - the y position of the monster
    * @param hp        - hit points - damage sustained by the monster
    * @param damage    - damage the monster causes
    * @param sleepMs   - delay between time monster moves
    */
   public Monster(World world, String code, int x, int y, int hp, int attackDamage, int sleepMs, String moveCode, int aggroRadius) {
      this.world        = world;
      this.x            = x;
      this.y            = y;
      this.hp           = hp;
      this.attackDamage = attackDamage;
      this.sleepMs      = sleepMs;
      this.aggroRadius  = aggroRadius;
   
      if      (code.toUpperCase().equals("SK")) type = MonsterType.SKELETON;
      else if (code.toUpperCase().equals("ZB")) type = MonsterType.ZOMBIE;
      else if (code.toUpperCase().equals("BT")) type = MonsterType.BAT;
      else if (code.toUpperCase().equals("GK")) type = MonsterType.GORK;
      else if (code.toUpperCase().equals("TO")) type = MonsterType.TORNADO;
      else if (code.toUpperCase().equals("EYE")) type = MonsterType.EYE;
      else if (code.toUpperCase().equals("LZ")) type = MonsterType.LASER;
      else                                      type = MonsterType.INVALID;
      
      if      (moveCode.toUpperCase().equals("AGGRO"))  moveType = MoveType.AGGRO;
      else if (moveCode.toUpperCase().equals("RANDOM")) moveType = MoveType.RANDOM;
      else if (moveCode.toUpperCase().equals("STILL"))  moveType = MoveType.STILL;
      else if (moveCode.toUpperCase().equals("STRAIGHT"))  moveType = MoveType.STRAIGHT;
      else                                              moveType = MoveType.STILL;         
   }

   /**
    * The avatar has attacked a monster!
    * @param points    - number of hit points to be subtracted from monster
    */
   public void incurDamage(int points) {
      hp -= points;
      if (timer == null) timer = new Stats();
      timer.reset();
   }

   /**
    * Draw this monster at its current location
    */
   public void draw() {
      double drawX = (x + 0.5 - World.offSetX) * Tile.SIZE;
      double drawY = (y + 0.5 - World.offSetY) * Tile.SIZE;
      switch (type) {
         case SKELETON: StdDraw.picture(drawX, drawY, "img-skeleton.png", Tile.SIZE, Tile.SIZE); 
            break;
         case ZOMBIE:   StdDraw.picture(drawX, drawY, "img-zombie.png",   Tile.SIZE, Tile.SIZE); 
            break;
         case BAT:      StdDraw.picture(drawX, drawY, "img-bat.png",      Tile.SIZE, Tile.SIZE); 
            break;
         case GORK:     StdDraw.picture(drawX, drawY, "img-gork.png",     Tile.SIZE, Tile.SIZE); 
            break;
         case TORNADO:  StdDraw.picture(drawX, drawY, "img-tornado.png",  Tile.SIZE, Tile.SIZE); 
            break;
         case EYE: StdDraw.picture(drawX, drawY, "eyeofSauron.png" , Tile.SIZE, Tile.SIZE);
            break;
         case LASER: StdDraw.picture(drawX, drawY, "laser.png" , Tile.SIZE, Tile.SIZE);
            break;
         default:       StdDraw.picture(drawX, drawY, "img-blank.gif",    Tile.SIZE, Tile.SIZE); 
      }
   
      //Show health for a small amount of time after taking damage
      if ((timer != null) && (timer.elapsedTime() < World.DISPLAY_DAMAGE_SEC)) {
         String healthString = "" + hp;
         //Draw background box
         StdDraw.setPenColor(new Color(0, 0, 0, 150)); //black with alpha
         StdDraw.filledRectangle(drawX, drawY - Tile.SIZE/2 + 8, (int)(healthString.length()*4.5)+4, 8);                
         //With font size 14, each digit is 4 pixels wide and 8 pixels tall                       
         //Draw health text
         StdDraw.setPenColor(StdDraw.RED);
         StdDraw.setFont(new Font("SansSerif", Font.BOLD, 14));
         StdDraw.text(drawX, drawY - Tile.SIZE/2 + 8, healthString);
      }      
   }

   /**
    * Get the number of hit points the monster has remaining
    * @return the number of hit points
    */
   public int getHitPoints() {
      return hp;
   }

   /**
    * Get the amount of damage a monster causes
    * @return amount of damage monster causes
    */
   public int getAttackDamage() {
      return attackDamage;
   }

   /**
    * Get the x position of the monster
    * @return x position
    */
   public int getX() {
      return x;
   }

   /**
    * Get the y position of the monster
    * @return y position
    */
   public int getY() {
      return y;
   }

   /**
    * Set the new location of the monster
    * @param x the new x location
    * @param y the new y location
    */
   public void setLocation(int x, int y) {
      this.x = x;
      this.y = y;
   }

        
   /**
    * Thread that runs on loop moving the monster 
    * around as long as it is alive
    */
   public void run() {
      while (hp > 0)
      {         
         Tile nextLocation = getNextLocation();  
         if (nextLocation != null)          
               world.monsterMove(nextLocation.getX(), nextLocation.getY(), this);                             
         
         // ***** Thread sleeps for moment until next move *****
         try { Thread.sleep(sleepMs); }
         catch (InterruptedException e) { System.out.println(e); }            
      }
   }
   
   private Tile getNextLocation() {
        Tile[][] tiles = world.getTileMatrx();
        Tile answer = tiles[x][y];
        Tile avatarTile = world.getAvatarTile();
        Monster beam = null;
        double dist = Math.sqrt(Math.pow(Math.abs(avatarTile.getX()-x), 2) + Math.pow(Math.abs(avatarTile.getY()-y),2));
        if (dist <= aggroRadius && aggroRadius != -1) {
         this.moveType = MoveType.AGGRO;
        } 
        if (dist <= aggroRadius && this.type == MonsterType.EYE) {
          Tile next = getBFSMove();
          xChange = next.getX() - this.x;
          yChange = next.getY() - this.y;
          beam = new Monster(world, "LZ" , next.getX(), next.getY(), 1, 10, 1, "STRAIGHT", -1);
          world.addMonster(beam);
          // beam.draw();
          return null;
        } else if (this.type == MonsterType.EYE) {
          this.moveType = MoveType.AGGRO;
        }
        if (this.moveType == MoveType.RANDOM) {
           answer = getRandomMove();
        } else if (this.moveType == MoveType.AGGRO) {
            answer = getBFSMove();
        } else if (this.moveType == MoveType.STRAIGHT) {
            if (tiles[x+xChange][y+yChange].equals(avatarTile)) {
               world.removeMonster(beam);
            }
            answer = getStraightMove();
        }
        return answer;         
   }
   
   private Tile getRandomMove() {
      Tile[][] tiles = world.getTileMatrx();
      Tile chosenMove = tiles[x][y];         
      // ***** <YOUR CODE GOES HERE> *****
      // Return a Tile object indicating the next location for this monster who moves randomly
      ArrayList<Tile> moves = new ArrayList<>();
      
            if (tiles[x+1][y].isPassable()) {
               moves.add(tiles[x+1][y]);
            }
            if (tiles[x-1][y].isPassable()) {
               moves.add(tiles[x-1][y]);
            }
            if (tiles[x][y+1].isPassable()) {
               moves.add(tiles[x][y+1]);
            }
            if (tiles[x][y-1].isPassable()) {
               moves.add(tiles[x][y-1]);
            }
      Random rand = new Random();
      if (moves.size() == 0) {
         return chosenMove;
      }
      chosenMove = moves.get(rand.nextInt(moves.size()));
      return chosenMove;
   }
   
   private Tile getStraightMove() {
     Tile[][] tiles = world.getTileMatrx();
     Tile closest = null;
     Tile avatarTile = world.getAvatarTile();
     
     if (x + xChange < 0 || x + xChange > tiles.length || y + yChange < 0 || y + yChange > tiles[0].length) {
      return null;
      } else {
         closest = tiles[x+xChange][y+yChange];
      }  
      return closest;
   }
   
   private Tile getBFSMove() { 
      Tile[][] tiles = world.getTileMatrx();
      Tile avatarTile = world.getAvatarTile();
      Tile chosenMove = null;
      // ***** <YOUR CODE GOES HERE> *****  
      // Return a Tile object indicating the next location for this monster whose goal is to take the shortest path to the avatar.
      Queue<Tile> q = new LinkedList<>();
      HashSet<Tile> visited = new HashSet<>();
      HashMap<Tile,Tile> path = new HashMap<>();
      q.offer(tiles[x][y]);
      visited.add(tiles[x][y]);
      
      while (!q.isEmpty()) {
         Tile current = q.poll();
         if (current.getX() == avatarTile.getX() && current.getY() == avatarTile.getY()) {
            break;
         }
         ArrayList<Tile> moves = new ArrayList<>();
            if (tiles[current.getX()+1][current.getY()].isPassable()) {
               moves.add(tiles[current.getX()+1][current.getY()]);
            }
            if (tiles[current.getX()-1][current.getY()].isPassable()) {
               moves.add(tiles[current.getX()-1][current.getY()]);
            }
            if (tiles[current.getX()][current.getY()+1].isPassable()) {
               moves.add(tiles[current.getX()][current.getY()+1]);
            }
            if (tiles[current.getX()][current.getY()-1].isPassable()) {
               moves.add(tiles[current.getX()][current.getY()-1]);
            }
            if (tiles[current.getX()][current.getY()].isPassable()) {
               moves.add(tiles[current.getX()][current.getY()]);
            }
         for (int i = 0; i < moves.size(); i++) {
               if (!visited.contains(moves.get(i))) {
                  visited.add(moves.get(i));
                  q.offer(moves.get(i));
                  path.put(moves.get(i), current);
                  
               }
            }
      }
      Tile check = avatarTile;
      while (!(check.getX() == x && check.getY() == y)) {
         if (path.get(check).equals(tiles[x][y])) {
               chosenMove = check;
               break;
            }
            check = path.get(check);
      }
      
      return chosenMove;                
   }
   
   
     
}


