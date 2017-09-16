package project2.Elements.Environment.Blocks;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import project2.Controllers.App;
import project2.Controllers.Loader;
import project2.Controllers.World;

import java.util.Timer;
import java.util.TimerTask;

public class TNT extends Block {

    private Image explosionTile;
    private Boolean isExploding = false;
    private final int EXPLOSION_TIME = 400;

    public TNT(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.TNT);
        setObjectTile("tnt");
        explosionTile = new Image("res/explosion.png");
    }


    @Override
    public void render(Graphics g) {
        super.render(g);
        if (this.isExploding) { // if the item is exploding, show the explosion tile
            explosionTile.draw(getCellOnDirection(Loader.Directions.LEFT).getColumn() * App.TILE_SIZE + getWorld().X_offset, getCellOnDirection(Loader.Directions.UP).getRow() * App.TILE_SIZE + getWorld().Y_offset);
        }
    }


    public void explode() throws SlickException {
        isExploding = true;

        getWorld().lateRenderAdd(this);

        this.unstack();

        // set up a timer to render the explosion
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isExploding = false;
                getWorld().lateRenderClear();
            }
        }, EXPLOSION_TIME);

    }





}
