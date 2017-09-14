package project2.Elements.Characters;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import project2.Controllers.App;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicObject;
import project2.Elements.Environment.BasicTerrain;

import java.util.ArrayList;



abstract public class Character extends BasicObject {

    public Character(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.MOVEABLE);
    }



    /* helper functions */


    // moving towards different directions
    public Boolean move(Loader.Directions direction) {

        BasicTerrain destination = getTerrainOnDirection(direction);
        BasicObject obstacle = destination.getOccupiedObject();

        // if destination is empty, do not move
        if (destination == null) {
            return false;
        }

        // if there is an obstacle in front
        if (obstacle != null) {
            /*
            if (obstacle.move(direction)) {
                // if the obstacle moved, move too
                this.move(direction);
            } else {
                // if the obstacle did not move, do not move
                return false;
            }*/
            return false;
        }


        if (!destination.isSolid()) {
            this.getOccupiedTerrain().removeObject();
            destination.occupy(this);
        } else {
            return false;
        }


        return true;
    }


    public void onCollide(BasicObject object, Loader.Directions direction) throws SlickException {
        // TODO: move
    }

}
