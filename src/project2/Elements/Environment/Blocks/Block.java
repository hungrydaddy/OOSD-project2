package project2.Elements.Environment.Blocks;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicObject;

public class Block extends BasicObject {

    public Block(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.BLOCK);
        setObjectTile("stone");
    }

    @Override
    public void update(Loader.Directions direction) throws SlickException {
    }


    @Override
    public void onCollide(BasicObject object, Loader.Directions direction) throws SlickException {
        move(direction);
    }
}
