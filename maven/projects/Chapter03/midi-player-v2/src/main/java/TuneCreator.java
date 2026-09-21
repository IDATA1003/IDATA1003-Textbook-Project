
/**
 * A class to create a simple tune.
 *
 * @author (your name)
 * @version 1.0
 */
public class TuneCreator
{
    private MidiPlayer player;

    /**
     * Create and initialise a TuneCreator
     */
    public TuneCreator()
    {
        player = new MidiPlayer();
    }

    /**
     * Create a new tune and play it.
     */
    public void createAndPlay()
    {
        SimpleTune tune = new SimpleTune();
        
        tune.addNote("G4", 2);
        tune.addNote("G4", 2);
        tune.addNote("G4", 4);
        tune.addNote("G4", 2);
        tune.addNote("A4", 6);
        tune.addNote("E4", 4);
        tune.addNote("G4", 4);
        tune.addNote("G4", 4);
        tune.addNote("C5", 2);
        tune.addNote("D5", 6);
        
        tune.setInstrument(7);
        
        player.playTune(tune);
    }
}
