
package angrybirdproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class AngryBirdProject extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Menu menu=new Menu();
        Levels level1= new Levels();
        root.getChildren().add(menu);
        menu.startButton(root,level1);
        menu.exiteButton(primaryStage);
         Timeline loop=new Timeline(new KeyFrame(Duration.millis(90),e->{
             level1.destroyBird();
             level1.collisionWithEnemy();
             level1.collisionWithWood();
             level1.destroyObjects();
        }));
         loop.setCycleCount(Timeline.INDEFINITE);
         loop.play();
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("Angry Birds");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
