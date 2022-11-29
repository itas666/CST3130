package ia666.middlesex.javascrapper;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.persistence.*;

/**
 *
 * @author Code
 */



@Entity
@Table(name="real_estate_inventory")
public class PropertyInventory {
    @Id
    @Column(name = "id", nullable = false)
    int id;
    /*
    @Id
    @Column(name = "real_estate_id")
    RealEstate realEstateId;
    */
    @Column(name = "url")
    String url;
    
    @Column(name = "price")
    double price;
    

    // One to Many relationship - the real_estate_inventory table
    // has the URL to the property and the price
    @ManyToOne(cascade = CascadeType.ALL)
    @ElementCollection(targetClass=Integer.class)
    @JoinColumn(name = "property_id")
    private Property property;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @ElementCollection(targetClass=Integer.class)
    @JoinColumn(name = "real_estate_id")
    private RealEstate realEstate;

    public String toString(){
        return "PropertyInventory [propertyId=" + property.getId() + ", realEstateId=" + realEstate.getId() +
        ", url=" + url + ", price=" + price + ", id=" + id + "]";
    }
    
    //Getters and setters
    public Property getProperty() {
        return property;
    }
    public void setProperty(Property property) {
        this.property = property;
    }
    public RealEstate getRealEstate() {
        return realEstate;
    }
    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }


}
