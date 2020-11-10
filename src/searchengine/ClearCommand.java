package searchengine;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

/**
 * Clear Search List button control
 * @author Emmanuel Gamboa
 *
 */
public class ClearCommand extends Command{

	/*
	 * Constructor
	 * @param model sets our model to model
	 */
	public ClearCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	/*
	 * Clears the saved list of Searches
	 */
	public void execute() {
		SearchEngine temp = (SearchEngine) model;
		temp.clearWebsites();
		Utilities.inform("Search List Cleared");
	}

}
