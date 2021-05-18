
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
import edu.csc413.tankgame.model.EntityDynamic;
import edu.csc413.tankgame.model.GameWorld;

import static edu.csc413.tankgame.Constants.*;
import static edu.csc413.tankgame.Constants.SHELL_X_UPPER_BOUND;

public abstract class Shell extends EntityDynamic {


    private Entity entityParent;

    public Shell(String id, double x, double y, double angle, String image, Entity entityParent) {
        super(id, x, y, angle, image);
        this.entityParent = entityParent;
    }


    protected abstract void doActionShell(GameWorld gameWorld);

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
        moveForward(SHELL_MOVEMENT_SPEED);
    }

    @Override
    public double getWidth() {
        return SHELL_WIDTH;
    }

    @Override
    public double getHeight() {
        return SHELL_HEIGHT;
    }
}
