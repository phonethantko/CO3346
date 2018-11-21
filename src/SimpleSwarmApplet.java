import java.util.Timer;
import java.util.TimerTask;

import processing.core.PApplet;

public class SimpleSwarmApplet extends PApplet {
	int D = 2, L = 1000, numParticles = 1000;
	SimpleSwarm swarm;
	Interpreter interpreter = new Interpreter();
	//This is timer
	TimerTask task = new TimerTask() {
		public void run() {
			interpreter.interpret(swarm);
		}
	};
	Timer timer = new Timer();
	public void settings() {
		size(L,L);
	}
	public void setup() {
		swarm = new SimpleSwarm(numParticles, D, L);
		smooth();
		noStroke();
		timer.scheduleAtFixedRate(task, 1000L, 1000L);
	}
	public void draw() {
		background(0);
		fill(255);
		swarm.draw(this);
	}
	
	public void keyPressed() {
		if (key == CODED) {
		    if (keyCode == UP) {
		      swarm.inner++;
		    } else if (keyCode == DOWN) {
		    	swarm.inner--;
		    } else if (keyCode == ALT) {
		    	swarm.outer++;
		    } else if (keyCode == CONTROL) {
		    	swarm.outer--;
		    } 
		  } 
	}
	public static void main(String[] args) {
		PApplet.main(new String[] {"SimpleSwarmApplet"});

	}

}