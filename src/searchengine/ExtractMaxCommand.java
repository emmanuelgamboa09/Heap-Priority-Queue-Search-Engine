package searchengine;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

/**
 * Extract Max button control
 * @author Emmanuel Gamboa
 *
 */
public class ExtractMaxCommand extends Command{

	/*
	 * Constructor
	 * @param model sets our model to model
	 */
	public ExtractMaxCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Extracts our Highest Page Rank from our Priority Queue
	 */
	public void execute() {
		SearchEngine temp = (SearchEngine) model;
		if(temp.getPriorityQueue().size() == 0) {
			Utilities.error("List is Empty. Please add to the list before using this command");
			return;
		}
		Search highest = temp.getHeap().heapMaximum(temp.getPriorityQueue());
		temp.extractMax();
		String response ="Max Extracted PageRank:" + highest.getPageRank() + " "+ highest.getWebsite();	
		Utilities.inform(response);		
	}

}
