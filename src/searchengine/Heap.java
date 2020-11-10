package searchengine;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Priority Queue for Max heap and Heap Sorting algorithm class
 * @author Emmanuel Gamboa
 *
 */
public class Heap {

	private int heapSize;
	
	/*
	 * Default Constructor
	 * Sets heapSize equal to zero
	 */
	public Heap() {
		this.heapSize = 0;
	}

	/*
	 * Maintans the Max Heap property of a specific index in the array
	 * @param arr the supplied array
	 * @param index the index within the array to max-heap
	 */
	public void maxHeapify(ArrayList<Search> arr, int index) {
		int largest = 0;
		int leftChild = getLeftChild(index) ;
		int rightChild = getRightChild(index);
		if(leftChild < this.heapSize && arr.get(leftChild).getPageRank() > arr.get(index).getPageRank()) {
			largest = leftChild;
		}
		else {
			largest = index;
		}
		if(rightChild < this.heapSize && arr.get(rightChild).getPageRank() > arr.get(largest).getPageRank()) {
			largest = rightChild;
		}
		if(largest != index) {
			Collections.swap(arr, index, largest);
			maxHeapify(arr, largest);
		}
	}

	/*
	 * Converts entire array into a max heap
	 * @param arr the supplied array
	 */
	public void buildMaxHeapify(ArrayList<Search> arr) {
		this.heapSize = arr.size();
		for(int i = arr.size()/2; i >= 0; i--) {
			maxHeapify(arr, i);
		}
	}

	/*
	 * Sorts the array using the max heap property
	 * @param arr the supplied array
	 */
	public void heapSort(ArrayList<Search> arr) {
		buildMaxHeapify(arr);
		for(int i = arr.size() - 1; i > 0; i--) {
			Collections.swap(arr, 0, i);
			this.heapSize--;
			maxHeapify(arr, 0);
		}
	}

	/*
	 * Returns the Search with the largest Page Rank
	 * @param arr the supplied array
	 * @return the Search with highest Priority Rank
	 */
	public Search heapMaximum(ArrayList<Search> arr) {
		return arr.get(0);
	}

	/*
	 * Removes the highest page rank Search from the list
	 * @param arr the supplied array
	 * @return Highest priority rank search
	 */
	public Search heapExtractMax(ArrayList<Search> arr) {
		if(heapSize < 1) {
			System.err.println("Heap UnderFlow");
			return null;
		}else {
			Search max = arr.get(0);
			arr.set(0, arr.get(heapSize-1));
			arr.remove(arr.size()-1);
			this.heapSize = this.heapSize - 1;
			maxHeapify(arr, 0);
			return max;
		}
		
	}

	/*
	 * Increases the Page Rank of the given index
	 * @param arr the supplied array
	 * @param i the index of the Search
	 * @param key the new Page Rank of the specific index
	 */
	public void heapIncreaseKey(ArrayList<Search> arr, int i, int key) {
		if(key < arr.get(i).getPageRank()) {
			System.err.println("New Key Is Smaller Than Current Key");
		}else {
			arr.get(i).increasePageRank(key);
			while(i > 0 && arr.get(getParent(i)).getPageRank() < arr.get(i).getPageRank()) {
				Collections.swap(arr, i, getParent(i));
				i = getParent(i);
			}
		}
	}

	/*
	 * Inserts a Search into the heapList
	 * @param arr the supplied array
	 * @param key the value of the PageRank
	 */
	public void heapInsert(ArrayList<Search> arr, int key) {
		this.heapSize = this.heapSize + 1;
		arr.get(heapSize-1).setPageRank(Integer.MIN_VALUE);
		heapIncreaseKey(arr, this.heapSize-1, key);
	}

	/*
	 * Returns the parent of the given index
	 * @param i index you are trying to find the parents for
	 * @return parent index
	 */
	public int getParent(int i) {
		int index = 0;
		if(i % 2 == 0) {
			index = i/2-1;
		}
		else {
			index = i/2;
		}
		return index;
	}
	
	/*
	 * Returns the left child of the index
	 * @param i index of parent node
	 * @return left child index
	 */
	public int getLeftChild(int i) {
		return (i * 2) + 1;
	}
	
	/*
	 * Returns the right child of the index
	 * @param i index of parent node
	 * @return right child index
	 */
	public int getRightChild(int i) {
		return (i * 2) + 2;
	}

	/*
	 * Change heapSize of the heap
	 * @param i new heap size
	 */
	public void setHeapSize(int i) {
		this.heapSize = i;
	}
	
	/*
	 * Returns heapSize
	 * @return heapSize
	 */
	public int getHeapSize() {
		return heapSize;
	}

}
