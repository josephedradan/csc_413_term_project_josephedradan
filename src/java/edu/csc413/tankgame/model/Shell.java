
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

import static edu.csc413.tankgame.Constants.SHELL_MOVEMENT_SPEED;

public abstract class Shell extends EntityDynamic {

    static int i = 0;

    private Entity entityParent;

    public Shell(String id, double x, double y, double angle, String image, Entity entityParent) {
        super(id + i, x, y, angle, image);
        i++;  // TODO: TOO CHEAP FIX THIS GARBAGE
        this.entityParent = entityParent;
    }

    @Override
    public void doActionEntityDynamic(GameWorld gameWorld) {
        moveForward(SHELL_MOVEMENT_SPEED);
    }
}
