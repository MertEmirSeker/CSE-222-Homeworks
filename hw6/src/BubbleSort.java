public class BubbleSort extends SortAlgorithm
{

	public BubbleSort(int input_array[])
    {
		super(input_array);
	}
	
    @Override
    public void sort()
    {
        int flag;
        for (int i = 0; i < arr.length - 1; i++)
        {
            flag = 0;
            for (int j = 0; j < arr.length - 1 - i; j++)
            {
                comparison_counter++;  // Counting every comparison of two elements
                if (arr[j] > arr[j + 1])
                {
                    swap(j, j + 1);
                    flag = 1;  // Mark swap has occurred
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (flag == 0)
                break;
        }
    }
    
    @Override
    public void print()
    {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}
