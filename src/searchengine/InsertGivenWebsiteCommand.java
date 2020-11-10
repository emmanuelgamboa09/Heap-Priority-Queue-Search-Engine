package searchengine;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

/**
 * Insert into Priority Queue button control
 * @author Emmanuel Gamboa
 *
 */
public class InsertGivenWebsiteCommand extends Command{

	/*
	 * Constructor
	 * @param model sets our model to model
	 */
	public InsertGivenWebsiteCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Inserts a new Search into our Priority Queue
	 */
	public void execute() {
		SearchEngine temp = (SearchEngine) model;
		String url = Utilities.ask("Enter url for website");
		String frequency = Utilities.ask("Enter Frequency Score");
		String timeExisted = Utilities.ask("Enter Time Existed Score" );
		String otherLinks = Utilities.ask("Enter Other Links to Website Score");
		String paid = Utilities.ask("Enter Paid Score");

		try {
			int frequencyScore = Integer.parseInt(frequency);
			int timeExistedScore = Integer.parseInt(timeExisted);
			int otherLinksScore = Integer.parseInt(otherLinks);
			int paidScore = Integer.parseInt(paid);
			if(frequencyScore < 0 || timeExistedScore < 0|| otherLinksScore < 0|| paidScore < 0) {
				Utilities.error("Integers must be greater or equal to 0");
				return;
			}
			Search search = new Search(url, frequencyScore, timeExistedScore, otherLinksScore, paidScore);
			temp.addToPriorityList(search);
		} catch (Exception e) {
			Utilities.error("Input must be an integer for all the score");
		}
	}

}
