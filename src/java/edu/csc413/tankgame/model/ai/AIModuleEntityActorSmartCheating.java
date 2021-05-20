
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/17/2021
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
import edu.csc413.tankgame.model.EntityActor;
import edu.csc413.tankgame.model.GameWorld;

import static edu.csc413.tankgame.Constants.*;

public class AIModuleEntityActorSmartCheating extends AIModuleEntityActorSmart {

    protected double turnLefTurnSpeedSpinning = Math.PI / 12;

    protected boolean moveDown = true;
    protected boolean moveRight = true;


    public AIModuleEntityActorSmartCheating(GameWorld gameWorld) {
        super(gameWorld);
    }


    protected void spinHack(EntityActor entityActor) {

        // Y is less than Low bound then move down
        if (entityActor.getY() <= TANK_Y_LOWER_BOUND) {
            moveDown = true;
        } else if (entityActor.getY() >= TANK_Y_UPPER_BOUND) {
            moveDown = false;
        }

        // X is less than low bound then move right
        if (entityActor.getX() <= TANK_X_LOWER_BOUND) {
            moveRight = true;
        } else if (entityActor.getX() >= TANK_X_UPPER_BOUND) {
            moveRight = false;
        }

//        System.out.println(Math.round(Math.toDegrees( getY())));
//        System.out.println(Math.round(Math.toDegrees(entityActor.getAngle())) + " " + entityActor.getY());

        // Move vertically
        long angle = Math.abs(Math.round(Math.toDegrees(entityActor.getAngleRelativeToWorld())));
        if (moveDown) {
            // Move Down logic
            if (angle == 90) {
                moveForwardMovementSpeed = ((Math.random() * 100) + 50);
            } else if (angle == 270) {
                moveBackwardMovementSpeed = ((Math.random() * 100) + 50);
            }
        } else {
            // Move Up logic
            if (angle == 270) {
                moveForwardMovementSpeed = ((Math.random() * 100) + 50);
            } else if (angle == 90) {
                moveBackwardMovementSpeed = ((Math.random() * 100) + 50);
            }
        }

        // Move horizontally
        if (moveRight) {
            // Move Right Logic
            if (angle == 0) {
                moveForwardMovementSpeed = ((Math.random() * 100) + 50);
            } else if (angle == 180) {
                moveBackwardMovementSpeed = ((Math.random() * 100) + 50);
            }
        } else {
            // Move Left Logic
            if (angle == 180) {
                moveForwardMovementSpeed = ((Math.random() * 100) + 50);
            } else if (angle == 0) {
                moveBackwardMovementSpeed = ((Math.random() * 100) + 50);
            }
        }
        turnLefTurnSpeed = turnLefTurnSpeedSpinning;
    }


    /**
     * The below is Spin hacking logic
     *
     * @param entityActor A given actor
     */
    @Override
    protected void thinkMove(EntityActor entityActor) {
        enableMoveAll();
        spinHack(entityActor);
    }

    @Override
    protected void thinkAction(EntityActor entityActor) {
        activateActionsWhenInLineOfSight(entityActor);
    }

    public void setTurnLefTurnSpeedSpinning(double turnLefTurnSpeedSpinning) {
        this.turnLefTurnSpeedSpinning = turnLefTurnSpeedSpinning;
    }
}

// TODO: OLD LOGIC IN THE TANK AI CLASS, IGNORE

//    private boolean moveDown = true;
//    private boolean moveRight = true;

//        if (this.getY() <= 100) {
//            moveDown = true;
//        } else if (this.getY() >= 924) {
//            moveDown = false;
//        }
//
//        if (this.getX() <= 100) {
//            moveRight = true;
//        } else if (this.getX() >= 668) {
//            moveRight = false;
//        }
//
//        // Move vertically
////        System.out.println(Math.round(Math.toDegrees( getY())));
//
//        System.out.println(Math.round(Math.toDegrees(this.getAngle())) + " " + this.getY());
//        if (moveDown) {
//            long angle = Math.abs(Math.round(Math.toDegrees(this.getAngle())));
//            if (angle == 90) {
//                moveForward((Math.random() * 100) + 50);
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
////        turnLeft(Math.PI/12);
//        turnLeft(Math.PI / 120);
//
//        if (checkIfInLineOfSightFastUsingVectors(gameWorld.getEntity(TANK_PLAYER_ID),.999)) {
//            this.ActivateActionPrimary(gameWorld);
//        }