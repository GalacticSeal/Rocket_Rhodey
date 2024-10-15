package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import java.awt.*;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected final int MAX_MENU_ITEMS = 2;
    protected final Color LIT_COLOR = new Color(255, 215, 0);
    protected final Color UNLIT_COLOR = new Color(49, 207, 240);
    protected SpriteFont playGame;
    protected SpriteFont titleLabel, titleLabel2, options;
    protected SpriteFont credits;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();
    private boolean loaded = false;
    private boolean pressed = false;

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        
    }

    @Override
    public void initialize() {
        
        titleLabel = new SpriteFont("ROCKET", 140, 37, "Impact", 72, Color.lightGray);
        titleLabel.setFontStyle(Font.BOLD);
        titleLabel.setOutlineColor(Color.black);
        titleLabel.setOutlineThickness(5);
        titleLabel2 = new SpriteFont("RHODEY", 200, 97, "Impact", 72, Color.black);
        titleLabel2.setFontStyle(Font.BOLD);
        titleLabel2.setOutlineColor(Color.lightGray);
        titleLabel2.setOutlineThickness(5);

        playGame = new SpriteFont("PLAY GAME", 100, 223, "Arial", 30, UNLIT_COLOR);
        playGame.setOutlineColor(Color.black);
        playGame.setOutlineThickness(3);
        /*
         * WE WILL NEED TO ADD THE OPTIONS BUTTON
         */
        options = new SpriteFont("OPTIONS", 100, 273, "Arial", 30, UNLIT_COLOR);
        options.setOutlineColor(Color.black);
        options.setOutlineThickness(3);

        credits = new SpriteFont("CREDITS", 100, 323, "Arial", 30, UNLIT_COLOR);
        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(3);
        background = new TitleScreenMap();
        background.setAdjustCamera(false);//add code here for background 
        
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
    }
    private void select() {
        menuItemSelected = currentMenuItemHovered;
        if (menuItemSelected == 0) {
            screenCoordinator.setGameState(GameState.LEVEL);
        } else if (menuItemSelected == 1) {
            screenCoordinator.setGameState(GameState.OPTIONS);
        } else if (menuItemSelected == 2) {
            screenCoordinator.setGameState(GameState.CREDITS);
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
            background.update(null);

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
                playGame.setColor(LIT_COLOR);
                options.setColor(UNLIT_COLOR);
                credits.setColor(UNLIT_COLOR);
                pointerLocationX = 70;
                pointerLocationY = 230;
            } else if (currentMenuItemHovered == 1) {
                playGame.setColor(UNLIT_COLOR);
                options.setColor(LIT_COLOR);
                credits.setColor(UNLIT_COLOR);
                pointerLocationX = 70;
                pointerLocationY = 280;
            } else if (currentMenuItemHovered == 2) {
                playGame.setColor(UNLIT_COLOR);
                options.setColor(UNLIT_COLOR);
                credits.setColor(LIT_COLOR);
                pointerLocationX = 70;
                pointerLocationY = 330;
            }
            // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
            if (Keyboard.isKeyUp(Key.SPACE)) {
                keyLocker.unlockKey(Key.SPACE);
            }
            if (Math.abs(MouseControls.getMouseY() - 235) < 20) {
                currentMenuItemHovered = 0;
                if (pressed) {
                    select();
                }
            } else if (Math.abs(MouseControls.getMouseY() - 285) < 20) {
                currentMenuItemHovered = 1;
                if (pressed) {
                    select();
                }
            } else if (Math.abs(MouseControls.getMouseY() - 335) < 20) {
                currentMenuItemHovered = 2;
                if (pressed) {
                    select();
                }
            }
            if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
                select();
            }
            // handles mouse
            pressed = false;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        titleLabel2.draw(graphicsHandler); // this is intentional so that "Rocket" goes over "Rhodey"
        titleLabel.draw(graphicsHandler);
        playGame.draw(graphicsHandler);
        options.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, UNLIT_COLOR, Color.black, 2);
    }
}
