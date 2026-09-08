/**
 * Simple representation of MIDI note within a tune,
 * including its playing duration.
 * A default duration of 8 ticks is used if not
 * otherwise specified.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class Note
{
    // The name of the note; e.g. "C", "C#", "E5", etc.
    private String noteName;
    // How long the note should last.
    private int duration;
    
    /**
     * Create a note with the default duration.
     * @param noteName The name of the note.
     */
    public Note(String noteName)
    {
        this.noteName = noteName;
        this.duration = 8;
    }
    
    /**
     * Create a note with the given duration.
     * @param noteName The name of the note.
     * @param duration How long the note should last.
     */
    public Note(String noteName, int duration) 
    {
        this.noteName = noteName;
        this.duration = duration;
    }
    
    /**
     * Get the name of the note.
     * @return The note's name.
     */
    public String getNoteName() 
    {
        return noteName;
    }
    
    /**
     * Get the duration of the note.
     * @return The note's duration.
     */
    public int getDuration() 
    {
        return duration;
    }
}
