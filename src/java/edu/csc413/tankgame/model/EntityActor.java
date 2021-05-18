
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/8/2021
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

public abstract class EntityActor extends EntityDynamic {

    /*
    TODO: THE BELOW SHOULD VARY PER WEAPON TYPE

    */

    // Cool Down for activatePrimaryAction
    protected int activatePrimaryActionCoolDownPerDoAction = COOL_DOWN_PRIMARY_ACTION;
    private int activatePrimaryActionCoolDownCount = 0;
    private boolean boolActivatePrimaryAction;

    // Cool Down for activateSecondaryAction
    protected int activateSecondaryActionCoolDownPerDoAction = COOL_DOWN_SECONDARY_ACTION;
    private int activateSecondaryActionCoolDownCount = 0;
    private boolean boolActivateSecondaryAction;

    // Cool Down for activateTertiaryAction
    protected int activateTertiaryActionCoolDownPerDoAction = COOL_DOWN_TERTIARY_ACTION;
    private int activateTertiaryActionCoolDownCount = 0;
    private boolean boolActivateTertiaryAction;

    public EntityActor(String id, double x, double y, double angle, String image) {
        super(id, x, y, angle, image);
    }

    /*
    TODO: DO THE BELOW WITH A HASH TABLE, IT WILL BE CLEANER
    THE COST IS THE THE CODE WILL LOOK TOO ADVANCE... I GUESS LOL
     */

    /**
     * Ask if you canActivatePrimaryAction
     *
     * @return if you canActivatePrimaryAction
     */
    protected boolean canActivatePrimaryAction() {
        if (!boolActivatePrimaryAction) {
            return false;
        }
        activatePrimaryActionCoolDownCount = activatePrimaryActionCoolDownPerDoAction;
        boolActivatePrimaryAction = false;
        return true;
    }

    /**
     * Ask if you canActivateSecondaryAction
     *
     * @return if you canActivateSecondaryAction
     */
    protected boolean canActivateSecondaryAction() {
        if (!boolActivateSecondaryAction) {
            return false;
        }
        activateSecondaryActionCoolDownCount = activateSecondaryActionCoolDownPerDoAction;
        boolActivateSecondaryAction = false;
        return true;
    }

    /**
     * Ask if you canActivateTertiaryAction
     *
     * @return if you canActivateTertiaryAction
     */
    protected boolean canActivateTertiaryAction() {
        if (!boolActivateTertiaryAction) {
            return false;
        }
        activateTertiaryActionCoolDownCount = activateTertiaryActionCoolDownPerDoAction;
        boolActivateTertiaryAction = false;
        return true;
    }

    // Implementation of the below is done by the subclasses
    protected abstract void activatePrimaryAction(GameWorld gameWorld);

    protected abstract void activateSecondaryAction(GameWorld gameWorld);

    protected abstract void activateTertiaryAction(GameWorld gameWorld);

    /**
     * Does the Cool Down overhead
     */
    private void doOverheadCoolDown() {
        if (activatePrimaryActionCoolDownCount <= 0) {
            boolActivatePrimaryAction = true;
        } else {
            --activatePrimaryActionCoolDownCount;
        }
        if (activateSecondaryActionCoolDownCount <= 0) {
            boolActivateSecondaryAction = true;
        } else {
            --activateSecondaryActionCoolDownCount;
        }
        if (activateTertiaryActionCoolDownCount <= 0) {
            boolActivateTertiaryAction = true;
        } else {
            --activateTertiaryActionCoolDownCount;
        }
    }

    @Override
    public void doActionEntityDynamic(GameWorld gameWorld) {
        doOverheadCoolDown();
        doActionEntityActor(gameWorld);
    }

    public abstract void doActionEntityActor(GameWorld gameWorld);

    /**
     * Since i'm limited on time to create really good game design, i'm stuck with toggling cheats.
     */
    protected void enableCheating() {
        activatePrimaryActionCoolDownPerDoAction = 0;
        activateSecondaryActionCoolDownPerDoAction = 0;
        activateTertiaryActionCoolDownPerDoAction = 0;
    }
}
