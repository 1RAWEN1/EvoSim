import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DecorAnimal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DecorAnimal extends Actor
{
    /**
     * Act - do whatever the DecorAnimal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int size=12;
    
    GreenfootImage eye;
    
    int r;
    
    int r1;
    int rotspeed=15;
    int xbarier;
    public DecorAnimal(int xbarier){
        this.xbarier=xbarier;
        updateImage();
    }
    public void updateImage(){
        GreenfootImage im=new GreenfootImage(size,size);
        im.setColor(new Color(0, 255, 255, 255));
        im.fill();
        eye=new GreenfootImage("eye.png");
        eye.scale(10,10);
        eye.rotate(r);
        im.drawImage(eye,1,1);
        setImage(im);
    }
    public void act() 
    {
        randomMove();
        if(isAtEdge() || getX()>xbarier){
            turnTowards(xbarier/2,getWorld().getHeight()/2);
            r1=getRotation();
            setRotation(0);
        }
        if(r-(rotspeed-1)>r1|| r1>r+(rotspeed-1)){
            if(r1>r){
                r=r+rotspeed;
                if(r1-r>180){
                    r=r-(rotspeed*2);
                }
            }
            if(r1<r){
                r=r-rotspeed;
                if(r-r1>180){
                    r=r+(rotspeed*2);  
                }
            }
            if(r-(rotspeed-1)<=r1 || r1<r+(rotspeed-1)){
                r=r1;
            }
        }
        setRotation(r);
        r=getRotation();
        move(1);
        setRotation(0);
        updateImage();// Add your action code here.
    }  
    
    public void randomMove(){
        r1=r+Greenfoot.getRandomNumber(31)-15;
    }
}
