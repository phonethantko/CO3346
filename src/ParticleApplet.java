import processing.core.PApplet;

public class ParticleApplet extends PApplet {

    int L = 400;
    Ball ball1 = new Ball();
    Ball ball2 = new Ball();

    public void settings() {
        size(L, L);
    }

    public void setup() {
        background(0);
        surface.setResizable(true);
        ball1.color = 120;
        ball1.velX *= -1;
    }

    public void draw() {
        update();
        background(0);
        ball1.draw(this);
        ball2.draw(this);
    }

    public void update() {
        ball1.update(this);
        ball2.update(this);
    }

    public static void main(String[] args) {
        // Starting a new applet window
        PApplet.main(new String[]{
           "ParticleApplet" // Name of the class
        });
    }
}
