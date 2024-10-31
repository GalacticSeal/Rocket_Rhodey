package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;

// This class is for the credits screen
public class CreditsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected SpriteFont creditsLabel;
    protected SpriteFont credits0, credits1, credits2, credits3, credits4, ogCreatedByLabel;
    protected SpriteFont createdByLabel;
    protected SpriteFont returnInstructionsLabel;
    private BufferedImage creditBufferedImage;

    public CreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        // background = new TitleScreenMap();
        // background.setAdjustCamera(false);
        try {
            creditBufferedImage = ImageIO.read(getClass().getResourceAsStream("/RocketMenuBack.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        creditsLabel = new SpriteFont("CREDITS", 15, 7, "Impact", 30, Color.white);
        creditsLabel.setOutlineColor(Color.black);
        creditsLabel.setOutlineThickness(5);
        createdByLabel = new SpriteFont("Created by:", 130, 121, "Times New Roman", 20, Color.white);
        credits0 = new SpriteFont("Isaias Barreto", 170, 141, "Times New Roman", 20, Color.white);
        credits1 = new SpriteFont("Jason Handrahan", 170, 161, "Times New Roman", 20, Color.white);
        credits2 = new SpriteFont("Jeremy Wiening", 170, 181, "Times New Roman", 20, Color.white);
        credits3 = new SpriteFont("Jesse Chaput", 170, 201, "Times New Roman", 20, Color.white);
        credits4 = new SpriteFont("Paul Zegarek", 170, 221, "Times New Roman", 20, Color.white);
        ogCreatedByLabel = new SpriteFont("Original game created by: Alex Thimineur", 130, 321, "Times New Roman", 20, Color.white);
        returnInstructionsLabel = new SpriteFont("Press Space to return to the menu", 20, 532, "Times New Roman", 30, Color.white);
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // background.update(null);

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // if space is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(creditBufferedImage, 0, 0,800,617);
        creditsLabel.draw(graphicsHandler);
        createdByLabel.draw(graphicsHandler);
        credits0.draw(graphicsHandler);
        credits1.draw(graphicsHandler);
        credits2.draw(graphicsHandler);
        credits3.draw(graphicsHandler);
        credits4.draw(graphicsHandler);
        ogCreatedByLabel.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
