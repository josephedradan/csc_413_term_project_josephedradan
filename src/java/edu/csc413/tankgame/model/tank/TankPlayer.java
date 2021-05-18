
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

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.controller.KeyboardInterpreter;
import edu.csc413.tankgame.model.GameWorld;

import static edu.csc413.tankgame.Constants.*;

public class TankPlayer extends Tank {

    private final KeyboardInterpreter keyboardInterpreter;

    public TankPlayer(String id, double x, double y, double angle, String image, KeyboardInterpreter keyboardInterpreter) {
        super(id, x, y, angle, image);
        this.keyboardInterpreter = keyboardInterpreter;

    }

    public TankPlayer(String id, double x, double y, double angle, KeyboardInterpreter keyboardInterpreter) {
        this(id, x, y, angle, IMAGE_TANK_PLAYER, keyboardInterpreter);

    }

    @Override
    protected void doActionTank(GameWorld gameWorld) {

//        System.out.println(this.getAngleRelativeToWorld());
//        System.out.println(this.getAngleRadBetweenLineOfSightAndEntity(gameWorld.getEntity(TANK_AI_1_ID)));
//        System.out.println(Math.toDegrees(this.getAngleRadBetweenLineOfSightAndEntity(gameWorld.getEntity(TANK_AI_1_ID))));

//        System.out.println(Math.toDegrees(getAngle()));
//        System.out.println(getAngle());

        if (keyboardInterpreter.upPressed()) {
            this.moveForward(Constants.TANK_MOVEMENT_SPEED);
//            System.out.println("Up");
//            System.out.println(getX() + " " + getY());
//            System.out.println(getAngle());
        }
        if (keyboardInterpreter.downPressed()) {
            this.moveBackward(Constants.TANK_MOVEMENT_SPEED);
        }
        if (keyboardInterpreter.leftPressed()) {
            this.turnLeft(Constants.TANK_TURN_SPEED);
        }
        if (keyboardInterpreter.rightPressed()) {
            this.turnRight(Constants.TANK_TURN_SPEED);
        }
        if (keyboardInterpreter.actionPressed()) {
            this.ActivateActionPrimary(gameWorld);
        }

        // DEBUGGING GARBAGE

//        System.out.println(checkIfInLineOfSightSlowUsingLines(gameWorld.getEntity(TANK_AI_1_ID),01));
//        System.out.println(getQuadrantBasedOnAngleRad() + " "+ getQuadrantEntityRelative(gameWorld.getEntity(TANK_AI_1_ID)) +" " + Math.cos(getAngle()) + " " + Math.sin(getAngle()) + " " + getBValue());
//        System.out.println(checkIfInLineOfSightSlowUsingLines(gameWorld.getEntity(TANK_AI_1_ID), 100));
//        System.out.println(getQuadrantEntityRelative(gameWorld.getEntity(TANK_AI_1_ID)));

//        System.out.println(getQuadrantEntityRelativeDistances(gameWorld.getEntity(TANK_AI_1_ID)));

//        System.out.println(getQuadrantsEntityRelative(gameWorld.getEntity(TANK_AI_1_ID)));;
//        System.out.println(this.getQuadrantBasedOnAngleRad());
    }
}
