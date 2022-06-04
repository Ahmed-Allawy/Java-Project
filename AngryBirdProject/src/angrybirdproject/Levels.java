
package angrybirdproject;

import java.util.Stack;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class Levels extends Pane{
    Sprite background;
    Sprite catapult_left,catapult_right;
    double width=800,height=500;
    int numberOfBirds=3;
    Stack<Birds> birds= new Stack<>();
    Objects enemy ;
    Objects wood_v,wood_h;
    boolean state1=false,state2=false,state3=false;
    Button restart=new Button("Restart");
    public Levels(){
        draw();
    }
    private void draw(){
        background=new Sprite("level.jpg");
        background.setWidth(width);
        background.setHeight(height);
        catapult_left=new Sprite("catapult_left.png");
        catapult_right=new Sprite("catapult_right.png");
        catapultPosition();
        enemy=new Objects("pig.png");
        enemy.object.setWidth(40);
        enemy.object.setHeight(40);
        enemy.ground=318;
        wood_v=new Objects("wood_v.png");
        wood_h=new Objects("wood_h.png");
        drawWood();
        getChildren().addAll(background,catapult_right,wood_h,wood_v,enemy);
        for(int i=0;i<numberOfBirds;i++)
            birds.push(new Birds(i*50));
        birds.peek().movetoCatapult();
        for(int i=0;i<numberOfBirds;i++)
           getChildren().add(birds.elementAt(i));
        getChildren().addAll(catapult_left,restart);
    }
    public void destroyBird(){
        if(!birds.isEmpty()&&birds.peek().destroy()){
               getChildren().remove(birds.peek());
               birds.pop();
               if(!birds.isEmpty())birds.peek().movetoCatapult();
            }
    }
    public void collisionWithEnemy(){
        if(!birds.isEmpty())
            if(Math.pow(Math.pow(birds.peek().posX-enemy.posX, 2)+Math.pow(birds.peek().posY-enemy.posY, 2),0.5)<41){
                enemy.dx=birds.peek().dx;
                enemy.ground=430;
                state1=true;
            }
         if(state3==true||state2==true) enemy.ground=430;
    }
    public void collisionWithWood(){
        if(!birds.isEmpty())
            if(Math.pow(Math.pow(birds.peek().posX-wood_v.posX, 2)+Math.pow(birds.peek().posY-wood_v.posY, 2),0.5)<40){
                wood_v.dx=birds.peek().dx;
                state2=true;
            }
        if(state3==true) wood_v.ground=430;
         if(!birds.isEmpty())
            if(Math.pow(Math.pow(birds.peek().posX-wood_h.posX, 2)+Math.pow(birds.peek().posY-wood_h.posY, 2),0.5)<50){
                wood_h.dx=birds.peek().dx;
                wood_h.ground=430;
                state3=true;
            }
    }
    private void catapultPosition(){
        catapult_left.setPositionX(75);
        catapult_left.setPositionY(240);
        catapult_right.setPositionX(76);
        catapult_right.setPositionY(240);
    }
    public void drawWood(){
        wood_v.object.setWidth(70);
        wood_v.object.setHeight(100);
        wood_h.object.setWidth(83);
        wood_h.object.setHeight(20);
        wood_h.object.setPositionX(450);
        wood_h.ground=329;
    }
    public void destroyObjects(){
        if(state1==true && enemy.dx==0 && enemy.dy==0)
            getChildren().remove(enemy);
        if(state2==true && wood_h.dx==0 && wood_h.dy==0)
            getChildren().remove(wood_h);
        if(state3==true && wood_v.dx==0 && wood_v.dy==0)
            getChildren().remove(wood_v);
    }
}
