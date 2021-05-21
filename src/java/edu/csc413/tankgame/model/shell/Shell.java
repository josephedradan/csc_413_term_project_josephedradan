
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/4/2021
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

package edu.csc413.tankgame.model.shell;

import edu.csc413.tankgame.model.Damager;
import edu.csc413.tankgame.model.Entity;
import edu.csc413.tankgame.model.EntityDynamic;
import edu.csc413.tankgame.model.GameWorld;

import static edu.csc413.tankgame.Constants.*;

public abstract class Shell extends EntityDynamic implements Damager {

    // FIXES MULTIPLE COLLISION DUE TO GARBAGE COLLISION SYSTEM
//    private boolean hasBeenDestroyed = false;

    private Entity entityParent;

    protected double damage;

    public Shell(String id, double x, double y, double angle, String image, Entity entityParent, double health, double damage) {
        super(id, x, y, angle, image, health);
        this.entityParent = entityParent;
        this.damage = damage;

    }

    @Override
    protected void boundaryHandler(GameWorld gameWorld) {
        if (this.getY() <= SHELL_Y_LOWER_BOUND) {
            gameWorld.addEntityToQueueRemoveFromWorld(this);
        } else if (this.getY() >= SHELL_Y_UPPER_BOUND) {
            gameWorld.addEntityToQueueRemoveFromWorld(this);
        }
        if (this.getX() <= SHELL_X_LOWER_BOUND) {
            gameWorld.addEntityToQueueRemoveFromWorld(this);
        } else if (this.getX() >= SHELL_X_UPPER_BOUND) {
            gameWorld.addEntityToQueueRemoveFromWorld(this);
        }
    }

    @Override
    public void doActionEntityDynamic(GameWorld gameWorld) {
        doActionShell(gameWorld);
    }

    protected abstract void doActionShell(GameWorld gameWorld);

    @Override
    public double getWidth() {
        return SHELL_WIDTH;
    }

    @Override
    public double getHeight() {
        return SHELL_HEIGHT;
    }

    @Override
    protected void collidedEntity(GameWorld gameWorld, Entity entity) {
        collidedShell(gameWorld, entity);
    }

    protected abstract void collidedShell(GameWorld gameWorld, Entity entity);

    public Entity getEntityParent() {
        return entityParent;
    }

    // TODO use this to steal shells from other entities for the memes
    public void setEntityParent(Entity entity) {
        this.entityParent = entity;
    }

    @Override
    public double getDamage() {
        return damage;
    }

}
