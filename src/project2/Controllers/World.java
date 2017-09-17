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
	private BasicCell[][] map;
	public Integer X_offset;
	public Integer Y_offset;
	private Integer height;
	private Integer width;


	// objects under user control
	private Game game;
	private Player player;
	private ArrayList<Target> targets = new ArrayList<>();
	private ArrayList<Skeleton> skeletons = new ArrayList<>();
	private ArrayList<Rogue> rogues = new ArrayList<>();
	private ArrayList<Mage> mages = new ArrayList<>();
	private ArrayList<Ice> ices = new ArrayList<>();
	private Door door;
	private Switch mSwitch;

	private ArrayList<BasicObject> lateRenderQueue = new ArrayList<>();



	public World(String level, Game game) throws SlickException {
		this.game = game;
		ArrayList<String> lvlInfo = loadLevelFile(level);
		setupOffsets(lvlInfo.get(0));
		parseLevel(lvlInfo);
	}



	public World(String level, Game game, String lastWorld) throws SlickException {
		this.game = game;
		ArrayList<String> lvlInfo = new ArrayList<>();
		String[] lastWorldList = lastWorld.split("\n");

		for (int i = 0;i < lastWorldList.length;i++) {
			lvlInfo.add(lastWorldList[i]);
		}

		setupOffsets(lvlInfo.get(0));
		parseLevel(lvlInfo);
	}




	public void update(Input input, int delta) throws SlickException {

		if (input.isKeyPressed(Input.KEY_R)) {
			getGame().restartCurrentLevel();
		}
		if (input.isKeyPressed(Input.KEY_Z)) {
			getGame().rewind();
		}
		if (input.isKeyPressed(Input.KEY_N)) {
			getGame().startNextLevel();
		}


		Boolean playerMoved = false;
		// updating the player, save if valid move
		if (input.isKeyPressed(Input.KEY_UP)) {
			getGame().saveLastMove();
			player.update(Extra.Directions.UP);
			playerMoved = true;
		} else if (input.isKeyPressed(Input.KEY_DOWN)) {
			getGame().saveLastMove();
			player.update(Extra.Directions.DOWN);
			playerMoved = true;
		} else if (input.isKeyPressed(Input.KEY_LEFT)) {
			getGame().saveLastMove();
			player.update(Extra.Directions.LEFT);
			playerMoved = true;
		} else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			getGame().saveLastMove();
			player.update(Extra.Directions.RIGHT);
			playerMoved = true;
		}

		// updating the skeletons
		for (int i = 0;i < skeletons.size();i++) {
			skeletons.get(i).update(null);
		}

		// updating the ice
		for (int i = 0;i < ices.size();i++) {
			ices.get(i).update(null);
		}


		// door toggle
		if (getSwitch() != null && getDoor() != null) {
			if (getSwitch().hasBlock()) {
				getDoor().doorHide();
			} else {
				getDoor().doorShow();
			}
		}


		// if the player entered a valid move
		if (playerMoved) {

			// rogue update
			for (int i = 0;i < rogues.size();i++) {
				rogues.get(i).update(null);
			}

			// mage update
			for (int i = 0;i < mages.size();i++) {
				mages.get(i).update(null);
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
		if (lateRenderQueue == null) {
			return;
		}
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
	private ArrayList<String> loadLevelFile(String level) {
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
				map[i][j] = new BasicCell(i, j);
			}
		}


		// remove the first line - map size info
		lvlInfo.remove(0);
		// iterate to parse the map
		for (int i = 0;i < lvlInfo.size();i++) {
			String[] temp = lvlInfo.get(i).split(",");
			Integer column = Integer.parseInt(temp[1]);
			Integer row = Integer.parseInt(temp[2]);

			// applying different rules to objects and terrains
			switch (temp[0]) {
				case "floor":
					map[row][column].setObject(new Floor(this));
					break;
				case "wall":
					map[row][column].setObject(new Wall(this));
					break;
				case "target":
					Target newTarget = new Target(this);
					addTarget(newTarget);
					map[row][column].setObject(newTarget);
					break;
				case "cracked":
					CrackedWall cracked = new CrackedWall(this);
					cracked.setCell(map[row][column]);
					cracked.setParent(map[row][column].getObject().getLastChild());
					cracked.getParent().stack(cracked);
					break;
				case "switch":
					Switch mSwitch = new Switch(this);
					mSwitch.setCell(map[row][column]);
					mSwitch.setParent(map[row][column].getObject().getLastChild());
					mSwitch.getParent().stack(mSwitch);
					setSwitch(mSwitch);
					break;
				case "door":
					Door door = new Door(this);
					door.setCell(map[row][column]);
					door.setParent(map[row][column].getObject().getLastChild());
					door.getParent().stack(door);
					setDoor(door);
					break;
				case "stone":
					Block block = new Block(this);
					block.setCell(map[row][column]);
					block.setParent(map[row][column].getObject().getLastChild());
					block.getParent().stack(block);
					break;
				case "ice":
					Ice ice = new Ice(this);
					ice.setCell(map[row][column]);
					ice.setParent(map[row][column].getObject().getLastChild());
					ice.getParent().stack(ice);
					ices.add(ice);
					break;
				case "tnt":
					if (getGame().tagBanned(Extra.Tag.TNT)) {
						break;
					}
					TNT tnt = new TNT(this);
					tnt.setCell(map[row][column]);
					tnt.setParent(map[row][column].getObject().getLastChild());
					tnt.getParent().stack(tnt);
					break;
				case "skeleton":
					Skeleton skeleton = new Skeleton(this);
					skeleton.setCell(map[row][column]);
					skeleton.setParent(map[row][column].getObject().getLastChild());
					skeleton.getParent().stack(skeleton);
					skeletons.add(skeleton);
					break;
				case "rogue":
					Rogue rogue = new Rogue(this);
					rogue.setCell(map[row][column]);
					rogue.setParent(map[row][column].getObject().getLastChild());
					rogue.getParent().stack(rogue);
					rogues.add(rogue);
					break;
				case "mage":
					Mage mage = new Mage(this);
					mage.setCell(map[row][column]);
					mage.setParent(map[row][column].getObject().getLastChild());
					mage.getParent().stack(mage);
					mages.add(mage);
					break;
				case "player":
					Player player = new Player(this);
					player.setCell(map[row][column]);
					player.setParent(map[row][column].getObject().getLastChild());
					player.getParent().stack(player);
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

	public BasicCell[][] getMap() {
		return map;
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
