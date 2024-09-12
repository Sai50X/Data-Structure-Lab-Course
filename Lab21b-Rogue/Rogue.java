/*************************************************************************
 *  Compilation:  javac Rogue.java
 * 
 *************************************************************************/
import java.util.*;
 
public class Rogue {
    private Game game;
    private Dungeon dungeon;
    private int N;

    // constructor - save away some useful information
    public Rogue(Game game) {
        this.game    = game;
        this.dungeon = game.getDungeon();
        this.N       = dungeon.size();
    }


    // TAKE A RANDOM LEGAL MOVE
    // YOUR MAIN TASK IS TO RE-IMPLEMENT THIS METHOD TO DO SOMETHING INTELLIGENT
    public Site move() {
        Site monster = game.getMonsterSite();
        Site rogue   = game.getRogueSite();

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
        Site answer = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Site site = new Site(i, j);
                if (dungeon.isLegalMove(rogue, site)) {
                   sites.offer(site);
                }
            }
        }
        while (!sites.isEmpty()) {
            Site curr = sites.poll();
            if (answer == null) {
               answer = curr;
            } else if (curr.manhattanTo(monster) > answer.manhattanTo(monster)) {
               answer = curr;
            }
        }
        return answer;
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
                if (dungeon.isLegalMove(rogue, site)) {
                    n++;
                    if (Math.random() <= 1.0 / n) move = site;
                }
            }
        }
        return move;
    }
    
}

