import java.awt.*;

public class Point {

    private int x;
    private int y;
    private Color color;
    private int size;
    private boolean reflect;

    public Point(int x, int y, Color color, int size, boolean reflect){
        this.x = x;
        this.y = y;
        this.color = color;
        this.size = size;
        this.reflect = reflect;
    }

    public Point(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setReflect(boolean reflect) {
        this.reflect = reflect;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public boolean getReflect() {
        return reflect;
    }

    public boolean isOverlapping(Point point){
        double distance = Math.sqrt(Math.pow((this.x - point.x), 2) + Math.pow((this.y - point.y), 2));
        return distance < (this.size + point.size) / 2;
    }
}
