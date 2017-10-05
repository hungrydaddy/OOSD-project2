package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;

import java.util.Date;

public class Skeleton extends Enemy {

    private Extra.Directions movingDirection = Extra.Directions.UP;
    private Date lastMove;
    private final int MOVE_INTERVAL = 1000;


    public Skeleton(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.SKELETON);
        setObjectTile("skull");
    }



    /** handles movements and turning around for skeleton
     * @throws SlickException
     */
    @Override
    public void update() throws SlickException {
        if (lastMove == null) {
            lastMove = new Date();
        }

        Date now = new Date();


        long timeSinceLastMove = new Date().getTime() - lastMove.getTime();
        if (timeSinceLastMove > MOVE_INTERVAL) {
            if (!move(movingDirection)) {
                changeDirection();
            }
            lastMove = now;
        }

    }



    /** skeleton cannot push blocks, so return false when encountering blocks
     * @param direction the direction the object is moving towards
     * @return whether it can move or not
     * @throws SlickException
     */
    @Override
    public Boolean move(Extra.Directions direction) throws SlickException {
        BasicCell destination = getCellOnDirection(direction);

        if (destination.getRootObject().childrenHaveTag(Extra.Tag.BLOCK)) {
            return false;
        }
        return super.move(direction);
    }


    /** handles contact of skeleton, if incoming object is a block, skeleton cannot be moved
     * @param object    the incoming object
     * @param direction the direction that the object is moving towards
     * @return whether the skeleton can be moved or not
     * @throws SlickException
     */
    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        super.contact(object, direction);
        if (object.hasTag(Extra.Tag.BLOCK)) {
            return false;
        }
        return super.contact(object, direction);
    }



    private void changeDirection() {
        if (movingDirection == Extra.Directions.UP) {
            movingDirection = Extra.Directions.DOWN;
        } else {
            movingDirection = Extra.Directions.UP;
        }
    }

}
