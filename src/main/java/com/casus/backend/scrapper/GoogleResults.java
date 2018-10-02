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
		winst.search("\""+term+"\"");
		String url=winst.getNewurl();
		System.out.println(url);
		int count=1;
		boolean keeploping =true;
	
		ArrayList<String> firstlist = new ArrayList();
		Document doc =  Jsoup.connect(url+"&currentPage="+currentpage).get();
		
		
			this.cator= doc.body().getElementsByClass("category-name").text();
			System.out.println(this.cator);
			sellers.addAll(doc.body().getElementsByClass("seller-name ellipsis").eachAttr("title"));
			prices.addAll(doc.body().getElementsByClass("price-new").eachText());
			
		
	
			
		
	ArrayList<Double> pricese= new ArrayList();
	System.out.println(sellers);
	System.out.println(prices);
ArrayList<String> prices2=new ArrayList<String>(prices.subList(0, sellers.size()));
sellers=new ArrayList<String>(sellers.subList(0, prices2.size()));
	
System.out.println(cator);
System.out.println(sellers.size());
System.out.println(prices2.size());

	for(String price:prices2) {
		if(price.matches(".*\\d+.*")) {
			pricese.add(Double.parseDouble(((price.substring(2).replaceAll("\\.","").replaceAll(",",".")))));
		}
	}
	this.lowestprice=Collections.min(pricese);
	String target = ("€ "+Collections.min(pricese)).toString().replaceAll("\\.",",");
	if(target.endsWith(",0")) {
		target+="0";
		
	}
	System.out.println(target);
	
	this.the_seller=sellers.get(prices2.indexOf(target));
	System.out.println(this.the_seller);
	
	this.highestprice=Collections.max(pricese);
	System.out.println(this.highestprice);
	
	
	   }

	public double getHighestprice() {
		return highestprice;
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

    

