// 209205665 Roy Amit

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a block on the screen, that a ball can collide with.
 */
public class Block implements Sprite, Collidable, HitNotifier {

    public static final double ERROR_RANGE = Math.pow(10, -10);

    private final Rectangle rect;
    private java.awt.Color color;
    private final List<HitListener> hitListeners;

    /**
     * This function is the constructor. It creates a new Block with location (rectangle location).
     *
     * @param rectangle - rectangle object.
     */
    public Block(Rectangle rectangle) {
        this.rect = rectangle;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This is a constructor. Here we create a block with a point (upper-left), width and height.
     *
     * @param upperLeft - a point of the block
     * @param width     - width of the block
     * @param height    - height of the block
     */
    public Block(Point upperLeft, double width, double height) {
        this(new Rectangle(upperLeft, width, height));
    }

    /**
     * This function sets the color for a block.
     *
     * @param color - a color.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * This function returns the "collision shape" of the object.
     *
     * @return - "collision shape" of the object
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
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
     * This function Notifies the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  - the collision point.
     * @param currentVelocity - the current velocity.
     * @return - new velocity expected after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVel = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        if (Math.abs(this.rect.getUpperLeft().getY() - collisionPoint.getY()) < ERROR_RANGE
                || Math.abs(this.rect.getDownLeft().getY() - collisionPoint.getY()) < ERROR_RANGE) {
            newVel.setDy(newVel.getDy() * (-1));
        }
        if (Math.abs(this.rect.getUpperLeft().getX() - collisionPoint.getX()) < ERROR_RANGE
                || Math.abs(this.rect.getUpperRight().getX() - collisionPoint.getX()) < ERROR_RANGE) {
            newVel.setDx(newVel.getDx() * (-1));
        }
        this.notifyHit(hitter);
        return newVel;
    }

    /**
     * This function draws the sprite (block) to the screen.
     *
     * @param d - DrawSurface object.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * This function notifies the sprite (block) that time has passed (currently we do nothing).
     */
    @Override
    public void timePassed() {
    }

    /**
     * This function adds a block to the collidable list, and the sprite list.
     *
     * @param g - GameLevel object.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * This function removes a block from the game.
     *
     * @param game - Game object.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * @param hitter - Ball object.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl - HitListener object.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl - HitListener object.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}