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
    int wintimer;
    public Fon(GreenfootImage im1){
        setImage(im1);
    }
    public void setImage1(GreenfootImage im, GreenfootImage image){
        image.clear();
        //image.setColor(new Color(80,80,80,255));
        //image.fill();
        //System.out.println(((double)w/getWorld().getWidth()));
        //im.scale(1200,700);
        //image.drawImage(im,500-(int)(cof*MyWorld.pl.getX()),300-(int)(cof*MyWorld.pl.getY()));
        image.drawImage(im,0,0);
        //image.setColor(Color.GRAY);
        //image.fillRect(0,0,100,getWorld().getHeight());
        //image.fillRect(getWorld().getWidth()-100,0,100,getWorld().getHeight());
        //image.setColor(Color.BLACK);
        //image.drawRect(100,-100,1000,1000);
        temp.setValue("Temp:"+MyWorld.pl.tg);
        if(MyWorld.pl.tg>36){
            red=Math.abs(MyWorld.pl.tg-36)*20;
            if(red>255){
                red=255;
            }
            temp.setFillColor(new Color(red, 255-red/2,0,255));
        }
        else if(MyWorld.pl.tg<=36){
            blue=Math.abs(MyWorld.pl.tg-36)*20;
            if(blue>255){
                blue=255;
            }
            temp.setFillColor(new Color(0, 255-blue/2,blue,255));
        }
        if(MyWorld.plmode==0){
            image.drawImage(temp.updateImage(), 0,240);
        }
        else if(MyWorld.plmode==1){
            image.drawImage(temp.updateImage(), 0,100);
        }
        else if(MyWorld.plmode==2){
            image.drawImage(temp.updateImage(), 0,70);
        }
        image.drawImage(drawBar(100,20,MyWorld.pl.myAn,30,Color.WHITE,new Color(0,255,255,255),"животные"),0,10);
        if(MyWorld.bot1!=null){
            image.drawImage(drawBar(100,20,MyWorld.bot1.myAn,30,Color.WHITE,new Color(255,30,0,255),"животные"),0,40);
            image.drawImage(drawBar(100,20,MyWorld.plants,50,Color.WHITE,Color.GREEN,"растения"),0,70);
        }
        else{
            image.drawImage(drawBar(100,20,MyWorld.plants,50,Color.WHITE,Color.GREEN,"растения"),0,40);
        }
        /*image.setColor(Color.WHITE);
        image.fillRect(0,10,100,20);
        image.setColor(Color.BLACK);
        image.drawRect(0,10,100,20);
        image.setColor(new Color(0,255,255,255));
        image.fillRect(1,11,(int)(((double)MyWorld.pl.myAn/30)*98),19);
        image.setColor(Color.WHITE);
        image.fillRect(0,40,100,20);
        image.setColor(Color.BLACK);
        image.drawRect(0,40,100,20);
        image.setColor(new Color(255,30,0,255));
        image.fillRect(1,41,(int)(((double)MyWorld.bot1.myAn/30)*98),19);
        image.setColor(Color.WHITE);
        image.fillRect(0,70,100,20);
        image.setColor(Color.BLACK);
        image.drawRect(0,70,100,20);
        image.setColor(Color.GREEN);
        image.fillRect(1,71,(int)(((double)MyWorld.plants/50)*98),19);*/
        if(MyWorld.pl.can==0){
            unLab.setValue("Super: 0");
        }
        else if(MyWorld.pl.superselection==1){
            unLab.setValue("Super: растения");
            image.setColor(Color.GREEN);
            image.drawOval(600-(int)(25*cof),350-(int)(25*cof),(int)(50*cof),(int)(50*cof));
        }
        else if(MyWorld.pl.superselection==2){
            unLab.setValue("Super: жизнь");
            image.setColor(new Color(0,255,255,255));
            image.drawOval(600-(int)(15*cof),350-(int)(15*cof),(int)(30*cof),(int)(30*cof));
        }
        else if(MyWorld.pl.superselection==3){
            unLab.setValue("Super: эво");
            image.setColor(new Color(0,255,255,255));
            image.drawOval(600-(int)(50*cof),350-(int)(50*cof),(int)(100*cof),(int)(100*cof));
        }
        else{
            unLab.setValue("Super: 1");
        }
        unLab.setFillColor(Color.BLACK);
        if(MyWorld.plmode>0){
            image.drawImage(unLab.updateImage(),0,160);
        }
        else{
            image.drawImage(unLab.updateImage(),0,300);
        }
        if(MyWorld.plmode<2){
            if(MyWorld.pl.myAn>=30 || MyWorld.bot1.xp<=0){
                unLab.setValue("ПОБЕДА!");
                unLab.setFillColor(Color.GREEN);
                image.drawImage(unLab.updateImage2(),600-(unLab.updateImage().getWidth()/2),350);
                if(wintimer==0){
                    wintimer=100;
                }
            }
            else if(MyWorld.bot1.myAn>=30 || MyWorld.pl.xp<=0){
                unLab.setValue("ПОРАЖЕНИЕ!");
                unLab.setFillColor(Color.RED);
                image.drawImage(unLab.updateImage2(),600-(unLab.updateImage().getWidth()/2),350);
                if(wintimer==0){
                    wintimer=100;
                }
            }
            else if(MyWorld.plants>=50){
                unLab.setValue("НИЧЬЯ!");
                unLab.setFillColor(Color.BLACK);
                image.drawImage(unLab.updateImage2(),600-(unLab.updateImage().getWidth()/2),350);
                if(wintimer==0){
                    wintimer=100;
                }
            }
            unLab.setFillColor(Color.BLACK);
            
            unLab.setValue("Evo: "+MyWorld.pl.elixir);
            if(MyWorld.plmode==1){
                image.drawImage(unLab.updateImage(),0,130);
            }
            else{
                image.drawImage(unLab.updateImage(),0,270);
            }
        }
        if(MyWorld.plmode==0){
            //GreenfootImage water1=new GreenfootImage("Sit"+(MyWorld.pl.wt3)+".png");
            //water1.scale(water1.getWidth()*5, water1.getHeight()*5);
            image.drawImage(drawBar(100,20,MyWorld.pl.water2,MyWorld.pl.mw,new Color(153,217,234),new Color(0,126,232),"вода"),0, 130);
            //GreenfootImage sit1=new GreenfootImage("Sit"+(20+MyWorld.pl.sit2+1)+".png");
            //sit1.scale(sit1.getWidth()*5, sit1.getHeight()*5);
            image.drawImage(drawBar(100,20,MyWorld.pl.sit1,MyWorld.pl.msit,new Color(255,242,0),new Color(255,127,39),"еда"),0, 160);
            //GreenfootImage xp1=new GreenfootImage("XP"+(MyWorld.pl.xp*3/MyWorld.pl.mxp)+".png");
            //xp1.scale(xp1.getWidth()*5, xp1.getHeight()*5);
            image.drawImage(drawBar(100,20,MyWorld.pl.xp,MyWorld.pl.mxp,new Color(181,230,29),new Color(34,177,76),"здоровье"),0, 190);
            //unLab.setValue(MyWorld.pl.air+"/"+MyWorld.pl.mair);
            //unLab.setFillColor(Color.BLACK);
            image.drawImage(drawBar(100,20,MyWorld.pl.air,MyWorld.pl.mair,Color.WHITE,new Color(205,205,205),"О2"), 0,215);
        }
        
        if(MyWorld.plan!=null){
            image.drawImage(drawBar(100,20,MyWorld.plan.water2,MyWorld.plan.mw,new Color(153,217,234),new Color(0,126,232),"вода"),1100, 10);
            image.drawImage(drawBar(100,20,MyWorld.plan.sit1,MyWorld.plan.msit,new Color(255,242,0),new Color(255,127,39),"еда"),1100, 40);
            image.drawImage(drawBar(100,20,MyWorld.plan.xp,MyWorld.plan.mxp,new Color(181,230,29),new Color(34,177,76),"здоровье"),1100, 70);
            image.drawImage(drawBar(100,20,MyWorld.plan.air,MyWorld.plan.mair,Color.WHITE,new Color(205,205,205),"О2"), 1100,100);
            /*image.drawImage(drawBar(100,20,MyWorld.pl.air,MyWorld.pl.mair,Color.WHITE,new Color(205,205,205)), 0,215);
            GreenfootImage water=new GreenfootImage("Sit"+(MyWorld.plan.wt3)+".png");
            water.scale(water.getWidth()*5, water.getHeight()*5);
            image.drawImage(water,950-water.getWidth()/2, 20);
            GreenfootImage sit=new GreenfootImage("Sit"+(20+MyWorld.plan.sit2+1)+".png");
            sit.scale(sit.getWidth()*5, sit.getHeight()*5);
            image.drawImage(sit,950-sit.getWidth()/2, 60);
            GreenfootImage xp=new GreenfootImage("XP"+(MyWorld.plan.xp*3/MyWorld.plan.mxp)+".png");
            xp.scale(xp.getWidth()*5, xp.getHeight()*5);
            image.drawImage(xp,950-xp.getWidth()/2, 100);*/
            unLab.setValue("Temp:"+MyWorld.plan.tg);
            if(MyWorld.plan.tg>36){
                red=Math.abs(MyWorld.plan.tg-36)*20;
                if(red>255){
                    red=255;
                }
                unLab.setFillColor(new Color(red, 255-red/2,0,255));
            }
            else if(MyWorld.plan.tg<=36){
                blue=Math.abs(MyWorld.plan.tg-36)*20;
                if(blue>255){
                    blue=255;
                }
                unLab.setFillColor(new Color(0, 255-blue/2,blue,255));
            }
            image.drawImage(unLab.updateImage(), 1100,125);
            unLab.setValue("Age:"+MyWorld.plan.myage+"/"+MyWorld.plan.age);
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1100,150);
            image.drawImage(sword,1110,180);
            unLab.setValue(""+(MyWorld.plan.damage + MyWorld.plan.poisondam));
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1150,180);
            image.drawImage(shield,1110,210);
            unLab.setValue(""+(int)(MyWorld.plan.protection*100));
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1150,210);
            image.drawImage(mask,1110,240);
            unLab.setValue(""+(int)(MyWorld.plan.maskcof*100));
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1150,240);
            if(MyWorld.plan.xich<=0.3){
                unLab.setValue("растительноядное");
            }
            else if(MyWorld.plan.xich>=0.7){
                unLab.setValue("хищник");
            }
            else{
                unLab.setValue("всеядное");
            }
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1100,270);
        }
        else if(MyWorld.observedPlant != null){
            image.drawImage(drawBar(100,20,MyWorld.observedPlant.water2, MyWorld.observedPlant.mw,new Color(153,217,234),new Color(0,126,232),"вода"),1100, 10);
            image.drawImage(drawBar(100,20,MyWorld.observedPlant.colfood, MyWorld.observedPlant.mcolf,new Color(255,242,0),new Color(255,127,39),"еда"),1100, 40);
            image.drawImage(drawBar(100,20,MyWorld.observedPlant.xp, MyWorld.observedPlant.mxp,new Color(181,230,29),new Color(34,177,76),"здоровье"),1100, 70);
            unLab.setValue("Temp:"+MyWorld.observedPlant.tg);
            if(MyWorld.observedPlant.tg>36){
                red=Math.abs(MyWorld.observedPlant.tg-36)*20;
                if(red>255){
                    red=255;
                }
                unLab.setFillColor(new Color(red, 255-red/2,0,255));
            }
            else if(MyWorld.observedPlant.tg<=36){
                blue=Math.abs(MyWorld.observedPlant.tg-36)*20;
                if(blue>255){
                    blue=255;
                }
                unLab.setFillColor(new Color(0, 255-blue/2,blue,255));
            }
            image.drawImage(unLab.updateImage(), 1100,95);
            /*unLab.setValue(MyWorld.plan.air+"/"+MyWorld.plan.mair);
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1100,160);*/
            unLab.setValue("Age:"+MyWorld.observedPlant.myage+"/"+MyWorld.observedPlant.age);
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1100,120);
            image.drawImage(sword,1110,150);
            unLab.setValue(""+(MyWorld.observedPlant.damage + MyWorld.observedPlant.poisondam));
            unLab.setFillColor(Color.BLACK);
            image.drawImage(unLab.updateImage(), 1150,150);
        }
        if(MyWorld.pl.newselect==1){
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
                /*unLab.setValue("?");
                unLab.setFillColor(Color.BLACK);
                newSelect.drawImage(unLab.updateImage(), ((i/2)*100+(i/2)*50)+50-unLab.updateImage().getWidth()/2,50-unLab.updateImage().getHeight()/2);*/
            }
            image.drawImage(newSelect,600-newSelect.getWidth()/2,400);
            image.setColor(Color.RED);
            image.drawOval(600-(int)(50*cof),350-(int)(50*cof),(int)(100*cof),(int)(100*cof));
        }
        if(MyWorld.pl.newselect==1 && MyWorld.pl.selection!=0){
            unLab.setValue(Lobby.strVal.get(MyWorld.pl.parameters[(MyWorld.pl.selection-1)*2]));
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
        //MyWorld mw=(MyWorld)getWorld();
        //unLab.setValue("FPS: "+mw.fps);
        //image.drawImage(unLab.updateImage(),0,600);
        setImage(image);
    }
    public void act() 
    {
        if(MyWorld.plmode==2){
            if(Greenfoot.isKeyDown(Lobby.b.get(5))){
                w+=50*cof;
                if(w>4800){
                    w=4800;
                }
            }
            if(Greenfoot.isKeyDown(Lobby.b.get(6))){
                w-=50*cof;
                if(w<1200){
                    w=1200;
                }
            }
        }
        if(wintimer>0){
            wintimer--;
            if(wintimer==0){
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
