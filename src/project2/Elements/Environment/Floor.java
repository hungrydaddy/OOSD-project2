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
    public void update(Input input, int delta) {
        // nothing to update
    }


    @Override
    public void onCollide(BasicObject object, Loader.Directions direction) throws SlickException {
        /*
        if (getStackedObject() == null) { // if nothing stacked on, collide now
            stackOn(object);
        } else { // if something is stacked on, collide with that one
            getStackedObject().onCollide(object, direction);
        }*/
        stackOn(object);
    }
}
