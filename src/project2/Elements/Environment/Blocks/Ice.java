package project2.Elements.Environment.Blocks;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;

import java.util.Date;

public class Ice extends Block {

    private Boolean sliding = false;
    private Date lastMoveDate;
    private Extra.Directions lastDirection;
    private BasicCell lastStaticPostion;
    private final int MOVE_INTERVAL = 250;


    public Ice(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.ICE);
        setObjectTile("ice");
        lastStaticPostion = getCell();
    }


    /** handles updates for ice movements
     */
    @Override
    public void update() throws SlickException {
        if (!sliding) {
            lastStaticPostion = getCell();
            return;
        }

        if (lastMoveDate == null) {
            lastMoveDate = new Date();
        }

        Date now = new Date();

        long timeSinceLastMove = new Date().getTime() - lastMoveDate.getTime();
        if (timeSinceLastMove > MOVE_INTERVAL) {
            if (!move(lastDirection)) { // if the ice is not moving, set it not sliding
                sliding = false;
                lastMoveDate = null;
            } else {
                lastMoveDate = now;
            }
        }
    }



    /** move one step towards different directions, modified for ice movements
     * @param direction the direction the object is moving towards
     */
    @Override
    public Boolean move(Extra.Directions direction) throws SlickException {
        sliding = true;
        lastDirection = direction;

        return super.move(direction);
    }








    public BasicCell getLastStaticPostion() {
        return lastStaticPostion;
    }
}
