
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hole here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hole extends RealObject
{
    /**
     * Act - do whatever the Hole wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int loc;
    int size;
    GreenfootImage im;
    public Hole(int size, int loc){
        this.loc=loc;
        this.size=size;
        updateImage();
    }
    int start;
    public void act() 
    {
        if(start==0){
            getWorld().addObject(new HoleRoom(size*2, loc,this),getX(),getY());
            start=1;
        }
        
        touchWater();
        
        if(touchWater && loc!=3){
            loc=3;
            updateImage();
        }
        else if(!touchWater && loc!=1){
            loc=1;
            updateImage();
        }
        // Add your action code here.
    }

    public void updateImage(){
        if(loc==1){
            im=new GreenfootImage("hole.png");
            im.scale(size,size);
        }
        else if(loc==3){
            im=new GreenfootImage("whole.png");
            im.scale(size,size);
        }
        setImage(im);
    }
    
    boolean touchWater;
    Water w;
    int dist;
    public void touchWater(){
        touchWater=false;
        for(int i=0;i<getIntersectingObjects(Water.class).size();i++){
            w=getIntersectingObjects(Water.class).get(i);
            dist=(int)Math.sqrt(Math.pow(getX()-w.getX(),2)+Math.pow(getY()-w.getY(),2));
            if(dist<=(w.size/2)-(size/2)){
                touchWater=true;
                break;
            }
        }
    }
}
