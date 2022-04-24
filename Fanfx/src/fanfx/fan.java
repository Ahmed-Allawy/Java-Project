
package fanfx;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

public class fan extends Pane {
    private double x=100,y=100;
    private double radius = 50;
    private double angle=5;
    Timeline time;
    public fan(int n){
        Arc [] A = new Arc[n];
        for(int i=0;i<A.length;i++){
            A[i] = new Arc(x,y,radius,radius,(360/A.length*i),200/4);
            A[i].setType(ArcType.ROUND);
            getChildren().add(A[i]);
        }
        time = new Timeline(new KeyFrame(Duration.millis(40),e->{
            for(int k=0;k<A.length;k++){
                A[k].setStartAngle(A[k].getStartAngle()+angle);
            }
        }));
        time.setCycleCount(Timeline.INDEFINITE);
    }
    private void setx(double x){this.x = x;}
    public void sety(double y){this.y = y;}
    public void setradius(double radius){this.radius = radius;}
    private double getAngle(){return this.angle;}
    public void revarse(){angle*=-1;}
    public void Play(){time.play();}
    public void Pause(){time.pause();}
}
