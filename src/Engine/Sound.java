package Engine;

import java.io.File;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private static File[] musicFiles = {
        // new File( %location%.wav )
        new File(Config.RESOURCES_PATH + "/music/07 - Concrete Halls.wav"),
        new File(Config.RESOURCES_PATH + "/music/Lost Colony - SA2.wav"),
        new File(Config.RESOURCES_PATH + "/music/11 - Warmth.wav"),
        new File(Config.RESOURCES_PATH + "/music/Icecap - SA1.wav"),
        
        // title theme
        new File(Config.RESOURCES_PATH + "/music/blaster_master_title_djka.wav")
    };
    private static File[] soundEffects = {
        // new File( %location%.wav )
        new File(Config.RESOURCES_PATH + "action_jump.wav"),
        new File(Config.RESOURCES_PATH + "rpg_fire.wav"),
        new File(Config.RESOURCES_PATH + "stun_gun.wav"),
        // menu sounds
        new File(Config.RESOURCES_PATH + "menu_hover.wav"),
        new File(Config.RESOURCES_PATH + "menu_select.wav"),
        new File(Config.RESOURCES_PATH + "rocket.wav")
    };
    public static final int JUMP_SOUND = 0;
    public static final int RPG_SOUND = 1;
    public static final int STUN_SOUND = 2;
    private static Clip clip;
    private static Clip clip2;
    
    public static void playMusic(int location) {
        try {
            if (clip != null) {
                clip.stop();
                clip.close();
            } else {
                clip = AudioSystem.getClip();
            }
            clip.open(AudioSystem.getAudioInputStream(
                musicFiles[location] // location from music files.
                ));
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find audio file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void playSFX(int location) {
        try {
            clip2 = AudioSystem.getClip();
            clip2.open(AudioSystem.getAudioInputStream(
                soundEffects[location] // location from sound files.
                ));
            clip2.start();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find audio file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
