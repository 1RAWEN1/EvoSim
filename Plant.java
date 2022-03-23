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
    ArrayList<Double> dna;
    ArrayList<Double> dna2 = new ArrayList<>();
    int location=1;
    int action;
    int stopMoveForward;
    int r1;
    int r;
    int turnToR1;
    int timer;
    int x;
    int y;
    GreenfootImage image;
    
    double startX;
    double startY;
    
    //эволюционирующие показатели
    double moveCof;
    double speed;
    double waterSpeed;
    double canClimb;
    
    int ageFodGrow;
    int myAge;

    int maxWater;
    int drink;
    int water;
    int waterValueForBar;
    int maxWater1;
    
    int period;
    int tim;
    
    double predation;
    int damage;
    
    double heatCof;
    int creatureTemp;
    int surroundingTemp;
    private final int needTemp = 36;
    
    int age;
    
    int maxSize;
    int size;
    int size1;
    int hp;
    int maxHp;
    
    int fertility;
    int area;
    
    int distToChild;
    
    int radius;
    int radius1;
    
    double cof;
    int satietyValueForBar;
    int maxSatiety;
    int satiety;
    int fEnergy;
    int eat;
    int maxSatiety1;
    
    int poison;
    
    double hibernationCof;
    boolean hibernation;
    
    double rootLength;

    int radiusView;

    //добыча
    Animal extraction;
    
    //партнер
    Plant pl;
    
    Animal animal;
    Plant plant;
    Egg egg;
    DieAnimal food;

    private final int foodCof = 5000;//7000
    
    public Plant(ArrayList<Double> dna1, int food1, int water1, boolean inHole, boolean mutate){
        this.inHole=inHole;
        if(this.inHole){
            ist=1;
        }
        dna = new ArrayList<>(dna1);
        r = Greenfoot.getRandomNumber(360);

        if(mutate) {
            for (int i = 0; i < dna.size(); i++) {
                double d = dna.get(i);
                if (d != -1) {
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

                    if (d < 0) {
                        d = 0;
                    }
                    dna.set(i, d);
                }
            }
        }
        
        if(dna.get(0)>=0){
            moveCof = dna.get(0);
        }
        else{
            moveCof =0.0;
            dna.set(0, moveCof);
        }
        if(moveCof >1){
            moveCof =1;
        }
        else if(moveCof <0){
            moveCof =0;
        }

        if(dna.get(1)>=0){
            ageFodGrow = dna.get(1).intValue();
        }
        else{
            ageFodGrow = 5;//4
            dna.set(1, 5.5);
        }

        ageFodGrow = Math.max(ageFodGrow, maxSize);
        
        if(dna.get(3)>=0){
            period = dna.get(3).intValue();
        }
        else{
            period = 3;//3
            dna.set(3, 3.5);
        }
        
        if(dna.get(4)>=0){
            predation = dna.get(4);
        }
        else{
            predation =0.0;
            dna.set(4, predation);
        }
        if(predation >1){
            predation =1;
        }
        else if(predation <0){
            predation =0;
        }
        
        if(dna.get(5)>=0){
            heatCof = dna.get(5);
        }
        else{
            heatCof =0.6;
            dna.set(5, heatCof);
        }
        if(heatCof >0.9){
            heatCof =0.9;
        }
        else if(heatCof <0){
            heatCof =0;
        }
        
        if(dna.get(6)>=0){
            age= dna.get(6).intValue();
        }
        else{
            age=80;//125 80
            dna.set(6, (double)age);
        }
        
        if(dna.get(7)>=0){
            maxSize = dna.get(7).intValue();
            if(maxSize <=0){
                maxSize =1;
            }
            size =(int)(maxSize *0.1);
            if(size <=0){
                size =1;
            }
        }
        else{
            maxSize = 5;
            size =(int)(maxSize *0.1);
            if(size <= 0){
                size = 1;
            }
            dna.set(7, 5.5);
        }

        if(dna.get(2)>=0){
            maxWater1 = dna.get(2).intValue();
            water = water1;
        }
        else{
            maxWater1 = foodCof;
            water = maxWater1;
            dna.set(2, (double) maxWater1);
        }
        
        if(dna.get(8)>=0){
            fertility = (int) (1 / dna.get(8));
        }
        else{
            fertility =1;
            dna.set(8, (double) fertility);
        }

        fertility = Math.max(fertility, 1);
        
        if(dna.get(9)>=0){
            distToChild = dna.get(9).intValue();
            if(distToChild <=0){
                distToChild =1;
            }
        }
        else{
            distToChild = 180;//200
            dna.set(9, (double) distToChild);
        }
        
        if(dna.get(10)>=0){
            radius= dna.get(10).intValue();
        }
        else{
            radius = 180;//200
            dna.set(10, (double) radius);
        }
        
        /*if(dna.get(11)>=0){
            cof = dna.get(11);
        }
        else{
            cof = 1.4;//1.6
            dna.set(11, cof);
        }

        if(cof > 2.4){
            cof = 2.4;
        }*/
        cof = 1.4;//1.6
        
        if(dna.get(12)>=0){
            poison = (int)(dna.get(12) * 100);
        }
        else{
            poison = 0;
            dna.set(12, (double) poison / 100);
        }
        
        if(dna.get(13)>=0){
            hibernationCof = dna.get(13);
        }
        else{
            hibernationCof =0;
            dna.set(13, hibernationCof);
        }
        if(hibernationCof <0){
            hibernationCof =0;
        }
        if(hibernationCof >1){
            hibernationCof =1;
        }
        
        if(dna.get(14)>=0){
            rootLength = dna.get(14);
        }
        else{
            rootLength = 0.0;
            dna.set(14, rootLength);
        }
        if(rootLength > 1){
            rootLength = 1;
        }
        else if(rootLength < 0){
            rootLength = 0;
        }
        
        if(dna.get(15)>=0){
            maxSatiety1 = dna.get(15).intValue();
            satiety = food1;
        }
        else{
            maxSatiety1 = foodCof;
            satiety = maxSatiety1;
            dna.set(15, (double) maxSatiety1);
        }

        if(dna.get(16)>=0){
            radius1 = dna.get(16).intValue();
        }
        else{
            radius1 = 30;
            dna.set(16, (double) radius1);
        }
        
        if(moveCof >=0.5){
            waterSpeed = 0.3;
        }
        if(moveCof >=0.7){
            speed= 0.3;
        }
        if(moveCof >= 0.9){
            canClimb = 1;
        }
        maxHp = getFullSize() * 10;
        hp = maxHp;
        area =50* size /20;
        damage=(int)(5 * predation);
        tim=0;
        radiusView = radius1 / maxSize;
        updateImage();
        MyWorld.plants++;
    }

    public int getFullSize(){
        return (int)Math.pow(size, 3);
    }

    int transparent;
    public void updateImage(){
        size = maxSize;
        transparent=255;
        if(ageFodGrow >= myAge){
            size =(int)(maxSize *((double) myAge/ ageFodGrow));
        }
        if(size <= 0){
            size = 1;
        }

        radiusView = (int) (((double) size / maxSize) * radius1);
        maxSatiety = size * maxSatiety1;
        maxWater = size * maxWater1;

        size1= size;
        if(location==2){
            size1=(int)(size *1.5);
        }
        else if(location==3 || inHole){
            transparent=100;
        }
        
        if(!hibernation){
            image = new GreenfootImage("pl.png");
            image.scale(size1, size1);
            image.setTransparency(transparent);
        }
        else{
            image = new GreenfootImage(size, size);
            image.setColor(new Color(153, 217, 234, transparent));
            image.fillOval(0, 0, size, size);
        }
        setImage(image);
        
        maxHp = getFullSize() * 10;
        area =50* size /20;
        damage=(int)(5 * predation);
        eat= size *2000;
        drink=2000* size;
    }
    
    Water w;
    int dist;
    boolean touchWater;
    boolean canDrink;
    public void touchWater(){
        touchWater=false;
        if(!inHole){
            for(int i=0;i<getIntersectingObjects(Water.class).size();i++){
                w=getIntersectingObjects(Water.class).get(i);
                dist=(int)Math.sqrt(Math.pow(getX()-w.getX(),2)+Math.pow(getY()-w.getY(),2));
                if(dist<=(w.size/2)-(size /2) || w.ocean){
                    touchWater=true;
                    break;
                }
            }
        }
    }
    
    public void canDrink(){
        canDrink =false;
        for(Water water : getObjectsInRange(getWorld().getWidth(),Water.class)){
            dist=(int) Math.sqrt(Math.pow(getX() - water.getX(), 2) + Math.pow(getY() - water.getY(), 2));
            if(touchWater || dist<=(int) ((water.size / 2) * (1 + rootLength)) && moveCof < 0.5){
                canDrink =true;
            }
            else if(getY() < ((double)getWorld().getHeight() / 2) + (getWorld().getHeight() * rootLength / 4) && moveCof < 0.5){
                canDrink = true;
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
            if(dist<=(h.size/2)-(size /2)){
                touchHole=true;
                break;
            }
        }
        if(touchHole && ist==0 && h.loc==location){
            if(!inHole && h.size> maxSize){
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
                if(dist<=(hr.size/2)-(size /2)){
                    touchHR=true;
                    break;
                }
            }
        
            if(hr!=null && inHole && hr.loc!=location){
                location=hr.loc;
            }
        }
    }

    double maskCof;
    int start = 0;
    public void act() 
    {
        if(start==0){
            //Math.max(200, Math.min(getY(), 500))
            //Math.min(Math.max(getX(), 300), 900)
            setLocation(Math.min(Math.max(getX(), 300), 900), getY());
            dx = getX();
            dy = getY();
            start=1;
        }
        if(moveCof > 0.5 && !hibernation){
            turnTo();
            move();
            touchWater();
            moveBack();
        }

        calcMask();
        turned = false;
        move = false;
        
        x=getX();
        y=getY();
        surroundingTemp =(int)(((double)(x)/getWorld().getWidth())*MyWorld.temp);
        
        if(touchWater || location==3){
            surroundingTemp -=10;
        }
        hibernation();
        if(timer>=MyWorld.year){
            myAge++;
            tim++;
            timer=0;
        }
        
        fEnergy =0;
        updateImage();
        if(!hibernation && !inHole || !hibernation && touchHole){
            photosynthesis();
        }
        satiety = satiety + fEnergy;
        if(satiety > maxSatiety){
            satiety = maxSatiety;
        }
        
        temp();
        if(MyWorld.plants < 200 && myAge >= ageFodGrow && fertility * maxSatiety1 < satiety && fertility * maxWater1 < water && !hibernation){
            replicase();
        }
        if(!hibernation){
            drink();
            attack();
        }
        dive();
        climb();
        die();
    }  
    
    public void hibernation(){
        //creatureTemp = (int) (needTemp * heatCof + (surroundingTemp * (1 - heatCof)));
        if(satiety > maxSatiety){
            satiety = maxSatiety;
        }
        if(satiety > 0){
            satietyValueForBar = satiety / (maxSatiety / 10);
        }
        if(water > maxWater){
            water = maxWater;
        }
        if(water > 0){
            waterValueForBar = water / (maxWater / 10);
        }
        if(creatureTemp < needTemp - 10 || creatureTemp > needTemp + 10){
            satiety = satiety - (int) (size * size * size * hibernationCof) - 1;
            water = water - (int) (size * size * size * hibernationCof) - 1;
            timer += hibernationCof;
            hibernation = true;
        }
        else{
            satiety = satiety - (size * size * size);
            water = water - (size * size * size);
            timer++;
            hibernation = false;
            water = water - fEnergy;
        }


        if(hp < maxHp && Math.abs(creatureTemp - needTemp) <= 10){
            hp++;
        }
    }
    
    public void photosynthesis(){
        fEnergy = (int) (size * size * 5 * cof * (1 - predation));
        if(location != 2 && moveCof < 0.5){
            for(Plant p : getObjectsInRange((int) (rootLength * size), Plant.class)){
                if(p.moveCof < 0.5 && p.rootLength > 0)
                p.satiety -= (int) Math.pow(size, 3);
            }
        }
        for(Plant p : getIntersectingObjects(Plant.class)){
            p.satiety -= (int) Math.pow(size, 3);
        }

        water -= fEnergy;
    }
    
    int childX;
    int childY;
    
    int rotToChild;

    //double maxPlantDist;
    //double distToObj;
    //ArrayList<Plant> plants = new ArrayList<>();
    public boolean mySpecies(Plant p){
        boolean mySpecies = true;
        for(int i = 0; i < dna.size(); i++){
            if(Math.min(dna.get(i), p.dna.get(i)) <= 1 && Math.abs(dna.get(i) - p.dna.get(i)) > MyWorld.classificationOfSpecies || Math.min(dna.get(i), p.dna.get(i)) > 1 && Math.abs(dna.get(i) - p.dna.get(i)) / Math.max(dna.get(i), p.dna.get(i)) > MyWorld.classificationOfSpecies){
                mySpecies = false;
                break;
            }
        }
        return mySpecies;
    }
    public void replicase(){
        pl=null;
        //maxPlantDist = 0;
        /*for(Plant p:getObjectsInRange(radius, Plant.class)){
            distToObj = Math.pow(getX() - p.getX(), 2) + Math.pow(getY() - p.getY(), 2);
            if(distToObj > maxPlantDist){
                pl = p;
            }
        }*/
        if(getObjectsInRange(radius, Plant.class).size() > 0){
            for(int i = 0; i < getObjectsInRange(radius, Plant.class).size(); i++) {
                pl = getObjectsInRange(radius, Plant.class).get(i);
                if(mySpecies(pl)){
                    break;
                }
                else{
                    pl = null;
                }
                //int plantsAroundMe = getObjectsInRange(radius, Plant.class).size();
                //pl = getObjectsInRange(radius, Plant.class).get(Greenfoot.getRandomNumber(plantsAroundMe));
            }
        }
        double randRot = Math.toRadians(Greenfoot.getRandomNumber(360));
        int dist = Math.max(maxSize * 2, Greenfoot.getRandomNumber(distToChild));

        if(pl!=null && inHole == pl.inHole && tim > period && pl.location == location && pl.tim > pl.period && Math.abs(pl.predation - predation) <= MyWorld.classificationOfSpecies && pl.myAge >= pl.ageFodGrow){
            for(int i = 0; i < fertility; i++){
                dna2.clear();
                for(int i1 = 0; i1 < dna.size(); i1++){
                    if(Greenfoot.getRandomNumber(2)==1){
                        dna2.add(dna.get(i1));
                    }
                    else{
                        dna2.add(pl.dna.get(i1));
                    }
                }
                Plant child = new Plant(dna2, maxSatiety1, maxWater1, inHole, true);

                childX = x + (int) (Math.cos(randRot) * dist);
                childY = y + (int) (Math.sin(randRot) * dist);
                if(inHole){
                    inHole();
                    if(hr!=null && Math.sqrt(Math.pow(hr.getX()- childX,2)+Math.pow(hr.getY()- childY,2)) > (double) hr.size / 2){
                        setLocation(hr.getX(),hr.getY());
                        turnTowards(childX, childY);
                        rotToChild =getRotation();
                        childX =hr.getX()+(int)(Math.cos(Math.toRadians(rotToChild))*((hr.size/2)-1));
                        childY =hr.getY()+(int)(Math.sin(Math.toRadians(rotToChild))*((hr.size/2)-1));
                    }
                }
                getWorld().addObject(child, childX, childY);
                satiety = satiety - maxSatiety1;
                water = water - maxWater1;
                tim=0;
            }
        }
        else if(tim> period *2){
            for(int i = 0; i< fertility; i++){
                dna2.clear();
                dna2.addAll(dna);
                Plant child = new Plant(dna2, maxSatiety1, maxWater1, inHole, false);

                childX = x + (int) (Math.cos(randRot) * (size + 1));
                childY = y + (int) (Math.sin(randRot) * (size + 1));
                if(inHole){
                    inHole();
                    if(hr!=null && Math.sqrt(Math.pow(hr.getX()- childX,2)+Math.pow(hr.getY()- childY,2)) > (double) hr.size / 2){
                        setLocation(hr.getX(),hr.getY());
                        turnTowards(childX, childY);
                        rotToChild =getRotation();
                        childX =hr.getX()+(int)(Math.cos(Math.toRadians(rotToChild))*((hr.size/2)-1));
                        childY =hr.getY()+(int)(Math.sin(Math.toRadians(rotToChild))*((hr.size/2)-1));
                    }
                }
                getWorld().addObject(child, childX, childY);
                satiety = satiety - maxSatiety1;
                water = water - maxWater1;
                tim=0;
            }
        }
    }
    
    public void turnTo(){
        animal=null;
        food=null;
        if(getObjectsInRange(radiusView, Animal.class).size()>0){
            animal=getObjectsInRange(radiusView, Animal.class).get(0);
            if(animal.inHole!=inHole){
                animal=null;
            }
        }
        if(getObjectsInRange(radiusView, DieAnimal.class).size()>0 && !inHole){
            food=getObjectsInRange(radiusView, DieAnimal.class).get(0);
        }
        if(inHole && !touchHole && getObjectsInRange((maxSize +2)*2,Hole.class).size()>0){
            turnTowards(getObjectsInRange((maxSize +2)*2,Hole.class).get(0).getX(), getObjectsInRange((maxSize +2)*2,Hole.class).get(0).getY());
            r1=getRotation();
            turnToR1 =1;
        }
        else if(isAtEdge()){
            turnTowards(getWorld().getWidth()/2, getWorld().getHeight()/2);
            r1=getRotation();
            turnToR1 =1;
        }
        else if(getObjectsInRange(radiusView, Water.class).size()>0 && waterValueForBar <7){
            turnTowards(getObjectsInRange(radiusView, Water.class).get(0).getX(), getObjectsInRange(radiusView, Water.class).get(0).getY());
            r1=getRotation();
            turnToR1 =1;
        }
        else if(predation >0.3 && food!=null && satietyValueForBar <7){
            turnTowards(food.getX(), food.getY());
            r1=getRotation();
            turnToR1 =1;
        }
        else if(predation >0.3 && animal!=null && satietyValueForBar <7){
            turnTowards(animal.getX(), animal.getY());
            r1=getRotation();
            turnToR1 =1;
        }
        else if(predation >0.3 && egg!=null && satietyValueForBar <7){
            turnTowards(egg.getX(), egg.getY());
            r1=getRotation();
            turnToR1 =1;
        }
        else if(predation <=0.3 && animal!=null && animal.canEatPlant()){
            turnTowards(animal.getX(), animal.getY());
            if(r1>=180){
                r1=getRotation() - 180;
            }
            else{
                r1=getRotation() + 180;
            }
            turnToR1 =1;
        }
        else{
            randomMove();
        }
    }
    public void randomMove(){
        r1=Greenfoot.getRandomNumber(360);
        turnToR1 =1;
    }
    
    public void drink(){
        canDrink();
        if(canDrink && water < maxWater && location!=2 || water < maxWater && location==3){
            water = water + drink;
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
        
        extraction =null;
        if(animal != null && predation > 0.3){
            extraction = animal;
            if(extraction.location == location){
                extraction.hp -= (int) (poison * (1 - extraction.poisonProtection)) + Math.max(damage - extraction.getProtection(), 0);

                extraction.hunterPlant = this;

                satiety -= poison * 200;

                extraction.die();
            }
        }
        
        if(food!=null && satiety < maxSatiety && location==1 && predation > 0.3){
            food.satiety -= Math.min(eat, maxSatiety - satiety);
            satiety += Math.min(eat, maxSatiety - satiety);
            if(satietyValueForBar < 9){
                stopMoveForward = 1;
            }
        }
    }
    
    public void temp(){
        creatureTemp =(int)((needTemp * heatCof +(surroundingTemp *(1- heatCof))));
        //satiety -=(int)(Math.abs(surroundingTemp - needTemp)* heatCof * 0.1 * getFullSize());
        //water = water -(int)(Math.abs(surroundingTemp - needTemp)* heatCof * 0.1 * getFullSize());
    }
    
    boolean turned;
    boolean move;
    int rotSpeed;
    public void move(){
        startX =dx;
        startY =dy;
        if(!touchWater && !inHole|| location==1){
            rotSpeed =(int)(speed* size *15);
        }
        else if(touchWater && !inHole|| location==3){
            rotSpeed =(int)(waterSpeed * size *15);
        }
        if(r-(rotSpeed -1)>r1|| r1>r+(rotSpeed -1)){
            if(turnToR1 ==1 && r1>r){
                r=r+ rotSpeed;
                if(r1-r>180){
                    r=r-(rotSpeed *2);
                }
                satiety -= rotSpeed * size;
                turned = true;
            }
            if(turnToR1 ==1 && r1<r){
                r=r- rotSpeed;
                if(r-r1>180){
                    r=r+(rotSpeed *2);
                }
                satiety -= rotSpeed * size;
                turned = true;
            }

            r %= 360;
            if(r<0){
                r += 360;
            }
            if(Math.abs(r1 - r) < rotSpeed){
                r = r1;
                turnToR1 = 0;
            }
        }
        if(action ==0 && stopMoveForward ==0 && !inHole || inHole && !turned && action ==0 && stopMoveForward ==0){
            if(touchWater && !inHole && location == 1 || location==3){
                doubleMove(waterSpeed * size);
                satiety -=(int)(Math.pow(waterSpeed, 2) * size);
                water -=(int)(Math.pow(waterSpeed, 2) * size);
                move = true;
            }
            else{
                doubleMove(speed* size);
                satiety -=(int)(Math.pow(speed, 2) * size);
                water -=(int)(Math.pow(speed, 2) * size);
                move = true;
            }
        }
        r %= 360;

        setRotation(0);

        stopMoveForward =0;
        action =0;
    }

    private void calcMask(){
        maskCof = (0.9 / Math.pow(size, 2)) * (1.0 - (move ? 0.5 : 0));
    }
    
    double dx;
    double dy;
    public void doubleMove(double v){
        dx+=v*Math.cos(Math.toRadians(r));
        dy+=v*Math.sin(Math.toRadians(r));
        
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
        
        if(moveCof >0.5){
            if(touchWater && waterSpeed ==0 && !inHole || inHole && !touchHR && location==1 ||
            !touchWater && !inHole && speed==0 || inHole && !touchHR && location==3){
                dx= startX;
                dy= startY;
                setLocation((int) startX,(int) startY);
            }
        }
        
        touchHole();
    }
    
    public void dive1(){
        if(touchWater && location==1){
            location=3;
        }
    }
    public void up(){
        if(location==3 && !inHole){
            location=1;
        }
    }
    
    public void dive(){
        touchWater();
        if(!hibernation){
            if(location==1){
                dive1();
            }
            if(extraction !=null && extraction.location==1 && location==3){
                up();
            }
        }
        
        if(!touchWater && location==3){
            up();
        }
    }
    
    public void climb(){
        if(moveCof > 0.5 && location == 1 && !hibernation && !inHole){
            if(plant!=null && plant.location==1 && plant.maxSize > maxSize){
                location=2;
            }
        }
        if(location==2 && plant==null || location == 2 && plant.location != 1){
            location=1;
        }
    }
    
    public void die(){
        if(water <=0 || satiety <= 0){
            hp-=50;
        }
        if(myAge >age){
            hp =0;
        }
        if(hp <= 0 && getWorld() != null){
            if(MyWorld.observedPlant == this){
                MyWorld.observedPlant = null;
            }
            MyWorld.plants--;
            getWorld().removeObject(this);
        }
    }
}
