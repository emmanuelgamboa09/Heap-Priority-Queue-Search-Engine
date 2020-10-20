package searchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class SearchEngineSearches {

	private Heap heap;
	private ArrayList<Search> websites;
	private ArrayList<Search> heapList;

	/*
	 * Default constructor
	 * Initializes the heap
	 * Initializes the websites list
	 * Initializes the heapList list
	 */
	public SearchEngineSearches() {
		heap = new Heap();
		websites = new ArrayList<Search>();
		heapList = new ArrayList<Search>();
	}

	/*
	 * Uses our crawler to search for a list of websites
	 * @param keyword What you want to search for
	 * @return ArrayList of urls found based off of the keyword
	 */
	public ArrayList<Search> search(String keyword) {
		//Creates Random to use with our Search
		Random rnd = new Random();
		
		// Creates our crawler for our search 
		Crawler temp = new Crawler();
		
		//Our list of urls from our search
		ArrayList<String> result = temp.getLinks(keyword);
		
		//Creates an arraylist for our Searches
		ArrayList<Search> url = new ArrayList<Search>();
		int resultsSize = 30;
		if(result.size() < 30) {
			resultsSize = result.size();
		}
		// adds 30 urls to the temperary search list with randomly generated values
		for(int i = 0; i < resultsSize; i++) {
			url.add(new Search(result.get(i), rnd.nextInt(101)+1,rnd.nextInt(101)+1,rnd.nextInt(101)+1,rnd.nextInt(101)+1));
		}
		return url;
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
		return heapList;
	}
	
	/*
	 * Returns the heap
	 */
	public Heap getHeap() {
		return heap;
	}

	/*
	 * Adds Searches to the list of websites
	 * @param websites list of urls received from searching
	 */
	public void addWebsites(ArrayList<Search> websites) {
		this.websites.addAll(websites);

		//Sorts list after Searches have been added
		sortEntireList();
	}
	
	/*
	 * Clears the website list
	 */
	public void clearWebsites() {
		websites.clear();
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

	/*
	 * Increase the Priority Queue (PageRank) of a specific index
	 * @param index which element to increase
	 * @param key how much you want to increase the page rank by
	 */
	public void increasePriorityQueueKey(int index, int key ) {
		heap.heapIncreaseKey(heapList, index, key);
	}

	/*
	 * Creates the priority Queue
	 */
	public void createPriorityQueue() {
		// adds first 20 urls from your searches to the priority queue
		for(int i = 0; i < 20; i++) {
			heapList.add(websites.get(i));
		}
		
		//Sets heapsize to the size of the Priority Queue
		this.heap.setHeapSize(heapList.size());
	}
	
	public void addToHeapList(Scanner in) {
		System.out.println("Enter a website url");
		String url = in.nextLine();
		System.out.println("Enter a frequency score");
		int frequency = in.nextInt();
		System.out.println("Enter a time existed score");
		int timeExisted = in.nextInt();
		System.out.println("Enter a other links score");
		int otherLinks = in.nextInt();
		System.out.println("Enter a paid score");
		int paid = in.nextInt();
		in.nextLine();
		Search search = new Search(url, frequency, timeExisted, otherLinks, paid);
		heapList.add(search);
		//Inserts our search into our PriorityQueue maintaing Max Heap properties
		this.heap.heapInsert(heapList, search.getPageRank());
	}
	
	public void printList(ArrayList<Search> arr) {
		int i = 1;
		for(Search website : arr) {
			System.out.println(i+" Score:" + website.getPageRank() + 
					" frequency: " + website.getFrequencyScore()  +
					" Time Existed Score: " + website.getTimeExistedScore() +
					" Other Links Score: " + website.getOtherLinksScore() +
					" Paid Score: " + website.getPaidScore() + " "
					+ website.getWebsite());
			i++;
		}
	}
}
