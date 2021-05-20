
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 4/23/2021
 *
 * Purpose:
 *
 * Details:
 *
 * Description:
 *
 * Notes:
 *
 * IMPORTANT NOTES:
 *
 * Explanation:
 *
 * Reference:
 *
 */

package edu.csc413.tankgame.model;

import static edu.csc413.tankgame.Constants.TANK_TURN_SPEED;

/**
 * EntityDynamic is an entity that can move...
 */
public abstract class EntityDynamic extends EntityPhysical {

    private double xPrevious = x;
    private double yPrevious = y;

    public EntityDynamic(String id, double x, double y, double angle, String image, double health) {
        super(id, x, y, angle, image, health);
    }


    // TODO: The methods below are provided so you don't have to do the math for movement. You should call these methods
    //       from the various subclasses of Entity in their implementations of doActionComplete.

    protected void moveForward(double movementSpeed) {
        x += movementSpeed * Math.cos(angleRelativeToWorld);
        y += movementSpeed * Math.sin(angleRelativeToWorld);
    }

    protected void moveBackward(double movementSpeed) {
        x -= movementSpeed * Math.cos(angleRelativeToWorld);
        y -= movementSpeed * Math.sin(angleRelativeToWorld);
    }

    protected void turnLeft(double turnSpeed) {

        if (turnSpeed == 0) {
            return;
        }

        angleRelativeToWorld -= turnSpeed;

        // TODO: FIX THIS ABSOLUTE TRASH MATH
        if (angleRelativeToWorld < 0) {
            angleRelativeToWorld = 2 * Math.PI;
        }
        if (angleRelativeToWorld > 2 * Math.PI) {
            angleRelativeToWorld = 0;
        }

//        angle = angle % (2 * Math.PI);

    }

    protected void turnRight(double turnSpeed) {
        if (turnSpeed == 0) {
            return;
        }

        angleRelativeToWorld += turnSpeed;

//        if (angle < 0) {
//            angle = 2 * Math.PI;
//        }
//        if (angle > 2 * Math.PI) {
//            angle = 0;
//        }

        // TODO: IS THIS EVEN GOOD MATH? FIGURE IT OUT
        angleRelativeToWorld = angleRelativeToWorld % (2 * Math.PI);

    }

    /**
     * Get this entity to look towards the given entity using movement
     * Literally more math intensive than your version. RIP
     * The AI is supposed to think, not the entity itself.
     *
     * @param entity
     */
    protected void lookTowardsEntity(Entity entity) {
        double tolerance = Math.toRadians(3);

        double angleBetweenLineOfSightAndEntity = getAngleRadBetweenLineOfSightAndEntity(entity); // 180 degrees based

        double lowAngleRad = getCosineSimilarityBetweenPointAngleAndEntity(this.getAngleRelativeToWorld() - tolerance, this.getX(), this.getY(), entity);
        double highAngleRad = getCosineSimilarityBetweenPointAngleAndEntity(this.getAngleRelativeToWorld() + tolerance, this.getX(), this.getY(), entity);

        if (angleBetweenLineOfSightAndEntity < tolerance) {
            return;
        }

        if (highAngleRad < lowAngleRad) {
            turnLeft(TANK_TURN_SPEED);
        } else {
            turnRight(TANK_TURN_SPEED);
        }
    }

    /*
    Cheap solution to collision that works by setting the current position to the previous frame position...
    */
    private void recordPreviousPosition() {
        xPrevious = x;
        yPrevious = y;
    }

    public double getyPrevious() {
        return yPrevious;
    }

    public double getxPrevious() {
        return xPrevious;
    }

    protected void returnToPreviousPosition() {
        x = xPrevious;
        y = yPrevious;
    }

    /**
     * A Handler for the boundary handlers
     * TODO: MAYBE SOMETHING ADVANCE CAN BE ADDED HERE IDK....
     */
    private void boundaryHandlerHandler(GameWorld gameWorld) {
        boundaryHandler(gameWorld);
    }


    protected abstract void boundaryHandler(GameWorld gameWorld);

    /**
     * All EntityDynamic can doActionEntityPhysical, even if the details of their doActionComplete logic may vary based on the specific
     * type of EntityDynamic.
     * The default behavior is to call doActionEntityDynamic.
     * The reason for this function is to allow for overhead to be done.
     *
     * @param gameWorld The game world
     */
    public void doActionEntityPhysical(GameWorld gameWorld) {
        recordPreviousPosition(); // Overhead function
        boundaryHandlerHandler(gameWorld);  // Overhead function
        doActionEntityDynamic(gameWorld);
    }

    /**
     * This is the actual action of the EntityDynamic
     *
     * @param gameWorld The game world
     */
    public abstract void doActionEntityDynamic(GameWorld gameWorld);


}
