package searchengine;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

/**
 * Insert Searches into Priority Queue button command
 * @author Emmanuel Gamboa
 *
 */
public class InsertPriorityCommand extends Command{

	/*
	 * Constructor
	 * @param model sets our model to model
	 */
	public InsertPriorityCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates our priority queue
	 */
	public void execute() {

		SearchEngine temp = (SearchEngine) model;
		String response = Utilities.ask("How many would you like to insert into the Priority Queue");
		try {
			int amount = Integer.parseInt(response);
			if(amount > temp.getWebsites().size()) {
				Utilities.inform("We didn't find this many searches. All the searches we can add will be added.");
				amount = temp.getWebsites().size();
			}
			temp.addToPriorityList(temp.getWebsites(), amount);
		}catch(Exception e) {
			Utilities.error("Please enter an integer value");
		}
	}

}
