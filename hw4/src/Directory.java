import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Class representing a directory in the file system.
 */
public class Directory extends FileSystemElement
{

    private LinkedList<FileSystemElement> children;

    /**
     * Constructs a Directory with the specified name and parent.
     *
     * @param name   the name of the directory
     * @param parent the parent directory of the directory
     */
    public Directory(String name, FileSystemElement parent)
    {
        super(name, parent);
        this.children = new LinkedList<>();
    }

    /**
     * Adds a child FileSystemElement to this directory.
     *
     * @param child the FileSystemElement to add as a child
     */
    public void add_child(FileSystemElement child)
    {
        if (child != null)
        {
            children.add(child);
            child.set_parent(this);
        }
    }

    /**
     * Retrieves a child FileSystemElement with the specified name.
     *
     * @param name the name of the child FileSystemElement to retrieve
     * @return the child FileSystemElement with the specified name, or null if not found
     */
    public FileSystemElement get_child(String name)
    {
        return children.stream().filter(child -> child.get_name().equals(name)).findFirst().orElse(null);
    }

    /**
     * Retrieves a list of all children of this directory.
     *
     * @return a list of all children of this directory
     */
    public LinkedList<FileSystemElement> get_children()
    {
        return new LinkedList<>(children);
    }

    /**
     * Searches for a FileSystemElement with the specified name recursively in this directory and its subdirectories.
     *
     * @param name the name of the FileSystemElement to search for
     * @return the FileSystemElement with the specified name, or null if not found
     */
    public FileSystemElement search(String name)
    {
        for (FileSystemElement child : children)
        {
            if (child.get_name().equals(name))
                return child;  // Return the child if names match

            if (child instanceof Directory)
            {
                FileSystemElement result = ((Directory) child).search(name);
                if (result != null)
                    return result;  // Return the result of the recursive search

            }
        }
        return null;  // Return null if no match is found
    }


    // --------------------- 4 DELETE FILE / DIRECTORY
    /**
     * Removes the child FileSystemElement with the specified name from this directory.
     *
     * @param name the name of the child FileSystemElement to remove
     * @return true if the child was successfully removed, false otherwise
     */
    public boolean remove_child(String name)
    {
        Optional<FileSystemElement> childToRemoveOpt = children.stream().filter(child -> child.get_name().equals(name)).findFirst();

        if (!childToRemoveOpt.isPresent())
        {
            System.out.println("No such file or directory.");
            return false;
        }

        FileSystemElement childToRemove = childToRemoveOpt.get();
        if (childToRemove instanceof Directory)
        {
            // Recursively delete all contents of the directory
            ((Directory) childToRemove).clear_all_children();
        }

        // Remove the child after clearing its contents
        children.remove(childToRemove);
        childToRemove.set_parent(null);
        return true;
    }

    // Helper method to recursively delete all children of a directory
    private void clear_all_children()
    {
        Iterator<FileSystemElement> iterator = children.iterator();
        while (iterator.hasNext())
        {
            FileSystemElement child = iterator.next();
            if (child instanceof Directory)
                ((Directory) child).clear_all_children();

            iterator.remove(); // Safe removal during iteration
            child.set_parent(null);
        }
    }
}
