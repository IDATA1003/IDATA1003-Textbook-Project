import java.util.ArrayList;
import java.util.List;

/**
 * This class stores information about a news feed post in a 
 * social network. Posts can be stored and displayed. This class
 * serves as a superclass for more specific post types.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.4
 */
public abstract sealed class Post permits MessagePost, PhotoPost
{
    private final String username;  // username of the post's author
    private final long timestamp;
    private int likes;
    private final List<String> comments;

    /**
     * Constructor for objects of class Post.
     * 
     * @param author    The username of the author of this post.
     */
    public Post(String author)
    {
        username = author;
        timestamp = System.currentTimeMillis();
        likes = 0;
        comments = new ArrayList<>();
    }

    /**
     * Record one more 'Like' indication from a user.
     */
    public void like()
    {
        likes++;
    }

    /**
     * Record that a user has withdrawn his/her 'Like' vote.
     */
    public void unlike()
    {
        if (likes > 0) {
            likes--;
        }
    }

    /**
     * Add a comment to this post.
     * 
     * @param text  The new comment to add.
     */
    public void addComment(String text)
    {
        comments.add(text);
    }

    /**
     * Return the time of creation of this post.
     * 
     * @return The post's creation time, as a system time value.
     */
    public long getTimeStamp()
    {
        return timestamp;
    }

    /**
     * Get the author of this post.
     * @return The name of the post's author.
     */
    public String getAuthor() 
    {
        return username;
    }

    /**
     * Return a formatted version of the posting time.
     * @return The posting time.
     */
    public String getFormattedTime() 
    {
        return timeString(timestamp);
    }

    /**
     * Get the number of likes for this post.
     * @return The number of likes.
     */
    public int getLikes() 
    {
        return likes;
    }

    /**
     * Get a copy of the list of comments.
     * @return The comments for this post.
     */
    public List<String> getComments() 
    {
        return List.copyOf(comments);
    }

    /**
     * Create a string describing a time point in the past in terms 
     * relative to current time, such as "30 seconds ago" or "7 minutes ago".
     * Currently, only seconds and minutes are used for the string.
     * 
     * @param time  The time value to convert (in system milliseconds)
     * @return      A relative time string for the given time
     */
    
    private String timeString(long time)
    {
        long current = System.currentTimeMillis();
        long pastMillis = current - time;      // time passed in milliseconds
        long seconds = pastMillis/1000;
        long minutes = seconds/60;
        if(minutes > 0) {
            return minutes + " minutes ago";
        }
        else {
            return seconds + " seconds ago";
        }
    }
}
