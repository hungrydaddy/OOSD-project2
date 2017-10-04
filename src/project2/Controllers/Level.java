/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;



import org.newdawn.slick.*;

import java.util.ArrayList;

public class Level {

    private ArrayList<String> sceneSnapshots = new ArrayList<>();
    private Scene currentScene;
    private Boolean update = true;
    private Boolean usedTNT = false;




    public Level(int lvl_num) throws SlickException {
        currentScene = new Scene(false, Extra.loadLevelFile(lvl_num));
        sceneSnapshots.add(Extra.snapshot(currentScene)); // adding the initial scene
    }


    public void render(Graphics g) throws SlickException {
        currentScene.render(g);

        // showing the number of moves
        g.drawString("Moves: " + getNumberOfMoves(), 20.0f, 20.0f);
    }



    /**
     * updates for a level, handles key input for player, detects if player died or TNT used
     */
    public void update(Input input) throws SlickException {
        if (input.isKeyPressed(Input.KEY_R)) {
            rewind(true);
        }
        if (input.isKeyPressed(Input.KEY_Z)) {
            rewind(false);
        }


        Boolean playerMoved = false;
        // updating the player, save if valid move
        if (input.isKeyPressed(Input.KEY_UP)) {
            saveLastScene();
            currentScene.getPlayer().update(Extra.Directions.UP);
            playerMoved = true;
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            saveLastScene();
            currentScene.getPlayer().update(Extra.Directions.DOWN);
            playerMoved = true;
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            saveLastScene();
            currentScene.getPlayer().update(Extra.Directions.LEFT);
            playerMoved = true;
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            saveLastScene();
            currentScene.getPlayer().update(Extra.Directions.RIGHT);
            playerMoved = true;
        }



        if (!update) {
            return;
        } else { // when updating
            currentScene.update(playerMoved);
            if (currentScene.getPlayer().playerDead()) { // check if the player died
                rewind(true);
            }
            if (currentScene.usedTNT()) { // if tnt used, set true
                usedTNT = true;
            }
        }

    }





    /** goes one step backward, or even restart
     * @param restart a state, whether to restart the whole level or to go back one step
     */
    public void rewind(Boolean restart) throws SlickException {
        if (!restart && sceneSnapshots.size() == 1) { // if there is only the initial scene
            return;
        }
        update = false;
        currentScene.sceneDestroy();
        currentScene = null;
        // restoring the last movement
        if (!restart) { // delete the last move
            String snapshot = sceneSnapshots.get(sceneSnapshots.size() - 1);
            currentScene = new Scene(usedTNT, snapshot);
            sceneSnapshots.remove(sceneSnapshots.size() - 1);
        } else { // restart the level
            usedTNT = false;
            String snapshot = sceneSnapshots.get(0);
            currentScene = new Scene(usedTNT, snapshot);
            sceneSnapshots.clear();
            sceneSnapshots.add(snapshot);
        }
        update = true;
    }



    /**
     * destroy the current level, free up the memory
     */
    public void levelDestroy() {
        sceneSnapshots.clear();
        sceneSnapshots = null;
        currentScene.sceneDestroy();
        currentScene = null;
    }


    /**
     * detects if the current level is won, will get inside current scene to determine
     */
    public Boolean levelWon() {
        return currentScene.levelWon();
    }


    private void saveLastScene() {
        sceneSnapshots.add(Extra.snapshot(currentScene));
    }


    private int getNumberOfMoves() {
        return sceneSnapshots.size() - 1;
    }
}
