import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WaterRadius here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WaterRadius extends Actor
{
    /**
     * Act - do whatever the WaterRadius wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int size;
    public WaterRadius(int size){
        updateImage(size);
    }
    public void act() 
    {
        // Add your action code here.
    }  
    
    GreenfootImage im;
    public void updateImage(int size){
        if(this.size!=size && im==null){
            this.size=size;
            GreenfootImage im=new GreenfootImage(size*2,size*2);
            im.setColor(new Color(0,0,0,60));
            im.fillOval(0,0,im.getWidth(),im.getHeight());
            setImage(im);
        }
        else if(this.size!=size){
            this.size=size;
            im.scale(size*2,size*2);
            setImage(im);
        }
    }
}
