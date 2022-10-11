/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ia666.middlesex.lablecture4;
/**
 *
 * @author Code
 */
public class FastCar extends Car{
    
    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */
    FastCar(){
        this.racer = new Person();
        this.breakdownChance = 15;
        this.maxFuel = 50;
        this.currentFuel = maxFuel;
        this.fuelConsumption = 10;
        this.distanceDriven = 0;
        this.carSpeed = 150;
    }
}
