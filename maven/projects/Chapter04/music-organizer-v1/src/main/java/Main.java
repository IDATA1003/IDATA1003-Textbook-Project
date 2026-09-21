/**
 * Main class for the Music Organizer application.
 * Running some simple tests of the MusicOrganizer class.
 */

public class Main {
  
    public static void main(String[] args) {
        MusicOrganizer organizer = new MusicOrganizer();
        organizer.addFile("song1.mp3");
        organizer.addFile("song2.mp3");
        organizer.addFile("song3.mp3");

        System.out.println("Number of files: " + organizer.getNumberOfFiles());
        organizer.listFile(1);
        organizer.removeFile(0);
        System.out.println("Number of files after removal: " + organizer.getNumberOfFiles());
    }
}
