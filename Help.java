import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Help here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Help extends Actor
{
    /**
     * Act - do whatever the Help wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    static boolean mInArea;
    int x;
    int y;
    int w;
    int h;
    
    //int[] xc=new int[8];
    //int[] yc=new int[8];
    
    double cof;
    
    GreenfootImage im=new GreenfootImage(1200,700);
    public Help(int x, int y, int w, int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        /*xc[0]=0;
        yc[0]=0;
        xc[1]=1200;
        yc[1]=0;
        xc[2]=0;
        yc[2]=700;
        xc[3]=1200;
        yc[3]=700;*/
        updateImage();
        mInArea=false;
    }
    
    double p=0.05;
    public void updateImage(){
        im.clear();
        im.setColor(new Color(0,0,0,150));
        cof+=(1-cof)*0.2;
        if(cof>0.99){
            cof=1;
        }
        /*xc[4]=(int)(x*cof);
        yc[4]=(int)(y*cof);
        xc[5]=(int)(x*cof);
        yc[5]=700-(int)((700-y)*cof);
        xc[6]=1200-(int)((1200-x)*cof);
        yc[6]=700-(int)((700-y)*cof);
        xc[7]=1200-(int)((1200-x)*cof);
        yc[7]=(int)(y*cof);*/
        //im.fillPolygon(xc,yc,8);
        im.fillRect(0,0,(int)(x*cof)-w/2,700);
        im.fillRect((int)(x*cof)-w/2,0,1200,(int)(y*cof)-h/2);
        im.fillRect(1200-(int)((1200-x)*cof)+w/2,(int)(y*cof)-h/2,1200,700);
        im.fillRect((int)(x*cof)-w/2,700-(int)((700-y)*cof)+h/2,(1200-(int)((1200-x)*cof))-(int)(x*cof)+w,700);
        MouseInfo mi=Greenfoot.getMouseInfo();
        if(mi!=null && Math.abs(mi.getX()-x)<w/2 && Math.abs(mi.getY()-y)<h/2){
            mInArea=true;
        }
        else{
            mInArea=false;
        }
        setImage(im);
    }
    public void act() 
    {
        updateImage();// Add your action code here.
    }    
}
