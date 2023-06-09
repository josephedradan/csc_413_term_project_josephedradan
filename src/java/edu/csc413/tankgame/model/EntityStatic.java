
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 4/23/2021
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

public abstract class EntityStatic extends EntityPhysical {

    public EntityStatic(String id, double x, double y, double angle, String image, double health) {
        super(id, x, y, angle, image, health);
    }

    @Override
    public void doActionEntityPhysical(GameWorld gameWorld) {
        doActionEntityStatic(gameWorld);
    }

    public abstract void doActionEntityStatic(GameWorld gameWorld);
}
