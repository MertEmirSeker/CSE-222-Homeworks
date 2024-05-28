import java.util.*;

/**
 * SocialNetworkGraph represents a social network as a graph with people as nodes and friendships as edges.
 */
public class SocialNetworkGraph
{
    private Map<String, Person> people = new HashMap<>();
    private Map<Person, List<Person>> friendships = new HashMap<>();

    /**
     * Adds a person to the social network.
     *
     * @param name    the name of the person
     * @param age     the age of the person
     * @param hobbies the hobbies of the person
     */
    public void addPerson(String name, int age, List<String> hobbies)
    {
        if (people.containsKey(name))
        {
            System.out.println("Person with this name already exists.");
            return;
        }
        Person person = new Person(name, age, hobbies);
        people.put(name, person);
        friendships.put(person, new ArrayList<>());
        System.out.println("Person added: " + person + " (Timestamp: " + person.getTimestamp() + ")");
    }

    /**
     * Removes a person from the social network.
     *
     * @param name the name of the person to remove
     */
    public void removePerson(String name)
    {
        Person person = getPersonByName(name);
        if (person == null) return;

        people.remove(name);
        friendships.remove(person);

        for (List<Person> friends : friendships.values())
        {
            friends.remove(person);
        }

        System.out.println("Person removed: " + name);
    }

    /**
     * Adds a friendship between two people in the social network.
     *
     * @param name1 the name of the first person
     * @param name2 the name of the second person
     */
    public void addFriendship(String name1, String name2)
    {
        Person person1 = getPersonByName(name1);
        Person person2 = getPersonByName(name2);
        if (person1 == null || person2 == null) return;

        friendships.get(person1).add(person2);
        friendships.get(person2).add(person1);
        System.out.println("Friendship added between " + person1.getName() + " and " + person2.getName());
    }

    /**
     * Removes a friendship between two people in the social network.
     *
     * @param name1 the name of the first person
     * @param name2 the name of the second person
     */
    public void removeFriendship(String name1, String name2)
    {
        Person person1 = getPersonByName(name1);
        Person person2 = getPersonByName(name2);
        if (person1 == null || person2 == null) return;

        friendships.get(person1).remove(person2);
        friendships.get(person2).remove(person1);
        System.out.println("Friendship removed between " + person1.getName() + " and " + person2.getName());
    }

    /**
     * Finds and prints the shortest path between two people in the social network using BFS.
     *
     * @param startName the name of the starting person
     * @param endName   the name of the ending person
     */
    public void findShortestPath(String startName, String endName)
    {
        Person start = getPersonByName(startName);
        Person end = getPersonByName(endName);
        if (start == null || end == null) return;

        Queue<Person> queue = new LinkedList<>();
        Map<Person, Person> prev = new HashMap<>();
        Set<Person> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty())
        {
            Person current = queue.poll();

            if (current.equals(end))
            {
                printPath(start, end, prev);
                return;
            }

            for (Person neighbor : friendships.get(current))
            {
                if (!visited.contains(neighbor))
                {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                }
            }
        }

        System.out.println("No path found between " + startName + " and " + endName);
    }

    /**
     * Prints the shortest path from the start person to the end person.
     *
     * @param start the starting person
     * @param end   the ending person
     * @param prev  the map of previous persons in the path
     */
    private void printPath(Person start, Person end, Map<Person, Person> prev)
    {
        List<Person> path = new ArrayList<>();
        for (Person at = end; at != null; at = prev.get(at))
        {
            path.add(at);
        }
        Collections.reverse(path);
        StringBuilder pathString = new StringBuilder();
        for (int i = 0; i < path.size(); i++)
        {
            pathString.append(path.get(i).getName());
            if (i < path.size() - 1)
            {
                pathString.append(" -> ");
            }
        }
        System.out.println("Shortest path: " + pathString);
    }

    /**
     * Counts and prints the number of clusters (connected components) in the social network using BFS.
     */
    public void countClusters()
    {
        Set<Person> visited = new HashSet<>();
        int clusterCount = 0;

        for (Person person : people.values())
        {
            if (!visited.contains(person))
            {
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster);
                clusterCount++;
                System.out.println("Cluster " + clusterCount + ":");
                for (Person p : cluster)
                {
                    System.out.println(p.getName());
                }
            }
        }

        System.out.println("Number of clusters found: " + clusterCount);
    }

    /**
     * Performs a BFS to find all people in the same cluster as the start person.
     *
     * @param start   the starting person
     * @param visited the set of visited people
     * @param cluster the list of people in the current cluster
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster)
    {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty())
        {
            Person current = queue.poll();
            cluster.add(current);

            for (Person neighbor : friendships.get(current))
            {
                if (!visited.contains(neighbor))
                {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    /**
     * Suggests friends for a person based on the number of mutual friends and common hobbies.
     *
     * @param name           the name of the person for whom to suggest friends
     * @param maxSuggestions the maximum number of suggestions to make
     */
    public void suggestFriends(String name, int maxSuggestions)
    {
        Person person = getPersonByName(name);
        if (person == null) return;

        Map<Person, Double> scores = new HashMap<>();
        Map<Person, Integer> mutualFriendsMap = new HashMap<>();
        Map<Person, Integer> commonHobbiesMap = new HashMap<>();
        List<Person> directFriends = friendships.get(person);

        for (Person p : people.values())
        {
            if (p.equals(person) || directFriends.contains(p))
                continue;

            int mutualFriends = 0;
            int commonHobbies = 0;

            for (Person friend : directFriends)
            {
                if (friendships.get(friend).contains(p))
                {
                    mutualFriends++;
                }
            }

            for (String hobby : person.getHobbies())
            {
                if (p.getHobbies().contains(hobby))
                {
                    commonHobbies++;
                }
            }

            double score = mutualFriends + 0.5 * commonHobbies;
            scores.put(p, score);
            mutualFriendsMap.put(p, mutualFriends);
            commonHobbiesMap.put(p, commonHobbies);
        }

        System.out.println("Suggested friends for " + name + ":");
        scores.entrySet().stream().sorted(Map.Entry.<Person, Double>comparingByValue().reversed()).limit(maxSuggestions).forEach(entry->{
            Person p = entry.getKey();
            double score = entry.getValue();
            int mutualFriends = mutualFriendsMap.get(p);
            int commonHobbies = commonHobbiesMap.get(p);
            System.out.println(p.getName() + " (Score: " + score + ", " + mutualFriends + " mutual friends, " + commonHobbies + " common hobbies)");
        });
    }

    /**
     * Helper method to get a person by name and handle null checks.
     *
     * @param name the name of the person
     * @return the Person object if found, otherwise null
     */
    private Person getPersonByName(String name)
    {
        Person person = people.get(name);
        if (person == null)
        {
            System.out.println("Person not found: " + name);
        }
        return person;
    }
}
