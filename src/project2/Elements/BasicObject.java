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

import java.util.ArrayList;


abstract public class BasicObject {

    private World world;
    private Image objectTile;
    private Boolean canGoThrough;
    private BasicCell cell;
    private BasicObject parent;
    private BasicObject child;
    private ArrayList<Loader.Tag> tags;


    public BasicObject(World world) {
        this.world = world;
        this.tags = new ArrayList<>();
    }





    abstract public void update(Input input, int delta) throws SlickException;


    public void render(Graphics g) {
        if (objectTile != null) { // render this tile first
            objectTile.draw(getRow() * App.TILE_SIZE + world.X_offset, getColumn() * App.TILE_SIZE + world.Y_offset);
        }
        if (child != null) { // if something is stacked on this object, continue to render it
            child.render(g);
        }
    }







    /* internal functions */
    public void stackOn(BasicObject object) throws SlickException {
        if (getChild() == null) {
            object.getParent().setChild(null);
            object.setParent(this);
            object.setCell(cell);
            this.setChild(object);
        }
    }



    // moving towards different directions
    public Boolean move(Loader.Directions direction) throws SlickException {
        BasicCell destination = getCellOnDirection(direction);
        destination.getObject().onCollide(this, direction);
        return true;
    }


    abstract public void onCollide(BasicObject object, Loader.Directions direction) throws SlickException;





    // get a terrain in a direction
    public BasicCell getCellOnDirection(Loader.Directions direction) {
        BasicCell targetCell;

        switch (direction) {
            case UP:
                if (getColumn() - 1 < 0) {
                    targetCell = null;
                } else {
                    targetCell = world.map[getColumn() - 1][getRow()];
                }
                break;
            case DOWN:
                if (getColumn() + 1 >= world.height) {
                    targetCell = null;
                } else {
                    targetCell = world.map[getColumn() + 1][getRow()];
                }
                break;
            case RIGHT:
                if (getRow() + 1 >= world.width) {
                    targetCell = null;
                } else {
                    targetCell = world.map[getColumn()][getRow() + 1];
                }
                break;
            case LEFT:
                if (getRow() - 1 < 0) {
                    targetCell = null;
                } else {
                    targetCell = world.map[getColumn()][getRow() - 1];
                }
                break;
            default:
                targetCell = null;
                break;
        }

        return targetCell;
    }





    /* encapsulations */
    public Integer getColumn() {
        return this.cell.getColumn();
    }

    public Integer getRow() {
        return this.cell.getRow();
    }

    public Boolean canGoThrough() {
        return canGoThrough;
    }

    public void setCell(BasicCell cell) {
        this.cell = cell;
    }

    public BasicObject getChild() {
        return child;
    }

    public void setChild(BasicObject child) {
        this.child = child;
    }

    public BasicObject getParent() {
        return parent;
    }

    public void setParent(BasicObject parent) {
        this.parent = parent;
    }

    public ArrayList<Loader.Tag> getTags() {
        return tags;
    }

    public World getWorld() {
        return world;
    }

    public BasicCell getCell() {
        return cell;
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
