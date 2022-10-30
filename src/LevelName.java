// 209205665 Roy Amit

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class represents a level name displayed as a sprite on the screen.
 */
public class LevelName implements Sprite {

    private final String levelName;
    private final Color color;

    /**
     * This function is the constructor of the level name sprite.
     *
     * @param levelName - the level name.
     * @param color     - the color of the text.
     */
    public LevelName(String levelName, Color color) {
        this.levelName = levelName;
        this.color = color;
    }

    /**
     * This function draws the sprite to the screen.
     *
     * @param d - DrawSurface object
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawText(550, 16, "Level Name: " + this.levelName, 15);
    }

    /**
     * This function notifies the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * This function adds a sprite to the game.
     *
     * @param g - GameLevel object.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
