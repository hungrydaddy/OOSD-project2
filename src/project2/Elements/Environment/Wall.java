package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;

public class Wall extends BasicObject {

    public Wall(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.WALL);
        setObjectTile("wall");
    }


    /** handles contacts between the incoming object and the current object
     * @param object the incoming object
     * @param direction the direction that the object is moving towards
     * @return false, because the wall cannot be pushed away
     */
    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        return false;
    }
}
