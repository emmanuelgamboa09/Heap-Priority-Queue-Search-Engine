package searchengine;

public class Search {

	private int pageRank;
	private int frequency;
	private int timeExisted;
	private int otherLinks;
	private int paid;
	private String website;
	
	/*
	 * Default Constructor
	 * Creates a new Search
	 * @param websites url of the websites
	 * @param frequency how often the website is visited
	 * @param timeExisted how long the websites has been available
	 * @param otherLinks links from other websites to that webpage
	 * @param paid how much the user paid for a higher chance of displaying the websites when searched
	 * pageRank is set to the total of the frequency, timeExisted, otherLinks, and paid value
	 */
	public Search(String website, int frequency, int timeExisted, int otherLinks, int paid) {
		this.website = website;
		this.frequency = frequency;
		this.timeExisted = timeExisted;
		this.otherLinks = otherLinks;
		this.paid = paid;
		pageRank = frequency + timeExisted + otherLinks + paid;
	}

	/*
	 * Changes the PageRank
	 * @param pageRank new pageRank Value
	 */
	public void setPageRank(int pageRank) {
		this.pageRank = pageRank;
	}
	
	/*
	 * Returns the Frequency Score
	 */
	public int getFrequencyScore() {
		return frequency;
	}
	
	/*
	 * Returns the Time Existed Score
	 */
	public int getTimeExistedScore() {
		return timeExisted;
	}
	
	/*
	 * Returns the Other Links Score
	 */
	public int getOtherLinksScore() {
		return otherLinks;
	}
	
	/*
	 * Returns the Paid Score
	 */
	public int getPaidScore() {
		return paid;
	}
	
	/*
	 * Returns the Page Rank Score
	 */
	public int getPageRank() {
		return pageRank;
	}
	
	/*
	 * Returns the url of the websites
	 */
	public String getWebsite() {
		return website;
	}
	
	/*
	 * Used to increase the PageRank Score of the Search.
	 * This adds the increase amount to the paid score since 
	 * the only way to increase the overall score is to pay for 
	 * a higher score
	 */
	public void increasePageRank(int key) {
		this.pageRank = key;
		int increase = key - frequency - otherLinks - paid - timeExisted;
		paid = paid + increase;
	}
}
