package project2.Elements.Environment.Blocks;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;

public class Block extends BasicObject {

    public Block(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.BLOCK);
        setObjectTile("stone");
    }


    /** handles contacts between the incoming object and the current object
     * @param object the incoming object
     * @param direction the direction that the object is moving towards
     * @return if the block is moved, return true, if not, return false
     */
    @Override
    public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException {
        if (object.hasTag(Extra.Tag.ICE)) {
            return false;
        }
        return move(direction);
    }
}
