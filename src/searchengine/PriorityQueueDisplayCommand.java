package searchengine;

import mvc.Command;
import mvc.Model;

/**
 * Display Priority Queue button command
 * @author Emmanuel Gamboa
 *
 */
public class PriorityQueueDisplayCommand extends Command{

	/*
	 * Constructor
	 * @param model sets our model to model
	 */
	public PriorityQueueDisplayCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Switches our display to the Priority Queue list
	 */
	public void execute() {
		SearchEngine temp = (SearchEngine) model;
		temp.priorityListDisplay();
	}

}
