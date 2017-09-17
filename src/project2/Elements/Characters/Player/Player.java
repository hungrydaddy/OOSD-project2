package project2.Elements.Characters.Player;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.World;
import project2.Elements.BasicObject;
import project2.Elements.Characters.Character;

public class Player extends Character{

    private Boolean playerDead = false;

    public Player(World world) throws SlickException {
        super(world);
        getTags().add(Extra.Tag.PLAYER);
        setObjectTile("player_left");
    }




    // key listener
    @Override
    public void update(Extra.Directions direction) throws SlickException {
        this.move(direction);
    }



    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        if (object.hasTag(Extra.Tag.ENEMY)) { //if an enemy contacted player
            playerDied();
        }
        return false;
    }


    public Boolean playerDead() {
        return playerDead;
    }

    public void playerDied() {
        playerDead = true;
    }

}
