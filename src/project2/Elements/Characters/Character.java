package project2.Elements.Characters;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;


abstract public class Character extends BasicObject {

    public Character(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
    }


    /**
     * @param object    the incoming object
     * @param direction the direction that the object is moving towards
     * @return whether the current object has moved or not
     * @throws SlickException
     */
    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        return move(direction);
    }

}
