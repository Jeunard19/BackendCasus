package com.casus.backend.scrapper;

import java.awt.List;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleResults {
	private ArrayList<String> sellers = new ArrayList();
	private ArrayList<String> prices= new ArrayList();
	private String cator=null;
	private double lowestprice;
	private double highestprice;
	private String the_seller;
	
	public void gethtmlinfo(String term) throws UnsupportedEncodingException, IOException {
		int currentpage=1;
		Winst winst = new Winst();
		winst.search(term);
		String url=winst.getNewurl();
		System.out.println(url);
		int count=1;
		boolean keeploping =true;
	while(keeploping) {
		ArrayList<String> firstlist = new ArrayList();
		Document doc =  Jsoup.connect(url+"&currentPage="+currentpage).get();
		currentpage+=1;
		if(count ==1) {
			firstlist.addAll(doc.body().getElementsByClass("seller-name ellipsis").eachText());
			System.out.println(firstlist);
			count+=1;
			sellers.addAll(doc.body().getElementsByClass("seller-name ellipsis").eachText());
			prices.addAll(doc.body().getElementsByClass("price-and-thumb-container").eachText());
			cator= doc.body().getElementsByClass("category-name").text();

		} else {
			if(count==4) {
				break;
			}
			count+=1;
			sellers.addAll(doc.body().getElementsByClass("seller-name ellipsis").eachText());
			prices.addAll(doc.body().getElementsByClass("price-and-thumb-container").eachText());
			
		
		}
			
		}
	ArrayList<Double> pricese= new ArrayList();
	System.out.println(sellers);
	System.out.println(prices);
ArrayList<String> sellers2=new ArrayList<String>(sellers.subList(0, prices.size()));
	System.out.println(cator);
	for(String price:prices) {
		if(price.matches(".*\\d+.*")) {
			pricese.add(Double.parseDouble(((price.substring(2).replaceAll(",",".")))));
		}
	}
	this.lowestprice=Collections.min(pricese);
	this.the_seller=sellers2.get(prices.indexOf(("€ "+Collections.min(pricese)).toString().replaceAll("\\.",",")));
	this.highestprice=Collections.max(pricese);
	
	   }

	public double getHighestprice() {
		return highestprice;
	}

	public void setHighestprice(double highestprice) {
		this.highestprice = highestprice;
	}

	public double getLowestprice() {
		return lowestprice;
	}


	public GoogleResults(String term) throws UnsupportedEncodingException, IOException {
		this.gethtmlinfo(term);
	}

	public ArrayList<String> getSellers() {
		return sellers;
	}

	public ArrayList<String> getPrices() {
		return prices;
	}

	
	public String getCator() {
		return cator;
	}

	
		
		
		
		
	}

    

