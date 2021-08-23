import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

import java.io.*;
/**
 * Write a description of class Lobby here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lobby extends World
{

    static ArrayList<String> b=new ArrayList<String>();
    public static ArrayList<String> strVal=new ArrayList<String>();
    static boolean pressStart=false;
    static ArrayList<String> scenario=new ArrayList<String>();
    /**
     * Constructor for objects of class Lobby.
     * 
     */
    int lindex;
    
    static int slide=0;
    static boolean train=true;
    public Lobby()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1); 
        pressStart=false;
        Help.mInArea=false;
        if(strVal.size()==0){
            strVal.add("Запас воздуха\nвлияет на время, которое животное может пробыть без кислорода");
            strVal.add("Система дыхания\nлегкие или жабры?");
            strVal.add("Умение лазать\nживотное сможет бегать по деревьям");
            strVal.add("Анабиоз\nв непригодной температуре сможет дольше быть в состоянии сна");
            strVal.add("Живорождение\nживотное будет рождаться сразу или в яйце?");
            strVal.add("Возраст для взросления\nвозраст, при котором показатели животного достигают максимума");
            strVal.add("Скорость полета\nскорость в воздухе");
            strVal.add("Урон от яда\nдополнительный урон");
            strVal.add("Маскировка\nживотное будет сложнее заметить");
            strVal.add("Запас воды\nживотное сможет дольше обходится без жидкости");
            strVal.add("Время между размножениями\nживотные будут быстрее увеличивать численность");
            strVal.add("Защита\nживотное будет получать меньше урона");
            strVal.add("Скорость в воде\nскорость плавания");
            strVal.add("Личинка\nживотные смогут появлятся на большом расстоянии от родителей");
            strVal.add("Максимальный возраст\nвремя жизни");
            strVal.add("Потомки\nколичество рожденных животных за один сезон");
            strVal.add("Скорость\nскорость на земле");
            strVal.add("Радиус обзора\nрасстояние видиния животного");
            strVal.add("Размер\nразмер животного");
            strVal.add("Мех\nживотное сможет выдерживать большую разницу температур");
            strVal.add("Хищник\nрастительно-, все- ядное или хищник?");
            strVal.add("Запас жира\nживотное сможет дольше обходится без еды");
            strVal.add("Скорость копания\nсможет быстрее копать норки");
        }
        
        if(b.size()==0){
            b.add("E");
            b.add("Q");
            b.add("1");
            b.add("2");
            b.add("3");
            b.add("=");
            b.add("-");
            b.add("лкм");
        }
        
        if(scenario.size()==0){
            scenario.add("Привет, новый биокриэйтор! У нас как раз есть парочка подходящих планет. Есть пустые, а есть с другими создателями жизни.");
            scenario.add("Для начала давай попробуем просто создать жизнь.");
            scenario.add("Для жизни нужна вода, необходимо ее найти, иначе животные сразу погибнут. Чтобы передвигаться использую W, A, S, D. Чтобы создать животных нажми пробел.");
            scenario.add("Теперь нужна еда. Для этого нажми Q, потом 1 и снова Q. Ты создашь растения, также их можно найти на карте.");
            scenario.add("Жизнь есть, теперь к своим прямым обязанностям. Биокриэйторы должны создавать жизнь на планете и поддерживать ее. Животные и сами могут приспособится к окружающей среде, но если биокриэйтор будет ей помогать, то этот процесс будет идти быстрее. Чтобы улучшить животных нажми Е, выбери параметр(1, 2, 3) и снова нажми Е. Также параметр можно прокачивать совсем немного или же менять полностью, для этого используй + и -.");
            scenario.add("Еще одним важным параметром является температура. Если разница температуры с 36 больше 10, то животное войдет в анабиоз и, если теспература не нормализуется, умрет.");
            scenario.add("Чтобы было удобнее, можно следить за параметрами животного, нажав на него правой кнопкой мыши. Что же, основы ты знаешь, теперь давай посмотрим другие планеты. Для этого нажми Backspace.");
            scenario.add("На новой планете будут соперники, которые хотят, чтобы именно их животные были главным видом планеты");
            scenario.add("Для победы необходимо, чтобы именно твои животные первыми достигли численности 30. Удачи!");
            for(int i=0;i<scenario.size();i++){
                String s=scenario.get(i);
                lindex=getWidth()/15;
                for(int i1=0;i1<s.length();i1++){
                    if(i1>=lindex && s.charAt(i1)==' '){
                        String s1=s.substring(0,i1);
                        String s2=s.substring(i1,s.length());
                        s=s1+"\n"+s2;
                        lindex+=getWidth()/15;
                        scenario.set(i,s);
                    }
                }
            }
        }
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    Ser ser;
    Label lab;
    private void prepare()
    {
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("save.ser"));
           
            ser = (Ser)ois.readObject();
            train=ser.train;
           
            ois.close();
        }catch(Exception e){}
            
        for(int i=0;i<20;i++){
            DecorAnimal da=new DecorAnimal(getWidth());
            da.r=Greenfoot.getRandomNumber(360);
            addObject(da,Greenfoot.getRandomNumber(getWidth()),Greenfoot.getRandomNumber(getHeight()));
        }
        EvoSim evoSim = new EvoSim();
        addObject(evoSim,600,180);
        Play play = new Play();
        addObject(play,600,350);
        AnimalBut ab=new AnimalBut();
        addObject(ab,600,500);
        Settings settings = new Settings();
        addObject(settings,600,650);
        Button b=new Button(1);
        addObject(b,600,350);
        Button b1=new Button(2);
        addObject(b1,600,500);
        Button b2=new Button(3);
        addObject(b2,600,650);
        if(train){
            if(slide==0 || slide==7){
                h=new Help(600,350,384,90);
                addObject(h,600,350);
            }
            lab=new Label(scenario.get(slide),30);
            addObject(lab,600,50);
        }

        Greenfoot.start();
        Greenfoot.setSpeed(100);
    }
    Help h;
    public void act(){
        if(Help.mInArea && Greenfoot.mousePressed(null) && Lobby.slide==0 && train){
            Lobby.slide++;
            removeObject(h);
            h=new Help(600,650,384,90);
            addObject(h,600,350);
            removeObject(lab);
            lab=new Label(scenario.get(slide),30);
            addObject(lab,600,50);
            pressStart=true;
        }
        else if(Help.mInArea && Greenfoot.mousePressed(null) && Lobby.slide==7 && !pressStart){
            removeObject(h);
            h=new Help(600,500,384,90);
            addObject(h,600,350);
            removeObject(lab);
            addObject(lab,600,50);
            pressStart=true;
        }
    }
}
