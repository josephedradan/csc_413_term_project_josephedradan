
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

import edu.csc413.tankgame.model.Entity;
import edu.csc413.tankgame.model.GameWorld;
import edu.csc413.tankgame.model.tank.Tank;

import static edu.csc413.tankgame.Constants.*;

public class ShellBasic extends Shell {

    protected double damage = SHELL_DAMAGE_BASIC;

    public ShellBasic(String id, double x, double y, double angle, String image, Entity entityParent, double health) {
        super(id, x, y, angle, image, entityParent, health);
    }


    public ShellBasic(String id, double x, double y, double angle, Entity entityParent) {
        this(id, x, y, angle, IMAGE_SHELL_STANDARD, entityParent, SHELL_HEALTH);
    }

    @Override
    protected void doActionShell(GameWorld gameWorld) {
        moveForward(SHELL_MOVEMENT_SPEED);
    }

    @Override
    protected void collidedShell(GameWorld gameWorld, Entity entity) {
        if ((entity instanceof Tank)) {
            Tank tank = (Tank) entity;
            gameWorld.addEntityToQueueRemoveFromWorld(this);
        }
    }

    @Override
    public double getDamage() {
        return damage;
    }
}
