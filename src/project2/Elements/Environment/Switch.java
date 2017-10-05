package project2.Elements.Environment;

import org.newdawn.slick.SlickException;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;


public class Switch extends Target {

    // this object can turn on or off the floor
    public Switch(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.SWITCH);
        setObjectTile("switch");
    }



    /** handles updates, door show and door hide
     * @throws SlickException
     */
    @Override
    public void update() throws SlickException {
        if (hasBlock()) {
            getScene().getDoor().doorHide();
        } else {
            getScene().getDoor().doorShow();
        }

        super.update();
    }
}
