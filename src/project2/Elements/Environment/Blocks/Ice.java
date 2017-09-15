package project2.Elements.Environment.Blocks;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;

public class Ice extends Block {

    public Ice(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.ICE);
        setObjectTile("ice");
    }
}
