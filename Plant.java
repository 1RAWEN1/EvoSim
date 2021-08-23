import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Plant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plant extends RealObject
{
    /**
     * Act - do whatever the Plant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    ArrayList<Double> par=new ArrayList<Double>();
    ArrayList<Double> par2=new ArrayList<Double>();
    int onground=1;
    int moven;
    int dei;
    int stopp;
    int stopf;
    int r1;
    int r;
    int turntor1;
    //double prkw;
    int sop;
    int timer;
    int x;
    int y;
    int start;
    GreenfootImage image;
    
    double startx;
    double starty;
    
    //эволюционирующие показатели
    double movecof;
    double speed;
    double wspeed;
    double canclimb;
    
    int agesp;
    int myage;
    
    int mw1;
    int mw;
    int drink;
    int water2;
    int wt3;
    
    int p;
    int tim;
    
    double xich;
    int damage;
    
    double sogr;
    int tg;
    int ts;
    int needt;
    
    int age;
    
    int size;
    int size3;
    int size1;
    int xp;
    int mxp;
    
    int plod;
    int rad;
    
    int radis;
    
    int radius;
    
    double cof;
    int sit2;
    int mcolf1;
    int mcolf;
    int colfood;
    int fenergi;
    int eat;
    
    int poisondam;
    
    double anabiozcof;
    boolean anabioz;
    
    double watercof;
    
    //бары
    //Label label1;
    
    //добыча
    Animal dob;
    Plant dob1;
    
    //партнер
    Plant pl;
    
    Animal animal;
    Plant plant;
    Egg egg;
    DieAnimal food;
    
    public Plant(ArrayList<Double> par1, int food1,boolean inHole){
        this.inHole=inHole;
        if(this.inHole){
            ist=1;
        }
        par=(ArrayList<Double>) par1.clone();
        setRotation(Greenfoot.getRandomNumber(360));
        
        for(int i=0;i<par.size();i++){
            double d=par.get(i);
            if(d>=1){
                if(Greenfoot.getRandomNumber(3)+1==1){
                    d+=d*MyWorld.cofic;
                }
                else if(Greenfoot.getRandomNumber(2)+1==1){
                    d-=d*MyWorld.cofic;
                }
            }
            else if(d<1){
                if(Greenfoot.getRandomNumber(3)+1==1){
                    d+=MyWorld.cofic;
                }
                else if(Greenfoot.getRandomNumber(2)+1==1){
                    d-=MyWorld.cofic;
                }
            }
            par.set(i, d);
        }
        
        if(par.get(0)>=0){
            movecof=par.get(0);
        }
        else{
            movecof=0.0;
            par.set(0, movecof);
        }
        if(movecof>1){
            movecof=1;
        }
        else if(movecof<0){
            movecof=0;
        }
        
        if(par.get(1)>=0){
            agesp=par.get(1).intValue();
        }
        else{
            agesp=10;
            par.set(1, (double)agesp);
        }
        
        if(par.get(2)>=0){
            mw1=par.get(2).intValue();
            mw=mw1/2;
            water2=mw;
        }
        else{
            mw1=80000;
            mw=mw1/2;
            water2=mw;
            par.set(2, (double)mw);
        }
        
        if(par.get(3)>=0){
            p=par.get(3).intValue();
        }
        else{
            p=10;
            par.set(3, (double)p);
        }
        
        if(par.get(4)>=0){
            xich=par.get(4);
        }
        else{
            xich=0.0;
            par.set(4, xich);
        }
        if(xich>1){
            xich=1;
        }
        else if(xich<0){
            xich=0;
        }
        
        if(par.get(5)>=0){
            sogr=par.get(5);
        }
        else{
            sogr=0.5;
            par.set(5, sogr);
        }
        if(sogr>0.9){
            sogr=0.9;
        }
        else if(sogr<0){
            sogr=0;
        }
        
        if(par.get(6)>=0){
            age=par.get(6).intValue();
        }
        else{
            age=80;
            par.set(6, (double)age);
        }
        
        if(par.get(7)>=0){
            size=par.get(7).intValue();
            if(size<=0){
                size=1;
            }
            size3=(int)(size*0.1);
            if(size3<=0){
                size3=1;
            }
            //rost=(size-size3)/agesp;
        }
        else{
            size=4/*+Greenfoot.getRandomNumber(6)+1-3*/;
            size3=(int)(size*0.1);
            if(size3<=0){
                size3=1;
            }
            par.set(7, (double)size);
            //rost=(size-size3)/agesp;
        }
        
        if(par.get(8)>=0){
            plod=par.get(8).intValue();
        }
        else{
            plod=1;
            par.set(8, (double)plod);
        }
        
        if(par.get(9)>=0){
            radis=par.get(9).intValue();
            if(radis<=0){
                radis=1;
            }
        }
        else{
            radis=20;
            par.set(9, (double)radis);
        }
        
        if(par.get(10)>=0){
            radius=par.get(10).intValue();
        }
        else{
            radius=100;
            par.set(10, (double)radius);
        }
        
        if(par.get(11)>=0){
            cof=par.get(11);
        }
        else{
            cof=4;
            /*prok=Greenfoot.getRandomNumber(3)+1;
            if(prok==1){
                cof=cof+(cof*MyWorld.cofic);
            }
            else if(prok==2){
                cof=cof-(cof*MyWorld.cofic);
            }*/
            par.set(11, cof);
        }
        
        if(par.get(12)>=0){
            poisondam=par.get(12).intValue();
        }
        else{
            poisondam=0;
            par.set(12, (double)poisondam);
        }
        
        if(par.get(13)>=0){
            anabiozcof=par.get(13);
        }
        else{
            anabiozcof=0;
            par.set(13, anabiozcof);
        }
        if(anabiozcof<0){
            anabiozcof=0;
        }
        if(anabiozcof>1){
            anabiozcof=1;
        }
        
        if(par.get(14)>=0){
            watercof=par.get(14);
        }
        else{
            watercof=0.0;
            par.set(14, watercof);
        }
        if(watercof>1){
            watercof=1;
        }
        else if(watercof<0){
            watercof=0;
        }
        
        if(par.get(15)>=0){
            mcolf1=par.get(15).intValue();
            mcolf=mcolf1/2;
            colfood=food1;
        }
        else{
            mcolf1=size*35000;
            mcolf=mcolf1/2;
            colfood=mcolf;
            par.set(15, (double)mcolf);
        }
        
        if(movecof>=0.5){
            wspeed=(double)1/size;
        }
        if(movecof>=0.7){
            speed=(double)1/size;
        }
        if(movecof>=0.9){
            canclimb=1;
        }
        mxp=size3;
        xp=mxp;
        rad=50*size3/20;
        damage=(int)(size3*xich);
        if(damage<=0 && xich>0.3){
            damage=1;
        }
        tim=0;
        //mcolf=size3*17000;
        updateImage();
        MyWorld.plants++;
    }
    int transparent;
    public void updateImage(){
        /*image = new GreenfootImage(n, n);
        image.setColor(new Color((int)(xich*255), (int)((1-xich)*255), 0, 255));
        image.fillOval(0, 0, n, n);*/
        size3=size;
        transparent=255;
        if(agesp>myage){
            size3=(int)(size*((double)(myage)/agesp));
            mcolf=(int)((((double)(myage)/(agesp*2))+0.5)*mcolf1);
            //eat=mcolf/50;
            mw=(int)((((double)(myage)/(agesp*2))+0.5)*mw1);
        }
        if(agesp>=myage){
            mcolf=mcolf1;
            mw=mw1;
        }
        size1=size3;
        if(onground==2){
            size1=(int)(size3*1.5);
        }
        else if(onground==3 || inHole){
            transparent=100;
        }
        
        if(size3<=0){
            size3=1;
        }
        if(size1<=0){
            size1=1;
        }
        
        if(anabioz==false){
            image = new GreenfootImage("pl.png");
            image.scale(size1, size1);
            image.setTransparency(transparent);
        }
        else{
            image = new GreenfootImage(size3, size3);
            image.setColor(new Color(153, 217, 234, transparent));
            image.fillOval(0, 0, size3, size3);
        }
        //image.setColor(Color.BLACK);
        //image.drawOval(0,0,image.getWidth()-1,image.getHeight()-1);
        setImage(image);
        
        mxp=size3;
        rad=50*size3/20;
        damage=(int)(size3*xich);
        mcolf=size3*17000;
        eat=size3*700;
        drink=700*size3;
    }
    
    Water w;
    int dist;
    boolean touchWater;
    boolean candrink;
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
    
    Water w1;
    public void canDrink(){
        candrink=false;
        for(int i=0;i<getObjectsInRange(getWorld().getWidth(),Water.class).size();i++){
            w1=getObjectsInRange(getWorld().getWidth(),Water.class).get(i);
            dist=(int)Math.sqrt(Math.pow(getX()-w1.getX(),2)+Math.pow(getY()-w1.getY(),2));
            if(dist<=(w1.size/2)+(w1.size/2)*watercof && movecof<0.5){
                candrink=true;
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
        if(touchHole && ist==0 && h.loc==onground){
            /*r=getRotation();
            turnTowards(h.getX(),h.getY());
            rtoh=Math.abs(r-getRotation());
            setRotation(r);
            if(rtoh>180){
                rtoh=360-rtoh;
            }*/
            if(!inHole && h.size>size){
                inHole=true;
            }
            else if(inHole){
                inHole=false;
            }
            
            ist=1;
        }
        if(!touchHole){
            ist=0;
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
        
            if(hr!=null && inHole && hr.loc!=onground){
                onground=hr.loc;
            }
        }
    }
    
    //int seclater;
    
    public void act() 
    {
        if(start==0){
            /*if(tg==0){
                ts=(int)(((double)getX()/getWorld().getWidth())*MyWorld.temp);
                if(touchWater){
                    ts-=10;
                }
                //t1=Greenfoot.getRandomNumber(2)+1;
                /*if(t1==1){
                    needt=(int)(ts*sogr)+Greenfoot.getRandomNumber(10);
                }
                else if(t1==2){
                    needt=(int)(ts*sogr)-Greenfoot.getRandomNumber(10);
                }
                needt=36;
                tg=(int)((needt*sogr+(ts*(1-sogr))));
            }*/
            dx=getX();
            dy=getY();
            needt=36;
            start=1;
        }
        if(movecof>0.5 && anabioz==false){
            turnTo();
            move();
            touchWater();
        }
        moveBack();
        
        x=getX();
        y=getY();
        ts=(int)(((double)(x)/getWorld().getWidth())*MyWorld.temp);
        
        if(touchWater || onground==3){
            ts-=10;
        }
        anabioz();
        if(timer>=50){
            //seclater++;
            myage++;
            tim++;
            timer=0;
            if(sit2>=7 && wt3>=7 && xp<mxp && tg>=needt-10 && tg<=needt+10){
                xp++;
            }
        }
        
        /*if(seclater>=30){
        }*/
        fenergi=0;
        /*if(isTouching(Water.class) || isTouching(Ocean.class)){
            colfood-=(image.getWidth());
        }*/
        updateImage();
        if(anabioz==false && !inHole || anabioz==false && inHole && touchHole){
            fotosintez();
        }
        colfood=colfood+(int)(fenergi);
        if(colfood>mcolf){
            colfood=mcolf;
        }
        //water2=water2-(int)(size*size*6*wp);
        
        temp();
        if(myage>=agesp && sit2>=7 && wt3>=7 && anabioz==false){
            replicase();
        }
        //barMove();
        if(anabioz==false){
            drink();
            attack();
        }
        dive();
        climb();
        die();// Add your action code here.
    }  
    
    public void anabioz(){
        tg=(int)((needt*sogr+(ts*(1-sogr))));
        if(colfood>0 && colfood<=mcolf){
            sit2=colfood/(mcolf/10);
        }
        if(water2>0 && water2<=mw){
            wt3=water2/(mw/10);
        }
        if(tg<needt-10 || tg>needt+10){
            colfood=colfood-(int)(size3*size3*size3*anabiozcof);
            water2=water2-(int)(anabiozcof*size3*size3*size3);
            timer+=anabiozcof;
            anabioz=true;
        }
        else{
            colfood=colfood-(size3*size3*size3);
            water2=water2-(size3*size3*size3);
            timer++;
            anabioz=false;
        }
        water2=water2-fenergi;
    }
    
    /*public void barMove(){
        sit.setLocation(x,y-30);
        hlbr.setLocation(x,y-10);
        t.setLocation(x,y-40);
        wb.setLocation(x,y-20);
        label1.setLocation(x,y-50);
        if(mcolf>0){
            sit2=colfood/(mcolf/10);
            if(sit2<0){
                sit2=0;
            }
            if(sit2>10){
                sit2=10;
            }
        }
        if(mw>0){
            wt3=water2/(mw/10);
            if(wt3<0){
                wt3=0;
            }
            if(wt3>10){
                wt3=10;
            }
        }
        tg=(int)((needt*sogr+(ts*(1-sogr))));
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
        wb.setImage("Sit"+wt3+".png");
        
        sit.setImage("Sit"+(sit2+21)+".png");
        
        if(xp>=0){
            hlbr.setImage("XP"+(int)((double)(xp*3)/mxp)+".png"); 
        }
    }*/
    
    public void fotosintez(){
        fenergi=(int)(size3*size3*5*cof);
        MyWorld.res-=size3;
        if(plant!=null && onground!=2){
            sop=0;
            for(int i=0;i<getIntersectingObjects(Plant.class).size();i++){
                sop=sop+(int)Math.pow(getIntersectingObjects(Plant.class).get(i).size3,3);
            }
            fenergi=fenergi-sop;
        }
        water2-=fenergi;
    }
    
    int cx;
    int cy;
    
    int rtoc;
    public void replicase(){
        pl=null;
        //for(int i=0;i<getObjectsInRange(radius, Plant.class).size();i++){
            if(getObjectsInRange(radius, Plant.class).size()>0){
                pl=getObjectsInRange(radius, Plant.class).get(0);
                if(inHole==pl.inHole && tim>p/* && colfood>(mcolf/3)*2 && water2>(mw/3)*2*/ && pl.onground==onground && pl.tim>pl.p && Math.abs(pl.xich-xich)<=0.1){
                    //break;
                }
                else{
                    pl=null;
                }
            }
        //}
        if(pl!=null){
            for(int i=0; i<plod; i++){
                //double xich1,double sogr1, int age1, int size1, int plod1, int tg1
                par2.clear();
                for(int i1=0;i1<par.size();i1++){
                    if(Greenfoot.getRandomNumber(2)==1){
                        par2.add(par.get(i1));
                    }
                    else{
                        par2.add(pl.par.get(i1));
                    }
                }
                Plant an=new Plant(par2,size*size*size*1000,inHole);
                cx=((x+pl.getX())/2)+Greenfoot.getRandomNumber(radis)-radis/2;
                cy=((y+pl.getY())/2)+Greenfoot.getRandomNumber(radis)-radis/2;
                if(inHole){
                    inHole();
                    if(hr!=null && Math.sqrt(Math.pow(hr.getX()-cx,2)+Math.pow(hr.getY()-cy,2))>hr.size/2){
                        startx=getX();
                        starty=getY();
                        setLocation(hr.getX(),hr.getY());
                        turnTowards(cx,cy);
                        rtoc=getRotation();
                        setRotation(r);
                        cx=hr.getX()+(int)(Math.cos(Math.toRadians(rtoc))*((hr.size/2)-1));
                        cy=hr.getY()+(int)(Math.sin(Math.toRadians(rtoc))*((hr.size/2)-1));
                    }
                }
                try{
                    getWorld().addObject(an, cx, cy);
                }catch(Exception e){
                    getWorld().addObject(an, x+Greenfoot.getRandomNumber(radis)-radis/2, y+Greenfoot.getRandomNumber(radis)-radis/2);
                }
                colfood=colfood-size*size*size*1000;
                water2=water2-size*size*size*1000;
                tim=0;
            }
        }
        else if(tim>p*2/* && water2>(mw/3)*2 && colfood>(mcolf/3)*2*/){
            for(int i=0; i<plod; i++){
                //double xich1,double sogr1, int age1, int size1, int plod1, int tg1
                par2.clear();
                for(int i1=0;i1<par.size();i1++){
                    par2.add(par.get(i1));
                }
                Plant an=new Plant(par2, size*size*size*1000,inHole);
                cx=x+Greenfoot.getRandomNumber(radis)-radis/2;
                cy=y+Greenfoot.getRandomNumber(radis)-radis/2;
                if(inHole){
                    inHole();
                    if(hr!=null && Math.sqrt(Math.pow(hr.getX()-cx,2)+Math.pow(hr.getY()-cy,2))>hr.size/2){
                        startx=getX();
                        starty=getY();
                        setLocation(hr.getX(),hr.getY());
                        turnTowards(cx,cy);
                        rtoc=getRotation();
                        setRotation(r);
                        cx=hr.getX()+(int)(Math.cos(Math.toRadians(rtoc))*((hr.size/2)-1));
                        cy=hr.getY()+(int)(Math.sin(Math.toRadians(rtoc))*((hr.size/2)-1));
                    }
                }
                getWorld().addObject(an, cx, cy);
                colfood=colfood-size*size*size*1000;
                water2=water2-size*size*size*1000;
                tim=0;
            }
        }
    }
    
    public void turnTo(){
        animal=null;
        food=null;
        if(getObjectsInRange(radius, Animal.class).size()>0){
            animal=getObjectsInRange(radius, Animal.class).get(0);
            if(animal.inHole!=inHole){
                animal=null;
            }
        }
        if(getObjectsInRange(radius, DieAnimal.class).size()>0 && !inHole){
            food=getObjectsInRange(radius, DieAnimal.class).get(0);
        }
        if(inHole && !touchHole && getObjectsInRange((size+2)*2,Hole.class).size()>0){
            turnTowards(getObjectsInRange((size+2)*2,Hole.class).get(0).getX(), getObjectsInRange((size+2)*2,Hole.class).get(0).getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(isAtEdge()){
            turnTowards(getWorld().getWidth()/2, getWorld().getHeight()/2);
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(getObjectsInRange(radius, Water.class).size()>0 && wt3<7){
            turnTowards(getObjectsInRange(radius, Water.class).get(0).getX(), getObjectsInRange(radius, Water.class).get(0).getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(xich>0.3 && food!=null && sit2<7){
            turnTowards(food.getX(), food.getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(xich>0.3 && animal!=null && sit2<7){
            turnTowards(animal.x, animal.y);
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(xich>0.3 && egg!=null && sit2<7){
            r=getRotation();
            turnTowards(egg.getX(), egg.getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(xich<=0.3 && animal!=null && animal.xich<0.7){
            turnTowards(animal.x, animal.y);
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
    
    public void drink(){
        canDrink();
        if(candrink && water2<mw && onground!=2 || water2<mw&& onground==3){
            water2=water2+drink;
        }
    }
    
    public void attack(){
        plant=(Plant)getOneIntersectingObject(Plant.class);
        animal=(Animal)getOneIntersectingObject(Animal.class);
        egg=(Egg)getOneIntersectingObject(Egg.class);
        food=(DieAnimal)getOneIntersectingObject(DieAnimal.class);
        
        if(inHole){
            food=null;
        }
        if(plant!=null && inHole!=plant.inHole){
            plant=null;
        }
        if(animal!=null && inHole!=animal.inHole){
            animal=null;
        }
        if(egg!=null && inHole!=egg.inHole){
            egg=null;
        }
        
        dob=null;
        dob1=null;
        if(animal!=null && xich>0.3){
            dob=animal;
            if(dob.onground==onground){
                dob.xp-=((damage+poisondam)*(1-dob.protection));
                dob.xichpl=this;
            }
        }
        
        if(food!=null && xich>0.3 && colfood<mcolf && onground==1){
            colfood=colfood+eat;
            food.sit-=eat;
            if(sit2<9){
                stopp=1;
            }
        }
        /*else if(tPl!=null && xich<0.7){
            dob1=tPl;
            if(dob1.onground==onground && colfood<mcolf){
                colfood+=eat;
                dob1.colfood-=eat;
                xp-=dob1.poisondam;
                stopp=1;
            }
        }*/
    }
    
    public void temp(){
        tg=(int)((needt*sogr+(ts*(1-sogr))));
        colfood-=(int)(Math.abs(ts-needt)*sogr*size3);
        water2=water2-(int)(Math.abs(ts-needt)*sogr*size3);
    }
    
    int isturn;
    int rotspeed;
    public void move(){
        startx=dx;
        starty=dy;
        if(moven>0 && dei==0){
            if(!touchWater){
                dmove(-speed);
            }
            else if(touchWater){
                dmove(-wspeed);
            }
            if(moven>0){
                moven=moven-1;
            }
            dei=1;
        }
        if(!touchWater && !inHole|| onground==1){
            rotspeed=(int)(speed*size3*15);
        }
        else if(touchWater && !inHole|| onground==3){
            rotspeed=(int)(wspeed*size3*15);
        }
        if(r-(rotspeed-1)>r1|| r1>r+(rotspeed-1)){
            if(turntor1==1 && r1>r){
                r=r+rotspeed;
                if(r1-r>180){
                    r=r-(rotspeed*2);
                }
                setRotation(r);
                colfood-=rotspeed;
                r=getRotation();
                isturn=1;
            }
            if(turntor1==1 && r1<r){
                r=r-rotspeed;
                if(r-r1>180){
                    r=r+(rotspeed*2);  
                }
                setRotation(r);
                colfood-=rotspeed;
                r=getRotation();
                isturn=1;
            }
            if(r-(rotspeed-1)<=r1 || r1<r+(rotspeed-1)){
                r=r1;
                turntor1=0;
            }
        }
        if(dei==0 && stopp==0 && !inHole || inHole && isturn==0 && dei==0 && stopp==0){
            if(!touchWater && !inHole || onground==1){
                dmove(speed*size3);
                colfood-=(int)(speed*size);
                water2-=(int)(speed*size);
            }
            else if(touchWater && !inHole || onground==3){
                dmove(wspeed*size3);
                colfood-=(int)(wspeed*size);
                water2-=(int)(wspeed*size);
            }
            dei=1;
        }
        isturn=0;
        stopp=0;
        stopf=0;
        dei=0;
    }
    
    double dx;
    double dy;
    public void dmove(double v){
        dx+=v*Math.cos(Math.toRadians(getRotation()));
        dy+=v*Math.sin(Math.toRadians(getRotation()));
        
        if(dx<0){
            dx=0;
        }
        if(dy<0){
            dy=0;
        }
        if((int)dx>getWorld().getWidth()){
            dx=getWorld().getWidth();
        }
        if((int)dy>getWorld().getHeight()){
            dy=getWorld().getHeight();
        }
        setLocation((int)dx,(int)dy);
    }
    
    public void moveBack(){
        inHole();
        
        if(movecof>0.5){
            if(touchWater && wspeed==0 && !inHole || inHole && !touchHR && onground==1 ||
            !touchWater && !inHole && speed==0 || inHole && !touchHR && onground==3){
                dx=startx;
                dy=starty;
                setLocation((int)startx,(int)starty);
            }
        }
        
        touchHole();
    }
    
    public void dive1(){
        if(touchWater && onground==1){
            onground=3;
        }
    }
    public void up(){
        if(onground==3 && !inHole){
            onground=1;
        }
    }
    
    public void dive(){
        touchWater();
        if(anabioz==false){
            if(onground==1 || dob!=null && dob.onground==3 && onground==1 || dob1!=null && dob1.onground==3 && onground==1){
                dive1();
            }
            if(dob!=null && dob.onground==1 && onground==3 || dob1!=null && dob1.onground==1 && onground==3){
                up();
            }
        }
        
        if(!touchWater && onground==3){
            up();
        }
    }
    
    public void climb(){
        if(movecof>0.5 && onground==1 && anabioz==false && !inHole){
            if(plant!=null && plant.onground==1 && plant.size>size && onground==1){
                onground=2;
            }
        }
        if(onground==2 && plant==null){
            onground=1;
        }
    }
    
    public void die(){
        if(water2<=0){
            xp--;
        }
        if(colfood<=0){
            xp--;
        }
        if(myage>age){
            xp=0;
        }
        if(xp<=0){
            MyWorld.plants--;
            getWorld().removeObject(this);
        }
    }
}
