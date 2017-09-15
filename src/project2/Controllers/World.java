/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import project2.Elements.BasicCell;
import project2.Elements.Characters.Player.Player;
import project2.Elements.Environment.Door;
import project2.Elements.Environment.Switch;
import project2.Elements.Environment.Target;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class World {

	// map info
	public BasicCell[][] map;
	public Integer X_offset;
	public Integer Y_offset;
	public Integer height;
	public Integer width;

	private Game game;


	// objects under user control
	private Player player;
	private ArrayList<Target> targets = new ArrayList<>();
	private Door door;
	private Switch mSwitch;



	public World(int level, Game game) throws SlickException {
		this.game = game;
		ArrayList<String> lvlInfo = loadLevelFile(level);
		setupOffsets(lvlInfo.get(0));

		Loader.parseLevel(lvlInfo, this);
	}




	public void update(Loader.Directions direction) throws SlickException {
		player.update(direction);
	}



	public void render(Graphics g) throws SlickException {
		// iterate thru the map to render every object and terrain
		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {
				map[i][j].render(g);
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



	//
	private ArrayList<String> loadLevelFile(int level) {
		String lvlFilePath = "res/levels/" + level + ".lvl";
		ArrayList<String> lvlInfo = new ArrayList<String>();
		String line;

		// try reading the csv file and append it to an array for later usage
		try (BufferedReader reader = new BufferedReader(new FileReader(lvlFilePath))) {
			while ((line = reader.readLine()) != null) {
				lvlInfo.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lvlInfo;
	}



	public Boolean levelWon() {
		for (int i = 0;i < targets.size();i++) {
			if (!targets.get(i).hasBlock()) { // if any of the targets does not have a block
				return false;
			}
		}
		return true;
	}


	public Game getGame() {
		return game;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


	public Door getDoor() {
		return door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}

	public Switch getSwitch() {
		return mSwitch;
	}

	public void setSwitch(Switch mSwitch) {
		this.mSwitch = mSwitch;
	}

	public void addTarget(Target target) {
		this.targets.add(target);
	}


}
