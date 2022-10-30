// 209205665 Roy Amit

/**
 * This interface will be used by things that can be collided with.
 */
public interface Collidable {

    /**
     * This function returns the "collision shape" of the object.
     *
     * @return - "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * This function Notifies the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter          - Ball.
     * @param collisionPoint  - the collision point.
     * @param currentVelocity - the current velocity.
     * @return - new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}