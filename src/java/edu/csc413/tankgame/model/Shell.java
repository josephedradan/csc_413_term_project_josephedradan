
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

package edu.csc413.tankgame.model;

public abstract class Shell extends Dynamic {

    private Entity entityParent;

    public Shell(String typeId, double x, double y, double angle, String image, Entity entityParent) {
        super(typeId, x, y, angle, image);
        this.entityParent = entityParent;
    }

    @Override
    public void act(GameWorld gameWorld) {

    }
}
