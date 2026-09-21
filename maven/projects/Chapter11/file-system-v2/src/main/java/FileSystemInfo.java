import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * A class providing information on aspects of a file system
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.2
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
     * List the hierarchy of folders starting at folder.
     * 
     * @param folder The folder at the root of the hierarchy.
     */
    private void listFolder(File folder)
    {
        if(folder.isDirectory()) {
            List<File> subfolders = new LinkedList<>();
            System.out.println("Folder: " + folder.getName());
            for(File f : folder.listFiles()) {
                System.out.println(f.getName());
                if(f.isDirectory()) {
                    subfolders.add(f);
                }
            }
            for(File f : subfolders) {
                listFolder(f);
            }
        }
    }
}
