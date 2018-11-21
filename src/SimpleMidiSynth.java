import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class SimpleMidiSynth {
    Synthesizer synthesizer = null;
    MidiChannel channel;

    public SimpleMidiSynth() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
        }catch(MidiUnavailableException e) {
            e.printStackTrace();
        }
        MidiChannel[] channels = synthesizer.getChannels();
        channel = channels[0];
    }
    public void close() { synthesizer.close();}


    public static void main(String[] args) {
        SimpleMidiSynth synth = new SimpleMidiSynth();
        for(int i = 0; i< 8;i+=2) {
            synth.channel.noteOn(60+i, 80);
            synth.channel.noteOn(62+i, 80);
            synth.channel.noteOn(64+i, 80);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {

            }finally {
                synth.channel.noteOff(60);
                synth.channel.noteOff(62);
                synth.channel.noteOff(64);
            }
        }
        synth.close();

    }

}