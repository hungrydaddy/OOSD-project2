package project2.Elements.Characters.Enemies;

import org.lwjgl.Sys;
import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicCell;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Skeleton extends Enemy {

    private Loader.Directions movingDirection = Loader.Directions.UP;

    private Date lastMove;
    private final int MOVE_INTERVAL = 1000;

    public Skeleton(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.SKELETON);
        setObjectTile("skull");
    }


    @Override
    public void update(Loader.Directions direction) throws SlickException {
        if (lastMove == null) {
            //TODO: sort out time
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
    public Boolean move(Loader.Directions direction) throws SlickException {
        BasicCell destination = getCellOnDirection(direction);

        if (destination == null) {
            return false;
        }

        if (destination.getObject().childrenHaveTag(Loader.Tag.BLOCK)) {
            return false;
        }
        return super.move(direction);
    }




    private void changeDirection() {
        if (movingDirection == Loader.Directions.UP) {
            movingDirection = Loader.Directions.DOWN;
        } else {
            movingDirection = Loader.Directions.UP;
        }
    }

}
