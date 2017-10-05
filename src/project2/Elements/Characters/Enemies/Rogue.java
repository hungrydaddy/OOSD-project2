package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;

public class Rogue extends Enemy {

    private Extra.Directions movingDirection;

    public Rogue(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.ROGUE);
        setObjectTile("rogue");
        movingDirection = Extra.Directions.LEFT;
    }



    /** handles movements update for the rogue, and direction changing
     * @throws SlickException
     */
    @Override
    public void update() throws SlickException {
        // auto movement for rogue
        if (!move(movingDirection)) {
            changeDirection();
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
