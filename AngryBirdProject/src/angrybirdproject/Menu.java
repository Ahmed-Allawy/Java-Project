
package angrybirdproject;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Pane{
    Sprite background;
    Button start,exite;
    double width=800,height=500;
    VBox container = new VBox(10);
    public Menu(){
        draw();
    }
    private void draw(){
        background=new Sprite("background1.jpg");
        background.setWidth(width);
        background.setHeight(height);
        start=new Button("START");
        exite=new Button(" EXITE ");
        container.getChildren().addAll(start,exite);
        container.setTranslateX(width/2);
        container.setTranslateY(height/2);
        getChildren().addAll(background,container);
    }
    public void startButton(StackPane scene,Pane level){
        start.setOnAction(e->{
            scene.getChildren().remove(0);
            scene.getChildren().add(level);
        });
    }
   public void exiteButton(Stage primaryStage){
        exite.setOnAction(e->{primaryStage.close();});
    }
}
