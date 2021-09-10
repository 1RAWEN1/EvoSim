import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Creature here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RealObject extends Actor
{
    /**
     * Act - do whatever the Creature wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void drawImage(){
        if(Math.sqrt(Math.pow(getX()-MyWorld.pl.getX(),2)+Math.pow(getY()-MyWorld.pl.getY(),2))
        <(Fon.cof*700/4)+(getImage().getWidth()*1.2/2)){
            MyWorld.fon.drawImage(getImage(), getX()-getImage().getWidth()/2, getY()-getImage().getHeight()/2);
        }
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
