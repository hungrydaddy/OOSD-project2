/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import org.newdawn.slick.SlickException;
import project2.Elements.BasicObject;
import project2.Elements.Characters.Player.Player;
import project2.Elements.Environment.BasicTerrain;

import java.util.ArrayList;

public class Loader {
	// Converts a world coordinate to a tile coordinate,
	// and returns if that location is a blocked tile



	public enum Tag {
		PLAYER, MOVEABLE, ENEMY,
		FLOOR
	}


	public enum Directions {
		LEFT, RIGHT, UP, DOWN
	}


	// loading the required level
	public static BasicTerrain[][] parseLevel(ArrayList<String> lvlInfo, World world) throws SlickException {

		BasicTerrain[][] map = new BasicTerrain[world.height][world.width];

		// remove the first line - map size info
		lvlInfo.remove(0);

		// iterate to parse the map
		for (int i = 0;i < lvlInfo.size();i++) {
			String[] temp = lvlInfo.get(i).split(",");
			Integer row = Integer.parseInt(temp[1]);
			Integer column = Integer.parseInt(temp[2]);
			Integer x = row * App.TILE_SIZE;
			Integer y = column * App.TILE_SIZE;

			// applying different rules to objects and terrains
			switch (temp[0]) {
				case "floor":
					map[column][row] = new BasicTerrain(BasicTerrain.TerrainType.FLOOR, row, column, world);
					break;
				case "wall":
					map[column][row] = new BasicTerrain(BasicTerrain.TerrainType.WALL, row, column, world);
					break;
				case "target":
					map[column][row] = new BasicTerrain(BasicTerrain.TerrainType.TARGET, row, column, world);
					break;
				case "stone":
					map[column][row] = new BasicTerrain(BasicTerrain.TerrainType.STONE, row, column, world);
					break;
				case "player":
					map[column][row].occupy(new Player(world));
					break;
				default:
					map[column][row] = new BasicTerrain(BasicTerrain.TerrainType.EMPTY, row, column, world);
					break;
			}
		}

		return map;
	}





	// taking a snaoshot of the current world
	public static String snapshot(World world) {
		String extract = "";

		// TODO: finish snapshot
		return extract;
	}


	// get the object by its tag
	public static BasicObject findObjectByTag(Tag tag, World world) {
		BasicObject object = null;

		// iterate thru the map to find the object
		for (int i = 0;i < world.map.length;i++) {
			for (int j = 0;j < world.map[i].length;j++) {
				BasicTerrain target = world.map[i][j];
				if (target.getOccupiedObject() != null) {
					// if the object has the tag, return it
					if (target.getOccupiedObject().hasTag(Tag.PLAYER)) {
						return target.getOccupiedObject();
					}
				}
			}
		}

		return object;
	}

}