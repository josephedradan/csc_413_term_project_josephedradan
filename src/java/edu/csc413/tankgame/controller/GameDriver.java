package edu.csc413.tankgame.controller;

import edu.csc413.tankgame.model.*;
import edu.csc413.tankgame.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static edu.csc413.tankgame.Constants.*;

public class GameDriver {
    private final MainView mainView;
    private final RunGameView runGameView;


    private GameWorld gameWorld;

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

    // Is this an ActionListener? Is called when a button is pressed from the StartMenuView
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

        KeyboardInterpreter keyboardInterpreter = new KeyboardInterpreter(
                KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);


        TankPlayable tankPlayable = new TankPlayable(TANK_PLAYER_ID, PLAYER_TANK_INITIAL_X, PLAYER_TANK_INITIAL_Y, PLAYER_TANK_INITIAL_ANGLE, keyboardInterpreter);

        TankAi tankAi = new TankAi(TANK_AI_1_ID, 900, AI_TANK_1_INITIAL_Y, 0); // Angle must be 0 to spin hack properly

        TankAi tankAi2 = new TankAi(TANK_AI_2_ID, 50, AI_TANK_1_INITIAL_Y, 0); // Angle must be 0 to spin hack properly


        gameWorld.addEntity(tankPlayable);
        gameWorld.addEntity(tankAi);
        gameWorld.addEntity(tankAi2);

    }

    /**
     * updateGame is repeatedly called in the gameplay loop. The code in this method should run a single frame of the
     * game. As long as it returns true, the game will continue running. If the game should stop for whatever reason
     * (e.g. the player tank being destroyed, escape being pressed), it should return false.
     */
    private boolean updateGame() {
        for (Entity entity : gameWorld.getEntitiesFast()) {
            if (entity instanceof Dynamic) {
                ((Dynamic) entity).act(gameWorld);

            }

            // TODO BETTER SOLUTION PLS
            runGameView.setSpriteLocationAndAngle(entity);

        }
        gameWorld.pushEntitiesFromQueueToWorld();
        return true;
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
