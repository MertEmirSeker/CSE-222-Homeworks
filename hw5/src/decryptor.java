import java.util.Map;
import java.util.Iterator;

public class decryptor
{
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;


	// constructor for decryptor
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text)
	{
		this.map = _map;
		this.key = _key.toUpperCase();
		this.cipher_text = text.toUpperCase();
	}

	public void decrypt()
	{
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}
	
	private void generate_keystream()
	{
		// Iterate through each character of the cipher text
		for (int i = 0, j = 0; i < cipher_text.length(); i++, j++)
		{
			// Check if j has reached the end of the key
			if (j == key.length())
				j = 0;  // Reset j to 0 to repeat the key


			// Append the current character from the key to the keystream
			keystream += key.charAt(j);
		}
	}
	
	private void generate_plain_text()
	{
		// You must use map.get(x).keySet() with an iterator in this method
		for (int i = 0; i < cipher_text.length(); i++)
		{
			char char_cipher = cipher_text.charAt(i);  // The character from the cipher text
			char char_keystream = keystream.charAt(i);  // The corresponding character from the keystream

			// Retrieve the mapping for the keystream character
			Map<Character, Character> row = map.get(char_keystream);

			// Use an iterator over the keySet of the row to find the matching plain text character
			Iterator<Character> iterator = row.keySet().iterator();
			while (iterator.hasNext())
			{
				char plainChar = iterator.next();
				if (row.get(plainChar).equals(char_cipher))
				{
					plain_text += plainChar;  // Append the decrypted character to the plain text
					break;
				}
			}
		}
	}

	public String get_keystream()
	{
		return keystream; // Returns the keystream
	}
	
	public String get_plain_text()
	{
		return plain_text;	// Returns the plain_text
	}
}
