package project2.Elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;

public class BasicCell {


    private BasicObject object;
    private Integer column;
    private Integer row;


    // constructor
    public BasicCell(Integer row, Integer column) throws SlickException {
        this.column = column;
        this.row = row;
    }


    // renders both the terrain and occpuied object
    public void render(Graphics g) {
        if (object != null) {
            object.render(g);
        }
    }



    // destroy this cell
    public void cellDestroy() {
        if (getObject() != null) {
            getObject().objectDestroy();
            object = null;
        }
    }



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
