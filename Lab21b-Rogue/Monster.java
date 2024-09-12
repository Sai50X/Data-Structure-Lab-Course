/*************************************************************************
 *  Compilation:  javac Monster.java
 * 
 *************************************************************************/
import java.util.*;

public class Monster {
    private Game game;
    private Dungeon dungeon;
    private int N;
    private Site[][] map;

    // constructor - save away some useful information
    public Monster(Game game) {
        this.game    = game;
        this.dungeon = game.getDungeon();
        this.N       = dungeon.size();
        map = new Site[N][N];
        for (int r = 0; r < N; r++)
            for (int c = 0; c < N; c++)
                map[r][c] = new Site(r, c);  

    }

    // TAKE A RANDOM LEGAL MOVE
    // YOUR MAIN TASK IS TO RE-IMPLEMENT THIS METHOD TO DO SOMETHING INTELLIGENT
    public Site move() {
        Site monster = map[game.getMonsterSite().i()][game.getMonsterSite().j()];
        Site rogue   = map[game.getRogueSite().i()][game.getRogueSite().j()];
        
        Site move = getValidMove();  //This is a bad idea.
        //Use a better strategy to determine the next move.

        return move;
    }

    public Site getValidMove() {
        Site monster = game.getMonsterSite();
        Site rogue   = game.getRogueSite();
        Site move    = null;

        // take random legal move
        Queue<Site> sites = new LinkedList<>();
        sites.offer(monster);
        HashSet<Site> visited = new HashSet<>();
        visited.add(monster);
        HashMap<Site, Site> path = new HashMap<>(); 
        while (!sites.isEmpty()) {
            
            Site current = sites.poll();
            if (current.equals(rogue)) {
               path.put(rogue, current);
               break;
            }
            ArrayList<Site> moves = new ArrayList<>();
            for (int i = 0; i < N; i++) {
               for (int j = 0; j < N; j++) {
                Site site = new Site(i, j);
                if (dungeon.isLegalMove(current, site) && (current.i() == i || current.j() == j) && !visited.contains(site)) {
                    moves.add(site);
                  }
               }
            }
            for (int i = 0; i < moves.size(); i++) {
               if (!visited.contains(moves.get(i))) {
                  visited.add(moves.get(i));
                  sites.offer(moves.get(i));
                  path.put(moves.get(i), current);
                  
               }
            }
        }
        Site check = rogue;
        while (!check.equals(monster)) {
            if (path.get(check).equals(monster)) {
               move = check;
               break;
            }
            check = path.get(check);
        }
        return move;
    }
    
    public Site getRandomValidMove() {
        Site monster = game.getMonsterSite();
        Site rogue   = game.getRogueSite();
        Site move    = null;

        // take random legal move
        int n = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Site site = new Site(i, j);
                if (dungeon.isLegalMove(monster, site)) {
                    n++;
                    if (Math.random() <= 1.0 / n) move = site;
                }
            }
        }
        return move;
    }

}


