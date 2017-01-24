/**
 * @Author Chris Carrillo
 * @Date 01/23/2017
 */
public class Guitar37 implements Guitar {
    public static final String KEYBOARD =
            "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout

    private static final int KEYBOARD_LENGTH = KEYBOARD.length(); // string length of KEYBOARD

    private GuitarString[] strings; // array of GuitarString
    private int numTics = 0; // counts the number of tics

    /**
     * Takes in a boolean to determine if KPGuitarStrings or SimpleGuitarStrings
     * should be created.
     */
    public Guitar37(boolean useKP) {
        if (useKP) {
            strings = new GuitarString[KEYBOARD_LENGTH];

            for (int i = 0; i < strings.length; i++) {
                strings[i] = new KPGuitarString(440 * (Math.pow(2, (i - 24.0) / 12.0)));
            }
        }
        else {
            strings = new GuitarString[KEYBOARD_LENGTH];

            for (int i = 0; i < strings.length; i++) {
                strings[i] = new SimpleGuitarString(440 * (Math.pow(2, (i - 24.0) / 12.0)));
            }
        }
    }

    /**
     * Sets i to the pitch + 12. If i < keyboard length and i >= 0, then
     * pluck the string in the array.
     */
    @Override
    public void playNote(int pitch) {
        int i = pitch + 12;

        if (i < KEYBOARD_LENGTH && i >= 0)
            strings[i].pluck();
    }

    /**
     * hasString verifies returns true if the given character
     * has a note on the guitar and false if it does not
     */
    @Override
    public boolean hasString(char key) {
        return (KEYBOARD.indexOf(key) != -1);
    }

    /**
     * pluck takes a character to first check if the character has a note
     * if yes, then pluck the string, if not throw an IllegalArgumentException
     */
    @Override
    public void pluck(char key) {
        if (hasString(key))
            strings[KEYBOARD.indexOf(key)].pluck();
        else
            throw new IllegalArgumentException();
    }

    /**
     * sample returns the sum using a foreach loop
     * for every GuitarString of s in strings, add
     * the sample to sum
     */
    @Override
    public double sample() {
        double sum = 0.0;

        for (GuitarString s : strings) {
            sum += s.sample();
        }
        return sum;
    }

    /**
     * tic uses a foreach loop to advance the time
     * 1 tic and increments the number of tics
     */
    @Override
    public void tic() {
        for (GuitarString gs : strings) {
            gs.tic();
        }
        numTics++;
    }

    /**
     * time returns the number of tics after
     * tic increments the number of tics
     */
    @Override
    public int time() {
        return numTics;
    }
}