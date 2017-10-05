package project2.Elements.Characters.Player;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;
import project2.Elements.Characters.Character;



public class Player extends Character{


    private Boolean playerDead = false;

    public Player(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.PLAYER);
        setObjectTile("player_left");
    }




    /** if an enemy walks into the player, player dies
     * @param object    the incoming object
     * @param direction the direction that the object is moving towards
     * @return false, player cannot be pushed away
     * @throws SlickException
     */
    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        if (object.hasTag(Extra.Tag.ENEMY)) { //if an enemy contacted player
            setPlayerDead();
        }
        return false;
    }







    /* getters and setters */

    public Boolean playerIsDead() {
        return playerDead;
    }

    public void setPlayerDead() {
        playerDead = true;
    }

}
