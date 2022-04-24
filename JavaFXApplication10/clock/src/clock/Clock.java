
package clock;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Clock extends Application {
    public static double anglem,angleh;
    public static int s,m,h; 
    @Override
    public void start(Stage primaryStage) {
        
        /////////////lables to print time /////////////////
        Label texts = new Label();
        texts.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Label textm = new Label();
        textm.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Label texth = new Label();
        texth.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Label LS = new Label("Second: ");
        LS.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Label LM = new Label("Minute: ");
        LM.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Label LH = new Label("Hour:   ");
        LH.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        ///////////////Border to pane and Grid/////////////
        BorderPane b = new BorderPane();
        /////////////make a scene and take its dimentions////////////////
        Scene scene = new Scene(b,500,500);
        ////////////draw Cricel in the center of the scene/////////
        Circle c = new Circle(scene.getWidth()/2,scene.getHeight()/2,150);
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLUE);
        c.setStrokeWidth(10);
        //////////// draw Hour line in the center of the scene///////////
        Line lh = new Line(scene.getWidth()/2,scene.getHeight()/2,340,340);
        lh.setStroke(Color.RED);
        lh.setStrokeWidth(5);
         //////////// draw minute line in the center of the scene///////////
        Line lm = new Line(scene.getWidth()/2,scene.getHeight()/2,150,250);
        lm.setStroke(Color.BROWN);
        lm.setStrokeWidth(5);
        //////////// draw second line in the center of the scene///////////
        Line ls = new Line(scene.getWidth()/2,scene.getHeight()/2,300,210);
        ls.setStroke(Color.GREEN);
        ls.setStrokeWidth(5);
        
        
        ////////////////////////////////////animation to hour line ////////////////////
        Rotate rotationh = new Rotate(angleh,lh.getStartX(),lh.getStartY());
//      rotationh.pivotXProperty().bind(lh.startXProperty());
//      rotationh.pivotYProperty().bind(lh.startYProperty());
        lh.getTransforms().add(rotationh);
      
//      Timeline timeh = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotationh.angleProperty(),0)),
//                          new KeyFrame(Duration.seconds(3600), new KeyValue(rotationh.angleProperty(),360)));
//      timeh.setCycleCount(Timeline.INDEFINITE);
//      timeh.play();
        
        ////////////////////////////////////animation to minutes line ////////////////////
         Rotate rotationm = new Rotate(anglem,lm.getStartX(),lm.getStartY());
//      rotationm.pivotXProperty().bind(lm.startXProperty());
//      rotationm.pivotYProperty().bind(lm.startYProperty());
         lm.getTransforms().add(rotationm);
      
//      Timeline timem = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotationm.angleProperty(),0)),
//                          new KeyFrame(Duration.seconds(60), new KeyValue(rotationm.angleProperty(),360)));
//      timem.setCycleCount(Timeline.INDEFINITE);
//      timem.play();
        
         //////////////animation to secondes line///////////////
      Rotate rotation = new Rotate(0,ls.getStartX(),ls.getStartY());
//      rotation.pivotXProperty().bind(ls.endXProperty());
//      rotation.pivotYProperty().bind(ls.startYProperty());
      ls.getTransforms().add(rotation);
      
      Timeline time = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(),0)),
                          new KeyFrame(Duration.seconds(60), new KeyValue(rotation.angleProperty(),360)),
                          new KeyFrame(Duration.ZERO,e->{
                            anglem+=6;
                            rotationm.setAngle(anglem);
                            angleh+=0.5;
                             rotationh.setAngle(angleh);
                          }));
      time.setCycleCount(Timeline.INDEFINITE);
      time.play();
      
             ///////////to print time on lables////////////
      Timeline t = new Timeline(new KeyFrame(Duration.seconds(1),e->{
           if(s>=59){
               s=0;
               texts.setText(" ");
           }
           else{
               s++;
               texts.setText(""+s);
           }
           if(m>=59){
               m=0;
               textm.setText(" ");
           }
           if(s==59){
               m++;
               textm.setText(""+m);}
           if(m==59){
               h++;
               texth.setText(""+h);
                          } 
      }));
      t.setCycleCount(Timeline.INDEFINITE);
      t.play();
      
       ///////////pane to /cricle and lines/////////////
        Pane p = new Pane ();
        p.getChildren().addAll(c,lh,lm,ls);
       ////////////put lables on /////Grid/////////////////////
        GridPane G = new GridPane();
        G.add(LS, 0, 0);
        G.add(texts,1, 0);
        G.add(LM, 0, 1);
        G.add(textm, 1, 1);
        G.add(LH, 0, 2);
        G.add(texth, 1, 2);
            
         /////Grid in top//////
        b.setTop(G);
        /////////pane in center////////
        b.setCenter(p);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Clock");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
