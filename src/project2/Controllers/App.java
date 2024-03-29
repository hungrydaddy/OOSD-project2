/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */


package project2.Controllers;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the level.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame
{
 	/** screen width, in pixels */
    public static final int SCREEN_WIDTH = 800;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 600;
    /** size of the tiles, in pixels */
    public static final int TILE_SIZE = 32;

    private Game currentGame;

    public App()
    {    	
        super("Shadow Blocks");
    }

    @Override
    public void init(GameContainer gc)
    throws SlickException
    {
        currentGame = new Game();
    }

    /** Update the level state for a frame.
     * @param gc The Slick level container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
    throws SlickException
    {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        currentGame.update(input);
    }

    /** Render the entire screen, so it reflects the current level state.
     * @param gc The Slick level container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
    throws SlickException
    {
    	currentGame.render(g);
    }

    /** Start-up method. Creates the level and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
    throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new App());
        // setShowFPS(true), to show frames-per-second.
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}