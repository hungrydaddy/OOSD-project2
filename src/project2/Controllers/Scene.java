/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
import java.util.Date;

public class Scene {

	// map properties
	private BasicCell[][] map;
	private Integer X_offset;
	private Integer Y_offset;
	private Integer height;
	private Integer width;


	// objects under user control
	private Player player;
	private ArrayList<Target> targets = new ArrayList<>();
	private ArrayList<Skeleton> skeletons = new ArrayList<>();
	private ArrayList<Rogue> rogues = new ArrayList<>();
	private ArrayList<Mage> mages = new ArrayList<>();
	private ArrayList<Ice> ices = new ArrayList<>();
	private TNT tnt;
	private Door door;
	private Switch mSwitch;
	private Date explosionDate;
	private final int EXPLOSION_DURATION = 400;
	private Boolean usedTNT = false;



	public Scene(Boolean usedTNT, String snapshot) throws SlickException {
		this.usedTNT = usedTNT;
		parseLevel(snapshot);
	}



	/** updates for the scene, handles movable units and doors and switches
	 * @param playerMoved a state indicating whether the player moved, some units' movements are depending on player
	 */
	public void update(Boolean playerMoved) throws SlickException {
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
			if (getSwitch().childrenHaveTag(Extra.Tag.BLOCK)) {
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



	/** renders the entire map, branching up to every single object stacked on each cell, also handles tnt effect
	 * @param g graphics from slick lib
	 */
	public void render(Graphics g) throws SlickException {
		// iterate thru the map to render every object and terrain
		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {
				map[i][j].render(g);
			}
		}

		if (usedTNT()) { // render tnt after everything to make sure the effects display
			displayExplosion();
		}
	}





	/** checks if every target has a block
	 * @return whether level is won or not
	 */
	public Boolean levelWon() {
		for (int i = 0;i < targets.size();i++) {
			if (!targets.get(i).childrenHaveTag(Extra.Tag.BLOCK)) { // if any of the targets does not have a block
				return false;
			}
		}
		return true;
	}




	/**
	 * destroys the current scene and free up the memory
	 */
	public void sceneDestroy() {
		player = null;
		targets.clear();
		targets = null;
		door = null;
		mSwitch = null;
		tnt = null;

		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {
				map[i][j].cellDestroy();
				map[i][j] = null;
			}
			map[i] = null;
		}
		map = null;
	}




	// calculation of offsets to centre the map
	private void setupOffsets(String sizeInfo) throws SlickException {
		String[] mapSize = sizeInfo.split(",");
		width = Integer.parseInt(mapSize[0]);
		height = Integer.parseInt(mapSize[1]);

		X_offset = (App.SCREEN_WIDTH - width * App.TILE_SIZE) / 2;
		Y_offset = (App.SCREEN_HEIGHT - height * App.TILE_SIZE) / 2;
	}



	private void displayExplosion() throws SlickException {
		if (tnt == null) {
			return;
		}

		Date now = new Date();

		if (explosionDate == null) {
			explosionDate = now;
		}

		long timeSinceExplosion = new Date().getTime() - explosionDate.getTime();
		Image explosionTile = new Image("res/explosion.png");
		if (timeSinceExplosion < EXPLOSION_DURATION) {
			explosionTile.draw(tnt.getCellOnDirection(Extra.Directions.LEFT).getColumn() * App.TILE_SIZE + X_offset, tnt.getCellOnDirection(Extra.Directions.UP).getRow() * App.TILE_SIZE + Y_offset);
		}
	}




	// parse the level from snapshot for the current scene
	private void parseLevel(String snapshot) throws SlickException {
		ArrayList<String> lvlInfo = new ArrayList<>();

		for (int i = 0;i < snapshot.split("\n").length;i++) {
			lvlInfo.add(snapshot.split("\n")[i]);
		}

		setupOffsets(lvlInfo.get(0));

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
					map[row][column].setObject(new Floor(this, map[row][column]));
					break;
				case "wall":
					map[row][column].setObject(new Wall(this, map[row][column]));
					break;
				case "target":
					Target newTarget = new Target(this, map[row][column]);
					addTarget(newTarget);
					map[row][column].setObject(newTarget);
					break;
				case "cracked":
					if (usedTNT) {
						break;
					}
					CrackedWall cracked = new CrackedWall(this, map[row][column]);
					cracked.setParent(map[row][column].getObject().getLastChild());
					cracked.getParent().stack(cracked);
					break;
				case "switch":
					Switch mSwitch = new Switch(this, map[row][column]);
					mSwitch.setParent(map[row][column].getObject().getLastChild());
					mSwitch.getParent().stack(mSwitch);
					setSwitch(mSwitch);
					break;
				case "door":
					Door door = new Door(this, map[row][column]);
					door.setParent(map[row][column].getObject().getLastChild());
					door.getParent().stack(door);
					setDoor(door);
					break;
				case "stone":
					Block block = new Block(this, map[row][column]);
					block.setParent(map[row][column].getObject().getLastChild());
					block.getParent().stack(block);
					break;
				case "ice":
					Ice ice = new Ice(this, map[row][column]);
					ice.setParent(map[row][column].getObject().getLastChild());
					ice.getParent().stack(ice);
					ices.add(ice);
					break;
				case "tnt":
					if (usedTNT) {
						break;
					}
					TNT tnt = new TNT(this, map[row][column]);
					tnt.setParent(map[row][column].getObject().getLastChild());
					tnt.getParent().stack(tnt);
					this.tnt = tnt;
					break;
				case "skeleton":
					Skeleton skeleton = new Skeleton(this, map[row][column]);
					skeleton.setParent(map[row][column].getObject().getLastChild());
					skeleton.getParent().stack(skeleton);
					skeletons.add(skeleton);
					break;
				case "rogue":
					Rogue rogue = new Rogue(this, map[row][column]);
					rogue.setParent(map[row][column].getObject().getLastChild());
					rogue.getParent().stack(rogue);
					rogues.add(rogue);
					break;
				case "mage":
					Mage mage = new Mage(this, map[row][column]);
					mage.setParent(map[row][column].getObject().getLastChild());
					mage.getParent().stack(mage);
					mages.add(mage);
					break;
				case "player":
					Player player = new Player(this, map[row][column]);
					player.setParent(map[row][column].getObject().getLastChild());
					player.getParent().stack(player);
					setPlayer(player);
					break;
				default:
					break;
			}
		}
	}









	/* getters and setters */

	public Integer getHeight() {
		return height;
	}

	public Integer getWidth() {
		return width;
	}

	public BasicCell[][] getMap() {
		return map;
	}

	public Integer getX_offset() {
		return X_offset;
	}

	public Integer getY_offset() {
		return Y_offset;
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

	public ArrayList<Ice> getIces() {
		return ices;
	}

	public void setSwitch(Switch mSwitch) {
		this.mSwitch = mSwitch;
	}

	public void addTarget(Target target) {
		this.targets.add(target);
	}

	public Boolean usedTNT() {
		if (tnt == null) { // if no tnt in the scene
			return false;
		}
		if (tnt.usedTNT()) {
			return true;
		}
		return false;
	}

}
