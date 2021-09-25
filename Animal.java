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
    private final Player myPlayer;
    int teamNum; //speciesID
    ArrayList<Double> dna;
    private final ArrayList<Double> dna2=new ArrayList<>(); //childDNA
    int location;
    private boolean isStopFly = false;
    int animalRotation;
    int rotationToTarget;//rotation to target
    boolean isStopMoveForward = false;
    boolean canTurn = false;
    double timer;
    boolean isStart = false;
    GreenfootImage image;
    GreenfootImage fon;//world background
            
    double startX;// start location from X
    double startY;//start location from Y

    //food coordinates
    int foodX = -1;
    int foodY = -1;
    
    int maxAnimalSize;//animal max size
    int animalSize;//animal size now
    int imageSize;
    int maxHp;
    int hp;
    
    int maxAir;
    int air;
    
    double respiratorySystem;
    
    boolean canClimb;
    
    double hibernationCof;
    boolean hibernation;
    
    double liveBirth;
    
    int ageForGrow;//in this age animal parameters is max
    int myAge;//current age
    
    double flyingSpeed;
    double flyCof = 1.0;
    boolean fly = false;
    
    double poisonCof;
    int poison;
    
    double maskCof;
    double maxMaskCof;

    int maxWater;
    double thirst;
    int drink;
    int water;
    
    int period;
    int reproductiveTimer;
    int sex;

    double protection;
    
    double waterSpeed;
    
    double moveCof;
    
    int maxAge;
    
    int fertility;
    
    double speed;

    int radiusView;
    int maxRadiusView;
    
    double heatCof;
    int animalTemp;
    int surroundTemp;
    private final int needTemp = 36;
    
    double predation;
    int damage;

    int maxSatiety;
    int satiety;
    double starve;
    int eat;
    
    double digSpeed;
    
    //партнер
    Animal animalPartner;
    
    //на меня охотятся
    Plant hunterPlant;
    Animal hunterAnimal;
    
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

    private final int foodCof = 12000;
    
    public Animal(ArrayList<Double> dna1, Player pl, int tn, boolean inHole, int food, int water){
        this.inHole=inHole;
        if(this.inHole){
            isTouchingHole = true;
        }
        animalRotation =Greenfoot.getRandomNumber(360);
        rotationToTarget = animalRotation;
        teamNum=tn;
        myPlayer =pl;
        this.dna = new ArrayList<>(dna1);

        mutate();

        //create method initAnimalSize
        if(dna.get(18)>=0){
            maxAnimalSize =dna.get(18).intValue();
            if(maxAnimalSize <=0){
                maxAnimalSize =1;
            }
            animalSize =(int)(maxAnimalSize *0.1);
            if(animalSize <=0){
                animalSize =1;
            }
        }
        else{
            maxAnimalSize =3;
            animalSize =(int)(maxAnimalSize *0.1);
            if(animalSize <=0){
                animalSize =1;
            }
            dna.set(18, (double) maxAnimalSize);
        }
        distToChild = 2 * maxAnimalSize;
        fon = new GreenfootImage(maxAnimalSize, maxAnimalSize);
        
        if(dna.get(0)>=0){
            maxAir=dna.get(0).intValue();
            air=maxAir;
        }
        else{
            maxAir= maxAnimalSize *1000;
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
            canClimb=dna.get(2)>0.5;
        }
        else{
            canClimb=false;
            dna.set(2, 0.0);
        }
        if(dna.get(2)>1){
            dna.set(2, 1.0);
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
            maxMaskCof =dna.get(8);
        }
        else{
            maxMaskCof =0.0;
            dna.set(8, maxMaskCof);
        }
        if(maxMaskCof >1){
            maxMaskCof =1;
        }
        else if(maxMaskCof <0){
            maxMaskCof =0;
        }
        maskCof = maxMaskCof;
        
        if(dna.get(9)>=0){
            maxWater =dna.get(9).intValue();
            this.water =water;
        }
        else{
            maxWater = maxAnimalSize *foodCof;
            this.water = maxWater;
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
            waterSpeed = 0.5 / maxAnimalSize;
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
            maxAge =dna.get(14).intValue();
        }
        else{
            maxAge =70;
            dna.set(14, (double) maxAge);
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
            radiusView =dna.get(17).intValue();
        }
        else{
            radiusView =30;
            dna.set(17, (double) radiusView);
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

            if(myPlayer.predators < 2 && Greenfoot.getRandomNumber(20)==1 && canEatPlant()){
                predation =  1.0;
                dna.set(20, predation);

                //waterSpeed *= 0.66;
                //dna.set(12, waterSpeed);

                /*if(speed > 0){
                    speed += 0.05;
                    dna.set(16, speed);
                }*/
            }
        }
        else{
            predation = 0.0;
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
            satiety =food;
        }
        else{
            maxSatiety = maxAnimalSize *foodCof;
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
        maxHp = animalSize;
        hp = maxHp;
        reproductiveTimer =0;
        damage=(int)(animalSize * predation);
        if(damage<=1 && predation >=0.3){
            damage=1;
        }
        maxRadiusView = radiusView / maxAnimalSize;
        updateImage();
        if(MyWorld.plMode == 2) {
            if (canEatPlant() && canEatMeat()) {
                myPlayer.omnivorous++;
            }
            else if(canEatPlant()){
                myPlayer.myAn++;
            }
            else{
                myPlayer.predators++;
            }
        }
        else{
            myPlayer.myAn++;
        }
    }

    private void mutate(){
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

                if (d < 0) {
                    d = 0;
                }
                dna.set(i, d);
            }
        }
    }

    private void calcAnimalSize(){
        animalSize =(int) (maxAnimalSize *((double) myAge / ageForGrow));

        maxHp = animalSize;
        damage=(int)(animalSize * predation);
        if(damage<=0 && canEatMeat()){
            damage=1;
        }
        eat= animalSize *2000;
        drink= animalSize *2000;
    }

    private void calcAnimalViewRadius(){
        maxRadiusView = (int) (((double) radiusView / maxAnimalSize) * animalSize);
    }

    private void animateFlyingAnimal(){
        imageSize = (int) (animalSize * flyCof);
        transparent = (int) (255 * (1.0 / flyCof));
        if(transparent >255){
            transparent =255;
        }
    }

    private void animateClimbingAnimal(){
        imageSize =(int)(animalSize *1.5);
    }

    private void animateSwimmingAnimal(){
        transparent =100;
    }

    private void setAnimalColor(){
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
                image.setColor(new Color((int)(predation *255), (((int)(55* poisonCof)+(int)((1- blueColorCof)*255))/255)*255, (int)((1- blueColorCof)*255), 255));
            }
        }
        else{
            image.setColor(new Color(153, 217, 234, 255));
        }

        createImage();
    }

    private void createImage(){
        image.fill();
        fon.setTransparency((int)(255*(maxMaskCof)));
        if(MyWorld.observedAnimal ==null && MyWorld.plMode ==2) {
            image.drawImage(fon, 0, 0);
        }
        image.setTransparency(transparent);
        image.scale(imageSize, imageSize);
    }

    int transparent;
    double blueColorCof;
    public void updateImage(){
        transparent = 255;
        animalSize = maxAnimalSize;
        if(!isGrowUp()){
            calcAnimalSize();

            calcAnimalViewRadius();
        }

        imageSize = animalSize;
        if(location == 0){
            animateFlyingAnimal();
        }
        else if(location==2){
            animateClimbingAnimal();
        }
        else if(location==3 || inHole){
            animateSwimmingAnimal();
        }  
        
        if(animalSize <=0){
            animalSize =1;
        }
        if(imageSize <=0){
            imageSize =1;
        }
        image = new GreenfootImage(animalSize, animalSize);
        poisonCof =(double)poison/ animalSize;
        if(poisonCof >1){
            poisonCof =1;
        }
        
        blueColorCof = poisonCof;
        if(poisonCof < predation){
            blueColorCof = predation;
        }

        setAnimalColor();
        setImage(image);
    }
    
    Water w;//touching water object
    int dist;//distance to water

    private int waterX = -1;//loc water x
    private int waterY = -1;//loc water y
    public void touchWater(){
        touchWater=false;
        if(!inHole){
            for(int i=0;i<getIntersectingObjects(Water.class).size();i++){
                w=getIntersectingObjects(Water.class).get(i);
                dist=(int)Math.sqrt(Math.pow(getX()-w.getX(),2)+Math.pow(getY()-w.getY(),2));
                if(dist<=(w.size/2)-(animalSize /2)){
                    waterX = w.getX();
                    waterY = w.getY();

                    touchWater=true;
                    break;
                }
            }
        }
    }
    
    boolean touchHole;
    Hole h;//touching hole
    
    boolean inHole;
    
    boolean isTouchingHole;
    
    public void touchHole(){
        touchHole=false;
        for(int i=0;i<getIntersectingObjects(Hole.class).size();i++){
            h=getIntersectingObjects(Hole.class).get(i);
            dist=(int)Math.sqrt(Math.pow(getX()-h.getX(),2)+Math.pow(getY()-h.getY(),2));
            if(dist<=(h.size/2)-(animalSize /2)){
                touchHole=true;
                break;
            }
        }
        
        if(touchHole && !isTouchingHole && h.loc==location){
            if(!inHole && h.size> maxAnimalSize && respiratorySystem<=0.5 && h.loc==3 || !inHole && h.size> maxAnimalSize && respiratorySystem>0.5 && h.loc==1){
                inHole=true;
            }
            else if(inHole){
                inHole=false;
            }
            isTouchingHole = true;
        }
        
        if(!touchHole){
            isTouchingHole = false;
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
                if(dist<=(hr.size/2)-(animalSize /2)){
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
        if(getObjectsInRange(maxRadiusView,Hole.class).size()==0 && digTimer ==0 && starve < 0.5 && thirst < 0.5 && location!=0 && location!=2 && digSpeed >=1){
            digTimer = (int) (Math.pow((maxAnimalSize + 2) * 2, 2) / (animalSize * animalSize * digSpeed));
        }
        if(timer==0 && digTimer >0){
            digTimer--;
            if(digTimer ==0){
                getWorld().addObject(new Hole(maxAnimalSize +2,location),getX(),getY());
            }
        }
    }
    
    public void act() 
    {
        if(!isStart){
            fon.drawImage(getWorld().getBackground(),-getX(),-getY());
            dx=getX();
            dy=getY();
            isStart=true;
        }
        if(!hibernation){
            think();
            move();
            touchWater();
            moveBack();
        }
        
        hibernation();
        if(digTimer >0){
            isStopMoveForward =true;
        }
        if(timer>=MyWorld.year){
            timer=0;
            myAge++;
            reproductiveTimer++;
        }
        updateImage();
        
        temp();
        
        if(!hibernation){
            if(respiratorySystem<=0.5 && location==1 && diveIn){
                dive();
            }
            diveIn = true;

            attack();
            if(isGrowUp() && starve < 0.2 && thirst < 0.2){
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

    public boolean isFullHp(){
        return hp < maxHp;
    }

    public void hibernation(){
        animalTemp = getAnimalTemp();
        if(Math.abs(animalTemp - needTemp) > 10){
            satiety = satiety - (int)(hibernationCof * animalSize * animalSize * animalSize);
            water = water - (int)(hibernationCof * animalSize * animalSize * animalSize);
            timer += hibernationCof;
            hibernation = true;
        }
        else{
            satiety = satiety - (animalSize * animalSize * animalSize);
            water = water - (animalSize * animalSize * animalSize);
            timer++;
            hibernation = false;
        }

        if(timer % 10 == 0 && starve < 0.3 && thirst < 0.3 && isFullHp() && Math.abs(animalTemp - needTemp) <= 10){
            hp++;
        }
    }
    
    public void temp(){
        animalTemp = getAnimalTemp();
        satiety = satiety -(int)(Math.abs(surroundTemp - needTemp)* heatCof * animalSize);
        water = water -(int)(Math.abs(surroundTemp - needTemp)* heatCof * animalSize);
    }
    
    int childX;
    int childY;
    
    int rotToMyChild;
    public void replicase(){
        if(speed == 0 && waterSpeed == 0 && flyingSpeed == 0){
            if(reproductiveTimer > period *2 && location != 0){
                for(int i = 0; i< fertility; i++){
                    int childFood = (int) (maxSatiety * liveBirth / (2 * fertility));
                    int childWater = (int)(maxWater * liveBirth  / (2 * fertility));
                    dna2.clear();
                    dna2.addAll(dna);
                    double randRot = Math.toRadians(Greenfoot.getRandomNumber(360));
                    int dist=Greenfoot.getRandomNumber(distToChild);

                    childX = getX() + (int) (Math.cos(randRot) * dist);
                    childY = getY() + (int) (Math.sin(randRot) * dist);
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
                        Animal child=new Animal(dna2, myPlayer, teamNum, inHole
                                , childFood, childWater);
                        getWorld().addObject(child, childX, childY);
                    }
                    else{
                        Egg child = new Egg(dna2, location, myPlayer, teamNum, inHole, moveCof);
                        getWorld().addObject(child, childX, childY);
                    }

                    satiety = satiety - childFood;
                    water = water - childWater;

                    reproductiveTimer = 0;
                }
            }
            else if(location == 0){
                stopFlying();
            }
        }
        else{
            if(location != 0 && reproductiveTimer > period && touchingAn !=null && touchingAn.teamNum==teamNum && !touchingAn.hibernation && touchingAn.reproductiveTimer > touchingAn.period && touchingAn.isGrowUp()){
                if(touchingAn.location==location) {
                    for (int i = 0; i < fertility; i++) {
                        animalPartner = touchingAn;
                        int childFood = (int) (maxSatiety * liveBirth / (2 * fertility));
                        int childWater = (int) (maxWater * liveBirth / (2 * fertility));
                        dna2.clear();
                        for (int i1 = 0; i1 < dna.size(); i1++) {
                            if (Greenfoot.getRandomNumber(2) == 1) {
                                dna2.add(dna.get(i1));
                            } else {
                                dna2.add(animalPartner.dna.get(i1));
                            }
                        }
                        if (liveBirth >= 0.5) {
                            Animal child = new Animal(dna2, myPlayer, teamNum, inHole
                                    , childFood, childWater);
                            getWorld().addObject(child, getX(), getY());
                        } else {
                            Egg child = new Egg(dna2, location, myPlayer, teamNum, inHole, moveCof);
                            getWorld().addObject(child, getX(), getY());
                        }
                        satiety = satiety - childFood;
                        water = water - childWater;

                        reproductiveTimer = 0;
                    }
                }
                else if(location == 3 && touchingAn.location == 1){
                    up();
                }
                else if(location == 1 && touchingAn.location == 3){
                    dive();
                }
            }
            else if(location != 0 && reproductiveTimer > period *2){
                for(int i = 0; i< fertility; i++){
                    int childFood = (int) (maxSatiety * liveBirth / (2 * fertility));
                    int childWater = (int)(maxWater * liveBirth  / (2 * fertility));
                    dna2.clear();
                    dna2.addAll(dna);
                    if(liveBirth >=0.5){
                        Animal child=new Animal(dna2, myPlayer, teamNum, inHole
                                , childFood, childWater);
                        getWorld().addObject(child, getX(), getY());
                    }
                    else{
                        Egg child=new Egg(dna2, location, myPlayer, teamNum,inHole, moveCof);
                        getWorld().addObject(child, getX(), getY());
                    }
                    satiety = satiety - childFood;
                    water = water - childWater;
                    reproductiveTimer =0;
                }
            }
            else if(location == 0){
                stopFlying();
            }
        }
    }
    
    DieAnimal food;
    
    Player pl1;
    double d1;
    
    Player pl;
    double distance;
    public void getPlInRange(){
        pl=null;
        if(MyWorld.plMode ==0){
            distance = maxRadiusView +1;
            for(int i = 0; i<getObjectsInRange(maxRadiusView,Player.class).size(); i++){
                pl1=getObjectsInRange(maxRadiusView,Player.class).get(i);
                d1=Math.sqrt(Math.pow(getX()-pl1.getX(),2)+Math.pow(getY()-pl1.getY(),2));
                if(pl1.teamNum!=teamNum && pl1.inHole==inHole && d1< distance){
                    distance =d1;
                    pl=pl1;
                }
                if(pl1.teamNum!=teamNum && pl1.inHole==inHole && intersects(pl1)){
                    pl=pl1;
                    break;
                }
            }
        }
    }

    public int getSurroundTemp() {
        surroundTemp = (int) (((double) getX() / getWorld().getWidth()) * MyWorld.temp);
        if(touchWater || location == 3){
            surroundTemp -= 10;
        }
        return surroundTemp;
    }

    double attraction;
    private int myPreferences() {
        attraction = 0.0;

        if(reproductiveTimer > period && isGrowUp() && starve < 0.2 && thirst < 0.2) {
            attraction = (double)(reproductiveTimer - period) / period;
        }

        if(thirst >= starve && thirst >= attraction){
            return 1;
        }
        else if(starve >= attraction){
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

        searchForAnimals();
        if(getObjectsInRange(maxRadiusView, Egg.class).size()>0){
            egg =getObjectsInRange(maxRadiusView, Egg.class).get(0);
            if(egg.inHole!=inHole){
                egg =null;
            }
        }
        
        getPlInRange();
        

        if(inHole && !touchHole && getObjectsInRange((maxAnimalSize +2)*2,Hole.class).size()>0){
            turnTowards(getObjectsInRange((maxAnimalSize +2)*2,Hole.class).get(0).getX(), getObjectsInRange((maxAnimalSize +2)*2,Hole.class).get(0).getY());
            rotationToTarget =getRotation();
            canTurn =true;
        }
        else if(MyWorld.plMode <2 && Math.sqrt(Math.pow(myPlayer.getX()-getX(),2)+Math.pow(myPlayer.getY()-getY(),2))> maxRadiusView){
            turnTowards(myPlayer.getX(), myPlayer.getY());
            rotationToTarget =getRotation();
            canTurn = true;
        }
        else if(isAtEdge()){
            turnTowards(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            rotationToTarget =getRotation();
            canTurn = true;
        }
        else if(movementAlongTheEdge != -1) {
            rotationToTarget = movementAlongTheEdge;
            canTurn = true;
            movementAlongTheEdge = -1;
        }
        else if (!touchWater && getObjectsInRange(maxRadiusView, Water.class).size() > 0 && (double) air / maxAir <= 0.7 && respiratorySystem <= 0.5) {
            turnTowards(getObjectsInRange(maxRadiusView, Water.class).get(0).getX(), getObjectsInRange(maxRadiusView, Water.class).get(0).getY());
            rotationToTarget = getRotation();
            canTurn = true;
        } else if (!touchWater && waterX != -1 && waterY != -1 && (double) air / maxAir <= 0.7 && respiratorySystem <= 0.5) {
            turnTowards(waterX, waterY);
            rotationToTarget = getRotation();
            canTurn = true;
        }
        else if(target == 1) {
            searchForWater();
        }
        else if(target == 2) {
            searchForFood();
        }
        else{
            searchForTeammate();
        }
    }

    public void searchForAnimals(){
        for(Animal animalInRange : getObjectsInRange(maxRadiusView, Animal.class)) {
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
                    if (Math.sqrt(Math.pow(getX() - animalInRange.getX(), 2) + Math.pow(getY() - animalInRange.getY(), 2)) < maxRadiusView - (int) (animalInRange.maskCof * maxRadiusView)) {
                        minDistToTeammate = distToObject;

                        teammateAnimal = animalInRange;
                    } else if (intersects(animalInRange)) {
                        minDistToTeammate = distToObject;

                        teammateAnimal = animalInRange;
                    }
                }
                else if(animalInRange.teamNum != teamNum && distToObject < minDistToEnemy && animalInRange.predation > predation ||
                        animalInRange.teamNum != teamNum && distToObject < minDistToEnemy && canEatMeat()) {
                    if (Math.pow(getX() - animalInRange.getX(), 2) + Math.pow(getY() - animalInRange.getY(), 2) < Math.pow(maxRadiusView - (int) (animalInRange.maskCof * maxRadiusView), 2)) {
                        minDistToEnemy = distToObject;

                        enemyAnimal = animalInRange;
                    } else if (intersects(animalInRange)) {
                        minDistToEnemy = distToObject;

                        enemyAnimal = animalInRange;
                    }
                }
            }
        }
    }

    public void searchForWater(){
        if (getObjectsInRange(maxRadiusView, Water.class).size() > 0 && thirst > 0.5
                || getObjectsInRange(maxRadiusView, Water.class).size() > 0 && (double) air / maxAir <= 0.6 && respiratorySystem <= 0.5) {
            turnTowards(getObjectsInRange(maxRadiusView, Water.class).get(0).getX(), getObjectsInRange(maxRadiusView, Water.class).get(0).getY());
            rotationToTarget = getRotation();
            canTurn = true;
        } else if (thirst > 0.5 && waterX != -1 && waterY != -1
                || waterX != -1 && waterY != -1 && (double) air / maxAir <= 0.6 && respiratorySystem <= 0.5) {
            turnTowards(waterX, waterY);
            rotationToTarget = getRotation();
            canTurn = true;
        }
        else if(enemyAnimal !=null && enemyAnimal.canEatMeat() && enemyAnimal.predation > predation){
            if(getObjectsInRange(maxRadiusView,Hole.class).size() > 0 && getObjectsInRange(maxRadiusView,Hole.class).get(0).size < enemyAnimal.animalSize){
                turnTowards(getObjectsInRange(maxRadiusView,Hole.class).get(0).getX(), getObjectsInRange(maxRadiusView,Hole.class).get(0).getY());
                rotationToTarget =getRotation();
            }
            else{
                turnTowards(enemyAnimal.getX(), enemyAnimal.getY());
                if(getRotation() >= 180){
                    rotationToTarget =getRotation() - 180;
                }
                else{
                    rotationToTarget =getRotation() + 180;
                }
            }

            canTurn = true;
        }
        else{
            randomMove();
        }
    }

    public void searchForFood(){
        if(canEatPlant()) {
            for (Plant plant : getObjectsInRange(maxRadiusView, Plant.class)) {
                if (plant.inHole == inHole) {
                    distToObject = Math.pow(plant.getX() - getX(), 2) + Math.pow(plant.getY() - getY(), 2);
                    if (location == plant.location && plant.damage + plant.poison <= (int) (protection * animalSize) && distToObject < minFoodDist && plant.satietyValueForBar > 3) {
                        this.plant = plant;

                        minFoodDist = distToObject;
                    }
                }
            }
        }
        if(canEatMeat() && !inHole){
            for (DieAnimal food : getObjectsInRange(maxRadiusView, DieAnimal.class)) {
                distToObject = Math.pow(food.getX() - getX(), 2) + Math.pow(food.getY() - getY(), 2);
                if (distToObject < minDistToExtraction) {
                    this.food = food;

                    minDistToExtraction = distToObject;
                }
            }
        }
        if(plant !=null && canEatPlant()){
            foodX= plant.getX();
            foodY= plant.getY();
        }
        else if(food != null && canEatMeat()){
            foodX = food.getX();
            foodY = food.getY();
        }

        if(enemyAnimal !=null && enemyAnimal.canEatMeat() && enemyAnimal.predation > predation && starve < 0.2 ||
                enemyAnimal !=null && !enemyAnimal.canEatPlant() && enemyAnimal.predation > predation && minFoodDist > minDistToEnemy / 2 ||
                enemyAnimal !=null && enemyAnimal.predation > predation && enemyAnimal.canEatMeat() && enemyAnimal.canEatPlant() && plant == null){
            if(getObjectsInRange(maxRadiusView,Hole.class).size() > 0 && getObjectsInRange(maxRadiusView,Hole.class).get(0).size < enemyAnimal.animalSize){
                turnTowards(getObjectsInRange(maxRadiusView,Hole.class).get(0).getX(), getObjectsInRange(maxRadiusView,Hole.class).get(0).getY());
                rotationToTarget =getRotation();
            }
            else{
                turnTowards(enemyAnimal.getX(), enemyAnimal.getY());
                if(getRotation() >= 180){
                    rotationToTarget =getRotation() - 180;
                }
                else{
                    rotationToTarget =getRotation() + 180;
                }
            }

            canTurn = true;
        }
        else if (canEatPlant() && plant != null) {
            turnTowards(plant.getX(), plant.getY());
            rotationToTarget = getRotation();
            canTurn = true;
        }
        // && minDistToExtraction > minDistToEnemy && isGrowUp() ||
        //                enemyAnimal != null && canEatMeat() && MyWorld.plMode == 2 && food == null
        else if (canEatMeat() && enemyAnimal != null && MyWorld.plMode < 2 || enemyAnimal != null && canEatMeat() && MyWorld.plMode == 2 && minDistToExtraction > minDistToEnemy && isGrowUp() ||
            enemyAnimal != null && canEatMeat() && MyWorld.plMode == 2 && food == null) {
            turnTowards(enemyAnimal.getX(), enemyAnimal.getY());
            rotationToTarget = getRotation();
            canTurn = true;
        } else if (canEatMeat() && food != null) {
            turnTowards(food.getX(), food.getY());
            rotationToTarget = getRotation();
            canTurn = true;
        } else if (canEatMeat() && egg != null && egg.teamNum != teamNum) {
            turnTowards(egg.getX(), egg.getY());
            rotationToTarget = getRotation();
            canTurn = true;
        } else if (foodX != -1 && foodY != -1) {
            turnTowards(foodX, foodY);
            rotationToTarget = getRotation();
            canTurn = true;
        } else if (pl != null && canEatMeat()) {
            turnTowards(pl.getX(), pl.getY());
            rotationToTarget = getRotation();
            canTurn = true;
        }
        else{
            randomMove();
        }
    }

    public void searchForTeammate(){
        if(enemyAnimal !=null && enemyAnimal.canEatMeat() && enemyAnimal.predation > predation){
            if(getObjectsInRange(maxRadiusView,Hole.class).size() > 0 && getObjectsInRange(maxRadiusView,Hole.class).get(0).size < enemyAnimal.animalSize){
                turnTowards(getObjectsInRange(maxRadiusView,Hole.class).get(0).getX(), getObjectsInRange(maxRadiusView,Hole.class).get(0).getY());
                rotationToTarget =getRotation();
            }
            else{
                turnTowards(enemyAnimal.getX(), enemyAnimal.getY());
                if(getRotation() >= 180){
                    rotationToTarget =getRotation() - 180;
                }
                else{
                    rotationToTarget =getRotation() + 180;
                }
            }

            canTurn = true;
        }
        else if (teammateAnimal != null && teammateAnimal.reproductiveTimer > teammateAnimal.period && reproductiveTimer > period && !teammateAnimal.hibernation &&
                teammateAnimal.isGrowUp()) {
            turnTowards(teammateAnimal.getX(), teammateAnimal.getY());
            rotationToTarget = getRotation();
            canTurn = true;
        }
        else{
            randomMove();
        }
    }

    public void randomMove(){
        if(Greenfoot.getRandomNumber(2)==1){
            rotationToTarget =Greenfoot.getRandomNumber(360);
        }
        canTurn = true;
    }
    
    public void breathe(){
        if(respiratorySystem <= 0.5) {
            if (location != 3 && air > 0) {
                air = air - (maxAnimalSize * maxAnimalSize * maxAnimalSize);
            } else if (air < maxAir && location == 3) {
                air += (maxAnimalSize * maxAnimalSize * maxAnimalSize);
                if (air > maxAir) {
                    air = maxAir;
                }
            }
        }
        else {
            if (location == 3 && air > 0) {
                air = air - (maxAnimalSize * maxAnimalSize * maxAnimalSize);
            } else if (location != 3 && air < maxAir) {
                air += (maxAnimalSize * maxAnimalSize * maxAnimalSize);
                if (air > maxAir) {
                    air = maxAir;
                }
            }
        }
    }
    
    public void drink(){
        if(water < maxWater){
            if(location == 3 || location == 1 && touchWater) {
                water = water + drink;
                if (thirst > 0.1) {
                    isStopMoveForward = true;
                }
            }
            else if (touchWater && location == 0 && thirst > starve){
                stopFlying();
            }
        }
        
        if(water > maxWater){
            water = maxWater;
        }
        if(water > 0){
            thirst = 1.0 - ((double)water / maxWater);
        }
    }
    
    public void eat(){
        if(Math.pow(getX() - foodX, 2) + Math.pow(getY() - foodY, 2) < Math.pow(maxRadiusView, 2)){
            if(getOneObjectAtOffset(foodX - getX(), foodY - getY(), Plant.class) == null && canEatPlant() ||
        getOneObjectAtOffset(foodX - getX(), foodY - getY(), DieAnimal.class) == null && canEatMeat()) {
                foodX = -1;
                foodY = -1;
            }
        }
        if(touchingPl !=null && canEatPlant() && satiety < maxSatiety && location==2 || touchingPl !=null && canEatPlant() && satiety < maxSatiety && touchingPl.satiety > touchingPl.maxSatiety * (1 - ((double) animalSize / touchingPl.size))){
            if(location == 2 || location == touchingPl.location) {
                touchingPl.satiety -= Math.min(eat, maxSatiety - satiety);
                satiety += Math.min((int) (eat * (1 - predation)), maxSatiety - satiety);
            }
            else if(location == 3 && touchingPl.location == 1){
                up();
            }
            else if(location == 1 && touchingPl.location == 3){
                dive();
            }
            else if(location == 0){
                stopFlying();
            }

            if (isHungry()) {
                isStopMoveForward = true;
            }
        }
        
        if(touchingFood !=null && canEatMeat() && satiety < maxSatiety){
            if(location == 1) {
                touchingFood.satiety -= Math.min(eat, maxSatiety - satiety);
                satiety += Math.min((eat * predation), maxSatiety - satiety);
            }
            else if(location == 3){
                up();
            }
            else if(location == 0){
                stopFlying();
            }

            if (isHungry()) {
                isStopMoveForward = true;
            }
        }

        if(satiety > 0){
            starve = 1.0 - ((double)satiety / maxSatiety);
        }
        
    }

    public boolean isGrowUp(){
        return myAge >= ageForGrow;
    }

    public boolean isHungry(){
        return starve > 0.1;
    }

    public boolean canEatPlant(){
        return predation < 0.7;
    } 
    
    public boolean canEatMeat(){
        return predation > 0.3;
    }

    public boolean canAttack(Animal an){
        return (an.damage + an.poison) - (int) (animalSize * protection) <= 0 &&(damage + poison) - (int) (an.animalSize * an.protection)>0||
                hp / ((an.damage + an.poison) - (int) (animalSize * protection)) > an.hp / ((damage + poison) - (int) (an.animalSize * an.protection)) ||
                hp % ((an.damage + an.poison) - (int) (animalSize * protection)) != 0;
    }

    public void attack(){
        extraction =null;
        if(canEatMeat() && pl!=null){
            if(pl.location==location){
                pl.xp-=(damage+poison);
                pl.hunter =this;
            }
        }
        else if(canEatMeat() && touchingAn !=null && predation > touchingAn.predation && touchingAn.teamNum!=teamNum){
            extraction = touchingAn;
            if(touchingAn.location==location){
                extraction.hp -= Math.max((damage + poison) - (int) (extraction.animalSize * extraction.protection), 0);

                extraction.hunterAnimal =this;
            }
            else if(location == 3 && extraction.location == 1){
                up();
            }
            else if(location == 1 && extraction.location == 3){
                dive();
            }
            else if(location == 0){
                stopFlying();
            }

            if (!canEatPlant()) {
                System.out.println(extraction.myAge + " " + movementSpeed + " " + extraction.movementSpeed);
            }
        }
        else if(canEatMeat() && touchingEgg !=null && touchingEgg.teamNum!=teamNum && satiety < maxSatiety && touchingEgg.size <= animalSize){
            if(location == touchingEgg.location){
                satiety += (touchingEgg.size * 10000);
                getWorld().removeObject(touchingEgg);
                touchingEgg =null;
            }
            else if(location == 3 && touchingEgg.location == 1){
                up();
            }
            else if(location == 1 && touchingEgg.location == 3){
                dive();
            }
            else if(location == 0){
                stopFlying();
            }
        }
        else{
            defense();
        }
    }
    
    public void defense(){
        if(isFullHp() && hunterAnimal !=null && location== hunterAnimal.location){
            hunterAnimal.hp -= Math.max((damage + poison) - (int) (hunterAnimal.animalSize * hunterAnimal.protection), 0);
        }
        if(isFullHp() && hunterPlant !=null && location == hunterPlant.location){
            hunterPlant.hp -= (damage + poison);
        }
        hunterAnimal =null;
        hunterPlant =null;
    }
    
    int rotationSpeed;
    boolean isTurn = false;
    boolean isMove = false;

    double movementSpeed;
    public void move(){
        startX = dx;
        startY = dy;

        if(!isStopMoveForward && !inHole || inHole && !isTurn && !isStopMoveForward){
            if(location!=3 && flyingSpeed >=speed && flyingSpeed >= waterSpeed && starve < 0.7 && thirst < 0.7 && !isStopFly && isGrowUp() && !inHole || location==0 && !isStopFly){
                movementSpeed = flyingSpeed * (1 - protection);

                doubleMove(movementSpeed * animalSize);
                satiety = satiety -(int) (Math.pow(movementSpeed, 2) * animalSize);
                water = water -(int) (Math.pow(movementSpeed, 2) * animalSize);
                fly=true;
            }
            else if(touchWater && !inHole && location == 1 || location==3){
                movementSpeed = waterSpeed * (1 - protection);

                doubleMove(movementSpeed * animalSize);
                satiety = satiety -(int) (Math.pow(movementSpeed, 2) * animalSize);
                water = water -(int) (Math.pow(movementSpeed, 2) * animalSize);
            }
            else{
                movementSpeed = speed * (1 - protection);

                doubleMove(movementSpeed * animalSize);
                satiety = satiety -(int) (Math.pow(movementSpeed, 2) * animalSize);
                water = water -(int) (Math.pow(movementSpeed, 2) * animalSize);
            }

            isMove = true;
        }

        rotationSpeed =(int)(movementSpeed * animalSize * 15);

        if(animalRotation != rotationToTarget && canTurn){
            if(Math.abs(rotationToTarget - animalRotation) < rotationSpeed){
                animalRotation = rotationToTarget;
                canTurn = false;
                isTurn = false;
            }
            else {
                if (rotationToTarget > animalRotation) {
                    if (rotationToTarget - animalRotation > 180) {
                        animalRotation -= rotationSpeed;
                    } else {
                        animalRotation += rotationSpeed;
                    }
                    satiety = satiety - rotationSpeed * animalSize;
                    isTurn = true;
                }
                if (rotationToTarget < animalRotation) {
                    if (animalRotation - rotationToTarget > 180) {
                        animalRotation += rotationSpeed;
                    } else {
                        animalRotation -= rotationSpeed;
                    }
                    satiety = satiety - rotationSpeed * animalSize;
                    isTurn = true;
                }
            }

            animalRotation %= 360;
            if(animalRotation <0){
                animalRotation += 360;
            }
        }

        setRotation(0);

        calcMask();

        isMove = false;
        isTurn = false;
        isStopMoveForward =false;

        inHole();
    }

    private void calcMask(){
        maskCof = maxMaskCof * (1.0 - (isTurn ? 0.1 : 0) - (isMove ? 0.2 : 0));
    }
    
    double dx;
    double dy;
    public void doubleMove(double v){
        dx += v*Math.cos(Math.toRadians(animalRotation));
        dy += v*Math.sin(Math.toRadians(animalRotation));
        
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

    public int getAnimalTemp(){
        return (int) (needTemp * heatCof + (getSurroundTemp() * (1 - heatCof)));
    }

    private int movementAlongTheEdge = -1;
    public void moveBack(){
        if(touchWater && !fly && waterSpeed ==0 && !inHole || inHole && !touchHR && location==1 ||
        !touchWater && !fly && !inHole && speed==0 || inHole && !touchHR && location==3 ||
        Math.abs(needTemp - getAnimalTemp()) > 10){
            dx = startX;
            dy = startY;

            if(needTemp - 10 > getAnimalTemp()){
                movementAlongTheEdge = 0;
            }
            else if(needTemp + 10 < getAnimalTemp()){
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
            diveIn = false;

            location=1;
        }
    }

    boolean diveIn = true;
    public void swim(){
        if((double)air / maxAir < 0.3 && touchWater && location == 0){
            stopFlying();
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
        if(canClimb && location==1 && !hibernation && !inHole){
            if(touchingPl !=null && touchingPl.location==1 && touchingPl.maxSize > maxAnimalSize){
                location=2;
            }
        }
        if(location==2 && touchingPl ==null){
            location=1;
        }
    }

    public void stopFlying(){
        isStopFly =true;
    }
    
    public void fly(){
        if(hibernation && location == 0){
            stopFlying();
        }
        if(isStopFly){
            fly = false;
        }

        if(flyCof >1 && !fly){
            flyCof -=0.05;
            if(flyCof < 1){
                flyCof = 1;
            }
        }
        if(fly){
            if(flyCof >=1 && flyCof <2){
                flyCof +=0.05;
            }
            else if(flyCof <1){
                flyCof =1;
            }
            else if(flyCof >2){
                flyCof =2;
            }
            location=0;
        }
        if(!fly && flyCof ==1 && location==0){
            location=1;
            isStopFly =false;
        }
    }
    
    public void die(){
        if(satiety <=0){
            hp--;
        }
        if(myAge > maxAge){
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

            if(MyWorld.plMode == 2) {
                if (canEatPlant() && canEatMeat()) {
                    myPlayer.omnivorous--;
                }
                else if(canEatPlant()){
                    myPlayer.myAn--;
                }
                else{
                    myPlayer.predators--;
                }
            }
            else{
                myPlayer.myAn--;
            }
            DieAnimal da=new DieAnimal(animalSize, satiety, maxSatiety);
            getWorld().addObject(da,getX(),getY());
            getWorld().removeObject(this);
        } 
    }
}

