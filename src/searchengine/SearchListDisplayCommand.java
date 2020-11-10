package searchengine;

import mvc.Command;
import mvc.Model;

/**
 * Display Search List button control
 * @author Emmanuel Gamboa
 *
 */
public class SearchListDisplayCommand extends Command {

	/*
	 * Constructor
	 * @param model sets our model to model
	 */
	public SearchListDisplayCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Displays our search list 
	 */
	public void execute() {
		SearchEngine temp = (SearchEngine) model;
		temp.searchListDisplay();
	}

}
