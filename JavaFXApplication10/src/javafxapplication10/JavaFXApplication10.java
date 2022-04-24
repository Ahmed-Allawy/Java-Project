
package javafxapplication10;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXApplication10 extends Application {
    //////////////directios of ball////////////
    public static double directionx=-1;
    public static double directiony=1;
    /////////////ball speed//////////
    public static double speed=2;
    public static int Pl1Score = 0;
    public static int Pl2Score = 0;
    @Override
    public void start(Stage primaryStage) {
        /////////////show score on a screen///////////////
        Label score = new Label(Pl1Score+"|"+Pl2Score);
        /////////////////////////////make border between two palyers(vertical line)//////////
        Line l = new Line(400/*screen width /2*/,0/*screen top*/,400/*screen width /2*/,500/*screen height*/);
        l.setFill(Color.BLUEVIOLET);
        ////******************/////////////////
        Pane pane = new Pane();
        //////////player1//////////
        Rectangle player1  = new Rectangle(10,250/*screen width /2*/,20/*player width*/,100/*palyerheight*/);
        player1.setFill(Color.BLUE);
        ///////////////moving palyer1 with mouse//////////
        pane.setOnMouseMoved(e->{
            //////////change Y postion of palyer with mouse Y position////
                player1.setY(e.getY());
                //////////منع الاعب من الاختفاء اسفل الشاشة مع حركة المواس لتحت/////
                if((player1.getY()/*player Y posiont*/+100/*player height*/)>500/*screen height*/)
                    player1.setY(400/*screen height - player height*/);
        });
        ///////////////////////////player2///////
        Rectangle player2  = new Rectangle(770/*screen width*/,200/*screen width /2 - player height*/,20/*player width*/,100/*palyerheight*/);
        player2.setFill(Color.RED);
       //////////////ball/////////////////
        Circle ball = new Circle(400/*screen width /2*/,250/*screen height/2*/,10/*ball radius*/);
        ball.setFill(Color.BLACK);
        /////////////////game loop///////////////
        Timeline time = new Timeline(new KeyFrame(Duration.millis(10),e->{
            //////////////////collition ball with up and down borders////////////////
            if(((ball.getCenterY()/*ball x position*/+10/*ball radius*/)>500/*screen height*/)
                              || ((ball.getCenterY()/*ball y position*/+10/*ball radius*/)<5/*screen top*/))
                directiony*=-1;
            
            //////////////random moving balll in two direction/////////////////
            ball.setCenterX(ball.getCenterX()+directionx*speed); /// change x position with speed //////
            ball.setCenterY(ball.getCenterY()+directiony*speed); /// change y position with speed //////
            
           ////////////////////moving player2 (AI)//////////////////
            if(directionx==1/*moving ball to player direction*/ && ball.getCenterX()>400//علشان الاعب يتحرك لما الكورة تدخل في المدي بتاعه
                    && ball.getCenterY()<(player2.getY()+50/*palyer height/2*/))//علشان اعرف الكورة جاية من فوق ولا من تحت 
                player2.setY(player2.getY()-speed);
            if(directionx==1/*moving ball to player direction*/ && ball.getCenterX()>400 //علشان الاعب يتحرك لما الكورة تدخل في المدي بتاعه
                    && ball.getCenterY()>(player2.getY()-50/*palyer height/2*/) ) //علشان اعرف الكورة جاية من فوق ولا من تحت 
                 player2.setY(player2.getY()+speed);
            ///////////return palyer to ints oreginal position ///////////
            if(player2.getY()>190/*علشان اعرف الاعل تحت ولا فوق الموضع الاصلي بتاعه*/ && 
                    directionx==-1/*ball move fare from player*/)
                player2.setY(player2.getY()-speed);/// move player up //////
            if(player2.getY()<190/*علشان اعرف الاعل تحت ولا فوق الموضع الاصلي بتاعه*/
                    && directionx==-1/*ball move fare from player*/)
                player2.setY(player2.getY()+speed);/// move palyer down  //////
            ///منع الاعب من الاختفاء اسفل الشاشة /////
             if((player2.getY()+100/*player height*/)>500/*screen height*/)
               player2.setY(400/*screen height - player height*/);
             ///////////////collition balll with player1////////////
            if((ball.getCenterX()-10)<(player1.getX()+20) && (ball.getCenterX()-10)>(player1.getX()) && 
               (ball.getCenterY()+10>player1.getY() && 
                ball.getCenterY()-10<(player1.getY()+100))) 
                directionx=1;
            
            ///////////////collition balll with player2////////////
             if((ball.getCenterX()+10)>(player2.getX()) && (ball.getCenterX()+10)<(player2.getX()+20) && 
               (ball.getCenterY()>player2.getY() && 
                ball.getCenterY()<(player2.getY()+100))) 
                directionx=-1;
             
             //////////////////score of player2/////////////
            if(ball.getCenterX()<0){
                Pl2Score +=1;
                score.setText(Pl1Score+"|"+Pl2Score);
                ball.setCenterX(400);
                ball.setCenterY(250);
                directionx=1;
            }
            //////////////////score of player1/////////////
            if(ball.getCenterX()>800){
                Pl1Score +=1;
                score.setText(Pl1Score+"|"+Pl2Score);
                ball.setCenterX(400);
                ball.setCenterY(250);
                directionx=-1;
            }
            
        
        }));
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
        //////////////put palyer1 , player1 and ball on a pane///////////
        pane.getChildren().addAll(player1,player2,ball,l);
        //////////////put score label and pane on border pane//////////
        BorderPane border = new BorderPane();
        border.setBottom(pane);
        border.setCenter(score);
        //////////////////put borderpane on a scene/////////////
        Scene scene = new Scene(border,800,500);
        primaryStage.setTitle("Ping Pong Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
