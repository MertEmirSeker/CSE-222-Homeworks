import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents a file system with directories and files.
 */
public class FileSystem
{
    private Directory root;
    private Directory current_directory;

    /**
     * Constructs a FileSystem with a root directory and sets the current directory to the root.
     */
    public FileSystem()
    {
        this.root = new Directory("root", null);
        this.current_directory = this.root;
    }

    /**
     * Retrieves the current path of the file system.
     *
     * @return the current path of the file system
     */

    public String get_current_path()
    {
        // If the current directory is the root directory, return "/"
        if (current_directory == root)
            return "/";

        StringBuilder path = new StringBuilder();
        Directory temp = current_directory;
        // Traverse up the directory tree until reaching the root directory
        while (temp != null && temp != root)
        {
            path.insert(0, "/" + temp.get_name());            // Insert the directory name at the beginning of the path
            temp = (Directory) temp.get_parent();            // Move to the parent directory

        }
        return path.toString();
    }

    /**
     * Retrieves the current directory of the file system.
     *
     * @return the current directory of the file system
     */
    public Directory get_current_directory()
    {
        return current_directory;
    }


    // ---------------------- 1 CHANGE DIRECTORY
    /**
     * Changes the current directory to the specified path.
     *
     * @param path the path of the directory to change to
     * @return true if the directory change is successful, false otherwise
     */

    public boolean change_directory(String path)
    {
        // If the given path is the root directory, set the current directory to root
        if (path.equals("/"))
        {
            current_directory = root;
            return true;
        }
        Directory new_directory = root;
        // If the path starts with a "/", remove it for uniform handling
        if (path.startsWith("/"))
            path = path.substring(1);  // Strip the leading slash for uniform handling


        return change_directory_recursive(new_directory, path.split("/"), 0);
    }

    /**
     * Recursively changes the current directory to the directory specified by the given path parts.
     *
     * @param current the current directory being processed in the recursion
     * @param parts   the array of path parts to traverse
     * @param index   the index of the current path part being processed
     * @return true if the directory change is successful, false otherwise
     */
    private boolean change_directory_recursive(Directory current, String[] parts, int index)
    {
        if (index == parts.length)
        {
            // All parts processed successfully
            current_directory = current;
            return true;
        }

        String part = parts[index];
        if (part.isEmpty() || part.equals("."))
        {
            // Skip empty parts and current directory references
            return change_directory_recursive(current, parts, index + 1);
        }
        else if (part.equals(".."))
        {
            // Move up to the parent directory
            if (current.get_parent() != null)
                return change_directory_recursive((Directory) current.get_parent(), parts, index + 1);
            else
                return false;  // No parent to move up to

        }
        else
        {
            // Move down to the specified child directory
            FileSystemElement next = current.get_child(part);
            if (next instanceof Directory)
                return change_directory_recursive((Directory) next, parts, index + 1);
            else
                return false;  // Next part is not a directory

        }
    }


    // -------------------------- 2 LIST DIRECTORY CONTENT

    /**
     * Lists the contents of the current directory recursively.
     */
    public void list_directory_contents()
    {
        System.out.println("Listing contents of " + current_directory.get_name() + ":");
        list_contents("", current_directory);  // Start with no indent for the root level
    }

    /**
     * Recursively lists the contents of the specified directory with the given indentation.
     *
     * @param indent the indentation string to use for visual hierarchy
     * @param dir    the directory whose contents should be listed
     */
    private void list_contents(String indent, Directory dir)
    {
        for (FileSystemElement child : dir.get_children())
        {
            if (child instanceof Directory)
                System.out.println(indent + "* " + child.get_name() + "/");

            else if (child instanceof File)
                System.out.println(indent + child.get_name());

        }
    }

    // ---------------------------- 3 CREATE FILE OR DIRECTORY
    /**
     * Creates a file or directory at the specified path.
     *
     * @param type the type of element to create ("f" for file, "d" for directory)
     * @param path the path where the element should be created
     */
    public void create_file_or_directory(String type, String path)
    {
        String[] parts = path.split("/");
        if (parts.length == 1)
            // Base case: only one part, create here in currentDirectory
            create_file_or_directory(type, parts[0], current_directory);
        else
        {
            // Recursive case: navigate or create nested directories
            String next_part = parts[0];
            String remaining_path = path.substring(next_part.length() + 1);
            Directory next_dir = (Directory) current_directory.get_child(next_part);
            if (next_dir == null)
            {
                // Directory does not exist, create it in currentDirectory
                next_dir = new Directory(next_part, current_directory);
                current_directory.add_child(next_dir);
                System.out.println("Directory created: " + next_part + "/");
            }
            // Recurse into the next directory
            create_file_or_directory(type, remaining_path);
        }
    }

