import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class GalleryPanel extends JPanel {
    ArrayList<ImageComponent> images = new ArrayList<>();
    JPanel imagesPanel = new JPanel();

    public GalleryPanel(){
        setLayout(new BorderLayout());
        imagesPanel.setLayout(new FlowLayout());
        add(imagesPanel, BorderLayout.CENTER);
    }

    public boolean addImage(Image image){
        if(images.size() == 12){
            return false;
        }
        ImageComponent img = new ImageComponent(image);
        img.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for(ImageComponent current: images){
                    current.setBorder(null);
                }
                img.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.cyan));
            }
        });
        images.add(img);
        imagesPanel.add(img);
        imagesPanel.revalidate();
        return true;
    }


    public void removeImage(ImageComponent image){
        if(image != null) {
            getGraphics().clearRect(image.getX(), image.getY(), image.getWidth(), image.getHeight());
            imagesPanel.remove(image);
            images.remove(image);
            imagesPanel.revalidate();
        }
    }


    public Iterator<ImageComponent> getImageIterator(){
        return images.iterator();
    }
}
