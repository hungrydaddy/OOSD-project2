/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;



import org.newdawn.slick.*;

import java.util.ArrayList;

public class Game {

    private int currentLvl;
    private int numberOfMoves = 0;
    private ArrayList<String> worldSnapshots = new ArrayList<>();
    private ArrayList<String> levels = new ArrayList<>();
    private World currentWorld;
    private Boolean update = true;


    public Game() throws SlickException {
        // initialising the game, starting from lvl 0
        currentLvl = 0;
        currentWorld = new World(currentLvl, this);
    }


    public void update(Input input, int delta) throws SlickException {
        if (!update) {
            return;
        }

        currentWorld.update(input, delta);

        if (currentWorld.levelWon()) {
            if (currentLvl == 5) {
                // won
                return;
            } else {
                startLevel(++currentLvl);
                return;
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
        update = false;
        update = true;
    }


    // purge everything and restart
    private void startLevel(int level) throws SlickException {
        update = false;
        worldSnapshots.clear();
        currentWorld.worldDestroy();
        currentWorld = null;
        currentWorld = new World(level, this);
        numberOfMoves = 0;

        update = true;
    }


}
