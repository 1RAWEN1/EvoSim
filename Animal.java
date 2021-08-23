import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Animal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animal extends RealObject
{
    /**
     * Act - do whatever the Animal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Player myPl;
    int teamNum;
    boolean canSee;
    ArrayList<Double> par;
    ArrayList<Double> par2=new ArrayList<Double>();
    int onground=1;
    int stopf;
    Actor water;
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
            
    double startx;
    double starty;
    
    int foodx;
    int foody;
    
    //эволюционирующие показатели
    int msize;
    int size;
    int size1;
    int mxp;
    int xp;
    
    int mair;
    int air;
    
    double sysdix;
    
    double canclimb;
    
    double anabiozcof;
    boolean anabioz;
    
    double livebirth;
    
    int agesp;
    int myage;
    
    double pspeed;
    double flycof;
    int fly;
    
    double poicof;
    int poisondam;
    
    double maskcof;
    
    int mw1;
    int mw;
    int wt3;
    int drink;
    int water2;
    
    int p;
    int tim;
    int pol;
    
    //int rad;
    double protection;
    
    double wspeed;
    
    double movecof;
    
    int age;
    
    int plod;
    
    double speed;
    
    int radius;
    
    double sogr;
    int tg;
    int ts;
    int needt=36;
    
    double xich;
    int damage;
    
    int msit1;
    int msit;
    int sit1;
    int sit2;
    int eat;
    
    double dspeed;
    
    //партнер
    Animal an1;
    
    //на меня охотятся
    Plant xichpl;
    Animal xichan;
    
    //добыча
    Animal dob;
    
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
    
    int radis;
    
    public Animal(ArrayList<Double> par1, Player pl, int tn, boolean inHole, int food1, int water1){
        this.inHole=inHole;
        if(this.inHole){
            ist=1;
        }
        setRotation(Greenfoot.getRandomNumber(360));
        r=getRotation();
        teamNum=tn;
        myPl=pl;
        par=(ArrayList<Double>) par1.clone();
        for(int i=0;i<par.size();i++){
            double d=par.get(i);
            if(d>1){
                if(Greenfoot.getRandomNumber(3)+1==1){
                    d+=d*MyWorld.cofic;
                }
                else if(Greenfoot.getRandomNumber(2)+1==1){
                    d-=d*MyWorld.cofic;
                }
            }
            else{
                if(Greenfoot.getRandomNumber(3)+1==1){
                    d+=MyWorld.cofic;
                }
                else if(Greenfoot.getRandomNumber(2)+1==1){
                    d-=MyWorld.cofic;
                }
            }
            par.set(i, d);
        }
        
        if(par.get(18)>=0){
            msize=par.get(18).intValue();
            if(msize<=0){
                msize=1;
            }
            size=(int)(msize*0.1);
            if(size<=0){
                size=1;
            }
        }
        else{
            msize=3;
            size=(int)(msize*0.1);
            if(size<=0){
                size=1;
            }
            par.set(18, (double)msize);
        }
        radis=2*msize;
        fon=new GreenfootImage(msize, msize);
        
        if(par.get(0)>=0){
            mair=par.get(0).intValue();
            air=mair;
        }
        else{
            mair=msize*1000;
            air=mair;
            par.set(0, (double)mair);
        }
        
        if(par.get(1)>=0){
            sysdix=par.get(1);
        }
        else{
            sysdix=0.0;
            par.set(1, sysdix);
        }
        if(sysdix>1){
            sysdix=1;
        }
        else if(sysdix<0){
            sysdix=0;
        }
        
        if(par.get(2)>=0){
            canclimb=par.get(2);
        }
        else{
            canclimb=0.0;
            par.set(2, canclimb);
        }
        if(canclimb>1){
            canclimb=1;
        }
        else if(canclimb<0){
            canclimb=0;
        }
        
        pol=Greenfoot.getRandomNumber(2);
        
        if(par.get(3)>=0){
            anabiozcof=par.get(3);
        }
        else{
            anabiozcof=1;
            par.set(3, anabiozcof);
        }
        if(anabiozcof<0){
            anabiozcof=0;
        }
        if(anabiozcof>1){
            anabiozcof=1;
        }
        
        if(par.get(4)>=0){
            livebirth=par.get(4);
        }
        else{
            livebirth=1;
            par.set(4, livebirth);
        }
        if(livebirth>1){
            livebirth=1;
        }
        else if(livebirth<0){
            livebirth=0;
        }
        
        if(par.get(5)>=0){
            agesp=par.get(5).intValue();
        }
        else{
            agesp=10;
            par.set(5, (double)agesp);
        }
        
        if(par.get(6)>=0){
            pspeed=par.get(6);
        }
        else{
            pspeed=0;
            par.set(6, pspeed);
        }
        
        if(par.get(7)>=0){
            poisondam=par.get(7).intValue();
        }
        else{
            poisondam=0;
            par.set(7, (double)poisondam);
        }
        
        if(par.get(8)>=0){
            maskcof=par.get(8);
        }
        else{
            maskcof=0.0;
            par.set(8, maskcof);
        }
        if(maskcof>1){
            maskcof=1;
        }
        else if(maskcof<0){
            maskcof=0;
        }
        
        if(par.get(9)>=0){
            mw1=par.get(9).intValue();
            mw=mw1/2;
            water2=water1;
        }
        else{
            mw1=msize*35000;
            mw=mw1/2;
            water2=mw;
            par.set(9, (double)mw);
        }
        
        if(par.get(10)>=0){
            p=par.get(10).intValue();
        }
        else{
            p=6;
            par.set(10, (double)p);
        }
        
        if(par.get(11)>=0){
            protection=par.get(11);
        }
        else{
            protection=0;
            par.set(11, protection);
        }
        if(protection>1){
            protection=1;
        }
        if(protection<0){
            protection=0;
        }
        
        if(par.get(12)>=0){
            wspeed=par.get(12);
        }
        else{
            wspeed=(double) 1 / msize;
            par.set(12, wspeed);
        }
        
        if(par.get(13)>=0){
            movecof=par.get(13);
        }
        else{
            movecof=0.0;
            par.set(13, movecof);
        }
        if(movecof>1.0){
            movecof=1.0;
        }
        else if(movecof<0){
            movecof=0;
        }
        
        if(par.get(14)>=0){
            age=par.get(14).intValue();
        }
        else{
            age=70;
            par.set(14, (double)age);
        }
        
        if(par.get(15)>0){
            plod=par.get(15).intValue();
        }
        else{
            plod=1;
            par.set(15, (double)plod);
        }
        
        if(par.get(16)>=0){
            speed=par.get(16);
        }
        else{
            speed=0;
            par.set(16, speed);
        }
        
        if(par.get(17)>=0){
            radius=par.get(17).intValue();
        }
        else{
            radius=100;
            par.set(17, (double)radius);
        }
        
        if(par.get(19)>=0){
            sogr=par.get(19);
        }
        else{
            sogr=0.5;
            par.set(19, sogr);
        }
        if(sogr>0.9){
            sogr=0.9;
        }
        else if(sogr<0){
            sogr=0;
        }
        
        if(par.get(20)>=0){
            xich=par.get(20);
        }
        else{
            if(MyWorld.plmode<2){
                xich=0.0;
            }
            else{
                xich = Greenfoot.getRandomNumber(4)==1 ? 0.7 : 0;
            }
            par.set(20, xich);
        }
        if(xich>1){
            xich=1;
        }
        else if(xich<0){
            xich=0;
        }
        
        if(par.get(21)>0){
            msit1=par.get(21).intValue();
            msit=msit1/2;
            sit1=food1;
        }
        else{
            msit1=msize*35000;
            msit=msit1/2;
            sit1=msit;
            par.set(21, (double)msit);
        }
        
        if(par.get(22)>0){
            dspeed=par.get(22);
        }
        else{
            dspeed=0.0;
            par.set(22, dspeed);
        }
        
        onground=1;
        mxp=size;
        xp=mxp;
        tim=0;
        damage=(int)(size*xich);
        if(damage<=1 && xich>=0.3){
            damage=1;
        }
        updateImage();
        myPl.myAn++;
    }
    int t4;
    double bcof;
    public void updateImage(){
        t4=255;
        size=msize;
        if(agesp>myage){
            size=(int)(msize*((double)(myage)/agesp));
            msit=(int)((((double)(myage)/(agesp*2))+0.5)*msit1);
            mw=(int)((((double)(myage)/(agesp*2))+0.5)*mw1);
        }
        if(agesp>=myage){
            msit=msit1;
            mw=mw1;
        }
        size1=size;
        if(onground==0){
            size1=(int)(size*flycof);
            t4=(int)(255*(1.0/flycof));
            if(t4>255){
                t4=255;
            }
        }
        else if(onground==2){
            size1=(int)(size*1.5);
        }
        else if(onground==3 || inHole){
            t4=100;
        }  
        
        if(size<=0){
            size=1;
        }
        if(size1<=0){
            size1=1;
        }
        image = new GreenfootImage(size,size);
        poicof=(double)poisondam/size;
        if(poicof>1){
            poicof=1;
        }
        
        bcof=poicof;
        if(poicof<xich){
            bcof=xich;
        }
        if(!anabioz){
            if(MyWorld.plmode<2 || MyWorld.plan!=null && MyWorld.plmode==2){
                if(MyWorld.plan!=null && MyWorld.plmode==2){
                    if(Math.abs(MyWorld.plan.xich-xich) <= MyWorld.cofic){
                        teamNum=0;
                    }
                    else{
                        teamNum=1;
                    }
                }
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
                if(MyWorld.plmode==2){
                    teamNum=0;
                }
            }
            else{
                image.setColor(new Color((int)(xich*255), (((int)(55*poicof)+(int)((1-bcof)*255))/255)*255, (int)((1-bcof)*255), 255));
            }
        }
        else{
            image.setColor(new Color(153, 217, 234, 255));
        }
        image.fill();
        fon.setTransparency((int)(255*(maskcof)));
        image.drawImage(fon,0,0);
        image.setTransparency(t4);
        image.scale(size1,size1);
        setImage(image);
        
        mxp=size;
        damage=(int)(size*xich);
        if(damage<=0 && xich>0.3){
            damage=1;
        }
        eat=size*700;
        drink=size*700;
    }
    
    Water w;
    int dist;

    private int waterX = 0;
    private int waterY = 0;
    public void touchWater(){
        touchWater=false;
        if(!inHole){
            for(int i=0;i<getIntersectingObjects(Water.class).size();i++){
                w=getIntersectingObjects(Water.class).get(i);
                dist=(int)Math.sqrt(Math.pow(getX()-w.getX(),2)+Math.pow(getY()-w.getY(),2));
                if(dist<=(w.size/2)-(size/2)){
                    waterX = w.getX();
                    waterY = w.getY();

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
            if(dist<=(h.size/2)-(size/2)){
                touchHole=true;
                break;
            }
        }
        
        if(touchHole && ist==0 && h.loc==onground){
            if(!inHole && h.size>msize && sysdix<=0.5 && h.loc==3 || !inHole && h.size>msize && sysdix>0.5 && h.loc==1){
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
                if(dist<=(hr.size/2)-(size/2)){
                    touchHR=true;
                    break;
                }
            }
        
            if(hr!=null && inHole && hr.loc!=onground){
                onground=hr.loc;
            }
        }
    }
    
    int digtimer;
    
    public void dig(){
        if(getObjectsInRange(radius,Hole.class).size()==0 && digtimer==0 && sit2>5 && wt3>5 && onground!=0 && onground!=2 && dspeed>=1){
            digtimer=(int)(Math.pow((msize+2)*2,2)/(size*size*dspeed));
        }
        if(timer==0 && digtimer>0){
            digtimer--;
            if(digtimer==0){
                getWorld().addObject(new Hole(msize+2,onground),getX(),getY());
            }
        }
    }
    
    public void act() 
    {
        if(start==0){
            fon.drawImage(getWorld().getBackground(),-getX(),-getY());
            dx=getX();
            dy=getY();
            start=1;
        }
        if(!anabioz){
            turnTo();
            move();
            touchWater();
            moveBack();
        }
        x=getX();
        y=getY();
        r=getRotation();
        if(sit1<0){
            sit1=0;
        }
        
        ts=(int)(((double)(x)/getWorld().getWidth())*MyWorld.temp);
        if(touchWater || onground==3){
            ts-=10;
        }
        anabioz();
        if(digtimer>0){
            stopp=1;
        }
        if(timer>=50){
            timer=0;
            myage++;
            tim++;
            if(sit2>=7 && wt3>=7 && xp<mxp && tg>=needt-10 && tg<=needt+10){
                xp++;
            }
        }
        updateImage();
        
        temp();
        
        if(!anabioz){
            attack();
            if(onground!=0 && myage>=agesp && sit2>=7 && wt3>=7){
                replicase();
            }
            drink();
            eat();
            dig();
        }
        
        brith();
        dive();
        climb();
        FlyOrNo();
        die();
    }  
    
    public void anabioz(){
        tg=(int)((needt*sogr+(ts*(1-sogr))));
        if(tg<needt-10 || tg>needt+10){
            sit1=sit1-(int)(anabiozcof*size*size*size);
            water2=water2-(int)(anabiozcof*size*size*size);
            timer+=anabiozcof;
            anabioz=true;
        }
        else{
            sit1=sit1-(size*size*size);
            water2=water2-(size*size*size);
            timer++;
            anabioz=false;
        }
    }
    
    public void temp(){
        tg=(int)((needt*sogr+(ts*(1-sogr))));
        sit1=sit1-(int)(Math.abs(ts-needt)*sogr*size);
        water2=water2-(int)(Math.abs(ts-needt)*sogr*size);
    }
    
    int cx;
    int cy;
    
    int rtoc;
    public void replicase(){
        if(speed==0 && wspeed==0 && pspeed==0){
            water=getOneIntersectingObject(Water.class);
            if(tim>p*2){
                for(int i=0; i<plod; i++){
                    par2.clear();
                    par2.addAll(par);
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
                    if(livebirth>=0.5){
                        Animal an=new Animal(par2, myPl, teamNum, inHole
                                , (int)(msize*msize*msize*1000*livebirth), (int)(msize*msize*msize*1000*livebirth));
                        getWorld().addObject(an, cx, cy);
                    }
                    else{
                        Egg an=new Egg(par2, onground, myPl, teamNum, inHole, movecof);
                        getWorld().addObject(an, cx, cy);
                    }
                    sit1=sit1-(int)(msize*msize*msize*1000*livebirth);
                    water2=water2-(int)(msize*msize*msize*1000*livebirth);
                    tim=0;
                }
            }
        }
        else{
            if(tim>p && tAn!=null && tAn.teamNum==teamNum && !tAn.anabioz && tAn.onground==onground && tAn.tim>tAn.p){
                for(int i=0; i<plod; i++){
                    an1=tAn;
                    par2.clear();
                    for(int i1=0;i1<par.size();i1++){
                        if(Greenfoot.getRandomNumber(2)==1){
                            par2.add(par.get(i1));
                        }
                        else{
                            par2.add(an1.par.get(i1));
                        }
                    }
                    if(livebirth>=0.5){
                        Animal an=new Animal(par2, myPl, teamNum, inHole
                                , (int)(msize*msize*msize*1000*livebirth), (int)(msize*msize*msize*1000*livebirth));
                        getWorld().addObject(an, x, y);
                    }
                    else{
                        Egg an=new Egg(par2, onground, myPl, teamNum, inHole, movecof);
                        getWorld().addObject(an, x, y);
                    }
                    sit1=sit1-(int)(msize*msize*msize*1000*livebirth);
                    water2=water2-(int)(msize*msize*msize*1000*livebirth);

                    tim=0;
                }
            }
            else if(tim>p*2){
                for(int i=0; i<plod; i++){
                    par2.clear();
                    par2.addAll(par);
                    if(livebirth>=0.5){
                        Animal an=new Animal(par2, myPl, teamNum, inHole
                                , (int)(msize*msize*msize*1000*livebirth), (int)(msize*msize*msize*1000*livebirth));
                        getWorld().addObject(an, x, y);
                    }
                    else{
                        Egg an=new Egg(par2, onground, myPl, teamNum,inHole, movecof);
                        getWorld().addObject(an, x, y);
                    }
                    sit1=sit1-(int)(msize*msize*msize*1000*livebirth);
                    water2=water2-(int)(msize*msize*msize*1000*livebirth);
                    tim=0;
                }
            }
        }
    }
    
    DieAnimal food;
    
    Player pl1;
    double d1;
    
    Player pl;
    double d;
    public void getPlInRange(){
        pl=null;
        if(MyWorld.plmode==0){
            d=radius+1;
            for(int i=0;i<getObjectsInRange(radius,Player.class).size();i++){
                pl1=getObjectsInRange(radius,Player.class).get(i);
                d1=Math.sqrt(Math.pow(getX()-pl1.getX(),2)+Math.pow(getY()-pl1.getY(),2));
                if(pl1.teamNum!=teamNum && pl1.inHole==inHole && d1<d){
                    d=d1;
                    pl=pl1;
                }
                if(pl1.teamNum!=teamNum && pl1.inHole==inHole && intersects(pl1)){
                    pl=pl1;
                    break;
                }
            }
        }
    }

    public void turnTo(){
        An=null;
        AnInR=null;
        SopInR=null;
        PlInR=null;
        EggInR=null;
        food=null;
        if(MyWorld.plmode==2){
            teamNum=0;
        }
        if(getObjectsInRange(radius, Animal.class).size()>0){
            for(Animal animalInRange : getObjectsInRange(radius, Animal.class)) {
                An = animalInRange;
                if (An.inHole != inHole) {
                    An = null;
                }
                else {
                    canSee=false;
                    if (MyWorld.plmode == 2) {
                        if (Math.abs(An.xich - xich) <= MyWorld.cofic) {
                            An.teamNum = 0;
                        } else {
                            An.teamNum = 1;
                        }
                    }
                    if(AnInR == null && An.teamNum == teamNum) {
                        if (Math.sqrt(Math.pow(getX() - An.getX(), 2) + Math.pow(getY() - An.getY(), 2)) < radius - (int) (An.maskcof * radius)) {
                            canSee = true;
                        } else if (intersects(An)) {
                            canSee = true;
                        }
                        if (canSee) {
                            AnInR = An;
                        }
                    }
                    else if(SopInR == null && An.teamNum != teamNum) {
                        if (Math.sqrt(Math.pow(getX() - An.getX(), 2) + Math.pow(getY() - An.getY(), 2)) < radius - (int) (An.maskcof * radius)) {
                            canSee = true;
                        } else if (intersects(An)) {
                            canSee = true;
                        }
                        if (canSee) {
                            SopInR = An;
                        }
                    }
                }

                if(AnInR != null && SopInR != null){
                    break;
                }
            }
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
        
        getPlInRange();
        
        if(PlInR!=null && xich<0.7){
            foodx=PlInR.getX();
            foody=PlInR.getY();
        }
        else if(food != null && xich > 0.3){
            foodx = food.getX();
            foody = food.getY();
        }

        if(inHole && !touchHole && getObjectsInRange((msize+2)*2,Hole.class).size()>0){
            turnTowards(getObjectsInRange((msize+2)*2,Hole.class).get(0).getX(), getObjectsInRange((msize+2)*2,Hole.class).get(0).getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(MyWorld.plmode<2 && Math.sqrt(Math.pow(myPl.getX()-getX(),2)+Math.pow(myPl.getY()-getY(),2))>radius){
            turnTowards(myPl.getX(), myPl.getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(isAtEdge() || needt-9>tg || needt+9<tg){
            if(needt-9>tg){
                r1=0;
            }
            else if(needt+9<tg){
                r1=180;
            }
            else{
                turnTowards(getWorld().getWidth()/2, getWorld().getHeight()/2);
                r1=getRotation();
            }
            setRotation(r);
            turntor1=1;
        }
        else if(SopInR!=null && xich<=0.3 && SopInR.xich>0.3){
            /*if(getObjectsInRange(radius,Hole.class).size() > 0 && SopInR.size>size){
                turnTowards(getObjectsInRange(radius,Hole.class).get(0).getX(), getObjectsInRange(radius,Hole.class).get(0).getY());
                r1=getRotation();
            }
            else{*/
                r=getRotation();
                turnTowards(SopInR.x, SopInR.y);
                if(r1>=180){
                    r1=getRotation()-(180-Greenfoot.getRandomNumber(30));
                }
                else{
                    r1=getRotation()+(180-Greenfoot.getRandomNumber(30));
                }
            //}

            setRotation(r);
            turntor1 = 1;
        }
        else if(getObjectsInRange(radius, Water.class).size()>0 && wt3<7
                || getObjectsInRange(radius, Water.class).size() > 0 && (double)air / mair <= 0.5 && sysdix <= 0.5){
            turnTowards(getObjectsInRange(radius, Water.class).get(0).getX(), getObjectsInRange(radius, Water.class).get(0).getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(wt3 < 7 && waterX != 0 && waterY != 0
                ||  waterX != 0 && waterY != 0 && (double)air / mair <= 0.5 && sysdix <= 0.5){
            turnTowards(waterX, waterY);
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(xich<0.7 && sit2<7){
            for (Plant plant : getObjectsInRange(radius, Plant.class)){
                PlInR = plant;
                if (PlInR.inHole != inHole) {
                    PlInR = null;
                }

                if(plant.tg !=26 && plant.tg != 46) {
                    break;
                }
            }
            if(PlInR != null) {
                turnTowards(PlInR.getX(), PlInR.getY());
                r1 = getRotation();
                setRotation(r);
                turntor1 = 1;
            }
        }
        else if(sit2<7 && foodx!=0 && foody!=0){
            turnTowards(foodx, foody);
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
        else if(SopInR!=null && xich>0.3 && MyWorld.plmode<2 || SopInR!=null && xich>0.3 && MyWorld.plmode==2 && sit2<7){
            turnTowards(SopInR.x, SopInR.y);
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
       else if(pl!=null && xich>0.3){
            turnTowards(pl.getX(), pl.getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(xich>0.3 && EggInR!=null && sit2<7 && EggInR.teamNum!=teamNum){
            turnTowards(EggInR.getX(), EggInR.getY());
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else if(AnInR!=null && tim>p && sit2>=7 && wt3>=7 && !AnInR.anabioz && AnInR.tim>AnInR.p){
            turnTowards(AnInR.x, AnInR.y);
            r1=getRotation();
            setRotation(r);
            turntor1=1;
        }
        else{
            randomMove();
        }

        if(movementAlongTheEdge != -1) {
            r1 = movementAlongTheEdge;
            r1 %= 360;
            movementAlongTheEdge = -1;
        }
        //(int)Math.sqrt(Math.pow(getObjectsInRange(radius, Animal.class).get(0).getX()-getX(),2)+Math.pow(getObjectsInRange(radius, Animal.class).get(0).getY()-getY(),2))<=radius-(int)((radius*getObjectsInRange(radius, Animal.class).get(0).maskcof)/100)
    }

    public void randomMove(){
        if(Greenfoot.getRandomNumber(2)==1){
            r1=Greenfoot.getRandomNumber(360);
        }
        turntor1=1;
    }
    
    public void brith(){
        if(sysdix<=0.5 && onground!=3 && air>0){
            air=air-(msize*msize*msize);
        }
        else if(sysdix<=0.5 && air<mair && onground==3){
            air+=(msize*msize*msize);
            if(air>mair){
                air=mair;
            }
        }
        if(sysdix>0.5 && onground==3 && air>0){
            air=air-(msize*msize*msize);
        }
        else if(sysdix>0.5 && onground!=3 && air<mair){
            air+=(msize*msize*msize);
            if(air>mair){
                air=mair;
            }
        }
    }
    
    public void drink(){
        if(onground==3 && water2<mw || touchWater && water2<mw && onground==1){
            water2=water2+drink;
            if(wt3<9){
                stopp=1;
            }
        }
        
        if(water2>mw){
            water2=mw;
        }
        if(water2>0){
            wt3=water2/(mw/10);
        }
    }
    
    public void eat(){
        if(Math.abs(getX() - foodx) < getImage().getWidth() / 2 && Math.abs(getY() - foody) < getImage().getHeight() / 2){
            foodx = 0;
            foody = 0;
        }
        if(tPl!=null && xich<0.7 && sit1<msit && onground==2 || tPl!=null && xich<0.7 && sit1<msit && onground==tPl.onground){
            sit1=sit1+(int)(eat*(1-xich));
            tPl.colfood-=eat;
            xp-=tPl.poisondam;
            if(sit2<9){
                stopp=1;
            }
        }
        
        if(tFood!=null && xich>0.3 && sit1<msit && onground==1){
            sit1=sit1+eat;
            tFood.sit-=eat;
            if(sit2<9){
                stopp=1;
            }
        }
        
        if(sit1>msit){
            sit1=msit;
        }
        if(sit1>0){
            sit2=sit1/(msit/10);
        }
        // fly==1 || getIntersectingObjects(Plant.class).size()>0 && getIntersectingObjects(Plant.class).get(0).xich<=0.3 && sit1>(msit/3)*2 && xp<mxp && tg>=needt-10 && tg<=needt+10 && age>0 || getIntersectingObjects(Plant.class).size()==0 &&sit1>(msit/3)*2 && xp<mxp && tg>=needt-10 && tg<=needt+10 && age>0 || getIntersectingObjects(Animal.class).size()>0 && getIntersectingObjects(Animal.class).get(0).xich<=0.3 && sit1>(msit/3)*2 && xp<mxp && tg>=needt-10 && tg<=needt+10 && age>0  || getIntersectingObjects(Animal.class).size()==0 && sit1>(msit/3)*2 && xp<mxp && tg>=needt-10 && tg<=needt+10 && age>0
        
    }
    
    public void attack(){
        dob=null;
        //if(xich>0.3 && tAn!=null && tAn.xich-0.1>xich || xich>0.3 && tAn!=null && tAn.xich+0.1<xich){
        if(xich>0.3 && pl!=null && MyWorld.plmode != 2 ||
                xich>0.3 && pl!=null && sit2<7){
            if(pl.onground==onground){
                pl.xp-=(damage+poisondam);
                pl.xichan=this;
            }
        }
        else if(xich>0.3 && tAn!=null && tAn.teamNum!=teamNum){
            dob=tAn;
            if(tAn.onground==onground){
                if((int)(dob.size*dob.protection)<(damage+poisondam)){
                    dob.xp-=((damage+poisondam)-(dob.size*dob.protection));
                }
                dob.xichan=this;
            }
        }
        else if(xich>0.3 && tEgg!=null && tEgg.teamNum!=teamNum){
            if(sit1<msit && tEgg.size3<=size && onground==tEgg.onground){
                sit1=sit1+(tEgg.size3*35000);
                getWorld().removeObject(tEgg);
                tEgg=null;
            }
            else if(tEgg!=null && sit1<msit && tEgg.size3<=size && onground==1 && tEgg.onground==3){
                dive1();
            }
            else if(tEgg!=null && sit1<msit && tEgg.size3<=size && onground==3 && tEgg.onground==1){
                up();
            }
        }
        else{
            defance();
        }
    }
    
    public void defance(){
        if(xp<mxp && xichan!=null && onground==xichan.onground && (int)(xichan.size*xichan.protection)<(damage+poisondam)){
            xichan.xp-=(damage+poisondam)-(xichan.size*xichan.protection);
        }
        if(xp<mxp && xichpl!=null && onground==xichpl.onground){
            xichpl.xp-=(damage+poisondam);
        }
        xichan=null;
        xichpl=null;
    }
    
    int rotspeed;
    int isturn;
    public void move(){
        startx=getX();
        starty=getY();
        if(moven>0 && dei==0){
            if(onground!=3 && pspeed>=speed && pspeed>=wspeed && sit2>5 && wt3>5 && stopf==0 && agesp==0  && !inHole|| onground==0){
                dmove(-pspeed);
                sit1=sit1-(int)(pspeed*pspeed);
                water2=water2-(int)(pspeed*pspeed);
                fly=1;
            }
            else if(!touchWater){
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
        if(fly==1){
            rotspeed=(int)(pspeed*size*15);
        }
        else if(!touchWater && !inHole|| onground==1){
            rotspeed=(int)(speed*size*15);
        }
        else if(touchWater && !inHole|| onground==3){
            rotspeed=(int)(wspeed*size*15);
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
                isturn=1;
            }
            if(turntor1==1 && r1<r){
                r=r-rotspeed;
                if(r-r1>180){
                    r=r+(rotspeed*2);  
                }
                setRotation(r);
                sit1=sit1-rotspeed;
                r=getRotation();
                isturn=1;
            }
            if(r-(rotspeed-1)<=r1 || r1<r+(rotspeed-1)){
                r=r1;
                turntor1=0;
            }
        }
        if(dei==0 && stopp==0 && !inHole || inHole && isturn==0 && dei==0 && stopp==0){
            if(onground!=3 && pspeed>=speed && pspeed>=wspeed && sit2>5 && wt3>5 && stopf==0 && agesp==0 && !inHole|| onground==0){
                dmove(pspeed*size);
                sit1=sit1-(int)(Math.pow(pspeed*size,2));
                water2=water2-(int)(Math.pow(pspeed*size,2));
                fly=1;
            }
            else if(!touchWater && !inHole || onground==1){
                dmove(speed*size);
                sit1-=(int)(speed*size);
                water2-=(int)(speed*size);
            }
            else if(touchWater && !inHole|| onground==3){
                dmove(wspeed*size);
                sit1-=(int)(wspeed*size);
                water2-=(int)(wspeed*size);
            }
            dei=1;
        }
        isturn=0;
        stopp=0;
        stopf=0;
        dei=0;
        inHole();
    }
    
    double dx;
    double dy;
    public void dmove(double v){
        dx += v*Math.cos(Math.toRadians(getRotation()));
        dy += v*Math.sin(Math.toRadians(getRotation()));
        
        if(dx < 0){
            dx = 0;
        }
        if(dy < 0){
            dy = 0;
        }
        if((int) dx > getWorld().getWidth()){
            dx = getWorld().getWidth();
        }
        if((int) dy > getWorld().getHeight()){
            dy = getWorld().getHeight();
        }
        setLocation((int) dx,(int) dy);
    }

    private int movementAlongTheEdge = -1;
    public void moveBack(){
        if(touchWater && fly==0 && wspeed==0 && !inHole || inHole && !touchHR && onground==1 || 
        !touchWater && fly==0 && !inHole && speed==0 || inHole && !touchHR && onground==3){
            dx=startx;
            dy=starty;

            turnTowards(waterX, waterY);
            movementAlongTheEdge = getRotation() + 100;

            turntor1 = 1;
            setRotation(0);
            touchWater();

            setLocation((int)startx,(int)starty);
        }

        tPl=(Plant)getOneIntersectingObject(Plant.class);
        tAn=(Animal)getOneIntersectingObject(Animal.class);
        tEgg=(Egg)getOneIntersectingObject(Egg.class);
        tFood=(DieAnimal)getOneIntersectingObject(DieAnimal.class);
        
        if(MyWorld.plmode==2 && tAn!=null){
            if(Math.abs(tAn.xich-xich) <= MyWorld.cofic){
                tAn.teamNum=0;
            }
            else{
                tAn.teamNum=1;
            }
        }
        if(MyWorld.plmode==2 && tEgg!=null){
            if(Math.abs(tEgg.xich-xich) <= MyWorld.cofic){
                tEgg.teamNum=0;
            }
            else{
                tEgg.teamNum=1;
            }
        }
        
        if(inHole){
            tFood=null;
        }
        if(tPl!=null && inHole!=tPl.inHole){
            tPl=null;
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
        if(touchWater && onground==1){
            onground=3;
            if(air<=0 && sysdix<=0.5){
                air=1;
            }
        }
    }
    public void up(){
        if(onground==3 && !inHole){
            onground=1;
            if(air<=0 && sysdix>0.5){
                air=1;
            }
        }
    }
    
    public void dive(){
        if(anabioz==false){
            if(sysdix<=0.5 && onground==1 || dob!=null && dob.onground==3 && onground==1 || tPl!=null && xich<0.7 && sit1<msit && onground==1 && tPl.onground==3){
                dive1();
            }
            if(tFood!=null && xich>0.3 && sit1<msit && onground==3 || dob!=null && dob.onground==1 && onground==3 || air<=0 && onground==3 && sysdix>0.5 || tPl!=null && xich<0.7 && sit2<7 && onground==3 && tPl.onground==1){
                up();
            }
        }
        
        if(!touchWater && onground==3){
            up();
        }
    }
    
    public void climb(){
        if(canclimb>0.5 && onground==1 && anabioz==false && !inHole){
            if(tPl!=null && tPl.onground==1 && tPl.size>msize && onground==1){
                onground=2;
            }
        }
        if(onground==2 && tPl==null){
            onground=1;
        }
    }
    
    public void FlyOrNo(){
        if(sit2<=5 && fly==1 || wt3<=5 && fly==1){
            fly=0;
            stopf=1;
        }
        if(tim>p && onground==0 && sit1>(msit/3)*2 && water2>(mw/3)*2 && tAn!=null && tAn.tim>tAn.p && tAn.teamNum==teamNum){
            fly=0;
            stopf=1;
        }
        if(tPl!=null && xich<0.7 && sit2<7 && onground==0){
            fly=0;
            stopf=1;
        }
        if(anabioz==true){
            fly=0;
            stopf=1;
        }
        if(tAn!=null && xich>0.3 && tAn.teamNum!=teamNum){
            if(sit2<7 && tAn.onground==1){
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
        if(fly==0 && flycof==1 && onground==0){
            onground=1;
        }
        else if(fly==1){
            onground=0;
        }
    }
    
    public void die(){
        if(sit1<=0){
            xp--;
        }
        if(myage>age){
            xp=0;
        }
        if(water2<=0){
            xp--;
        }
        if(air<=0){
            xp--;
        }
        if(xp<=0){
            if(MyWorld.plan==this){
                MyWorld.plan=null;
            }
            myPl.myAn--;
            DieAnimal da=new DieAnimal(size,sit1,msit);
            getWorld().addObject(da,getX(),getY());
            getWorld().removeObject(this);
        } 
    }
}