    /**
     * Creates a file or directory with the specified name in the given directory.
     *
     * @param type      the type of element to create ("f" for file, "d" for directory)
     * @param name      the name of the file or directory to create
     * @param directory the directory in which to create the file or directory
     */
    private void create_file_or_directory(String type, String name, Directory directory)
    {
        FileSystemElement existingElement = directory.get_child(name);
        if (existingElement != null)
        {
            System.out.println("Error: A file or directory with the name '" + name + "' already exists.");
            return;
        }
        if (type.equals("f"))
        {
            File new_file = new File(name, directory);
            directory.add_child(new_file);
            System.out.println("File created: " + name);
        }
        else if (type.equals("d"))
        {
            Directory new_directory = new Directory(name, directory);
            directory.add_child(new_directory);
            System.out.println("Directory created: " + name + "/");
        }
        else
            System.out.println("Error: Invalid type specified. Use 'f' for file or 'd' for directory.");

    }




    // ------------------------- 5 MOVE
    /**
     * Finds the directory located at the specified path.
     *
     * @param path the path of the directory to find
     * @return the directory located at the specified path, or null if not found
     */
    public Directory find_directory_by_path(String path)
    {
        return find_directory_recursive(root, path.split("/"), 0);
    }

    /**
     * Recursively finds the directory located at the specified path parts.
     *
     * @param current the current directory being processed in the recursion
     * @param parts   the array of path parts to traverse
     * @param index   the index of the current path part being processed
     * @return the directory located at the specified path parts, or null if not found
     */
    private Directory find_directory_recursive(Directory current, String[] parts, int index)
    {
        if (index >= parts.length || current == null)
            return current;
        if (parts[index].isEmpty() || parts[index].equals("."))

            return find_directory_recursive(current, parts, index + 1);

        else if (parts[index].equals(".."))
            return find_directory_recursive((Directory) current.get_parent(), parts, index + 1);
        else
        {
            FileSystemElement next = current.get_child(parts[index]);
            if (next instanceof Directory)
                return find_directory_recursive((Directory) next, parts, index + 1);

            return null;
        }
    }

    /**
     * Moves a file or directory from the current directory to the specified target path.
     *
     * @param source_name  the name of the file or directory to move
     * @param target_path  the path of the target directory where the file or directory should be moved
     * @return a message indicating the result of the move operation
     */
    // Moving a file or directory from one path to another
    public String move_file_or_directory(String source_name, String target_path)
    {
        // Locate the source directory and the element to be moved
        FileSystemElement move = current_directory.get_child(source_name);
        if (move == null)
            return "Source file or directory not found.";

        // Locate the target directory
        Directory target_directory = find_directory_by_path(target_path);
        if (target_directory == null)
            return "Target directory not found.";

        // Perform the move if the target directory does not already contain an element with the same name
        if (target_directory.get_child(source_name) != null)
            return "An element with the same name already exists in the target directory.";


        // Remove from current and add to target
        get_current_directory().remove_child(source_name);
        target_directory.add_child(move);
        move.set_parent(target_directory);

        return "File moved: " + source_name + " to " + target_path;
    }



    // -------------------------- 6 SEARCH
    /**
     * Searches for a file or directory by name starting from the root directory.
     *
     * @param name The name of the file or directory to search for.
     * @return A string message indicating whether the file or directory was found, along with its path if found.
     */
    public String search_file_or_directory(String name)
    {
        FileSystemElement found = search_recursive(root, name);
        if (found != null)
            return "Found: " + get_path(found);

        return "File or directory not found.";
    }

    /**
     * Recursively searches for a file or directory within the specified directory.
     *
     * @param dir The directory to start the search from.
     * @param name The name of the file or directory to search for.
     * @return The FileSystemElement if found, or null if not found.
     */
    private FileSystemElement search_recursive(Directory dir, String name)
    {
        if (dir.get_name().equals(name))
            return dir;

        for (FileSystemElement child : dir.get_children())
        {
            if (child.get_name().equals(name))
                return child;

            if (child instanceof Directory)
            {
                FileSystemElement result = search_recursive((Directory) child, name);
                if (result != null)
                    return result;

            }
        }
        return null;
    }

