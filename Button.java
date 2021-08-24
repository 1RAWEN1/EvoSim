import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int type;
    public Button(int type){
        this.type=type;
        setNullImage();
    }
    public void setNullImage(){
        GreenfootImage im=new GreenfootImage(1,1);
        setImage(im);
    }
    public void act() 
    {
        if(Lobby.pressStart){
            if(type==1){
                setImage("surv.png");
            }
            else if(type==2){
                setImage("upgr.png");
            }
            else if(type==3){
                setImage("evolve.png");
            }
        }
        else{
            setNullImage();
        }
        Help h=(Help)getOneIntersectingObject(Help.class);
        if(Greenfoot.mousePressed(this) && !Lobby.train|| h!=null && Greenfoot.mousePressed(null) && Help.mInArea && h.y==getY()){
            if(h!=null){
                Lobby.slide++;
            }
            MyWorld.plMode =type-1;
            MyWorld mw=new MyWorld();
            Greenfoot.setWorld(mw);
        }
    }    
}
