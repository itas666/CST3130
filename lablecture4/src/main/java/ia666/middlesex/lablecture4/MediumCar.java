/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ia666.middlesex.lablecture4;
/**
 *
 * @author Code
 */
public class MediumCar extends Car{
    
    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */
    MediumCar(){
        this.racer = new Person();
        this.breakdownChance = 10;
        this.maxFuel = 100;
        this.currentFuel = maxFuel;
        this.fuelConsumption = 20;
        this.distanceDriven = 0;
        this.carSpeed = 130;
    }
}
