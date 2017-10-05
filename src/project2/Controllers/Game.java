/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;



import org.newdawn.slick.*;

import java.util.ArrayList;

public class Game {

    private int currentLvlNum = 0;
    private Level currentLevel;
    private ArrayList<String> worldSnapshots = new ArrayList<>();
    private final int FINAL_LEVEL = 4;
    private Boolean update = true;



    public Game() throws SlickException {
        // initialising the game, starting from lvl 0
        currentLevel = new Level(currentLvlNum);
    }


    /** handles the update of the current level, and jumps to next level if current is won
     * @param input input from the key detection
     * @throws SlickException
     */
    public void update(Input input) throws SlickException {
        if (!update) {
            return;
        }

        if (currentLevel.levelWon() || input.isKeyPressed(Input.KEY_X)) { // press X to jump to the next level
            startNextLevel();
            return;
        }

        currentLevel.update(input);
    }



    public void render(Graphics g) throws SlickException {
        currentLevel.render(g);
    }







    /**
     * autonomously starts the next level, stops if max level reached
     * @throws SlickException
     */
    public void startNextLevel() throws SlickException {
        update = false;

        if (currentLvlNum == FINAL_LEVEL) { // won
            currentLevel = new Level(++currentLvlNum); // goes to the "you win" level
            return;
        } else {
            worldSnapshots.clear();
            currentLevel.levelDestroy();
            currentLevel = null;
            currentLevel = new Level(++currentLvlNum);
        }

        update = true;
    }


}
