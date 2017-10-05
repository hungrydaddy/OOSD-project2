package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;
import project2.Elements.Characters.Character;

abstract public class Enemy extends Character {

    public Enemy(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.ENEMY);
    }



    /** handles contact from enemies or player
     * @param object    the incoming object
     * @param direction the direction that the object is moving towards
     * @return if the incoming object is an enemy, they can stack on, otherwise returns false
     * @throws SlickException
     */
    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        if (object.hasTag(Extra.Tag.PLAYER)) { // if player contacted with an enemy
            getScene().getPlayer().setPlayerDead();
        }
        if (object.hasTag(Extra.Tag.ENEMY)) { // enemy can stack on enemy
            stack(object);
            return true;
        }
        return false;
    }


}
