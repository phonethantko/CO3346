import processing.core.PApplet;

public class Ball {

    int x, y; // Position of the ball
    int radius; // Dimension of the ball
    int mass;
    float forceX, forceY;
    float accelX, accelY;
    int velX, velY;
    int color;

    public Ball(){
        x = y = 100;
        radius = 50;
        mass = 1;
        forceX = 0;
        forceY = 9.81f;
        accelX = accelY = 0;
        velX = 50;
        velY = 10;
        color = 255;
    }

    public void draw(PApplet app) {
        app.ellipseMode(app.RADIUS);
        app.fill(255);
        app.ellipse(x, y, radius, radius);
    }

    public void update(PApplet app) {
        float t = 0.1f; // t is the time passed since the last update

        // Calculate the acceleration form force: F = ma
        accelX = forceX / mass;
        accelY = forceY / mass;

        // Calculate the velocity using v = u + a*t
        velX += accelX * t;
        velY += accelY * t;

        //Calculate the position using s'= s + v*t
        x += velX * t;
        y += velY * t;

        // Check for floor
        if(y > app.height - radius) {
            velY *= -0.9f;
            y = app.height - radius;

        }
        // Check for wall
        if(x > app.width - radius) {
            velX *= -0.9f;
            x = app.width - radius;
        } else if ( x< radius) {
            velX *= -0.9f;
            x = radius;
        }
    }

}
