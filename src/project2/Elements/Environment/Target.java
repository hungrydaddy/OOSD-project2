package project2.Elements.Environment;


import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicObject;

public class Target extends Floor {


    public Target(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.TARGET);
        setObjectTile("target");
    }


    @Override
    public Boolean contact(BasicObject object, Loader.Directions direction) throws SlickException {
        super.contact(object, direction);
        return true;
    }










    public Boolean hasBlock() {
        if (getChild() == null) {
            return false;
        } else if (getChild().hasTag(Loader.Tag.BLOCK)) {
            return true;
        }
        return false;
    }
}
