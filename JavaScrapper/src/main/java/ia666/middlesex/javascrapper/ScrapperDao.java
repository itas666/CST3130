/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ia666.middlesex.javascrapper;

import java.util.*;

/**
 *
 * @author Code
 */



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/** Saving and merging with Hibernate */
public class ScrapperDao {
    SessionFactory sessionFactory;



    /** A quick save function that will save the property, and save a record to the real_estate_inventory
     * as well. This is a quick save function, and it will not check if the property already exists
     * in the database.
     */
    public void saveProperty(RealEstate realEstate, String address, float price, String photoUrl, String url, String postcode){
        /* This operation will be locked because I trust updating many properties
         at the same time can cause issues
         */
        synchronized(this){
            // Create a new session
            System.out.println("Saving property: " + address);
            Session session = sessionFactory.openSession();
            System.out.println("Session created - real estate: " + realEstate.toString());
            session.beginTransaction();
            
            // Create a new property
            Property property = new Property();
            property.setAddress(address);
            property.setPhotoUrl(photoUrl);
            property.setPostcode(postcode);

            System.out.println("Property created: " + property.toString());
            // Save the property
            session.save(property);
            
            // Create a new real estate inventory
            PropertyInventory propertyInventory = new PropertyInventory();
            propertyInventory.setPrice(price);
            propertyInventory.setUrl(url);
            propertyInventory.setRealEstate(realEstate);
            propertyInventory.setProperty(property);
            System.out.println("Property Inventory created: " + propertyInventory.toString());
            // Save the property inventory
            session.save(propertyInventory);
            
            // Commit the transaction
            session.getTransaction().commit();
            session.close();
        }
    }

    /** Select a single property given the postcode, address and realEstateId */
    public Property getSingleProperty(String postcode, String address, int realEstateId){
        //Get a new Session instance from the session factory and start transaction
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        //Get filtered property records from the database
        List<Property> properties = session.createQuery("from property p JOIN real_estate_inventory rei ON "
        + " p.property_id = rei.property_id where postcode = :postcode and address = :address"
        + " and real_estate_id = :realEstateId")
                .setParameter("postcode", postcode)
                .setParameter("address", address)
                .setParameter("realEstateId", realEstateId)
                .list();

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        if(properties.size() == 1){
            return properties.get(0);
        }
        return null;
    }

    public List<RealEstate> getAllRealEstates(){
        //Get a new Session instance from the session factory and start transaction
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        //Get filtered property records from the database
        List<RealEstate> realEstates = session.createQuery("from RealEstate").getResultList();

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        return realEstates;
    }


    /** Sets up the session factory.
     *  Call this method first.  */
    public void init(){
        try {
            //Create a builder for the standard service registry
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

            //Load configuration from hibernate configuration file.
            //Here we are using a configuration file that specifies Java annotations.
            standardServiceRegistryBuilder.configure("hibernate.cfg.xml");

            //Create the registry that will be used to build the session factory
            StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
            try {
                //Create the session factory - this is the goal of the init method.
                sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            }
            catch (Exception e) {
/* The registry would be destroyed by the SessionFactory,
but we had trouble building the SessionFactory, so destroy it manually */
                System.err.println("Session Factory build failed.");
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy( registry );
            }

            //Ouput result
            System.out.println("Session factory built.");

        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("SessionFactory creation failed." + ex);
        }
    }
}
