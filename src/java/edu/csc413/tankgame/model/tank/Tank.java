package edu.csc413.tankgame.model.tank;

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.model.EntityActor;
import edu.csc413.tankgame.model.GameWorld;
import edu.csc413.tankgame.model.ShellStandard;

import static edu.csc413.tankgame.Constants.IMAGE_TANK_AI;
import static edu.csc413.tankgame.Constants.SHELL_STANDARD_ID;

/**
 * Entity class representing all tanks in the game.
 */
public abstract class Tank extends EntityActor {


    public Tank(String id, double x, double y, double angle, String image) {
        super(id, x, y, angle, image);
    }

    public Tank(String id, double x, double y, double angle) {
        super(id, x, y, angle, IMAGE_TANK_AI);
    }

    protected void activatePrimaryAction(GameWorld gameWorld) {
        if (canActivatePrimaryAction()) {
            gameWorld.addEntityToQueue(new ShellStandard(SHELL_STANDARD_ID, getShellX(), getShellY(), getShellAngle(), this));
        }
    }

    protected void activateSecondaryAction(GameWorld gameWorld) {
    }

    protected void activateTertiaryAction(GameWorld gameWorld) {
    }

    // The following methods will be useful for determining where a shell should be spawned when it
    // is created by this tank. It needs a slight offset so it appears from the front of the tank,
    // even if the tank is rotated. The shell should have the same angle as the tank.

    private double getShellX() {
        return this.getX() + Constants.TANK_WIDTH / 2 + 45.0 * Math.cos(this.getAngleRelativeToWorld()) - Constants.SHELL_WIDTH / 2;
    }

    private double getShellY() {
        return this.getY() + Constants.TANK_HEIGHT / 2 + 45.0 * Math.sin(this.getAngleRelativeToWorld()) - Constants.SHELL_HEIGHT / 2;
    }

    private double getShellAngle() {
        return this.getAngleRelativeToWorld();
    }

    private void doTankOverhead() {


    }

    @Override
    public void doActionEntityActor(GameWorld gameWorld) {
        doTankOverhead();
        doActionTank(gameWorld);
    }

    protected abstract void doActionTank(GameWorld gameWorld);
}
