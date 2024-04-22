import java.sql.Timestamp;

/**
 * Abstract class representing a file system element such as a file or directory.
 */
public abstract class FileSystemElement
{

    private String name;
    private Timestamp dateCreated;
    private FileSystemElement parent;

    /**
     * Constructs a FileSystemElement with the specified name and parent.
     *
     * @param name   the name of the file system element
     * @param parent the parent directory of the file system element
     */
    public FileSystemElement(String name, FileSystemElement parent)
    {
        this.name = name;
        this.parent = parent;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Gets the name of the file system element.
     *
     * @return the name of the file system element
     */
    public String get_name()
    {
        return name;
    }

    /**
     * Sets the name of the file system element.
     *
     * @param name the new name for the file system element
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the creation date of the file system element.
     *
     * @return the creation date of the file system element
     */
    public Timestamp get_date()
    {
        return dateCreated;
    }

    /**
     * Gets the parent directory of the file system element.
     *
     * @return the parent directory of the file system element
     */
    public FileSystemElement get_parent()
    {
        return parent;
    }

    /**
     * Sets the parent directory of the file system element.
     *
     * @param parent the new parent directory for the file system element
     */
    public void set_parent(FileSystemElement parent)
    {
        this.parent = parent;
    }
}
