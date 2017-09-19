package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicObject;

public class Wall extends BasicObject {

    public Wall(Scene scene) throws SlickException {
        super(scene);
        getTags().add(Extra.Tag.WALL);
        setObjectTile("wall");
    }

    @Override
    public void update(Extra.Directions direction) throws SlickException {

    }


    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        return false;
    }
}
