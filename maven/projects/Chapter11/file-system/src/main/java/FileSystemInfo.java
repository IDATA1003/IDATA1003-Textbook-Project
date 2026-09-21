import java.io.File;

/**
 * A class providing information on aspects of a file system
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 */
public class FileSystemInfo
{    
    /**
     * Print out the content of the folder given as a parameter.
     * 
     * @param folderName The name of the folder to be listed.
     */
    public void listFolder(String folderName)
    {
        listFolder(new File(folderName));
    }

    /**
     * Print out the content of the folder given as a parameter.
     * The given File should be a folder rather than a data file.
     * Nothing will be printed if it is not a folder.
     * 
     * @param folder The folder of interest.
     */
    private void listFolder(File folder)
    {
        if(folder.isDirectory()) {
            for(File file : folder.listFiles()) {
                System.out.println(file.getName());
            }
        }
    }
}
