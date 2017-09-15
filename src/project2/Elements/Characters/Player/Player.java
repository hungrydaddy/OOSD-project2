package project2.Elements.Characters.Player;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicObject;
import project2.Elements.Characters.Character;

public class Player extends Character{


    public Player(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.PLAYER);
        setObjectTile("player_left");
    }




    // key listener
    @Override
    public void update(Input input, int delta) {
        if (getWorld() == null) {
            return;
        }

        // key detection
        if (input.isKeyPressed(Input.KEY_UP)) {
            this.move(Loader.Directions.UP);
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            this.move(Loader.Directions.DOWN);
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            this.move(Loader.Directions.LEFT);
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            this.move(Loader.Directions.RIGHT);
        }
    }



    @Override
    public void onCollide(BasicObject object, Loader.Directions direction) throws SlickException {
        if (object.hasTag(Loader.Tag.ENEMY)) {
            getWorld().gameOver();
        }
    }


}
