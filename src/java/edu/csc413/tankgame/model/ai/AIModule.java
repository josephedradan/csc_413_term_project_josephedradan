
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

    protected boolean boolActivateActionPrimary;
    protected boolean boolActivateActionSecondary;
    protected boolean boolActivateActionTertiary;

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

    public boolean shouldActivateActionPrimary() {
        return boolActivateActionPrimary;
    }

    public boolean shouldActivateActionSecondary() {
        return boolActivateActionSecondary;
    }

    public boolean shouldActivateActionTertiary() {
        return boolActivateActionTertiary;
    }

    protected void enableMoveAll() {
        boolMoveForward = true;
        boolMoveBackward = true;
        boolTurnLeft = true;
        boolTurnRight = true;
    }

    protected void enableActionAll() {
        boolActivateActionPrimary = true;
        boolActivateActionSecondary = true;
        boolActivateActionTertiary = true;
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

        boolActivateActionPrimary = false;
        boolActivateActionSecondary = false;
        boolActivateActionTertiary = false;
    }
}
