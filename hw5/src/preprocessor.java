public class preprocessor
{
	private String initial_string;
	private String preprocessed_string;
		
	public preprocessor(String str)
	{
		this.initial_string = str;
	}

	public void preprocess()
	{
		// do not edit this method
		capitalize();
		clean();
	}
	
	private void capitalize()
	{
		// Initialize an empty string to store the capitalized characters
		String temp_string = "";
		for (int i = 0; i < initial_string.length(); i++)
		{
			// Get the character at index i
			char c = initial_string.charAt(i);
			// Skip 'ı' and 'İ'
			if (c == 'ı' || c == 'İ')
				continue;
			else
				temp_string += Character.toUpperCase(c);	// Convert the character to uppercase and append it to the temporary string

		}
		this.initial_string = temp_string;	// Update the initial string with the capitalized version
	}

	private void clean()
	{
		preprocessed_string = "";
		int contains_english = 0;  // Check if the input contains any English characters

		for (int i = 0; i < initial_string.length(); i++)
		{
			char c = initial_string.charAt(i);
			// Accept only characters between 'A' and 'Z'
			if (c >= 'A' && c <= 'Z')
			{
				preprocessed_string += c;
				contains_english = 1;  // Set to 1 if an english character is found
			}
		}

		if (contains_english == 0)
		{
			// Exit the program if no english characters are found
			System.out.println("Given input is not proper. Please try again.");
			System.exit(0);
		}

	}

	public String get_preprocessed_string()
	{
		return preprocessed_string; // returns the preprocessed_string
	}
}