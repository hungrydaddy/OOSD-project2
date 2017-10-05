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




    /** the algorithm from the specs to track down player
     * @throws SlickException
     */
    @Override
    public void update() throws SlickException {
        // the method on specs to track down the player
        int distX = (getColumn() - getScene().getPlayer().getColumn());
        int distY = (getRow() - getScene().getPlayer().getRow());

        Extra.Directions directionX;
        Extra.Directions directionY;

        if (distX < 0) {
            directionX = Extra.Directions.RIGHT;
        } else {
            directionX = Extra.Directions.LEFT;
        }

        if (distY < 0) {
            directionY = Extra.Directions.DOWN;
        } else {
            directionY = Extra.Directions.UP;
        }


        if (Math.abs(distX) > Math.abs(distY) && canMoveInDirection(directionX)) {
            // move in X direction
            move(directionX);
        } else if (canMoveInDirection(directionY)) {
            // move in Y direction
            move(directionY);
        }
    }




    // a function to check if mage can move in a specific direction
    private Boolean canMoveInDirection(Extra.Directions direction) {
        Boolean hasWall = getCellOnDirection(direction).getRootObject().childrenHaveTag(Extra.Tag.WALL);
        Boolean hasBlock = getCellOnDirection(direction).getRootObject().childrenHaveTag(Extra.Tag.BLOCK);
        if (hasWall || hasBlock) {
            return false;
        }
        return true;
    }



}
