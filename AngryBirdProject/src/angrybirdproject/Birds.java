
package angrybirdproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;


public class Birds extends Pane{
    Sprite Bird ;
    Line rope1,rope2 ;
    double startPosX=100,startPosY=270,dx=0.1,dy=0.1,posX=0,posY=0;
    double velocity=0, distance=0, angle=0, gravity=9.8;
    boolean state=false;
    public Birds(int d){
       draw(d);
       drageBird();
       animation();
    }
    private void draw(int d){
        Bird = new Sprite("redbird.png");
        Bird.setWidth(40.0);
        Bird.setHeight(40.0);
        Bird.setPositionX(50+startPosX+d);
        Bird.setPositionY(startPosY+130);
        drawRope();
        getChildren().addAll(rope1,Bird,rope2);
    }
   private void drageBird(){
        Bird.setOnMouseDragged(e->{
            if(Bird.getPositionX()<30)
                Bird.setPositionX(30);
            else if(Bird.getPositionX()>startPosX)
                 Bird.setPositionX(startPosX);
            else
                Bird.setPositionX(Bird.getPositionX()+e.getX());
            if(Bird.getPositionY()<230)
                Bird.setPositionY(230);
            else if(Bird.getPositionY()>370)
                  Bird.setPositionY(370);
            else
               Bird.setPositionY(Bird.getPositionY()+e.getY());
            
            distance=Math.pow(startPosX-Bird.getPositionX(), 2)+Math.pow(Bird.getPositionY()-startPosY, 2);
            velocity=0.5*Math.pow(distance, 0.5);
            angle=(Math.atan((Bird.getPositionY()-startPosY)/(startPosX-Bird.getPositionX())));
            dx=velocity*Math.cos(angle);
            dx=Math.floor(dx);
            dy=velocity*Math.sin(angle);
            dy=Math.floor(dy);
            rope1.setEndX(Bird.getPositionX());
            rope1.setEndY(Bird.getPositionY()+40);
            rope2.setEndX(Bird.getPositionX());
            rope2.setEndY(Bird.getPositionY()+40);
        });
    }
    private void animation(){
        Timeline animate = new Timeline(new KeyFrame(Duration.millis(90),e->{
            Bird.setPositionX(Bird.getPositionX()+dx);
            Bird.setPositionY(Bird.getPositionY()-dy);
            dy-=gravity;
            //check collisions
            collisionWithScene();
            posX=Bird.getPositionX();
            posY=Bird.getPositionY();
            if(rope1.getEndX()<startPosX){
                rope1.setEndX(rope1.getEndX()+dx);
                rope1.setEndY(rope1.getEndY()-dy);
                rope2.setEndX(rope2.getEndX()+dx);
                rope2.setEndY(rope2.getEndY()-dy);
            }
        }));
        animate.setCycleCount(Timeline.INDEFINITE);
        
        Bird.setOnMouseReleased(e->{
            animate.play();
        });
    }
    private void collisionWithScene(){
         if(Bird.getPositionX()<20 || Bird.getPositionX()>750)
             dx*=-1; //bouncing
         if(Bird.getPositionY()>400 && dy<0){
             dy*=-1;  //bouncing
             dy=0.5*dy; // as lose power
             dx=0.5*dx; //as friction
             if(dy<=9.8) dy=0;
             if(dx<2) dx=0;
             if(dx==0&&dy==0)state=true;
           }
    }
    public void movetoCatapult(){
        Bird.setPositionX(startPosX);
        Bird.setPositionY(startPosY);
    }
    public boolean destroy(){
        return state;
    }
    private void drawRope(){
        rope1=new Line();
        rope1.setStartX(140);
        rope1.setStartY(290);
        rope1.setStrokeWidth(15);
        rope1.setEndX(140);
        rope1.setEndY(290);
        rope1.setFill(Color.BROWN);
        rope2=new Line();
        rope2.setStartX(100);
        rope2.setStartY(290);
        rope2.setStrokeWidth(15);
        rope2.setEndX(100);
        rope2.setEndY(290);
        rope2.setFill(Color.BROWN);
    }
}
