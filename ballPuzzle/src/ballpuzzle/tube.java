
package ballpuzzle;

import java.util.Stack;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;


public class tube extends Pane{
    private int x,y,width=50,height=150;
    private int ballX,ballY;
    private int capacity=0;
    private int ID=0;
    private Polyline a = new Polyline();
    private Stack<ball> b = new Stack<>();
    public tube(int x,int y){
        this.x=x;
        this.y=y;
        ballX=this.x+25;
        ballY=this.y+this.height-25;
        shape();}
    private void shape(){
        a.getPoints().add(this.width*1.0+this.x);
        a.getPoints().add(0.0+this.y);
        ////////
        a.getPoints().add(this.width*1.0+this.x);
        a.getPoints().add(this.height*1.0+this.y);
        //////
        a.getPoints().add(0.0+this.x);
        a.getPoints().add(this.height*1.0+this.y);
        ///////
        a.getPoints().add(0.0+this.x);
        a.getPoints().add(0.0+this.y);
        a.setFill(Color.WHITE);
        a.setStroke(Color.BLUE);
        getChildren().add(a);
    }
    public int getBallX(){return this.ballX;}
    public int getBallY(){return this.ballY;}
    public void setID(int id){ID=id;}
    public int getID(){return this.ID;}
    public void setCapacity(int capacity){this.capacity=capacity;}
    public int getCapacity(){return this.capacity;}
    public void FillWithBall(ball ball){
        this.ballY-=46;
        b.add(ball);
    }
    public boolean collition(double x,double y){
        if( x>this.x && x<(this.x+this.width)&& y>this.y && y<(this.y+this.height)){
            return true;
        }
        else {
            return false;
        }
    }
    public void selected(){
        this.a.setStrokeWidth(3);
    }
    public void unSelected(){
        this.a.setStrokeWidth(1);
    }
    public void animateUp(){
        b.peek().move(this.ballX,this.y-this.height+125);
    }
    public void animateDown(){
        b.peek().move(this.ballX,this.ballY+46);
    }
    public void removeBall(tube tube){
        if(this.b.size()!=0 && tube.b.size()!=3 && this.getBallColor()==tube.getBallColor()){
            tube.addBall(this.b.peek());
            b.pop();
            this.ballY+=46;
        }
        else animateDown();
    }
    public void addBall(ball ball){
        this.ballY-=46;
        ball.move(this.ballX,this.ballY+46);
        b.add(ball);
    }
    public int getBallColor(){
        System.out.println(b.peek().getColor());
        return b.peek().getColor();}
}
