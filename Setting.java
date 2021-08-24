import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Setting here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Setting extends World
{

    /**
     * Constructor for objects of class Setting.
     * 
     */
    public Setting()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1); 
        prepare();
    }
    
    Scroller sc;
    Label volumeLab;
    MBut mBut;
    public void prepare(){
        for(int i=0;i<20;i++){
            DecorAnimal da=new DecorAnimal(getWidth());
            da.r=Greenfoot.getRandomNumber(360);
            addObject(da,Greenfoot.getRandomNumber(getWidth()),Greenfoot.getRandomNumber(getHeight()));
        }
        SFon sf=new SFon();
        addObject(sf,600,400);
        for(int i1 = 0; i1<Lobby.buttons.size(); i1++){
            addObject(new Counter(i1),500,300+i1*30);
        }
        Label lab=new Label("Улучшение: ",30);
        addObject(lab,300,300);
        Label lab1=new Label("Супер: ",30);
        addObject(lab1,300,330);
        Label lab2=new Label("Выбор карточки(1): ",30);
        addObject(lab2,300,360);
        Label lab3=new Label("Выбор карточки(2): ",30);
        addObject(lab3,300,390);
        Label lab4=new Label("Выбор карточки(3): ",30);
        addObject(lab4,300,420);
        Label lab5=new Label("Прокачка(увеличение): ",30);
        addObject(lab5,300,450);
        Label lab6=new Label("Прокачка(уменьшение): ",30);
        addObject(lab6,300,480);
        Label lab7=new Label("Погружение: ",30);
        addObject(lab7,300,510);
        Label lab8=new Label("По умолчанию",30);
        addObject(lab8,400,550);
        Back b=new Back();
        addObject(b,100,100);
        sc=new Scroller(volume);
        addObject(sc,850,300);
        volumeLab=new Label("",30);
        addObject(volumeLab,1000,300);
        mBut = new MBut();
        addObject(mBut,700,300);
    }
    
    static int volume=100;
    public void act(){
        volume=sc.getValue();
        if(!mBut.getStatus()){
            volume=0;
        }
        volumeLab.setValue(""+volume+" %");
    }
}
