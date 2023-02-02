
public class insertionMergeSort {
	
	private static long keyComparsion = 0;
	private static long sum = 0;

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		//int[] numbers = {1,2,3,4,5};
		//int[] duplicate = {1,2,3,4,5};
		
		
		
		int[] numbers = new int[300000];
		int[] duplicate = new int[300000];
		for(int i = 0; i < numbers.length; i++)
		{
			int number1 = (int)(Math.random()*100000);
			numbers[i] = number1;
			duplicate[i] = number1;
		}
		
		//k is the number of thresholds, g is the number of times program is ran 
		for(int k = 1; k <= 100; k++)
		{
			for(int g = 1; g <= 1000;g++) //Running each threshold 40 times with different array
			{
				int[] numbers1 = new int[300000];
				for(int p = 0; p < numbers1.length;p++)
				{
					int number3 = (int)(Math.random()*100000);
					numbers[p]= number3;
				}
				long start = System.currentTimeMillis();
				insertionMerge(0, numbers.length-1, numbers, k);
				long end = System.currentTimeMillis();
				long time = end - start;
				sum += time;
				//long sum = 0;
				//sum = sum + time;
				/*for(int l = 0; l < numbers.length; l++)
				{
					int dupe = duplicate[l];
					numbers[l] = dupe;
				}*/
			}
			System.out.println("Sort time taken on average: " + sum/1000 + " miliseconds for threshold: " + k + 
					"\nNumber of key comparisons: " + getKeycomparsion()/1000);
			sum = 0;
		}
		
	}
	
    public static void printArray(int[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
	
	public static void insertionMerge(int n, int m, int[] slot, int threshold)
	{
		int mid = (n+m)/2;
		if(m - n  + 1> threshold)
		{
			//keyComparsion++;
			insertionMerge(n, mid, slot, threshold);
			insertionMerge(mid + 1, m, slot, threshold);
			merge(n, m, mid, slot);
		}
		else 
		{
			insertionSort(n, m, slot);
		}
	}
	
	static void insertionSort(int n, int m, int[] slot)
	{
		for(int i = n; i < m; i++)
		{
			int temp = slot[i+1];
			int j = i + 1;
			while(j > n && slot[j-1] > temp) 
			{
				keyComparsion++;
				slot[j] = slot[j - 1];
				j--;
			}
			slot[j] = temp;
			if(j > n)
			{
				keyComparsion++;
			}
		}
	}
	public static void merge(int n, int m, int mid, int[] slot) // n is left, m is right
	{
		if(m - n <= 0)
		{
			return;
		}
		
		int leftSide = mid - n + 1; 
		int rightSide = m - mid;
		
		//Creating new Array
		int[] leftArray = new int[leftSide];
		int[] rightArray = new int[rightSide];
		
		//Copy the main array into the left and right array respectively
		for(int i = 0; i < leftSide; i++)
		{
			leftArray[i] = slot[n + i];
		}
		
		for(int j = 0; j < rightSide; j++)
		{
			rightArray[j] = slot[mid + j + 1];
		}
		
		int i = 0;
		int j = 0;
		int k = n;
		
		//Merge both the left and right array		
		while(i < leftSide && j < rightSide)
		{
			keyComparsion++;
			if(leftArray[i] < rightArray[j])
			{
				slot[k] = leftArray[i];
				i++;
			}
			else
			{
				slot[k] = rightArray[j];
				j++;
			}
			k++;
		}
		
		while(i < leftSide)
		{
			slot[k] = leftArray[i];
			i++;
			k++;
		}
		while(j < rightSide)
		{
			slot[k] = rightArray[j];
			j++;
			k++;
		}
	}
	public static long getKeycomparsion()
	{
		long i = keyComparsion;
		keyComparsion = 0;
		return i;
	}
	
	public static void resetKeycomparsion()
	{
		keyComparsion = 0;	
	}

}
