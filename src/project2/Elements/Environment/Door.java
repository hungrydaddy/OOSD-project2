package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;

public class Door extends BasicObject {

    public Door(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.DOOR);
        setObjectTile("door");
    }




    /** handles contacts between the incoming object and the current object
     * @param object the incoming object
     * @param direction the direction that the object is moving towards
     * @return false, door cannot be pushed away
     * @throws SlickException
     */
    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        return false;
    }







    /**
     * hides the door by detaching it temporarily
     */
    public void doorHide() {
        if (getParent() != null) {
            getParent().setChild(null);
            this.setParent(null);
        }
    }



    /**
     * shows the door
     */
    public void doorShow() {
        if (getParent() == null) {
            this.setParent(getCell().getRootObject().getLastChild());
            getParent().setChild(this);
        }
    }

}
