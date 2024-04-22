import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class to demonstrate and interact with the file system management system.
 */
public class Main
{


    // fonksiyonlar düzenlenecek
    // javadoc ve rapor hazırlanacak + yorum satırı
    /**
     * Initializes the file system with predefined directory structure.
     *
     * @return the initialized file system
     */

    private static FileSystem initialize_file_system()
    {
        FileSystem fs = new FileSystem();

        // Creating some directories and files
        Directory home = new Directory("home", fs.get_current_directory());
        fs.get_current_directory().add_child(home);
        Directory user = new Directory("user", home);
        home.add_child(user);
        Directory documents = new Directory("Documents", user);
        user.add_child(documents);

        Directory projects = new Directory("Projects", documents);
        documents.add_child(projects);
        Directory reports = new Directory("Reports", documents);  // Now correctly setting documents as parent
        documents.add_child(reports);  // Add reports to documents, not user
        Directory project1 = new Directory("Project1", projects);
        projects.add_child(project1);
        Directory project2 = new Directory("Project2", projects);
        projects.add_child(project2);


        File file2 = new File("Notes.txt", projects);
        projects.add_child(file2);
        File file3 = new File("Report.docx", projects);
        projects.add_child(file3);


        // Initial directory set to /home/user/Documents
        fs.change_directory("/home/user/Documents");

        return fs;
    }

    /**
     * Main method to run the file system management system.
     *
     * @param args command line arguments (not used)
     */

    public static void main(String[] args)
    {
        FileSystem fs = initialize_file_system();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the File & Folder Directory Tree System !!!");

        while(true)
        {
            System.out.println();
            System.out.println("===== File System Management Menu =====");
            System.out.println("1. Change directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file/directory");
            System.out.println("4. Delete file/directory");
            System.out.println("5. Move file/directory");
            System.out.println("6. Search file/directory");
            System.out.println("7. Print directory tree");
            System.out.println("8. Sort contents by date created");
            System.out.println("9. Exit");

            int choice = 0;

            while (true)
            {
                System.out.print("Please select an option: ");
                try
                {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }

            switch (choice)
            {
                case 1:
                    System.out.println();
                    System.out.println("Current directory: " + fs.get_current_path());
                    System.out.print("Enter new directory path: ");
                    String path = scanner.nextLine();
                    if (fs.change_directory(path))
                        System.out.println("Directory changed to: " + fs.get_current_path());
                    else
                        System.out.println("Failed to change directory.");

                    break;

                case 2:
                    System.out.println();
                    fs.list_directory_contents();

                    break;

                case 3:
                    System.out.println();
                    System.out.println("Current directory: " + fs.get_current_path());
                    System.out.print("Create file or directory (f/d): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter name for new " + (type.equals("f") ? "file" : "directory") + ": ");
                    String name = scanner.nextLine();
                    fs.create_file_or_directory(type, name);

                    break;

                case 4:
                    System.out.println("Current directory: " + fs.get_current_path());
                    fs.list_directory_contents();

                    System.out.print("Enter the name of the file/directory to delete: ");
                    String nameToDelete = scanner.nextLine();
                    boolean remove_result = fs.get_current_directory().remove_child(nameToDelete);  // Metodun doğru çağrıldığı yer
                    System.out.println(remove_result ? "Deletion successful." : "No such file or directory.");  // Sonuca göre uygun mesaj
                    break;
                case 5:
                    System.out.println("Current directory: " + fs.get_current_path());
                    fs.list_directory_contents();
                    System.out.print("Enter the name of the file/directory to move: ");
                    String sourceName = scanner.nextLine();
                    System.out.print("Enter new directory path: ");
                    String targetPath = scanner.nextLine();
                    String result_move = fs.move_file_or_directory(sourceName, targetPath);
                    System.out.println(result_move);
                    break;
                case 6:
                    System.out.print("Search query: ");
                    String query = scanner.nextLine();
                    System.out.println("Searching from root...");
                    String result = fs.search_file_or_directory(query);
                    System.out.print("Found: ");
                    System.out.println(result.startsWith("File") ? "Found: " + result : result);
                    break;
                case 7:
                    fs.print_directory_contents();
                    break;
                case 8:
                    if (fs.get_current_directory() != null)
                        fs.print_sorted_contents();
                    else
                        System.out.println("No current directory selected.");

                    break;
                case 9:
                    System.out.println("Exiting System Management System. Goodbye!!!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }


}
