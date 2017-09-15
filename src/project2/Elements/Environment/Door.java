package project2.Elements.Environment;

import org.newdawn.slick.Input;
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
    public void update(Input input, int delta) {
    }

    @Override
    public void onCollide(BasicObject object, Loader.Directions direction) throws SlickException {
    }
}
