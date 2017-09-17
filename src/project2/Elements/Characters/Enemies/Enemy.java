package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.World;
import project2.Elements.BasicObject;
import project2.Elements.Characters.Character;

abstract public class Enemy extends Character {

    public Enemy(World world) throws SlickException {
        super(world);
        getTags().add(Extra.Tag.ENEMY);
    }


    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        if (object.hasTag(Extra.Tag.PLAYER)) { // if player contacted with an enemy
            getWorld().getPlayer().playerDied();
        }
        super.contact(object, direction);
        return true;
    }
}
