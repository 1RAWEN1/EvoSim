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
    ArrayList<Double> dna = new ArrayList<>();
    ArrayList<Double> dna1 = new ArrayList<>();

    public static int res=200;
    public static final int dnaSizeOfAnimal = 23;
    public static final int dnaSizeOfPlant = 16;
    public static int an;
    public static int plants;
    public static final double cofOfEvolution = 0.2;
    public static final int temp=60;

    public static Player pl;
    public static Player bot;
    public static ForMouse fm=new ForMouse();
    public static Fon fon1;
    public static int teams;
    static int plMode;
    static GreenfootImage image=new GreenfootImage(1200, 700);
    
    
    public static Animal observedAnimal;
    
    static GreenfootImage fon=new GreenfootImage(1200,700);
    
    GreenfootImage worldFon;
    GreenfootImage worldImage;
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
        observedAnimal =null;
        Fon.w=4800;
        bot =null;
        initTime=System.currentTimeMillis();
        prepare();
    }

    GreenfootImage snow=new GreenfootImage("snow1.png");
    GreenfootImage ground=new GreenfootImage("ground1.jpg");
    GreenfootImage sand=new GreenfootImage("sand.png");
    int numOfWater;
    public void prepare(){
        setPaintOrder(Fon.class);

        numOfWater = Greenfoot.getRandomNumber(2)+1;
        for(int i = 0; i< numOfWater; i++){
            Water water=new Water();
            addObject(water, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight())); 
        }
        addObject(new Water(), getWidth()/2, getHeight()/2); 
        for(int i=0;i<10+Greenfoot.getRandomNumber(10)-5;i++){
            Hole h=new Hole(Greenfoot.getRandomNumber(9)+1,1);
            addObject(h, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
        for(int i = 0; i < MyWorld.dnaSizeOfAnimal; i++){
            dna.add((double) (-1));
        }
        for(int i=0;i<MyWorld.dnaSizeOfPlant;i++){
            dna1.add((double) (-1));
        }
        pl=new Player(1);
        addObject(pl, 600, 600);
        if(MyWorld.plMode <2){
            bot =new Player(0);
            addObject(bot, 600, 100);
        }
        addObject(fm, 600, 350);
        fon1=new Fon(null);
        addObject(fon1, 600, 350);
    }
    Actor subject;
    
    public static Plant observedPlant = null;
    Animal an1;
    Plant plant;
    int xc;
    int yc;
    
    int eyeSize;
    GreenfootImage im;
    
    GreenfootImage eye;
    public void updateFon(){
        fon.setColor(new Color(80,80,80,255));
        fon.fill();

        if(!pl.inHole || MyWorld.plMode >0){
            fon.drawImage(getBackground(), 0, 0);
            for(int i=0;i<pl.getWRList().size();i++){
                subject =pl.getWRList().get(i);
                fon.drawImage(subject.getImage(), subject.getX()- subject.getImage().getWidth()/2, subject.getY()- subject.getImage().getHeight()/2);
            }
            for(int i=0;i<pl.getWList().size();i++){
                subject =pl.getWList().get(i);
                fon.drawImage(subject.getImage(), subject.getX()- subject.getImage().getWidth()/2, subject.getY()- subject.getImage().getHeight()/2);
            }
        }

        if(pl.inHole || MyWorld.plMode >0){
            for(int i=0;i<pl.getHRList().size();i++){
                subject =pl.getHRList().get(i);
                fon.drawImage(subject.getImage(), subject.getX()- subject.getImage().getWidth()/2, subject.getY()- subject.getImage().getHeight()/2);
            }
        }

        for(int i=0;i<pl.getHList().size();i++){
            subject =pl.getHList().get(i);
            fon.drawImage(subject.getImage(), subject.getX()- subject.getImage().getWidth()/2, subject.getY()- subject.getImage().getHeight()/2);
        }

        if(!pl.inHole || MyWorld.plMode >0){
            for(int i=0;i<pl.getDAList().size();i++){
                subject =pl.getDAList().get(i);
                fon.drawImage(subject.getImage(), subject.getX()- subject.getImage().getWidth()/2, subject.getY()- subject.getImage().getHeight()/2);
            }
        }

        for(int i=0;i<pl.getAList().size();i++){
            if(pl.getAList().get(i).inHole==pl.inHole || MyWorld.plMode >0){
                subject =pl.getAList().get(i);
                fon.drawImage(subject.getImage(), subject.getX()- subject.getImage().getWidth()/2, subject.getY()- subject.getImage().getHeight()/2);
            }
        }

        if(plMode ==0){
            subject =pl;
            fon.drawImage(subject.getImage(), subject.getX()- subject.getImage().getWidth()/2, subject.getY()- subject.getImage().getHeight()/2);
            if(bot !=null && bot.inHole==pl.inHole){
                subject = bot;
                fon.drawImage(subject.getImage(), subject.getX()- subject.getImage().getWidth()/2, subject.getY()- subject.getImage().getHeight()/2);
            }
        }

        fon.setColor(Color.RED);
        if(observedAnimal !=null){
            xc= observedAnimal.getX()- observedAnimal.getImage().getWidth()/2;
            yc= observedAnimal.getY()- observedAnimal.getImage().getHeight()/2;
            fon.drawRect(xc-1, yc-1 , observedAnimal.getImage().getWidth()+1, observedAnimal.getImage().getHeight()+1);
        }
        else if (observedPlant != null){
            xc=observedPlant.getX()-observedPlant.getImage().getWidth()/2;
            yc=observedPlant.getY()-observedPlant.getImage().getHeight()/2;
            fon.drawRect(xc-1, yc-1 ,observedPlant.getImage().getWidth()+1,observedPlant.getImage().getHeight()+1);
        }
        Fon.cof = ( (double) Fon.w / 1200);
        
        im=new GreenfootImage((int) (1200 / Fon.cof), (int) (700 / Fon.cof));
        im.setColor(new Color(58,171,221));
        im.fill();
        im.drawImage(fon,(im.getWidth()/2)-pl.getX(),(im.getHeight()/2)-pl.getY());
        im.scale(1200,700);
        if(plMode ==0){
            subject =pl;
            eye=new GreenfootImage("eye.png");
            eyeSize =(int)(subject.getImage().getWidth()*Fon.cof*0.85);
            if(eyeSize <1){
                eyeSize =1;
            }
            eye.scale(eyeSize, eyeSize);
            eye.rotate(subject.getRotation());
            eye.setTransparency(pl.t4);
            im.drawImage(eye, (subject.getImage().getWidth()%2)*(int)(Fon.cof/2)+(int)((subject.getX()-pl.getX()+(int)(600/Fon.cof))*Fon.cof)-eye.getWidth()/2, (subject.getImage().getWidth()%2)*(int)(Fon.cof/2)+(int)((subject.getY()-pl.getY()+(int)(350/Fon.cof))*Fon.cof)-eye.getHeight()/2);
            if(bot !=null && bot.inHole==pl.inHole){
                subject = bot;
                eye=new GreenfootImage("eye.png");
                eyeSize =(int)(subject.getImage().getWidth()*Fon.cof*0.85);
                if(eyeSize <1){
                    eyeSize =1;
                }
                eye.scale(eyeSize, eyeSize);
                eye.rotate(subject.getRotation());
                eye.setTransparency(bot.t4);
                im.drawImage(eye, (subject.getImage().getWidth()%2)*(int)(Fon.cof/2)+(int)((subject.getX()-pl.getX()+(int)(600/Fon.cof))*Fon.cof)-eye.getWidth()/2, (subject.getImage().getWidth()%2)*(int)(Fon.cof/2)+(int)((subject.getY()-pl.getY()+(int)(350/Fon.cof))*Fon.cof)-eye.getHeight()/2);
            }
        }
        for(int i=0;i<pl.getPList().size();i++){
            if(pl.getPList().get(i).inHole==pl.inHole || MyWorld.plMode >0){
                GreenfootImage pl1=new GreenfootImage("plant1.png");
                plant=pl.getPList().get(i);
                pl1.scale((int)(plant.size *Fon.cof),(int)(plant.size *Fon.cof));
                pl1.setTransparency(plant.transparent);
                if(plant.hibernation) {
                    pl1.setColor(new Color(153, 217, 234, 100));
                    pl1.fillOval(0, 0, pl1.getWidth() - 1, pl1.getHeight() - 1);
                }

                im.drawImage(pl1, (plant.size %2)*(int)((Fon.cof/2)+0.5)+(int)(0.5+(plant.getX()-pl.getX()+(int)(600/Fon.cof))*Fon.cof)-pl1.getWidth()/2, (plant.size %2)*(int)((Fon.cof/2)+0.5)+(int)(0.5+(plant.getY()-pl.getY()+(int)(350/Fon.cof))*Fon.cof)-pl1.getHeight()/2);
            }
        }
        for(int i=0;i<pl.getAList().size();i++){
            if(pl.getAList().get(i).inHole==pl.inHole || MyWorld.plMode >0){
                an1=pl.getAList().get(i);
                eye=new GreenfootImage("eye.png");
                int eyeSize;
                eyeSize=(int)(an1.size*0.85*Fon.cof);
                if(eyeSize<1){
                    eyeSize=1;
                }
                eye.scale(eyeSize,eyeSize);
                eye.rotate(an1.r);
                int t;
                if(an1.t4>(int)(255*an1.maskCof)){
                    t=an1.t4;
                }
                else{
                    t=(int)(255*(1-an1.maskCof));
                }
                eye.setTransparency(t);
                im.drawImage(eye, (an1.size%2)*(int)((Fon.cof/2)+0.5)+(int)(0.5+(an1.getX()-pl.getX()+(int)(600/Fon.cof))*Fon.cof)-eye.getWidth()/2, (an1.size%2)*(int)((Fon.cof/2)+0.5)+(int)(0.5+(an1.getY()-pl.getY()+(int)(350/Fon.cof))*Fon.cof)-eye.getHeight()/2);
            }
        }
        fon1.setImage1(im,image);
    }

    public void act(){
        getFPS();

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
