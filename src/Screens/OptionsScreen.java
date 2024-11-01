package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import SpriteFont.SpriteFont;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OptionsScreen extends Screen {
    // I'm using the Title Menu screen as a reference for building the Options Screen.
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();
    private boolean loaded = false;
    private boolean pressed = false;
    private BufferedImage settingsBufferedImage;
    
    protected SpriteFont optionsLabel, keybinds, goBack;
    protected final int MAX_MENU_ITEMS = 1;
    protected final Color LIT_COLOR = new Color(255, 215, 0);
    protected final Color UNLIT_COLOR = new Color(49, 207, 240);
    
    public OptionsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        optionsLabel = new SpriteFont("OPTIONS", 15, 7, "Impact", 30, Color.white);
        optionsLabel.setOutlineColor(Color.black);
        optionsLabel.setOutlineThickness(5);
        // ADDS THE BUTTONS
        keybinds = new SpriteFont("KEYBINDS", 200, 123, "Arial", 30, UNLIT_COLOR);
        keybinds.setOutlineColor(Color.black);
        keybinds.setOutlineThickness(3);
        goBack = new SpriteFont("<- BACK", 200, 323, "Arial", 30, UNLIT_COLOR);
        goBack.setOutlineColor(Color.black);
        goBack.setOutlineThickness(3);

        // setup graphics on screen (background map, spritefont text)
        // background = new TitleScreenMap();
        try {
            settingsBufferedImage = ImageIO.read(getClass().getResourceAsStream("/RocketMenuBack.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // background.setAdjustCamera(false);
        keyLocker.lockKey(Key.SPACE);
    }
    private void select() {
        menuItemSelected = currentMenuItemHovered;
        if (menuItemSelected == 0) {
            // keybind
            screenCoordinator.setGameState(GameState.KEYBINDS);
        } else if (menuItemSelected == 1) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }
    public void update() {
        if (MouseControls.isMousePressed()) {
            // it looks like im nesting but I'm trying to prevent unnecessary clicks on title screen
            if (loaded) {
                pressed = true;
            }
        } else {
            loaded = true;
            // update background map (to play tile animations)
            // background.update(null);

            // if down or up is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
            if (Keyboard.isKeyDown(Key.DOWN) &&  keyPressTimer == 0) {
                keyPressTimer = 14;
                currentMenuItemHovered++;
            } else if (Keyboard.isKeyDown(Key.UP) &&  keyPressTimer == 0) {
                keyPressTimer = 14;
                currentMenuItemHovered--;
            } else {
                if (keyPressTimer > 0) {
                    keyPressTimer--;
                }
            }

            // if down is pressed on last menu item or up is pressed on first menu item, "loop" the selection back around to the beginning/end
            if (currentMenuItemHovered > MAX_MENU_ITEMS) {
                currentMenuItemHovered = 0;
            } else if (currentMenuItemHovered < 0) {
                currentMenuItemHovered = MAX_MENU_ITEMS;
            }

            // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
            if (currentMenuItemHovered == 0) {
                keybinds.setColor(LIT_COLOR);
                //options.setColor(UNLIT_COLOR);
                goBack.setColor(UNLIT_COLOR);
                pointerLocationX = 170;
                pointerLocationY = 130;
            } else if (currentMenuItemHovered == 1) {
                keybinds.setColor(UNLIT_COLOR);
                //options.setColor(UNLIT_COLOR);
                goBack.setColor(LIT_COLOR);
                pointerLocationX = 170;
                pointerLocationY = 330;
            }

            // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
            if (Keyboard.isKeyUp(Key.SPACE)) {
                keyLocker.unlockKey(Key.SPACE);
            }
            if (Math.abs(MouseControls.getMouseY() - 135) < 20) {
                currentMenuItemHovered = 0;
                if (pressed) {
                    select();
                }
            } else if (Math.abs(MouseControls.getMouseY() - 335) < 20) {
                currentMenuItemHovered = 1;
                if (pressed) {
                    select();
                }
            }
            if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
                select();
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(settingsBufferedImage, 0, 0,800,617);
        optionsLabel.draw(graphicsHandler);
        keybinds.draw(graphicsHandler);
        goBack.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, UNLIT_COLOR, Color.black, 2);
    }
}
