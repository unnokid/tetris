package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.game.Game;
import javafx.scene.input.KeyEvent;


public class Main extends Application {
    public static Main main;
    public Game game =null;
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            main = this; //스태틱 변수에 자기자신을 넣어서 싱글톤으로 활용함.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Main.fxml"));
            AnchorPane anchorPane = (AnchorPane)loader.load();

            Scene scene = new Scene(anchorPane);

            scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                if(game != null)
                    game.keyHandler(e); //게임의 이벤트 핸들러로 넘겨준다.
            });
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
