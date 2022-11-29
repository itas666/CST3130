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


public class LettingsLondonScrapper extends BaseScrapper {

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
    LettingsLondonScrapper(){
    }
        
        
        
    void scrapeWebsite() throws Exception{
        scrapeFrontpage();
    }
        
    void scrapeFrontpage() throws Exception{
//Download HTML document from website
        boolean propertiesRemaining = true;
        for(int i=0; propertiesRemaining; i++){
//They list their properties many times...
            Document doc = Jsoup.connect(realEstate.getUrl() + i).get();
            if(debug) System.out.println(doc.html());

//Get all of the products on the page
            Elements properties = doc.select("div.property-list.content");
            if(info) System.out.println("Found " + properties.size() + " properties");

//Work through the products
            for(int j=0; j<properties.size(); j++){
                String linkToProperty = "https://www.lettingsoflondon.com/" +
                    properties.get(j).select("a").attr("href");

                Document subDoc = Jsoup.connect(linkToProperty).get();
                
//Get the address/title of the property
                Elements title = subDoc.select("h2.fnt-size24.padbtm").select("a");

//Get the property price
                Elements finalPrice = subDoc.select("div.price").select("span.price-pound");

//Get the weight
                Elements bedrooms = subDoc.select("div.price").select("i.fa.fa-bed.padrght");

//Output the data that we have downloaded
                if(debug)
                    System.out.println("HTML Output \nAddress: " + title.html() + ";\n Beds: " + 
                bedrooms.html() + ";\n PRICE: " + finalPrice.html());
                
                if(info) System.out.println("L.LONDON\nAddress: " + title.text() + "; Beds: " + 
                bedrooms.text() + "; PRICE: " + finalPrice.text().replaceAll("([^0-9.])", "") + 
                "PRICE(fulltext): " + finalPrice.text());

                //Add the property to the database
                dao.saveProperty(this.realEstate, title.text(),
                    Float.parseFloat(finalPrice.text().replaceAll("([^0-9.])", "")),
                    "PPPPP", "UUUU", "XXXX XXX");
                wait(1000);
            }
            if(properties.isEmpty()) propertiesRemaining = false;
        }
    }

    //constructor
    public LettingsLondonScrapper(RealEstate realEstate, ScrapperDao dao){
        this.realEstate = realEstate;
        this.dao = dao;
    }
        
}
