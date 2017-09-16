/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import project2.Elements.BasicCell;
import project2.Elements.BasicObject;
import project2.Elements.Characters.Enemies.Mage;
import project2.Elements.Characters.Enemies.Rogue;
import project2.Elements.Characters.Enemies.Skeleton;
import project2.Elements.Characters.Player.Player;
import project2.Elements.Environment.*;
import project2.Elements.Environment.Blocks.Block;
import project2.Elements.Environment.Blocks.Ice;
import project2.Elements.Environment.Blocks.TNT;

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
	private Integer height;
	private Integer width;
	private Boolean nextLevel = false;


	// objects under user control
	private Game game;
	private Player player;
	private ArrayList<Target> targets = new ArrayList<>();
	private ArrayList<Skeleton> skeletons = new ArrayList<>();
	private Door door;
	private Switch mSwitch;

	private ArrayList<BasicObject> lateRenderQueue = new ArrayList<>();



	public World(int level, Game game) throws SlickException {
		this.game = game;
		ArrayList<String> lvlInfo = loadLevelFile(level);
		setupOffsets(lvlInfo.get(0));
		parseLevel(lvlInfo);
	}




	public void update(Input input, int delta) throws SlickException {

		// updating the player
		if (input.isKeyPressed(Input.KEY_UP)) {
			player.update(Loader.Directions.UP);
		} else if (input.isKeyPressed(Input.KEY_DOWN)) {
			player.update(Loader.Directions.DOWN);
		} else if (input.isKeyPressed(Input.KEY_LEFT)) {
			player.update(Loader.Directions.LEFT);
		} else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			player.update(Loader.Directions.RIGHT);
		}

		// updating the skeletons
		for (int i = 0;i < skeletons.size();i++) {
			skeletons.get(i).update(null);
		}


		// door toggle
		if (getSwitch() != null) {
			if (getSwitch().hasBlock()) {
				getDoor().doorHide();
			} else {
				getDoor().doorShow();
			}
		}

	}



	public void render(Graphics g) throws SlickException {
		// iterate thru the map to render every object and terrain
		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {
				map[i][j].render(g);
			}
		}

		// rendering things afterwards
		for (int i = 0;i < lateRenderQueue.size(); i++) {
			lateRenderQueue.get(i).render(g);
		}

	}


	// adding objects to the queue to render late
	public void lateRenderAdd(BasicObject object) {
		lateRenderQueue.add(object);
	}

	// remove from the queue
	public void lateRenderClear() {
		lateRenderQueue.clear();
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



	// destroy this world
	public void worldDestroy() {
		game = null;
		player = null;
		targets.clear();
		targets = null;
		door = null;
		mSwitch = null;
		lateRenderQueue.clear();
		lateRenderQueue = null;

		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {
				map[i][j].cellDestroy();
				map[i][j] = null;
			}
			map[i] = null;
		}
		map = null;
	}










	public void parseLevel(ArrayList<String> lvlInfo) throws SlickException {

		map = new BasicCell[getHeight()][getWidth()];

		// initialise all the cells
		for (int i = 0;i < height; i++) {
			for (int j = 0;j < width; j++) {
				map[i][j] = new BasicCell(j, i);
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
					map[column][row].setObject(new Floor(this));
					break;
				case "wall":
					map[column][row].setObject(new Wall(this));
					break;
				case "target":
					Target newTarget = new Target(this);
					addTarget(newTarget);
					map[column][row].setObject(newTarget);
					break;
				case "cracked":
					CrackedWall cracked = new CrackedWall(this);
					cracked.setCell(map[column][row]);
					cracked.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(cracked);
					break;
				case "switch":
					Switch mSwitch = new Switch(this);
					mSwitch.setCell(map[column][row]);
					mSwitch.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(mSwitch);
					setSwitch(mSwitch);
					break;
				case "door":
					Door door = new Door(this);
					door.setCell(map[column][row]);
					door.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(door);
					setDoor(door);
					break;
				case "stone":
					Block block = new Block(this);
					block.setCell(map[column][row]);
					block.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(block);
					break;
				case "ice":
					Ice ice = new Ice(this);
					ice.setCell(map[column][row]);
					ice.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(ice);
					break;
				case "tnt":
					TNT tnt = new TNT(this);
					tnt.setCell(map[column][row]);
					tnt.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(tnt);
					break;
				case "skeleton":
					Skeleton skeleton = new Skeleton(this);
					skeleton.setCell(map[column][row]);
					skeleton.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(skeleton);
					skeletons.add(skeleton);
					break;
				case "rogue":
					Rogue rogue = new Rogue(this);
					rogue.setCell(map[column][row]);
					rogue.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(rogue);
					break;
				case "mage":
					Mage mage = new Mage(this);
					mage.setCell(map[column][row]);
					mage.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(mage);
					break;
				case "player":
					Player player = new Player(this);
					player.setCell(map[column][row]);
					player.setParent(map[column][row].getObject());
					map[column][row].getObject().stack(player);
					setPlayer(player);
					break;
				default:
					break;
			}
		}
	}








	/* encapsulations */

	public Integer getHeight() {
		return height;
	}

	public Integer getWidth() {
		return width;
	}

	public Game getGame() {
		return game;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
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
