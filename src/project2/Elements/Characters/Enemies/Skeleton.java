package project2.Elements.Characters.Enemies;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;

public class Skeleton extends Enemy {

    public Skeleton(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.SKELETON);
        setObjectTile("skull");
    }

    @Override
    public void update(Loader.Directions direction) throws SlickException {

    }
}
