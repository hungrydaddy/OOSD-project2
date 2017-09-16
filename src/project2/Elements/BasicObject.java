/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Elements;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import project2.Controllers.App;
import project2.Controllers.Loader;
import project2.Controllers.World;

import java.util.ArrayList;


abstract public class BasicObject {

    private World world;
    private Image objectTile;
    private BasicCell cell;
    private BasicObject parent;
    private BasicObject child;
    private ArrayList<Loader.Tag> tags;


    public BasicObject(World world) {
        this.world = world;
        this.tags = new ArrayList<>();
    }





    abstract public void update(Loader.Directions direction) throws SlickException;


    public void render(Graphics g) {
        if (objectTile != null) { // render this tile first
            objectTile.draw(getColumn() * App.TILE_SIZE + world.X_offset, getRow() * App.TILE_SIZE + world.Y_offset);
        }
        if (child != null) { // if something is stacked on this object, continue to render it
            child.render(g);
        }
    }







    /* internal functions */

    public void stack(BasicObject object) throws SlickException {
        if (getChild() == null) {
            object.unstack();
            object.setParent(this);
            object.setCell(cell);
            this.setChild(object);
        }
    }


    // move off the parent
    public void unstack() throws SlickException {
        getParent().setChild(null);
        setParent(null);
    }



    // moving towards different directions
    public Boolean move(Loader.Directions direction) throws SlickException {
        BasicCell destination = getCellOnDirection(direction);
        if (destination.getObject().contact(this, direction)) {
            return true;
        }
        return false;
    }




    abstract public Boolean contact(BasicObject object, Loader.Directions direction) throws SlickException;





    // get a cell in a direction
    public BasicCell getCellOnDirection(Loader.Directions direction) {
        BasicCell targetCell;

        switch (direction) {
            case UP:
                if (getRow() - 1 < 0) {
                    targetCell = null;
                } else {
                    targetCell = world.map[getRow() - 1][getColumn()];
                }
                break;
            case DOWN:
                if (getRow() + 1 >= world.getHeight()) {
                    targetCell = null;
                } else {
                    targetCell = world.map[getRow() + 1][getColumn()];
                }
                break;
            case RIGHT:
                if (getColumn() + 1 >= world.getWidth()) {
                    targetCell = null;
                } else {
                    targetCell = world.map[getRow()][getColumn() + 1];
                }
                break;
            case LEFT:
                if (getColumn() - 1 < 0) {
                    targetCell = null;
                } else {
                    targetCell = world.map[getRow()][getColumn() - 1];
                }
                break;
            default:
                targetCell = null;
                break;
        }

        return targetCell;
    }




    public Boolean childrenHaveTag(Loader.Tag tag) {
        if (hasTag(tag)) {
            return true;
        } else if (getChild() != null) {
            return getChild().childrenHaveTag(tag);
        }
        return false;
    }



    // destroy this object
    public void objectDestroy() {
        if (child != null) {
            child.objectDestroy();
            child = null;
        }
        world = null;
        cell = null;
        parent = null;
        objectTile = null;
        tags.clear();
        tags = null;
    }




    /* encapsulations */
    public Integer getColumn() {
        return getCell().getColumn();
    }

    public Integer getRow() {
        return getCell().getRow();
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
