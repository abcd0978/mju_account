package lib;

import database.Account;

public class QuickSort {
	public int partition(Account[] array,int start, int end) 
	{
		Account pivot = array[(start+end)/2]; 
		while(start<=end)
		{ 
			while(array[start].getPrice()<pivot.getPrice()) 
				start++; 
			while(array[end].getPrice()>pivot.getPrice()) 
				end--; 
			if(start<=end) 
			{ 
				Account tmp = array[start]; 
				array[start]=array[end]; 
				array[end]=tmp;
				start++;
				end--;
			} 
		} 
	return start; 
	} 
	public Account[] quickSort(Account[] array,int start, int end) 
	{ 
		int p = partition(array, start, end); 
		if(start<p-1)
			quickSort(array,start,p-1); 
		if(p<end) 
			quickSort(array,p,end); 
		return array; 
	}
	//////////////////////////////////////////////////////////////////아래는 문자열 정렬
	public int partition_S(Account[] array,int start, int end) 
	{
		Account pivot = array[(start+end)/2]; 
		while(start<=end)
		{ 
			while(array[start].getName().compareTo(pivot.getName())<0) 
				start++; 
			while(array[end].getName().compareTo(pivot.getName())>0)
				end--; 
			if(start<=end) 
			{ 
				Account tmp = array[start]; 
				array[start]=array[end]; 
				array[end]=tmp;
				start++;
				end--;
			} 
		} 
	return start; 
	} 
	public Account[] quickSort_S(Account[] array,int start, int end) 
	{ 
		int p = partition(array, start, end); 
		if(start<p-1)
			quickSort(array,start,p-1); 
		if(p<end) 
			quickSort(array,p,end); 
		return array; 
	}
}
