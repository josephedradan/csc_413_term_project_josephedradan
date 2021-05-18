
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

/**
 * EntityActor is an entity that is dynamic but can be thought of as an animate object
 */
public abstract class EntityActor extends EntityDynamic {

    /*
    TODO: THE BELOW SHOULD VARY PER WEAPON TYPE
    */

    // Cool Down for ActivateActionPrimary
    protected int ActivateActionPrimaryCoolDownPerDoAction = COOL_DOWN_PRIMARY_ACTION;
    private int ActivateActionPrimaryCoolDownCount = 0;
    private boolean boolActivateActionPrimary;

    // Cool Down for ActivateActionSecondary
    protected int ActivateActionSecondaryCoolDownPerDoAction = COOL_DOWN_SECONDARY_ACTION;
    private int ActivateActionSecondaryCoolDownCount = 0;
    private boolean boolActivateActionSecondary;

    // Cool Down for ActivateActionTertiary
    protected int ActivateActionTertiaryCoolDownPerDoAction = COOL_DOWN_TERTIARY_ACTION;
    private int ActivateActionTertiaryCoolDownCount = 0;
    private boolean boolActivateActionTertiary;

    public EntityActor(String id, double x, double y, double angle, String image) {
        super(id, x, y, angle, image);
    }

    /*
    TODO: DO THE BELOW WITH A HASH TABLE, IT WILL BE CLEANER
    THE COST IS THE THE CODE WILL LOOK TOO ADVANCE... I GUESS LOL
     */

    /**
     * Ask if you canActivateActionPrimary
     *
     * @return if you canActivateActionPrimary
     */
    protected boolean canActivateActionPrimary() {
        if (!boolActivateActionPrimary) {
            return false;
        }
        ActivateActionPrimaryCoolDownCount = ActivateActionPrimaryCoolDownPerDoAction;
        boolActivateActionPrimary = false;
        return true;
    }

    /**
     * Ask if you canActivateActionSecondary
     *
     * @return if you canActivateActionSecondary
     */
    protected boolean canActivateActionSecondary() {
        if (!boolActivateActionSecondary) {
            return false;
        }
        ActivateActionSecondaryCoolDownCount = ActivateActionSecondaryCoolDownPerDoAction;
        boolActivateActionSecondary = false;
        return true;
    }

    /**
     * Ask if you canActivateActionTertiary
     *
     * @return if you canActivateActionTertiary
     */
    protected boolean canActivateActionTertiary() {
        if (!boolActivateActionTertiary) {
            return false;
        }
        ActivateActionTertiaryCoolDownCount = ActivateActionTertiaryCoolDownPerDoAction;
        boolActivateActionTertiary = false;
        return true;
    }

    // Implementation of the below is done by the subclasses
    protected abstract void ActivateActionPrimary(GameWorld gameWorld);

    protected abstract void ActivateActionSecondary(GameWorld gameWorld);

    protected abstract void ActivateActionTertiary(GameWorld gameWorld);

    /**
     * Does the Cool Down overhead
     */
    private void doOverheadCoolDown() {
        if (ActivateActionPrimaryCoolDownCount <= 0) {
            boolActivateActionPrimary = true;
        } else {
            --ActivateActionPrimaryCoolDownCount;
        }
        if (ActivateActionSecondaryCoolDownCount <= 0) {
            boolActivateActionSecondary = true;
        } else {
            --ActivateActionSecondaryCoolDownCount;
        }
        if (ActivateActionTertiaryCoolDownCount <= 0) {
            boolActivateActionTertiary = true;
        } else {
            --ActivateActionTertiaryCoolDownCount;
        }
    }

    @Override
    protected void doActionEntityDynamic(GameWorld gameWorld) {
        doOverheadCoolDown();
        doActionEntityActor(gameWorld);
    }

    protected abstract void doActionEntityActor(GameWorld gameWorld);

    /**
     * Since i'm limited on time to create really good game design, i'm stuck with toggling cheats.
     */
    protected void enableCheating() {
        ActivateActionPrimaryCoolDownPerDoAction = 0;
        ActivateActionSecondaryCoolDownPerDoAction = 0;
        ActivateActionTertiaryCoolDownPerDoAction = 0;
    }
}
