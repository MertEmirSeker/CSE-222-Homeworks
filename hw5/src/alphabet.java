import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet
{
	private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();
	
	public alphabet()
	{
		// do not edit this method
		fill_english_alphabet();
		fill_map();
	}
	
	private void fill_english_alphabet()
	{
		// do not edit this method
		for(char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray())
		    english_alphabet.add(c);

	}
	
	private void fill_map()
	{
		// You must use the "english_alphabet" variable in this method, to fill the "map" variable.
		// You can define 1 or 2 iterators to iterate through the set items.

		// Initialize an iterator for the alphabet set
		Iterator<Character> main_iterator = english_alphabet.iterator();

		// Iterate through each character in the English alphabet
		while (main_iterator.hasNext())
		{
			char key = main_iterator.next();  // Get the row character

			Map<Character, Character> row_map = new HashMap<>();
			Iterator<Character> row_iterator = english_alphabet.iterator();

			// Shift to the right position
			for (int i = 0; i < key - 'A'; i++)
				row_iterator.next();


			// Fill the row map with cipher values
			for (char column = 'A'; column <= 'Z'; column++)
			{
				if (!row_iterator.hasNext())
					// Reset the iterator when end of alphabet is reached
					row_iterator = english_alphabet.iterator();

				row_map.put(column, row_iterator.next());
			}

			// Assign the row map to the main map with key as the row header
			map.put(key, row_map);
		}
	}

	public void print_map()
	{
		// do not edit this method
		System.out.println("*** Viegenere Cipher ***\n\n");
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for(Character k: map.keySet())
		{
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");
		
	}

	public Map get_map()
	{
		return map;	// Returns the map
	}
}