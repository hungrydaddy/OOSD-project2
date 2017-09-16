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
        currentLvl = 1;
        numberOfMoves = 0;
        currentWorld = new World(currentLvl, this);
        worldSnapshots = new ArrayList<>();
    }


    public void update(Input input, int delta) throws SlickException {
        if (input.isKeyPressed(Input.KEY_R)) {
            restartCurrentLevel();
        }
        if (input.isKeyPressed(Input.KEY_Z)) {
            rewind();
        }

        Boolean validMove = false;
        if (input.isKeyPressed(Input.KEY_UP)) {
            currentWorld.update(Loader.Directions.UP);
            validMove = true;
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            currentWorld.update(Loader.Directions.DOWN);
            validMove = true;
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            currentWorld.update(Loader.Directions.LEFT);
            validMove = true;
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            currentWorld.update(Loader.Directions.RIGHT);
            validMove = true;
        }


        if (validMove) {
            numberOfMoves++;
            // check if the game was won

            // door toggle
            if (currentWorld.getSwitch() != null) {
                if (currentWorld.getSwitch().hasBlock()) {
                    currentWorld.getDoor().doorHide();
                } else {
                    currentWorld.getDoor().doorShow();
                }
            }

            if (currentWorld.levelWon() == true) {
                if (currentLvl == 5) {
                    // won
                } else {
                    startLevel(++currentLvl);
                }
            }

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
        currentWorld = new World(level, this);
        numberOfMoves = 0;
    }


}
