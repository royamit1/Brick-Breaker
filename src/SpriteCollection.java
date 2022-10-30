// 209205665 Roy Amit

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a sprite collection. this object holds a list of all the sprites in this game.
 */
public class SpriteCollection {

    private final List<Sprite> spriteList;

    /**
     * This function is the constructor. It creates a new 'SpriteCollection' with a list of Sprites.
     *
     * @param spriteList - a list of Collidable.
     */
    public SpriteCollection(java.util.List<Sprite> spriteList) {
        this.spriteList = spriteList;
    }

    /**
     * Default constructor.
     */
    public SpriteCollection() {
        this(new ArrayList<>());
    }

    /**
     * This function adds the given Sprite to the sprite collection.
     *
     * @param s - Sprite object.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * This function returns the list of sprites.
     *
     * @return - list of sprites.
     */
    public List<Sprite> getSpriteCollection() {
        return this.spriteList;
    }

    /**
     * This function calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteListCopy = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : spriteListCopy) {
            sprite.timePassed();
        }
    }

    /**
     * This function call drawOn(d) on all sprites.
     *
     * @param d - DrawSurface object.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spriteList) {
            sprite.drawOn(d);
        }
    }
}