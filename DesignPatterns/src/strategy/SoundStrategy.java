package strategy;

/**
 * Created by alexeysoshin on 21/06/2016.
 */
public class SoundStrategy {
    private Sound sound;

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void makeSound() {
        System.out.println(sound.getSound());
    }
}
