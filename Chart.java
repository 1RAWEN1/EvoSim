import greenfoot.*;
import java.util.ArrayList;

public class Chart extends Actor{

    int x1;
    int y1;
    int x2;
    int y2;
    int x;
    int y;

    int maxX;
    int maxY;

    GreenfootImage myImage;

    ArrayList<ArrayList<Integer>> valueX =new ArrayList<>();
    ArrayList<ArrayList<Integer>> valueY =new ArrayList<>();

    public Chart(int lines, int xSize, int ySize){
        myImage = new GreenfootImage(xSize, ySize);
        for(int i = 0; i < lines; i++){
            valueX.add(new ArrayList<>());
            valueY.add(new ArrayList<>());
        }
    }

    public void updateImage(){
        myImage.clear();

        myImage.setColor(Color.WHITE);
        myImage.fill();

        myImage.setColor(Color.GREEN);
        updateImage1(1, 200);

        myImage.setColor(Color.BLUE);
        updateImage1(2, 200);

        myImage.setColor(new Color(75, 0, 130));
        updateImage1(3, 200);

        myImage.setColor(Color.RED);
        updateImage1(4, 200);

        setImage(myImage);
    }

    public void updateImage1(int num, int maxYPos){
        num--;
        x1=0;

        for(int i = 0; i < valueX.get(num).size(); i++){
            if(valueX.get(num).get(i)> maxX){
                maxX = valueX.get(num).get(i);
            }
            if(valueY.get(num).get(i)> maxY){
                maxY = valueY.get(num).get(i);
            }
        }
        if(maxYPos > 0){
            maxY = maxYPos;
        }

        if(maxY > 0 && maxX > 0){
            y1=myImage.getHeight()-((valueY.get(num).get(0) / maxY)*myImage.getHeight());
            for(int i = 0; i< valueX.get(num).size(); i++){
                x2= valueX.get(num).get(i);
                y2= valueY.get(num).get(i);
                x=(int)(((double)(x2)/ maxX)*myImage.getWidth());
                y=myImage.getHeight()-(int)(((double)(y2)/ maxY)*myImage.getHeight());
                myImage.drawLine(x1,y1,x,y);
                x1=x;
                y1=y;
            }
        }
    }

    public void addValue(boolean xPos, int num, int value){
        num--;

        if(xPos){
            valueX.get(num).add(value);
        }
        else{
            valueY.get(num).add(value);
        }
    }
}
