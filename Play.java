import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Play here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Play extends Actor
{
    /**
     * Act - do whatever the Play wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!Lobby.train){
            if(Greenfoot.mousePressed(this)){
                Lobby.pressStart=true;
            }
            else if(Greenfoot.mousePressed(null)){
                Lobby.pressStart=false;
            }
        }
        // Add your action code here.
    }    
}
