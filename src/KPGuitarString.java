import java.util.*;

/**
 * Implements your code here.
 * Change comments
 * @Author Your Name
 * @Date
 */
public class KPGuitarString implements GuitarString{
    private Queue<Double> ringBuffer; // Queue of the ringBuffer
    private double n; // double of the capacity

    public static final double EDF = 0.996; // energy decay factor constant

    /** constructor constructs a KPGuitarString of the given frequency
       throws IllegalArgumentException if freq <= 0 or n < 2
     */
    public KPGuitarString(double frequency) throws IllegalArgumentException {
        n = Math.round(StdAudio.SAMPLE_RATE / frequency);
        if (frequency <= 0 || n < 2) throw new IllegalArgumentException();
        ringBuffer = new LinkedList<Double>();

        for (int i = 0; i < n; i++) {
            ringBuffer.add(0.0);
        }
    }

    /** constructs KPGuitarString initializes ringBuffer to array values
       if init has less than 2 elements, IllegalArgumentException is thrown
     */
    public KPGuitarString(double[] init) throws IllegalArgumentException {
        if (init.length < 2) throw new IllegalArgumentException();
        ringBuffer = new LinkedList<Double>();
        for (int i = 0; i < init.length; i++) {
            ringBuffer.add(init[i]);
        }
    }

    // replaces elements in the ringBuffer with a random value between -0.5 & 0.5
    public void pluck() {
        Random rand = new Random();
        for (int i = 1; i < ringBuffer.size(); i++) {
            ringBuffer.remove();
            ringBuffer.add(rand.nextDouble() - 0.5);
        }
    }

    /** deletes sample at front of the ringBuffer & adds to the ringBuffer
       the average of the first 2 samples times the EDF
     */
    public void tic() {
        double n1 = ringBuffer.remove();
        double n2 = sample();
        ringBuffer.add(EDF * ((n1 + n2) / 2));
    }

    // returns the current sample
    public double sample() {
        return ringBuffer.peek();
    }
}
