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


    public void update(Input input, int delta) throws SlickException {
        if (!update) {
            return;
        }

        if (currentLevel.levelWon()) { // go to the next level if current is won
            startNextLevel();
            return;
        }

        currentLevel.update(input, delta);
    }


    public void render(Graphics g) throws SlickException {
        currentLevel.render(g);
    }







    /* helper functions */


    public void startNextLevel() throws SlickException {
        update = false;

        if (currentLvlNum == FINAL_LEVEL) {
            // won
            currentLevel = new Level(++currentLvlNum); // get to the win page
            return;
        } else {
            worldSnapshots.clear();
            currentLevel.levelDestory();
            currentLevel = null;
            currentLevel = new Level(++currentLvlNum);
        }

        update = true;
    }


}
