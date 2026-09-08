import java.util.ArrayList;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.6
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> trackList;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        player = new MusicPlayer();
        reader = new TrackReader();
        trackList = reader.readTracks("../audio", ".mp3");
        if(! trackList.isEmpty()) {
            System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        }
        else {
            System.out.println("No tracks loaded.");
        }
        System.out.println();
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        trackList.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param aTrack The track to be added.
     */
    public void addTrack(Track aTrack)
    {
        trackList.add(aTrack);
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return trackList.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        if(validIndex(index)) {
            System.out.print("Track " + index + ": ");
            Track aTrack = trackList.get(index);
            System.out.println(aTrack.getDetails());
        }
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track aTrack : trackList) {
            System.out.println(aTrack.getDetails());
        }
        System.out.println();
    }
    
    /**
     * Show a list of all the tracks,along with
     * their index in the collection.
     */
    public void listWithIndex()
    {
        System.out.println("Track listing: ");
        int index = 0;
        while(index < trackList.size()) {
            Track aTrack = trackList.get(index);
            System.out.printf("%d: %s%n", index, aTrack.getDetails());
            index++;
        }
        System.out.println();
    }
    
    /**
     * Find the index of the first track whose title contains
     * the given search string.
     * If there is no match, -1 is returned.
     * @param searchString The string to be searched for in the titles.
     * @return The first matching index, or -1 if there is none.
     */
    public int findFirstMatching(String searchString)
    {
        int index = 0;
        // Record that we will be searching until a match is found.
        int location = -1;

        while(location == -1 && index < trackList.size()) {
            if(trackList.get(index).getTitle().contains(searchString)) {
                location = index;
            }
            else {
                index++;
            }
        }
        return location;
    }    
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track aTrack : trackList) {
            if(aTrack.getArtist().contains(artist)) {
                System.out.println(aTrack.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(validIndex(index)) {
            trackList.remove(index);
        }
    }
    
    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if(trackList.size() > 0) {
            playTrack(0);
        }
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(validIndex(index)) {
            Track aTrack = trackList.get(index);
            player.startPlaying(aTrack.getFilename());
            aTrack.incrementPlayCount();
            System.out.println("Now playing: " + aTrack.getArtist() + " - " + aTrack.getTitle());
        }
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }
    
    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean validIndex(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= trackList.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
}
