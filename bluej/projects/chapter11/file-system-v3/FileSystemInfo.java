import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * A class providing information on aspects of a file system
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.3
 */
public class FileSystemInfo
{
    private static final String INDENT_LEVEL = "   ";
    
    /**
     * Print out the content of the folder given as a parameter.
     * Indent each new level to show the folder structure.
     * 
     * @param folderName The name of the folder to be listed.
     */
    public void listFolder(String folderName)
    {
        listFolder(new File(folderName));
    }

    /**
     * List the hierarchy of folders starting at folder.
     * If the file is not a folder, its name is simply printed.
     * Indent each new level to show the structure.
     * 
     * @param folder  The file to be listed.
     */
    private void listFolder(File folder) 
    {
        if (folder.isDirectory()) {
            listFormatted(folder, "", INDENT_LEVEL);
        } else {
            System.out.println(folder.getName());
        }
    }

    /**
     * List the contents of the given folder, applying
     * indentation at each level to show the nested structure.
     * 
     * @param folder The folder at the root of the hierarchy.
     * @param currentIndent The current amount of indentation.
     * @param levelIndent The indent to be added at each new level.
     */
    private void listFormatted(File folder,
                              String currentIndent,
                              String levelIndent)
    {
        System.out.printf("%s[%s]%n", currentIndent, folder.getName());
        currentIndent += levelIndent;
        for(File f : folder.listFiles()) {
            if(f.isDirectory()) {
                listFormatted(f, currentIndent, levelIndent);
            }
            else {
                System.out.printf("%s%s (%s)%n", currentIndent, f.getName(), 
                                                 formatSize(f));
            }
        }
    }

    /**
     * Return a string describing the size of the given file.
     * @param f The file.
     * @return A description of the file's size.
     */
    private String formatSize(File f)
    {
        long size = f.length();
        return String.format("%d bytes", size);
    }
}
