package Engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private static URL[] musicFiles = {
        // new File( %location%.wav )
        Sound.class.getClassLoader().getResource(Config.RESOURCES_PATH + "/music/07 - Concrete Halls.wav"),
            Sound.class.getClassLoader().getResource(Config.RESOURCES_PATH + "/music/Lost Colony - SA2.wav"),
            Sound.class.getClassLoader().getResource(Config.RESOURCES_PATH + "/music/11 - Warmth.wav"),
            Sound.class.getClassLoader().getResource(Config.RESOURCES_PATH + "/music/Icecap - SA1.wav"),
        
        // title theme
            Sound.class.getClassLoader().getResource(Config.RESOURCES_PATH + "/music/blaster_master_title_djka.wav")
    };
    private static URL[] soundEffects = {
        // new File( %location%.wav )
            Sound.class.getClassLoader().getResource(Config.RESOURCES_PATH + "action_jump.wav"),
            Sound.class.getClassLoader().getResource(Config.RESOURCES_PATH + "rpg_fire.wav"),
            Sound.class.getClassLoader().getResource(Config.RESOURCES_PATH + "stun_gun.wav"),
        // menu sounds
            Sound.class.getClassLoader().getResource(Config.RESOURCES_PATH + "menu_hover.wav"),
            Sound.class.getClassLoader().getResource(Config.RESOURCES_PATH + "menu_select.wav"),
            Sound.class.getClassLoader().getResource(Config.RESOURCES_PATH + "rocket.wav")
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
