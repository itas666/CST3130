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

public class FoxtonScrapper extends BaseScrapper{

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
    FoxtonScrapper(){
    }
        
        
    void scrapeWebsite() throws Exception{
        scrapeFrontpage();
    }
        
    void scrapeFrontpage() throws Exception{
        //Download HTML document from website
        
        Document doc = Jsoup.connect(this.realEstate.getUrl()).get();
        if(debug) System.out.println(doc.html());

        //Get all of the products on the page
        Elements properties = doc.select("div.property_summary");
        if(info) System.out.println("Found " + properties.size() + " properties");
        //Work through the products
        for(int i=0; i<properties.size(); ++i){
            
            //Get the address/title of the property
            Elements title = properties.get(i).select("h6").select("a");
            
            //Get the property price
            Elements finalPrice = properties.get(i).select("h2").select("a.tooltip");
            
            //Get the bedrooms
            Elements bedrooms = properties.get(i).select("div.facilities_wrapper>span.bedrooms");
            
            //Output the data that we have downloaded
            if(info)
            System.out.println("FOXTON\nAddress: " + title.text() + "; Beds: " + 
            bedrooms.text() + "; PRICE: " + finalPrice.text().replaceAll("([^0-9.])", "") + 
            "PRICE(fulltext): " + finalPrice.text());
                
            //Add the property to the database
                dao.saveProperty(this.realEstate, title.text(),
                Float.parseFloat(finalPrice.text().replaceAll("([^0-9.])", "")),
                "PPPPP", "UUUU", "XXXX XXX");
            wait(1000);
        }
        
        // All main page properties obtained
    }

    //constructor
    public FoxtonScrapper(RealEstate realEstate, ScrapperDao dao) {
        this.realEstate = realEstate;
        this.dao = dao;
    }
}
