/**
 * Min class for the music organizer application.
 * Play audiofiles from the folder ../audio
 */
public class Main {

    public static void main(String[] args) {
        MusicOrganizer organizer = new MusicOrganizer();

        System.out.println("Number of files: " + organizer.getNumberOfTracks());
        System.out.println("Listing all files:");
        for (int i = 0; i < organizer.getNumberOfTracks(); i++) {
            organizer.listTrack(i);
        }

        System.out.println("Starting to play the second file:");
        organizer.playTrack(1); // Play the second track (index 1)

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
