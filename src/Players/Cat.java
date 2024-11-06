package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;
import java.awt.Color;
import java.util.HashMap;

// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations
public class Cat extends Player {

    public Cat(float x, float y) {
       
        super(new SpriteSheet(ImageLoader.load("SoldierSprite.png"), 30, 40), x, y, "STAND_RIGHT");//30,40 
        gravity = .35f;
        jumpPower = 11f;
        walkSpeed = 2.3f;
        accelFactor = 10f;
        degradeFactor = 0.10f;
    }

    public void update() {
        super.update();
        if(Keyboard.isKeyDown(Key.C)){
                this.respawn();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
    }
    

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL) 
                            .withBounds(9,9, 8, 25)
                            .build()
            });

            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withScale(2)
                        .withBounds(14,9, 8, 25)
                        .build()
        });

            put("FALL_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 2))
                        .withScale(2)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL) 
                        .withBounds(9,9, 8, 25)
                        .build()

                        
        });
        put("FALL_LEFT", new Frame[] {
            new FrameBuilder(spriteSheet.getSprite(2, 2))
                    .withScale(2)
                    .withBounds(14,9, 8, 25)
                    .build()
        });

        put("JUMP_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0))
                        .withScale(2)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL) 
                        .withBounds(9,9, 8, 25)
                        .build()
        });

        put("JUMP_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0))
                        .withScale(2)
                        .withBounds(14,9, 8, 25)
                        .build()
            
        });

        put("CROUCH_LEFT", new Frame[] {
            new FrameBuilder(spriteSheet.getSprite(1, 0))
                    .withScale(2)
                    .withBounds(14,9, 8, 25)
                    .build()
        
        });
        put("CROUCH_RIGHT", new Frame[] {
            new FrameBuilder(spriteSheet.getSprite(1, 0))
                    .withScale(2)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(9,9, 8, 25)
                    .build()
        
        });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(9,9, 8, 25)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(9,9, 8, 25)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(9,9, 8, 25)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 3), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(9,9, 8, 25)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 4), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(9,9, 8, 25)
                            .build()
        });

        put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                        .withScale(2)
                        .withBounds(14,9, 8, 25)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 1), 14)
                        .withScale(2)
                        .withBounds(14,9, 8, 25)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 2), 14)
                        .withScale(2)
                        .withBounds(14,9, 8, 25)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 3), 14)
                        .withScale(2)
                        .withBounds(14,9, 8, 25)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 4), 14)
                        .withScale(2)
                        .withBounds(14,9, 8, 25)
                        .build()
    });


           

        //     put("DEATH_RIGHT", new Frame[] {
        //             new FrameBuilder(spriteSheet.getSprite(5, 0), 8)
        //                     .withScale(3)
        //                     .build(),
        //             new FrameBuilder(spriteSheet.getSprite(5, 1), 8)
        //                     .withScale(3)
        //                     .build(),
        //             new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
        //                     .withScale(3)
        //                     .build()
        //     });

        //     put("DEATH_LEFT", new Frame[] {
        //             new FrameBuilder(spriteSheet.getSprite(5, 0), 8)
        //                     .withScale(3)
        //                     .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
        //                     .build(),
        //             new FrameBuilder(spriteSheet.getSprite(5, 1), 8)
        //                     .withScale(3)
        //                     .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
        //                     .build(),
        //             new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
        //                     .withScale(3)
        //                     .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
        //                     .build()
        //     });
        }};
    }
}
