// 209205665 Roy Amit

/**
 * This class represents a CollisionInfo. this object holds a point of collision and a list of collidables.
 */
public class CollisionInfo {

    private final Point collisionP;
    private final Collidable collidable;

    /**
     * This function is the constructor. It creates a new 'CollisionInfo'.
     * This object has the point at which the collision occurs, and the collidable object involved in the collision.
     *
     * @param collisionP - the point at which the collision occurs.
     * @param collidable - collidable object involved in the collision
     */
    public CollisionInfo(Point collisionP, Collidable collidable) {
        this.collisionP = collisionP;
        this.collidable = collidable;
    }

    /**
     * This function returns the point at which the collision occurs.
     *
     * @return - the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionP;
    }

    /**
     * This function returns the collidable object involved in the collision.
     *
     * @return - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}