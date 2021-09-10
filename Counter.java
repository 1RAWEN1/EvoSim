import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Counter class that allows you to display a numerical value on screen.
 * 
 * The Counter is an actor, so you will need to create it, and then add it to
 * the world in Greenfoot.  If you keep a reference to the Counter then you
 * can adjust its value.  Here's an example of a world class that
 * displays a counter with the number of act cycles that have occurred:
 * 
 * <pre>
 * class CountingWorld
 * {
 *     private Counter actCounter;
 *     
 *     public CountingWorld()
 *     {
 *         super(600, 400, 1);
 *         actCounter = new Counter("Act Cycles: ");
 *         addObject(actCounter, 100, 100);
 *     }
 *     
 *     public void act()
 *     {
 *         actCounter.setValue(actCounter.getValue() + 1);
 *     }
 * }
 * </pre>
 * 
 * @author Neil Brown and Michael Kölling 
 * @version 1.0
 */
public class Counter extends Actor
{
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    private int value;
    private int target;
    private String prefix;
    int num;
    
    public Counter(int num)
    {
        this(Lobby.buttons.get(num),num);
    }

    /**
     * Create a new counter, initialised to 0.
     */
    public Counter(String prefix, int num)
    {
        this.num=num;
        background = getImage();  // get image from class
        value = 0;
        target = 0;
        this.prefix = prefix;
        updateImage();
    }
    
    /**
     * Animate the display to count up (or down) to the current target value.
     */
    boolean mpress;
    public void act() 
    {
        if (value < target) {
            value++;
            updateImage();
        }
        else if (value > target) {
            value--;
            updateImage();
        }
        if(Greenfoot.mousePressed(null)){
            if(Greenfoot.mousePressed(this)){
                mpress=true;
                Greenfoot.getKey();
            }
            else{
                mpress=false;
            }
        }
        if(!mpress){
            fillColor=Color.BLACK;
        }
        else if(mpress){
            fillColor=Color.RED;
        }
        
        if(mpress){
            String k=Greenfoot.getKey();
            //MouseInfo mi=Greenfoot.getMouseInfo();
            if(k!=null){
                Lobby.buttons.set(num,k);
                mpress=false;
            }
            /*else if(Greenfoot.mousePressed(null) && mi.getButton()==1){
                Lobby.b.set(num,"пкм");
                setPrefix(Lobby.b.get(num));
                mpress=false;
            }
            else if(Greenfoot.mousePressed(null) && mi.getButton()==3){
                Lobby.b.set(num,"лкм");
                setPrefix(Lobby.b.get(num));
                mpress=false;
            }*/
        }
        
        if(Lobby.buttons.get(num)!=null){
            setPrefix(Lobby.buttons.get(num));
        }
        else{
            setPrefix("");
        }
    }

    /**
     * Add a new score to the current counter value.  This will animate
     * the counter over consecutive frames until it reaches the new value.
     */
    public void add(int score)
    {
        target += score;
    }

    /**
     * Return the current counter value.
     */
    public int getValue()
    {
        return target;
    }

    /**
     * Set a new counter value.  This will not animate the counter.
     */
    public void setValue(int newValue)
    {
        target = newValue;
        value = newValue;
        updateImage();
    }
    
    /**
     * Sets a text prefix that should be displayed before
     * the counter value (e.g. "Score: ").
     */
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
        updateImage();
    }

    /**
     * Update the image on screen to show the current value.
     */
    Color fillColor=Color.BLACK;
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(background);
        GreenfootImage text = new GreenfootImage(prefix, 22, fillColor, transparent);
        
        if (text.getWidth() > image.getWidth() - 20)
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }
        
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                        (image.getHeight()-text.getHeight())/2);
        setImage(image);
    }
}
