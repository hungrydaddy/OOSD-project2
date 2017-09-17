package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicObject;

public class Door extends BasicObject {

    public Door(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.DOOR);
        setObjectTile("door");
    }

    @Override
    public void update(Loader.Directions direction) throws SlickException {

    }


    @Override
    public Boolean contact(BasicObject object, Loader.Directions direction) throws SlickException {
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
