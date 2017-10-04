/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Elements;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import project2.Controllers.App;
import project2.Controllers.Extra;
import project2.Controllers.Scene;

import java.util.ArrayList;


abstract public class BasicObject {

    private Scene scene;
    private Image objectTile;
    private BasicCell cell;
    private BasicObject parent;
    private BasicObject child;
    private ArrayList<Extra.Tag> tags;


    public BasicObject(Scene scene, BasicCell cell) {
        this.scene = scene;
        this.cell = cell;
        this.tags = new ArrayList<>();
    }




    /** handles updates for current object
     * @param direction the direction that the object is moving towards
     */
    public void update(Extra.Directions direction) throws SlickException {
        // do nothing by default
    }




    /** handles contacts between the incoming object and the current object
     * @param object the incoming object
     * @param direction the direction that the object is moving towards
     * @return Boolean, if the current object can be pushed away
     */
    abstract public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException;


    /** renders the current object, from a tile file.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g) {
        if (objectTile != null) { // render this tile first
            float xPosition = getColumn() * App.TILE_SIZE + scene.getX_offset();
            float YPosition = getRow() * App.TILE_SIZE + scene.getY_offset();
            objectTile.draw(xPosition, YPosition);
        }
        if (getChild() != null) { // if something is stacked on this object, continue to render it
            getChild().render(g);
        }
    }




    /** move one step towards different directions
     * @param direction the direction the object is moving towards
     */
    public Boolean move(Extra.Directions direction) throws SlickException {
        BasicCell destination = getCellOnDirection(direction);
        return destination.getObject().contact(this, direction);
    }



    /** stacks an object onto the current object
     * @param object stacks this input object onto the current object
     */
    public void stack(BasicObject object) throws SlickException {
        if (getChild() == null) {
            object.unstack();
            object.setParent(this);
            object.setCell(cell);
            this.setChild(object);
        }
    }


    // move off the parent
    protected void unstack() throws SlickException {
        if (getChild() != null) { // if has child, set child's parent to this object's parent
            getChild().setParent(getParent());
            getParent().setChild(getChild());
            setChild(null);
        } else {
            getParent().setChild(null);
        }
        setParent(null);
    }







    /** fetches the cell on a direction relative to the current object
     * @param direction 4 directions to choose
     * @return a cell object, can be used for further purposes
     */
    public BasicCell getCellOnDirection(Extra.Directions direction) {
        BasicCell targetCell;

        switch (direction) {
            case UP:
                if (getRow() - 1 < 0) {
                    targetCell = null;
                } else {
                    targetCell = scene.getMap()[getRow() - 1][getColumn()];
                }
                break;
            case DOWN:
                if (getRow() + 1 >= scene.getHeight()) {
                    targetCell = null;
                } else {
                    targetCell = scene.getMap()[getRow() + 1][getColumn()];
                }
                break;
            case RIGHT:
                if (getColumn() + 1 >= scene.getWidth()) {
                    targetCell = null;
                } else {
                    targetCell = scene.getMap()[getRow()][getColumn() + 1];
                }
                break;
            case LEFT:
                if (getColumn() - 1 < 0) {
                    targetCell = null;
                } else {
                    targetCell = scene.getMap()[getRow()][getColumn() - 1];
                }
                break;
            default:
                targetCell = null;
                break;
        }

        return targetCell;
    }




    /** detects if any children to this object has a specific tag
     * @param tag which tag to check
     * @return whether any children has this tag or not
     */
    public Boolean childrenHaveTag(Extra.Tag tag) {
        if (hasTag(tag)) {
            return true;
        } else if (getChild() != null) {
            return getChild().childrenHaveTag(tag);
        }
        return false;
    }



    /**
     * destroys this object and free the memory by dereferencing
     */
    public void objectDestroy() {
        if (child != null) {
            child.objectDestroy();
            child = null;
        }
        scene = null;
        cell = null;
        parent = null;
        objectTile = null;
        tags.clear();
        tags = null;
    }






    /* getters and setters */
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

    public BasicObject getLastChild() {
        if (getChild() == null) {
            return this;
        }
        return getChild().getLastChild();
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

    public ArrayList<Extra.Tag> getTags() {
        return tags;
    }

    public Scene getScene() {
        return scene;
    }

    public BasicCell getCell() {
        return cell;
    }

    public Boolean hasTag(Extra.Tag tag) {
        if (tags.contains(tag)) {
            return true;
        }
        return false;
    }

    public void setObjectTile(String name) throws SlickException {
        this.objectTile = new Image("res/" + name + ".png");
    }
}
