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


        mainLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update( (now-before) / 1000000000d);
                before = now;
                render();
            }
        };
        before = System.nanoTime();
        player = new Player(board);
        mainLoop.start();
    }


    public void update(double delta){
        //블럭 자동하강
        blockDownTime +=delta;
        if(blockDownTime >=0.5){
            player.down();
            blockDownTime =0;
        }
    }

    public void checkLineStatus(){
        for (int i = 19; i >=0 ; i--) {
            boolean clear = true;
            for (int j = 0; j < 10; j++) {
                if(!board[i][j].getFill()){
                    clear = false;
                    break;
                }
            }
            if(clear){
                score++;
                for (int j = 0; j <10 ; j++) {
                    board[i][j].setData(false,Color.WHITE);
                }

                for (int k = i-1; k >=0 ; k--) {
                    for (int j = 0; j < 10; j++) {
                        board[k+1][j].copyData(board[k][j]);
                    }
                }

                for (int j = 0; j < 10; j++) {
                    board[0][j].setData(false,Color.WHITE);
                }
                i++;
            }
        }

    }

    public void render(){
        gc.clearRect(0,0, width,height);
        gc.setStroke(Color.rgb(0,0,0));
        gc.setLineWidth(4);
        gc.strokeRect(0,0,width,height);

        for (int i = 0; i <20 ; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j].render(gc);
            }
        }
    }

    public void keyHandler(KeyEvent e) {
        player.keyHandler(e); //키보드 핸들링을 담당하는 매서드
    }




}
