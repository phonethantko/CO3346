public class Interpreter {
	SimpleMidiSynth synth = new SimpleMidiSynth();
	int nmin = 60, nmax = 72;
	public void interpret(SimpleSwarm swarm) {
		int note = (int)(nmin + swarm.x/1000 * (nmax - nmin));
		
		synth.channel.noteOn(note,80);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			
		}finally {
			synth.channel.noteOff(note);
		}
		
		
	}
}