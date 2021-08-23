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
    double xich;
    GreenfootImage image;
    int size3;
    int per;
    int tim;
    int timer;
    int onground;
    double livebirth;
    double movecof;
    boolean evolve;
    ArrayList <Double> par=new ArrayList<Double>();
    public Egg(ArrayList<Double> par1, int onground, Player pl, int tn, boolean inHole, double movecof, boolean evolve){
        this.evolve=evolve;
        this.movecof=movecof;
        teamNum=tn;
        myPl=pl;
        this.onground=onground;
        setRotation(Greenfoot.getRandomNumber(360));
        par=(ArrayList<Double>) par1.clone();
        livebirth=par1.get(4);
        xich=par.get(20);
        per=(int)(par.get(10)*1);
        size3=(int)(par.get(18)*0.1);
        if(size3<=0){
            size3=1;
        }
        updateImage(size3, 255);
    }
    public void updateImage(int s, int t){
        image = new GreenfootImage(s, s);
        image.setColor(new Color(255, 255, 255, t));
        image.fillOval(0, 0, s, s);
        setImage(image);
    }
    public void act() 
    {
        if(start==0){
            if(isTouching(Plant.class) && onground==2){
                updateImage((int)(size3*1.5), 255);
            }
            else if(onground==1){
                updateImage(size3, 255);
            }
            else if(onground==3){
                updateImage(size3, 100);
            }
            start=1;
        }
        timer++;
        if(timer>=50){
            timer=0;
            tim++;
        }
        if(tim>=per){
            Animal an=new Animal(par, myPl, teamNum, inHole, evolve);
            getWorld().addObject(an, getX(), getY());
            getWorld().removeObject(this);
        }// Add your action code here.
    }    
}

