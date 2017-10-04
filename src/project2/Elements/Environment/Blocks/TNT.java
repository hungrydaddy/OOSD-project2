package project2.Elements.Environment.Blocks;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import project2.Controllers.App;
import project2.Controllers.Extra;
import project2.Controllers.Scene;
import project2.Elements.BasicCell;

import java.util.Timer;
import java.util.TimerTask;

public class TNT extends Block {

    private Boolean exploded = false;

    public TNT(Scene scene, BasicCell cell) throws SlickException {
        super(scene, cell);
        getTags().add(Extra.Tag.TNT);
        setObjectTile("tnt");
    }



    /** @return if the tnt was exploded or not
     */
    public Boolean usedTNT() {
        return this.exploded;
    }


    /** triggers the explosion effect, and unstack the object
     */
    public void explode() throws SlickException {
        exploded = true;
        unstack();
    }

}
