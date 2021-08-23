import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Water here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Water extends RealObject
{
    /**
     * Act - do whatever the Water wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    ArrayList<Double> par=new ArrayList<Double>();
    ArrayList<Double> par2=new ArrayList<Double>();
    GreenfootImage image;
    int size;
    int start;
    int ts;
    int tg=30;
    double cof;
    int isp;
    int g;
    int b;
    int t;
    int timer;
    double cof2;
    double cof3;
    int t1;
    int g1;
    
    int rot;
    int dist;
    public void act() 
    {
        if(start==0){
            for(int i=0;i<MyWorld.parsizean;i++){
                par.add((double)(-1));
            }
            for(int i=0;i<MyWorld.parsizepl;i++){
                par2.add((double)(-1));
            }
            ts=(int)(((double)(getX())/getWorld().getWidth()*MyWorld.temp + (double)(getY())/getWorld().getHeight()*MyWorld.temp)/2);
            cof=(double)(ts)/tg;
            if(cof<1){
                if(cof==0){
                    cof=0.1;
                }
                size=Greenfoot.getRandomNumber((int)(getWorld().getWidth()*cof));
            }
            else if(cof>=1){
                size=Greenfoot.getRandomNumber((int)(getWorld().getWidth()/cof));
            }
            if(size<0){
                size=1;
            }
            image = new GreenfootImage(size, size);
            if(cof<1){
                g=(int)(200*(1-cof));
                t=50;
                image.setColor(new Color( 0, g, 255, t));
            }
            else if(cof>=1){
                g=70;
                t=(int)((30/cof));
                image.setColor(new Color( 0, g, 255, t));
            }
            //image.fillOval(0, 0, size, size);
            for(int i=10; i>0; i--){
                cof2=0.1*i;
                cof3=0.5-(cof2/2);
                g1=g+(int)(g*cof2/10);
                t1=t+(int)(t*cof2);
                if(g1>255){
                    g1=255;
                }
                if(t1>255){
                    t1=255;
                }
                image.setColor(new Color(0, g1, 255, t1));
                image.fillOval(0+(int)(size*cof3), 0+(int)(size*cof3), (int)(size*cof2), (int)(size*cof2));
            }
            setImage(image);
            if(MyWorld.plmode != 2){
                for(int i=0;i<10;i++){
                    getWorld().addObject(new Plant(par2,0,false), getX() + Greenfoot.getRandomNumber(size)-size/2, getY() + Greenfoot.getRandomNumber(size)-size/2);
                }
            }

            start=1;
        }
        try{
            timer++;
            if(MyWorld.plants<10 && ts>26 && !isTouching(Animal.class) && MyWorld.plmode != 2){
                for(int i=0;i<10;i++){
                    rot=Greenfoot.getRandomNumber(360);
                    dist=Greenfoot.getRandomNumber(size/2);
                    getWorld().addObject(new Plant(par2,0,false), getX() + (int)(dist*Math.cos(Math.toRadians(Greenfoot.getRandomNumber(rot)))), getY() + (int)(dist*Math.sin(Math.toRadians(Greenfoot.getRandomNumber(rot)))));
                }
            }
            else if(MyWorld.plants<8 && ts>26 && MyWorld.plmode == 2 && Greenfoot.getRandomNumber(100)==1){
                rot=Greenfoot.getRandomNumber(360);
                dist=Greenfoot.getRandomNumber(size/2);
                getWorld().addObject(new Plant(par2,0,false), getX() + (int)(dist*Math.cos(Math.toRadians(Greenfoot.getRandomNumber(rot)))), getY() + (int)(dist*Math.sin(Math.toRadians(Greenfoot.getRandomNumber(rot)))));
            }

            if(isTouching(Plant.class) && MyWorld.plmode==2 && ts>26 && MyWorld.pl.myAn<8 && Greenfoot.getRandomNumber(100)==1){
                rot=Greenfoot.getRandomNumber(360);
                dist=Greenfoot.getRandomNumber(size/2);
                getWorld().addObject(new Animal(par,MyWorld.pl,0,false,false), getX() + (int)(dist*Math.cos(Math.toRadians(Greenfoot.getRandomNumber(rot)))), getY() + (int)(dist*Math.sin(Math.toRadians(Greenfoot.getRandomNumber(rot)))));
            }

            if(timer==10){
                ts=(int)((double)(getX())/getWorld().getWidth()*MyWorld.temp);
                size-=(int)(size*ts*0.0001);
                if(size<=0){
                    size=1;
                }
                if((int)(size*ts*0.0001)!=0){
                    image=new GreenfootImage(size,size);
                    image.clear();
                    for(int i=10; i>0; i--){
                        cof2=0.1*i;
                        cof3=0.5-(cof2/2);
                        g1=g+(int)(g*cof2/10);
                        t1=t+(int)(t*cof2);
                        if(g1>255){
                            g1=255;
                        }
                        if(t1>255){
                            t1=255;
                        }
                        image.setColor(new Color(0, g1, 232, t1));
                        image.fillOval(0+(int)(size*cof3), 0+(int)(size*cof3), (int)(size*cof2), (int)(size*cof2));
                    }
                    setImage(image);
                }
                timer=0;
                if(size==1){
                    getWorld().removeObject(this);
                }
            }
        }catch(Exception e){}
    }    
}

