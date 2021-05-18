
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

package edu.csc413.tankgame.model;

import static edu.csc413.tankgame.Constants.WALL_HEIGHT;
import static edu.csc413.tankgame.Constants.WALL_WIDTH;

public class Wall extends EntityStatic {
    public Wall(String id, double x, double y, double angle, String image) {
        super(id, x, y, angle, image);
    }

    @Override
    public double getWidth() {
        return WALL_WIDTH;
    }

    @Override
    public double getHeight() {
        return WALL_HEIGHT;
    }
}
