
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
import edu.csc413.tankgame.model.ai.AIModuleEntityActorStandard;

import static edu.csc413.tankgame.Constants.IMAGE_TANK_AI;

/**
 * TODO: This should have more features to justice a new class...
 */
public class TankAICheating extends TankAI {

    public TankAICheating(AIModuleEntityActor aiModuleEntityActor, String id, double x, double y, double angle, String image) {
        super(aiModuleEntityActor, id, x, y, angle, image);
        this.enableCheating(); // The only difference compared to TankAi
    }

    public TankAICheating(AIModuleEntityActor aiModuleEntityActor, String id, double x, double y, double angle) {
        this(aiModuleEntityActor, id, x, y, angle, IMAGE_TANK_AI);
    }

    /**
     * Bypass checks and inject custom movement/turn speed
     *
     * @param gameWorld
     */
    @Override
    protected void doActionTankAIStandard(GameWorld gameWorld) {
        aiModuleEntityActor.think(this);
        moveForward(aiModuleEntityActor.getMoveForwardMovementSpeed());
        moveBackward(aiModuleEntityActor.getMoveBackwardMovementSpeed());
        turnLeft(aiModuleEntityActor.getTurnLeftTurnSpeed());
        turnRight(aiModuleEntityActor.getTurnRightTurnSpeed());

        if (aiModuleEntityActor.shouldActivatePrimaryAction()) {
            this.activatePrimaryAction(gameWorld);
        }
        if (aiModuleEntityActor.shouldActivateSecondaryAction()) {
            this.activateSecondaryAction(gameWorld);
        }
        if (aiModuleEntityActor.shouldActivateTertiaryAction()) {
            this.activateTertiaryAction(gameWorld);
        }
    }

    @Override
    public void doActionTank(GameWorld gameWorld) {
        doActionTankAIStandard(gameWorld);
    }
}
