package searchengine;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Crawler implemented for the search engine
 * @author Emmanuel Gamboa
 *
 */
public class Crawler {

	/*
	 * Returns a list of links based off of your keyword
	 * @param query what you want to search for
	 * @return list of url links
	 */
	public ArrayList<String> getLinks(String query) {
		ArrayList<String> result = new ArrayList<String>();
		String request = "https://www.google.com/search?q=" + query + "&num=40";	
		try {
			Document doc = Jsoup.connect(request).userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
					.timeout(5000).get();

			// get all links
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				String temp = link.attr("href");
				if(temp.startsWith("/url?q=")){
					result.add(temp.substring(15));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}