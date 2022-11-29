package ia666.middlesex.javascrapper;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import java.util.*;

/**
 *
 * @author Code
 */


@Entity
@Table(name="real_estate")
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "url")
    String url;

    /* Many to many binding was giving me issues
     * so I decided to use a one to many relationship
     * with the real_estate_inventory table
     */
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @OneToMany(mappedBy = "realEstate", cascade = CascadeType.ALL)
    List<PropertyInventory> propertyInventory = new ArrayList<>();

    public String toString(){
        return "id: " + id + "; name: " + name + "; url: " + url;
    }

    //Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    //default constructor
    public RealEstate(){}
    //constructor
    public RealEstate(int id, String name, String url){
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
