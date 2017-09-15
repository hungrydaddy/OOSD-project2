package project2.Elements.Environment;

import org.lwjgl.Sys;
import org.newdawn.slick.Input;
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
    public void onCollide(BasicObject object, Loader.Directions direction) throws SlickException {

        // first collide with the child
        if (getChild() != null) {
            getChild().onCollide(object, direction);
        }

        // then stack up this object
        if (getChild() == null) {
            stackOn(object);
        }

    }
}
