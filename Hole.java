
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
    GreenfootImage h=new GreenfootImage("hole.png");
    double cof;
    public void updateImage(){
        /*im.setColor(new Color(164,102,68,255));
        im.fillOval(0,0,size-1,size-1);
        for(int i=5; i>0; i--){
            cof=0.2*i;
            int t=(int)(255*(1-cof+0.5));
            if(t>255){
                t=255;
            }
            im.setColor(new Color(0, 0, 0, t));
            im.fillOval((int)((im.getWidth()*((1-cof)/2))+0.5), (int)((im.getHeight()*((1-cof)/2))+0.5), (int)(im.getWidth()*cof)-1, (int)(im.getHeight()*cof)-1);
        }*/
        if(loc==1){
            im=new GreenfootImage("hole.png");
            im.scale(size,size);
        }
        else if(loc==3){
            im=new GreenfootImage("whole.png");
            im.scale(size,size);
            //im.setColor(new Color(0,0,255,100));
            //im.fillOval(0,0,size-1,size-1);
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
