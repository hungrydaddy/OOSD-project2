package project2.Elements.Characters;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicObject;


abstract public class Character extends BasicObject {

    public Character(Scene scene) throws SlickException {
        super(scene);
    }



    /* helper functions */


    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        move(direction);
        return true;
    }

}
