/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import project2.Elements.BasicObject;
import project2.Elements.Environment.Blocks.Ice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Extra {


	/**
	 * tag combinations are identifiers of different objects
	 */
	public enum Tag {
		PLAYER, ENEMY,
		SKELETON, ROGUE, MAGE,
		FLOOR, TARGET, SWITCH, DOOR, WALL, CRACKED,
		BLOCK, ICE, TNT
	}


	/**
	 * 4 directions in the game
	 */
	public enum Directions {
		LEFT, RIGHT, UP, DOWN
	}




	/**
	 * @param lvl_num the level number
	 * @return the level file in the form of a string
	 */
	public static String loadLevelFile(int lvl_num) {
		String lvlFilePath = "res/levels/" + lvl_num + ".lvl";
		String output = "";

		// try reading the csv file and append it to an array for later usage
		try (BufferedReader reader = new BufferedReader(new FileReader(lvlFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				output = output + line + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}






	/** taking a snapshot of the current scene
	 * @param scene the current scene
	 * @return snapshot of the scene, format compatible with the level file, can be directly loaded
	 */
	public static String snapshot(Scene scene) {
		String extract = "" + scene.getWidth() + "," + scene.getHeight();

		for (int i = 0; i < scene.getHeight(); i++) {
			for (int j = 0; j < scene.getWidth(); j++) {
				extract += translateObjectToString(scene.getMap()[i][j].getObject());
			}
		}

		// deal with the door at the end
		if (scene.getDoor() != null) { // if there is a door
			extract = extract + "\ndoor," + scene.getDoor().getColumn() + "," + scene.getDoor().getRow();
		}

		// deal with the ices
		if (!scene.getIces().isEmpty()) {
			for (int i = 0;i < scene.getIces().size();i++) {
				extract = extract + "\nice," +  scene.getIces().get(i).getLastStaticPostion().getColumn() + "," + scene.getIces().get(i).getLastStaticPostion().getRow();
			}
		}

		// deal with the player
		if (scene.getPlayer() != null) {
			extract = extract + "\nplayer," + scene.getPlayer().getColumn() + "," + scene.getPlayer().getRow();
		}

		return extract;
	}




	// translates an object to string format compatible with level file
	private static String translateObjectToString(BasicObject object) {
		if (object == null) {
			return "";
		}

		String output = "\n";

		if (object.hasTag(Tag.SWITCH)) {
			output += "switch";
		} else if (object.hasTag(Tag.TARGET)) {
			output += "target";
		} else if (object.hasTag(Tag.FLOOR)) {
			output += "floor";
		} else if (object.hasTag(Tag.CRACKED)) {
			output += "cracked";
		} else if (object.hasTag(Tag.WALL)) {
			output += "wall";
		} else if (object.hasTag(Tag.DOOR)) {
			// deal with the door outside of this function
			return "";
		} else if (object.hasTag(Tag.ICE)) {
			// deal with the ices outside of this function
			return "";
		} else if (object.hasTag(Tag.TNT)) {
			output += "tnt";
		} else if (object.hasTag(Tag.BLOCK)) {
			output += "stone";
		} else if (object.hasTag(Tag.SKELETON)) {
			output += "skeleton";
		} else if (object.hasTag(Tag.ROGUE)) {
			output += "rogue";
		} else if (object.hasTag(Tag.MAGE)) {
			output += "mage";
		} else if (object.hasTag(Tag.PLAYER)) {
			// deal with the player outside of this function
			return "";
		}


		output = output + "," + object.getColumn() + "," + object.getRow();
		if (object.getChild() != null) {
			output += translateObjectToString(object.getChild());
		}

		return output;
	}



}