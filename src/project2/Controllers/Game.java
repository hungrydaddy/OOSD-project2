/*
* 100% work by Minghao Huang (Austin) StudentId: 813072 The University of Melbourne
* */

package project2.Controllers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game {

    private World world;

    public Game() throws SlickException {
        world = new World();
        // TODO: do shit
    }


    public void update(Input input, int delta) throws SlickException {
        world.update(input, delta);
    }


    public void render(Graphics g) throws SlickException {
        world.render(g);
    }

}
