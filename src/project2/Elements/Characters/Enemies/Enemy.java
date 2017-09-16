package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicObject;
import project2.Elements.Characters.Character;

abstract public class Enemy extends Character {

    public Enemy(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.ENEMY);
    }


    @Override
    public Boolean contact(BasicObject object, Loader.Directions direction) throws SlickException {
        if (object.hasTag(Loader.Tag.PLAYER)) { // if contacts with the player
            getWorld().getGame().restartCurrentLevel();
        }
        super.contact(object, direction);
        return true;
    }
}
