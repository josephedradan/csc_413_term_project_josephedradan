package edu.csc413.tankgame.model;

import static edu.csc413.tankgame.Constants.IMAGE_TANK_AI;

/**
 * A general concept for an entity in the Tank Game. This includes everything that can act or be interacted with, such
 * as tanks, shells, walls, power ups, etc.
 */
public abstract class Entity {
    /*
     * All objects are entities
     *
     * */
    // TODO: Implement. A lot of what's below is relevant to all Entity types, not just Tanks. Move it accordingly to
    //       Entity class.

    private String image;

    private final String typeId;
    protected double x;
    protected double y;
    protected double angle;

    public Entity(String typeId, double x, double y, double angle, String image) {
        this.typeId = typeId;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.image = image;
    }

    public String getTypeId() {
        return typeId;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}

