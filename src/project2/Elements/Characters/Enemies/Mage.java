package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;

public class Mage extends Enemy {
    public Mage(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.MAGE);
        setObjectTile("mage");
    }

    @Override
    public void update(Loader.Directions direction) throws SlickException {

    }
}
