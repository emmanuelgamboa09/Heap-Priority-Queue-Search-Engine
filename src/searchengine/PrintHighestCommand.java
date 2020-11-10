package searchengine;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

/**
 * Print Highest Priority button command
 * @author Emmanuel Gamboa
 *
 */
public class PrintHighestCommand extends Command{

	/*
	 * Constructor
	 * @param model sets our model to model
	 */
	public PrintHighestCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Prints the Search with the highest Page rank in our Priority Queue
	 */
	public void execute() {
		SearchEngine temp = (SearchEngine) model;
		if(temp.getPriorityQueue().size() == 0) {
			Utilities.error("List is Empty. Please add to the list before using this command");
			return;
		}
		Search highest = temp.getHeap().heapMaximum(temp.getPriorityQueue());
		String response ="PageRank:" + highest.getPageRank() + " "+ highest.getWebsite();	
		Utilities.inform(response);
	}

}
