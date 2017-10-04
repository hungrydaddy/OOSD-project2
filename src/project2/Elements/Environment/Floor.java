package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;

public class Floor extends BasicObject {



    public Floor(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.FLOOR);
        setObjectTile("floor");
    }


    /** handles contacts between the incoming object and the current object -- floor
     * @param object the incoming object
     * @param direction the direction that the object is moving towards
     * @return Boolean, if the current object can be pushed away
     */
    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        Boolean canMove = true;

        // first collide with the child
        if (getChild() != null) {
            canMove = getChild().contact(object, direction);
        }

        // stack up this object
        if (getChild() == null && canMove) {
            stack(object);
        }

        return canMove;
    }
}
