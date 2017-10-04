package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;


public class Switch extends Target {
    public Switch(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.SWITCH);
        setObjectTile("switch");
    }



    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        return super.contact(object, direction);
    }
}
