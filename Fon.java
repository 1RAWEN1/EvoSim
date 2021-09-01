import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fon extends Actor
{
    /**
     * Act - do whatever the Fon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Label temp=new Label("",30);
    Label unLab=new Label("",30);
    int red;
    int blue;
    public static int w=4800;
    public static double cof;
    
    GreenfootImage newSelect;
    
    GreenfootImage sword=new GreenfootImage("sword.png");
    GreenfootImage shield=new GreenfootImage("shield.png");
    GreenfootImage mask=new GreenfootImage("mask.png");
    
    GreenfootImage think=new GreenfootImage("think.png");
    int winTimer;
    public Fon(GreenfootImage im1){
        setImage(im1);
    }
    public void setImage1(GreenfootImage im, GreenfootImage image){
        image.clear();

        image.drawImage(im,0,0);

        temp.setValue("Temp:"+MyWorld.pl.tg);
        if(MyWorld.pl.tg>36){
            red=Math.abs(MyWorld.pl.tg-36)*20;
            if(red>255){
                red=255;
            }
            temp.setFillColor(new Color(red, 255-red/2,0,255));
        }
        else{
            blue=Math.abs(MyWorld.pl.tg-36)*20;
            if(blue>255){
                blue=255;
            }
            temp.setFillColor(new Color(0, 255-blue/2,blue,255));
        }
        if(MyWorld.plMode ==0){
            image.drawImage(temp.updateImage(), 0,240);
        }
        else if(MyWorld.plMode ==1){
            image.drawImage(temp.updateImage(), 0,100);
        }
        else if(MyWorld.plMode ==2){
            image.drawImage(temp.updateImage(), 0,70);
        }
        if(MyWorld.bot !=null){
            image.drawImage(drawBar(100,20,MyWorld.pl.myAn,30,Color.WHITE,new Color(0,255,255,255),"животные"),0,10);
            image.drawImage(drawBar(100,20,MyWorld.bot.myAn,30,Color.WHITE,new Color(255,30,0,255),"животные"),0,40);
            image.drawImage(drawBar(100,20,MyWorld.plants,50,Color.WHITE,Color.GREEN,"растения"),0,70);
        }
        else{
            image.drawImage(drawBar(100,20,MyWorld.pl.myAn,200,Color.WHITE,new Color(0,255,255,255),"животные"),0,10);
            image.drawImage(drawBar(100,20,MyWorld.plants,200,Color.WHITE,Color.GREEN,"растения"),0,40);
        }

        if(MyWorld.pl.can==0){
            unLab.setValue("Super: 0");
        }
        else if(MyWorld.pl.selectionOfSuper ==1){
            unLab.setValue("Super: растения");
            image.setColor(Color.GREEN);
            image.drawOval(600-(int)(25*cof),350-(int)(25*cof),(int)(50*cof),(int)(50*cof));
        }
        else if(MyWorld.pl.selectionOfSuper ==2){
            unLab.setValue("Super: жизнь");
            image.setColor(new Color(0,255,255,255));
            image.drawOval(600-(int)(15*cof),350-(int)(15*cof),(int)(30*cof),(int)(30*cof));
        }
        else if(MyWorld.pl.selectionOfSuper ==3){
            unLab.setValue("Super: эво");
            image.setColor(new Color(0,255,255,255));
            image.drawOval(600-(int)(50*cof),350-(int)(50*cof),(int)(100*cof),(int)(100*cof));
        }
        else{
            unLab.setValue("Super: 1");
        }
        unLab.setFillColor(Color.BLACK);
        if(MyWorld.plMode >0){
            image.drawImage(unLab.updateImage(),0,160);
        }
        else{
            image.drawImage(unLab.updateImage(),0,300);
        }
        if(MyWorld.plMode <2){
            if(MyWorld.pl.myAn>=30 || MyWorld.bot.xp<=0){
                unLab.setValue("ПОБЕДА!");
                unLab.setFillColor(Color.GREEN);
                image.drawImage(unLab.updateImage2(),600-(unLab.updateImage().getWidth()/2),350);
                if(winTimer ==0){
                    winTimer =100;
                }
            }
            else if(MyWorld.bot.myAn>=30 || MyWorld.pl.xp<=0){
                unLab.setValue("ПОРАЖЕНИЕ!");
                unLab.setFillColor(Color.RED);
                image.drawImage(unLab.updateImage2(),600-(unLab.updateImage().getWidth()/2),350);
                if(winTimer ==0){
                    winTimer =100;
                }
            }
            else if(MyWorld.plants>=50){
                unLab.setValue("НИЧЬЯ!");
                unLab.setFillColor(Color.BLACK);
                image.drawImage(unLab.updateImage2(),600-(unLab.updateImage().getWidth()/2),350);
                if(winTimer ==0){
                    winTimer =100;
                }
            }
            unLab.setFillColor(Color.BLACK);
            
            unLab.setValue("Evo: "+MyWorld.pl.elixir);
            if(MyWorld.plMode ==1){
                image.drawImage(unLab.updateImage(),0,130);
            }
            else{
                image.drawImage(unLab.updateImage(),0,270);
            }
        }
        if(MyWorld.plMode ==0){
            image.drawImage(drawBar(100,20,MyWorld.pl.water2,MyWorld.pl.mw,new Color(153,217,234),new Color(0,126,232),"вода"),0, 130);
            image.drawImage(drawBar(100,20,MyWorld.pl.sit1,MyWorld.pl.maxSatiety,new Color(255,242,0),new Color(255,127,39),"еда"),0, 160);
            image.drawImage(drawBar(100,20,MyWorld.pl.xp,MyWorld.pl.mxp,new Color(181,230,29),new Color(34,177,76),"здоровье"),0, 190);
            image.drawImage(drawBar(100,20,MyWorld.pl.air,MyWorld.pl.mair,Color.WHITE,new Color(205,205,205),"О2"), 0,215);
        }
        
        if(MyWorld.observedAnimal !=null){
            image.drawImage(drawBar(100,20,MyWorld.observedAnimal.water,MyWorld.observedAnimal.maxWater,new Color(153,217,234),new Color(0,126,232),"вода"),1100, 10);
            image.drawImage(drawBar(100,20,MyWorld.observedAnimal.satiety,MyWorld.observedAnimal.maxSatiety,new Color(255,242,0),new Color(255,127,39),"еда"),1100, 40);
            image.drawImage(drawBar(100,20,MyWorld.observedAnimal.hp,MyWorld.observedAnimal.maxHp,new Color(181,230,29),new Color(34,177,76),"здоровье"),1100, 70);
            image.drawImage(drawBar(100,20,MyWorld.observedAnimal.air,MyWorld.observedAnimal.maxAir,Color.WHITE,new Color(205,205,205),"О2"), 1100,100);
            unLab.setValue("Temp:"+MyWorld.observedAnimal.creatureTemp);
            if(MyWorld.observedAnimal.creatureTemp >36){
                red=Math.abs(MyWorld.observedAnimal.creatureTemp -36)*20;
                if(red>255){
                    red=255;
                }
                unLab.setFillColor(new Color(red, 255-red/2,0,255));
            }
            else{
                blue=Math.abs(MyWorld.observedAnimal.creatureTemp -36)*20;
                if(blue>255){
                    blue=255;
                }
                unLab.setFillColor(new Color(0, 255-blue/2,blue,255));
            }
            image.drawImage(unLab.updateImage(), 1100,125);
            unLab.setValue("Age:"+MyWorld.observedAnimal.myAge +"/"+MyWorld.observedAnimal.maxAge);
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1100,150);
            image.drawImage(sword,1110,180);
            unLab.setValue(""+(MyWorld.observedAnimal.damage + MyWorld.observedAnimal.poison));
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1150,180);
            image.drawImage(shield,1110,210);
            unLab.setValue(""+(int)(MyWorld.observedAnimal.protection*100));
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1150,210);
            image.drawImage(mask,1110,240);
            unLab.setValue(""+(int)(MyWorld.observedAnimal.maskCof *100));
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1150,240);
            if(MyWorld.observedAnimal.predation <=0.3){
                unLab.setValue("растительноядное");
            }
            else if(MyWorld.observedAnimal.predation >=0.7){
                unLab.setValue("хищник");
            }
            else{
                unLab.setValue("всеядное");
            }
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1100,270);
        }
        else if(MyWorld.observedPlant != null){
            image.drawImage(drawBar(100,20,MyWorld.observedPlant.water, MyWorld.observedPlant.maxWater,new Color(153,217,234),new Color(0,126,232),"вода"),1100, 10);
            image.drawImage(drawBar(100,20,MyWorld.observedPlant.satiety, MyWorld.observedPlant.maxSatiety,new Color(255,242,0),new Color(255,127,39),"еда"),1100, 40);
            image.drawImage(drawBar(100,20,MyWorld.observedPlant.hp, MyWorld.observedPlant.maxHp,new Color(181,230,29),new Color(34,177,76),"здоровье"),1100, 70);
            unLab.setValue("Temp:"+MyWorld.observedPlant.creatureTemp);
            if(MyWorld.observedPlant.creatureTemp >36){
                red=Math.abs(MyWorld.observedPlant.creatureTemp -36)*20;
                if(red>255){
                    red=255;
                }
                unLab.setFillColor(new Color(red, 255-red/2,0,255));
            }
            else{
                blue=Math.abs(MyWorld.observedPlant.creatureTemp -36)*20;
                if(blue>255){
                    blue=255;
                }
                unLab.setFillColor(new Color(0, 255-blue/2,blue,255));
            }
            image.drawImage(unLab.updateImage(), 1100,95);
            unLab.setValue("Age:"+MyWorld.observedPlant.myAge +"/"+MyWorld.observedPlant.age);
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1100,120);
            image.drawImage(sword,1110,150);
            unLab.setValue(""+(MyWorld.observedPlant.damage + MyWorld.observedPlant.poison));
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1150,150);
        }
        if(MyWorld.pl.newSelection ==1){
            newSelect=new GreenfootImage(450,100);
            for(int i=0;i<6;i+=2){
                newSelect.setColor(Color.WHITE);
                newSelect.drawImage(think,(i/2)*100+(i/2)*50,0);
                if(MyWorld.pl.parameters[i+1]==1){
                    newSelect.setColor(Color.GREEN);
                }
                else{
                    newSelect.setColor(Color.RED);
                }
                newSelect.drawRect((i/2)*100+(i/2)*50,0,100,99);
            }
            image.drawImage(newSelect,600-newSelect.getWidth()/2,400);
            image.setColor(Color.RED);
            image.drawOval(600-(int)(50*cof),350-(int)(50*cof),(int)(100*cof),(int)(100*cof));
        }
        if(MyWorld.pl.newSelection ==1 && MyWorld.pl.selection!=0){
            unLab.setValue(Lobby.descriptionOfParameters.get(MyWorld.pl.parameters[(MyWorld.pl.selection-1)*2]));
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage2(),600-unLab.updateImage2().getWidth()/2,400);
            if(MyWorld.pl.parameters[((MyWorld.pl.selection-1)*2)+1]==1){
                unLab.setValue((int)(100*MyWorld.pl.val)+" %");
            }
            else{
                unLab.setValue((int)(-100*MyWorld.pl.val)+" %");
            }
            image.drawImage(unLab.updateImage(),0,430);
        }
        if(Lobby.slide==5 && Greenfoot.mousePressed(null)){
            Lobby.slide++;
        }
        if(Lobby.train){
            unLab.setValue(Lobby.scenario.get(Lobby.slide));
            image.drawImage(unLab.updateImage2(),600-unLab.updateImage2().getWidth()/2,100);
        }
        setImage(image);
    }
    public void act() 
    {
        if(MyWorld.plMode ==2){
            if(Greenfoot.isKeyDown(Lobby.buttons.get(5))){
                w+=50*cof;
                if(w>4800){
                    w=4800;
                }
            }
            if(Greenfoot.isKeyDown(Lobby.buttons.get(6))){
                w-=50*cof;
                if(w<1200){
                    w=1200;
                }
            }
        }
        if(winTimer >0){
            winTimer--;
            if(winTimer ==0){
                Greenfoot.setWorld(new Lobby());
            }
        }// Add your action code here.
    } 
    
    Label name1;
    GreenfootImage b=new GreenfootImage(100,20);
    public GreenfootImage drawBar(int w, int h, int num, int num1, Color c1, Color c2, String name){
        //b.scale(w,h);
        b.clear();
        b.setColor(Color.BLACK);
        b.fill();
        b.setColor(c1);
        b.fillRect(1,1,w-2,h-2);
        b.setColor(c2);
        b.fillRect(1,1,(int)((w-2)*((double)num/num1)),h-2);
        b.setColor(new Color(230,230,230,230));
        b.fillRect(2,2,(int)((w-2)*((double)num/num1))-2,2+(int)(0.08*h));
        b.setColor(new Color(230,230,230,150));
        b.fillRect(2,4+(int)(0.08*h),(int)((w-2)*((double)num/num1))-2,2+(int)(0.08*h));
        b.setColor(new Color(0,0,0,80));
        b.fillRect(2,h-(5+(int)(0.08*h)*2),(int)((w-2)*((double)num/num1))-2,2+(int)(0.08*h));
        b.setColor(new Color(0,0,0,120));
        //h-(3+(int)(0.08*h)) 2+(int)(0.08*h)
        b.fillRect(2,h-(3+(int)(0.08*h)),(int)((w-2)*((double)num/num1))-2,2+(int)(0.08*h));
        name1=new Label(name,30);
        name1.setFillColor(c2);
        b.drawImage(name1.updateImage3(),(w/2)-name1.updateImage3().getWidth()/2,(h/2)-(name1.updateImage3().getHeight()/2));
        return b;
    }
}
