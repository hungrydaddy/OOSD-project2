/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class BasicCell {


    private BasicObject rootObject;
    private Integer column;
    private Integer row;



    public BasicCell(Integer row, Integer column) throws SlickException {
        this.column = column;
        this.row = row;
    }


    /** renders the objects of the current cell
     */
    public void render(Graphics g) {
        if (rootObject != null) {
            rootObject.render(g);
        }
    }



    /** destroys this cell and free the memory
     */
    public void cellDestroy() {
        if (getRootObject() != null) {
            getRootObject().objectDestroy();
            rootObject = null;
        }
    }






    /* getters and setters */
    public void setRootObject(BasicObject object) {
        this.rootObject = object;
        object.setCell(this);
    }

    public BasicObject getRootObject() {
        return rootObject;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }
}
