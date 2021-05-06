
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/5/2021
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

package edu.csc413.tankgame.model.ai;

import edu.csc413.tankgame.model.Entity;
import edu.csc413.tankgame.model.GameWorld;
import edu.csc413.tankgame.model.tank.Tank;

import static edu.csc413.tankgame.Constants.TANK_PLAYER_ID;

public class AiTank extends Ai {

    private boolean moveDown = true;
    private boolean moveRight = true;

    public AiTank(Tank tank) {
        super(tank);
    }

    @Override
    public void act(GameWorld gameWorld) {

//        Tank tank = (Tank) this.entity;
//
//        // SPIN HACKING
//
//        if (this.entity.getY() <= 100) {
//            moveDown = true;
//        } else if (this.entity.getY() >= 924) {
//            moveDown = false;
//        }
//
//        if (this.entity.getX() <= 100) {
//            moveRight = true;
//        } else if (this.entity.getX() >= 668) {
//            moveRight = false;
//        }
//
//        // Move vertically
////        System.out.println(Math.round(Math.toDegrees( getY())));
//
//        System.out.println(Math.round(Math.toDegrees(tank.getAngle())) + " " + tank.getY());
//        if (moveDown) {
//            long angle = Math.abs(Math.round(Math.toDegrees(this.getAngle())));
//            if (angle == 90) {
//                tank.moveForward((Math.random() * 100) + 50);
//            } else if (angle == 270) {
//                moveBackward((Math.random() * 100) + 50);
//            }
//        } else {
//            long angle = Math.abs(Math.round(Math.toDegrees(this.getAngle())));
//
//            if (angle == 270) {
//                moveForward((Math.random() * 100) + 50);
//            } else if (angle == 90) {
//                moveBackward((Math.random() * 100) + 50);
//            }
//        }
//
//        // Move horizontally
//        if (moveRight) {
//            long angle = Math.abs(Math.round(Math.toDegrees(this.getAngle())));
//            if (angle == 0) {
//                moveForward((Math.random() * 100) + 50);
//            } else if (angle == 180) {
//                moveBackward((Math.random() * 100) + 50);
//            }
//        } else {
//            long angle = Math.abs(Math.round(Math.toDegrees(this.getAngle())));
//
//            if (angle == 180) {
//                moveForward((Math.random() * 100) + 50);
//            } else if (angle == 0) {
//                moveBackward((Math.random() * 100) + 50);
//            }
//        }
//
//        turnLeft(Math.PI/12);
////        turnLeft(Math.PI / 60);
//
//        if (checkIfInLineOfSightFastUsingVectors(gameWorld.getEntity(TANK_PLAYER_ID),.80)) {
//            gameWorld.addEntityToQueue(this.activatePrimaryAction());
//        }
////        System.out.println(this.getQuadrantBasedOnAngle());
    }
}
