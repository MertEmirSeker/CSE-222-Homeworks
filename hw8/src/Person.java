import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Represents a person in the social network.
 */
public class Person
{
    private String name;
    private int age;
    private List<String> hobbies;
    private Date timestamp;

    /**
     * Constructs a new Person with the specified name, age, and hobbies.
     * The timestamp is set to the current date and time.
     *
     * @param name    the name of the person
     * @param age     the age of the person
     * @param hobbies the list of hobbies of the person
     */
    public Person(String name, int age, List<String> hobbies)
    {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        this.timestamp = new Date();
    }

    /**
     * Returns a string representation of the person.
     *
     * @return a string containing the name, age, and hobbies of the person
     */
    @Override
    public String toString()
    {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ")";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two persons are considered equal if their names are the same.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person)o;
        return name.equals(person.name);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the person's name.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }

    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the new name of the person
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the age of the person.
     *
     * @return the age of the person
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Sets the age of the person.
     *
     * @param age the new age of the person
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Gets the list of hobbies of the person.
     *
     * @return the list of hobbies
     */
    public List<String> getHobbies()
    {
        return hobbies;
    }

    /**
     * Sets the list of hobbies of the person.
     *
     * @param hobbies the new list of hobbies
     */
    public void setHobbies(List<String> hobbies)
    {
        this.hobbies = new ArrayList<>(hobbies);
    }

    /**
     * Gets the timestamp of when the person was created.
     *
     * @return the timestamp of the person
     */
    public Date getTimestamp()
    {
        return timestamp;
    }

    /**
     * Sets the timestamp of the person.
     *
     * @param timestamp the new timestamp
     */
    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }
}
