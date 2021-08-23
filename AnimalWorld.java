import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AnimalWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnimalWorld extends World
{

    /**
     * Constructor for objects of class AnimalWorld.
     * 
     */
    static int par;
    
    public AnimalWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    Label lab;
    private void prepare()
    {
        DecorAnimal myAnimal = new DecorAnimal(400);
        addObject(myAnimal,250,350);
        lab=new Label(Lobby.strVal.get(0),30);
        addObject(lab,800,300);
        Arrow arrow = new Arrow(true);
        addObject(arrow,800,230);
        Arrow arrow2 = new Arrow(false);
        addObject(arrow2,800,370);
    }
    
    public void act(){
        lab.setValue(Lobby.strVal.get(par));
    }
}
