
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

/**
 * TODO: This should have more features to justice a new class...
 */
public class TankAICheating extends TankAI {

    public TankAICheating(AIModuleEntityActor aiModuleEntityActor, String id, double x, double y, double angle, String image) {
        super(aiModuleEntityActor, id, x, y, angle, image);
        this.enableCheating();
    }

    public TankAICheating(AIModuleEntityActor aiModuleEntityActor, String id, double x, double y, double angle) {
        this(aiModuleEntityActor, id, x, y, angle, IMAGE_TANK_AI);
    }

    /**
     * Bypass checks and inject custom movement/turn speed because this object is cheating...
     *
     * @param gameWorld
     */
    @Override
    protected void doActionTankAI(GameWorld gameWorld) {
        aiModuleEntityActor.think(this);
        moveForward(aiModuleEntityActor.getMoveForwardMovementSpeed());
        moveBackward(aiModuleEntityActor.getMoveBackwardMovementSpeed());
        turnLeft(aiModuleEntityActor.getTurnLeftTurnSpeed());
        turnRight(aiModuleEntityActor.getTurnRightTurnSpeed());

        if (aiModuleEntityActor.shouldActivateActionPrimary()) {
            this.ActivateActionPrimary(gameWorld);
        }
        if (aiModuleEntityActor.shouldActivateActionSecondary()) {
            this.ActivateActionSecondary(gameWorld);
        }
        if (aiModuleEntityActor.shouldActivateActionTertiary()) {
            this.ActivateActionTertiary(gameWorld);
        }
    }

    @Override
    protected void doActionTank(GameWorld gameWorld) {
        doActionTankAI(gameWorld);
    }
}
