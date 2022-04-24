
package ballpuzzle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author أحمد علاوي
 */
public class BallPuzzle extends Application {
    public int tubesNumber[]={-1,-1};
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        tube tube[] = new tube[3];
        //generate tubes///
        for(int i=0;i<tube.length;i++){
            tube[i] = new tube(10+i*100,100);
            tube[i].setCapacity(3-i);
            tube[i].setID(i);
            root.getChildren().add(tube[i]);
        }
        //generate balls///
        ball b [] = new ball[6];
        for(int i=0;i<tube.length;i++){
            for(int j=0;j<tube[i].getCapacity();j++){
            b[j]= new ball(tube[i].getBallX(),tube[i].getBallY());
            b[j].setColor(j+1);
            b[j].color();
            tube[i].FillWithBall(b[j]);
            root.getChildren().add(b[j]);
            }
        }
        ////selected two tubes///
        root.setOnMouseClicked(e->{
             for(int i=0;i<tube.length;i++){
                 if(tube[i].collition(e.getX(),e.getY())){
                     tube[i].selected();
                     if(tubesNumber[0]==-1 && tubesNumber[1]==-1){
                         tube[i].animateUp();
                         tubesNumber[0]=tube[i].getID();
                     }
                     else tubesNumber[1]=tube[i].getID();
                 }
             }
             
        });
        Timeline time = new Timeline(new KeyFrame(Duration.millis(800),e->{
            if(tubesNumber[0]!=-1 && tubesNumber[1]!=-1 && tubesNumber[0]!=tubesNumber[1]){
                tube[tubesNumber[0]].removeBall(tube[tubesNumber[1]]);
                tube[tubesNumber[0]].unSelected();
                //tube[tubesNumber[0]].animateDown();
                tube[tubesNumber[1]].unSelected();
                tubesNumber[0]=-1;
                tubesNumber[1]=-1;
            }
            else if(tubesNumber[0]==tubesNumber[1] && tubesNumber[0]!=-1){
                tube[tubesNumber[0]].unSelected();
                tube[tubesNumber[0]].animateDown();
                tubesNumber[0]=-1;
                tubesNumber[1]=-1;
            }
        }));
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
