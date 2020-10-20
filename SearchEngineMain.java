package searchengine;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngineMain {
	
	public static void main(String[] args) {
		SearchEngineSearches engine = new SearchEngineSearches();
		Scanner in = new Scanner(System.in);
		String response = "";
		Boolean differentCommands = false;
		
		while(!response.contentEquals("quit")) {
			if(!differentCommands) {
				System.out.print("\nEnter an input for a specific action\n"
						+ "1 to search \n"
						+ "2 to print the list of websites, \n"
						+ "3 to create Priority Queue \n"
						+ "4 to clear websites list\n"
						+ "0 for Priority Queue commands\n"
						+ "quit to quit the program\n"
						+ "-->");
				response = in.nextLine();
				if(response.contentEquals("1")) {
					System.out.println("Enter something to search");
					response = in.nextLine();
					if(!response.isBlank()) {
						ArrayList<Search> temp = engine.search(response);
						for(Search website : temp) {
							System.out.println(website.getWebsite());
						}
						System.out.println("Would you like to add these urls to your list");
						response = in.nextLine();
						if(response.equalsIgnoreCase("yes")) {
							engine.addWebsites(temp);
						}
					}
				}else if(response.contentEquals("2")) {
					engine.printList(engine.getWebsites());
				}else if(response.contentEquals("3")) {
					if(engine.getWebsites().isEmpty()) {
						System.out.println("Your list is empty, You have nothing to create the priority queue");
					}else {
						engine.createPriorityQueue();
						System.out.println("Priority Queue Created");
					}
				}else if(response.contentEquals("4")) {
					engine.clearWebsites();
					System.out.println("List Cleared");
				}else if(response.contentEquals("0")){
					if(engine.getPriorityQueue().isEmpty()) {
						System.out.println("Priority Queue is empty");
					}else {
						differentCommands = true;
					}
				}
			}else {
				System.out.print("\nEnter an input for a specific action\n"
						+ "1 to print the highest ranked website from the Priority Queue\n"
						+ "2 to Extract Highest Rank Website\n"
						+ "3 to Print Priority Queue\n" 
						+ "4 to insert website into Priority Queue\n"
						+ "5 to change Priority Amount of a specific index\n"
						+ "0 to go back to other commands\n"
						+ "-->");
				response = in.nextLine();
				
				if(response.contentEquals("1")) {
					Search answer = engine.getHeap().heapMaximum(engine.getPriorityQueue());
					System.out.println("Score:" + answer.getPageRank() + 
							" "+ answer.getWebsite());				
				}else if(response.contentEquals("2")) {
					Search answer = engine.getHeap().heapExtractMax(engine.getPriorityQueue());
					if(answer != null) {
					System.out.println("Score:" + answer.getPageRank() + 
							" "+ answer.getWebsite());	
					}else {
						System.out.println("Priority Queue is Empty");
					}
				}else if(response.contentEquals("3")) {
					engine.printList(engine.getPriorityQueue());
				}else if(response.contentEquals("4")) {
					engine.addToHeapList(in);
					System.out.println("This has been added to the Priority Queue");
				}else if(response.contentEquals("5")) {
					System.out.println("Enter which index you want to increase");
					int index = in.nextInt();
					System.out.println("Enter a new Total priority score");
					int paidIncrease = in.nextInt();
					in.nextLine();
					if(engine.getPriorityQueue().size() >= index) {
						engine.increasePriorityQueueKey(index-1, paidIncrease);
					}else {
						System.out.println("The Priority Queue doesn't contain a value in this index");
					}
				}else if(response.contentEquals("0")){
					differentCommands = false;
					engine.sortEntireList();
				}
			}
		}
	}
}


