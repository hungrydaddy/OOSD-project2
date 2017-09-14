/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import project2.Helper.BasicObject;
import project2.Helper.BasicTerrain;

import java.util.ArrayList;

public class World {

	// map info
	public BasicTerrain[][] map;
	public Integer X_offset;
	public Integer Y_offset;
	public Integer height;
	public Integer width;


	public enum Directions {
		LEFT, RIGHT, UP, DOWN
	}

	// objects under user control
	private BasicObject player;



	public World() throws SlickException {
		ArrayList<String> lvlInfo = Loader.loadLevel(0);
		setupOffsets(lvlInfo.get(0));
		map = parseLevel(lvlInfo);
	}




	public void update(Input input, int delta) throws SlickException {
		player.update(input, delta);
	}



	public void render(Graphics g) throws SlickException {
		// iterate thru the map to render every object and terrain
		for (int i = 0;i < map.length;i++) {
			for (int j = 0;j < map[i].length;j++) {
				if (map[i][j] != null) {
					map[i][j].render(g);
				}
			}
		}
	}




	// calculation of offsets to centre the map
	private void setupOffsets(String sizeInfo) throws SlickException {
		String[] mapSize = sizeInfo.split(",");
		width = Integer.parseInt(mapSize[0]);
		height = Integer.parseInt(mapSize[1]);

		X_offset = (App.SCREEN_WIDTH - width * App.TILE_SIZE) / 2;
		Y_offset = (App.SCREEN_HEIGHT - height * App.TILE_SIZE) / 2;
	}





	// parsing the level info into a map
	public BasicTerrain[][] parseLevel(ArrayList<String> lvlInfo)
			throws SlickException {
		BasicTerrain[][] map = new BasicTerrain[height][width];

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
					map[column][row] = new BasicTerrain(BasicTerrain.TerrainType.FLOOR, row, column, this);
					break;
				case "wall":
					map[column][row] = new BasicTerrain(BasicTerrain.TerrainType.WALL, row, column, this);
					break;
				case "target":
					map[column][row] = new BasicTerrain(BasicTerrain.TerrainType.TARGET, row, column, this);
					break;
				case "stone":
					//map[column][row] = new BasicTerrain(BasicTerrain.TerrainType.STONE, row, column, this);
					map[column][row].occupy(new BasicObject(BasicObject.ObjectType.STONE, this));
					break;
				case "player":
					player = new BasicObject(BasicObject.ObjectType.PLAYER_LEFT, this);
					map[column][row].occupy(player);
					break;
				default:
					map[column][row] = new BasicTerrain(BasicTerrain.TerrainType.EMPTY, row, column, this);
					break;
			}
		}

		return map;
	}



}
