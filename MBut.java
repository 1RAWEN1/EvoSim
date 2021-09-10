import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MBut here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MBut extends Actor
{
    /**
     * Act - do whatever the MBut wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean status=true;
    public boolean getStatus(){
        return status;
    }
    public void act() 
    {
        if(Greenfoot.mousePressed(this)){
            status=!status;
            if(status){
                setImage("on.png");
            }
            else{
                setImage("off.png");
            }
        }// Add your action code here.
    }    
}
