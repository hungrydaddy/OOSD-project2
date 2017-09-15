/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import org.newdawn.slick.SlickException;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;
import project2.Elements.Characters.Player.Player;
import project2.Elements.Environment.Blocks.Block;
import project2.Elements.Environment.Floor;
import project2.Elements.Environment.Target;
import project2.Elements.Environment.Wall;

import java.util.ArrayList;

public class Loader {
	// Converts a world coordinate to a tile coordinate,
	// and returns if that location is a blocked tile



	public enum Tag {
		PLAYER, MOVEABLE, ENEMY,
		FLOOR, TARGET, SWITCH, DOOR, WALL, CRACKED,
		BLOCK, ICE, TNT,
	}


	public enum Directions {
		LEFT, RIGHT, UP, DOWN
	}


	// loading the required level
	public static BasicCell[][] parseLevel(ArrayList<String> lvlInfo, World world) throws SlickException {

		BasicCell[][] map = new BasicCell[world.height][world.width];

		// initialise all the cells
		for (int i = 0;i < world.height; i++) {
			for (int j = 0;j < world.width; j++) {
				map[i][j] = new BasicCell(j, i, world);
			}
		}


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
					map[column][row].setObject(new Floor(world));
					break;
				case "wall":
					map[column][row].setObject(new Wall(world));
					break;
				case "target":
					map[column][row].setObject(new Target(world));
					break;
				case "stone":
					BasicObject block = new Block(world);
					block.setCell(map[column][row]);
					map[column][row].getObject().stackOn(block);
					break;
				case "player":
					BasicObject player = new Player(world);
					player.setCell(map[column][row]);
					map[column][row].getObject().stackOn(player);
					world.setPlayer(player);
					break;
				default:
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


}