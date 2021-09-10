import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bot extends RealObject
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    ArrayList<Double> par=new ArrayList<Double>();
    ArrayList<RealObject> cr=new ArrayList<RealObject>();
    ArrayList<Double> par2=new ArrayList<Double>();
    double poicof;
    int p;
    int age;
    int plod;
    int speed;
    int radius;
    int size;
    double sogr;
    double xich;
    int tg;
    int ts;
    int msit;
    int x;
    int y;
    int needt;
    int start;
    int tim;
    Label label1;
    GreenfootImage image;
    int rstart;
    int r;
    double sogr2;
    public Bot(){
        par=new ArrayList<Double>();
        for(int i=0;i<MyWorld.dnaSizeOfAnimal;i++){
            par.add(-1.0);
        }
        setRotation(Greenfoot.getRandomNumber(360));
        
        size=3;
        /*par.set(0, (double)size);
        
        poicof=0;
        par.set(1, poicof);
        
        p=7;
        par.set(2, (double)p);
        
        age=-1;
        par.set(3, (double)age);
        
        plod=1;
        par.set(4, (double)plod);
        
        
        par.set(5, (double)1);
        
        radius=200;
        par.set(6, (double)radius);
        
        sogr=0.3;
        par.set(7, sogr);
        
        xich=0;
        par.set(8, xich);
        
        msit=size*35000;
        par.set(9, (double)msit);
        */
        speed=10;
        //MyWorld.bot++;
        updateImage(size, 255);
    }
    public void updateImage(int size1, int t4){
        image = new GreenfootImage(size1, size1);
        //image.setColor(new Color((int)(xich*255), (int)(poicof*255), (int)((1-xich)*255), t4));
        //image.fillOval(0, 0, size1, size1);
        setImage(image);
    }
    public void act() 
    {
        if(start==0){
            if(tg==0){
                ts=(int)(((double)(getX())/getWorld().getWidth())*MyWorld.temp);
                /*if(t1==1){
                    needt=(int)(ts*sogr)+Greenfoot.getRandomNumber(10);
                }
                else if(t1==2){
                    needt=(int)(ts*sogr)-Greenfoot.getRandomNumber(10);
                }*/
                needt=36;
                tg=(int)((needt*sogr+(ts*(1-sogr))));
            }
            start=1;
        }
        x=getX();
        y=getY();
        r=getRotation();
        ts=(int)(((double)(getX())/getWorld().getWidth())*MyWorld.temp);
        /*if(speed==0 && wspeed==0 && pspeed==0){
            //sit1=sit1+(int)(image.getWidth()*image.getWidth()*image.getWidth()*fcof);
            if(isTouching(Water.class) || isTouching(Ocean.class)){
                sit1=sit1+(int)((image.getWidth()*image.getWidth()*foodcof)+(eat*foodcof));
            }
        }*/
        temp();
        if(MyWorld.an==1 && tg>=needt-10 && tg<=needt+10){
            replicase();
        }
        turnTo();
        move();
        // Add your action code here.
    }  
    /*public void barMove(){
        sit.setLocation(x,y-30);
        hlbr.setLocation(x,y-10);
        t.setLocation(x,y-40);
        wb.setLocation(x,y-20);
        label1.setLocation(x,y-50);
        if(msit>0){
            sit2=sit1/(msit/10);
        }
        if(mw>0){
            wt3=water2/(mw/10);
        }
        if(MyWorld.showbars==1){
            setImageToBar();
        }
        else if(MyWorld.showbars==0){
            sit.setImage("nblok.png");
            hlbr.setImage("nblok.png");
            t.setImage("nblok.png");
            wb.setImage("nblok.png");
            label1.setValue("");
        }
    }
    public void setImageToBar(){
        if(ts>=needt){
            if(tg>needt+10){
                t.setImage("T3.png");
            }
            else if(tg<needt-20){
                t.setImage("T0.png");
            }
            else if(tg<needt-10){
                t.setImage("T1.png");
            }
            else{
                t.setImage("T2.png");
            }
        }
        else if(ts<needt){
            if(tg>needt+10){
                t.setImage("T3.png");
            }
            else if(tg<needt-20){
                t.setImage("T0.png");
            }
            else if(tg<needt-10){
                t.setImage("T1.png");
            }
            else{
                t.setImage("T2.png");
            }
        }
        if(wt3==10){
            wb.setImage("Sit10.png"); 
        }
        if(wt3==9){
            wb.setImage("Sit9.png"); 
        }
        if(wt3==8){
            wb.setImage("Sit8.png"); 
        }
        if(wt3==7){
            wb.setImage("Sit7.png"); 
        }
        if(wt3==6){
            wb.setImage("Sit6.png"); 
        }
        if(wt3==5){
            wb.setImage("Sit5.png"); 
        }
        if(wt3==4){
            sit.setImage("Sit4.png"); 
        }
        if(wt3==3){
            wb.setImage("Sit3.png"); 
        }
        if(wt3==2){
            wb.setImage("Sit2.png"); 
        }
        if(wt3==1){
            wb.setImage("Sit1.png"); 
        }
        if(wt3==0){
            wb.setImage("Sit0.png");
        }
        if(wt3<=0){
            wb.setImage("Sit00.png");
        }
        
        
        if(sit2==10){
            sit.setImage("Sit31.png"); 
        }
        if(sit2==9){
            sit.setImage("Sit30.png"); 
        }
        if(sit2==8){
            sit.setImage("Sit29.png"); 
        }
        if(sit2==7){
            sit.setImage("Sit28.png"); 
        }
        if(sit2==6){
            sit.setImage("Sit27.png"); 
        }
        if(sit2==5){
            sit.setImage("Sit26.png"); 
        }
        if(sit2==4){
            sit.setImage("Sit25.png"); 
        }
        if(sit2==3){
            sit.setImage("Sit24.png"); 
        }
        if(sit2==2){
            sit.setImage("Sit23.png"); 
        }
        if(sit2==1){
            sit.setImage("Sit22.png"); 
        }
        if(sit2==0){
            sit.setImage("Sit21.png");
        }
        if(sit1<=0){
            sit.setImage("Sit20.png");
        }
        if((double)(xp)/(double)(mxp)<=1 && (double)(xp)/(double)(mxp)>0.3*2/1.0){
            hlbr.setImage("XP3.png");  
        }
        if((double)(xp)/(double)(mxp)<=0.3*2/1.0 && (double)(xp)/(double)(mxp)>0.3/1.0){
            hlbr.setImage("XP2.png");   
        }
        if((double)(xp)/(double)(mxp)<=0.3/1.0){
            hlbr.setImage("XP1.png");   
        }
    }*/
    int i;
    public void temp(){
        i=getObjectsInRange(radius,Animal.class).size();
        if(i>0){
            sogr2=getObjectsInRange(radius,Animal.class).get(Greenfoot.getRandomNumber(i)).heatCof;
        }
        else if(i==0){
            sogr2=sogr;
        }
        tg=(int)((needt*sogr2+(ts*(1-sogr2))));
    }
    public void replicase(){
        for(int i=0; i<4; i++){
            //int age1, int plod1, int speed1, int radius1, int size1, double sogr1, double xich1, int tg1, int msit1
            par2.clear();
            for(int i1=0;i1<par.size();i1++){
                par2.add(par.get(i1));
            }
            //Animal an=new Animal(par2);
            //getWorld().addObject(an, x, y);
            tim=0;
        }
    }
    int movetox;
    int movetoy;
    public void turnTo(){
        if(tg<needt-10 || tg>needt+10){
            movetox=getWorld().getWidth()/2;
            movetoy=getWorld().getHeight()/2;
        }
        else if(getObjectsInRange(radius, Plant.class).size()>0){
            movetox=getObjectsInRange(radius, Plant.class).get(0).getX();
            movetoy=getObjectsInRange(radius, Plant.class).get(0).getY();
        }
        else{
            randomMove();
        }
    }
    public void randomMove(){
        if(getX()<movetox+10 && getX()>movetox-10 && getY()<movetoy+10 && getY()>movetoy-10){
            movetox=Greenfoot.getRandomNumber(getWorld().getWidth());
            movetoy=Greenfoot.getRandomNumber(getWorld().getHeight());
        }
    }
    public void move(){
        if(movetoy>getY()){
            setLocation(getX(), getY()+speed);
        }
        if(movetox<getX()){
            setLocation(getX()-speed, getY());
        }
        if(movetox>getX()){
            setLocation(getX()+speed, getY());
        }
        if(movetoy<getY()){
            setLocation(getX(), getY()-speed);
        }
    }
}
