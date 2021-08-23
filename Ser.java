import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
/**
 * Write a description of class Ser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ser extends Actor implements Serializable
{
    /**
     * Act - do whatever the Ser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean train;
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void save(boolean b){
        train=b;
    }
}
