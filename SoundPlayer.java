import javax.sound.sampled.*;

/**
 * This class is used to play sounds
 * @author Tadas Ivanauskas
 */
public class SoundPlayer{
    
    private String pathToSound;
    private float volume;

    /**
     * Constructor Method 
     * @param path The file path to the sound 
     */
    public SoundPlayer(String path){
        this.pathToSound = path;
        this.volume=1.0f;
    }

    /**
     * Method to play a sound using AudioInputStream and Clips
     */
    public void play(){
        try {
            /**
             * Grabbing the audio
             */
            AudioInputStream AIS = AudioSystem.getAudioInputStream(getClass().getResource(pathToSound));
            Clip soundClip = AudioSystem.getClip();
            soundClip.open(AIS);

            /**
             * Changing the volume
             */
             FloatControl control =(FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
             float vol = (float) (Math.log(volume) / Math.log(10) * 20);
             control.setValue(vol);
            
             soundClip.start();

            soundClip.drain();
            AIS.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to mute and unmute the volume
     */
    public void toggleMute(){
        if(volume>0){
            this.volume = 0;
        }
        
        else{
            this.volume=1.0f;
        }
    }

    /**
     * Method to change the file path to an audio sound
     * @param path The new path of the file
     */
    public void changePath(String path){
        this.pathToSound = path;
    }
}