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





    abstract public void update(Extra.Directions direction) throws SlickException;


    public void render(Graphics g) {
        if (objectTile != null) { // render this tile first
            objectTile.draw(getColumn() * App.TILE_SIZE + scene.X_offset, getRow() * App.TILE_SIZE + scene.Y_offset);
        }
        if (getChild() != null) { // if something is stacked on this object, continue to render it
            getChild().render(g);
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
        if (getChild() != null) { // if has child, set child's parent to this object's parent
            getChild().setParent(getParent());
            getParent().setChild(getChild());
            setChild(null);
        } else {
            getParent().setChild(null);
        }
        setParent(null);
    }



    // moving towards different directions
    public Boolean move(Extra.Directions direction) throws SlickException {
        BasicCell destination = getCellOnDirection(direction);
        return destination.getObject().contact(this, direction);
    }




    abstract public Boolean contact(BasicObject object, Extra.Directions direction) throws SlickException;





    // get a cell in a direction
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




    public Boolean childrenHaveTag(Extra.Tag tag) {
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
        scene = null;
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
