import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * A Label class that allows you to display a textual value on screen.
 * 
 * The Label is an actor, so you will need to create it, and then add it to the world
 * in Greenfoot.  If you keep a reference to the Label then you can change the text it
 * displays.  
 *
 * @author Amjad Altadmri 
 * @version 1.1
 */
public class Label extends Actor
{
    private String value;
    private int fontSize;
    Color fonColor=new Color(255,255,255,100);
    private Color lineColor = new Color(0,0,0,0);
    private Color fillColor = Color.BLACK;
    
    private static final Color transparent = new Color(0,0,0,0);

    
    /**
     * Create a new label, initialise it with the int value to be shown and the font size 
     */
    public Label(int value, int fontSize)
    {
        this(Integer.toString(value), fontSize);
    }
    
    /**
     * Create a new label, initialise it with the needed text and the font size 
     */
    public Label(String value, int fontSize)
    {
        this.value = value;
        this.fontSize = fontSize;
        updateImage1();
    }
    
    public void setFontSize(int fontSize){
        this.fontSize = fontSize;
    }

    /**
     * Sets the value  as text
     * 
     * @param value the text to be show
     */
    public void setValue(String value)
    {
        this.value = value;
        updateImage1();
    }
    
    /**
     * Sets the value as integer
     * 
     * @param value the value to be show
     */
    public void setValue(int value)
    {
        this.value = Integer.toString(value);
        updateImage1();
    }
    
    /**
     * Sets the line color of the text
     * 
     * @param lineColor the line color of the text
     */
    public void setLineColor(Color lineColor)
    {
        this.lineColor = lineColor;
        updateImage1();
    }
    
    /**
     * Sets the fill color of the text
     * 
     * @param fillColor the fill color of the text
     */
    public void setFillColor(Color fillColor)
    {
        this.fillColor = fillColor;
        if(this.fillColor!=Color.BLACK){
            lineColor=Color.BLACK;
        }
        updateImage1();
    }
    private void updateImage1()
    {
        fon=new GreenfootImage(value, fontSize, fillColor, fonColor, lineColor);
        //fon.drawImage(new GreenfootImage(value, fontSize, fillColor, fillColor),1,1);
        /*fon1=new GreenfootImage(fon.getWidth(),fon.getHeight());
        fon1.setColor(new Color(255,255,255,150));
        fon1.fill();
        fon1.drawImage(fon,0,0);*/
        setImage(fon);
    }
    /**
     * Update the image on screen to show the current value.
     */
    GreenfootImage fon;
    //GreenfootImage fon1;
    public GreenfootImage updateImage()
    {
        fon=new GreenfootImage(value, fontSize, fillColor, fonColor, lineColor);
        //fon.drawImage(new GreenfootImage(value, fontSize, fillColor, new Color(0,0,0,0)),-1,1);
        if(fon.getWidth()>100){
            fon.scale(100,(int)(((double)100/fon.getWidth())*fon.getHeight()));
        }
        /*fon1=new GreenfootImage(100,30);
        fon1.setColor(new Color(255,255,255,200));
        fon1.fill();
        //fon1.drawImage(fon,0,0);
        fon1.setColor(lineColor);
        fon1.setFont(new Font("Butler",false,false,26));
        fon1.drawString(value,0,26);
        fon1.setColor(fillColor);
        fon1.setFont(new Font("Butler",false,false,25));
        fon1.drawString(value,2,25);*/
        return fon;
    }
    
    public GreenfootImage updateImage2()
    {
        fon=new GreenfootImage(value, fontSize, fillColor, fonColor, lineColor);
        /*fon1=new GreenfootImage(fon.getWidth(),fon.getHeight());
        fon1.setColor(new Color(255,255,255,150));
        fon1.fill();
        fon1.drawImage(fon,0,0);*/
        return fon;
    }
    
    public GreenfootImage updateImage3()
    {
        fon=new GreenfootImage(value, fontSize, fillColor, transparent, lineColor);
        if(fon.getWidth()>100){
            fon.scale(100,(int)(((double)100/fon.getWidth())*fon.getHeight()));
        }
        /*fon1=new GreenfootImage(100,20);
        fon1.setFont(new Font("Butler",false,false,22));
        //fon1.drawImage(fon,0,0);
        fon1.setColor(fillColor);
        fon1.drawString(value,0,18);*/
        return fon;
    }
    
    public GreenfootImage updateImage4()
    {
        fon=new GreenfootImage(value, fontSize, fillColor, transparent, lineColor);
        /*fon1=new GreenfootImage(fon.getWidth(),fon.getHeight());
        fon1.setColor(new Color(255,255,255,150));
        fon1.fill();
        fon1.drawImage(fon,0,0);*/
        return fon;
    }
    
    public void act(){
        if(value.equals("По умолчанию") && Greenfoot.mousePressed(this)){
            Lobby.buttons.set(0,"E");
            Lobby.buttons.set(1,"Q");
            Lobby.buttons.set(2,"1");
            Lobby.buttons.set(3,"2");
            Lobby.buttons.set(4,"3");
            Lobby.buttons.set(5,"=");
            Lobby.buttons.set(6,"-");
            Lobby.buttons.set(7,"лкм");
        }
    }
}