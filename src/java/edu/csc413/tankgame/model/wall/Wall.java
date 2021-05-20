
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

package edu.csc413.tankgame.model.wall;

import edu.csc413.tankgame.model.Entity;
import edu.csc413.tankgame.model.EntityStatic;
import edu.csc413.tankgame.model.GameWorld;
import edu.csc413.tankgame.model.shell.Shell;
import edu.csc413.tankgame.model.shell.ShellBasic;
import edu.csc413.tankgame.model.tank.Tank;

import static edu.csc413.tankgame.Constants.*;

public class Wall extends EntityStatic {
    public Wall(String id, double x, double y, double angle, String image) {
        super(id, x, y, angle, image, WALL_HEALTH);
    }

    @Override
    public double getWidth() {
        return WALL_WIDTH;
    }

    @Override
    public double getHeight() {
        return WALL_HEIGHT;
    }

    /**
     * What will happen to Wall when entity collides with it
     * <p>
     * Walls don't collide with walls...
     *
     * @param gameWorld
     * @param entity
     */
    @Override
    protected void collidedStandard(GameWorld gameWorld, Entity entity) {
        if ((entity instanceof Tank)) {
            // TODO DO SOMETHING HERE... IDK LOL
            Tank tank = (Tank) entity;

        } else if (entity instanceof Shell) {
            Shell shell = (Shell) entity;

            if (shell instanceof ShellBasic){
                this.loseHealth(gameWorld, shell.getDamage());

            }
        }
    }

    @Override
    public void doActionEntityStatic(GameWorld gameWorld) {
        doActionWall(gameWorld);
    }
    protected void doActionWall(GameWorld gameWorld){

    };
}
