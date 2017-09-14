/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Elements;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import project2.Controllers.App;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.Environment.BasicTerrain;

import java.util.ArrayList;


abstract public class BasicObject {

    private World world;
    private Image objectTile;
    private Boolean canGoThrough;
    private BasicTerrain occupiedTerrain;
    private BasicObject stackedObject;
    private ArrayList<Loader.Tag> tags;


    public BasicObject(World world) {
        this.world = world;
        this.tags = new ArrayList<>();
    }





    abstract public void update(Input input, int delta);


    public void render(Graphics g) {
        if (objectTile != null) { // render this tile first
            objectTile.draw(getRow() * App.TILE_SIZE + world.X_offset, getColumn() * App.TILE_SIZE + world.Y_offset);
        }
        if (stackedObject != null) { // if something is stacked on this object, continue to render it
            stackedObject.render(g);
        }
    }







    /* internal functions */
    public void stackOn(BasicObject object) {
        if (stackedObject == null) {
            stackedObject = object;
        }
    }

    abstract public void onCollide(BasicObject object, Loader.Directions direction) throws SlickException;






    // get a terrain in a direction
    public BasicTerrain getTerrainOnDirection(Loader.Directions direction) {
        BasicTerrain targetTerrain;

        switch (direction) {
            case UP:
                if (getColumn() - 1 < 0) {
                    targetTerrain = null;
                } else {
                    targetTerrain = world.map[getColumn() - 1][getRow()];
                }
                break;
            case DOWN:
                if (getColumn() + 1 >= world.height) {
                    targetTerrain = null;
                } else {
                    targetTerrain = world.map[getColumn() + 1][getRow()];
                }
                break;
            case RIGHT:
                if (getRow() + 1 >= world.width) {
                    targetTerrain = null;
                } else {
                    targetTerrain = world.map[getColumn()][getRow() + 1];
                }
                break;
            case LEFT:
                if (getRow() - 1 < 0) {
                    targetTerrain = null;
                } else {
                    targetTerrain = world.map[getColumn()][getRow() - 1];
                }
                break;
            default:
                targetTerrain = null;
                break;
        }

        return targetTerrain;
    }





    /* encapsulations */
    public Integer getColumn() {
        return this.occupiedTerrain.getColumn();
    }

    public Integer getRow() {
        return this.occupiedTerrain.getRow();
    }

    public Boolean canGoThrough() {
        return canGoThrough;
    }

    public void setOccupiedTerrain(BasicTerrain target) {
        occupiedTerrain = target;
    }

    public BasicObject getStackedObject() {
        return stackedObject;
    }

    public ArrayList<Loader.Tag> getTags() {
        return tags;
    }

    public World getWorld() {
        return world;
    }

    public BasicTerrain getOccupiedTerrain() {
        return occupiedTerrain;
    }

    public Boolean hasTag(Loader.Tag tag) {
        if (tags.contains(tag)) {
            return true;
        }
        return false;
    }

    public void setObjectTile(String name) throws SlickException {
        this.objectTile = new Image("res/" + name + ".png");
    }
}
