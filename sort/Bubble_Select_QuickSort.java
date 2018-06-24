package com.Data_Structure_Algorithm.sort;

public class Bubble_Select_QuickSort {

	public static void showArr(int[] arr) {
		for (int item : arr) { System.out.print(item + " "); }
		System.out.println();
	}

	/**
	 * 冒泡排序
	 * 
	 * @param arr
	 */
	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; ++i) {
			for (int j = i + 1; j < arr.length; ++j) if (arr[i] > arr[j]) {
					arr[i] ^= arr[j]; 
					arr[j] ^= arr[i]; 
					arr[i] ^= arr[j];
				}
		}
	}

	/**
	 * 选择排序
	 * 
	 * @param arr
	 */
	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; ++i) {
			int temp = i;
			for (int j = i + 1; j < arr.length; ++j) if (arr[temp] > arr[j]) {
					temp = j;
				}
			arr[i] ^= arr[temp];
			arr[temp] ^= arr[i];
			arr[i] ^= arr[temp];
		}
	}

	/**
	 * 快速排序
	 * @param arr
	 * @param low
	 * @param hight
	 */
	public static void quickSort(int[] arr, int low, int hight) {
		if (low >= hight) { return; }
		int i = low, j = hight, base = arr[i];
		while (i < j) {
			while (i < j && arr[j] > base)--j;
			if (i < j) { arr[i] = arr[j]; ++i; }
			while (i < j && arr[i] < base) ++i;
			if (i < j) { arr[j] = arr[i]; --j; }
		}
		arr[i] = base;
		quickSort(arr, low, i - 1);
		quickSort(arr, j + 1, hight);
	}

	public static void main(String[] args) {
		int[] arr = { 4, 9, 2, 0 };
		// bubbleSort(arr);
		// selectSort(arr);
		quickSort(arr, 0, arr.length - 1);
		showArr(arr);
	}

}
