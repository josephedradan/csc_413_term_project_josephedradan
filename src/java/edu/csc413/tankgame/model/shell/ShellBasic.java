
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
import edu.csc413.tankgame.model.wall.Wall;

import static edu.csc413.tankgame.Constants.*;

public class ShellBasic extends Shell {

    public ShellBasic(String id, double x, double y, double angle, String image, Entity entityParent, double health, double damage) {
        super(id, x, y, angle, image, entityParent, health, damage);

    }

    public ShellBasic(String id, double x, double y, double angle, Entity entityParent) {
        this(id, x, y, angle, IMAGE_SHELL, entityParent, SHELL_BASIC_HEALTH, SHELL_BASIC_DAMAGE);
    }

    @Override
    protected void doActionShell(GameWorld gameWorld) {
        doActionShellBasic(gameWorld);
    }

    protected void doActionShellBasic(GameWorld gameWorld) {
        moveForward(SHELL_MOVEMENT_SPEED);
    }

    @Override
    protected void collidedShell(GameWorld gameWorld, Entity entity) {
        if (entity instanceof Shell) {
            Shell shell = (Shell) entity;

            // Shells from the same parent destroy each other by default
            if (!this.getEntityParent().equals(shell.getEntityParent())) {
                gameWorld.addEntityToQueueRemoveFromWorld(this);
            }

        } else if (entity instanceof Wall) {
            Wall wall = (Wall) entity;
            gameWorld.addEntityToQueueRemoveFromWorld(this);
        } else if ((entity instanceof Tank)) {
            Tank tank = (Tank) entity;
            gameWorld.addEntityToQueueRemoveFromWorld(this);
        }
    }

}
