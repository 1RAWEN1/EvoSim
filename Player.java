import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

import java.io.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends RealObject
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    ArrayList<Double> dna;
    
    ArrayList<Double> plantDna =new ArrayList<>();
    
    int teamNum;
    
    int newSelection;
    int[] parameters=new int[6];
    public Player(int botOrNo1){
        btoOrNo = botOrNo1;
        dna = new ArrayList<>();
        for(int i=0;i<MyWorld.dnaSizeOfAnimal;i++){
            dna.add(-1.0);
        }
        
        for(int i=0;i<MyWorld.dnaSizeOfPlant;i++){
            plantDna.add(-1.0);
        }
        setRotation(Greenfoot.getRandomNumber(360));
        
        size=3;
        
        sogr=0.5;
        radius=200;
        speed=1;
        teamNum=MyWorld.teams;
        MyWorld.teams++;
        if(MyWorld.plMode >0){
            speed=10;
        }
        updateImage(size,255);
    }
    public void updateImage(int size1, int t4){
        image = new GreenfootImage(size1, size1);
        setImage(image);
    }
    int btoOrNo;
    int myAn;
    
    int timer1;
    
    int selectionOfSuper;
    
    int elixir;
    int pushQ;
    public void act() 
    {
        timer1++;
        if(timer1>50){
            if(elixir<10){
                elixir++;
            }
            timer1=0;
        }
        if(MyWorld.plMode ==2){
            elixir=10;
        }
        if(myAn<4 && MyWorld.plMode <2 || MyWorld.plMode ==2){
            replicase();
        }
        if(myAn>0){
            newSelect();
        }
        if(btoOrNo ==1){
            if(Greenfoot.isKeyDown("Backspace") && MyWorld.plMode ==2){
                if(Lobby.slide==6){
                    Lobby.slide++;
                }
                Greenfoot.setWorld(new Lobby());
            }
            if(MyWorld.plMode ==0){
                act2();
            }
            else{
                temp1();
                plmove();
            }
            if(Greenfoot.isKeyDown(Lobby.buttons.get(1)) && elixir>=7 && pushQ ==0 && can==0 || btoOrNo ==0 && elixir>=7 && Greenfoot.getRandomNumber(10)==0 ||  btoOrNo ==0 && elixir==10){
                elixir-=7;
                can=1;
            }
            if(!Greenfoot.isKeyDown(Lobby.buttons.get(1))){
                pushQ =0;
            }
            if(can==1 && newSelection ==0){
                if(Greenfoot.isKeyDown(Lobby.buttons.get(2))){
                    selectionOfSuper =1;
                }
                else if(Greenfoot.isKeyDown(Lobby.buttons.get(3))){
                    selectionOfSuper =2;
                }
                else if(Greenfoot.isKeyDown(Lobby.buttons.get(4))){
                    selectionOfSuper =3;
                }
                if(Greenfoot.isKeyDown(Lobby.buttons.get(1)) && selectionOfSuper !=0){
                    mysuper(selectionOfSuper);
                    if(selectionOfSuper ==1 && Lobby.slide==3){
                        Lobby.slide++;
                    }
                    selectionOfSuper =0;
                    pushQ =1;
                }
            }
        }
        else if(btoOrNo ==0){
            if(MyWorld.plMode ==0){
                act1();
            }
            else{
                temp1();
                botturnTo();
                botmove();
            }
            if(newSelection ==0 && can==1){
                mysuper(1);
            }
        }
    }

    int selection;
    
    int epush;
    
    double val=0.5;
    public void newSelect(){
        if(Greenfoot.isKeyDown(Lobby.buttons.get(0)) && elixir>=5 && newSelection ==0 && btoOrNo ==1 && epush==0 || btoOrNo ==0 && elixir>=5 && Greenfoot.getRandomNumber(100)==0){
            elixir-=5;
            for(int i=0;i<6;i+=2){
                parameters[i]=Greenfoot.getRandomNumber(22);
                parameters[i+1]=Greenfoot.getRandomNumber(2);
            }
            newSelection =1;
            selection=0;
            val=0.5;
            if(btoOrNo ==0){
                selection=Greenfoot.getRandomNumber(3);
                updateAnimals(parameters[selection*2],parameters[(selection*2)+1],val);
                newSelection =0;
            }
        }
        if(!Greenfoot.isKeyDown(Lobby.buttons.get(0))){
            epush=0;
        }
        if(btoOrNo ==1){
            if(Greenfoot.isKeyDown(Lobby.buttons.get(2))){
                selection=1;
            }
            else if(Greenfoot.isKeyDown(Lobby.buttons.get(3))){
                selection=2;
            }
            else if(Greenfoot.isKeyDown(Lobby.buttons.get(4))){
                selection=3;
            }
            
            if(Greenfoot.isKeyDown(Lobby.buttons.get(5))){
                val+=0.01;
            }
            else if(Greenfoot.isKeyDown(Lobby.buttons.get(6))){
                val-=0.01;
            }
            
            if(val>1){
                val=1;
            }
            else if(val<0){
                val=0;
            }
            
            if(Greenfoot.isKeyDown(Lobby.buttons.get(0)) && selection!=0){
                updateAnimals(parameters[(selection-1)*2],parameters[((selection-1)*2)+1],val);
                if(Lobby.slide==4){
                    Lobby.slide++;
                }
                newSelection =0;
                epush=1;
            }
        }
    }
    double evolve;
    Animal an;
    public void updateAnimals(int num, int dob, double val1){
        if(dob==1){
            evolve=val1;
        }
        else{
            evolve=-val1;
        }
        for(int i=0;i<getObjectsInRange(100, Animal.class).size();i++){
            an=getObjectsInRange(100, Animal.class).get(i);
            if(an.teamNum==teamNum){
                if(an.dna.get(num)>1){
                    an.dna.set(num,an.dna.get(num)+evolve*an.dna.get(num));
                }
                else if(an.dna.get(num)<1){
                    an.dna.set(num,an.dna.get(num)+evolve);
                    if(an.dna.get(num)<0){
                        an.dna.set(num,0.0);
                    }
                    if(an.dna.get(num)>1){
                        an.dna.set(num,1.0);
                    }
                }
            }
        }
    }
    
    public void updateAnimals1(int num, int dob){
        if(dob==1){
            evolve=0.3;
        }
        else{
            evolve=-0.3;
        }
        for(int i=0;i<getObjectsInRange(100, Animal.class).size();i++){
            an=getObjectsInRange(100, Animal.class).get(i);
            if(an.dna.get(num)>1){
                an.dna.set(num,an.dna.get(num)+evolve*an.dna.get(num));
            }
            else if(an.dna.get(num)<=1){
                an.dna.set(num,an.dna.get(num)+evolve);
            }
        }
    }
    Plant pl;
    int can;
    public void mysuper(int sel){
        can=0;
        if(sel==1){
            for(int i=0;i<3;i++){
                pl=new Plant(plantDna,0, 0, false);
                pl.myAge =pl.ageFodGrow;
                pl.satiety =pl.maxSatiety1;
                getWorld().addObject(pl, getX() + Greenfoot.getRandomNumber(50)-50/2, getY() + Greenfoot.getRandomNumber(50)-50/2);
            }
        }
        else if(sel==2){
            for(int i=0;i<getObjectsInRange(30, Animal.class).size();i++){
                an=getObjectsInRange(30, Animal.class).get(i);
                if(an.teamNum==teamNum){
                    an.myAge =an.ageForGrow;
                }
            }
        }
        else if(sel==3){
            updateAnimals1(Greenfoot.getRandomNumber(MyWorld.dnaSizeOfAnimal),Greenfoot.getRandomNumber(2));
            updateAnimals1(Greenfoot.getRandomNumber(MyWorld.dnaSizeOfAnimal),Greenfoot.getRandomNumber(2));
        }
    }
    double sogr2;
    int i;
    public void temp1(){
        ts=(int)(((double)(getX())/getWorld().getWidth())*MyWorld.temp);
        touchWater();
        if(touchWater){
            ts-=10;
        }
        i=getObjectsInRange(radius,Animal.class).size();
        if(i>0){
            sogr2=getObjectsInRange(radius,Animal.class).get(0).heatCof;
        }
        else if(i==0){
            sogr2=sogr;
        }
        tg=(int)((needt*sogr2+(ts*(1-sogr2))));
    }
    public void replicase(){
        if(Greenfoot.isKeyDown("Space") && btoOrNo ==1 || btoOrNo ==0){
            if(MyWorld.plMode <2){
                for(int i=0; i<4; i++){
                    //int age1, int plod1, int speed1, int radius1, int size1, double sogr1, double xich1, int tg1, int msit1
                    Animal an=new Animal(dna, this, teamNum, inHole, 0, 0);
                    getWorld().addObject(an, getX(), getY());
                }
                if(btoOrNo ==1 && Lobby.slide==8 && Lobby.train){
                    Lobby.train=false;
                    save();
                }
            }
            else{
                Animal an=new Animal(dna, this, 0, false, 0, 0);
                getWorld().addObject(an, getX(), getY());
                touchWater();
                if(touchWater && Lobby.slide==2){
                    Lobby.slide++;
                }
            }
        }
    }
    
    Ser ser;
    public void save(){
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("save.ser"));
            
            ser=new Ser();
            ser.save(Lobby.train);
            os.writeObject(ser);
            
            os.close();
        }catch(Exception e){System.out.println(e);}
    }
    
    public void plmove(){
        if(Greenfoot.isKeyDown("S")){
            setLocation(getX(), getY()+speed);
        }
        if(Greenfoot.isKeyDown("A")){
            setLocation(getX()-speed, getY());
        }
        if(Greenfoot.isKeyDown("D")){
            setLocation(getX()+speed, getY());
        }
        if(Greenfoot.isKeyDown("W")){
            setLocation(getX(), getY()-speed);
        }
    }
    int movetox;
    int movetoy;
    public void botturnTo(){
        if(tg<needt-10 || tg>needt+10){
            movetox=getWorld().getWidth()/2;
            movetoy=getWorld().getHeight()/2;
        }
        else if(getObjectsInRange(radius, Plant.class).size()>0){
            movetox=getObjectsInRange(radius, Plant.class).get(0).getX();
            movetoy=getObjectsInRange(radius, Plant.class).get(0).getY();
        }
        else{
            randomMove1();
        }
    }
    public void randomMove1(){
        if(getX()<movetox+10 && getX()>movetox-10 && getY()<movetoy+10 && getY()>movetoy-10){
            movetox=Greenfoot.getRandomNumber(getWorld().getWidth());
            movetoy=Greenfoot.getRandomNumber(getWorld().getHeight());
        }
    }
    public void botmove(){
        if(movetoy<getY()){
            setLocation(getX(), getY()-speed);
        }
        if(movetoy>getY()){
            setLocation(getX(), getY()+speed);
        }
        if(movetox>getX()){
            setLocation(getX()+speed, getY());
        }
        if(movetox<getX()){
            setLocation(getX()-speed, getY());
        }
    }
    
    boolean canSee;
    int location=1;
    int stopf;
    int m;
    Actor water;
    Actor sea;
    //double lifecof;
    int r;
    int x;
    int y;
    int r1;
    int moven;
    int stopp;
    int dei;
    int turntor1;
    double timer;
    int start;
    GreenfootImage image;
    GreenfootImage fon;
            
    int rstart;
    int foodx;
    int foody;
    
    //эволюционирующие показатели
    int size=3;
    int size3;
    int mxp=100;
    int xp=mxp;
    
    int mair=3000;
    int air=mair;
    
    double sysdix=0.7;
    
    double canclimb;
    
    int pspeed;
    double flycof;
    int fly;
    
    double poicof;
    int poisondam;
    
    double maskcof;
    
    int mw=150000;
    int wt3;
    int drink;
    int water2=mw;
    
    int wspeed=1;
    
    int speed=1;
    
    int radius=200;
    
    double sogr=0.5;
    int tg;
    int ts;
    int needt=36;
    
    double xich=0.5;
    int damage=1;
    
    int msit=150000;
    int sit1=msit;
    int sit2;
    int eat;
    
    //на меня охотятся
    Plant xichpl;
    Animal xichan;
    
    Animal tAn;
    Plant tPl;
    Egg tEgg;
    DieAnimal tFood;
    
    Animal An;
    Animal AnInR;
    Animal SopInR;
    Plant PlInR;
    Egg EggInR;
    
    boolean touchWater;
    
    int t4;
    public void updateImage(){
        t4=255;
        size3=size;
        
        if(location==0){
            size3=(int)(size3*flycof);
            t4=(int)(255*(1.0/flycof));
            if(t4>255){
                t4=255;
            }
        }
        else if(location==2){
            size3=(int)(size3*1.5);
        }
        else if(location==3 || inHole){
            t4=100;
        }  
        
        if(size3<=0){
            size3=1;
        }
        image = new GreenfootImage(size3,size3);
        //image.scale(size3, size3);
        if(teamNum==0){
            image.setColor(new Color(0, 255, 255, 255));
        }
        else if(teamNum==1){
            image.setColor(new Color(255, 0, 0, 255));
        }
        else if(teamNum==2){
            image.setColor(new Color(0, 0, 255, 255));
        }
        else if(teamNum==3){
            image.setColor(new Color(255, 255, 0, 255));
        }
        else if(teamNum==4){
            image.setColor(new Color(0, 255, 255, 255));
        }
        else if(teamNum==5){
            image.setColor(new Color(0, 0, 0, 255));
        }
        image.fill();
        image.setTransparency(t4);
        setImage(image);
        
        eat=size3*700;
        drink=size3*700;
    }
    
    Water w;
    int dist;
    public void touchWater(){
        touchWater=false;
        if(!inHole){
            for(int i=0;i<getIntersectingObjects(Water.class).size();i++){
                w=getIntersectingObjects(Water.class).get(i);
                dist=(int)Math.sqrt(Math.pow(getX()-w.getX(),2)+Math.pow(getY()-w.getY(),2));
                if(dist<=(w.size/2)-(size3/2)){
                    touchWater=true;
                    break;
                }
            }
        }
    }
    
    boolean touchHole;
    Hole h;
    
    boolean inHole;
    
    int ist;
    
    public void touchHole(){
        touchHole=false;
        for(int i=0;i<getIntersectingObjects(Hole.class).size();i++){
            h=getIntersectingObjects(Hole.class).get(i);
            dist=(int)Math.sqrt(Math.pow(getX()-h.getX(),2)+Math.pow(getY()-h.getY(),2));
            if(dist<=(h.size/2)-(size3/2)){
                touchHole=true;
                break;
            }
        }
        
        if(touchHole && ist==0 && h.loc==location){
            
            if(!inHole && h.size>size && btoOrNo ==1 ||
            !inHole && h.size>size && sysdix<=0.5 && h.loc==3 && btoOrNo ==0 ||
            !inHole && h.size>size && sysdix>0.5 && h.loc==1 && btoOrNo ==0){
                inHole=true;
            }
            else if(inHole){
                inHole=false;
            }
            ist=1;
        }
        
        if(!touchHole && ist==1){
            ist=0;
        }
        
        if(h!=null && inHole && h.loc!=location){
            location=h.loc;
        }
    }
    
    boolean touchHR;
    HoleRoom hr;
    public void inHole(){
        touchHR=false;
        if(inHole){
            for(int i=0;i<getIntersectingObjects(HoleRoom.class).size();i++){
                hr=getIntersectingObjects(HoleRoom.class).get(i);
                dist=(int)Math.sqrt(Math.pow(getX()-hr.getX(),2)+Math.pow(getY()-hr.getY(),2));
                if(dist<=(hr.size/2)-(size3/2)){
                    touchHR=true;
                    break;
                }
            }
        }
    }
    
    public void act1() 
    {
        turnTo();
        move();
        touchWater();
        moveBack();
        x=getX();
        y=getY();
        r=getRotation();
        if(sit1<0){
            sit1=0;
        }
        
        ts=(int)(((double)(x)/getWorld().getWidth())*MyWorld.temp);
        if(touchWater){
            ts-=10;
        }
        if(timer>=50){
            timer=0;
            if(sit2>=7 && wt3>=7 && xp<mxp && tg>=needt-10 && tg<=needt+10){
                xp++;
            }
        }
        updateImage();
        
        temp();
        
        attack();
        drink();
        eat();
        brith();
        dive();
        
        climb();
        FlyOrNo();
        //barMove();
        die();
        // Add your action code here.
    } 
    
    public void act2() 
    {
        move1();
        touchWater();
        moveBack();
        x=getX();
        y=getY();
        r=getRotation();
        if(sit1<0){
            sit1=0;
        }
        
        ts=(int)(((double)(x)/getWorld().getWidth())*MyWorld.temp);
        if(touchWater){
            ts-=10;
        }
        if(timer>=50){
            timer=0;
            if(sit2>=7 && wt3>=7 && xp<mxp && tg>=needt-10 && tg<=needt+10){
                xp++;
            }
        }
        updateImage();
        if(sit1<0){
            sit1=0;
        }
        if(water2<0){
            water2=0;
        }
        if(sit1>msit){
            sit1=msit;
        }
        if(water2>mw){
            water2=mw;
        }
        if(sit1>0 && sit1<=msit){
            sit2=sit1/(msit/10);
        }
        if(water2>0 && water2<=mw){
            wt3=water2/(mw/10);
        }
        tPl=(Plant)getOneIntersectingObject(Plant.class);
        tAn=(Animal)getOneIntersectingObject(Animal.class);
        tEgg=(Egg)getOneIntersectingObject(Egg.class);
        tFood=(DieAnimal)getOneIntersectingObject(DieAnimal.class);
        
        temp();
        
        attack();
        drink();
        eat();
        brith();
        diveForPl();
        
        climb();
        FlyOrNo();
        die();
    }
    
    public void temp(){
        tg=(int)((needt*sogr+(ts*(1-sogr))));
        sit1=sit1-(int)(Math.abs(ts-needt)*sogr*image.getWidth());
        water2=water2-(int)(Math.abs(ts-needt)*sogr*image.getWidth());
    }
    
    DieAnimal food;
    
    public void turnTo(){
        canSee=false;
        An=null;
        AnInR=null;
        SopInR=null;
        PlInR=null;
        EggInR=null;
        food=null;
        if(getObjectsInRange(radius, Animal.class).size()>0){
            An=getObjectsInRange(radius, Animal.class).get(0);
            if(An.inHole!=inHole){
                An=null;
            }
            if(An!=null){
                if(Math.sqrt(Math.pow(getX()-An.getX(),2)+Math.pow(getY()-An.getY(),2))<radius-(int)(An.maskCof *radius)){
                    canSee=true;
                }
                else if(intersects(An)){
                    canSee=true;
                }
                if(canSee){
                    if(An.teamNum==teamNum){
                        AnInR=An;
                    }
                    else{
                        SopInR=An;
                    }
                }
            }
        }
        if(getObjectsInRange(radius, Plant.class).size()>0 && !inHole){
            PlInR=getObjectsInRange(radius, Plant.class).get(0);
        }
        if(getObjectsInRange(radius, Egg.class).size()>0){
            EggInR=getObjectsInRange(radius, Egg.class).get(0);
            if(EggInR.inHole!=inHole){
                EggInR=null;
            }
        }
        if(getObjectsInRange(radius, DieAnimal.class).size()>0 && !inHole){
            food=getObjectsInRange(radius, DieAnimal.class).get(0);
        }
        
        if(PlInR!=null){
            foodx=PlInR.getX();
            foody=PlInR.getY();
        }
        if(isAtEdge() || needt-7>tg || needt+7<tg){
            rstart=getRotation();
            if(needt-7>tg){
                r1=0;
            }
            else if(needt+7<tg){
                r1=180;
            }
            else{
                turnTowards(getWorld().getWidth()/2, getWorld().getHeight()/2);
                r1=getRotation();
            }
            setRotation(rstart);
            turntor1=1;
        }
        else if(getObjectsInRange(radius, Water.class).size()>0 && wt3<7){
            r=getRotation();
            turnTowards(getObjectsInRange(radius, Water.class).get(0).getX(), getObjectsInRange(radius, Water.class).get(0).getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(xich<0.7 && PlInR!=null && sit2<7){
            r=getRotation();
            turnTowards(PlInR.getX(), PlInR.getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(xich<0.7 && sit2<7 && foodx!=0 && foody!=0){
            r=getRotation();
            turnTowards(foodx, foody);
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(xich>0.3 && food!=null && sit2<7){
            r=getRotation();
            turnTowards(food.getX(), food.getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(xich>0.3 && EggInR!=null && sit2<7 && EggInR.teamNum!=teamNum){
            r=getRotation();
            turnTowards(EggInR.getX(), EggInR.getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(SopInR!=null && xich<=0.3 && SopInR.predation >0.3){
            r=getRotation();
            turnTowards(SopInR.x, SopInR.y);
            if(r1>=180){
                r1=getRotation()-(180-Greenfoot.getRandomNumber(90));
            }
            else if(r1<180){
                r1=getRotation()+(180-Greenfoot.getRandomNumber(90));
            }
            setRotation(r);
            turntor1=1;
        }
        else{
            randomMove();
        }
    }
    public void randomMove(){
        r1=Greenfoot.getRandomNumber(360);
        turntor1=1;
    }
    
    public void brith(){
        if(sysdix<=0.5 && location!=3 && air>0){
            air=air-(size*size*size);
        }
        else if(sysdix<=0.5 && air<mair && location==3){
            air+=(size*size*size);
            if(air>mair){
                air=mair;
            }
        }
        if(sysdix>0.5 && location==3 && air>0){
            air=air-(size*size*size);
        }
        else if(sysdix>0.5 && location!=3 && air<mair){
            air+=(size*size*size);
            if(air>mair){
                air=mair;
            }
        }
    }
    
    public void drink(){
        if(location==3 && water2<mw || touchWater && water2<mw && location==1){
            water2=water2+drink;
            if(wt3<9){
                stopp=1;
            }
        }
        
        if(water2>mw){
            water2=mw;
        }
        if(water2>0 && water2<=mw){
            wt3=water2/(mw/10);
        }
    }
    
    public void eat(){
        if(getX()>foodx-10 && getX()<foodx+10 && getY()>foody-10 && getY()<foody+10 && getObjectsInRange(radius, Plant.class).size()==0){
            foodx=0;
            foody=0;
        }
        if(tPl!=null && xich<0.7 && sit1<msit && location==2 || tPl!=null && xich<0.7 && sit1<msit && location==tPl.location){
            sit1=sit1+(int)(eat*(1-xich));
            tPl.satiety -=eat;
            xp-=tPl.poison;
            if(sit2<9){
                stopp=1;
            }
        }
        
        if(tFood!=null && xich>0.3 && sit1<msit && location==1){
            sit1=sit1+eat;
            tFood.satiety -=eat;
            if(sit2<9){
                stopp=1;
            }
        }
        
        if(sit1>msit){
            sit1=msit;
        }
        if(sit1>0 && sit1<=msit){
            sit2=sit1/(msit/10);
        }
        
    }
    
    public void attack(){
        if(xich>0.3 && tEgg!=null && tEgg.teamNum!=teamNum){
            if(sit1<msit && tEgg.image.getWidth()<=image.getWidth() && location==tEgg.location){
                sit1=sit1+(tEgg.image.getWidth()*35000);
                getWorld().removeObject(tEgg);
                tEgg=null;
            }
            else if(tEgg!=null && sit1<msit && tEgg.image.getWidth()<=image.getWidth() && location==1 && tEgg.location==3){
                dive1();
            }
            else if(tEgg!=null && sit1<msit && tEgg.image.getWidth()<=image.getWidth() && location==3 && tEgg.location==1){
                up();
            }
        }
        else{
            defance();
        }
    }
    
    public void defance(){
        if(xp<mxp && xichan!=null && location==xichan.location){
            xichan.hp -=(damage+poisondam);
        }
        if(xp<mxp && xichpl!=null && location==xichpl.location){
            xichpl.hp -=(damage+poisondam);
        }
        xichan=null;
        xichpl=null;
    }
    
    int rotspeed;
    public void move(){
        if(moven>0 && dei==0){
            if(location!=3 && pspeed>=speed && pspeed>=wspeed && sit2>5 && wt3>5 && stopf==0 || location==0){
                move(-pspeed);
                sit1=sit1-(pspeed*size);
                water2=water2-(pspeed*size);
                fly=1;
            }
            else if(!touchWater){
                move(-speed);
            }
            else if(touchWater){
                move(-wspeed);
            }
            if(moven>0){
                moven=moven-1;
            }
            dei=1;
        }
        if(fly==1){
            rotspeed=pspeed*15;
        }
        else if(!touchWater){
            rotspeed=speed*15;
        }
        else if(touchWater){
            rotspeed=wspeed*15;
        }
        if(r-(rotspeed-1)>r1|| r1>r+(rotspeed-1)){
            if(turntor1==1 && r1>r){
                r=r+rotspeed;
                if(r1-r>180){
                    r=r-(rotspeed*2);
                }
                setRotation(r);
                sit1=sit1-rotspeed;
                r=getRotation();
            }
            if(turntor1==1 && r1<r){
                r=r-rotspeed;
                if(r-r1>180){
                  r=r+(rotspeed*2);  
                }
                setRotation(r);
                sit1=sit1-rotspeed;
                r=getRotation();
            }
            if(r-(rotspeed-1)<=r1 || r1<=r+(rotspeed-1)){
                r=r1;
                turntor1=0;
            }
        }
        if(dei==0 && stopp==0){
            if(location!=3 && pspeed>=speed && pspeed>=wspeed && sit2>5 && wt3>5 && stopf==0 || location==0){
                move(pspeed);
                sit1=sit1-(pspeed*size);
                water2=water2-(pspeed*size);
                fly=1;
            }
            else if(!touchWater){
                move(speed);
            }
            else if(touchWater){
                move(wspeed);
            }
            dei=1;
        }
        stopp=0;
        stopf=0;
        dei=0;
        inHole();
    }
    
    public void move1(){
        if(moven>0 && dei==0){
            if(location!=3 && pspeed>=speed && pspeed>=wspeed && sit2>5 && wt3>5 && stopf==0 || location==0){
                move(-pspeed);
                sit1=sit1-(pspeed*size);
                water2=water2-(pspeed*size);
                fly=1;
            }
            else if(!touchWater){
                move(-speed);
            }
            else if(touchWater){
                move(-wspeed);
            }
            if(moven>0){
                moven=moven-1;
            }
            dei=1;
        }
        if(fly==1){
            rotspeed=pspeed*15;
        }
        else if(!touchWater){
            rotspeed=speed*15;
        }
        else if(touchWater){
            rotspeed=wspeed*15;
        }
        r=getRotation();
        turnTowards(MyWorld.fm.x,MyWorld.fm.y);
        r1=getRotation();
        setRotation(r);
        if(r-(rotspeed-1)>r1|| r1>r+(rotspeed-1)){
            if(r1>r){
                r=r+rotspeed;
                if(r1-r>180){
                    r=r-(rotspeed*2);
                }
                setRotation(r);
                sit1=sit1-rotspeed;
                r=getRotation();
            }
            if(r1<r){
                r=r-rotspeed;
                if(r-r1>180){
                  r=r+(rotspeed*2);  
                }
                setRotation(r);
                sit1=sit1-rotspeed;
                r=getRotation();
            }
            if(r-(rotspeed-1)<r1 || r1<=r+(rotspeed-1)){
                r=r1;
            }
        }
        if(Math.sqrt(Math.pow(getX()-MyWorld.fm.x,2)+Math.pow(getY()-MyWorld.fm.y,2))>30 && stopp==0){
            if(location!=3 && pspeed>=speed && pspeed>=wspeed && sit2>5 && wt3>5 && stopf==0 || location==0){
                move(pspeed);
                sit1=sit1-(pspeed*size);
                water2=water2-(pspeed*size);
                fly=1;
            }
            else if(!touchWater){
                move(speed);
            }
            else{
                move(wspeed);
            }
        }
        stopp=0;
        inHole();
    }
    public void moveBack(){
        if(touchWater && fly==0 && wspeed==0 && !inHole || inHole && !touchHR && location==1){
            drink();
            move(-speed);
        }
        else if(!touchWater && fly==0 && !inHole && speed==0 || inHole && !touchHR && location==3){
            move(-wspeed);
        }
        
        tPl=(Plant)getOneIntersectingObject(Plant.class);
        tAn=(Animal)getOneIntersectingObject(Animal.class);
        tEgg=(Egg)getOneIntersectingObject(Egg.class);
        tFood=(DieAnimal)getOneIntersectingObject(DieAnimal.class);
        
        if(inHole){
            tPl=null;
            tFood=null;
        }
        if(tAn!=null && inHole!=tAn.inHole){
            tAn=null;
        }
        if(tEgg!=null && inHole!=tEgg.inHole){
            tEgg=null;
        }
        
        touchHole();
    }
    
    public void dive1(){
        if(touchWater && location==1){
            location=3;
            if(air<=0 && sysdix<=0.5){
                air=1;
            }
        }
    }
    public void up(){
        if(location==3 && !inHole){
            location=1;
            if(air<=0 && sysdix>0.5){
                air=1;
            }
        }
    }
    
    public void dive(){
        touchWater();
        if(sysdix<=0.5 && location==1 || tPl!=null && xich<0.7 && sit1<msit && location==1 && tPl.location==3){
            dive1();
        }
        if(tFood!=null && xich>0.3 && sit1<msit && location==3 || !touchWater && location==3 || air<=1 && location==3 && sysdix>0.5 || tPl!=null && xich<0.7 && sit2<7 && location==3 && tPl.location==1){
            up();
        }
    }
    
    MouseInfo mi;
    int dive;
    int mp;
    public void diveForPl(){
        mi=Greenfoot.getMouseInfo();
        if(sysdix<=0.5 && location==1
        || Lobby.buttons.get(7).equals("лкм") && mi!=null && Greenfoot.mousePressed(null) && mi.getButton()==3 && dive==0 && mp==0
        || Greenfoot.isKeyDown(Lobby.buttons.get(7)) && dive==0 && mp==0){
            dive1();
            dive=1;
            mp=1;
        }
        if(air<=0 && location==3 && sysdix>0.5 || !touchWater && location==3
        || Lobby.buttons.get(7).equals("лкм") && mi!=null && Greenfoot.mousePressed(null) && mi.getButton()==3 && dive==1 && mp==0
        || Greenfoot.isKeyDown(Lobby.buttons.get(7)) && dive==0 && mp==0){
            up();
            dive=0;
            mp=1;
        }
        if(Lobby.buttons.get(7).equals("лкм") && !Greenfoot.mousePressed(null) || !Greenfoot.isKeyDown(Lobby.buttons.get(7))){
            mp=0;
        }
    }
    
    public void climb(){
        if(canclimb>0.5 && location==1){
            if(tPl!=null && tPl.location==1 && tPl.maxSize >size && location==1){
                location=2;
            }
        }
        if(location==2 && tPl==null){
            location=1;
        }
    }
    
    public void FlyOrNo(){
        if(sit2<=5 && fly==1 || wt3<=5 && fly==1){
            fly=0;
            stopf=1;
        }
        if(tPl!=null && xich<0.7 && sit2<7 && location==0){
            fly=0;
            stopf=1;
        }
        if(tAn!=null && xich>0.3 && tAn.teamNum!=teamNum){
            if(sit2<7 && tAn.location==1){
                fly=0;
                stopf=1;
            }
        }
        if(flycof>1 && fly==0){
            flycof-=0.05;
        }
        if(fly==1){
            if(flycof>=1 && flycof<2){
                flycof+=0.05;
            }
            else if(flycof<1){
                flycof=1;
            }
            if(flycof>2){
                flycof=2;
            }
        }
        if(fly==0 && flycof==1 && location==0){
            location=1;
        }
        else if(fly==1){
            location=0;
        }
    }
    
    public void die(){
        if(sit1<=0){
            xp--;
        }
        if(water2<=0){
            xp--;
        }
        if(air<=0){
            xp--;
        }
        if(needt-10>tg || needt+10<tg){
            xp--;
        }
        if(xp<=0){
            xp=0;
            MyWorld mw=(MyWorld)getWorld();
            mw.updateFon();
        } 
    }
    public ArrayList<Water> getWList(){
        return (ArrayList<Water>)getObjectsInRange(MyWorld.fon1.w, Water.class);
    }
    public ArrayList<WaterRadius> getWRList(){
        return (ArrayList<WaterRadius>)getObjectsInRange(MyWorld.fon1.w, WaterRadius.class);
    }
    public ArrayList<Animal> getAList(){
        return (ArrayList<Animal>)getObjectsInRange(MyWorld.fon1.w, Animal.class);
    }
    public ArrayList<Plant> getPList(){
        return (ArrayList<Plant>)getObjectsInRange(MyWorld.fon1.w, Plant.class);
    }
    public ArrayList<DieAnimal> getDAList(){
        return (ArrayList<DieAnimal>)getObjectsInRange(MyWorld.fon1.w, DieAnimal.class);
    }
    public ArrayList<Hole> getHList(){
        return (ArrayList<Hole>)getObjectsInRange(MyWorld.fon1.w, Hole.class);
    }
    public ArrayList<HoleRoom> getHRList(){
        return (ArrayList<HoleRoom>)getObjectsInRange(MyWorld.fon1.w, HoleRoom.class);
    }
}
