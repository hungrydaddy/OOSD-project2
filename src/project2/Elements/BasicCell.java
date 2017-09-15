package project2.Elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import project2.Controllers.World;

public class BasicCell {


    private World world;
    private BasicObject object;
    private Integer column;
    private Integer row;


    // constructor
    public BasicCell(Integer row, Integer column, World world) throws SlickException {
        this.world = world;
        this.column = column;
        this.row = row;
    }


    // renders both the terrain and occpuied object
    public void render(Graphics g) {
        if (object != null) {
            object.render(g);
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
