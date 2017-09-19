package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicObject;


public class Switch extends Target {
    public Switch(Scene scene) throws SlickException {
        super(scene);
        getTags().add(Extra.Tag.SWITCH);
        setObjectTile("switch");
    }



    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        super.contact(object, direction);
        return true;
    }
}
