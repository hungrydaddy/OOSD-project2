/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;



import org.newdawn.slick.*;

import java.util.ArrayList;

public class Game {

    private int currentLvl;
    private ArrayList<String> worldSnapshots = new ArrayList<>();
    private ArrayList<String> levels = new ArrayList<>();
    private ArrayList<Extra.Tag> bannedTags = new ArrayList<>();
    private World currentWorld;
    private Boolean update = true;



    public Game() throws SlickException {
        // initialising the levels
        setupLevels();
        // initialising the game, starting from lvl 0
        currentLvl = 0;
        currentWorld = new World(levels.get(currentLvl), this);
    }


    public void update(Input input, int delta) throws SlickException {
        if (!update) {
            return;
        }

        currentWorld.update(input, delta);

        if (currentWorld.levelWon()) { // check whether the level was won
            startNextLevel();
            return;
        }

        if (currentWorld.getPlayer().playerDead()) { // check if the player died
            restartCurrentLevel();
        }

    }


    public void render(Graphics g) throws SlickException {
        currentWorld.render(g);

        // showing the number of moves
        g.drawString("Moves: " + worldSnapshots.size(), 20.0f, 20.0f);
    }




    private void setupLevels() {
        levels.add("0");
        levels.add("1");
        levels.add("2");
        levels.add("3");
        levels.add("4");
        levels.add("5");
    }







    /* helper functions */
    public void restartCurrentLevel() throws SlickException {
        startLevel(levels.get(currentLvl));
    }



    // go backwards
    public void rewind() throws SlickException {
        if (worldSnapshots.isEmpty()) { // if the player has no past moves
            return;
        } else {
            update = false;

            currentWorld.worldDestroy();
            currentWorld = null;
            // restoring the last movement
            currentWorld = new World(levels.get(currentLvl), this, worldSnapshots.get(worldSnapshots.size() - 1));
            // delete the last move
            worldSnapshots.remove(worldSnapshots.size() - 1);

            update = true;
        }

    }



    public void saveLastMove() {
        worldSnapshots.add(Extra.snapshot(currentWorld));
    }


    // purge everything and restart
    private void startLevel(String level) throws SlickException {
        update = false;

        bannedTags.clear();
        worldSnapshots.clear();
        currentWorld.worldDestroy();
        currentWorld = null;
        currentWorld = new World(level, this);

        update = true;
    }


    public void startNextLevel() throws SlickException {
        if (currentLvl == 5) {
            // won
            return;
        } else {
            startLevel(levels.get(++currentLvl));
            return;
        }
    }



    public void banTag(Extra.Tag tag) {
        bannedTags.add(tag);
    }


    public Boolean tagBanned(Extra.Tag tag) {
        if (bannedTags.contains(tag)) {
            return true;
        }
        return false;
    }

}
