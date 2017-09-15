package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;

public class CrackedWall extends Wall {

    public CrackedWall(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.CRACKED);
        setObjectTile("cracked_wall");
    }

}
