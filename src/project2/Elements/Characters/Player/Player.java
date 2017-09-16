package project2.Elements.Characters.Player;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicObject;
import project2.Elements.Characters.Character;

public class Player extends Character{

    private Boolean playerDead = false;

    public Player(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.PLAYER);
        setObjectTile("player_left");
    }




    // key listener
    @Override
    public void update(Loader.Directions direction) throws SlickException {
        this.move(direction);
    }



    @Override
    public Boolean contact(BasicObject object, Loader.Directions direction) throws SlickException {
        if (object.hasTag(Loader.Tag.ENEMY)) { //if an enemy contacted player
            playerDied();
        }
        return true;
    }


    public Boolean playerDead() {
        return playerDead;
    }

    public void playerDied() {
        playerDead = true;
    }

}
