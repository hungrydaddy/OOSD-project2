/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import org.lwjgl.Sys;
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

import java.util.ArrayList;

public class Extra {
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




	// taking a snaoshot of the current world
	public static String snapshot(World world) {
		String extract = "" + world.getWidth() + "," + world.getHeight();

		for (int i = 0;i < world.getHeight();i++) {
			for (int j = 0;j < world.getWidth();j++) {
				extract += translateObjectToString(world.getMap()[i][j].getObject());
			}
		}

		// deal with the door at the end
		if (world.getDoor() != null) { // if there is a door
			extract = extract + "\ndoor," + world.getDoor().getColumn() + "," + world.getDoor().getRow();
		}

		return extract;
	}



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
			return "";
			// deal with the door outside of this function
		} else if (object.hasTag(Tag.ICE)) {
			output += "ice";
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
			output += "player";
		}


		output = output + "," + object.getColumn() + "," + object.getRow();
		if (object.getChild() != null) {
			output += translateObjectToString(object.getChild());
		}

		return output;
	}



}