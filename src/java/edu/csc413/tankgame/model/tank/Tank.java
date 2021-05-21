package edu.csc413.tankgame.model.tank;

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.model.Entity;
import edu.csc413.tankgame.model.EntityActor;
import edu.csc413.tankgame.model.GameWorld;
import edu.csc413.tankgame.model.ShellSimpleFactory;
import edu.csc413.tankgame.model.shell.Shell;
import edu.csc413.tankgame.model.shell.ShellBasic;
import edu.csc413.tankgame.model.wall.Wall;

import static edu.csc413.tankgame.Constants.*;

/**
 * Entity class representing all tanks in the game.
 */
public abstract class Tank extends EntityActor {

    private final ShellSimpleFactory shellSimpleFactory = ShellSimpleFactory.instance();

//    private Class shellType = ShellBasic.class;

    // Support for changing the type of shell used by the tank
    private String idShellType = ID_SHELL_BASIC;

    public Tank(String id, double x, double y, double angle, String image, double health) {
        super(id, x, y, angle, image, health);
    }

    public Tank(String id, double x, double y, double angle, double health) {
        this(id, x, y, angle, IMAGE_TANK_AI, health);
    }

    protected void ActivateActionPrimary(GameWorld gameWorld) {
        long number = gameWorld.getUniqueNumberForId();

        if (canActivateActionPrimary()) {

            Shell shell = shellSimpleFactory.getShell(
                    idShellType,
                    ID_SHELL_BASIC + number,
                    getShellX(),
                    getShellY(),
                    getShellAngle(),
                    this);

            gameWorld.addEntityToQueueForWorld(shell);
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
    public void doActionEntityActor(GameWorld gameWorld) {
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


    /**
     * What happens to Tank when entity collies with it
     * <p>
     * THIS LOOKS ULGY! I REQUIRE BETTER GAME DESIGN TO SUPPORT A SIMPLER DESIGN... BECAUSE THE OF THE PRIVATE!!!
     *
     * @param gameWorld
     * @param entity
     */
    protected void collidedTank(GameWorld gameWorld, Entity entity) {
        if ((entity instanceof Tank) || (entity instanceof Wall)) {

            // COLLISION VERSION 1 (QUICK AND EASY) STOP MOVEMENT
//            this.returnToPreviousPosition();

            // COLLISION VERSION 2 (SLOW ADN COMPLEX) GLIDE MOVEMENT
            if (determineHorizontalOrVerticalCollisionSimple(entity) == 0) {
                if (collisionPseudoByGoingLeft(entity)) {
                    this.x += entity.getX() + entity.getWidth() - this.x;
//                    System.out.println("1");
                } else if (collisionPseudoByGoingRight(entity)) {
                    this.x -= this.x + this.getWidth() - entity.getX();
//                    System.out.println("2");

                }

            } else if (determineHorizontalOrVerticalCollisionSimple(entity) == 1) {
                if (collisionPseudoByGoingUp(entity)) {
                    this.y -= this.y + this.getHeight() - entity.getY();
//                    System.out.println("3");

                } else if (collisionPseudoByGoingDown(entity)) {
                    this.y += entity.getY() + entity.getHeight() - this.y;
//                    System.out.println("4");

                }
            }

            if (entity instanceof Tank) {
                // TODO DO SOMETHING HERE... IDK
                Tank tank = (Tank) entity;

            } else if (entity instanceof Wall) {
                // TODO DO SOMETHING HERE... IDK
                Wall wall = (Wall) entity;
            }

        }

        // Shell Collision
        else if (entity instanceof Shell) {
//            System.out.println("\tHIT");
            Shell shell = (Shell) entity;

            if (shell instanceof ShellBasic) {

                // Only enemy shells can hurt you
                if (!shell.getEntityParent().equals(this)) {
                    this.loseHealth(gameWorld, shell.getDamage());
                }
            }
        }
    }

    private void collisionGuaranteed(GameWorld gameWorld, Entity entity) {
        /*
        I feel like tanks that collide with walls and tanks SHOULD NOT move... but having the ability to phase through
        objects is a cool idea...

        TODO: Figure out what to do with this...
        */

    }

    @Override
    protected void collidedEntity(GameWorld gameWorld, Entity entity) {
        collisionGuaranteed(gameWorld, entity);
        collidedTank(gameWorld, entity);
    }

}
