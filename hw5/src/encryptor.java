import java.util.Map;

public class encryptor
{
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";

	// constructor for encryptor
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text)
	{
		this.map = _map;
		this.key = _key.toUpperCase();
		this.plain_text = text.toUpperCase();
	}
	
	public void encrypt()
	{
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}
	
	private void generate_keystream()
	{
		// Iterate through each character of the plain text
		for (int i = 0, j = 0; i < plain_text.length(); i++, j++)
		{
			// Check if j has reached the end of the key
			if (j == key.length())
				j = 0;  // Reset j to 0 to repeat the key

			// Append the current character from the key to the keystream
			keystream += key.charAt(j);
		}
	}
	
	private void generate_cipher_text()
	{
		for (int i = 0; i < plain_text.length(); i++)
		{
			char char_plain = plain_text.charAt(i);	// Get the character at index i from the plain text
			char char_keystream = keystream.charAt(i);	// Get the character at index i from the keystream
			cipher_text += map.get(char_keystream).get(char_plain); // Find the mapping for the character from the keystream and the character from the plain text, then append the result to the cipher text

		}
	}

	public String get_keystream()
	{
		return keystream;  // returns the keystream
	}
	
	public String get_cipher_text()
	{
		return cipher_text;  // returns the cipher_text

	}
}
