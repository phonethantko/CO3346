import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import processing.core.PApplet;

public class SimpleSwarm {
	int D,L;
	float speed = 10, particleDiam = 5, acc =2;
	public float inner = 20, outer = 100;
	public float x=0, y = 0;
	private ArrayList<Particle> particles;

	
	
	public SimpleSwarm(int numParticles, int d, int l) {
		D = d; L = l;
		particles = new ArrayList<Particle>();
		for(int i=0;i< numParticles; i++) {
			//Note that x and v are 2D vectors
			float[] x = new float[D];
			float[] v = new float[D];
			for(int j=0;j<D;j++) {
				x[j] = (float)(L * Math.random());    //   0 < x < L
				v[j] = (float)(Math.random() - 0.5f); //-0.5 < v < 0.5
			}
			VecMaths.clamp(v, speed);//Limit to max speed
			particles.add(new Particle(x,v,particleDiam));
		}
	}

	public void draw(SimpleSwarmApplet simpleSwarmApplet) {
		for(Particle particle: particles) {
			//This stores all the particles in INNER circle
			float[] YIcm = new float[particle.position.length];
			int Isize = 0;
			//This stores all the particles in OUTER circle
			float[] YOcm = new float[particle.position.length];
			int Osize = 0;
			//Kinematic variables
			float acceleration[] = new float[particle.position.length];
			boolean inside = false, outside = false;//No particle in Inner or outer
			//The swarm Algorithms
			for(Particle p: particles) {
				//Check if p is itself
				if(p == particle) continue;//ignore current iteration
				//Determine if p is inside inner radius
				if(VecMaths.dist(particle.position, p.position) < inner) {
					inside = true; // These is particles in inner circle
					VecMaths.add(YIcm, YIcm, p.position);
					Isize++; //TRack the number of particle inside the inner circle
				}else if(VecMaths.dist(particle.position, p.position) < outer) {
					outside = true; // These is particles in inner circle
					VecMaths.add(YOcm, YOcm, p.position);
					Osize++; //TRack the number of particle inside the inner circle
				}
				
			}	
			//Step 3.1 page 58
			if(inside) {
				//Averaging the YIcm
				YIcm = VecMaths.scalarMulti(YIcm, 1.0f/Isize, YIcm);
				acceleration = VecMaths.subtract(acceleration, 
						particle.position, YIcm);
			}else if(outside) {
				//Averaging the YOcm
				YOcm = VecMaths.scalarMulti(YOcm, 1.0f/Osize, YOcm);
				acceleration = VecMaths.subtract(acceleration, 
						YOcm, particle.position);
			}else {
				acceleration = new float[] {0,0};
			}
			//Step 3.2 a <- a0 * a/|a|(|a , 0)
			if(inside || outside) {
				VecMaths.clamp(acceleration, acc);
//				acceleration = VecMaths.scalarMult(acceleration, 
//						1.0f/VecMaths.length(acceleration), 
//						acceleration);
			}
			//Step 3.3 v(t + 1) = v(t) + a(t)
			particle.velocity = VecMaths.add(particle.velocity, 
											 particle.velocity,
											 acceleration);
			//Step 3.4 v(t + 1) <- v0 * (vt+1)/|vt+1|
			VecMaths.clamp(particle.velocity, speed);
//			particle.velocity =VecMaths.scalarMult(particle.velocity, 
//					1.0f/VecMaths.length(particle.velocity), 
//					particle.velocity);
			
			//Step 3.5 x(t + 1) = x(t) + v(t)
			particle.position = VecMaths.add(particle.position, 
					 particle.position,
					 particle.velocity);
			//wrap the position
			wrap(particle.position);
			
			//Draw our particles
			simpleSwarmApplet.ellipse(	particle.position[0],
										particle.position[1],
										particleDiam, particleDiam);
		

		}
		simpleSwarmApplet.textSize(32);
		simpleSwarmApplet.fill(255);
		simpleSwarmApplet.text("inner:" + inner, 10, 60);
		simpleSwarmApplet.text("outer:" + outer, 10, 160);
		calculateXY();
		simpleSwarmApplet.text("X:"+x+ " Y:"+y, 10, 200);
	}

	private void calculateXY() {
		x = y = 0;
		for(Particle p : particles) {
			x += p.position[0];
			y += p.position[0];
		}
		x /= particles.size();
		y /= particles.size();
		
		Particle p = particles.get((int)(Math.random()*particles.size()));
		x = p.position[0];
		
	}

	private void wrap(float[] position) {
		for(int i = 0;i < position.length;i++) {
			position[i] += L;
			position[i] %= L;
		}
		
	}

}