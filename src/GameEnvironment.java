// 209205665 Roy Amit

import java.util.List;
import java.util.ArrayList;

/**
 * This class represent the game environment and will be a collection of many objects a ball can collide with.
 * The ball will know the game environment, and will use it to check for collisions and direct its movement.
 */
public class GameEnvironment {

    private final List<Collidable> collideList;
    private final List<Ball> ballList;

    /**
     * This function is the constructor. It creates a new 'GameEnvironment' with a list of collidables.
     *
     * @param interPoints - a list of Collidable.
     */
    public GameEnvironment(java.util.List<Collidable> interPoints) {
        this.collideList = interPoints;
        this.ballList = new ArrayList<>();
    }

    /**
     * Default constructor.
     */
    public GameEnvironment() {
        this(new ArrayList<>());
    }

    /**
     * This function adds the given collidable to the environment.
     *
     * @param c - Collidable object
     */
    public void addCollidable(Collidable c) {
        this.collideList.add(c);
    }

    /**
     * This function adds the given ball to the environment.
     *
     * @param b - Ball object.
     */
    public void addBall(Ball b) {
        this.ballList.add(b);
    }

    /**
     * This function returns a list of all the balls in the game.
     *
     * @return - list of all the balls.
     */
    public List<Ball> getBallList() {
        return this.ballList;
    }

    /**
     * This function returns the collidables list.
     *
     * @return - collidables list.
     */
    public List<Collidable> getCollideList() {
        return this.collideList;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory - Line object.
     * @return - the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestCollidable = null;
        // We run on the list of all collidable object.
        List<Collidable> collideListCopy = new ArrayList<Collidable>(this.collideList);
        for (Collidable collidable : collideListCopy) {
            // Here we get the closest point of collision with a specific collidable object.
            Point closestCollision = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (closestCollision != null) {
                if (closestPoint != null) {
                    if (closestCollision.distance(trajectory.start()) < closestPoint.distance(trajectory.start())) {
                        closestPoint = closestCollision;
                        closestCollidable = collidable;
                    }
                } else {
                    closestPoint = closestCollision;
                    closestCollidable = collidable;
                }
            }
        }
        if (closestPoint == null && closestCollidable == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestCollidable);
    }

}