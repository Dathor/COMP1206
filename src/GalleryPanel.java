import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GalleryPanel extends JPanel {
    ArrayList<Image> images = new ArrayList<>();


    public void addImage(Image image){
        images.add(image);
    }

    private void drawImages(Graphics2D graphics2D){
        for(int i = 0; i < images.size(); i++){
            //graphics2D.drawImage(images.get(i), null,0 + (i * 20), 0);
            graphics2D.drawImage(images.get(i), 0 + (i*20), 0, null);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        drawImages(graphics2D);
    }
}
