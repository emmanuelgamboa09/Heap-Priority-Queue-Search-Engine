package searchengine;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

/**
 * Search button control
 * @author Emmanuel Gamboa
 *
 */
public class SearchCommand extends Command{

	/*
	 * Constructor
	 * @param model sets our model to model
	 */
	public SearchCommand(Model model) {
		super(model);
	}

	/**
	 * Allows user to Search for a list of websites and adds them to our search list
	 */
	public void execute() {
		SearchEngine temp = (SearchEngine) model;
		String response = Utilities.ask("What Do You Want To Search For");
		temp.search(response);
		temp.searchListDisplay();
	}

}
