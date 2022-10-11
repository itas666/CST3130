/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ia666.middlesex.lablecture4;

/**
 *
 * @author Code
 */
public class Person {
    

    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */
    int breakInterval;

    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */    int timeRacingSinceLastBreak;
    
    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */
    Person(){
        this.breakInterval = 5;
        this.timeRacingSinceLastBreak = 0;
    }
    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */
    Person(int restInterval){
        this.breakInterval = restInterval;
        this.timeRacingSinceLastBreak = 0;
    }
    
    /**
     * Default constructor will assign a default time to rest of 5
     and time spent racing 0 as it should be when created
     */
    public boolean tick(){
        if(this.timeRacingSinceLastBreak >= this.breakInterval){
            return false;
        }
        this.timeRacingSinceLastBreak++;
        return true;
    }
    
    public void rest(){
        this.timeRacingSinceLastBreak = 0;
    }
}
