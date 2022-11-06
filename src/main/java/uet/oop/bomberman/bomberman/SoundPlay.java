package uet.oop.bomberman.bomberman;

import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class SoundPlay extends JFrame {

    public int flag = 0;
    private Clip clip;
    private long clipTimePosition;
    String soundFile;
    SoundPlay(String soundFile) {
        this.soundFile = soundFile;
        try{
            File f = new File("./" + soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            // Lower audio
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
    public void pauseMusic() {
        clipTimePosition = clip.getMicrosecondPosition();
        clip.stop();
    }
    public void continueMusic() {
        clip.setMicrosecondPosition(clipTimePosition);
        clip.start();
    }

}