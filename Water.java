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
    ArrayList<Double> par=new ArrayList<>();
    ArrayList<Double> par2=new ArrayList<>();
    GreenfootImage image;
    int size;
    int start;
    int surroundingTemp;
    int temp =30;
    double cof;
    int green;
    int transparency;
    int timer;
    double cof2;
    double cof3;
    int transparency1;
    int green1;
    
    int rot;
    int dist;
    public void act() 
    {
        if(start==0){
            for(int i=0;i<MyWorld.dnaSizeOfAnimal;i++){
                par.add((double)(-1));
            }
            for(int i=0;i<MyWorld.dnaSizeOfPlant;i++){
                par2.add((double)(-1));
            }
            surroundingTemp =(int)(((double)(getX())/getWorld().getWidth()*MyWorld.temp + (double)(getY())/getWorld().getHeight()*MyWorld.temp)/2);
            cof=(double)(surroundingTemp)/ temp;
            if(cof<1){
                if(cof==0){
                    cof=0.1;
                }
                size=Greenfoot.getRandomNumber((int)(getWorld().getWidth()*cof));
            }
            else if(cof>=1){
                size=Greenfoot.getRandomNumber((int)(getWorld().getWidth()/cof));
            }
            if(size<1){
                size=1;
            }
            image = new GreenfootImage(size, size);
            if(cof<1){
                green =(int)(200*(1-cof));
                transparency =50;
                image.setColor(new Color( 0, green, 255, transparency));
            }
            else if(cof>=1){
                green =70;
                transparency =(int)((30/cof));
                image.setColor(new Color( 0, green, 255, transparency));
            }
            //image.fillOval(0, 0, size, size);
            for(int i=10; i>0; i--){
                cof2=0.1*i;
                cof3=0.5-(cof2/2);
                green1 = green +(int)(green *cof2/10);
                transparency1 = transparency +(int)(transparency *cof2);
                if(green1 >255){
                    green1 =255;
                }
                if(transparency1 >255){
                    transparency1 =255;
                }
                image.setColor(new Color(0, green1, 255, transparency1));
                image.fillOval((int) (size * cof3), (int) (size * cof3), (int) (size * cof2), (int) (size * cof2));
            }
            setImage(image);
            if(MyWorld.plMode != 2){
                for(int i=0;i<10;i++){
                    getWorld().addObject(new Plant(par2,0, 0, false), getX() + Greenfoot.getRandomNumber(size)-size/2, getY() + Greenfoot.getRandomNumber(size)-size/2);
                }
            }

            start=1;
        }
        timer++;
        if(MyWorld.plants<10 && surroundingTemp >26 && !isTouching(Animal.class) && MyWorld.plMode != 2){
            for(int i=0;i<10;i++){
                rot=Greenfoot.getRandomNumber(360);
                dist=Greenfoot.getRandomNumber(size/2);
                getWorld().addObject(new Plant(par2,0, 0, false), getX() + (int)(dist*Math.cos(Math.toRadians(rot))), getY() + (int)(dist*Math.sin(Math.toRadians(rot))));
            }
        }
        else if(MyWorld.plants<8 && surroundingTemp >26 && MyWorld.plMode == 2 && Greenfoot.getRandomNumber(100)==1){
            rot=Greenfoot.getRandomNumber(360);
            dist=Greenfoot.getRandomNumber(size/2);
            getWorld().addObject(new Plant(par2,0, 0, false), getX() + (int)(dist*Math.cos(Math.toRadians(rot))), getY() + (int)(dist*Math.sin(Math.toRadians(rot))));
        }

        if(isTouching(Plant.class) && MyWorld.plMode ==2 && surroundingTemp >26 && MyWorld.pl.myAn<8 && Greenfoot.getRandomNumber(100)==1){
            rot=Greenfoot.getRandomNumber(360);
            dist=Greenfoot.getRandomNumber(size/2);
            getWorld().addObject(new Animal(par,MyWorld.pl,0,false, 0, 0), getX() + (int)(dist*Math.cos(Math.toRadians(rot))), getY() + (int)(dist*Math.sin(Math.toRadians(rot))));
        }

        if(timer==10){
            surroundingTemp =(int)((double)(getX())/getWorld().getWidth()*MyWorld.temp);
            size-=(int)(size* surroundingTemp *0.0001);
            if(size<1){
                size=1;
            }
            if((int)(size* surroundingTemp *0.0001)!=0){
                image=new GreenfootImage(size,size);
                image.clear();
                for(int i=10; i>0; i--){
                    cof2=0.1*i;
                    cof3=0.5-(cof2/2);
                    green1 = green +(int)(green *cof2/10);
                    transparency1 = transparency +(int)(transparency *cof2);
                    if(green1 >255){
                        green1 =255;
                    }
                    if(transparency1 >255){
                        transparency1 =255;
                    }
                    image.setColor(new Color(0, green1, 232, transparency1));
                    image.fillOval((int)(size*cof3), (int)(size*cof3), (int)(size*cof2), (int)(size*cof2));
                }
                setImage(image);
            }
            timer=0;
            if(size==1){
                getWorld().removeObject(this);
            }
        }
    }    
}

