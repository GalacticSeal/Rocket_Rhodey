package Screens;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Engine.ScreenManager;
import GameObject.Sprite;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// This class is for the level cleared screen
public class LevelClearedScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont endTime;

    private BufferedImage endScreen;

    private String finalTime;

    public LevelClearedScreen(String finalTime) {
        this.finalTime = finalTime;
        initialize();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("Level Cleared", 320, 170, "Arial", 30, Color.white);
        endTime = new SpriteFont("Your time: " + finalTime, 310, 239, "Arial", 30, Color.white);
        endScreen = ImageLoader.load("/Finalebackground.png");
    }

    @Override
    public void update() {

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // paint entire screen black and display level cleared text
        graphicsHandler.drawImage(endScreen, 0, 0,800,617);
        winMessage.draw(graphicsHandler);
        endTime.draw(graphicsHandler);
    }
}
