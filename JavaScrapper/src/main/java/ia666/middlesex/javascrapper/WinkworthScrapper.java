/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ia666.middlesex.javascrapper;

/**
 *
 * @author Code
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class WinkworthScrapper extends BaseScrapper{

    @Override
    public void run(){
        try{
            scrapeWebsite();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /** Constructor */
    WinkworthScrapper(){
    }
        
        
        
    void scrapeWebsite() throws Exception{
        scrapeFrontpage();
    }
        
    void scrapeFrontpage() throws Exception{
        //Download HTML document from website
        boolean propertiesRemaining = true;
        for(int i=1; propertiesRemaining; i++){
            if(debug) System.out.println("Connecting to " + realEstate.getUrl() + i);
            Document doc = Jsoup.connect(this.realEstate.getUrl() + i).userAgent("Opera").get();
            if(info) System.out.println("Connected to " + realEstate.getName() + i);
            //if(verbose) System.out.println(doc.html());
            propertiesRemaining=false;
            //Get all of the products on the page
            Elements properties = doc.select("article.search-result");
            if(info) System.out.println("Found " + properties.size() + " properties");

            //Work through the products
            for(int j=0; j<properties.size(); j++){
                //Get the address/title of the property
                Elements title = properties.get(j).select("h2.search-result__title");

                //Get the property price -- Remove all children elements


                Elements finalPrice = properties.get(j).select("h3.search-result__price>span.price-meta");
                finalPrice.remove();

                //Get the bedrooms, selecting first span child element of class specs__text
                Elements bedrooms = properties.get(j).select("div.search-result__specs");
                bedrooms = bedrooms.select("li.specs__item").first().select("span.specs__text");

                //Output the data that we have downloaded
                if(debug){
                    System.out.println("HTML Output \nAddress: " + title.html() + ";\n Beds: " + 
                    bedrooms.html() + ";\n PRICE: " + finalPrice.html());
                }
                

                if(info) System.out.println("WINKWORTH\nAddress: " + title.text() + "; BEDS: " + 
                bedrooms.text() + "; PRICE: " + finalPrice.text().replaceAll("([^0-9.])", "")
                + "; PRICE(fulltext): " + finalPrice.html());

                //Add the property to the database
                dao.saveProperty(this.realEstate, title.text(),
                    Float.parseFloat(finalPrice.text().replaceAll("([^0-9.])", "")),
                    "PPPPP", "UUUU", "XXXX XXX");
                wait(1000);
            }
            if(properties.size() == 0) propertiesRemaining = false;
        }
    }

    //constructor
    public WinkworthScrapper(RealEstate realEstate, ScrapperDao dao){
        this.realEstate = realEstate;
        this.dao = dao;
    }
}
