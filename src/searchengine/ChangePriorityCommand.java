package searchengine;


import mvc.Command;
import mvc.Model;
import mvc.Utilities;

/**
 * Change Priority Amount button control
 * @author Emmanuel Gamboa
 *
 */
public class ChangePriorityCommand extends Command{

	/*
	 * Constructor
	 * Creates our ChangePriorityCommand
	 * @param model sets our model to model
	 */
	public ChangePriorityCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Override
	 * Changes the priority score of a specific index within the priority list
	 */
	public void execute() {
		SearchEngine temp = (SearchEngine) model;

		if(temp.getPriorityQueue().size() == 0) {
			Utilities.error("List is Empty. Please add to the list before using this command");
			return;
		}
		String i = Utilities.ask("Enter the Index");
		String amount = Utilities.ask("Enter new Priority Amount");
		try {
			int index = Integer.parseInt(i);
			int newAmount = Integer.parseInt(amount);
			int cost = newAmount - temp.getPriorityQueue().get(index-1).getPageRank();
			if(cost <= 0) {
				Utilities.inform("Can't decrease priority of the index");
				return;
			}
			Boolean ask = Utilities.confirm("This will cost $"+cost+". Do you want to pay this?");
			if(!ask) {
				temp.increasePriorityQueueKey(index, newAmount);
			}
		}catch(Exception e) {
			Utilities.error("Please make sure you entered an valid integer values");
		}
	}

}

