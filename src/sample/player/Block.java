package sample.player;

import java.awt.*;

public class Block {

    private Color color;
    private boolean fill;
    private double x;
    private double y;
    private double size;
    private double borderSize;

    public Block(double x, double y,double size){
        color = Color.WHITE;
        fill= false;
        this.x = x;
        this.y = y;
        this.size= size;
        this.borderSize = 2;
    }
}
