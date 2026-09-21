import java.util.ArrayList;
import java.util.List;

/**
 * The NewsFeed class stores news posts for the news feed in a
 * social network application.
 * 
 * Display of the posts is currently simulated by printing the
 * details to the terminal. (Later, this should display in a browser.)
 * 
 * This version does not save the data to disk, and it does not
 * provide any search or ordering functions.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.4
 */
public class NewsFeed
{
    private final List<Post> posts;

    /**
     * Construct an empty news feed.
     */
    public NewsFeed()
    {
        posts = new ArrayList<>();
    }

    /**
     * Add a post to the news feed.
     * 
     * @param post  The post to be added.
     */
    public void addPost(Post post)
    {
        posts.add(post);
    }

    /**
     * Show the news feed. Currently: print the news feed details to the
     * terminal. (To do: replace this later with display in web browser.)
     */
    public void show()
    {
        for(Post post : posts) {
            System.out.println(formatPost(post));
        }
    }

    /**
     * Format the given post for display.
     */
    private String formatPost(Post post)
    {
        String formattedPost = post.getAuthor() + '\n';
        
        switch(post) {
            case MessagePost messagePost -> 
                formattedPost += messagePost.getText() + '\n';
            case PhotoPost photoPost -> 
                formattedPost += "  [" + photoPost.getImageFile() + "]\n  "
                                 + photoPost.getCaption() + '\n';
        }
        
        formattedPost += post.getFormattedTime();
        int likes = post.getLikes();
        
        formattedPost += switch (likes) 
            {
                case 0  -> "\n";
                case 1  -> "  -  " + likes + " person likes this.\n";
                default -> "  -  " + likes + " people likes this.\n";
            };

        List<String> comments = post.getComments();
        if (comments.isEmpty()) {
            formattedPost += "   No comments.\n";
        } 
        else {
            formattedPost += "   " + comments.size() 
                             + " comment(s). Click here to view.\n";
        }
        return formattedPost;
    }
}
