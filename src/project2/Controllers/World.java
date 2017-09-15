/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;

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
	private BasicObject player;



	public World(int level) throws SlickException {
		ArrayList<String> lvlInfo = loadLevelFile(level);
		setupOffsets(lvlInfo.get(0));

		Loader.parseLevel(lvlInfo, this);
	}




	public void update(Input input, int delta) throws SlickException {
		player.update(input, delta);
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



	public void gameOver() throws SlickException {
		// put some fancy stuff on the screen
		game.restartCurrentLevel();
	}







	public void setPlayer(BasicObject player) {
		this.player = player;
	}
}
