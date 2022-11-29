package ia666.middlesex.javascrapper;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Fetch;

/**
 *
 * @author Code
 */



@Entity
@Table(name="property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;
    
    @Column(name = "address")
    String address;
    
    @Column(name = "postcode")
    String price;
    
    @Column(name = "photo_url")
    String photoUrl;
    
    // One to Many relationship - the real_estate_inventory table
    // has the URL to the property and the price

    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    List<PropertyInventory> propertyInventory = new ArrayList<>();

    public String toString(){
        return "id: " + id + "; address: " + address + "; price: " + price + "; photoUrl: " + photoUrl;
    }
    
    //Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public void setPostcode (String postcode) {
        this.price = postcode;
    }
    public String getPostcode () {
        return price;
    }

}
