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
    int moveBack;
    int action;
    int stopMoveForward;
    int r1;
    int r;
    int turnToR1;
    int rivalry;
    int timer;
    int x;
    int y;
    int start;
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
    
    int maxWater1;
    int maxWater;
    int drink;
    int water;
    int waterValueForBar;
    
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
    
    double cof;
    int satietyValueForBar;
    int maxSatiety1;
    int maxSatiety;
    int satiety;
    int fEnergy;
    int eat;
    
    int poison;
    
    double hibernationCof;
    boolean hibernation;
    
    double rootLength;

    //добыча
    Animal extraction;
    Plant extraction1;
    
    //партнер
    Plant pl;
    
    Animal animal;
    Plant plant;
    Egg egg;
    DieAnimal food;
    
    public Plant(ArrayList<Double> dna1, int food1, int water1, boolean inHole){
        this.inHole=inHole;
        if(this.inHole){
            ist=1;
        }
        dna = new ArrayList<>(dna1);
        r = Greenfoot.getRandomNumber(360);
        
        for(int i = 0; i< dna.size(); i++){
            double d= dna.get(i);
            if(d>=1){
                if(Greenfoot.getRandomNumber(3)+1==1){
                    d+=d*MyWorld.cofOfEvolution;
                }
                else if(Greenfoot.getRandomNumber(2)+1==1){
                    d-=d*MyWorld.cofOfEvolution;
                }
            }
            else if(d<1){
                if(Greenfoot.getRandomNumber(3)+1==1){
                    d+=MyWorld.cofOfEvolution;
                }
                else if(Greenfoot.getRandomNumber(2)+1==1){
                    d-=MyWorld.cofOfEvolution;
                }
            }
            dna.set(i, d);
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
            ageFodGrow =10;
            dna.set(1, (double) ageFodGrow);
        }
        
        if(dna.get(2)>=0){
            maxWater1 = dna.get(2).intValue();
            maxWater = maxWater1 /2;
            water = maxWater;
        }
        else{
            maxWater1 =80000;
            maxWater = maxWater1 /2;
            water =water1;
            dna.set(2, (double) maxWater);
        }
        
        if(dna.get(3)>=0){
            period = dna.get(3).intValue();
        }
        else{
            period =10;
            dna.set(3, (double) period);
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
            heatCof =0.5;
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
            age=80;
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
            maxSize =4;
            size =(int)(maxSize *0.1);
            if(size <=0){
                size =1;
            }
            dna.set(7, (double) maxSize);
        }
        
        if(dna.get(8)>=0){
            fertility = dna.get(8).intValue();
        }
        else{
            fertility =1;
            dna.set(8, (double) fertility);
        }
        
        if(dna.get(9)>=0){
            distToChild = dna.get(9).intValue();
            if(distToChild <=0){
                distToChild =1;
            }
        }
        else{
            distToChild =20;
            dna.set(9, (double) distToChild);
        }
        
        if(dna.get(10)>=0){
            radius= dna.get(10).intValue();
        }
        else{
            radius=100;
            dna.set(10, (double)radius);
        }
        
        if(dna.get(11)>=0){
            cof= dna.get(11);
        }
        else{
            cof=4;
            dna.set(11, cof);
        }
        
        if(dna.get(12)>=0){
            poison= dna.get(12).intValue();
        }
        else{
            poison=0;
            dna.set(12, (double)poison);
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
            rootLength =0.0;
            dna.set(14, rootLength);
        }
        if(rootLength >1){
            rootLength =1;
        }
        else if(rootLength <0){
            rootLength =0;
        }
        
        if(dna.get(15)>=0){
            maxSatiety1 = dna.get(15).intValue();
            maxSatiety = maxSatiety1 /2;
            satiety =food1;
        }
        else{
            maxSatiety1 = maxSize *35000;
            maxSatiety = maxSatiety1 /2;
            satiety = maxSatiety;
            dna.set(15, (double) maxSatiety);
        }
        
        if(moveCof >=0.5){
            waterSpeed =(double)1/ maxSize;
        }
        if(moveCof >=0.7){
            speed=(double)1/ maxSize;
        }
        if(moveCof >=0.9){
            canClimb =1;
        }
        maxHp = size;
        hp = maxHp;
        area =50* size /20;
        damage=(int)(size * predation);
        if(damage<=0 && predation >0.3){
            damage=1;
        }
        tim=0;
        updateImage();
        MyWorld.plants++;
    }
    int transparent;
    public void updateImage(){
        size = maxSize;
        transparent=255;
        if(ageFodGrow > myAge){
            size =(int)(maxSize *((double)(myAge)/ ageFodGrow));
            maxSatiety =(int)((((double)(myAge)/(ageFodGrow *2))+0.5)* maxSatiety1);
            maxWater =(int)((((double)(myAge)/(ageFodGrow *2))+0.5)* maxWater1);
        }
        if(ageFodGrow >= myAge){
            maxSatiety = maxSatiety1;
            maxWater = maxWater1;
        }
        size1= size;
        if(location==2){
            size1=(int)(size *1.5);
        }
        else if(location==3 || inHole){
            transparent=100;
        }
        
        if(size <=0){
            size =1;
        }
        if(size1<=0){
            size1=1;
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
        
        maxHp = size;
        area =50* size /20;
        damage=(int)(size * predation);
        maxSatiety = size *17000;
        eat= size *700;
        drink=700* size;
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
                if(dist<=(w.size/2)-(size /2)){
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
            if(dist<=(int) ((water.size / 2) * (1 + rootLength)) && moveCof < 0.5){
                canDrink =true;
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
    
    public void act() 
    {
        if(start==0){
            dy=getY();
            start=1;
        }
        if(moveCof > 0.5 && !hibernation){
            turnTo();
            move();
            touchWater();
        }
        moveBack();
        
        x=getX();
        y=getY();
        surroundingTemp =(int)(((double)(x)/getWorld().getWidth())*MyWorld.temp);
        
        if(touchWater || location==3){
            surroundingTemp -=10;
        }
        hibernation();
        if(timer>=50){
            myAge++;
            tim++;
            timer=0;
            if(satietyValueForBar >=7 && waterValueForBar >=7 && hp < maxHp && creatureTemp >= needTemp -10 && creatureTemp <= needTemp +10){
                hp++;
            }
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
        if(myAge >= ageFodGrow && satietyValueForBar >=7 && waterValueForBar >=7 && !hibernation){
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
        creatureTemp = (int) (needTemp * heatCof + (surroundingTemp * (1 - heatCof)));
        if(satiety > 0 && satiety <= maxSatiety){
            satietyValueForBar = satiety / (maxSatiety / 10);
        }
        if(water > 0 && water <= maxWater){
            waterValueForBar = water / (maxWater / 10);
        }
        if(creatureTemp < needTemp - 10 || creatureTemp > needTemp + 10){
            satiety = satiety - (int) (size * size * size * hibernationCof);
            water = water - (int) (size * size * size * hibernationCof);
            timer += hibernationCof;
            hibernation = true;
        }
        else{
            satiety = satiety - (size * size * size);
            water = water - (size * size * size);
            timer++;
            hibernation = false;
        }
        water = water - fEnergy;
    }
    
    public void photosynthesis(){
        fEnergy = (int) (size * size * 5 * cof);
        MyWorld.res -= size;
        if(plant != null && location != 2){
            rivalry = 0;
            for(Plant p : getIntersectingObjects(Plant.class)){
                rivalry = rivalry + (int) Math.pow(p.size, 3);
            }
            fEnergy = fEnergy - rivalry;
        }
        water -= fEnergy;
    }
    
    int childX;
    int childY;
    
    int rotToChild;
    public void replicase(){
        pl=null;
        if(getObjectsInRange(radius, Plant.class).size()>0){
            pl=getObjectsInRange(radius, Plant.class).get(0);
        }
        if(inHole == pl.inHole && tim > period && pl.location == location && pl.tim > pl.period && Math.abs(pl.predation - predation) <= 0.1){
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
                Plant child = new Plant(dna2, maxSize * maxSize * maxSize *1000, maxSize * maxSize * maxSize *1000, inHole);

                childX =((x+pl.getX())/2)+Greenfoot.getRandomNumber(distToChild)- distToChild /2;
                childY =((y+pl.getY())/2)+Greenfoot.getRandomNumber(distToChild)- distToChild /2;
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
                try{
                    getWorld().addObject(child, childX, childY);
                }catch(Exception e){
                    getWorld().addObject(child, x+Greenfoot.getRandomNumber(distToChild)- distToChild /2, y+Greenfoot.getRandomNumber(distToChild)- distToChild /2);
                }
                satiety = satiety - maxSize * maxSize * maxSize *1000;
                water = water - maxSize * maxSize * maxSize *1000;
                tim=0;
            }
        }
        else if(tim> period *2){
            for(int i = 0; i< fertility; i++){
                dna2.clear();
                dna2.addAll(dna);
                Plant child = new Plant(dna2, maxSize * maxSize * maxSize *1000, maxSize * maxSize * maxSize *1000, inHole);

                childX = x + Greenfoot.getRandomNumber(distToChild) - distToChild / 2;
                childY = y + Greenfoot.getRandomNumber(distToChild) - distToChild / 2;
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
                satiety = satiety - maxSize * maxSize * maxSize *1000;
                water = water - maxSize * maxSize * maxSize *1000;
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
        else if(getObjectsInRange(radius, Water.class).size()>0 && waterValueForBar <7){
            turnTowards(getObjectsInRange(radius, Water.class).get(0).getX(), getObjectsInRange(radius, Water.class).get(0).getY());
            r1=getRotation();
            turnToR1 =1;
        }
        else if(predation >0.3 && food!=null && satietyValueForBar <7){
            turnTowards(food.getX(), food.getY());
            r1=getRotation();
            turnToR1 =1;
        }
        else if(predation >0.3 && animal!=null && satietyValueForBar <7){
            turnTowards(animal.x, animal.y);
            r1=getRotation();
            turnToR1 =1;
        }
        else if(predation >0.3 && egg!=null && satietyValueForBar <7){
            turnTowards(egg.getX(), egg.getY());
            r1=getRotation();
            turnToR1 =1;
        }
        else if(predation <=0.3 && animal!=null && animal.predation <0.7){
            turnTowards(animal.x, animal.y);
            if(r1>=180){
                r1=getRotation()-(180-Greenfoot.getRandomNumber(30));
            }
            else{
                r1=getRotation()+(180-Greenfoot.getRandomNumber(30));
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
            water = water +drink;
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
        extraction1 =null;
        if(animal!=null && predation >0.3){
            extraction =animal;
            if(extraction.location==location){
                extraction.hp -=((damage+poison)*(1- extraction.protection));
                extraction.hunterPlant =this;
            }
        }
        
        if(food!=null && predation >0.3 && satiety < maxSatiety && location==1){
            satiety = satiety +eat;
            food.satiety -=eat;
            if(satietyValueForBar <9){
                stopMoveForward =1;
            }
        }
    }
    
    public void temp(){
        creatureTemp =(int)((needTemp * heatCof +(surroundingTemp *(1- heatCof))));
        satiety -=(int)(Math.abs(surroundingTemp - needTemp)* heatCof * size);
        water = water -(int)(Math.abs(surroundingTemp - needTemp)* heatCof * size);
    }
    
    int turned;
    int rotSpeed;
    public void move(){
        startX =dx;
        startY =dy;
        if(moveBack >0 && action ==0){
            if(!touchWater){
                doubleMove(-speed);
            }
            else{
                doubleMove(-waterSpeed);
            }
            if(moveBack >0){
                moveBack = moveBack -1;
            }
            action =1;
        }
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
                satiety -= rotSpeed;
                turned =1;
            }
            if(turnToR1 ==1 && r1<r){
                r=r- rotSpeed;
                if(r-r1>180){
                    r=r+(rotSpeed *2);
                }
                satiety -= rotSpeed;
                turned =1;
            }
            if(r-(rotSpeed -1)<=r1 || r1<r+(rotSpeed -1)){
                r=r1;
                turnToR1 =0;
            }
        }
        if(action ==0 && stopMoveForward ==0 && !inHole || inHole && turned ==0 && action ==0 && stopMoveForward ==0){
            if(!touchWater && !inHole || location==1){
                doubleMove(speed* size);
                satiety -=(int)(speed* maxSize);
                water -=(int)(speed* maxSize);
            }
            else if(touchWater && !inHole || location==3){
                doubleMove(waterSpeed * size);
                satiety -=(int)(waterSpeed * maxSize);
                water -=(int)(waterSpeed * maxSize);
            }
        }
        r %= 360;

        setRotation(0);

        turned =0;
        stopMoveForward =0;
        action =0;
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
            if(extraction !=null && extraction.location==1 && location==3 || extraction1 !=null && extraction1.location==1 && location==3){
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
        if(location==2 && plant==null){
            location=1;
        }
    }
    
    public void die(){
        if(water <=0){
            hp--;
        }
        if(satiety <=0){
            hp--;
        }
        if(myAge >age){
            hp =0;
        }
        if(hp <=0){
            if(MyWorld.observedPlant == this){
                MyWorld.observedPlant = null;
            }
            MyWorld.plants--;
            getWorld().removeObject(this);
        }
    }
}
