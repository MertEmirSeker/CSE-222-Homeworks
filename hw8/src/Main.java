import java.util.*;

/**
 * Main class to demonstrate the functionality of the SocialNetworkGraph.
 */
public class Main
{
    /**
     * Main method to run the social network analysis program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        SocialNetworkGraph network = new SocialNetworkGraph();
        Scanner scanner = new Scanner(System.in);

        // Adding sample people to the network
        network.addPerson("John Doe", 25, Arrays.asList("reading", "hiking", "cooking"));
        network.addPerson("Jane Smith", 22, Arrays.asList("swimming", "cooking"));
        network.addPerson("Alice Johnson", 27, Arrays.asList("hiking", "painting"));
        network.addPerson("Bob Brown", 30, Arrays.asList("reading", "swimming"));
        network.addPerson("Emily Davis", 28, Arrays.asList("running", "swimming"));
        network.addPerson("Frank Wilson", 26, Arrays.asList("reading", "hiking"));

        // Adding friendships for demonstration
        network.addFriendship("John Doe", "Jane Smith");
        network.addFriendship("John Doe", "Alice Johnson");
        network.addFriendship("Jane Smith", "Bob Brown");
        network.addFriendship("Emily Davis", "Frank Wilson");

        // Finding shortest path for demonstration
        network.findShortestPath("John Doe", "Bob Brown");

        // Counting clusters for demonstration
        network.countClusters();

        // User interaction loop
        while (true)
        {
            System.out.println("===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            if (!scanner.hasNextInt())
            {
                System.out.println("Invalid option. Please enter a number between 1 and 8.");
                scanner.next(); // Consume invalid input
                continue;
            }

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option)
            {
                case 1:
                    System.out.print("Enter name and surname: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    if (!scanner.hasNextInt())
                    {
                        System.out.println("Invalid age. Please enter a valid integer.");
                        scanner.next(); // Consume invalid input
                        break;
                    }
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter hobbies (comma-separated): ");
                    String hobbiesInput = scanner.nextLine();
                    List<String> hobbies = Arrays.asList(hobbiesInput.split(","));
                    network.addPerson(name, age, hobbies);
                    break;
                case 2:
                    System.out.print("Enter name and surname: ");
                    name = scanner.nextLine();
                    network.removePerson(name);
                    break;
                case 3:
                    System.out.print("Enter first person’s name and surname: ");
                    String name1 = scanner.nextLine();
                    System.out.print("Enter second person’s name and surname: ");
                    String name2 = scanner.nextLine();
                    network.addFriendship(name1, name2);
                    break;
                case 4:
                    System.out.print("Enter first person’s name and surname: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter second person’s name and surname: ");
                    name2 = scanner.nextLine();
                    network.removeFriendship(name1, name2);
                    break;
                case 5:
                    System.out.print("Enter start person’s name and surname: ");
                    String startName = scanner.nextLine();
                    System.out.print("Enter end person’s name and surname: ");
                    String endName = scanner.nextLine();
                    network.findShortestPath(startName, endName);
                    break;
                case 6:
                    System.out.print("Enter person’s name and surname: ");
                    name = scanner.nextLine();
                    System.out.print("Enter maximum number of friends to suggest: ");
                    if (!scanner.hasNextInt())
                    {
                        System.out.println("Invalid number. Please enter a valid integer.");
                        scanner.next(); // Consume invalid input
                        break;
                    }
                    int maxSuggestions = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    network.suggestFriends(name, maxSuggestions);
                    break;
                case 7:
                    network.countClusters();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
