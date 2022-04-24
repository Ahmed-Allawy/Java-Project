
package ballpuzzle;

import java.util.Stack;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class ball extends Pane{
    private int x,y,radius=23;
    private Circle c;
    private int color;
    public ball(int x, int y){
        this.x=x;
        this.y=y;
        fill();
        
    }
    public void setColor(int color){this.color=color;}
    public int getColor(){return this.color;}
    private void fill(){
            c = new Circle(this.x,this.y,this.radius);
            getChildren().add(c);    
    }
    public void move(int x,int y){
        this.x=x;
        this.y=y;
        c.setCenterX(this.x);
        c.setCenterY(this.y);
    }
    public void color(){
        if(this.color==0)
            c.setFill(Color.RED);
        //else if(this.color==1)
           // c.setFill(Color.BLUE);
        else c.setFill(Color.BLUEVIOLET);
    }
}
