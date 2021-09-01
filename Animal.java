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
    ArrayList<Double> dna;
    ArrayList<Double> dna2=new ArrayList<>();
    int location;
    int stopFly;
    int r;
    int x;
    int y;
    int r1;
    int stopMoveForward;
    int action;
    int turnToR1;
    double timer;
    int start;
    GreenfootImage image;
    GreenfootImage fon;
            
    double startX;
    double startY;
    
    int foodX = -1;
    int foodY = -1;
    
    //эволюционирующие показатели
    int maxSize;
    int size;
    int size1;
    int maxHp;
    int hp;
    
    int maxAir;
    int air;
    
    double respiratorySystem;
    
    double canClimb;
    
    double hibernationCof;
    boolean hibernation;
    
    double liveBirth;
    
    int ageForGrow;
    int myAge;
    
    double flyingSpeed;
    double flyCof;
    int fly;
    
    double poisonCof;
    int poison;
    
    double maskCof;

    int maxWater;
    double waterNeed;
    int drink;
    int water;
    
    int period;
    int tim;
    int sex;

    double protection;
    
    double waterSpeed;
    
    double moveCof;
    
    int age;
    
    int fertility;
    
    double speed;

    int radius1;
    int radius;
    
    double heatCof;
    int creatureTemp;
    int surroundingTemp;
    private final int needTemp = 36;
    
    double predation;
    int damage;

    int maxSatiety;
    int satiety;
    double foodNeed;
    int eat;
    
    double digSpeed;
    
    //партнер
    Animal an1;
    
    //на меня охотятся
    Plant hunterPlant;
    Animal hunter;
    
    //добыча
    Animal extraction;
    
    Animal touchingAn;
    Plant touchingPl;
    Egg touchingEgg;
    DieAnimal touchingFood;
    
    Animal teammateAnimal;
    Animal enemyAnimal;
    Plant plant;
    Egg egg;
    
    boolean touchWater;
    
    int distToChild;

    private final int foodCof = 7000;
    
    public Animal(ArrayList<Double> dna1, Player pl, int tn, boolean inHole, int food1, int water1){
        this.inHole=inHole;
        if(this.inHole){
            ist=1;
        }
        r=Greenfoot.getRandomNumber(360);
        r1 = r;
        teamNum=tn;
        myPl=pl;
        dna = new ArrayList<>(dna1);
        for(int i=0;i<dna.size();i++){
            double d=dna.get(i);
            if(d != -1) {
                if (d > 1) {
                    if (Greenfoot.getRandomNumber(3) + 1 == 1) {
                        d += d * MyWorld.cofOfEvolution * Math.random();
                    } else if (Greenfoot.getRandomNumber(2) + 1 == 1) {
                        d -= d * MyWorld.cofOfEvolution * Math.random();
                    }
                } else {
                    if (Greenfoot.getRandomNumber(3) + 1 == 1) {
                        d += MyWorld.cofOfEvolution * Math.random();
                    } else if (Greenfoot.getRandomNumber(2) + 1 == 1) {
                        d -= MyWorld.cofOfEvolution * Math.random();
                    }
                }

                if (d < 0 && i != 20) {
                    d = 0;
                }
                dna.set(i, d);
            }
        }
        
        if(dna.get(18)>=0){
            maxSize=dna.get(18).intValue();
            if(maxSize<=0){
                maxSize=1;
            }
            size=(int)(maxSize*0.1);
            if(size<=0){
                size=1;
            }
        }
        else{
            maxSize=3;
            size=(int)(maxSize*0.1);
            if(size<=0){
                size=1;
            }
            dna.set(18, (double)maxSize);
        }
        distToChild = 2 * maxSize;
        fon = new GreenfootImage(maxSize, maxSize);
        
        if(dna.get(0)>=0){
            maxAir=dna.get(0).intValue();
            air=maxAir;
        }
        else{
            maxAir=maxSize*1000;
            air=maxAir;
            dna.set(0, (double)maxAir);
        }
        
        if(dna.get(1)>=0){
            respiratorySystem=dna.get(1);
        }
        else{
            respiratorySystem=0.0;
            dna.set(1, respiratorySystem);
        }
        if(respiratorySystem>1){
            respiratorySystem=1;
        }
        else if(respiratorySystem<0){
            respiratorySystem=0;
        }
        
        if(dna.get(2)>=0){
            canClimb=dna.get(2);
        }
        else{
            canClimb=0.0;
            dna.set(2, canClimb);
        }
        if(canClimb>1){
            canClimb=1;
        }
        else if(canClimb<0){
            canClimb=0;
        }
        
        sex =Greenfoot.getRandomNumber(2);
        
        if(dna.get(3)>=0){
            hibernationCof =dna.get(3);
        }
        else{
            hibernationCof =1;
            dna.set(3, hibernationCof);
        }
        if(hibernationCof <0){
            hibernationCof =0;
        }
        if(hibernationCof >1){
            hibernationCof =1;
        }
        
        if(dna.get(4)>=0){
            liveBirth =dna.get(4);
        }
        else{
            liveBirth =1;
            dna.set(4, liveBirth);
        }
        if(liveBirth >1){
            liveBirth =1;
        }
        else if(liveBirth <0){
            liveBirth =0;
        }
        
        if(dna.get(5)>=0){
            ageForGrow =dna.get(5).intValue();
        }
        else{
            ageForGrow = 10;
            dna.set(5, (double) ageForGrow);
        }
        
        if(dna.get(6)>=0){
            flyingSpeed =dna.get(6);
        }
        else{
            flyingSpeed =0;
            dna.set(6, flyingSpeed);
        }
        
        if(dna.get(7)>=0){
            poison=dna.get(7).intValue();
        }
        else{
            poison=0;
            dna.set(7, (double)poison);
        }
        
        if(dna.get(8)>=0){
            maskCof =dna.get(8);
        }
        else{
            maskCof =0.0;
            dna.set(8, maskCof);
        }
        if(maskCof >1){
            maskCof =1;
        }
        else if(maskCof <0){
            maskCof =0;
        }
        
        if(dna.get(9)>=0){
            maxWater =dna.get(9).intValue();
            water =water1;
        }
        else{
            maxWater =maxSize*foodCof;
            water = maxWater;
            dna.set(9, (double) maxWater);
        }
        
        if(dna.get(10)>=0){
            period =dna.get(10).intValue();
        }
        else{
            period = 4;
            dna.set(10, (double) period);
        }
        
        if(dna.get(11)>=0){
            protection=dna.get(11);
        }
        else{
            protection=0;
            dna.set(11, protection);
        }
        if(protection>1){
            protection=1;
        }
        if(protection<0){
            protection=0;
        }
        
        if(dna.get(12)>=0){
            waterSpeed =dna.get(12);
        }
        else{
            waterSpeed = 0.3 / maxSize;
            dna.set(12, waterSpeed);
        }
        
        if(dna.get(13)>=0){
            moveCof =dna.get(13);
        }
        else{
            moveCof =0.0;
            dna.set(13, moveCof);
        }
        if(moveCof >1.0){
            moveCof =1.0;
        }
        else if(moveCof <0){
            moveCof =0;
        }
        
        if(dna.get(14)>=0){
            age=dna.get(14).intValue();
        }
        else{
            age=70;
            dna.set(14, (double)age);
        }
        
        if(dna.get(15)>0){
            fertility =dna.get(15).intValue();
        }
        else{
            fertility = 1;
            dna.set(15, (double) fertility);
        }
        
        if(dna.get(16)>=0){
            speed=dna.get(16);
        }
        else{
            speed=0;
            dna.set(16, speed);
        }
        
        if(dna.get(17)>=0){
            radius1=dna.get(17).intValue();
        }
        else{
            radius1=30;
            dna.set(17, (double) radius1);
        }
        
        if(dna.get(19)>=0){
            heatCof =dna.get(19);
        }
        else{
            heatCof =0.5;
            dna.set(19, heatCof);
        }
        if(heatCof >0.9){
            heatCof =0.9;
        }
        else if(heatCof <0){
            heatCof =0;
        }
        
        if(dna.get(20)>=0){
            predation =dna.get(20);
        }
        else{
            if(MyWorld.plMode <2){
                predation = 0.0;
            }
            else{
                predation = Greenfoot.getRandomNumber(3)==1 ? 1.0 : 0;
                if(predation == 1.0){
                    waterSpeed += 0.05;
                }
            }
            dna.set(20, predation);
        }
        if(predation >1){
            predation =1;
        }
        else if(predation <0){
            predation =0;
        }
        
        if(dna.get(21)>0){
            maxSatiety =dna.get(21).intValue();
            satiety =food1;
        }
        else{
            maxSatiety = maxSize*foodCof;
            satiety = maxSatiety;
            dna.set(21, (double) maxSatiety);
        }
        
        if(dna.get(22)>0){
            digSpeed =dna.get(22);
        }
        else{
            digSpeed =0.0;
            dna.set(22, digSpeed);
        }
        
        location=1;
        maxHp =size;
        hp = maxHp;
        tim=0;
        damage=(int)(size* predation);
        if(damage<=1 && predation >=0.3){
            damage=1;
        }
        radius = radius1 / maxSize;
        updateImage();
        myPl.myAn++;
    }
    int t4;
    double blueCof;
    public void updateImage(){
        t4 = 255;
        size = maxSize;
        if(ageForGrow > myAge){
            size=(int) (maxSize*((double) myAge / ageForGrow));
        }

        radius = (int) (((double) radius1 / maxSize) * size);

        size1 = size;
        if(location == 0){
            size1 = (int) (size * flyCof);
            t4 = (int) (255 * (1.0 / flyCof));
            if(t4>255){
                t4=255;
            }
        }
        else if(location==2){
            size1=(int)(size*1.5);
        }
        else if(location==3 || inHole){
            t4=100;
        }  
        
        if(size<=0){
            size=1;
        }
        if(size1<=0){
            size1=1;
        }
        image = new GreenfootImage(size,size);
        poisonCof =(double)poison/size;
        if(poisonCof >1){
            poisonCof =1;
        }
        
        blueCof = poisonCof;
        if(poisonCof < predation){
            blueCof = predation;
        }
        if(!hibernation){
            if(MyWorld.plMode <2 || MyWorld.observedAnimal !=null && MyWorld.plMode ==2){
                if(MyWorld.observedAnimal !=null && MyWorld.plMode ==2){
                    if(Math.abs(MyWorld.observedAnimal.predation - predation) <= MyWorld.classificationOfSpecies){
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
                if(MyWorld.plMode ==2){
                    teamNum=0;
                }
            }
            else{
                image.setColor(new Color((int)(predation *255), (((int)(55* poisonCof)+(int)((1- blueCof)*255))/255)*255, (int)((1- blueCof)*255), 255));
            }
        }
        else{
            image.setColor(new Color(153, 217, 234, 255));
        }
        image.fill();
        fon.setTransparency((int)(255*(maskCof)));
        image.drawImage(fon,0,0);
        image.setTransparency(t4);
        image.scale(size1,size1);
        setImage(image);
        
        maxHp =size;
        damage=(int)(size* predation);
        if(damage<=0 && predation >0.3){
            damage=1;
        }
        eat=size*2000;
        drink=size*2000;
    }
    
    Water w;
    int dist;

    private int waterX = -1;
    private int waterY = -1;
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
        
        if(touchHole && ist==0 && h.loc==location){
            if(!inHole && h.size>maxSize && respiratorySystem<=0.5 && h.loc==3 || !inHole && h.size>maxSize && respiratorySystem>0.5 && h.loc==1){
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
        
            if(hr!=null && inHole && hr.loc!=location){
                location=hr.loc;
            }
        }
    }
    
    int digTimer;
    
    public void dig(){
        if(getObjectsInRange(radius,Hole.class).size()==0 && digTimer ==0 && foodNeed < 0.5 && waterNeed < 0.5 && location!=0 && location!=2 && digSpeed >=1){
            digTimer =(int)(Math.pow((maxSize+2)*2,2)/(size*size* digSpeed));
        }
        if(timer==0 && digTimer >0){
            digTimer--;
            if(digTimer ==0){
                getWorld().addObject(new Hole(maxSize+2,location),getX(),getY());
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
        if(!hibernation){
            think();
            move();
            touchWater();
            moveBack();
        }
        x=getX();
        y=getY();
        
        hibernation();
        if(digTimer >0){
            stopMoveForward=1;
        }
        if(timer>=MyWorld.year){
            timer=0;
            myAge++;
            tim++;
        }
        updateImage();
        
        temp();
        
        if(!hibernation){
            attack();
            if(location!=0 && myAge >= ageForGrow && foodNeed < 0.1 && waterNeed < 0.1){
                replicase();
            }
            drink();
            eat();
            dig();
        }
        
        breathe();
        swim();
        climb();
        fly();
        die();
    }
    
    public void hibernation(){
        creatureTemp = getCreatureTemp();
        if(Math.abs(creatureTemp - needTemp) > 10){
            satiety = satiety - (int)(hibernationCof *size*size*size);
            water = water - (int)(hibernationCof *size*size*size);
            timer += hibernationCof;
            hibernation = true;
        }
        else{
            satiety = satiety - (size*size*size);
            water = water - (size*size*size);
            timer++;
            hibernation = false;
        }

        if(timer % 10 == 0 && foodNeed < 0.3 && waterNeed < 0.3 && hp < maxHp && Math.abs(creatureTemp - needTemp) <= 10){
            hp++;
        }
    }
    
    public void temp(){
        creatureTemp = getCreatureTemp();
        satiety = satiety -(int)(Math.abs(surroundingTemp - needTemp)* heatCof *size);
        water = water -(int)(Math.abs(surroundingTemp - needTemp)* heatCof *size);
    }
    
    int childX;
    int childY;
    
    int rotToMyChild;
    public void replicase(){
        if(speed == 0 && waterSpeed == 0 && flyingSpeed == 0){
            if(tim > period *2){
                for(int i = 0; i< fertility; i++){
                    int childFood = (int) (maxSatiety * liveBirth / (2 * fertility));
                    int childWater = (int)(maxWater * liveBirth  / (2 * fertility));
                    dna2.clear();
                    dna2.addAll(dna);
                    double randRot = Math.toRadians(Greenfoot.getRandomNumber(360));
                    int dist=Greenfoot.getRandomNumber(distToChild);

                    childX =x + (int) (Math.cos(randRot) * dist);
                    childY =y + (int) (Math.sin(randRot) * dist);
                    if(inHole){
                        inHole();
                        if(hr!=null && Math.sqrt(Math.pow(hr.getX() - childX, 2) + Math.pow(hr.getY() - childY, 2)) > (double) hr.size / 2){
                            setLocation(hr.getX(),hr.getY());
                            turnTowards(childX, childY);
                            rotToMyChild =getRotation();
                            childX =hr.getX()+(int)(Math.cos(Math.toRadians(rotToMyChild))*((hr.size/2)-1));
                            childY =hr.getY()+(int)(Math.sin(Math.toRadians(rotToMyChild))*((hr.size/2)-1));
                        }
                    }
                    if(liveBirth >=0.5){
                        Animal child=new Animal(dna2, myPl, teamNum, inHole
                                , childFood, childWater);
                        getWorld().addObject(child, childX, childY);
                    }
                    else{
                        Egg child = new Egg(dna2, location, myPl, teamNum, inHole, moveCof);
                        getWorld().addObject(child, childX, childY);
                    }

                    satiety = satiety - childFood;
                    water = water - childWater;

                    tim = 0;
                }
            }
        }
        else{
            if(tim > period && touchingAn !=null && touchingAn.teamNum==teamNum && !touchingAn.hibernation && touchingAn.location==location && touchingAn.tim> touchingAn.period && touchingAn.myAge > touchingAn.ageForGrow){
                for(int i = 0; i< fertility; i++){
                    an1= touchingAn;
                    int childFood = (int) (maxSatiety * liveBirth / (2 * fertility));
                    int childWater = (int)(maxWater * liveBirth  / (2 * fertility));
                    dna2.clear();
                    for(int i1=0;i1<dna.size();i1++){
                        if(Greenfoot.getRandomNumber(2)==1){
                            dna2.add(dna.get(i1));
                        }
                        else{
                            dna2.add(an1.dna.get(i1));
                        }
                    }
                    if(liveBirth >=0.5){
                        Animal child=new Animal(dna2, myPl, teamNum, inHole
                                , childFood, childWater);
                        getWorld().addObject(child, x, y);
                    }
                    else{
                        Egg child=new Egg(dna2, location, myPl, teamNum, inHole, moveCof);
                        getWorld().addObject(child, x, y);
                    }
                    satiety = satiety -childFood;
                    water = water - childWater;

                    tim=0;
                }
            }
            else if(tim> period *2){
                for(int i = 0; i< fertility; i++){
                    int childFood = (int) (maxSatiety * liveBirth / (2 * fertility));
                    int childWater = (int)(maxWater * liveBirth  / (2 * fertility));
                    dna2.clear();
                    dna2.addAll(dna);
                    if(liveBirth >=0.5){
                        Animal child=new Animal(dna2, myPl, teamNum, inHole
                                , childFood, childWater);
                        getWorld().addObject(child, x, y);
                    }
                    else{
                        Egg child=new Egg(dna2, location, myPl, teamNum,inHole, moveCof);
                        getWorld().addObject(child, x, y);
                    }
                    satiety = satiety - childFood;
                    water = water - childWater;
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
        if(MyWorld.plMode ==0){
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

    public int getSurroundingTemp() {
        surroundingTemp = (int) (((double) getX() / getWorld().getWidth()) * MyWorld.temp);
        if(touchWater || location == 3){
            surroundingTemp -= 10;
        }
        return surroundingTemp;
    }

    double attraction;
    private int myPreferences() {
        attraction = 0.0;
        if(tim > period && ageForGrow < myAge && foodNeed < 0.1 && waterNeed < 0.1) {
            attraction = (double)(tim - period) / period;
        }

        if(waterNeed > foodNeed && waterNeed > attraction){
            return 1;
        }
        else if(foodNeed >= attraction){
            return 2;
        }
        else{
            return 3;
        }
    }

    double distToObject;

    double minDistToEnemy;
    double minDistToTeammate;

    double minFoodDist;

    double minDistToExtraction;
    public void think(){
        int target = myPreferences();
        teammateAnimal =null;
        enemyAnimal =null;
        plant =null;
        egg =null;
        food=null;

        minFoodDist = Math.pow(getWorld().getWidth(), 2) + Math.pow(getWorld().getHeight(), 2);
        minDistToEnemy = minFoodDist;
        minDistToTeammate = minFoodDist;
        minDistToExtraction = minFoodDist;
        if(MyWorld.plMode ==2){
            teamNum=0;
        }
        for(Animal animalInRange : getObjectsInRange(radius, Animal.class)) {
            if (animalInRange.inHole == inHole && animalInRange.location == location) {
                if (MyWorld.plMode == 2) {
                    if (Math.abs(animalInRange.predation - predation) <= MyWorld.classificationOfSpecies) {
                        animalInRange.teamNum = 0;
                    } else {
                        animalInRange.teamNum = 1;
                    }
                }

                distToObject = Math.pow(animalInRange.getX() - getX(), 2) + Math.pow(animalInRange.getY() - getY(), 2);

                if(animalInRange.teamNum == teamNum && distToObject < minDistToTeammate) {
                    if (Math.sqrt(Math.pow(getX() - animalInRange.getX(), 2) + Math.pow(getY() - animalInRange.getY(), 2)) < radius - (int) (animalInRange.maskCof * radius)) {
                        minDistToTeammate = distToObject;

                        teammateAnimal = animalInRange;
                    } else if (intersects(animalInRange)) {
                        minDistToTeammate = distToObject;

                        teammateAnimal = animalInRange;
                    }
                }
                else if(predation > 0.3 && animalInRange.teamNum != teamNum && distToObject < minDistToEnemy && animalInRange.myAge > animalInRange.ageForGrow ||
                        predation < 0.7 && animalInRange.teamNum != teamNum && distToObject < minDistToEnemy) {
                    if (Math.pow(getX() - animalInRange.getX(), 2) + Math.pow(getY() - animalInRange.getY(), 2) < Math.pow(radius - (int) (animalInRange.maskCof * radius), 2)) {
                        minDistToEnemy = distToObject;

                        enemyAnimal = animalInRange;
                    } else if (intersects(animalInRange)) {
                        minDistToEnemy = distToObject;

                        enemyAnimal = animalInRange;
                    }
                }
            }
        }
        if(getObjectsInRange(radius, Egg.class).size()>0){
            egg =getObjectsInRange(radius, Egg.class).get(0);
            if(egg.inHole!=inHole){
                egg =null;
            }
        }
        
        getPlInRange();
        

        if(inHole && !touchHole && getObjectsInRange((maxSize+2)*2,Hole.class).size()>0){
            turnTowards(getObjectsInRange((maxSize+2)*2,Hole.class).get(0).getX(), getObjectsInRange((maxSize+2)*2,Hole.class).get(0).getY());
            r1=getRotation();
            turnToR1=1;
        }
        else if(MyWorld.plMode <2 && Math.sqrt(Math.pow(myPl.getX()-getX(),2)+Math.pow(myPl.getY()-getY(),2))>radius){
            turnTowards(myPl.getX(), myPl.getY());
            r1=getRotation();
            turnToR1=1;
        }
        else if(isAtEdge()){
            turnTowards(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            r1=getRotation();
            turnToR1=1;
        }
        else if(movementAlongTheEdge != -1) {
            r1 = movementAlongTheEdge;
            turnToR1 = 1;
            movementAlongTheEdge = -1;
        }
        else if (!isTouching(Water.class) && getObjectsInRange(radius, Water.class).size() > 0 && (double) air / maxAir <= 0.6 && respiratorySystem <= 0.5) {
            turnTowards(getObjectsInRange(radius, Water.class).get(0).getX(), getObjectsInRange(radius, Water.class).get(0).getY());
            r1 = getRotation();
            turnToR1 = 1;
        } else if (!isTouching(Water.class) && waterX != -1 && waterY != -1 && (double) air / maxAir <= 0.6 && respiratorySystem <= 0.5) {
            turnTowards(waterX, waterY);
            r1 = getRotation();
            turnToR1 = 1;
        }
        else if(target == 1) {
            if (getObjectsInRange(radius, Water.class).size() > 0 && waterNeed > 0.5
                    || getObjectsInRange(radius, Water.class).size() > 0 && (double) air / maxAir <= 0.6 && respiratorySystem <= 0.5) {
                turnTowards(getObjectsInRange(radius, Water.class).get(0).getX(), getObjectsInRange(radius, Water.class).get(0).getY());
                r1 = getRotation();
                turnToR1 = 1;
            } else if (waterNeed > 0.5 && waterX != -1 && waterY != -1
                    || waterX != -1 && waterY != -1 && (double) air / maxAir <= 0.6 && respiratorySystem <= 0.5) {
                turnTowards(waterX, waterY);
                r1 = getRotation();
                turnToR1 = 1;
            }
            else if(enemyAnimal !=null && enemyAnimal.predation > 0.3 && enemyAnimal.predation > predation){
                if(getObjectsInRange(radius,Hole.class).size() > 0 && getObjectsInRange(radius,Hole.class).get(0).size < enemyAnimal.size){
                    turnTowards(getObjectsInRange(radius,Hole.class).get(0).getX(), getObjectsInRange(radius,Hole.class).get(0).getY());
                    r1=getRotation();
                }
                else{
                    turnTowards(enemyAnimal.x, enemyAnimal.y);
                    if(getRotation() >= 180){
                        r1=getRotation() - 180;
                    }
                    else{
                        r1=getRotation() + 180;
                    }
                }

                turnToR1 = 1;
            }
            else{
                randomMove();
            }
        }
        else if(target == 2) {
            if(predation < 0.7) {
                for (Plant plant : getObjectsInRange(radius, Plant.class)) {
                    if (plant.inHole == inHole) {
                        distToObject = Math.pow(plant.getX() - getX(), 2) + Math.pow(plant.getY() - getY(), 2);
                        if (location == plant.location && plant.damage + plant.poison <= (int) (protection * size) && distToObject < minFoodDist) {
                            this.plant = plant;

                            minFoodDist = distToObject;
                        }
                    }
                }
            }
            else if(!inHole){
                for (DieAnimal food : getObjectsInRange(radius, DieAnimal.class)) {
                    distToObject = Math.pow(food.getX() - getX(), 2) + Math.pow(food.getY() - getY(), 2);
                    if (distToObject < minDistToExtraction) {
                        this.food = food;

                        minDistToExtraction = distToObject;
                    }
                }
            }
            if(plant !=null && predation <0.7){
                foodX= plant.getX();
                foodY= plant.getY();
            }
            else if(food != null && predation > 0.3){
                foodX = food.getX();
                foodY = food.getY();
            }

            if(enemyAnimal !=null && enemyAnimal.predation > 0.3 && enemyAnimal.predation > predation && foodNeed < 0.3 ||
                    enemyAnimal !=null && enemyAnimal.predation > 0.3 && enemyAnimal.predation > predation && minFoodDist > minDistToEnemy){
                if(getObjectsInRange(radius,Hole.class).size() > 0 && getObjectsInRange(radius,Hole.class).get(0).size < enemyAnimal.size){
                    turnTowards(getObjectsInRange(radius,Hole.class).get(0).getX(), getObjectsInRange(radius,Hole.class).get(0).getY());
                    r1=getRotation();
                }
                else{
                    turnTowards(enemyAnimal.x, enemyAnimal.y);
                    if(getRotation() >= 180){
                        r1=getRotation() - 180;
                    }
                    else{
                        r1=getRotation() + 180;
                    }
                }

                turnToR1 = 1;
            }
            else if (predation < 0.7 && plant != null) {
                turnTowards(plant.getX(), plant.getY());
                r1 = getRotation();
                turnToR1 = 1;
            } else if (predation > 0.3 && enemyAnimal != null && MyWorld.plMode < 2 || enemyAnimal != null && predation > 0.3 && MyWorld.plMode == 2 && myAge > ageForGrow ||
                    enemyAnimal != null && predation > 0.3 && MyWorld.plMode == 2 && food == null) {
                turnTowards(enemyAnimal.getX(), enemyAnimal.getY());
                r1 = getRotation();
                turnToR1 = 1;
            } else if (predation > 0.3 && food != null) {
                turnTowards(food.getX(), food.getY());
                r1 = getRotation();
                turnToR1 = 1;
            } else if (predation > 0.3 && egg != null && egg.teamNum != teamNum) {
                turnTowards(egg.getX(), egg.getY());
                r1 = getRotation();
                turnToR1 = 1;
            } else if (foodX != -1 && foodY != -1) {
                turnTowards(foodX, foodY);
                r1 = getRotation();
                turnToR1 = 1;
            } else if (pl != null && predation > 0.3) {
                turnTowards(pl.getX(), pl.getY());
                r1 = getRotation();
                turnToR1 = 1;
            }
            else{
                randomMove();
            }
        }
        else if(target == 3){
            if(enemyAnimal !=null && enemyAnimal.predation > 0.3 && enemyAnimal.predation > predation){
                if(getObjectsInRange(radius,Hole.class).size() > 0 && getObjectsInRange(radius,Hole.class).get(0).size < enemyAnimal.size){
                    turnTowards(getObjectsInRange(radius,Hole.class).get(0).getX(), getObjectsInRange(radius,Hole.class).get(0).getY());
                    r1=getRotation();
                }
                else{
                    turnTowards(enemyAnimal.x, enemyAnimal.y);
                    if(getRotation() >= 180){
                        r1=getRotation() - 180;
                    }
                    else{
                        r1=getRotation() + 180;
                    }
                }

                turnToR1 = 1;
            }
            else if (teammateAnimal != null && teammateAnimal.tim > teammateAnimal.period && tim > period && !teammateAnimal.hibernation &&
            teammateAnimal.myAge > teammateAnimal.ageForGrow) {
                turnTowards(teammateAnimal.x, teammateAnimal.y);
                r1 = getRotation();
                turnToR1 = 1;
            }
            else{
                randomMove();
            }
        }
    }

    public void randomMove(){
        if(Greenfoot.getRandomNumber(2)==1){
            r1=Greenfoot.getRandomNumber(360);
        }
        turnToR1=1;
    }
    
    public void breathe(){
        if(respiratorySystem<=0.5 && location!=3 && air>0){
            air=air-(maxSize*maxSize*maxSize);
        }
        else if(respiratorySystem<=0.5 && air<maxAir && location==3){
            air+=(maxSize*maxSize*maxSize);
            if(air>maxAir){
                air=maxAir;
            }
        }
        if(respiratorySystem>0.5 && location==3 && air>0){
            air=air-(maxSize*maxSize*maxSize);
        }
        else if(respiratorySystem>0.5 && location!=3 && air<maxAir){
            air+=(maxSize*maxSize*maxSize);
            if(air>maxAir){
                air=maxAir;
            }
        }
    }
    
    public void drink(){
        if(location==3 && water < maxWater || touchWater && water < maxWater && location==1){
            water = water +drink;
            if(waterNeed > 0.1){
                stopMoveForward=1;
            }
        }
        
        if(water > maxWater){
            water = maxWater;
        }
        if(water > 0){
            waterNeed = 1.0 - ((double)water / maxWater);
        }
    }
    
    public void eat(){
        if(Math.pow(getX() - foodX, 2) + Math.pow(getY() - foodY, 2) < Math.pow(radius, 2)){
            if(getOneObjectAtOffset(foodX - getX(), foodY - getY(), Plant.class) == null && predation < 0.7 ||
        getOneObjectAtOffset(foodX - getX(), foodY - getY(), DieAnimal.class) == null && predation > 0.3) {
                foodX = -1;
                foodY = -1;
            }
        }
        if(touchingPl !=null && predation <0.7 && satiety < maxSatiety && location==2 || touchingPl !=null && predation <0.7 && satiety < maxSatiety && location == touchingPl.location){
            touchingPl.satiety -= Math.min(eat, maxSatiety - satiety);
            satiety += Math.min((int) (eat * (1 - predation)), maxSatiety - satiety);

            if(foodNeed > 0.1){
                stopMoveForward=1;
            }
        }
        
        if(touchingFood !=null && predation >0.3 && satiety < maxSatiety && location==1){
            touchingFood.satiety -= Math.min(eat, maxSatiety - satiety);
            satiety += Math.min(eat, maxSatiety - satiety);

            if(foodNeed > 0.1){
                stopMoveForward=1;
            }
        }
        
        if(satiety > maxSatiety){
            satiety = maxSatiety;
        }
        if(satiety > 0){
            foodNeed = 1.0 - ((double)satiety / maxSatiety);
        }
        
    }
    
    public void attack(){
        extraction =null;
        if(predation >0.3 && pl!=null){
            if(pl.location==location){
                pl.xp-=(damage+poison);
                pl.hunter =this;
            }
        }
        else if(predation >0.3 && touchingAn !=null && touchingAn.teamNum!=teamNum){
            extraction = touchingAn;
            if(touchingAn.location==location){
                extraction.hp -= Math.max((damage + poison) - (int) (extraction.size * extraction.protection), 0);

                extraction.hunter =this;
            }
        }
        else if(predation >0.3 && touchingEgg !=null && touchingEgg.teamNum!=teamNum){
            if(satiety < maxSatiety && touchingEgg.size <= size && location== touchingEgg.location){
                satiety += (touchingEgg.size * 10000);
                getWorld().removeObject(touchingEgg);
                touchingEgg =null;
            }
            else if(satiety < maxSatiety && touchingEgg.size <=size && location==1 && touchingEgg.location==3){
                dive();
            }
            else if(satiety < maxSatiety && touchingEgg.size <=size && location==3 && touchingEgg.location==1){
                up();
            }
        }
        else{
            defense();
        }
    }
    
    public void defense(){
        if(hp < maxHp && hunter !=null && location== hunter.location){
            hunter.hp -= Math.max((damage + poison) - (int) (hunter.size * hunter.protection), 0);
        }
        if(hp < maxHp && hunterPlant !=null && location == hunterPlant.location){
            hunterPlant.hp -= (damage + poison);
        }
        hunter =null;
        hunterPlant =null;
    }
    
    int rotationSpeed;
    boolean turned;
    public void move(){
        startX = dx;
        startY = dy;
        if(fly==1){
            rotationSpeed =(int)(flyingSpeed * size * 15);
        }
        else if(!touchWater && !inHole || location==1){
            rotationSpeed =(int)(speed*size*15);
        }
        else if(touchWater && !inHole || location==3){
            rotationSpeed =(int)(waterSpeed *size*15);
        }
        if(r != r1 && turnToR1 == 1){

            if(Math.abs(r1 - r) < rotationSpeed){
                r = r1;
                turnToR1 = 0;
            }
            else {
                if (r1 > r) {
                    if (r1 - r > 180) {
                        r -= rotationSpeed;
                    } else {
                        r += rotationSpeed;
                    }
                    satiety = satiety - rotationSpeed * size;
                    turned = true;
                }
                if (r1 < r) {
                    if (r - r1 > 180) {
                        r += rotationSpeed;
                    } else {
                        r -= rotationSpeed;
                    }
                    satiety = satiety - rotationSpeed * size;
                    turned = true;
                }
            }
            
            r %= 360;
            if(r<0){
                r += 360;
            }
        }

        if(action==0 && stopMoveForward==0 && !inHole || inHole && !turned && action==0 && stopMoveForward==0){
            if(location!=3 && flyingSpeed >=speed && flyingSpeed >= waterSpeed && foodNeed < 0.7 && waterNeed < 0.7 && stopFly==0 && ageForGrow ==0 && !inHole|| location==0){
                doubleMove(flyingSpeed *size);
                satiety = satiety -(int) (Math.pow(flyingSpeed, 2) * size);
                water = water -(int) (Math.pow(flyingSpeed, 2) * size);
                fly=1;
            }
            else if(!touchWater && !inHole || location==1){
                doubleMove(speed*size);
                satiety -= (int) (Math.pow(speed, 2) * size);
                water -= (int) (Math.pow(speed, 2) * size);
            }
            else if(touchWater && !inHole|| location==3){
                doubleMove(waterSpeed *size);
                satiety -=(int)(Math.pow(waterSpeed, 2) * size);
                water -=(int)(Math.pow(waterSpeed, 2) * size);
            }
            action=1;
        }

        setRotation(0);

        turned = false;
        stopMoveForward=0;
        stopFly=0;
        action=0;
        inHole();
    }
    
    double dx;
    double dy;
    public void doubleMove(double v){
        dx += v*Math.cos(Math.toRadians(r));
        dy += v*Math.sin(Math.toRadians(r));
        
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

    public int getCreatureTemp(){
        return (int) (needTemp * heatCof + (getSurroundingTemp() * (1 - heatCof)));
    }

    private int movementAlongTheEdge = -1;
    public void moveBack(){
        if(touchWater && fly==0 && waterSpeed ==0 && !inHole || inHole && !touchHR && location==1 ||
        !touchWater && fly==0 && !inHole && speed==0 || inHole && !touchHR && location==3 ||
        Math.abs(needTemp - getCreatureTemp()) > 10){
            dx = startX;
            dy = startY;

            if(needTemp - 10 > getCreatureTemp()){
                movementAlongTheEdge = 0;
            }
            else if(needTemp + 10 < getCreatureTemp()){
                movementAlongTheEdge = 180;
            }
            else if(waterX != 0 && waterY != 0){
                turnTowards(waterX, waterY);
                movementAlongTheEdge = getRotation();
            }

            setLocation((int) startX,(int) startY);
            touchWater();
        }

        touchingPl =(Plant)getOneIntersectingObject(Plant.class);
        touchingAn =(Animal)getOneIntersectingObject(Animal.class);
        touchingEgg =(Egg)getOneIntersectingObject(Egg.class);
        touchingFood =(DieAnimal)getOneIntersectingObject(DieAnimal.class);
        
        if(MyWorld.plMode ==2 && touchingAn !=null){
            if(Math.abs(touchingAn.predation - predation) <= MyWorld.classificationOfSpecies){
                touchingAn.teamNum=0;
            }
            else{
                touchingAn.teamNum=1;
            }
        }
        if(MyWorld.plMode ==2 && touchingEgg !=null){
            if(Math.abs(touchingEgg.predation - predation) <= MyWorld.classificationOfSpecies){
                touchingEgg.teamNum=0;
            }
            else{
                touchingEgg.teamNum=1;
            }
        }
        
        if(inHole){
            touchingFood =null;
        }
        if(touchingPl !=null && inHole!= touchingPl.inHole){
            touchingPl =null;
        }
        if(touchingAn !=null && inHole!= touchingAn.inHole){
            touchingAn =null;
        }
        if(touchingEgg !=null && inHole!= touchingEgg.inHole){
            touchingEgg =null;
        }
        
        touchHole();
    }
    
    public void dive(){
        if(touchWater && location==1){
            location=3;
        }
    }
    public void up(){
        if(location==3 && !inHole){
            location=1;
        }
    }
    
    public void swim(){
        if(!hibernation){
            if(respiratorySystem<=0.5 && location==1 || extraction !=null && extraction.location==3 && location==1 || touchingPl !=null && predation <0.7 && satiety < maxSatiety && location==1 && touchingPl.location==3){
                dive();
            }
            if(touchingFood !=null && predation >0.3 && foodNeed > 0.1 && location==3 || extraction !=null && extraction.location==1 && location==3 || touchingPl !=null && predation <0.7 && foodNeed > 0.3 && location==3 && touchingPl.location==1){
                up();
            }
        }

        if(air<=0 && location==3 && respiratorySystem>0.5){
            up();
        }
        if(air<=0 && location != 3 && respiratorySystem <= 0.5){
            dive();
        }
        
        if(!touchWater && location==3){
            up();
        }

        if(location != 3 && air<=0 && respiratorySystem>0.5){
            air=1;
        }
        else if(air<=0 && respiratorySystem<=0.5 && location == 3){
            air=1;
        }
    }
    
    public void climb(){
        if(canClimb>0.5 && location==1 && !hibernation && !inHole){
            if(touchingPl !=null && touchingPl.location==1 && touchingPl.maxSize >maxSize){
                location=2;
            }
        }
        if(location==2 && touchingPl ==null){
            location=1;
        }
    }
    
    public void fly(){
        if(foodNeed >= 0.7 && fly==1 || waterNeed >= 0.7 && fly==1){
            fly=0;
            stopFly=1;
        }
        if(tim> period && location==0 && satiety >(maxSatiety /3)*2 && water >(maxWater /3)*2 && touchingAn !=null && touchingAn.tim> touchingAn.period && touchingAn.teamNum==teamNum){
            fly=0;
            stopFly=1;
        }
        if(touchingPl !=null && predation <0.7 && foodNeed > 0.3 && location==0){
            fly=0;
            stopFly=1;
        }
        if(hibernation){
            fly=0;
            stopFly=1;
        }
        if(touchingAn !=null && predation >0.3 && touchingAn.teamNum!=teamNum){
            if(foodNeed > 0.3 && touchingAn.location==1){
                fly=0;
                stopFly=1;
            }
        }
        if(flyCof >1 && fly==0){
            flyCof -=0.05;
        }
        if(fly==1){
            if(flyCof >=1 && flyCof <2){
                flyCof +=0.05;
            }
            else if(flyCof <1){
                flyCof =1;
            }
            if(flyCof >2){
                flyCof =2;
            }
        }
        if(fly==0 && flyCof ==1 && location==0){
            location=1;
        }
        else if(fly==1){
            location=0;
        }
    }
    
    public void die(){
        if(satiety <=0){
            hp--;
        }
        if(myAge >age){
            hp =0;
        }
        if(water <=0){
            hp--;
        }
        if(air<=0){
            hp--;
        }
        if(hp <=0){
            if(MyWorld.observedAnimal ==this){
                MyWorld.observedAnimal =null;
            }
            myPl.myAn--;
            DieAnimal da=new DieAnimal(size, satiety, maxSatiety);
            getWorld().addObject(da,getX(),getY());
            getWorld().removeObject(this);
        } 
    }
}

