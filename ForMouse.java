import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ForMouse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ForMouse extends Actor
{
    /**
     * Act - do whatever the ForMouse wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int x;
    int y;
    public ForMouse(){
        GreenfootImage image=new GreenfootImage(10, 10);
        //image.setColor(Color.RED);
        //image.fill();
        setImage(image);
    }
    public void act() 
    {
        MouseInfo mi=Greenfoot.getMouseInfo();
        if(mi!=null && mi.getX()>=100 && mi.getX()<=1100 && MyWorld.pl.xp>0){
            x=(int)((mi.getX()-600)/Fon.cof)+MyWorld.pl.getX();
            y=(int)((mi.getY()-350)/Fon.cof)+MyWorld.pl.getY();
            setLocation(x,y);
        }

        if(Greenfoot.mousePressed(null) && isTouching(Animal.class)){
            MyWorld.observedAnimal =getIntersectingObjects(Animal.class).get(0);
        }
        else if(Greenfoot.mousePressed(null) && isTouching(Plant.class)){
            MyWorld.observedAnimal = null;

            MyWorld.observedPlant = getIntersectingObjects(Plant.class).get(0);
        }
        else if(Greenfoot.mousePressed(null) && !isTouching(Animal.class) && !isTouching(Plant.class)){
            MyWorld.observedAnimal =null;
            MyWorld.observedPlant = null;
        }
    }    
}
