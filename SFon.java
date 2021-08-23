import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SFon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SFon extends Actor
{
    /**
     * Act - do whatever the SFon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Label lab;
    public SFon(){
        updateImage();
    }
    public void updateImage(){
        GreenfootImage im=new GreenfootImage(500,200);
        im.setColor(Color.BLACK);
        im.drawRect(0,0,250,199);
        im.drawRect(250,0,249,199);
        im.drawLine(0,30,499,30);
        im.scale(1000,400);
        lab=new Label("Управление",40);
        im.drawImage(lab.updateImage4(),150,10);
        lab.setValue("Звук");
        im.drawImage(lab.updateImage4(),720,10);
        setImage(im);
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
