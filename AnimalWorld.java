import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;

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
    static int selectedParameter;
    
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
    private ArrayList<Double> animalDNA=new ArrayList<>();
    Label lab;
    private void prepare()
    {
        DecorAnimal myAnimal = new DecorAnimal(400);
        addObject(myAnimal,250,350);
        lab=new Label(Lobby.descriptionOfParameters.get(0),30);
        addObject(lab,800,300);
        Arrow arrow = new Arrow(true);
        addObject(arrow,800,230);
        Arrow arrow2 = new Arrow(false);
        addObject(arrow2,800,370);
        Back b=new Back();
        addObject(b,100,100);

        for(int i = 0; i < MyWorld.dnaSizeOfAnimal; i++){
            animalDNA.add(0.0);
        }

        animalDNA = new ArrayList<>(new Animal(animalDNA, MyWorld.pl, 0, false, 0, 0, false).dna);
    }
    
    public void act(){
        lab.setValue(Lobby.descriptionOfParameters.get(selectedParameter));
    }
}
