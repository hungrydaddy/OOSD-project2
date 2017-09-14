/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Elements;


import org.lwjgl.input.Controllers;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import project2.Controllers.App;
import project2.Controllers.Loader;
import project2.Controllers.World;

import java.util.ArrayList;


public class BasicObject {

    private World world;
    private Image objectTile;
    private Boolean moveable;
    private BasicTerrain occupiedTerrain;
    private ObjectType objectType;
    private ArrayList<Loader.Tag> tags;



    public enum ObjectType {
        STONE, PLAYER_LEFT
    }

    public BasicObject(ObjectType object, World world) throws SlickException {
        this.world = world;
        this.objectType = object;

        this.tags = new ArrayList<>();

        switch (object) {
            case STONE:
                moveable = true;
                objectTile = new Image("res/stone.png");
                break;
            case PLAYER_LEFT:
                moveable = true;
                objectTile = new Image("res/player_left.png");
                tags.add(Loader.Tag.PLAYER);
                break;
        }
    }


    // key listener
    public void update(Input input, int delta) {
        if (world == null) {
            return;
        }


        if (input.isKeyPressed(Input.KEY_UP)) {
            this.move(World.Directions.UP);
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            this.move(World.Directions.DOWN);
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            this.move(World.Directions.LEFT);
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            this.move(World.Directions.RIGHT);
        }
    }



    public void render(Graphics g) {
        if (objectTile != null) {
            objectTile.draw(getRow() * App.TILE_SIZE + world.X_offset, getColumn() * App.TILE_SIZE + world.Y_offset);
        } else {
            return;
        }
    }







    /* helper functions */


    // moving towards different directions
    public Boolean move(World.Directions direction) {
        BasicTerrain destination = getTerrainOnDirection(direction);
        BasicObject obstacle = destination.getOccupiedObject();

        // if destination is empty, do not move
        if (destination == null) {
            return false;
        }

        // if there is an obstacle in front
        if (obstacle != null) {
            if (obstacle.move(direction)) {
                // if the obstacle moved, move too
                this.move(direction);
            } else {
                // if the obstacle did not move, do not move
                return false;
            }
        }


        if (this.moveable && !destination.isSolid()) {
            this.occupiedTerrain.removeObject();
            destination.occupy(this);
        } else {
            return false;
        }

        return true;
    }


    // get a terrain in a direction
    public BasicTerrain getTerrainOnDirection(World.Directions direction) {
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







    // encapsulations
    public Integer getColumn() {
        return this.occupiedTerrain.getColumn();
    }

    public Integer getRow() {
        return this.occupiedTerrain.getRow();
    }

    public Boolean isMoveable() {
        return moveable;
    }

    public void setOccupiedTerrain(BasicTerrain target) {
        occupiedTerrain = target;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public Boolean hasTag(Loader.Tag tag) {
        if (tags.contains(tag)) {
            return true;
        }
        return false;
    }

}
