package project2.Elements.Environment;


import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;

public class Target extends Floor {

    // this object determines whether the game is won or not
    public Target(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.TARGET);
        setObjectTile("target");
    }



    /** detects if this target has a block on it
     * @return if the target has a block
     */
    public Boolean hasBlock() {
        if (this.getCell().getRootObject().childrenHaveTag(Extra.Tag.BLOCK)) {
            return true;
        }
        return false;
    }


}
