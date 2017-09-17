package project2.Elements.Environment.Blocks;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicCell;

import java.util.Date;

public class Ice extends Block {

    private Boolean sliding = false;
    private Date lastMove;
    private Loader.Directions lastDirection;
    private final int MOVE_INTERVAL = 250;


    public Ice(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.ICE);
        setObjectTile("ice");
    }



    @Override
    public void update(Loader.Directions direction) throws SlickException {
        if (!sliding) {
            return;
        }

        if (lastMove == null) {
            lastMove = new Date();
        }

        Date now = new Date();


        long timeSinceLastMove = new Date().getTime() - lastMove.getTime();
        if (timeSinceLastMove > MOVE_INTERVAL) {
            System.out.println(sliding);
            if (!move(lastDirection)) { // if the ice is not moving, set it not sliding
                sliding = false;
                lastMove = null;
            } else {
                lastMove = now;
            }
        }
    }



    @Override
    public Boolean move(Loader.Directions direction) throws SlickException {
        sliding = true;
        lastDirection = direction;

        return super.move(direction);
    }
}
