/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        return mergeSortHelper(list,  comparator);
	}

	private List<T> mergeSortHelper(List<T> list ,Comparator<T> comparator) {

		int size = list.size();

		//when the list has more than 2 elements
		if(size >= 2) {

			List<T> leftList = mergeSortHelper(new ArrayList<T>(list.subList(0, size/2)), comparator);
			List<T> rightList = mergeSortHelper(new ArrayList<T>(list.subList(size/2, size)), comparator);

			return merge(leftList, rightList, comparator);
		}

		return list;
	}

	private List<T> merge(List<T> leftList, List<T> rightList, Comparator<T> comparator){

		List<T> result = new ArrayList<T>();

		int leftListCursor = 0;
		int rightListCursor = 0;

		while(leftListCursor < leftList.size() && rightListCursor < rightList.size()) {

			if(comparator.compare(leftList.get(leftListCursor), rightList.get(rightListCursor)) < 0) {
				result.add(leftList.get(leftListCursor));
				leftListCursor++;
			}else {
				result.add(rightList.get(rightListCursor));
				rightListCursor++;
			}
		}

		//remaining

		while(leftListCursor < leftList.size()) {
			result.add(leftList.get(leftListCursor));
			leftListCursor++;
		}

		while(rightListCursor < rightList.size()) {
			result.add(rightList.get(rightListCursor));
			rightListCursor++;
		}

		return result;

	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
		PriorityQueue<T> heap = new PriorityQueue<T>(list.size(), comparator);

		for(T element : list) {
			heap.add(element);
		}

		list.clear();

		while(!heap.isEmpty()) {
			list.add(heap.poll());
		}

	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {

        // FILL THIS IN!

		heapSort(list, comparator);
		List<T> result = new ArrayList<T>();


		//System.out.println(k + " largest numbers");
		for(int i = list.size()-k;  i < list.size(); i++) {
			//System.out.println(list.get(i));
			result.add(list.get(i));
		}

        return result;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
