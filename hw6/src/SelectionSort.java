public class SelectionSort extends SortAlgorithm
{

	public SelectionSort(int input_array[])
    {
		super(input_array);
	}

    @Override
    public void sort()
    {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++)
        {
            // Find the minimum element in unsorted array
            int min_index = i;
            for (int j = i + 1; j < n; j++)
            {
                comparison_counter++; // Increment comparison counter for each comparison
                if (arr[j] < arr[min_index])
                    min_index = j;

            }

            // Swap the found minimum element with the first element
            swap(min_index, i);
        }
    }

    @Override
    public void print()
    {
    	System.out.print("Selection Sort\t=>\t");
    	super.print();
    }
}
