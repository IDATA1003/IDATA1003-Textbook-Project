public class Main {
  public static void main(String[] args) {
    SimpleTune tune = new SimpleTune();
    tune.addNote("C", 8);
    tune.addNote("D", 8);
    tune.addNote("E", 8);
    tune.showNotes();

    MidiPlayer midiPlayer = new MidiPlayer();
    midiPlayer.playTune(tune);
  }
}
