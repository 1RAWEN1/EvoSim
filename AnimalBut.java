import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AnimalBut here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnimalBut extends Actor
{
    /**
     * Act - do whatever the AnimalBut wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mousePressed(this) && !Lobby.train){
            Greenfoot.setWorld(new AnimalWorld());
        }// Add your action code here.
    }    
}
