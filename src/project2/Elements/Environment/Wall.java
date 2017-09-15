package project2.Elements.Environment;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicObject;

public class Wall extends BasicObject {

    public Wall(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.WALL);
        setObjectTile("wall");
    }

    @Override
    public void update(Loader.Directions direction) throws SlickException {

    }


    @Override
    public void onCollide(BasicObject object, Loader.Directions direction) throws SlickException {

    }
}
