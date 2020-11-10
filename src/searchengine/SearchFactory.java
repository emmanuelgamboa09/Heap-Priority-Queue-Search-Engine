package searchengine;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;

/**
 * Our factory in order to create our menu options for our gui
 * @author Emmanuel Gamboa
 *
 */
public class SearchFactory  implements AppFactory{

	/**
	 * Creates our SearchEngineSearches Model
	 */
	public Model makeModel() {
		return new SearchEngine();
	}

	/**
	 * Creates our edit commands 
	 */
	public String[] getEditCommands() {
		return new String[] {"Search", "Display Search List", "Insert Searches into Priority Queue", "Clear Search List",
				"Display Priority Queue", "Print Highest Priority", "Extract Max",
				"Insert into Priority Queue", "Change Priority Amount"};
	}

	/**
	 * Based off of the command entered, a specific command is called
	 */
	public Command makeEditCommand(Model model, String type) {
		if (type == "Search")
			return new SearchCommand(model);
		else if(type == "Display Search List")
			return new SearchListDisplayCommand(model);
		else if(type == "Insert Searches into Priority Queue")
			return new InsertPriorityCommand(model);
		else if(type == "Clear Search List")
			return new ClearCommand(model);
		else if(type == "Print Highest Priority")
			return new PrintHighestCommand(model);
		else if(type == "Extract Max")
			return new ExtractMaxCommand(model);
		else if(type == "Display Priority Queue")
			return new PriorityQueueDisplayCommand(model);
		else if(type == "Insert into Priority Queue")
			return new InsertGivenWebsiteCommand(model);
		else if(type == "Change Priority Amount")
			return new ChangePriorityCommand(model);
		
		return null;
	}

	/**
	 * Returns the title of our project
	 */
	public String getTitle() {
		return "Search Engine";
	}

	/**
	 * Instruction on how to use the gui
	 */
	public String[] getHelp() {
		return new String[] {"Search: Enter something to search for urls",
				"Display Search List: Displays the search List",
				"Insert Searches into Priority Queue: Allows user to add searches to priority queue",
				"Clear Search List: Erases everything in the search list",
				"Display Priority Queue: Displays priority queue list",
				"Print Highest Priority: Displays websites with highest page rank",
				"Extract Max: Displays and removes the highest page rank search in priority queue",
				"Insert into Priority Queue: Allows user to enter a search into the priority list",
				"Change Priority Amount: Allows user to change the page rank of a given index"};
	}

	/**
	 * Displays author 
	 */
	public String about() {
		return "Search Engine 1.0 Copyright 2020 by Emmanuel Gamboa";
	}

}
