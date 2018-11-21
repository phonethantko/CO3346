public class Particle {
    float[] position, velocity; // 1D array for x and y
    float diam; // Diameter of circle

    public Particle(float[] p, float[] v, float d) {
        position = p;
        velocity = v;
        diam = d;
    }
}
