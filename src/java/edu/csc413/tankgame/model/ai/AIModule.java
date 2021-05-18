
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

import edu.csc413.tankgame.model.EntityActor;
import edu.csc413.tankgame.model.GameWorld;

public abstract class AIModule {

    /**
     * An AI needs the gameworld regardless because they are always cheating even when they are not doing anything...
     * TODO: IDK when i'm going to need it, but I will probably...
     */
    protected final GameWorld gameWorld;

    protected double moveForwardMovementSpeed;
    protected double moveBackwardMovementSpeed;
    protected double turnLefTurnSpeed;
    protected double turnRightTurnSpeed;

    protected boolean boolMoveForward;
    protected boolean boolMoveBackward;
    protected boolean boolTurnLeft;
    protected boolean boolTurnRight;

    protected boolean boolActivatePrimaryAction;
    protected boolean boolActivateSecondaryAction;
    protected boolean boolActivateTertiaryAction;

    public AIModule(GameWorld gameWorld) {
        resetBrain();
        this.gameWorld = gameWorld;
    }

    public double getMoveForwardMovementSpeed() {
        return moveForwardMovementSpeed;
    }


    public double getMoveBackwardMovementSpeed() {
        return moveBackwardMovementSpeed;
    }


    public double getTurnLeftTurnSpeed() {
        return turnLefTurnSpeed;
    }

    public double getTurnRightTurnSpeed() {
        return turnRightTurnSpeed;
    }


    public boolean shouldMoveForward() {
        return boolMoveForward;
    }

    public boolean shouldMoveBackward() {
        return boolMoveBackward;
    }

    public boolean shouldTurnLeft() {
        return boolTurnLeft;
    }

    public boolean shouldTurnRight() {
        return boolTurnRight;
    }

    public boolean shouldActivatePrimaryAction() {
        return boolActivatePrimaryAction;
    }

    public boolean shouldActivateSecondaryAction() {
        return boolActivateSecondaryAction;
    }

    public boolean shouldActivateTertiaryAction() {
        return boolActivateTertiaryAction;
    }

    /**
     * How the AI should think when moving
     *
     * @param entityActor
     */
    protected abstract void thinkMove(EntityActor entityActor);

    /**
     * How the AI should think when doing an action
     *
     * @param entityActor
     */
    protected abstract void thinkAction(EntityActor entityActor);

    /**
     * This should technically support Ai modules not intended for the correct subclass of an EntityActor.
     * This is intended design...
     *
     * @param entityActor The entity that this Ai is working on
     */
    public void think(EntityActor entityActor) {
        resetBrain();
        thinkMove(entityActor);
        thinkAction(entityActor);
    }

    /**
     * Reset variables so they don't carry over for the next think call
     */
    private void resetBrain() {
        moveForwardMovementSpeed = 0;
        moveBackwardMovementSpeed = 0;
        turnLefTurnSpeed = 0;
        turnRightTurnSpeed = 0;

        boolMoveForward = false;
        boolMoveBackward = false;
        boolTurnLeft = false;
        boolTurnRight = false;

        boolActivatePrimaryAction = false;
        boolActivateSecondaryAction = false;
        boolActivateTertiaryAction = false;
    }
}
