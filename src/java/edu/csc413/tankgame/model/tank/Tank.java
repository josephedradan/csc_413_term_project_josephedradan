package edu.csc413.tankgame.model.tank;

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.model.EntityActor;
import edu.csc413.tankgame.model.GameWorld;
import edu.csc413.tankgame.model.shell.ShellStandard;

import static edu.csc413.tankgame.Constants.*;
import static edu.csc413.tankgame.Constants.TANK_X_UPPER_BOUND;

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

    protected void ActivateActionPrimary(GameWorld gameWorld) {
        long number = gameWorld.getUniqueNumberForId();

        if (canActivateActionPrimary()) {
            gameWorld.addEntityToQueueForWorld(
                    new ShellStandard(
                            ID_SHELL_STANDARD + number,
                            getShellX(),
                            getShellY(),
                            getShellAngle(),
                            this)
            );
        }
    }

    protected void ActivateActionSecondary(GameWorld gameWorld) {
    }

    protected void ActivateActionTertiary(GameWorld gameWorld) {
    }
    // is created by this tank. It needs a slight offset so it appears from the front of the tank,

    // The following methods will be useful for determining where a shell should be spawned when it
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
        // TODO: SOMETHING MIGHT BE ADDED HERE IDK...
    }

    @Override
    protected void boundaryHandler(GameWorld gameWorld) {
        if (this.getY() <= TANK_Y_LOWER_BOUND) {
            this.y = TANK_Y_LOWER_BOUND;
        } else if (this.getY() >= TANK_Y_UPPER_BOUND) {
            this.y = TANK_Y_UPPER_BOUND;
        }
        if (this.getX() <= TANK_X_LOWER_BOUND) {
            this.x = TANK_X_LOWER_BOUND;
        } else if (this.getX() >= TANK_X_UPPER_BOUND) {
            this.x = TANK_X_UPPER_BOUND;
        }
    }

    @Override
    protected void doActionEntityActor(GameWorld gameWorld) {
        doTankOverhead();
        doActionTank(gameWorld);
    }

    protected abstract void doActionTank(GameWorld gameWorld);

    @Override
    public double getWidth() {
        return TANK_WIDTH;
    }
    @Override
    public double getHeight() {
        return TANK_HEIGHT;
    }
}
