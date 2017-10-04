/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class BasicCell {


    private BasicObject object;
    private Integer column;
    private Integer row;


    // constructor
    public BasicCell(Integer row, Integer column) throws SlickException {
        this.column = column;
        this.row = row;
    }


    /** renders the objects of the current cell
     */
    public void render(Graphics g) {
        if (object != null) {
            object.render(g);
        }
    }



    /** destroys this cell and free the memory
     */
    public void cellDestroy() {
        if (getObject() != null) {
            getObject().objectDestroy();
            object = null;
        }
    }





    /* getters and setters */
    public void setObject(BasicObject object) {
        this.object = object;
        object.setCell(this);
    }

    public BasicObject getObject() {
        return object;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }
}
