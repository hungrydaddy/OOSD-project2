package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;

public class Rogue extends Enemy {
    public Rogue(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.ROGUE);
        setObjectTile("rogue");
    }

    @Override
    public void update(Loader.Directions direction) throws SlickException {

    }
}
