package edu.csc413.tankgame.model.tank;

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.model.Entity;
import edu.csc413.tankgame.model.EntityActor;
import edu.csc413.tankgame.model.GameWorld;
import edu.csc413.tankgame.model.shell.Shell;
import edu.csc413.tankgame.model.shell.ShellBasic;
import edu.csc413.tankgame.model.wall.Wall;

import static edu.csc413.tankgame.Constants.*;

/**
 * Entity class representing all tanks in the game.
 */
public abstract class Tank extends EntityActor {

    public Tank(String id, double x, double y, double angle, String image, double health) {
        super(id, x, y, angle, image, health);
    }

    public Tank(String id, double x, double y, double angle, double health) {
        this(id, x, y, angle, IMAGE_TANK_AI, health);
    }

    protected void ActivateActionPrimary(GameWorld gameWorld) {
        long number = gameWorld.getUniqueNumberForId();

        if (canActivateActionPrimary()) {
            gameWorld.addEntityToQueueForWorld(
                    new ShellBasic(
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
    @Override
    protected void collidedStandard(GameWorld gameWorld, Entity entity) {

        if ((entity instanceof Tank) || (entity instanceof Wall)) {

//            if (this instanceof TankPlayer) {
////                System.out.println(getHorizontalVerticalRelativeToWorldFromLineOfSight());
//
//            }


            this.returnToPreviousPosition();

//            // V2 Broken FIXME: CAN DETERMINE THE SIDE OF THE COLLISION BUT WILL BREAK EVEN HARDER!
//            short side = determineHorizontalOrVerticalCollisionSimple(entity);
//            if (side == 0) {
//                if (collisionPseudoByGoingUp(entity)) {
//                    this.y -= this.y + this.getHeight() - entity.getY();
////                moveForward(entity.getX() + entity.getWidth() - this.x);
//                    if (this instanceof TankPlayer) {
//                        System.out.printf("U %s           %s\n", this.y + this.getHeight() - entity.getY(), this.y);
//                    }
//                } else if (collisionPseudoByGoingDown(entity)) {
//                    this.y += entity.getY() + entity.getHeight() - this.y;
////                moveForward(-entity.getX() + entity.getWidth() - this.x);ssssss d
//                    if (this instanceof TankPlayer) {
//                        System.out.printf("D %s           %s\n", entity.getY() + entity.getHeight() - this.y, this.y);
//                    }
//                }
//            }
//            if (side == 1) {
//                if (collisionPseudoByGoingLeft(entity)) {
//                    this.x += entity.getX() + entity.getWidth() - this.x;
////                moveBackward(TANK_MOVEMENT_SPEED);
//                    if (this instanceof TankPlayer) {
//                        System.out.printf("L %s           %s\n", entity.getX() + entity.getWidth() - this.x, this.x);
//                    }
//                } else if (collisionPseudoByGoingRight(entity)) {
//                    this.x -= this.x + this.getWidth() - entity.getX();
////                moveBackward(TANK_MOVEMENT_SPEED);
//                    if (this instanceof TankPlayer) {
//                        System.out.printf("R %s           %s\n", this.x + this.getWidth() - entity.getX(), this.x);
//                    }
//                }
//            }


//            // V1 Broken FIXME: CAN'T DETERMINE THE CORRECT SIDE OF COLLISION
//            if (collisionPseudoByGoingLeft(entity)) {
////                this.x += entity.getX() + entity.getWidth() - this.x;
//                moveBackward(entity.getX() + entity.getWidth() - this.x);
//                if (this instanceof TankPlayer) {
////                    System.out.printf("L %s           %s\n", entity.getX() + entity.getWidth() - this.x, this.x);
////                    if (entity.getX() + entity.getWidth() - this.x >1){
////                        System.out.println("GREASDASDASDASD");
////                    }
//                }
//            }
//            else if (collisionPseudoByGoingRight(entity)) {
////                this.x -= this.x + this.getWidth() - entity.getX();
//                moveBackward(this.x + this.getWidth() - entity.getX());
//                if (this instanceof TankPlayer) {
////                    System.out.printf("R %s           %s\n", this.x + this.getWidth() - entity.getX(), this.x);
//                }
//            }
//            if (collisionPseudoByGoingUp(entity)) {
////                this.y -= this.y + this.getHeight() - entity.getY();
//                moveBackward(this.y + this.getHeight() - entity.getY());
//                System.out.println(entity.getX() + entity.getWidth() - this.x);
//                if (this instanceof TankPlayer) {
////                    System.out.printf("U %s           %s\n", this.y + this.getHeight() - entity.getY(), this.y);
//                }
//            } else if (collisionPseudoByGoingDown(entity)) {
////                this.y += entity.getY() + entity.getHeight() - this.y;
//                moveBackward(entity.getY() + entity.getHeight() - this.y);
//                if (this instanceof TankPlayer) {
//                    System.out.printf("D %s           %s\n", entity.getY() + entity.getHeight() - this.y, this.y);
//                }
//            }


            if (entity instanceof Tank) {
                // TODO DO SOMETHING HERE... IDK
                Tank tank = (Tank) entity;

            } else if (entity instanceof Wall) {
                // TODO DO SOMETHING HERE... IDK
                Wall wall = (Wall) entity;
            }
//            System.out.println();

        } else if (entity instanceof Shell) {
//            System.out.println("\tHIT");
            Shell shell = (Shell) entity;

            if (shell instanceof ShellBasic) {

                // Your shells can't hurt your
                if (!shell.getEntityParent().equals(this)) {
                    this.loseHealth(gameWorld, shell.getDamage());
                }
            }
        }
    }
}
