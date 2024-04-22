/**
 * Class representing a file in the file system.
 */
public class File extends FileSystemElement
{

    private String content;

    /**
     * Constructs a File with the specified name and parent.
     *
     * @param name   the name of the file
     * @param parent the parent directory of the file
     */
    public File(String name, FileSystemElement parent)
    {
        super(name, parent);
        this.content = "";
    }

}
