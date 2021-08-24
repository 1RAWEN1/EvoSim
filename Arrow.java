import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrow extends Actor
{
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean up;
    public Arrow(boolean up){
        this.up=up;
        if(!up){
            turn(180);
        }
    }
    public void act() 
    {
        if(Greenfoot.mousePressed(this)){
            setImage("upArrow.png");
            if(up && AnimalWorld.selectedParameter <MyWorld.dnaSizeOfAnimal-1){
                AnimalWorld.selectedParameter++;
            }
            if(!up && AnimalWorld.selectedParameter >0){
                AnimalWorld.selectedParameter--;
            }
        }
        if(Greenfoot.mouseClicked(null)){
            setImage("arrow1.png");
        }// Add your action code here.
    }    
}
