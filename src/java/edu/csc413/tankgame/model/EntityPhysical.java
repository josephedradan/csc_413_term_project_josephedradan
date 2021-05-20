
/*
 * Created by Joseph Edradan
 *
 * Github: https://github.com/josephedradan
 *
 * Date created: 5/18/2021
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

/**
 * Physical objects
 */
public abstract class EntityPhysical extends Entity {

    protected double health;

    public EntityPhysical(String id, double x, double y, double angleRelativeToWorld, String image, double health) {
        super(id, x, y, angleRelativeToWorld, image);
        this.health = health;
    }

    private void checkHealth(GameWorld gameWorld) {
        if (health <= 0) {
            System.out.printf("%s is dead\n", this);
            gameWorld.addEntityToQueueRemoveFromWorld(this);
        }
    }

    @Override
    public void doActionEntity(GameWorld gameWorld) {
        checkHealth(gameWorld);
        doActionEntityPhysical(gameWorld);
    }

    public abstract void doActionEntityPhysical(GameWorld gameWorld);

    protected void loseHealth(GameWorld gameWorld, double amount) {
        health -= amount;
    }

    protected void gainHealth(double amount) {
        health += amount;
    }

    protected void setHealth(double amount) {
        health = amount;
    }

    public double getHealth() {
        return health;
    }
}
