// 209205665 Roy Amit

import biuoop.DrawSurface;

/**
 * This class represent a ScoreIndicator which is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {

    private final Counter scoreCounter;
    private final java.awt.Color color;

    /**
     * This function is the constructor.
     *
     * @param scoreCounter - Counter object.
     * @param color        - Color.
     */
    public ScoreIndicator(Counter scoreCounter, java.awt.Color color) {
        this.scoreCounter = scoreCounter;
        this.color = color;
    }

    /**
     * This function returns the color of the block.
     *
     * @return - color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This function draws the sprite to the screen.
     *
     * @param d - DrawSurface object
     */
    @Override
    public void drawOn(DrawSurface d) {
        String stringScore = Integer.toString(this.scoreCounter.getValue());
        d.setColor(this.getColor());
        d.drawText(380, 16, "Score: " + stringScore, 15);
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
