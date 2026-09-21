/**
 * Min class for the music organizer application.
 * Play audiofiles from the folder ../audio
 */
public class Main {

    public static void main(String[] args) {
        MusicOrganizer organizer = new MusicOrganizer();
        organizer.addFile("../audio/Lisa Davies-Burning Man.mp3");
        organizer.addFile("../audio/Lisa Davies-Livid.mp3");
        organizer.addFile("../audio/Lisa Davies-Maverick.mp3");
        organizer.addFile("../audio/Studio Lisa-Avant Guardian.mp3");
        organizer.addFile("../audio/Studio Lisa-The Falcon.mp3");

        System.out.println("Number of files: " + organizer.getNumberOfFiles());
        System.out.println("Listing all files:");
        for (int i = 0; i < organizer.getNumberOfFiles(); i++) {
            organizer.listFile(i);
        }

        System.out.println("Starting to play the second file:");
        organizer.startPlaying(1);

        // Wait for a while to let the music play
        try {
            Thread.sleep(6000); // Wait for 6 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Stopping the music.");
        organizer.stopPlaying();
    }
}
