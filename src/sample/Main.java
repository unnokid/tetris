package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.game.Game;



public class Main extends Application {
    public static Main main;
    public Game game =null;
    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            main = this;
            FXMLLoader loader =new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Main.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);

            scene.addEventFilter(KeyEvent.KEY_PRESSED, e->{
                if(game != null){
                    game.keyHandler(e);
                }
            });

            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
