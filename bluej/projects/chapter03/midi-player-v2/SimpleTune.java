import java.util.ArrayList;

/**
 * Model a simple tune as a sequence of notes played
 * on a particular instrument.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class SimpleTune
{
    // The instrument to use.
    // The actual instrument is defined by the
    // MIDI implementation.
    private int instrument;
    // The notes of the tune, in sequence.
    private final ArrayList<Note> notes;
    
    /**
     * Support the creation of a simple tune.
     */
    public SimpleTune()
    {
        instrument = 0;
        notes = new ArrayList<>();
    }
    
    /**
     * Add a note to the tune.
     * @param noteName The name of the note; e.g., "C", "C#", "E5", etc.
     * @param duration The length of time the note should be played for.
     */
    public void addNote(String noteName, int duration)
    {
        notes.add(new Note(noteName, duration));
    }
    
    /**
     * List the notes and durations of the tune.
     */
    public void showNotes()
    {
        System.out.println("Instrument number: " + instrument);
        for(Note note : notes) {
            System.out.print(note.getNoteName() + " (" + note.getDuration() + ") ");
        }
        System.out.println();
    }
    
    /**
     * Discard all of the notes.
     */
    public void clear()
    {
        notes.clear();
    }
    
    /**
     * Get the instrument to be used with the tune.
     * @return The instrument.
     */
    public int getInstrument()
    {
        return instrument;
    }

    /**
     * Set the instrument to be used.
     * @param instrument A positive value within the range of available instruments.
     */
    public void setInstrument(int instrument)
    {
        if(instrument >= 0) {
            this.instrument = instrument;
        }
        else {
            System.err.println("The instrument number must be greater-than or equal-to zero: " +
                                instrument);
        }
    }

    /**
     * Get the notes of the tune.
     * @return The notes.
     */
    public ArrayList<Note> getNotes() 
    {
        return notes;
    }

}
