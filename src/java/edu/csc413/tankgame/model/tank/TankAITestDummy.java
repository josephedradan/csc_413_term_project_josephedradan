
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

package edu.csc413.tankgame.model.tank;

import edu.csc413.tankgame.model.GameWorld;
import edu.csc413.tankgame.model.ai.AIModuleEntityActor;

import static edu.csc413.tankgame.Constants.IMAGE_TANK_AI;

public class TankAITestDummy extends TankAI {

    public TankAITestDummy(AIModuleEntityActor aiModuleEntityActor, String id, double x, double y, double angle, String image) {
        super(aiModuleEntityActor, id, x, y, angle, image);
    }

    public TankAITestDummy(AIModuleEntityActor aiModuleEntityActor, String id, double x, double y, double angle) {
        this(aiModuleEntityActor, id, x, y, angle, IMAGE_TANK_AI);
    }

    protected void doActionTankAI(GameWorld gameWorld) {
        // TODO ADD ANYTHING TO TEST
//        aiModuleEntityActor.think(this);
//
//        if (aiModuleEntityActor.shouldMoveForward()) {
//            moveForward(TANK_MOVEMENT_SPEED);
//        }
//        if (aiModuleEntityActor.shouldMoveBackward()) {
//            moveBackward(TANK_MOVEMENT_SPEED);
//        }
//        if (aiModuleEntityActor.shouldTurnLeft()) {
//            turnLeft(TANK_TURN_SPEED);
//        }
//        if (aiModuleEntityActor.shouldTurnRight()) {
//            turnRight(TANK_TURN_SPEED);
//        }
//
//        if (aiModuleEntityActor.shouldActivateActionPrimary()) {
//            this.ActivateActionPrimary(gameWorld);
//        }
//        if (aiModuleEntityActor.shouldActivateActionSecondary()) {
//            this.ActivateActionSecondary(gameWorld);
//        }
//        if (aiModuleEntityActor.shouldActivateActionTertiary()) {
//            this.ActivateActionTertiary(gameWorld);
//        }
    }
}
