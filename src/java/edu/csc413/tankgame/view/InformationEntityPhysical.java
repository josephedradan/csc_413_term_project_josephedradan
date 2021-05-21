
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/21/2021
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

package edu.csc413.tankgame.view;

public class InformationEntityPhysical {

    private final double height = 4;

    private final double healthScalar = .1;

    private double x;
    private double y;
    private double health;
    private double entityWidth;
    private double entityHeight;

    public InformationEntityPhysical(double entityWidth, double entityHeight) {

        this.entityWidth = entityWidth;
        this.entityHeight = entityHeight;
    }

    public double getXCorrected() {
        return x - (health / 2) + (entityWidth / 2);
    }

    public double getYCorrected() {
        return y + (entityHeight/2);
    }

    public double getWidth() {
        return health;
    }

    public double getHeight() {
        return height;
    }

    public void setHealthBar(double x, double y, double health) {
        this.x = x;
        this.y = y;
        this.health = health * healthScalar;
    }
}
