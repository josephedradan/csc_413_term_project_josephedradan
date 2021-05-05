package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

import static edu.csc413.tankgame.Constants.*;

/**
 * Entity class representing all tanks in the game.
 */
public abstract class Tank extends Dynamic {


    public Tank(String typeId, double x, double y, double angle, String image) {
        super(typeId, x, y, angle, image);
    }

    public Tank(String typeId, double x, double y, double angle) {
        super(typeId, x, y, angle, IMAGE_TANK_AI);
    }


    protected Shell activatePrimaryAction() {
        return new ShellStandard(SHELL_STANDARD_ID, getShellX(), getShellY(), getShellAngle(), this);
    }

    // The following methods will be useful for determining where a shell should be spawned when it
    // is created by this tank. It needs a slight offset so it appears from the front of the tank,
    // even if the tank is rotated. The shell should have the same angle as the tank.

    private double getShellX() {
        return this.getX() + Constants.TANK_WIDTH / 2 + 45.0 * Math.cos(this.getAngle()) - Constants.SHELL_WIDTH / 2;
    }

    private double getShellY() {
        return this.getY() + Constants.TANK_HEIGHT / 2 + 45.0 * Math.sin(this.getAngle()) - Constants.SHELL_HEIGHT / 2;
    }

    private double getShellAngle() {
        return this.getAngle();
    }

}
