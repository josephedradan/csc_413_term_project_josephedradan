
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

import static edu.csc413.tankgame.Constants.*;

public class TankAi extends Tank {

    private boolean toggle = true;
    private boolean toggle2 = true;

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
            toggle = true;
        } else if (this.getY() >= 924) {
            toggle = false;
        }

        if (this.getX() <= 100){
            toggle2 = true;
        } else if (this.getX() >= 668){
            toggle2 = false;
        }

        // Move vertically
//        System.out.println(Math.round(Math.toDegrees(this.getAngle())) + " " + getY());
        if (toggle) {
            long angle = Math.abs(Math.round(Math.toDegrees(this.getAngle())));
            if (angle == 270) {
                moveForward((Math.random() * 100) + 50);
            } else if (angle == 90) {
                moveBackward((Math.random() * 100) + 50);
            }
        } else {
            long angle = Math.abs(Math.round(Math.toDegrees(this.getAngle())));

            if (angle == 90) {
                moveForward((Math.random() * 100) + 50);
            } else if (angle == 270) {
                moveBackward((Math.random() * 100) + 50);
            }
        }

        // Move horizontally
        if (toggle2) {
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

        gameWorld.addEntityToQueue(this.activatePrimaryAction());
    }
}
