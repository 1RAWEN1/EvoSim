import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HoleRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HoleRoom extends RealObject
{
    /**
     * Act - do whatever the HoleRoom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int loc;
    int size;
    GreenfootImage im;
    Hole myH;
    public HoleRoom(int size, int loc, Hole myH){
        this.size=size;
        this.loc=loc;
        this.myH=myH;
        updateImage();
    }
    int start;
    public void act() 
    {
        if(start==0){
            start=1;
        }
        if(loc!=myH.loc){
            loc=myH.loc;
            updateImage();
        }
        // Add your action code here.
    }  
    
    public void updateImage(){
        im=new GreenfootImage(size,size);
        im.clear();
        if(MyWorld.plMode ==0){
            im.setColor(new Color(132,102,47,255));
            im.fillOval(0,0,size-1,size-1);
        }
        else{
            im.setColor(new Color(132,102,47,100));
            im.fillOval(0,0,size-1,size-1);
        }
        if(loc==3){
            im.setColor(new Color(0,0,255,100));
            im.fillOval(0,0,size-1,size-1);
        }
        setImage(im);
    }
}
