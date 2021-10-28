package sample.game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import sample.player.Block;
import sample.player.Player;


public class Game {
    private GraphicsContext gc;
    public Block[][] board;

    private double width;
    private double height;

    private AnimationTimer mainLoop;
    private long before;

    private Player player;
    private double blockDownTime =0;

    private int score =0;

    public Game(Canvas canvas){
        width = canvas.getWidth();
        height = canvas.getHeight();

        double size = (width -4) /10;

        board = new Block[20][10];

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = new Block(j*size+2,i*size+2,size);
            }
        }
        this.gc = canvas.getGraphicsContext2D();
    }


    public void update(double delta){
        //블럭 자동하강
    }

    public void checkLineStatus(){

    }

    public void render(){

    }

    public void keyHandler(KeyEvent e) {
        player.keyHandler(e); //키보드 핸들링을 담당하는 매서드
    }




}
