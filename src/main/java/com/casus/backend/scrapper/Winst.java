package com.casus.backend.scrapper;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Winst {
	
	
	public String getNewurl() {
		return newurl;
	}

	String newurl;
	public void search(String  searchnew) throws IOException {
		String google = "http://www.google.com/search?q=";
		String search = "marktplaats gebruikt "+searchnew;
		String charset = "UTF-8";
		String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!
		
		Elements links =  Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");
		boolean stop=false;
		for (Element link : links) {
		    String title = link.text();
		    String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
		    url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
		    if(!stop) {
		    	this.newurl=url;
		    	stop=true;
		    } else {
		    	break;
		    }
		   // if (!url.startsWith("http")) {
		     //   continue; // Ads/news/etc.
		   // }

		    System.out.println("Title: " + title);
		    System.out.println("URL: " + url);
		}
	}

}
