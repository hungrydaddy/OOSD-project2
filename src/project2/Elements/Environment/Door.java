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

    @Override
    public void update(Extra.Directions direction) throws SlickException {

    }


    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        return false;
    }









    // detach the door
    public void doorHide() {
        if (getParent() != null) {
            getParent().setChild(null);
            this.setParent(null);
        }
    }


    // attach the door
    public void doorShow() {
        if (getParent() == null) {
            this.setParent(getCell().getObject());
            getParent().setChild(this);
        }
    }

}
