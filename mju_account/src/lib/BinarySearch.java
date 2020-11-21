package lib;

import database.Account;

public class BinarySearch 
{
	public Account binarySearch(int iKey, Account arr[]) 
	{
		QuickSort qs = new QuickSort();
		arr = qs.quickSort(arr, 0, arr.length-1);
        int mid=0;
        int left = 0;
        int right = arr.length - 1;
 
        while (right >= left) {
            mid = (right + left) / 2;
 
            if (iKey == arr[mid].getPrice()) {
                break;
            }
 
            if (iKey < arr[mid].getPrice()) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
 
        }
        return arr[mid];
    }
}
