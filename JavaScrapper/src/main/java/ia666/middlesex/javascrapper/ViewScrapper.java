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


public class ViewScrapper extends BaseScrapper{
    
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
    ViewScrapper(){
    }
        
        
        
    void scrapeWebsite() throws Exception{
        scrapeFrontpage();
    }
        
    void scrapeFrontpage() throws Exception{
        //Download HTML document from website
        int pagination = 12;
        int currentPagination = 0;
        boolean propertiesRemaining = true;
        for(int i=0; propertiesRemaining; i++){
            currentPagination = pagination*i;
            Document doc = Jsoup.connect(realEstate.getUrl() + currentPagination).get();
            if(debug) System.out.println(doc.html());

            //Get all of the products on the page
            Elements properties = doc.select("div.eapow-overview-desc");
            if(info) System.out.println("Found " + properties.size() + " properties");

            //Work through the products
            for(int j=0; j<properties.size(); j++){

                //Get the address/title of the property
                Elements title = properties.get(i).select("div.eapow-overview-title");

                //Get the property price
                Elements finalPrice = properties.get(i).select("p.eapow-overview-price");

                //Get the weight
                Elements bedrooms = properties.get(i).select("div#propIcon").select("span.IconNum");

                //Output the data that we have downloaded
                if(debug)
                    System.out.println("HTML Output \nAddress: " + title.html() + ";\n Beds: " + 
                bedrooms.html() + ";\n PRICE: " + finalPrice.html());
                
                System.out.println("Address: " + title.text() + "; Beds: " + 
                bedrooms.text().substring(0, 1) + "; PRICE: " +
                finalPrice.text().replaceAll("([^0-9.])", "") + 
                "; PRICE(fulltext): " + finalPrice.text());

                //Add property to database
                dao.saveProperty(this.realEstate, title.text(),
                    Float.parseFloat(finalPrice.text().replaceAll("([^0-9.])", "")),
                    "PPPPP", "UUUU", "XXXX XXX");

                wait(1000);
            }

        }
    }

    //constructor
    public ViewScrapper(RealEstate realEstate, ScrapperDao dao){
        this.realEstate = realEstate;
        this.dao = dao;
    }
}
