/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package ia666.middlesex.javascrapper;
import java.util.*;

/**
 *
 * @author Code
 */

public class ScrapingManager {

    //List of real estate companies that I have scrapers for
    List<RealEstate> realEstates = new ArrayList<RealEstate>();
    ScrapperDao dao = new ScrapperDao();




    //Create a new scraper for each website

    public void initialize() {
        System.out.println("Scrapping begins");
        dao.init();

        //Add the real estates to the list
        realEstates = dao.getAllRealEstates();

        WinkworthScrapper winkworth = new WinkworthScrapper(realEstates.get(4), dao);
        FoxtonScrapper foxton = new FoxtonScrapper(realEstates.get(0), dao);
        PorticoScrapper portico = new PorticoScrapper(realEstates.get(1), dao);
        //ViewScrapper view = new ViewScrapper(realEstates.get(2), dao);
        LettingsLondonScrapper lettingsLondon = new LettingsLondonScrapper(realEstates.get(3), dao);
        
        //Start the threads
        portico.start();
        //view.start(); //Fails
        foxton.start();
        lettingsLondon.start();
        winkworth.start();



        //Wait for the threads to finish
        /*try{
            portico.join();
            view.join();
            foxton.join();
            lettingsLondon.join();
            winkworth.join();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }*/

        System.out.println("End");
    }

    //default constructor
    public ScrapingManager() {
    }
}
