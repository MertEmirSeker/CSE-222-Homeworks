public class QuickSort extends SortAlgorithm
{

	public QuickSort(int input_array[])
    {
		super(input_array);
	}

    private int partition(int low, int high)
    {
        int pivot = arr[high]; // Taking the last element as pivot
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++)
        {
            comparison_counter++; // Incrementing comparison counter
            if (arr[j] < pivot)
            {
                i++; // Increment index of smaller element
                swap(i, j); // Swapping arr[i] and arr[j]
            }
        }
        swap(i + 1, high); // Swap arr[i+1] and arr[high] (or pivot)
        return i + 1; // Returning the partitioning index
    }

    private void sort(int low, int high)
    {
        if (low < high)
        {
            int pi = partition(low, high); // Partitioning index
            sort(low, pi - 1); // Recursively sort elements before partition
            sort(pi + 1, high); // Recursively sort elements after partition
        }
    }

    @Override
    public void sort()
    {
        sort(0, arr.length - 1);
    }


    @Override
    public void print()
    {
    	System.out.print("Quick Sort\t=>\t");
    	super.print();
    }
}
