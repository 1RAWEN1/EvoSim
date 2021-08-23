import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyAnimal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyAnimal extends Actor
{
    /**
     * Act - do whatever the MyAnimal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int size=12;
    GreenfootImage eye;
    int r;
    
    public MyAnimal(){
        updateImage();
    }
    public void updateImage(){
        GreenfootImage im=new GreenfootImage(size,size);
        im.setColor(new Color(0, 255, 255, 255));
        im.fill();
        eye=new GreenfootImage("eye.png");
        eye.scale(10,10);
        MouseInfo mi=Greenfoot.getMouseInfo();
        if(mi!=null){
            turnTowards(mi.getX(),mi.getY());
            r=getRotation();
            setRotation(0);
        }
        eye.rotate(r);
        im.drawImage(eye,1,1);
        setImage(im);
    }
    public void act() 
    {
        updateImage();// Add your action code here.
    }    
}
