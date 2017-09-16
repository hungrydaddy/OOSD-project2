package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicObject;

public class Floor extends BasicObject {



    public Floor(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.FLOOR);
        setObjectTile("floor");
    }

    @Override
    public void update(Loader.Directions direction) throws SlickException {
    }



    @Override
    public Boolean contact(BasicObject object, Loader.Directions direction) throws SlickException {
        Boolean needToStack = true;

        // first collide with the child
        if (getChild() != null) {
            needToStack = getChild().contact(object, direction);
        }

        // stack up this object
        if (getChild() == null && needToStack) {
            stack(object);
        }

        return true;
    }
}
