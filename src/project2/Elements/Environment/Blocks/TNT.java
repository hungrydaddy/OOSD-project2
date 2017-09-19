package project2.Elements.Environment.Blocks;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import project2.Controllers.App;
import project2.Controllers.Extra;
import project2.Controllers.Scene;

import java.util.Timer;
import java.util.TimerTask;

public class TNT extends Block {

    private Boolean exploded = false;

    public TNT(Scene scene) throws SlickException {
        super(scene);
        getTags().add(Extra.Tag.TNT);
        setObjectTile("tnt");
    }




    public Boolean usedTNT() {
        return this.exploded;
    }

    public void explode() throws SlickException {
        exploded = true;
        unstack();
    }

}
