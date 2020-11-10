package searchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import mvc.Model;

/**
 * Our model for our Search Engine
 * @author Emmanuel Gamboa
 *
 */
public class SearchEngine extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Heap heap;
	private ArrayList<Search> websites;
	private ArrayList<Search> priorityList;
	private boolean display;
	private Crawler crawler;
	/*
	 * Default constructor
	 * Initializes the heap
	 * Initializes the websites list
	 * Initializes the priorityList list
	 */
	public SearchEngine() {
		heap = new Heap();
		websites = new ArrayList<Search>();
		priorityList = new ArrayList<Search>();
		display = true;
		crawler = new Crawler();
	}

	/*
	 * Returns the arraylist of websites
	 * @return websites
	 */
	public ArrayList<Search> getWebsites(){
		return websites;
	}

	/*
	 * Returns the Priority Queue List
	 * @return heapList Priority Queue list
	 */
	public ArrayList<Search> getPriorityQueue(){
		return priorityList;
	}

	/**
	 * Returns our display boolean
	 */
	public Boolean getDisplay() {
		return display;
	}
	
	/*
	 * Returns the heap
	 */
	public Heap getHeap() {
		return heap;
	}

	/*
	 * Clears the website list
	 */
	public void clearWebsites() {
		websites.clear();
		changed();
	}

	/*
	 * Increase the Priority Queue (PageRank) of a specific index
	 * @param index which element to increase
	 * @param key how much you want to increase the page rank by
	 */
	public void increasePriorityQueueKey(int index, int key ) {
		heap.heapIncreaseKey(priorityList, index-1, key);
		changed();
	}

	/**
	 * Extracts max from our priority list
	 */
	public void extractMax() {
		heap.heapExtractMax(priorityList);
		changed();
	}

	/**
	 * adds Searches to our priority list
	 * @param arrayList Search to add to lis
	 */
	public void addToPriorityList(ArrayList<Search> arrayList, int index) {
		for(int i = 0; i < index ; i++) {
		priorityList.add(arrayList.get(i));
		this.heap.heapInsert(priorityList, arrayList.get(i).getPageRank());
		}
		changed();
	}
	
	/**
	 * adds the search which the user created into our priority list
	 * @param search
	 */
	public void addToPriorityList(Search search) {
		priorityList.add(search);
		//Inserts our search into our PriorityQueue maintaining Max Heap properties
		this.heap.heapInsert(priorityList, search.getPageRank());
		changed();
	}

	/**
	 * Changes our display to True which
	 * would print our SearchList
	 */
	public void searchListDisplay() {
		display = true;
		changed();
	}

	/**
	 * Changes our display to False which
	 * would print our Priority Queue list
	 */
	public void priorityListDisplay() {
		display = false;
		changed();
	}
	
	/*
	 * Uses our crawler to search for a list of websites
	 * @param keyword What you want to search for
	 * @return ArrayList of urls found based off of the keyword
	 */
	public void search(String keyword) {
		//Creates Random to use with our Search
		Random rnd = new Random();

		//Our list of urls from our search
		ArrayList<String> result = crawler.getLinks(keyword);

		//Creates an arraylist for our Searches
		ArrayList<Search> url = new ArrayList<Search>();

		for(int i = 0; i < result.size(); i++) {
			url.add(new Search(result.get(i), rnd.nextInt(101)+1,rnd.nextInt(101)+1,rnd.nextInt(101)+1,rnd.nextInt(101)+1));
		}
		websites = url;
		sortEntireList();
		display = true;
		changed();
	}
	
	/*
	 * Sorts the Search list so you can return your search
	 * in order based off the page rank
	 * This does not sort the Priority Queue since we only care
	 * that it is sorted with a Max Heap Property
	 */
	public void sortEntireList() {
		// Saves our heapSize if we have already created a priority queue
		int originalHeapSize = this.heap.getHeapSize();

		//set heapsize to the overall search list size so we can heapSort it again 
		this.heap.setHeapSize(websites.size());
		this.heap.heapSort(websites);

		//Reverse array size its in reverse order
		Collections.reverse(websites);

		//Set Heap Size to original size. This is needed if the Priority Queue was already created
		this.heap.setHeapSize(originalHeapSize);
	}
	
}
