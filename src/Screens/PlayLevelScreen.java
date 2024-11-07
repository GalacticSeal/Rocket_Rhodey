package Screens;

import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Engine.GraphicsHandler;
import Engine.Screen;
import Engine.Sound;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.TestMap;
import Players.Cat;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;

    private BufferedImage levelBufferedImage;
    boolean backgroundSwitched = false;

    private long lastUpdateTime;
    private int elapsedTime = 0;
    private Font timerFont;
    private int minutes;
    private int seconds;

    private GraphicsHandler timer;

    BufferedImage[] biomeBackgrounds;
    int[] biomeHeights = {7900, 6748, 4800, 2630};
    int currentBiome = 0;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // define/setup map
        this.map = new TestMap();

        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);

        try {
            biomeBackgrounds = new BufferedImage[] {
                ImageIO.read(getClass().getResourceAsStream("/levelBackground.png")),
                ImageIO.read(getClass().getResourceAsStream("/lushBackground.png")),
                ImageIO.read(getClass().getResourceAsStream("/lavaBackground.png")),
                ImageIO.read(getClass().getResourceAsStream("/iceBackground.png")),
            };
            levelBufferedImage = biomeBackgrounds[0];
            Sound.playMusic(0);
        } catch (IOException e) {
            System.out.println("cannot load background");
            e.printStackTrace();
        }

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;

        lastUpdateTime = System.currentTimeMillis();
        timerFont = new Font("Arial", Font.BOLD, 20);
    }

    public void update() {
        double y = player.getY();
        // System.out.println("Player Y coordinate: " + y); //Testing line
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:

                long currentTime = System.currentTimeMillis();
                    if (currentTime - lastUpdateTime >= 1000) {
                    elapsedTime++;
                    lastUpdateTime = currentTime;
                    System.out.println("Elapsed time: " + elapsedTime);
                    minutes = elapsedTime / 60;
                    seconds = elapsedTime % 60;

                    draw(timer);

                }

                

                player.update();
                map.update(player);

                int newBiome = currentBiome;

                for (int i = 0; i < biomeHeights.length; i++) {
                    if (y <= biomeHeights[i]) {
                        newBiome = i;
                    }
                }

                if (newBiome != currentBiome) {
                    currentBiome = newBiome;
                    levelBufferedImage = biomeBackgrounds[currentBiome];
                    //System.out.println("You are in biome " + currentBiome); //debug statement
                    Sound.playMusic(currentBiome);
                }

                //elapsedTime = (int) ((System.currentTimeMillis() - startTime) / 1000);
                
                break;
            // if level has been completed, bring up level cleared screen
            // case LEVEL_COMPLETED:
            //     if (levelCompletedStateChangeStart) {
            //         screenTimer = 130;
            //         levelCompletedStateChangeStart = false;
            //     } else {
            //         levelClearedScreen.update();
            //         screenTimer--;
            //         if (screenTimer == 0) {
            //             goBackToMenu();
            //         }
            //     }
            //     break;
            // // wait on level lose screen to make a decision (either resets level or sends player back to main menu)
            // case LEVEL_LOSE:
            //     levelLoseScreen.update();
            //     break;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
            graphicsHandler.drawImage(levelBufferedImage,0,0,800,617);
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);

                String formattedTime = String.format("%02d:%02d", minutes, seconds);

                graphicsHandler.drawString("Time: " + formattedTime,650, 20, timerFont, Color.WHITE);

                timer = graphicsHandler;
                break;
            // case LEVEL_COMPLETED:
            //     levelClearedScreen.draw(graphicsHandler);
            //     break;
            // case LEVEL_LOSE:
            //     levelLoseScreen.draw(graphicsHandler);
            //     break;
        }
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    @Override
    public void onLevelCompleted() {
        if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    @Override
    public void onDeath() {
        if (playLevelScreenState != PlayLevelScreenState.LEVEL_LOSE) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE;
        }
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, LEVEL_LOSE
    }
}