    /**
     * Constructs the full path for the specified file system element from its parent directory up to the root.
     *
     * @param element The file system element for which to construct the path.
     * @return The full path of the file system element.
     */
    private String get_path(FileSystemElement element)
    {
        StringBuilder path = new StringBuilder();
        while (element != null)
        {
            path.insert(0, element.get_name() + (path.length() > 0 ? "/" : ""));
            element = element.get_parent();
        }
        return path.toString();
    }



    // ----------------- 7 PRINT TREE
    /**
     * Prints the contents of the current directory along with the path from the root directory to the current directory, the contents are printed with indentation to represent the directory hierarchy.
     */
    public void print_directory_contents()
    {
        System.out.println("Path to current directory from root:");
        List<String> path = new ArrayList<>();
        // Build path starting from the parent of the current directory
        if (current_directory.get_parent() instanceof Directory)
            build_path((Directory) current_directory.get_parent(), path);

        Collections.reverse(path);  // Reverse to print from root to current
        print_path(path);
        print_current_directory_contents(current_directory, path.size() * 2);  // Print contents with indentation
    }

    /**
     * Recursively constructs the path from the root directory to the specified directory.
     *
     * @param dir  the directory for which to construct the path
     * @param path the list to which the path elements will be added
     */
    private void build_path(Directory dir, List<String> path)
    {
        if (dir != null)
        {
            path.add(dir.get_name());
            if (dir.get_parent() instanceof Directory)
                build_path((Directory) dir.get_parent(), path);

        }
    }
    /**
     * Prints the path elements with indentation.
     *
     * @param path the list of path elements to print
     */
    private void print_path(List<String> path)
    {
        String indent = "";
        for (String name : path)
        {
            System.out.println(indent + "* " + name + "/");
            indent += "  ";
        }
    }
    /**
     * Prints the contents of the current directory along with the specified indentation level, each directory is marked with an asterisk (*) and a slash (/) after its name, and files are printed without a slash, directories are printed recursively with additional indentation for hierarchy representation.
     *
     * @param dir         the current directory whose contents to print
     * @param indentLevel the level of indentation for printing
     */
    private void print_current_directory_contents(Directory dir, int indentLevel)
    {
        String indent = String.join("", Collections.nCopies(indentLevel, " "));
        System.out.println(indent + "* " + dir.get_name() + "/ (Current Directory)");
        for (FileSystemElement child : dir.get_children())
        {
            if (child instanceof Directory)
                System.out.println(indent + "  " + "* " + child.get_name() + "/");
            else
                System.out.println(indent + "  " + child.get_name());

        }
    }

// ------------------- 8 SORT CONTENTS BY DATE CREATED
    /**
     * Sorts the contents of the current directory and its subdirectories recursively based on creation date, this method first sorts the immediate children of the current directory and then recursively sorts the contents of each directory.
     */
public void sort_contents_by_date()
{
    // Sort the current directory's immediate children
    Collections.sort(current_directory.get_children(), Comparator.comparing(FileSystemElement::get_date));
    // Recursively sort the contents of each directory
    sort_contents_recursive(current_directory);
}

    /**
     * Recursively sorts the contents of the specified directory and its subdirectories based on creation date.
     *
     * @param dir the directory whose contents to sort recursively
     */
    private void sort_contents_recursive(Directory dir)
    {
        for (FileSystemElement child : new ArrayList<>(dir.get_children()))
        {
            if (child instanceof Directory)
            {
                Directory child_dir = (Directory) child;
                Collections.sort(child_dir.get_children(), Comparator.comparing(FileSystemElement::get_date));
                sort_contents_recursive(child_dir);
            }
        }
    }
    /**
     * Prints the sorted contents of the current directory and its subdirectories based on creation date, the contents are printed along with their creation dates.
     */
    public void print_sorted_contents()
    {
        sort_contents_by_date(); // Ensure contents are sorted before printing
        System.out.println("Sorted contents of " + get_path_from_root(current_directory) + ":");
        for (FileSystemElement child : current_directory.get_children())
        {
            if (child instanceof Directory)
                System.out.println("* " + child.get_name() + "/ (" + child.get_date().toString() + ")");
            else
                System.out.println(child.get_name() + " (" + child.get_date().toString() + ")");

        }
    }

    /**
     * Constructs the path from the root directory to the specified directory.
     *
     * @param dir the directory for which to construct the path
     * @return the path from the root directory to the specified directory
     */
    private String get_path_from_root(Directory dir)
    {
        if (dir.get_parent() == null)
            return dir.get_name();
        else
            return get_path_from_root((Directory) dir.get_parent()) + "/" + dir.get_name();

    }



}






