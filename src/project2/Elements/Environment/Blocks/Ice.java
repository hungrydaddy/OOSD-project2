package project2.Elements.Environment.Blocks;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;

import java.util.Date;

public class Ice extends Block {

    private Boolean sliding = false;
    private Date lastMoveDate;
    private Extra.Directions lastDirection;
    private final int MOVE_INTERVAL = 250;


    public Ice(Scene scene) throws SlickException {
        super(scene);
        getTags().add(Extra.Tag.ICE);
        setObjectTile("ice");
    }



    @Override
    public void update(Extra.Directions direction) throws SlickException {
        if (!sliding) {
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



    @Override
    public Boolean move(Extra.Directions direction) throws SlickException {
        sliding = true;
        lastDirection = direction;

        return super.move(direction);
    }
}
