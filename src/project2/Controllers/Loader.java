/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import org.newdawn.slick.SlickException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {
	// Converts a world coordinate to a tile coordinate,
	// and returns if that location is a blocked tile




	// loading the required level
	public static ArrayList<String> loadLevel(Integer levelNumber) throws SlickException {
		String lvlFilePath = "res/levels/" + levelNumber + ".lvl";
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




}
