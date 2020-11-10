package searchengine;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

import mvc.AppFactory;
import mvc.AppPanel;

/**
 * Creates our panel that is displayed in our gui
 * @author Emmanuel Gamboa
 *
 */
public class SearchPanel extends AppPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List displayList = new List();
	
	/*
	 * Constructor
	 * Creates our different panels for our gui
	 * @param factory the AppFactory interface from the mvc
	 */
	public SearchPanel(AppFactory factory) {
		super(factory);
		
		JButton search = new JButton("Search");
		search.addActionListener(this);
		JButton print = new JButton("Display Search List");
		print.addActionListener(this);
		JButton priorityQueue = new JButton("Insert Searches into Priority Queue");
		priorityQueue.addActionListener(this);
		JButton clear = new JButton("Clear Search List");
		clear.addActionListener(this);
		JButton printHighest = new JButton("Print Highest Priority");
		printHighest.addActionListener(this);
		JButton extractHighest = new JButton("Extract Max");
		extractHighest.addActionListener(this);
		JButton printPriorityQueue = new JButton("Display Priority Queue");
		printPriorityQueue.addActionListener(this);
		JButton insert = new JButton("Insert into Priority Queue");
		insert.addActionListener(this);
		JButton changePriorityAmount = new JButton("Change Priority Amount");
		changePriorityAmount.addActionListener(this);
		
		
		this.setLayout(new GridLayout(2,1));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,3));
		JPanel searchPanel = new JPanel(new GridLayout(4,1));
		JPanel priorityPanel = new JPanel(new GridLayout(5,1));

		JPanel panel = new JPanel();
		
		panel = new JPanel();
		panel.add(print);
		searchPanel.add(panel);
		
		panel = new JPanel();
		panel.add(search);
		searchPanel.add(panel);
		
		panel = new JPanel();
		panel.add(priorityQueue);
		searchPanel.add(panel);
		
		panel = new JPanel();
		panel.add(clear);
		searchPanel.add(panel);
		
		panel = new JPanel();
		panel.add(printPriorityQueue);
		priorityPanel.add(panel);
		
		panel = new JPanel();
		panel.add(printHighest);
		priorityPanel.add(panel);
		
		panel = new JPanel();
		panel.add(extractHighest);
		priorityPanel.add(panel);
		
		
		panel = new JPanel();
		panel.add(insert);
		priorityPanel.add(panel);
		
		panel = new JPanel();
		panel.add(changePriorityAmount);
		priorityPanel.add(panel);
		 
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(searchPanel);
		panel.add(priorityPanel);
	
		JPanel text = new JPanel();
		text.setLayout(new BorderLayout());
		text.add(displayList);
		this.add(text, "North");
		this.add(panel, "South");
	}
	
	/*
	 * Checks which list you want to display and switches between them
	 * 
	 */
	public void propertyChange(PropertyChangeEvent arg) {
		SearchEngine temp = (SearchEngine) model;
		// clears the list first
		this.displayList.removeAll();
		
		ArrayList<Search> tempList= null;
		if(temp.getDisplay()) {
			tempList = temp.getWebsites();
		}
		else {
			tempList = temp.getPriorityQueue();
		}
		
		// goes through the list and and adds all the searches to it
		for(int i = 0; i < tempList.size(); i++) {
			this.displayList.add((i+1)+" "+tempList.get(i).toString());
		}
		super.propertyChange(arg);		
	}
	
	public static void main(String[] args) {
		AppFactory factory = new SearchFactory();
		AppPanel panel = new SearchPanel(factory);
		panel.display();
	}
}
