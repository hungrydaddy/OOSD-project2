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




	// taking a snaoshot of the current world
	public static String snapshot(World world) {
		String extract = "";

		// TODO: finish snapshot
		return extract;
	}


}