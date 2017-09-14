/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */


package project2.Elements.Environment;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import project2.Controllers.App;
import project2.Controllers.World;
import project2.Elements.BasicObject;


public class BasicTerrain {


    private Boolean solid;
    private Boolean occupied;
    private TerrainType terrainType;


    private World world;
    private BasicObject occupiedObject;
    private Image terrianTile;
    private Integer column;
    private Integer row;


    public enum TerrainType {
        EMPTY, FLOOR, WALL, TARGET, STONE
    }


    // constructor
    public BasicTerrain(TerrainType terrian, Integer row, Integer column, World world) throws SlickException {
        this.world = world;
        this.column = column;
        this.row = row;


        this.terrainType = terrian;
        occupied = false;
        occupiedObject = null;
        switch (terrian) {
            case FLOOR:
                solid = false;
                terrianTile = new Image("res/floor.png");
                break;
            case WALL:
                solid = true;
                terrianTile = new Image("res/wall.png");
                break;
            case TARGET:
                solid = false;
                terrianTile = new Image("res/target.png");
                break;
            // for now, stone does not hv physical properties, so take it as a terrain
            case STONE:
                solid = false;
                terrianTile = new Image("res/stone.png");
                break;
            case EMPTY:
                solid = true;
                terrianTile = null;
        }

    }

    public void update(Input input, int delta) {
        // nothing for now
    }



    // renders both the terrain and occpuied object
    public void render(Graphics g) {
        if (terrianTile != null) {
            terrianTile.draw(row * App.TILE_SIZE + world.X_offset, column * App.TILE_SIZE + world.Y_offset);
        }
        if (occupied) {
            occupiedObject.render(g);
        }
    }




    // handles the occupation of this terrain
    public Boolean occupy(BasicObject incomingObject) {
        if (occupied) {
            return false;
        }
        occupiedObject = incomingObject;
        incomingObject.setOccupiedTerrain(this);
        occupied = true;
        return true;
    }


    // handles removal of an object
    public Boolean removeObject() {
        if (!occupied) {
            return false;
        }
        occupiedObject.setOccupiedTerrain(null);
        occupiedObject = null;
        occupied = false;
        return true;
    }



    public BasicObject getOccupiedObject() {
        return occupiedObject;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    public Boolean isSolid() {
        return solid;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }
}


