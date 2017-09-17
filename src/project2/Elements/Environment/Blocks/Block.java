package project2.Elements.Environment.Blocks;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.World;
import project2.Elements.BasicObject;

public class Block extends BasicObject {

    public Block(World world) throws SlickException {
        super(world);
        getTags().add(Extra.Tag.BLOCK);
        setObjectTile("stone");
    }

    @Override
    public void update(Extra.Directions direction) throws SlickException {
    }


    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        if (object.hasTag(Extra.Tag.ICE)) {
            return false;
        }
        move(direction);
        return true;
    }
}
