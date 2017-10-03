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


    @Override
    public void update(Extra.Directions direction) throws SlickException {
        if (lastMove == null) {
            lastMove = new Date();
        }

        Date now = new Date();


        long timeSinceLastMove = new Date().getTime() - lastMove.getTime();
        if (timeSinceLastMove > MOVE_INTERVAL) {
            if (!move(movingDirection)) {
                changeDirection();
                move(movingDirection);
            }
            lastMove = now;
        }

    }



    @Override
    public Boolean move(Extra.Directions direction) throws SlickException {
        BasicCell destination = getCellOnDirection(direction);

        if (destination.getObject().childrenHaveTag(Extra.Tag.BLOCK)) {
            return false;
        }
        return super.move(direction);
    }


    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        super.contact(object, direction);
        if (object.hasTag(Extra.Tag.BLOCK)) {
            return false;
        }
        return true;
    }

    private void changeDirection() {
        if (movingDirection == Extra.Directions.UP) {
            movingDirection = Extra.Directions.DOWN;
        } else {
            movingDirection = Extra.Directions.UP;
        }
    }

}
