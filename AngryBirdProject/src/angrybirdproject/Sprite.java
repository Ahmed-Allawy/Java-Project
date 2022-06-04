
package angrybirdproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView{
    String imagepath;
    Image img;
    public Sprite(String imagepath){
        this.imagepath=imagepath;
        img=new Image(imagepath);
        setImage(img);
    }
    public double getPositionX(){return super.getTranslateX();}
    public double getPositionY(){return super.getTranslateY();}
    public void setPositionX(double x){super.setTranslateX(x);}
    public void setPositionY(double y){super.setTranslateY(y);}
    public double getWidth(){return super.getFitWidth();}
    public double getHeight(){return super.getFitHeight();}
    public void setWidth(double w){super.setFitWidth(w);}
    public void setHeight(double h){super.setFitHeight(h);}
}
