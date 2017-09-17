package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.World;

public class Rogue extends Enemy {

    private Extra.Directions movingDirection;

    public Rogue(World world) throws SlickException {
        super(world);
        getTags().add(Extra.Tag.ROGUE);
        setObjectTile("rogue");
        movingDirection = Extra.Directions.LEFT;
    }



    @Override
    public void update(Extra.Directions direction) throws SlickException {
        // auto movement for rogue
        if (!move(movingDirection)) {
            changeDirection();
            move(movingDirection);
        }
    }





    private void changeDirection() {
        if (movingDirection == Extra.Directions.LEFT) {
            movingDirection = Extra.Directions.RIGHT;
        } else {
            movingDirection = Extra.Directions.LEFT;
        }
    }



}
