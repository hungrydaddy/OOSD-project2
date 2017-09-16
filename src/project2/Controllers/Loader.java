/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import org.newdawn.slick.SlickException;
import project2.Elements.BasicCell;
import project2.Elements.Characters.Enemies.Mage;
import project2.Elements.Characters.Enemies.Rogue;
import project2.Elements.Characters.Enemies.Skeleton;
import project2.Elements.Characters.Player.Player;
import project2.Elements.Environment.*;
import project2.Elements.Environment.Blocks.Block;
import project2.Elements.Environment.Blocks.Ice;
import project2.Elements.Environment.Blocks.TNT;

import java.util.ArrayList;

public class Loader {
	// Converts a world coordinate to a tile coordinate,
	// and returns if that location is a blocked tile



	public enum Tag {
		PLAYER, MOVEABLE, ENEMY,
		SKELETON, ROGUE, MAGE,
		FLOOR, TARGET, SWITCH, DOOR, WALL, CRACKED,
		BLOCK, ICE, TNT
	}


	public enum Directions {
		LEFT, RIGHT, UP, DOWN
	}


	// loading the required level
	public static void parseLevel(ArrayList<String> lvlInfo, World world) throws SlickException {

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
					Target newTarget = new Target(world);
					world.addTarget(newTarget);
					map[column][row].setObject(newTarget);
					break;
				case "cracked":
					CrackedWall cracked = new CrackedWall(world);
					cracked.setCell(map[column][row]);
					cracked.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(cracked);
					break;
				case "switch":
					Switch mSwitch = new Switch(world);
					mSwitch.setCell(map[column][row]);
					mSwitch.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(mSwitch);
					world.setSwitch(mSwitch);
					break;
				case "door":
					Door door = new Door(world);
					door.setCell(map[column][row]);
					door.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(door);
					world.setDoor(door);
					break;
				case "stone":
					Block block = new Block(world);
					block.setCell(map[column][row]);
					block.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(block);
					break;
				case "ice":
					Ice ice = new Ice(world);
					ice.setCell(map[column][row]);
					ice.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(ice);
					break;
				case "tnt":
					TNT tnt = new TNT(world);
					tnt.setCell(map[column][row]);
					tnt.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(tnt);
					break;
				case "skeleton":
					Skeleton skeleton = new Skeleton(world);
					skeleton.setCell(map[column][row]);
					skeleton.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(skeleton);
					break;
				case "rogue":
					Rogue rogue = new Rogue(world);
					rogue.setCell(map[column][row]);
					rogue.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(rogue);
					break;
				case "mage":
					Mage mage = new Mage(world);
					mage.setCell(map[column][row]);
					mage.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(mage);
					break;
				case "player":
					Player player = new Player(world);
					player.setCell(map[column][row]);
					player.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(player);
					world.setPlayer(player);
					break;
				default:
					break;
			}
		}

		world.map = map;
	}





	// taking a snaoshot of the current world
	public static String snapshot(World world) {
		String extract = "";

		// TODO: finish snapshot
		return extract;
	}


}