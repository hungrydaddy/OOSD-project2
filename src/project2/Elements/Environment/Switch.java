package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicObject;


public class Switch extends Target {
    public Switch(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.SWITCH);
        setObjectTile("switch");
    }



    @Override
    public Boolean contact(BasicObject object, Loader.Directions direction) throws SlickException {
        super.contact(object, direction);
        return true;
    }
}
