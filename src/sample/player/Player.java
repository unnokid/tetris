package sample.player;

import java.awt.*;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;


import java.util.Random;

public class Player {
    private Point2D[][][] shape = new Point2D[7][][];
    private int currnet =0;
    private int rotate =0;
    private int nowColor=0;

    private Color[] Colorset = new Color[7];

    private Random random;

    private int x = 5;
    private int y = 2;

    private Block[][] board;

    public Player(Block[][] board){
        this.board = board;
        
        //테트리스 모양 생성

        //색지정
        Colorset[0] = Color.GRAY;
        Colorset[1] = Color.BLUE;
        Colorset[2] = Color.CYAN;
        Colorset[3] = Color.YELLOW;
        Colorset[4] = Color.GREEN;
        Colorset[5] = Color.ORANGE;
        Colorset[6] = Color.PINK;

        random = new Random();
        currnet = random.nextInt(shape.length);
        nowColor = random.nextInt(Colorset.length);

        draw(false);

    }

    private void draw(boolean remove){

    }

    public Point2D[] getPointArray(String pointStr){
        Point2D[] arr = new Point2D[4];
        String[] pointList = pointStr.split(":");
        for (int i = 0; i < pointList.length; i++) {
            String[] point = pointList[i].split(",");
            double x = Double.parseDouble(point[0]);
            double y = Double.parseDouble(point[1]);
            arr[i] = new Point2D(x, y);
        }
        return arr;
    }

    public void keyHandler(KeyEvent e){

    }

    private void move(int dx, int dy, boolean rot){

    }

    public boolean down(){

        return false;
    }

    private void getNextBlock(){
        currnet = random.nextInt(shape.length);
        nowColor = random.nextInt(Colorset.length);
        x=5;
        y=2;
        rotate =0;
    }
    private boolean checkPossible(){

        return true;
    }
}
