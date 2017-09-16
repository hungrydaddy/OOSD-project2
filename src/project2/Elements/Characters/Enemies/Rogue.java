package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicCell;

public class Rogue extends Enemy {

    private Loader.Directions movingDirection;

    public Rogue(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.ROGUE);
        setObjectTile("rogue");
        movingDirection = Loader.Directions.LEFT;
    }



    @Override
    public void update(Loader.Directions direction) throws SlickException {
        // auto movement for rogue
        if (!move(movingDirection)) {
            changeDirection();
            move(movingDirection);
        }
    }





    private void changeDirection() {
        if (movingDirection == Loader.Directions.LEFT) {
            movingDirection = Loader.Directions.RIGHT;
        } else {
            movingDirection = Loader.Directions.LEFT;
        }
    }



}
