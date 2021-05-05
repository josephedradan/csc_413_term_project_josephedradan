
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

public abstract class Dynamic extends Entity {


    public Dynamic(String typeId, double x, double y, double angle, String image) {
        super(typeId, x, y, angle, image);
    }


    // TODO: The methods below are provided so you don't have to do the math for movement. You should call these methods
    //       from the various subclasses of Entity in their implementations of act.

    protected void moveForward(double movementSpeed) {
        x += movementSpeed * Math.cos(angle);
        y += movementSpeed * Math.sin(angle);
    }

    protected void moveBackward(double movementSpeed) {
        x -= movementSpeed * Math.cos(angle);
        y -= movementSpeed * Math.sin(angle);
    }

    protected void turnLeft(double turnSpeed) {
        angle -= turnSpeed;

        // TODO: FIX THIS ABSOLUTE TRASH MATH
//        if (angle < 0) {
//            angle = 2 * Math.PI;
//        }
//        if (angle > 2 * Math.PI) {
//            angle = 0;
//        }

        angle = angle % (2 * Math.PI);

    }

    protected void turnRight(double turnSpeed) {
        angle += turnSpeed;

        // TODO: FIX THIS ABSOLUTE TRASH MATH

//        if (angle < 0) {
//            angle = 2 * Math.PI;
//        }
//        if (angle > 2 * Math.PI) {
//            angle = 0;
//        }

        angle = angle % (2 * Math.PI);

    }

    /**
     * All entities can act, even if the details of their act logic may vary based on the specific type of Entity.
     */
    public abstract void act(GameWorld gameWorld);
}
