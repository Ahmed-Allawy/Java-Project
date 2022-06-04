
package angrybirdproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Objects extends Pane{
    Sprite object;
    double startPosX=450,startPosY=0,dx=1,dy=1,posX=0,posY=0;
    double velocity=0, distance=0,gravity=9.8,ground=430;
    boolean state=false;
    String path;
    public Objects(String path){
        this.path=path;
        draw();
        animation();
    }
    private void draw(){
        object=new Sprite(path);
        object.setPositionX(startPosX);
        object.setPositionY(startPosY);
        getChildren().add(object);
    }
    private void animation(){
        Timeline animate = new Timeline(new KeyFrame(Duration.millis(90),e->{
            object.setPositionX(object.getPositionX()+dx);
            object.setPositionY(object.getPositionY()-dy);
            dy-=gravity;
            //check collisions
            collisionWithScene();
            posX=object.getPositionX();
            posY=object.getPositionY();
        }));
        animate.setCycleCount(Timeline.INDEFINITE);
        animate.play();
    }
    private void collisionWithScene(){
         if(object.getPositionX()<20 || object.getPositionX()>750)
             dx*=-1; //bouncing
         if(object.getPositionY()+object.getHeight()>ground && dy<0){
             dy*=-1;  //bouncing
             dy=0.5*dy; // as lose power
             dx=0.5*dx; //as friction
             if(dy<=9.8) dy=0;
             if(dx<2) dx=0;
           }
    }
}
