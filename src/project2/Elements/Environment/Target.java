package project2.Elements.Environment;


import org.newdawn.slick.SlickException;
import project2.Controllers.Loader;
import project2.Controllers.World;
import project2.Elements.BasicObject;

public class Target extends Floor {

    private Boolean hasBlock;

    public Target(World world) throws SlickException {
        super(world);
        getTags().add(Loader.Tag.TARGET);
        setObjectTile("target");
    }


    @Override
    public void onCollide(BasicObject object, Loader.Directions direction) throws SlickException {

        if (getStackedObject() == null) {
            stackOn(object);
            if (object.hasTag(Loader.Tag.BLOCK)) {
                hasBlock = true;
            }
        } else {
            // if something stacked, do this
        }

    }

}
