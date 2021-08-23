import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DieAnimal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DieAnimal extends RealObject
{
    /**
     * Act - do whatever the DieAnimal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int size;
    int sit;
    int msit;
    
    GreenfootImage myimage;
    public DieAnimal(int size1, int sit1, int msit1){
        size=size1;
        msit=size*60000;
        sit=msit;
        myimage=new GreenfootImage(size,size);
        myimage.setColor(Color.BLACK);
        myimage.fill();
        updateImage();
    }
    int start;
    public void act() 
    {
        if(start==0){
            start=1;
        }
        sit-=100;
        updateImage();
        remove();// Add your action code here.
    } 
    
    int size1;
    public void updateImage(){
        size1=(int)(((double)sit/msit)*size);
        if(size1<1){
            size1=1;
        }
        myimage.scale(size1,size1);
        setImage(myimage);
    }
    
    public void remove(){
        if(sit<=0){
            getWorld().removeObject(this);
        }
    }
}
