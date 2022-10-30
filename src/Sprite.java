// 209205665 Roy Amit

import biuoop.DrawSurface;

/**
 * This interface represents a sprite - a game object that can be drawn to the screen.
 * Sprites can be drawn on the screen, and can be notified that time has passed.
 */
public interface Sprite {

    /**
     * This function draws the sprite to the screen.
     *
     * @param d - DrawSurface object
     */
    void drawOn(DrawSurface d);

    /**
     * This function notifies the sprite that time has passed.
     */
    void timePassed();

    /**
     * This function adds a sprite to the game.
     *
     * @param g - GameLevel object.
     */
    void addToGame(GameLevel g);
}