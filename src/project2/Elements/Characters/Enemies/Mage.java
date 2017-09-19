package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;

public class Mage extends Enemy {

    public Mage(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.MAGE);
        setObjectTile("mage");
    }



    @Override
    public void update(Extra.Directions direction) throws SlickException {
        // the method on specs to track down the player
        int distX = (getColumn() - getScene().getPlayer().getColumn());
        int distY = (getRow() - getScene().getPlayer().getRow());

        int sgnX;
        int sgnY;
        if (distX < 0) {
            sgnX = -1;
        } else {
            sgnX = 1;
        }

        if (distY < 0) {
            sgnY = -1;
        } else {
            sgnY = 1;
        }

        if (Math.abs(distX) > Math.abs(distY)) {
            // move in X direction
            for (int i = 0;i < Math.abs(distX);i++) {
                if (sgnX == -1) { // player on the right, move right
                    move(Extra.Directions.RIGHT);
                } else {
                    move(Extra.Directions.LEFT);
                }
            }
        } else {
            // move in Y direction
            for (int i = 0;i < Math.abs(distY);i++) {
                if (sgnY == -1) { // player on the bottom, move down
                    move(Extra.Directions.DOWN);
                } else {
                    move(Extra.Directions.UP);
                }
            }
        }
    }


}
