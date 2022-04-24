
package fanfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Fanfx extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        ///////////many fan///////////
        HBox h1 = new HBox();
        fan Fan[] = new fan[4];
        for(int i=0;i<Fan.length;i++){
              Fan[i] = new fan(5);
              h1.getChildren().add(Fan[i]);
        }
        /////////////one fan/////////////
        fan f = new fan(4);
       
        ///////////////control buttoms////////////
        Button Play = new Button("Play");
        Button Pause = new Button("Pause");
        Button Revarse = new Button("Revarse");
        ////////////////set buttoms on HBox/////////////
        HBox h = new HBox();
        h.getChildren().addAll(Play,Pause,Revarse);
        /////////// set Fane center of borderpane and HBox bottom of borderpane/////
        BorderPane border = new BorderPane();
        border.setTop(f);
        border.setCenter(h1);
        border.setBottom(h);
        
        ///////////do actions/////////////
        Play.setOnAction(e->{
            ///////////action for one fan///////////
            f.Play();
            //////action for many fan///////
            for(int k=0;k<Fan.length;k++){
                Fan[k].Play();
            }
        });
        Pause.setOnAction(e->{
            ///////////action for one fan///////////
            f.Pause();
             //////action for many fan///////
            for(int k=0;k<Fan.length;k++){
                Fan[k].Pause();
            }
        });
        Revarse.setOnAction(e->{
            ///////////action for one fan///////////
            f.revarse();
             //////action for many fan///////
            for(int k=0;k<Fan.length;k++){
                Fan[k].revarse();
            }
        });
        
                
        
        Scene scene = new Scene(border,200,200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FAN");
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
