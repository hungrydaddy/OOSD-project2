package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;

public class Floor extends BasicObject {



    public Floor(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.FLOOR);
        setObjectTile("floor");
    }

    @Override
    public void update(Extra.Directions direction) throws SlickException {
    }



    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        Boolean canStack = true;

        // first collide with the child
        if (getChild() != null) {
            canStack = getChild().contact(object, direction);
        }

        // stack up this object
        if (getChild() == null && canStack) {
            stack(object);
        }

        return canStack;
    }
}
