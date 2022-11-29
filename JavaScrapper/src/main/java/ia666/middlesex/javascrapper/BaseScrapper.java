/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ia666.middlesex.javascrapper;

/**
 *
 * @author Code
 */
public class BaseScrapper extends Thread{
    boolean debug = false;
    boolean verbose = false;
    boolean info = true;
    
    RealEstate realEstate;
    ScrapperDao dao;
    
    //private int threadId;

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
    public BaseScrapper(){
    }
    
    public static void wait(int ms){
        try{
            Thread.sleep(ms);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
        
        
        
    void scrapeWebsite() throws Exception{
        scrapeFrontpage();
    }
        
    void scrapeFrontpage() throws Exception{}

    //constructor
    public BaseScrapper(RealEstate realEstate, ScrapperDao dao) {
        this.realEstate = realEstate;
        this.dao = dao;
    }
    /*
    public synchronized void uploadProperty(
        String company, String address, float price, int bedrooms
    )
*/
}