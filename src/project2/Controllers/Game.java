/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;



import org.newdawn.slick.*;

import java.util.ArrayList;

public class Game {

    private int currentLvl;
    private int numberOfMoves;
    private ArrayList<String> worldSnapshots;
    private World currentWorld;


    public Game() throws SlickException {
        // initialising the game, starting from lvl 0
        currentLvl = 3;
        numberOfMoves = 0;
        currentWorld = new World(currentLvl, this);
        worldSnapshots = new ArrayList<>();
    }


    public void update(Input input, int delta) throws SlickException {
        currentWorld.update(input, delta);

        if (currentWorld.levelWon()) {
            if (currentLvl == 5) {
                // won
            } else {
                startLevel(++currentLvl);
            }
        }

        if (currentWorld.getPlayer().playerDead()) {
            restartCurrentLevel();
        }

        if (input.isKeyPressed(Input.KEY_R)) {
            restartCurrentLevel();
        }
        if (input.isKeyPressed(Input.KEY_Z)) {
            rewind();
        }
    }


    public void render(Graphics g) throws SlickException {
        currentWorld.render(g);

        // showing the number of moves
        g.drawString("Moves: " + numberOfMoves, 20.0f, 20.0f);
    }










    /* helper functions */
    public void restartCurrentLevel() throws SlickException {
        startLevel(currentLvl);
    }



    // go backwards
    private void rewind() {
    }


    // purge everything and restart
    private void startLevel(int level) throws SlickException {
        worldSnapshots.clear();
        currentWorld.worldDestroy();
        currentWorld = null;
        currentWorld = new World(level, this);
        numberOfMoves = 0;
    }


}
