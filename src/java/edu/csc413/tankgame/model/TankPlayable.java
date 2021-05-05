
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

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.controller.KeyboardInterpreter;

import static edu.csc413.tankgame.Constants.IMAGE_TANK_PLAYER;

public class TankPlayable extends Tank {

    private final KeyboardInterpreter keyboardInterpreter;

    public TankPlayable(String typeId, double x, double y, double angle, String image, KeyboardInterpreter keyboardInterpreter) {
        super(typeId, x, y, angle, image);
        this.keyboardInterpreter = keyboardInterpreter;

    }

    public TankPlayable(String typeId, double x, double y, double angle, KeyboardInterpreter keyboardInterpreter) {
        this(typeId, x, y, angle, IMAGE_TANK_PLAYER, keyboardInterpreter);

    }

    @Override
    public void act(GameWorld gameWorld) {

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
//            System.out.println("Down");
        }
        if (keyboardInterpreter.leftPressed()) {
            this.turnLeft(Constants.TANK_TURN_SPEED);
//            System.out.println("Left");

        }
        if (keyboardInterpreter.rightPressed()) {
            this.turnRight(Constants.TANK_TURN_SPEED);
//            System.out.println("Right");
        }
        if (keyboardInterpreter.actionPressed()) {
//            System.out.println("ACTION");
            gameWorld.addEntityToQueue(this.activatePrimaryAction());
        }

    }
}
