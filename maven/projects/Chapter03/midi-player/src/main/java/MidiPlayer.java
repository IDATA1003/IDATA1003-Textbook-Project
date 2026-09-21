import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.sound.midi.*;

/**
 * A class that supports the creation of simple MIDI sequences.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class MidiPlayer 
{
    // Simple names of the basic notes.
    private static final Set<Character> noteNames = Set.of('A', 'B', 'C', 'D', 'E', 'F', 'G');
    // PlayedNote sequence with sharp notation.
    private static final String[] noteNamesSharps = {
        "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B",
    };
    // PlayedNote sequence with flat notation.
    private static final String[] noteNamesFlats = {
        "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B",
    };
    private static final int NOTES_PER_OCTAVE = noteNamesSharps.length;
    // In MIDI notation, the note value of C in octave 1.
    private static final int C1_BASE_OFFSET = 24;
    // private static final int MIDDLE_C = 60;

    // Default values for creating simple tunes.
    private static final int TICKS_PER_BEAT = 4;
    private static final int VELOCITY = 90;
    private static final int CHANNEL = 1;

    // Set an arbitrary limit to the number of instruments shown.
    private static final int NUM_INSTRUMENTS = 16;
    // The default name of a place to save a track.
    private static final String DEFAULT_FILENAME = "track.csv";

    // The available instruments.
    private Instrument[] instruments;
    // The sequencer to use.
    private Sequencer sequencer;
    // Whether to show the notes as they are played.
    private boolean showNotes;

    /**
     * Create a player of synthesized MIDI sounds.
     */
    public MidiPlayer()
    {
        try {
            instruments = MidiSystem.getSynthesizer().getAvailableInstruments();
            sequencer = MidiSystem.getSequencer();
            // Report on Meta events.
            // These have been embedded in the track to report on the
            // notes being played.
            sequencer.addMetaEventListener(e -> { 
                handleMetaMessage(e);
             });
            showNotes = false;
        }
        catch(MidiUnavailableException ex) {
            throw new RuntimeException("Unfortunately, it is not possible to play MIDI tunes.");
        }
    }
    
    /**
     * Play the given tune.
     * @param tune The tune to be played.
     */
    public void playTune(SimpleTune tune)
    {
        try {
            if(!tune.getNotes().isEmpty()) {
                Sequence sequence = new Sequence(Sequence.PPQ, TICKS_PER_BEAT);
                Track track = sequence.createTrack();
                int tick = 0;
                int instrument = tune.getInstrument();
                if(instrument >= 0 && instrument < instruments.length) {
                    track.add(new MidiEvent(
                            new ShortMessage(ShortMessage.PROGRAM_CHANGE, CHANNEL, instrument, 0), 0));
                    for(Note note : tune.getNotes()) {
                        int noteValue = noteNameToMIDIValue(note.getNoteName());
                        int duration = note.getDuration();
                        if(noteValue > 0) {
                            addNoteToTrack(noteValue, track, tick, duration);
                            tick += duration;
                        }
                        else {
                            throw new IllegalStateException("Note " + note + " is not recognised.");
                        }
                    }
                    playTrack(sequence);
                }
                else {
                    System.err.println("The instrument number must be a positive number less than " +
                            instruments.length);
                }
            }
            else {
                System.err.println("The tune has no notes. Try using its addNote method.");
            }
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException("There is an error in the MIDI data.");
        }
        catch(IllegalStateException e) {
            System.err.println(e.getMessage());
        }
        
    }

    /**
     * Show a subset of the available instruments.
     */
    public void showInstruments()
    {
        showInstruments(NUM_INSTRUMENTS);
    }

    /**
     * Show the given number of instruments (0 to howMany-1).
     * @param howMany How many to show.
     */
    private void showInstruments(int howMany)
    {
        for(int i = 0; i < howMany; i++) {
            System.out.println(String.format("%d: %s", i, instruments[i].getName()));
        }
    }

    /**
     * Play a single note for the given duration (in ticks).
     * @param note The note to be played; e.g. "C", "C#", "Db", "C4" (middle C), "C#5", etc.
     * @param duration The duration of the note, in ticks.
     */
    public void playNote(String note, int duration)
    {
        try {
            Sequence shortSequence = new Sequence(Sequence.PPQ, TICKS_PER_BEAT);
            Track shortTrack = shortSequence.createTrack();
            int noteValue = noteNameToMIDIValue(note);
            if(noteValue > 0) {
                addNoteToTrack(noteValue, shortTrack, 0, duration);
                playTrack(shortSequence);
            }
            else {
                System.err.println("Note " + note + " is not recognised.");
            }
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException("There is an error in the MIDI data.");
        }
    }

    /**
     * Play the given sequence.
     * @param sequence The sequence to be played.
     */
    private void playTrack(Sequence sequence)
    {
        try {
            sequencer.open();
            sequencer.addControllerEventListener(e -> System.out.println(e), new int[] { ShortMessage.NOTE_ON, });
            sequencer.setSequence(sequence);
            sequencer.start();
            while(sequencer.isRunning()) {
                Thread.sleep(100);
            }
            // One more pause to let any sound die away. Needed?
            Thread.sleep(500);
            sequencer.close();
        } catch (InvalidMidiDataException | MidiUnavailableException e) {
            System.err.println("Something went wrong with playing the track.");
        } catch(InterruptedException e) {
            sequencer.close();
        }
    }

    /**
     * Add a note to the given track.
     * @param noteValue MIDI value for the note to be played: 21, 22, etc.
     * @param track The track to which it is to be added.
     * @param tick The time tick at which to play the note.
     * @param duration The length of the note in ticks.
     */
    private void addNoteToTrack(int noteValue, Track track, int tick, int duration)
    {
        try {
            // Include a meta event to that playing can be tracked.
            track.add(new MidiEvent(
                new MetaMessage(0, new byte[] { (byte) ShortMessage.NOTE_ON, (byte) noteValue, }, 2), tick));
            track.add(new MidiEvent(
                new ShortMessage(ShortMessage.NOTE_ON, CHANNEL, noteValue, VELOCITY), tick));
            track.add(new MidiEvent(
                new ShortMessage(ShortMessage.NOTE_OFF, CHANNEL, noteValue, VELOCITY), tick + duration));
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException("There is an error in the MIDI data.");
        }
    }

    /**
     * Return the MIDI integer value for the given note;
     * e.g. "C", "C#", "Db", "C4" (middle C), "C#5", etc.
     * @param note The note.
     * @return the value matching the note, or -1 if the note is not recognised.
     */
    private int noteNameToMIDIValue(String note)
    {
        int noteValue = -1;
        int len = note.length();
        if(len > 0 && len <= 3) {
            String noteName;
            int octave;
            if(len > 1 && Character.isDigit(note.charAt(len - 1))) {
                octave = note.charAt(len - 1) - '0';
                noteName = note.substring(0, len - 1);
            } 
            else {
                // No octave suffix.
                octave = 4;
                noteName = note;
            }        
            int noteNameLength = noteName.length();  
            int noteIndex; 
            if(noteNameLength == 2 && noteName.charAt(noteNameLength - 1) == '#') {
                noteIndex = Arrays.asList(noteNamesSharps).indexOf(noteName);
            } 
            else {
                noteIndex = Arrays.asList(noteNamesFlats).indexOf(noteName);
            }
            if(noteIndex >= 0 && octave >= 0 && octave <= 8) {
                noteValue = C1_BASE_OFFSET + NOTES_PER_OCTAVE * (octave - 1) + noteIndex;
            }
        }

        return noteValue;
    }

    /**
     * Return the note name for the given note value.
     * @param noteValue The MIDI note value.
     * @return The name of the note; e.g. "C", "C#", "Db", "C4" (middle C), "C#5", etc.
     */
    private String noteValueToNoteName(int noteValue)
    {
        int octave = noteValue / NOTES_PER_OCTAVE - 1;
        int noteIndex = noteValue - (octave + 1) * NOTES_PER_OCTAVE;
        if(octave != 4) {
            return noteNamesSharps[noteIndex] + octave;
        }
        else {
            return noteNamesSharps[noteIndex];
        }
    }

    /**
     * Process the meta message event.
     * @param e Meta message.
     */
    private void handleMetaMessage(MetaMessage e)
    {
        if(showNotes) {
            // System.out.print(String.format("Status: %d Type: %d Length: %d ",
            //                  e.getStatus(), e.getType(), e.getLength()));
            byte[] data = e.getData();
            // 47 appears to mark the end of the sequence.
            if(e.getType() != 47) {
                System.out.print(noteValueToNoteName(data[1]) + " ");
            }
            else {
                System.out.println();
            }
        }
    }
}
