package project2.Elements.Environment;


import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicObject;

public class Target extends Floor {


    public Target(Scene scene) throws SlickException {
        super(scene);
        getTags().add(Extra.Tag.TARGET);
        setObjectTile("target");
    }


    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        super.contact(object, direction);
        return true;
    }










    public Boolean hasBlock() {
        if (getChild() == null) {
            return false;
        } else if (getChild().hasTag(Extra.Tag.BLOCK)) {
            return true;
        }
        return false;
    }
}
