package edu.csc413.tankgame.controller;

import edu.csc413.tankgame.WallInformation;
import edu.csc413.tankgame.model.*;
import edu.csc413.tankgame.model.ai.AIModuleEntityActorBasic;
import edu.csc413.tankgame.model.ai.AIModuleEntityActorCheating;
import edu.csc413.tankgame.model.ai.AIModuleEntityActorTestDummy;
import edu.csc413.tankgame.model.tank.TankAI;
import edu.csc413.tankgame.model.tank.TankAICheating;
import edu.csc413.tankgame.model.tank.TankAITestDummy;
import edu.csc413.tankgame.model.tank.TankPlayer;
import edu.csc413.tankgame.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import static edu.csc413.tankgame.Constants.*;

public class GameDriver {
    private final MainView mainView;
    private final RunGameView runGameView;

    private GameWorld gameWorld;
    private CollisionDetector collisionDetector;

    public GameDriver() {

        // Initialize window stuff
        mainView = new MainView(this::startMenuActionPerformed); // Passing a function or an object?

        // Run main window
        runGameView = mainView.getRunGameView();


    }

    public void start() {

        // Set Game screen on the main window
        mainView.setScreen(MainView.Screen.START_GAME_SCREEN);
    }

    /**
     * Notes:
     * Is this an ActionListener? Is called when a button is pressed from the StartMenuView
     * <p>
     * TODO: FIGURE OUT WHAT THIS DOES
     *
     * @param actionEvent
     */
    private void startMenuActionPerformed(ActionEvent actionEvent) {
        /*
        Get actionEvent's ActionCommand and see if that command is either START_BUTTON_ACTION_COMMAND
        EXIT_BUTTON_ACTION_COMMAND and execute the appropriate command.

        actionEvent will probably be a button
        */
        switch (actionEvent.getActionCommand()) {
            case StartMenuView.START_BUTTON_ACTION_COMMAND -> runGame();
            case StartMenuView.EXIT_BUTTON_ACTION_COMMAND -> mainView.closeGame();
            default -> throw new RuntimeException("Unexpected action command: " + actionEvent.getActionCommand());
        }
    }

    // Run the game
    private void runGame() {

        // Change screen
        mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);

        // Make a runnable object that contains the game loop
        Runnable gameRunner = () -> {

            // Initialize game stuff
            setUpGame();
            while (updateGame()) {

                // Draw the stuff on the screen
                runGameView.repaint();
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
            // When the game is over (escape the while loop).
            mainView.setScreen(MainView.Screen.END_MENU_SCREEN);
            resetGame();
        };

        // Run game on a thread
        new Thread(gameRunner).start();
    }

    /**
     * setUpGame is called once at the beginning when the game is started. Entities that are present from the start
     * should be initialized here, with their corresponding sprites added to the RunGameView.
     */
    private void setUpGame() {
        gameWorld = new GameWorld(runGameView);
        initializeWWalls(gameWorld);
        collisionDetector = new CollisionDetector(gameWorld);


        KeyboardInterpreter keyboardInterpreter = new KeyboardInterpreter(
                KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);


        TankPlayer tankPlayer = new TankPlayer(ID_TANK_PLAYER, TANK_PLAYER_INITIAL_X, TANK_PLAYER_INITIAL_Y, TANK_PLAYER_INITIAL_ANGLE, keyboardInterpreter);


        AIModuleEntityActorCheating aiModuleTankSpinHack = new AIModuleEntityActorCheating(gameWorld);
        aiModuleTankSpinHack.setAccuracy(.999);
        aiModuleTankSpinHack.setAccuracy(.95);
        aiModuleTankSpinHack.setTurnLefTurnSpeedSpinning(Math.PI / 60);

        AIModuleEntityActorBasic aiModuleEntityActorBasic = new AIModuleEntityActorBasic(gameWorld);

        AIModuleEntityActorTestDummy aiModuleEntityActorTestDummy = new AIModuleEntityActorTestDummy(gameWorld);

        TankAI tankAITestDummy = new TankAITestDummy(aiModuleEntityActorTestDummy, ID_TANK_AI_1, 500, 500, 0); // Angle must be 0 to spin hack properly

        TankAI tankAICheating = new TankAICheating(aiModuleTankSpinHack, ID_TANK_AI_2, 50, TANK_AI_1_INITIAL_Y, 0); // Angle must be 0 to spin hack properly

        TankAI tankAICheatingAutoTarget = new TankAI(aiModuleEntityActorBasic, ID_TANK_AI_3, 400, 400, 0); // Angle must be 0 to spin hack properly

        gameWorld.addEntityToQueueForWorld(tankPlayer);
        gameWorld.addEntityToQueueForWorld(tankAITestDummy);
        gameWorld.addEntityToQueueForWorld(tankAICheating);
        gameWorld.addEntityToQueueForWorld(tankAICheatingAutoTarget);

    }

    /**
     * updateGame is repeatedly called in the gameplay loop. The code in this method should run a single frame of the
     * game. As long as it returns true, the game will continue running. If the game should stop for whatever reason
     * (e.g. the player tank being destroyed, escape being pressed), it should return false.
     */
    private boolean updateGame() {
        for (Entity entity : gameWorld.getEntitiesFast()) {

            // Movement of Dynamic entities
            if (entity instanceof EntityDynamic) {
                ((EntityDynamic) entity).doActionComplete(gameWorld);
            }


            // TODO BETTER SOLUTION PLS
            runGameView.setSpriteLocationAndAngle(entity);
        }

        collisionDetector.run();
        System.out.println();

        gameWorld.pushEntitiesFromQueueForWorldToWorld();
        gameWorld.popEntitiesFromQueueRemoveFromWorld();
        return true;
    }

    private void initializeWWalls(GameWorld gameWorld) {
        List<WallInformation> wallInformationList = WallInformation.readWalls();
        for (WallInformation wallInformation : wallInformationList) {

            gameWorld.addEntityToQueueForWorld(new Wall(
                    ID_WALL_STANDARD + gameWorld.getUniqueNumberForId(),
                    wallInformation.getX(),
                    wallInformation.getY(),
                    0,
                    wallInformation.getImageFile()
            ));
        }
    }


    /**
     * resetGame is called at the end of the game once the gameplay loop exits. This should clear any existing data from
     * the game so that if the game is restarted, there aren't any things leftover from the previous run.
     */
    private void resetGame() {
        // TODO: Implement.
        runGameView.reset();
    }


    public static void main(String[] args) {
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();

    }
}
