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
    int satiety;
    int maxSatiety;
    
    GreenfootImage myImage;
    public DieAnimal(int size1, int satiety1, int maxSatiety1){
        size = size1;
        maxSatiety = maxSatiety1;
        satiety = satiety1;
        myImage =new GreenfootImage(size,size);
        myImage.setColor(Color.BLACK);
        myImage.fill();
        updateImage();
    }
    int start;
    public void act() 
    {
        if(start==0){
            start=1;
        }
        satiety -= 15;
        updateImage();
        remove();
    } 
    
    int size1;
    public void updateImage(){
        size1=(int)(((double) satiety / maxSatiety)*size);
        if(size1<1){
            size1=1;
        }
        myImage.scale(size1,size1);
        setImage(myImage);
    }
    
    public void remove(){
        if(satiety <=0){
            getWorld().removeObject(this);
        }
    }
}
