package ia666.middlesex.javascrapper;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-package ia666.middlesex.javascrapper;


/**
 *
 * @author Code
 *
default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class PorticoScrapper extends BaseScrapper{

    @Override
    public void run(){
        try{
            dao = new ScrapperDao();
            dao.init();
            scrapeWebsite();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /** Constructor */
    public PorticoScrapper(){
    }
    
    void scrapeWebsite() throws Exception{
        scrapeFrontpage();
        scrapeHiddenProperties();
    }
    
    void scrapeFrontpage() throws Exception{
        //Download HTML document from website
        
        Document doc = Jsoup.connect(realEstate.getUrl()).get();
        if(this.debug) System.out.println(doc.select(".main").html());

        //Get all of the products on the page
        Elements properties = doc.select(".property_module_wrapper");
        if(info) System.out.println("Found " + properties.size() + " properties");
        
        //Work through the products
        for(int i=0; i<properties.size(); ++i){
            
            //Get the address/title of the property
            Elements title = properties.get(i).select(".property_module_title").select("a");
            
            //Get the property price
            Elements finalPrice = properties.get(i).select("a");
            
            //Get the weight
            Elements bedrooms = properties.get(i).select(".pm_icon_wrapper");
            
            //Output the data that we have downloaded
            if(info) System.out.println("PORTICO\nAddress: " + title.text() + "; Beds: " + 
            bedrooms.text() + "; PRICE: " + finalPrice.text().replaceAll("([^0-9,])", "") + 
            "PRICE(fulltext): " + finalPrice.html());
            wait(1000);
        }
        // All main page properties obtained
    }
        
    void scrapeHiddenProperties() throws Exception{
        //Download HTML document from website
        
        Document doc = Jsoup.connect("https://www.portico.com/search-results/?type=rent&where=").get();
        // Scraping "hidden" properties
        if(verbose) System.out.println(doc.html());
        
        //Get all of the products on the page
        Elements properties = doc.select(".pp_load");
        
        //Download HTML document from website
        
        Document subDoc;
        //Work through the products
        if(info) System.out.println("Found " + properties.size() + " hidden properties");

        for(int i=0; i<properties.size(); ++i){
            //Extract the link for all of the hidden properties
            Elements link = properties.get(i).select("#hypPPOpen");
            String linkText = link.attr("href");
            
            // Connect to each one of the hidden properties
            // because they are not fully loaded
            subDoc = Jsoup.connect("https://www.portico.com" + linkText).get();
            //Get the address/title of the property
            
            //Get the property price
            Elements finalPrice = subDoc.select(".detail_imgtxt_l3");
            
            //Get the property price
            Elements title = subDoc.select("h1.align_left.all_uppercase");
            
            //Get the weight
            Elements bedrooms = subDoc.select(".detail_imgtxt_l12");
            
            //Output the data that we have downloaded
            if (info) System.out.println("PORTICO\nAddress: " + title.text() + "; Beds: " + 
            bedrooms.text() + "; PRICE: " + finalPrice.text());

            //Add property to database
            
            dao.saveProperty(this.realEstate, title.text(),
            Float.parseFloat(finalPrice.text().replaceAll("([^0-9.])", "")),
            "PPPPP", "UUUU", "XXXX XXX");

/*********** ADD More selectors ******/
/********** Week 7 DB through hibernate *********/
/********* Spring for multithread ************/
            wait(1000);
        }
    }

    //constructor
    public PorticoScrapper(RealEstate realEstate, ScrapperDao dao){
        this.realEstate = realEstate;
        this.dao = dao;
    }
}
