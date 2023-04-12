package com.example.lzzll.jpa.common.test;

import javax.sound.midi.*;

/**
 * @Author lf
 * @Date 2022/9/9 17:16
 * @Description:
 */
public class SoundPlayClass {

    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException {
        Sequencer player = MidiSystem.getSequencer();
        player.open();
        Sequence seq = new Sequence(Sequence.PPQ,4);
        Track track = seq.createTrack();
        ShortMessage message = new ShortMessage();
        message.setMessage(144,1,44,100);
        MidiEvent midiEvent = new MidiEvent(message, 1);
        track.add(midiEvent);
        ShortMessage message2 = new ShortMessage();
        message2.setMessage(128,1,44,100);
        MidiEvent midiEvent2 = new MidiEvent(message2, 1);
        track.add(midiEvent2);
        player.setSequence(seq);
        player.start();
    }

}
