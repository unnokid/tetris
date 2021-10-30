package sample.player;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import sample.Main;




public class Player {
    private Point2D[][][] shape = new Point2D[7][][];
    private int current =0;
    private int rotate =0;
    private int nowColor=0;

    private Color[] colorSet = new Color[7];

    private Random random;

    private int x = 5;
    private int y = 2;

    private Block[][] board;

    public Player(Block[][] board){
        this.board = board;
        
        //테트리스 모양 생성
        shape[0] = new Point2D[2][];
        shape[0][0] = getPointArray("0,-1:0,0:0,1:0,2");
        shape[0][1] = getPointArray("-1,0:0,0:1,0:2,0");

        shape[1] = new Point2D[1][];
        shape[1][0] = getPointArray("0,0:1,0:0,1:1,1");

        shape[2] = new Point2D[4][];
        shape[2][0] = getPointArray("0,-2:0,-1:0,0:1,0");
        shape[2][1] = getPointArray("0,1:0,0:1,0:2,0");
        shape[2][2] = getPointArray("-1,0:0,0:0,1:0,2");
        shape[2][3] = getPointArray("-2,0:-1,0:0,0:0,-1");

        shape[3] = new Point2D[4][];
        shape[3][0] = getPointArray("0,-2:0,-1:0,0:-1,0");
        shape[3][1] = getPointArray("0,-1:0,0:1,0:2,0");
        shape[3][2] = getPointArray("0,0:1,0:0,1:0,2");
        shape[3][3] = getPointArray("-2,0:-1,0:0,0:0,1");

        shape[4] = new Point2D[2][];
        shape[4][0] = getPointArray("0,0:0,-1:1,-1:-1,0");
        shape[4][1] = getPointArray("0,0:0,-1:1,0:1,1");

        shape[5] = new Point2D[2][];
        shape[5][0] = getPointArray("0,0:0,-1:-1,-1:1,0");
        shape[5][1] = getPointArray("0,0:0,-1:-1,0:-1,1");

        shape[6] = new Point2D[4][];
        shape[6][0] = getPointArray("0,0:0,-1:1,0:-1,0");
        shape[6][1] = getPointArray("0,0:0,1:1,0:0,-1");
        shape[6][2] = getPointArray("0,0:0,1:1,0:-1,0");
        shape[6][3] = getPointArray("0,0:0,1:-1,0:0,-1");
        //색지정
        colorSet[0] = Color.GRAY;
        colorSet[1] = Color.BLUE;
        colorSet[2] = Color.CYAN;
        colorSet[3] = Color.YELLOW;
        colorSet[4] = Color.GREEN;
        colorSet[5] = Color.ORANGE;
        colorSet[6] = Color.PINK;

        random = new Random();
        current = random.nextInt(shape.length);
        nowColor = random.nextInt(colorSet.length);

        draw(false);

    }

    private void draw(boolean remove) {
        //블럭을 판에서 표시해주거나 없애주는 매서드
        for(int i = 0; i < shape[current][rotate].length; i++) {
            int bx = (int)shape[current][rotate][i].getX() + x;
            int by = (int)shape[current][rotate][i].getY() + y;
            board[by][bx].setData(!remove, colorSet[nowColor]); //제거나 색칠이냐
        }
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
        int dx = 0;
        int dy = 0;
        boolean rot = false;//회전
        if(e.getCode() == KeyCode.LEFT){
            dx-=1;
        }
        else if(e.getCode() == KeyCode.RIGHT){
            dx+=1;
        }
        else if(e.getCode() == KeyCode.UP){
            rot = true;
        }

        move(dx,dy,rot);

        if(e.getCode() == KeyCode.DOWN){
            down();
        }else if(e.getCode() == KeyCode.SPACE){
            while(!down()){

            }
        }
    }

    private void move(int dx, int dy, boolean rot){
        draw(true);
        x +=dx;
        y +=dy;
        if(rot){
            rotate = (rotate +1)% shape[current].length;
        }
        if(!checkPossible()){
            x -=dx;
            y -=dy;
            if(rot){
                rotate = rotate -1 <0 ? shape[current].length -1: rotate -1;
            }
        }
        draw(false);
    }

    public boolean down(){
        draw(true);
        y +=1;
        if(!checkPossible()){
            y -=1;
            draw(false);
            Main.main.game.checkLineStatus();
            getNextBlock();
            draw(false);
            return true;
        }
        draw(false);
        return false;
    }

    private void getNextBlock(){
        current = random.nextInt(shape.length);
        nowColor = random.nextInt(colorSet.length);
        x=5;
        y=2;
        rotate =0;
    }
    private boolean checkPossible(){
        for (int i = 0; i < shape[current][rotate].length; i++) {
            int bx = (int) shape[current][rotate][i].getX()+x;
            int by = (int) shape[current][rotate][i].getY()+y;
            if(bx <0 || by <0 || bx>=10 || by>=20){
                return false;
            }
            if(board[by][bx].getFill()){
                return false;
            }
        }
        return true;
    }
}
