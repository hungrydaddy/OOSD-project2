package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;
import project2.Elements.Environment.Blocks.TNT;

public class CrackedWall extends Wall {

    public CrackedWall(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.CRACKED);
        setObjectTile("cracked_wall");
    }


    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        if (object.hasTag(Extra.Tag.TNT)) { // if collided with TNT, remove this wall

            unstack();

            ((TNT) object).explode();
        }
        return false;
    }
}
