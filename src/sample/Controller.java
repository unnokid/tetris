package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import sample.game.Game;

public class Controller {
    @FXML
    private Canvas gameCanvas;

    @FXML
    public void intialize(){
        System.out.println("메인 레이아웃 초기화");
        Main.main.game = new Game(gameCanvas);

    }
}
