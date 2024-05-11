public class MergeSort extends SortAlgorithm
{
	
	public MergeSort(int input_array[])
    {
		super(input_array);
	}

    private void merge(int[] temp_array, int left_start, int right_end)
    {
        int left_end = (left_start + right_end) / 2;
        int right_start = left_end + 1;
        int size = right_end - left_start + 1;

        int left = left_start;
        int right = right_start;
        int index = left_start;

        while (left <= left_end && right <= right_end)
        {
            comparison_counter++; // Incrementing comparison counter
            if (arr[left] <= arr[right])
            {
                temp_array[index] = arr[left];
                left++;
            }
            else
            {
                temp_array[index] = arr[right];
                right++;
            }
            index++;
        }

        System.arraycopy(arr, left, temp_array, index, left_end - left + 1);
        System.arraycopy(arr, right, temp_array, index, right_end - right + 1);
        System.arraycopy(temp_array, left_start, arr, left_start, size);


      /*
      for (int i = left; i <= left_end; i++)
            temp_array[index++] = arr[i];

        for (int i = right; i <= right_end; i++)
            temp_array[index++] = arr[i];

        for (int i = left_start; i <= right_end; i++)
            arr[i] = temp_array[i];

      */
    }

    private void sort(int[] temp_array, int left_start, int right_end)
    {
        if (left_start >= right_end)
            return; // Base case: the array is already divided into a single element

        int middle = (left_start + right_end) / 2;
        sort(temp_array, left_start, middle); // Sort the left half
        sort(temp_array, middle + 1, right_end); // Sort the right half
        merge(temp_array, left_start, right_end); // Merge the sorted halves
    }

    @Override
    public void sort()
    {
        int[] temp_array = new int[arr.length];
        sort(temp_array, 0, arr.length - 1);
    }
    
    @Override
    public void print()
    {
    	System.out.print("Merge Sort\t=>\t");
    	super.print();
    }
}
