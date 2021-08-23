import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    ArrayList<Double> par=new ArrayList<Double>();
    ArrayList<Double> par2=new ArrayList<Double>();
    
    public static int maxwr;
    public static int res=200;
    public static int parsizean=23;
    public static int parsizepl=16;
    public static int an;
    public static int plants;
    public static double cofic=0.2;
    public static int temp=60;
    public static Player pl;
    public static Player bot1;
    public static ForMouse fm=new ForMouse();
    public static Fon fon1;
    public static int teams;
    static int plmode;
    static GreenfootImage image=new GreenfootImage(1200, 700);
    
    
    public static Animal plan;
    
    static GreenfootImage fon=new GreenfootImage(1200,700);
    
    GreenfootImage worldFon;
    GreenfootImage worldImage;
    int x;
    int y;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1); 
        worldFon=getBackground();
        worldImage=new GreenfootImage(worldFon);
        teams=0;
        an=0;
        plants=0;
        res=200;
        plan=null;
        Fon.w=4800;
        bot1=null;
        initTime=System.currentTimeMillis();
        prepare();
    }
    GreenfootImage snow=new GreenfootImage("snow1.png");
    GreenfootImage ground=new GreenfootImage("ground1.jpg");
    GreenfootImage sand=new GreenfootImage("sand.png");
    int waterquan;
    int ts;
    public void prepare(){
        setPaintOrder(Fon.class);

        waterquan=Greenfoot.getRandomNumber(2)+1;
        for(int i=0;i<waterquan;i++){
            Water water=new Water();
            addObject(water, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight())); 
        }
        addObject(new Water(), getWidth()/2, getHeight()/2); 
        for(int i=0;i<10+Greenfoot.getRandomNumber(10)-5;i++){
            Hole h=new Hole(Greenfoot.getRandomNumber(9)+1,1);
            addObject(h, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        for(int i=0;i<MyWorld.parsizean;i++){
            par.add((double)(-1));
        }
        for(int i=0;i<MyWorld.parsizepl;i++){
            par2.add((double)(-1));
        }
        pl=new Player(1);
        addObject(pl, 600, 600);
        if(MyWorld.plmode<2){
            bot1=new Player(0);
            addObject(bot1, 600, 100);
        }
        addObject(fm, 600, 350);
        fon1=new Fon(null);
        addObject(fon1, 600, 350);
    }
    Actor crt;
    
    public static Plant observedPlant = null;
    Animal an1;
    Plant plant;
    GreenfootImage cri;
    int xc;
    int yc;
    
    int eyesize;
    GreenfootImage im;
    
    GreenfootImage eye;
    public void updateFon(){
        fon.setColor(new Color(80,80,80,255));
        fon.fill();

        if(!pl.inHole || MyWorld.plmode>0){
            fon.drawImage(getBackground(), 0, 0);
            for(int i=0;i<pl.getWRList().size();i++){
                crt=pl.getWRList().get(i);
                fon.drawImage(crt.getImage(), crt.getX()-crt.getImage().getWidth()/2, crt.getY()-crt.getImage().getHeight()/2);
            }
            for(int i=0;i<pl.getWList().size();i++){
                crt=pl.getWList().get(i);
                fon.drawImage(crt.getImage(), crt.getX()-crt.getImage().getWidth()/2, crt.getY()-crt.getImage().getHeight()/2);
            }
        }

        if(pl.inHole || MyWorld.plmode>0){
            for(int i=0;i<pl.getHRList().size();i++){
                crt=pl.getHRList().get(i);
                fon.drawImage(crt.getImage(), crt.getX()-crt.getImage().getWidth()/2, crt.getY()-crt.getImage().getHeight()/2);
            }
        }

        for(int i=0;i<pl.getHList().size();i++){
            crt=pl.getHList().get(i);
            fon.drawImage(crt.getImage(), crt.getX()-crt.getImage().getWidth()/2, crt.getY()-crt.getImage().getHeight()/2);
        }

        if(!pl.inHole || MyWorld.plmode>0){
            for(int i=0;i<pl.getDAList().size();i++){
                crt=pl.getDAList().get(i);
                fon.drawImage(crt.getImage(), crt.getX()-crt.getImage().getWidth()/2, crt.getY()-crt.getImage().getHeight()/2);
            }
        }

        for(int i=0;i<pl.getAList().size();i++){
            if(pl.getAList().get(i).inHole==pl.inHole || MyWorld.plmode>0){
                crt=pl.getAList().get(i);
                fon.drawImage(crt.getImage(), crt.getX()-crt.getImage().getWidth()/2, crt.getY()-crt.getImage().getHeight()/2);
            }
        }

        if(plmode==0){
            crt=pl;
            fon.drawImage(crt.getImage(), crt.getX()-crt.getImage().getWidth()/2, crt.getY()-crt.getImage().getHeight()/2);
            if(bot1!=null && bot1.inHole==pl.inHole){
                crt=bot1;
                fon.drawImage(crt.getImage(), crt.getX()-crt.getImage().getWidth()/2, crt.getY()-crt.getImage().getHeight()/2);
            }
        }

        fon.setColor(Color.RED);
        if(plan!=null){
            xc=plan.getX()-plan.getImage().getWidth()/2;
            yc=plan.getY()-plan.getImage().getHeight()/2;
            fon.drawRect(xc-1, yc-1 ,plan.getImage().getWidth()+1,plan.getImage().getHeight()+1);
        }
        else if (observedPlant != null){
            xc=observedPlant.getX()-observedPlant.getImage().getWidth()/2;
            yc=observedPlant.getY()-observedPlant.getImage().getHeight()/2;
            fon.drawRect(xc-1, yc-1 ,observedPlant.getImage().getWidth()+1,observedPlant.getImage().getHeight()+1);
        }
        fon1.cof=((double)Fon.w/1200);
        
        im=new GreenfootImage((int)(1200/fon1.cof), (int)(700/fon1.cof));
        im.setColor(new Color(58,171,221));
        im.fill();
        im.drawImage(fon,(im.getWidth()/2)-pl.getX(),(im.getHeight()/2)-pl.getY());
        im.scale(1200,700);
        if(plmode==0){
            crt=pl;
            eye=new GreenfootImage("eye.png");
            eyesize=(int)(crt.getImage().getWidth()*fon1.cof*0.85);
            if(eyesize<1){
                eyesize=1;
            }
            eye.scale(eyesize,eyesize);
            eye.rotate(crt.getRotation());
            eye.setTransparency(pl.t4);
            im.drawImage(eye, (crt.getImage().getWidth()%2)*(int)(fon1.cof/2)+(int)((crt.getX()-pl.getX()+(int)(600/fon1.cof))*fon1.cof)-eye.getWidth()/2, (crt.getImage().getWidth()%2)*(int)(fon1.cof/2)+(int)((crt.getY()-pl.getY()+(int)(350/fon1.cof))*fon1.cof)-eye.getHeight()/2);
            if(bot1!=null && bot1.inHole==pl.inHole){
                crt=bot1;
                eye=new GreenfootImage("eye.png");
                eyesize=(int)(crt.getImage().getWidth()*fon1.cof*0.85);
                if(eyesize<1){
                    eyesize=1;
                }
                eye.scale(eyesize,eyesize);
                eye.rotate(crt.getRotation());
                eye.setTransparency(bot1.t4);
                im.drawImage(eye, (crt.getImage().getWidth()%2)*(int)(fon1.cof/2)+(int)((crt.getX()-pl.getX()+(int)(600/fon1.cof))*fon1.cof)-eye.getWidth()/2, (crt.getImage().getWidth()%2)*(int)(fon1.cof/2)+(int)((crt.getY()-pl.getY()+(int)(350/fon1.cof))*fon1.cof)-eye.getHeight()/2);
            }
        }
        for(int i=0;i<pl.getPList().size();i++){
            if(pl.getPList().get(i).inHole==pl.inHole || MyWorld.plmode>0){
                GreenfootImage pl1=new GreenfootImage("plant1.png");
                plant=pl.getPList().get(i);
                pl1.scale((int)(plant.size3*fon1.cof),(int)(plant.size3*fon1.cof));
                pl1.setTransparency(plant.transparent);
                if(plant.anabioz) {
                    pl1.setColor(new Color(153, 217, 234, 100));
                    pl1.fillOval(0, 0, pl1.getWidth() - 1, pl1.getHeight() - 1);
                }

                im.drawImage(pl1, (plant.size3%2)*(int)((fon1.cof/2)+0.5)+(int)(0.5+(plant.getX()-pl.getX()+(int)(600/fon1.cof))*fon1.cof)-pl1.getWidth()/2, (plant.size3%2)*(int)((fon1.cof/2)+0.5)+(int)(0.5+(plant.getY()-pl.getY()+(int)(350/fon1.cof))*fon1.cof)-pl1.getHeight()/2);
            }
        }
        for(int i=0;i<pl.getAList().size();i++){
            if(pl.getAList().get(i).inHole==pl.inHole || MyWorld.plmode>0){
                an1=pl.getAList().get(i);
                eye=new GreenfootImage("eye.png");
                int eyesize;
                eyesize=(int)(an1.size*0.85*fon1.cof);
                if(eyesize<1){
                    eyesize=1;
                }
                eye.scale(eyesize,eyesize);
                eye.rotate(an1.getRotation());
                int t;
                if(an1.t4>(int)(255*an1.maskcof)){
                    t=an1.t4;
                }
                else{
                    t=(int)(255*(1-an1.maskcof));
                }
                eye.setTransparency(t);
                im.drawImage(eye, (an1.size%2)*(int)((fon1.cof/2)+0.5)+(int)(0.5+(an1.getX()-pl.getX()+(int)(600/fon1.cof))*fon1.cof)-eye.getWidth()/2, (an1.size%2)*(int)((fon1.cof/2)+0.5)+(int)(0.5+(an1.getY()-pl.getY()+(int)(350/fon1.cof))*fon1.cof)-eye.getHeight()/2);
            }
        }
        fon1.setImage1(im,image);
    }
    int t;
    int c;
    int b;
    int setmytcof;
    public void act(){
        getFPS();
        maxwr=0;

        res+=100;
        if(res>200){
            res=200;
        }

        updateFon();
    }
    int frames=49;
    int fps;
    long initTime;
    public int getFPS(){
        frames++;
        long time = System.currentTimeMillis();
        int secs = (int)((time-initTime)/1000);
        if(secs > 0){
            fps = frames/secs;
            initTime = time;
            frames = 0;
        }
        return fps;
    }
}
