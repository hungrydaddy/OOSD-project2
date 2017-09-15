package project2.Elements.Characters;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;


abstract public class Character extends BasicObject {

    public Character(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.MOVEABLE);
    }



    /* helper functions */



    public void onCollide(BasicObject object, Loader.Directions direction) throws SlickException {
        // TODO: move
    }

}
