
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

package edu.csc413.tankgame.model.tank;

import edu.csc413.tankgame.model.GameWorld;

import static edu.csc413.tankgame.Constants.IMAGE_TANK_AI;
import static edu.csc413.tankgame.Constants.TANK_PLAYER_ID;

public class TankAi extends Tank {

    private boolean moveDown = true;
    private boolean moveRight = true;

    public TankAi(String typeId, double x, double y, double angle, String image) {
        super(typeId, x, y, angle, image);
    }

    public TankAi(String typeId, double x, double y, double angle) {
        this(typeId, x, y, angle, IMAGE_TANK_AI);
    }

    @Override
    public void act(GameWorld gameWorld) {

        // SPIN HACKING

        if (this.getY() <= 100) {
            moveDown = true;
        } else if (this.getY() >= 924) {
            moveDown = false;
        }

        if (this.getX() <= 100) {
            moveRight = true;
        } else if (this.getX() >= 668) {
            moveRight = false;
        }

        // Move vertically
//        System.out.println(Math.round(Math.toDegrees( getY())));

        System.out.println(Math.round(Math.toDegrees(this.getAngle())) + " " + getY());
        if (moveDown) {
            long angle = Math.abs(Math.round(Math.toDegrees(this.getAngle())));
            if (angle == 90) {
                moveForward((Math.random() * 100) + 50);
            } else if (angle == 270) {
                moveBackward((Math.random() * 100) + 50);
            }
        } else {
            long angle = Math.abs(Math.round(Math.toDegrees(this.getAngle())));

            if (angle == 270) {
                moveForward((Math.random() * 100) + 50);
            } else if (angle == 90) {
                moveBackward((Math.random() * 100) + 50);
            }
        }

        // Move horizontally
        if (moveRight) {
            long angle = Math.abs(Math.round(Math.toDegrees(this.getAngle())));
            if (angle == 0) {
                moveForward((Math.random() * 100) + 50);
            } else if (angle == 180) {
                moveBackward((Math.random() * 100) + 50);
            }
        } else {
            long angle = Math.abs(Math.round(Math.toDegrees(this.getAngle())));

            if (angle == 180) {
                moveForward((Math.random() * 100) + 50);
            } else if (angle == 0) {
                moveBackward((Math.random() * 100) + 50);
            }
        }

        turnLeft(Math.PI/12);
//        turnLeft(Math.PI / 60);

        if (checkIfInLineOfSightFastUsingVectors(gameWorld.getEntity(TANK_PLAYER_ID),.99)) {
            gameWorld.addEntityToQueue(this.activatePrimaryAction());
        }
//        System.out.println(this.getQuadrantBasedOnAngle());
    }
}
