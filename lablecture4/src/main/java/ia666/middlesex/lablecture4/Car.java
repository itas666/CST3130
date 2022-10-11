/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ia666.middlesex.lablecture4;
/**
 *
 * @author Code
 */
public class Car {
    
    
    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */
    Person racer;
    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */
    int breakdownChance;
    
    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */
    int currentFuel, maxFuel, fuelConsumption;
    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */
    int distanceDriven, carSpeed;
    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */
    
    public void refuel(){
        this.currentFuel = this.maxFuel;
    }
    
    public boolean carBrokenDown(){
        if ( (int) (Math.random()*100) <= this.breakdownChance){
            return true;
        }
        return false;
    }
    
    public boolean advance(){
        //If we can consume fuel
        if((this.currentFuel-this.fuelConsumption) < 0){
            System.out.println("Refueling!");
            this.refuel();
            return false;
        }
        
        if(!this.racer.tick()){
            System.out.println("Driver tired, resting...!");
            this.racer.rest();
            return false;
        }
        
        if(this.carBrokenDown()){
            System.out.println("Car broken, needs to be repaired...");
            this.refuel();
            return false;
        }
        
        System.out.println("Moving car!");
        this.distanceDriven += this.carSpeed;
        this.currentFuel -= this.fuelConsumption;
        
        System.out.println("Driven " + this.distanceDriven + " so far!");
        return true;
    }
    
    public void drive(int time){
        for(int i = 0; i < time; i++){
            this.advance();
        }
        
        System.out.println("Distance driven: " + this.distanceDriven);
    }
}
