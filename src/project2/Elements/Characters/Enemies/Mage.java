package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.App;
import project2.Controllers.Loader;
import project2.Controllers.World;

public class Mage extends Enemy {

    public Mage(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.MAGE);
        setObjectTile("mage");
    }



    @Override
    public void update(Loader.Directions direction) throws SlickException {
        // the method on specs to track down the player
        int distX = (getColumn() - getWorld().getPlayer().getColumn());
        int distY = (getRow() - getWorld().getPlayer().getRow());

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
                    move(Loader.Directions.RIGHT);
                } else {
                    move(Loader.Directions.LEFT);
                }
            }
        } else {
            // move in Y direction
            for (int i = 0;i < Math.abs(distY);i++) {
                if (sgnY == -1) { // player on the bottom, move down
                    move(Loader.Directions.DOWN);
                } else {
                    move(Loader.Directions.UP);
                }
            }
        }
    }


}
