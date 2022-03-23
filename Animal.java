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
    int maxAir1;
    
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
    int maxWater1;
    
    int period;
    int reproductiveTimer;
    int sex;

    double protection;
    //int maxArmor;
    //int armor;
    
    double waterSpeed;
    
    double poisonProtection;
    
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
    int maxSatiety1;
    
    double digSpeed;

    //double damageCof;
    
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

    private final int foodCof = 5500;//5500
    
    public Animal(ArrayList<Double> dna1, Player pl, int tn, boolean inHole, int food, int water, boolean mutate){
        this.inHole=inHole;
        if(this.inHole){
            isTouchingHole = true;
        }
        animalRotation =Greenfoot.getRandomNumber(360);
        rotationToTarget = animalRotation;
        teamNum=tn;
        myPlayer =pl;
        this.dna = new ArrayList<>(dna1);

        if(mutate)
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
            maxAnimalSize =2;
            animalSize =(int)(maxAnimalSize *0.1);
            if(animalSize <=0){
                animalSize =1;
            }
            dna.set(18, 2.8);//2.8
        }
        distToChild = 2 * maxAnimalSize;
        fon = new GreenfootImage(maxAnimalSize, maxAnimalSize);
        
        if(dna.get(0)>=0){
            maxAir1=dna.get(0).intValue();
            air=maxAir1;
        }
        else{
            maxAir1= 1000;
            air=maxAir1;
            dna.set(0, (double)maxAir1);
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
            liveBirth = 1;
            dna.set(4, liveBirth);
        }
        if(liveBirth <0){
            liveBirth =0;
        }
        
        if(dna.get(5)>=0){
            ageForGrow =dna.get(5).intValue();
        }
        else{
            ageForGrow = 6;//8
            dna.set(5, (double) ageForGrow);
        }

        ageForGrow = Math.max(ageForGrow, maxAnimalSize);
        
        if(dna.get(6)>=0){
            flyingSpeed =dna.get(6);
        }
        else{
            flyingSpeed =0.0;
            dna.set(6, flyingSpeed);
        }
        
        if(dna.get(7)>=0){
            poison = (int)(dna.get(7) * 100);
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
            maxWater1 =dna.get(9).intValue();
            this.water =water;
        }
        else{
            maxWater1 = foodCof;
            this.water = maxWater1;
            dna.set(9, (double) maxWater1);
        }
        
        if(dna.get(10)>=0){
            period =dna.get(10).intValue();
        }
        else{
            period = 5;//2
            dna.set(10, (double)period);
        }
        period = Math.max(period, 1);
        
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
            waterSpeed = 0.3;//0.3
            dna.set(12, waterSpeed);
        }
        
        if(dna.get(13)>=0){
            poisonProtection =dna.get(13);
        }
        else{
            poisonProtection =0.0;
            dna.set(13, poisonProtection);
        }
        if(poisonProtection < 0){
            poisonProtection = 0;
        }
        if(poisonProtection > 1.0){
            poisonProtection = 1;
        }
        
        if(dna.get(14)>=0){
            maxAge =dna.get(14).intValue();
        }
        else{
            maxAge =50;
            dna.set(14, (double) maxAge);
        }
        
        if(dna.get(15)>0){
            fertility = (int) (1 / dna.get(15));
        }
        else{
            fertility = 1;
            dna.set(15, (double)fertility);
        }

        fertility = Math.max(fertility, 1);
        
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
            radiusView = 50;//45
            dna.set(17, (double) radiusView);
        }
        
        if(dna.get(19)>=0){
            heatCof =dna.get(19);
        }
        else{
            heatCof =0.7;
            dna.set(19, heatCof);
        }
        if(heatCof >0.9){
            heatCof =0.9;
        }
        else if(heatCof <0){
            heatCof =0;
        }

        if(dna.get(21)>0){
            maxSatiety1 =dna.get(21).intValue();
            satiety =food;
        }
        else{
            maxSatiety1 = foodCof;
            satiety = maxSatiety1;
            dna.set(21, (double) maxSatiety1);
        }

        if(dna.get(20)>=0){
            predation =dna.get(20);

            /*if(myPlayer.predators + myPlayer.omnivorous < 2 && myPlayer.myAn > 20 && canEatPlant()){//22
                predation =  1.0;
                dna.set(20, predation);
                //damageCof = 1.0;
                //dna.set(23, damageCof);
            }*/
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
        
        if(dna.get(22)>0){
            digSpeed =dna.get(22);
        }
        else{
            digSpeed =0.0;
            dna.set(22, digSpeed);
        }

        /*if(dna.get(23)>0){
            damageCof =dna.get(23);
        }
        else{
            damageCof = 0.0;
            dna.set(23, damageCof);
        }*/

        //damageCof = Math.min(damageCof, 1.0);
        //maxMaskCof = Math.min(maxMaskCof, 0.6);
        //speed = Math.min(1, speed);
        //waterSpeed = Math.min(1, waterSpeed);
        //flyingSpeed = Math.min(1, flyingSpeed);
        
        location = 1;
        maxHp = getFullSize() * 10;
        hp = maxHp;
        reproductiveTimer = 0;
        damage = (int)(getFullSize() * predation * 8) + (getFullSize() * 2);
        maxRadiusView = radiusView / maxAnimalSize;
        updateImage();
        if(MyWorld.plMode == 2) {
            if (predation < 0.7 && predation > 0.3) {
                myPlayer.omnivorous++;
            }
            else if(predation < 0.3){
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

    public int getFullSize(){
        return (int)Math.pow(animalSize, 3);
    }

    private void calcAnimalSize(){
        animalSize =(int) (maxAnimalSize *((double) myAge / ageForGrow));

        if(animalSize <= 0){
            animalSize = 1;
        }
        maxHp = getFullSize() * 10;
        //(int)(2 * animalSize * predation * 100);
        //(int)(2 * animalSize * predation * 100 / 3) + (int)((double)animalSize * 100 / 3);
        damage = (int)(getFullSize() * predation * 8) + (getFullSize() * 2);
        /*if(damage <= 0){
            damage = 1;
        }*/
        eat= animalSize * 2000;
        drink= animalSize * 2000;

        //maxArmor = (int) (animalSize * protection);
    }

    private void calcAnimalViewRadius(){
        maxRadiusView = (int) (((double) radiusView / maxAnimalSize) * animalSize);
    }

    private void calcAnimalParameters(){
        maxSatiety = maxSatiety1 * animalSize;
        maxWater = maxWater1 * animalSize;
        maxAir = maxAir1 * animalSize;
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
                    boolean mySpecies = true;
                    boolean myTeam = true;
                    for(int i = 0; i < dna.size(); i++){
                        if(Math.min(dna.get(i), MyWorld.observedAnimal.dna.get(i)) <= 1 && Math.abs(dna.get(i) - MyWorld.observedAnimal.dna.get(i)) > MyWorld.classificationOfSpecies || Math.min(dna.get(i), MyWorld.observedAnimal.dna.get(i)) > 1 && Math.abs(dna.get(i) - MyWorld.observedAnimal.dna.get(i)) / Math.max(dna.get(i), MyWorld.observedAnimal.dna.get(i)) > MyWorld.classificationOfSpecies){
                            mySpecies = false;
                            if(Math.min(dna.get(i), MyWorld.observedAnimal.dna.get(i)) <= 1 && Math.abs(dna.get(i) - MyWorld.observedAnimal.dna.get(i)) > MyWorld.classificationOfEnemies || Math.min(dna.get(i), MyWorld.observedAnimal.dna.get(i)) > 1 && Math.abs(dna.get(i) - MyWorld.observedAnimal.dna.get(i)) / Math.max(dna.get(i), MyWorld.observedAnimal.dna.get(i)) > MyWorld.classificationOfEnemies){
                                myTeam = false;
                                break;
                            }
                        }
                    }
                    if (mySpecies) {
                        teamNum = 0;
                    } else if(myTeam){
                        teamNum = 2;
                    } else{
                        teamNum = 1;
                    }
                }
                /*if(MyWorld.observedAnimal != null && MyWorld.observedAnimal.canWin(this)){
                    teamNum = 0;
                }
                else{
                    teamNum = 1;
                }*/
                if(teamNum==0){
                    image.setColor(new Color(0, 255, 255, 255));
                }
                else if(teamNum==1){
                    image.setColor(new Color(255, 0, 0, 255));
                }
                else if(teamNum==2){
                    image.setColor(new Color(255, 0, 255, 255));
                }
                else if(teamNum==3){
                    image.setColor(new Color(255, 255, 0, 255));
                }
                else if(teamNum==4){
                    image.setColor(new Color(0, 0, 255, 255));
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

            calcAnimalParameters();
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
        
        image = new GreenfootImage(animalSize, animalSize);
        poisonCof = Math.min((double)poison / (animalSize * 100), 1);
        
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
                if(dist<=(w.size/2)-(animalSize /2) || w.ocean){
                    if(!w.ocean) {
                        waterX = w.getX();
                        waterY = w.getY();
                    }
                    else{
                        waterX = getX();
                        waterY = getWorld().getHeight() / 4;
                    }

                    touchWater=true;
                    break;
                }
                else if(dist<=(w.size/2)-radiusView && Math.pow(dist, 2) < Math.pow(getX()-waterX,2)+Math.pow(getY()-waterY,2)){
                    waterX = w.getX();
                    waterY = w.getY();
                }
                else if(getY() < (getWorld().getHeight() / 2) + radiusView){
                    waterX = getX();
                    waterY = getWorld().getHeight() / 4;
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
            digTimer = (int) (Math.pow(maxAnimalSize * 2, 2) / (animalSize * animalSize * digSpeed));
        }
        if(timer==0 && digTimer >0){
            digTimer--;
            if(digTimer ==0){
                getWorld().addObject(new Hole(maxAnimalSize,location),getX(),getY());
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
        
        temp();

        fly();
        
        if(!hibernation){
            if(respiratorySystem <= 0.5 && location==1 && diveIn){
                dive();
            }
           /*else if(respiratorySystem>0.5 && location==3) {
                up();
            }*/

            diveIn = true;

            attack();
            if(isGrowUp() && starve < (1 - 0.3 - (liveBirth * fertility / maxAnimalSize)) && thirst < (1 - 0.3 - (liveBirth * fertility / maxAnimalSize))){
                replicase();
            }
            drink();
            eat();
            dig();
        }
        
        breathe();
        climb();
        swim();
        updateImage();
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

        /*if(timer % 5 == 0 && starve < 0.7 && thirst < 0.7 && armor < maxArmor && Math.abs(animalTemp - needTemp) <= 10){
            armor++;
        }*/

        if(isFullHp()){
            hp += 1;
            //= Math.max(1, animalSize * 2)
        }
    }
    
    public void temp(){
        animalTemp = getAnimalTemp();
        satiety = satiety - (int)(Math.abs(surroundTemp - needTemp) * heatCof * 0.1 * getFullSize());
        water = water - (int)(Math.abs(surroundTemp - needTemp) * heatCof * 0.1 * getFullSize());
    }
    
    int childX;
    int childY;

    int firstTeamNum;

    int rotToMyChild;
    public void replicase(){
        if(touchingAn != null) {
            firstTeamNum = touchingAn.teamNum;
            setTeam(touchingAn, MyWorld.classificationOfSpecies);
        }

        if(speed == 0 && waterSpeed == 0 && flyingSpeed == 0){
            if(reproductiveTimer > period *2 && location != 0){
                for(int i = 0; i< fertility; i++){
                    int childFood = (int)(maxSatiety1 * liveBirth);
                    int childWater = (int)(maxWater1 * liveBirth);
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
                                , childFood, childWater, true);
                        getWorld().addObject(child, childX, childY);
                    }
                    else{
                        Egg child = new Egg(dna2, location, myPlayer, teamNum, inHole, childFood, childWater, true);
                        getWorld().addObject(child, childX, childY);
                    }

                    satiety = satiety - childFood;
                    water = water - childWater;

                    reproductiveTimer = 0;
                }
            }
        }
        else{
            if(location != 0 && reproductiveTimer > period && touchingAn !=null && touchingAn.teamNum==teamNum && !touchingAn.hibernation && touchingAn.reproductiveTimer > touchingAn.period && touchingAn.isGrowUp()){
                if(touchingAn.location==location) {
                    for (int i = 0; i < fertility; i++) {
                        animalPartner = touchingAn;
                        int childFood = (int)(maxSatiety1 * liveBirth);
                        int childWater = (int)(maxWater1 * liveBirth);
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
                                    , childFood, childWater, true);
                            getWorld().addObject(child, getX(), getY());
                        } else {
                            Egg child = new Egg(dna2, location, myPlayer, teamNum, inHole, childFood, childWater, true);
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
            else if(location != 0 && reproductiveTimer > period * 2 && animalSize == 1){
                for(int i = 0; i< fertility; i++){
                    int childFood = (int)(maxSatiety1 * liveBirth);
                    int childWater = (int)(maxWater1 * liveBirth);
                    dna2.clear();
                    dna2.addAll(dna);
                    if(liveBirth >=0.5){
                        Animal child=new Animal(dna2, myPlayer, teamNum, inHole
                                , childFood, childWater, false);
                        getWorld().addObject(child, getX(), getY());
                    }
                    else{
                        Egg child=new Egg(dna2, location, myPlayer, teamNum,inHole, childFood, childWater, false);
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

        if(touchingAn != null)
        touchingAn.teamNum = firstTeamNum;
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
        if(touchWater && location == 1 || location == 3){
            surroundTemp -= 10;
        }
        return surroundTemp;
    }

    double attraction;
    private int myPreferences() {
        attraction = 0.0;

        if(reproductiveTimer > period && isGrowUp() && starve < (1 - 0.3 - (liveBirth * fertility / maxAnimalSize)) && thirst < (1 - 0.3 - (liveBirth * fertility / maxAnimalSize))) {
            attraction = 1.0;
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

    double minEggDist;
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
            /*egg =getObjectsInRange(maxRadiusView, Egg.class).get(0);
            if(egg.inHole!=inHole){
                egg =null;
            }*/

            for (Egg egg : getObjectsInRange(maxRadiusView, Egg.class)) {
                distToObject = Math.pow(egg.getX() - getX(), 2) + Math.pow(egg.getY() - getY(), 2);
                if (Math.pow(getX() - egg.getX(), 2) + Math.pow(getY() - egg.getY(), 2) < Math.pow(maxRadiusView - (int) (egg.maskCof * maxRadiusView), 2)) {
                    this.egg = egg;

                    minEggDist = distToObject;

                } else if (intersects(egg)) {
                    this.egg = egg;

                    minEggDist = distToObject;
                }
            }
        }
        
        getPlInRange();
        

        if(inHole && !touchHole && getObjectsInRange((maxAnimalSize +2)*2,Hole.class).size()>0){
            turnTowards(getObjectsInRange((maxAnimalSize +2)*2,Hole.class).get(0).getX(), getObjectsInRange((maxAnimalSize +2)*2,Hole.class).get(0).getY());
            rotationToTarget =getRotation();
            canTurn =true;
        }
        else if(MyWorld.plMode <2 && Math.sqrt(Math.pow(myPlayer.getX()-getX(),2)+Math.pow(myPlayer.getY()-getY(),2))> 100){
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

    //Animal lastEnemy;
    public void searchForAnimals(){
        //lastEnemy = enemyAnimal;
        for(Animal animalInRange : getObjectsInRange(maxRadiusView, Animal.class)) {
            if (animalInRange.inHole == inHole && animalInRange.location == location) {
                if (MyWorld.plMode == 2) {
                    setTeam(animalInRange, MyWorld.classificationOfSpecies);
                }

                distToObject = Math.pow(animalInRange.getX() - getX(), 2) + Math.pow(animalInRange.getY() - getY(), 2);

                if (animalInRange.teamNum == teamNum && distToObject < minDistToTeammate) {
                    if (Math.sqrt(Math.pow(getX() - animalInRange.getX(), 2) + Math.pow(getY() - animalInRange.getY(), 2)) < maxRadiusView - (int) (animalInRange.maskCof * maxRadiusView)) {
                        minDistToTeammate = distToObject;

                        teammateAnimal = animalInRange;
                    } else if (intersects(animalInRange)) {
                        minDistToTeammate = distToObject;

                        teammateAnimal = animalInRange;
                    }
                } else {
                    if (MyWorld.plMode == 2) {
                        setTeam(animalInRange, MyWorld.classificationOfEnemies);
                    }

                    if (animalInRange.canEatMeat() && animalInRange.teamNum != teamNum && distToObject < minDistToEnemy && animalInRange.canWin(this)) {
                        if (Math.pow(getX() - animalInRange.getX(), 2) + Math.pow(getY() - animalInRange.getY(), 2) < Math.pow(maxRadiusView - (int) (animalInRange.maskCof * maxRadiusView), 2)) {
                            minDistToEnemy = distToObject;

                            enemyAnimal = animalInRange;
                        } else if (intersects(animalInRange)) {
                            minDistToEnemy = distToObject;

                            enemyAnimal = animalInRange;
                        }
                    }
                    //  && distToObject * (movementSpeed - enemyAnimal.movementSpeed) *  enemyAnimal.satiety < minDistToEnemy * (movementSpeed - animalInRange.movementSpeed) * animalInRange.satiety
                    //distToObject * animalInRange.movementSpeed * animalInRange.protection < minDistToExtraction * enemyAnimal.movementSpeed * enemyAnimal.protection  distToObject * (movementSpeed - enemyAnimal.movementSpeed) * enemyAnimal.satiety < minDistToEnemy * (movementSpeed - animalInRange.movementSpeed) * animalInRange.satiety
                    else if (enemyAnimal == null && canEatMeat() && animalInRange.teamNum != teamNum && canWin(animalInRange) ||
                            enemyAnimal != null && canEatMeat() && animalInRange.teamNum != teamNum && canWin(animalInRange) && distToObject * (movementSpeed - enemyAnimal.movementSpeed) * enemyAnimal.satiety < minDistToEnemy * (movementSpeed - animalInRange.movementSpeed) * animalInRange.satiety) {
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
        // || enemyAnimal !=null && enemyAnimal.canWin(this) && enemyAnimal.canEatMeat() && enemyAnimal.canEatPlant() && plant == null
        //enemyAnimal !=null && enemyAnimal.canEatMeat() && enemyAnimal.canWin(this) && starve < 0.5 ||
        //                enemyAnimal !=null && enemyAnimal.canEatMeat() && enemyAnimal.canWin(this) && minFoodDist > minDistToEnemy / 2
        else if(enemyAnimal !=null && enemyAnimal.canEatMeat() && enemyAnimal.canWin(this)){
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
    double targetX;
    double targetY;
    public void searchForFood(){
        if(canEatPlant()) {
            for (Plant plant : getObjectsInRange(maxRadiusView, Plant.class)) {
                if (plant.inHole == inHole) {
                    distToObject = Math.pow(plant.getX() - getX(), 2) + Math.pow(plant.getY() - getY(), 2);
                    // && (int) (plant.poison * (1 - poisonProtection)) <= 0 && plant.damage - (int) (protection * animalSize) <=0
                    //distToObject / plant.satietyValueForBar < minFoodDist / this.plant.satietyValueForBar && plant.satietyValueForBar > 3
                    if (this.plant == null ||
                            distToObject / Math.max(1 ,getValueOfSatietyICanEat(plant)) < minFoodDist / Math.max(1, getValueOfSatietyICanEat(this.plant))) {
                        if (Math.pow(getX() - plant.getX(), 2) + Math.pow(getY() - plant.getY(), 2) < Math.pow(maxRadiusView - (int) (plant.maskCof * maxRadiusView), 2)) {
                            this.plant = plant;

                            minFoodDist = distToObject;

                        } else if (intersects(plant)) {
                            this.plant = plant;

                            minFoodDist = distToObject;
                        }
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

        targetX = -1;
        targetY = -1;

        if(plant !=null && canEatPlant()){
            foodX = plant.getX();
            foodY = plant.getY();
        }
        else if(food != null && canEatMeat()){
            foodX = food.getX();
            foodY = food.getY();
        }
        //enemyAnimal !=null && enemyAnimal.predation > predation && enemyAnimal.canEatMeat() && enemyAnimal.canEatPlant() && plant == null
        // && starve < 0.5 ||
        //                enemyAnimal !=null && enemyAnimal.canEatMeat() && enemyAnimal.canWin(this) && minFoodDist > minDistToEnemy / 2
        if(enemyAnimal !=null && enemyAnimal.canEatMeat() && enemyAnimal.canWin(this)){
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
        //canEatPlant() && plant != null && minFoodDist < minDistToExtraction
        //canEatPlant() && !canEatMeat() && plant != null ||
        //               canEatPlant() && plant != null && enemyAnimal == null && minFoodDist < minDistToExtraction ||
        //               canEatPlant() && plant != null && minFoodDist < minDistToExtraction && minFoodDist * (movementSpeed - enemyAnimal.movementSpeed) * predation < minDistToEnemy * movementSpeed * (1 - predation)
        // * (movementSpeed - enemyAnimal.movementSpeed) * enemyAnimal.satiety * predation   * movementSpeed * (plant.satiety - (plant.maxSatiety * ((double)Math.max(0, plant.size - animalSize) / plant.size))) * (1 - predation)
        //canEatPlant() && !canEatMeat() && plant != null ||
        //                canEatPlant() && canEatMeat() && plant != null && minFoodDist * (food != null ? predation * food.satiety : 0) < minDistToExtraction * (1 - predation) * (plant.satiety - (plant.maxSatiety * ((double)Math.max(0, plant.size - animalSize) / plant.size)))
        //                        && minFoodDist * (enemyAnimal != null ? (movementSpeed - enemyAnimal.movementSpeed) * enemyAnimal.satiety * predation : 0) < minDistToEnemy * movementSpeed * (plant.satiety - (plant.maxSatiety * ((double)Math.max(0, plant.size - animalSize) / plant.size))) * (1 - predation)
        else if (canEatPlant() && !canEatMeat() && plant != null ||
                 canEatPlant() && canEatMeat() && plant != null && minFoodDist * (food != null ? predation * food.satiety : 0) < minDistToExtraction * (1 - predation) * getValueOfSatietyICanEat(plant)
                                && minFoodDist * (enemyAnimal != null ? (movementSpeed - enemyAnimal.movementSpeed) * enemyAnimal.satiety * predation : 0) < minDistToEnemy * movementSpeed * getValueOfSatietyICanEat(plant) * (1 - predation)) {
            turnTowards(plant.getX(), plant.getY());
            rotationToTarget = getRotation();
            canTurn = true;
        }
        //canEatMeat() && enemyAnimal != null && MyWorld.plMode < 2 || enemyAnimal != null && canEatMeat() && MyWorld.plMode == 2 && minDistToExtraction * (movementSpeed - enemyAnimal.movementSpeed) > minDistToEnemy * movementSpeed/* && isGrowUp() ||
        //            enemyAnimal != null && canEatMeat() && MyWorld.plMode == 2 && food == null*/
        // && minDistToExtraction > minDistToEnemy && isGrowUp() ||
        //                enemyAnimal != null && canEatMeat() && MyWorld.plMode == 2 && food == null
        else if (canEatMeat() && enemyAnimal != null && MyWorld.plMode < 2 || enemyAnimal != null && canEatMeat() && MyWorld.plMode == 2 && food == null || enemyAnimal != null && canEatMeat() && MyWorld.plMode == 2 && minDistToExtraction * (movementSpeed - enemyAnimal.movementSpeed) * enemyAnimal.satiety > minDistToEnemy * movementSpeed * food.satiety) {
            turnTowards(enemyAnimal.getX(), enemyAnimal.getY());
            rotationToTarget = getRotation();
            canTurn = true;
        } else if (canEatMeat() && food != null) {
            turnTowards(food.getX(), food.getY());
            rotationToTarget = getRotation();
            canTurn = true;
            targetX = food.getX();
            targetY = food.getY();
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

    public double getValueOfSatietyICanEat(Plant p){
        return canClimb ? p.satiety : (p.satiety - (p.maxSatiety * ((double)Math.max(0, p.size - animalSize) / p.size)));
    }

    public void searchForTeammate(){
        if(enemyAnimal !=null && enemyAnimal.canEatMeat() && enemyAnimal.canWin(this)){
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
        else if (teammateAnimal != null && teammateAnimal.reproductiveTimer > teammateAnimal.period && !teammateAnimal.hibernation &&
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
                air += (maxAnimalSize * maxAnimalSize * maxAnimalSize * 20);
                if (air > maxAir) {
                    air = maxAir;
                }
            }
        }
        else {
            if (location == 3 && air > 0) {
                air = air - (maxAnimalSize * maxAnimalSize * maxAnimalSize * 20);
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
                water += Math.min(drink, maxWater - water);
                if (thirst > 0.1) {
                    isStopMoveForward = true;
                }
            }
            else if (touchWater && location == 0 && thirst >= starve){
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

    double degree = 1;
    public void eat(){
        /*if(predation > 0.3){
            MyWorld.observedAnimal = this;
        }*/
        if(Math.pow(getX() - foodX, 2) + Math.pow(getY() - foodY, 2) < Math.pow(maxRadiusView, 2)){
            if(getOneObjectAtOffset(foodX - getX(), foodY - getY(), Plant.class) == null && canEatPlant() ||
            getOneObjectAtOffset(foodX - getX(), foodY - getY(), DieAnimal.class) == null && canEatMeat()) {
                foodX = -1;
                foodY = -1;
            }
        }
        if(touchingPl !=null && canEatPlant() && satiety < maxSatiety && location==3 && touchingPl.location == 3 || touchingPl !=null && canEatPlant() && satiety < maxSatiety && location==2 || touchingPl !=null && canEatPlant() && satiety < maxSatiety && touchingPl.satiety > touchingPl.maxSatiety * (1 - Math.min(1, (double) animalSize / touchingPl.size))){
            if(touchingPl.satiety > 1000) {
                if (location == 2 || location == touchingPl.location) {
                    touchingPl.satiety -= Math.min(Math.min(eat, maxSatiety - satiety), (touchingPl.satiety - 1) * Math.pow(1 - predation, degree));//Math.min(eat, maxSatiety - satiety)
                    satiety += Math.min(Math.min((int) (eat * Math.pow(1 - predation, degree)), maxSatiety - satiety), (touchingPl.satiety - 1) * Math.pow(1 - predation, degree));
                    //touchingPl.satiety -= Math.min(eat, maxSatiety - satiety);
                    //satiety += Math.min((int) (eat * (1 - predation)), maxSatiety - satiety);
                } else if (location == 3 && touchingPl.location == 1) {
                    up();
                } else if (location == 1 && touchingPl.location == 3) {
                    dive();
                } else if (location == 0 && starve >= thirst) {
                    stopFlying();
                }

                if (isHungry() && starve >= thirst) {
                    isStopMoveForward = true;
                }
            }
        }
        
        if(touchingFood !=null && canEatMeat() && satiety < maxSatiety){
            if(location == 1) {
                touchingFood.satiety -= Math.min(eat, maxSatiety - satiety);//Math.min(eat, maxSatiety - satiety)
                satiety += Math.min(eat * predation, maxSatiety - satiety);
            }
            else if(location == 3){
                up();
            }
            else if(location == 0 && starve >= thirst){
                stopFlying();
            }

            if (isHungry() && starve >= thirst) {
                isStopMoveForward = true;
            }
        }

        if(satiety > 0){
            starve = 1.0 - ((double)satiety / maxSatiety);
        }
    }

    public boolean isGrowUp(){
        return myAge > ageForGrow;
    }

    public boolean isHungry(){
        return starve > 0.1;
    }

    public boolean canEatPlant(){
        return predation < 1.0;
    } 
    
    public boolean canEatMeat(){
        return predation > 0.0;
    }

    public boolean canWin(Animal an){
        int enemyDamage = Math.max(0, an.damage - (int) (animalSize * protection * 100)) + (int) (an.poison * (1 - poisonProtection));
        int myDamage = Math.max(0, damage - (int) (an.animalSize * an.protection * 100)) + (int) (poison * (1 - an.poisonProtection));

        return enemyDamage == 0 && myDamage > 1 || myDamage > 1 && hp / enemyDamage > an.hp / myDamage ||
                myDamage > 1 && hp / enemyDamage == an.hp / myDamage && hp % enemyDamage > 0;
    }

    /*public void attackAnimal(int damage){
        if(armor <= 0){
            hp -= damage;
        }
        else{
            armor -= damage;

            if(armor < 0){
                hp += armor;
            }
        }
    }*/

    public void attack(){
        /*if(touchingAn != null && canEatMeat()) {
            System.out.println(pl + " " + touchingAn + " " + canWin(touchingAn) + " " + (touchingAn.teamNum != teamNum) + " " + touchingAn.location + " " + location);
        }*/
        extraction =null;
        if(canEatMeat() && pl!=null){
            if(pl.location==location){
                pl.xp-=(damage+poison);

                satiety -= poison * 100;

                pl.hunter =this;
            }
        }
        //|| canEatMeat() && canEatPlant() && touchingAn !=null && canWin(touchingAn) && touchingAn.teamNum!=teamNum && plant == null
        else if(canEatMeat() && touchingAn !=null && canWin(touchingAn) && touchingAn.teamNum!=teamNum){
            extraction = touchingAn;
            if(extraction.location==location || location == 0 && extraction.location != 3){
                extraction.hp -= (int) (poison * (1 - extraction.poisonProtection)) + Math.max(0, damage - (int) (extraction.animalSize * extraction.protection * 100));

                extraction.hunterAnimal = this;

                satiety -= poison * 100;

                extraction.die();
            }
            else if(location == 3 && extraction.location == 1){
                up();
            }
            else if(location == 1 && extraction.location == 3){
                dive();
            }
            else if(location == 0 && starve >= thirst){
                stopFlying();
            }

            targetX = extraction.dx;
            targetY = extraction.dy;
        }
        else if(canEatMeat() && touchingEgg !=null && touchingEgg.teamNum!=teamNum && satiety < maxSatiety && touchingEgg.size <= animalSize){
            if(location == touchingEgg.location){
                satiety += touchingEgg.food * predation;
                getWorld().removeObject(touchingEgg);
                touchingEgg =null;
            }
            else if(location == 3 && touchingEgg.location == 1){
                up();
            }
            else if(location == 1 && touchingEgg.location == 3){
                dive();
            }
            else if(location == 0 && starve >= thirst){
                stopFlying();
            }
        }
        else{
            defense();
        }
        hunterAnimal =null;
        hunterPlant =null;
    }

    public int getProtection(){
        return (int) (getFullSize() * protection * 10);
    }
    
    public void defense(){
        if(isFullHp() && hunterAnimal !=null && location== hunterAnimal.location){
            hunterAnimal.hp -= (int) (poison * (1 - hunterAnimal.poisonProtection)) + Math.max(damage - hunterAnimal.getProtection(), 0);

            satiety -= poison * 200;

            hunterAnimal.die();
        }
        if(isFullHp() && hunterPlant !=null && location == hunterPlant.location){
            hunterPlant.hp -= (damage + poison);

            satiety -= poison * 200;

            hunterPlant.die();
        }
    }
    
    int rotationSpeed;
    boolean isTurn = false;
    boolean isMove = false;

    double movementSpeed;
    public void move(){
        startX = dx;
        startY = dy;

        if(!isStopMoveForward && !inHole || inHole && !isTurn && !isStopMoveForward){
            if(location!=3 && flyingSpeed >= speed && flyingSpeed >= waterSpeed && !inHole && stayingOnTheGround == 0 || location==0){
                movementSpeed = flyingSpeed * animalSize;

                satiety = satiety -(int) (Math.pow(movementSpeed, 2) * animalSize);
                water = water -(int) (Math.pow(movementSpeed, 2) * animalSize);
                fly = true;
            }
            else if(touchWater && !inHole && location == 1 || location==3){
                movementSpeed = waterSpeed * animalSize;

                satiety = satiety -(int) (Math.pow(movementSpeed, 2) * animalSize);
                water = water -(int) (Math.pow(movementSpeed, 2) * animalSize);
            }
            else{
                movementSpeed = speed * animalSize;

                satiety = satiety -(int) (Math.pow(movementSpeed, 2) * animalSize);
                water = water -(int) (Math.pow(movementSpeed, 2) * animalSize);
            }

            isMove = true;
        }

        int rotationSpeed1 = (int)(movementSpeed * 15);

        rotationSpeed = (int)(rotationSpeed1 * (1 - protection));

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
                }
                else{
                    if (animalRotation - rotationToTarget > 180) {
                        animalRotation += rotationSpeed;
                    } else {
                        animalRotation -= rotationSpeed;
                    }
                }
                satiety = satiety - rotationSpeed1 * animalSize;
                isTurn = true;
            }

            animalRotation %= 360;
            if(animalRotation <0){
                animalRotation += 360;
            }
        }

        setRotation(0);

        doubleMove(movementSpeed * (1 - protection));

        isTurn = false;
        isStopMoveForward = false;

        inHole();
    }
    // - (isTurn ? 0.25 : 0)
    private void calcMask(){
        maskCof = Math.min(1.0, ((maxMaskCof + (0.9 / Math.pow(animalSize, 2))) * (1.0 - (isMove ? 0.5 : 0))) + Math.min(0.5, touchingPl != null ? Math.pow(touchingPl.size, 2) : 0 * 0.5 / Math.pow(animalSize, 2)));
    }
    
    double dx;
    double dy;

    double xSpeed;
    double ySpeed;
    public void doubleMove(double v){
        xSpeed = v * Math.cos(Math.toRadians(animalRotation));
        ySpeed = v * Math.sin(Math.toRadians(animalRotation));
        dx += xSpeed;
        dy += ySpeed;

        if(Math.abs(dx - targetX) < xSpeed && Math.abs(dy - targetY) < ySpeed){
            dx = targetX;
            dy = targetY;
        }
        
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
        if(touchWater && location != 0 && waterSpeed ==0 && !inHole || inHole && !touchHR && location==1 || air < maxAir && !touchWater && respiratorySystem <= 0.5 && location == 3 ||
        !touchWater && location != 0 && !inHole && speed==0 || inHole && !touchHR && location==3 ||
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
            setTeam(touchingAn, MyWorld.classificationOfEnemies);

        }
        if(MyWorld.plMode ==2 && touchingEgg !=null){
            setTeamForEgg(touchingEgg, MyWorld.classificationOfEnemies);
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

        calcMask();
        isMove = false;

        touchHole();
    }

    public void setTeam(Animal animal, double classificationOfSpecies){
        boolean mySpecies = true;
        for(int i = 0; i < dna.size(); i++){
            //System.out.println(i + " " + Math.abs(dna.get(i) - animalInRange.dna.get(i)) + " " + Math.abs(dna.get(i) - animalInRange.dna.get(i)) / Math.max(dna.get(i), animalInRange.dna.get(i)));
            if(Math.min(dna.get(i), animal.dna.get(i)) <= 1 && Math.abs(dna.get(i) - animal.dna.get(i)) > classificationOfSpecies || Math.min(dna.get(i), animal.dna.get(i)) > 1 && Math.abs(dna.get(i) - animal.dna.get(i)) / Math.max(dna.get(i), animal.dna.get(i)) > classificationOfSpecies){
                mySpecies = false;
                break;
            }
        }
        if (mySpecies) {
            animal.teamNum = 0;
        } else {
            animal.teamNum = 1;
        }
    }

    public void setTeamForEgg(Egg animal, double classificationOfSpecies){
        boolean mySpecies = true;
        for(int i = 0; i < dna.size(); i++){
            //System.out.println(i + " " + Math.abs(dna.get(i) - animalInRange.dna.get(i)) + " " + Math.abs(dna.get(i) - animalInRange.dna.get(i)) / Math.max(dna.get(i), animalInRange.dna.get(i)));
            if(Math.min(dna.get(i), animal.dna.get(i)) <= 1 && Math.abs(dna.get(i) - animal.dna.get(i)) > classificationOfSpecies || Math.min(dna.get(i), animal.dna.get(i)) > 1 && Math.abs(dna.get(i) - animal.dna.get(i)) / Math.max(dna.get(i), animal.dna.get(i)) > classificationOfSpecies){
                mySpecies = false;
                break;
            }
        }
        if (mySpecies) {
            animal.teamNum = 0;
        } else {
            animal.teamNum = 1;
        }
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

        if(air<=0 && location==3 && respiratorySystem > 0.5){
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

        //System.out.println(fly + " " + location + " " + respiratorySystem + " " + this);
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
        isStopFly = true;
    }

    int stayingOnTheGround;
    public void fly(){
        if(hibernation && location == 0){
            stopFlying();
        }
        if(isStopFly){
            fly = false;
        }

        if(flyCof > 1 && !fly){
            flyCof -= 0.05;
            if(flyCof < 1){
                flyCof = 1;
            }
        }
        else if(fly){
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

        if(stayingOnTheGround > 0){
            stayingOnTheGround --;
        }

        if(!fly && flyCof == 1 && location==0){
            location=1;
            stayingOnTheGround = 20;
            isStopFly = false;
        }
    }
    
    public void die(){
        if(satiety <=0 || water <= 0 || air <= 0){
            hp-=50;
        }
        if(myAge > maxAge){
            hp =0;
        }
        if(hp <= 0 && getWorld() != null){
            if(MyWorld.observedAnimal ==this){
                MyWorld.observedAnimal =null;
            }

            if(MyWorld.plMode == 2) {
                if (predation < 0.7 && predation > 0.3) {
                    myPlayer.omnivorous--;
                }
                else if(predation < 0.3){
                    myPlayer.myAn--;
                }
                else{
                    myPlayer.predators--;
                }
            }
            else{
                myPlayer.myAn--;
            }
            /*if(predation > 0.3) {
                MyWorld mw = (MyWorld) getWorld();
                System.out.println(water + " " + satiety + " " + air + " " + hunterPlant + " " + hunterAnimal + " " + damage + " " + protection + " " + (mw.chartTimer / 500));
            }*/
            DieAnimal da=new DieAnimal(animalSize, satiety, maxSatiety);
            getWorld().addObject(da,getX(),getY());
            getWorld().removeObject(this);
        } 
    }
}

