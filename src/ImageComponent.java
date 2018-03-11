import javax.swing.*;
import java.awt.*;


public class ImageComponent extends JComponent {

    private Image img;

    public ImageComponent(Image img){
        this.img = img;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    public Dimension getPreferredSize() {
        return new Dimension(img.getWidth(null), img.getHeight(null));
    }


}
