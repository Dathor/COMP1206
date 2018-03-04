import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

public class DoilyPanel extends JPanel {

    private int lines = 12;
    private boolean showLines = true;
    private ArrayList<Point> points = new ArrayList<>();
    private Color currentColor;
    private int currentSize = 20;
    private boolean reflect = false;
    private boolean eraser = false;

    public DoilyPanel() {
        this.setBackground(Color.BLACK);
        this.addMouseListener(new MouseDrawingListener());
        this.addMouseMotionListener(new MouseDrawingListener());
        this.currentColor = Color.RED;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public void setShowLines(boolean showLines) {
        this.showLines = showLines;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public void setReflect(boolean reflect) {
        this.reflect = reflect;
    }

    public void setEraser(boolean eraser) {
        this.eraser = eraser;
    }

    public boolean getReflect() {
        return this.reflect;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public boolean getEraser() {
        return eraser;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        drawPoints(graphics2D);

        if (showLines) {
            drawLines(graphics2D);
        }

    }

    private void drawLines(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        int centerWidth = this.getWidth() / 2;
        int centerHeight = this.getHeight() / 2;
        double theta = 360.0 / lines;

        for (int i = 0; i < lines; i++) {
            graphics2D.rotate(Math.toRadians(theta), centerWidth, centerHeight);
            graphics2D.drawLine(centerWidth, centerHeight, -300, centerHeight);
        }
    }

    private void drawPoints(Graphics2D graphics2D) {
        graphics2D.setColor(Color.RED);
        int centerWidth = this.getWidth() / 2;
        int centerHeight = this.getHeight() / 2;
        double theta = 360.0 / lines;
        for (Point point : points) {
            for (int i = 0; i < lines; i++) {
                graphics2D.rotate(Math.toRadians(theta), centerWidth, centerHeight);
                graphics2D.fillOval(point.getX() - (point.getSize() / 2), point.getY() - (point.getSize() / 2), point.getSize(), point.getSize());
                if(point.getReflect()) {
                    graphics2D.fillOval(point.getX() - (point.getSize() / 2), this.getHeight() - point.getY() - (point.getSize() / 2), point.getSize(), point.getSize());
                }
            }

        }
    }

        class MouseDrawingListener extends MouseAdapter {
            public void mouseClicked(MouseEvent e) {
                if(!eraser) {
                    points.add(new Point(e.getX(), e.getY(), currentColor, currentSize, reflect));
                } if(eraser && !points.isEmpty()){
                    Iterator<Point> it = points.iterator();
                    Point erased = new Point(e.getX(), e.getY(), currentSize);
                    while(it.hasNext()){
                        Point p = it.next();
                        if(p.isOverlapping(erased)){
                            it.remove();
                        }
                    }
                }
                repaint();
            }

            public void mouseDragged(MouseEvent e) {
                if(!eraser) {
                    points.add(new Point(e.getX(), e.getY(), currentColor, currentSize, reflect));
                } if(eraser && !points.isEmpty()){
                    Iterator<Point> it = points.iterator();
                    Point erased = new Point(e.getX(), e.getY(), currentSize);
                    while(it.hasNext()){
                        Point p = it.next();
                        if(p.isOverlapping(erased)){
                            it.remove();
                        }
                    }
                }
                repaint();
            }
        }
}

