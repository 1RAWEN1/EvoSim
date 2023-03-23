import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Egg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Egg extends RealObject
{
    /**
     * Act - do whatever the Egg wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean inHole;
    int teamNum;
    Player myPl;
    int start;
    double predation;
    GreenfootImage image;
    int size;
    int period;
    int tim;
    int timer;
    int location;
    double liveBirth;
    ArrayList <Double> dna;

    double maskCof;

    int food;
    int water;

    boolean mutate;
    public Egg(ArrayList<Double> dna1, int location, Player pl, int tn, boolean inHole, int food, int water, boolean mutate){
        this.inHole = inHole;
        teamNum=tn;
        myPl=pl;
        this.location=location;
        dna = new ArrayList<>(dna1);
        liveBirth = dna.get(4);
        predation = dna.get(20);
        period = (int)(dna.get(10)*1);
        size = (int)(dna.get(18)*0.1);
        if(size < 1){
            size = 1;
        }

        this.mutate = mutate;

        maskCof = 0.9 / Math.pow(size, 2);
        this.food = food;
        this.water = water;
        updateImage(size, 255);
    }
    public void updateImage(int s, int t){
        image = new GreenfootImage(s, s);
        image.setColor(new Color(255, 255, 255, t));
        image.fill();
        setImage(image);
    }
    public void act() 
    {
        if(start==0){
            if(isTouching(Plant.class) && location==2){
                updateImage((int)(size *1.5), 255);
            }
            else if(location==3 || inHole){
                updateImage(size, 100);
            }
            else if(location==1){
                updateImage(size, 255);
            }
            start=1;
        }

        if(location == 2 && !isTouching(Plant.class)){
            location = 1;
            updateImage(size, 255);
        }

        timer++;
        if(timer>=50){
            timer=0;
            tim++;
        }
        if(tim>= period){
            Animal an=new Animal(dna, myPl, teamNum, inHole
                    , food, water, mutate);
            getWorld().addObject(an, getX(), getY());
            getWorld().removeObject(this);
        }// Add your action code here.
    }
}

