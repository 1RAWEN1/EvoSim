import greenfoot.*;
import java.util.ArrayList;

public class Chart extends Actor{

    int x1;
    int y1;
    double x2;
    double y2;
    int x;
    int y;

    double maxY;

    GreenfootImage myImage;

    int typeOfChart = 0;
    int maxTypeOfChart = MyWorld.dnaSizeOfAnimal + MyWorld.dnaSizeOfPlant + 1;

    Label lab = new Label("", 30);

    public void min() {
       typeOfChart--;
       if(typeOfChart < 0){
           typeOfChart = maxTypeOfChart + typeOfChart;
       }

       updateImage();
    }

    public void max() {
        typeOfChart++;

        typeOfChart %= maxTypeOfChart;

        updateImage();
    }

    ArrayList<ArrayList<Double>> valueY =new ArrayList<>();

    public Chart(int xSize, int ySize){
        myImage = new GreenfootImage(xSize, ySize);
        for(int i = 0; i < maxTypeOfChart + MyWorld.dnaSizeOfAnimal + 3; i++){
            valueY.add(new ArrayList<>());
        }

        createImage();
    }

    public void createImage(){
        myImage.clear();

        myImage.setColor(Color.WHITE);
        myImage.fill();

        setImage(myImage);
    }

    public void updateImage(){
        myImage.clear();

        myImage.setColor(Color.WHITE);
        myImage.fill();

        if(typeOfChart == 0) {
            myImage.setColor(Color.GREEN);
            updateImage1(1, 200);

            myImage.setColor(Color.BLUE);
            updateImage1(2, 200);

            myImage.setColor(new Color(75, 0, 130));
            updateImage1(3, 200);

            myImage.setColor(Color.RED);
            updateImage1(4, 200);
        }
        else if(typeOfChart - 1 < MyWorld.dnaSizeOfAnimal){
            updateImage2(5 + ((typeOfChart - 1) * 2));

            lab.setFillColor(new Color(0, 0, 0, 100));
            lab.setValue(Lobby.descriptionOfParameters.get(typeOfChart - 1));
            myImage.drawImage(lab.updateImage2(),600-lab.updateImage2().getWidth()/2,50);
        }
        else{
            myImage.setColor(Color.GREEN);
            updateImage1(5 + (MyWorld.dnaSizeOfAnimal * 2) + (typeOfChart - (MyWorld.dnaSizeOfAnimal + 1)), 0);

            lab.setFillColor(new Color(0, 0, 0, 100));
            lab.setValue(Lobby.descriptionOfPlantParameters.get(typeOfChart - (MyWorld.dnaSizeOfAnimal + 1)));
            myImage.drawImage(lab.updateImage2(),600-lab.updateImage2().getWidth()/2,50);
        }

        setImage(myImage);
    }

    public void updateImage1(int num, int maxYPos){
        num--;
        x1=0;
        maxY = 0;

        if(maxYPos > 0){
            maxY = maxYPos;
        }
        else{
            for(int i = 0; i < valueY.get(num).size(); i++){
                if(valueY.get(num).get(i)> maxY){
                    maxY = valueY.get(num).get(i);
                }
            }
        }

        if(maxY > 0){
            y1=(int) (myImage.getHeight()-((valueY.get(num).get(0) / maxY)*myImage.getHeight()));
            for(int i = 0; i< valueY.get(num).size(); i++){
                x2= i;
                y2= valueY.get(num).get(i);
                x=(int)(((x2 + 1) / valueY.get(num).size())*myImage.getWidth());
                y=myImage.getHeight()-(int)((y2 / maxY)*myImage.getHeight());
                myImage.drawLine(x1,y1,x,y);
                x1=x;
                y1=y;
            }
        }

        if(num > 3){
            lab.setFillColor(Color.GREEN);
            lab.setValue("Value: " + valueY.get(num).get(valueY.get(num).size() - 1));

            myImage.drawImage(lab.updateImage2(),600-lab.updateImage2().getWidth()/2,100);
        }
    }
    public void updateImage2(int num){
        num--;
        x1=0;
        maxY = 0;

        for(int i = 0; i < valueY.get(num).size(); i++){
            if(valueY.get(num).get(i)> maxY){
                maxY = valueY.get(num).get(i);
            }
        }
        for(int i = 0; i < valueY.get(num + 1).size(); i++){
            if(valueY.get(num + 1).get(i)> maxY){
                maxY = valueY.get(num + 1).get(i);
            }
        }

        if(maxY > 0){
            myImage.setColor(Color.BLUE);
            y1=(int) (myImage.getHeight()-((valueY.get(num).get(0) / maxY)*myImage.getHeight()));
            for(int i = 0; i< valueY.get(num).size(); i++){
                x2= i;
                y2= valueY.get(num).get(i);
                x=(int)(((x2 + 1) / valueY.get(num).size())*myImage.getWidth());
                y=myImage.getHeight()-(int)((y2 / maxY)*myImage.getHeight());
                myImage.drawLine(x1,y1,x,y);
                x1=x;
                y1=y;
            }

            myImage.setColor(Color.RED);
            y1=(int) (myImage.getHeight()-((valueY.get(num + 1).get(0) / maxY)*myImage.getHeight()));
            for(int i = 0; i< valueY.get(num + 1).size(); i++){
                x2= i;
                y2= valueY.get(num + 1).get(i);
                x=(int)(((x2 + 1) / valueY.get(num + 1).size())*myImage.getWidth());
                y=myImage.getHeight()-(int)((y2 / maxY)*myImage.getHeight());
                myImage.drawLine(x1,y1,x,y);
                x1=x;
                y1=y;
            }
        }

        lab.setFillColor(Color.BLUE);
        lab.setValue("Value: " + valueY.get(num).get(valueY.get(num).size() - 1));
        myImage.drawImage(lab.updateImage2(),600-lab.updateImage2().getWidth()/2,110);

        lab.setFillColor(Color.RED);
        lab.setValue("Value: " + valueY.get(num + 1).get(valueY.get(num + 1).size() - 1));
        myImage.drawImage(lab.updateImage2(),600-lab.updateImage2().getWidth()/2,140);
    }

    public void addValue(int num, double value){
        num--;

        valueY.get(num).add(value);
    }
}
