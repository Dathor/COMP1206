import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

public class DoilyFrame extends JFrame {

    private JPanel panel = new JPanel();
    private JPanel settingsPanel = new JPanel();
    private DoilyPanel doilyPanel = new DoilyPanel();
    private Color currentColor = Color.RED;
    private GalleryPanel galleryPanel = new GalleryPanel();

    public void init(){
        this.setContentPane(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,1000);

        panel.setLayout(new BorderLayout());

        JButton clear = new JButton("Clear");
        JButton undo = new JButton("Undo");
        JButton redo = new JButton("Redo");
        JCheckBox reflect = new JCheckBox("Reflect");
        JCheckBox showLines = new JCheckBox("Hide sector lines");
        JCheckBox eraser = new JCheckBox("Erase");
        JButton save = new JButton("Save");
        JSpinner size = new JSpinner(new SpinnerNumberModel(20,10,100,10));
        JButton color = new JButton();
        JLabel sizeLabel = new JLabel("Pen size:");
        JSpinner sectors = new JSpinner(new SpinnerNumberModel(12, 2, 36, 1));
        JLabel sectorsLabel = new JLabel("Sectors:");

        sizeLabel.setLabelFor(size);
        sectorsLabel.setLabelFor(sectors);
        color.setIcon(setButtonIcon(Color.RED));
        galleryPanel.setPreferredSize(new Dimension(150, 800));

        showLines.addItemListener(new ShowLinesListener());

        size.addChangeListener(e -> doilyPanel.setCurrentSize((int)size.getValue()));

        clear.addActionListener((e) -> {
            doilyPanel.getPoints().clear();
            repaint();
        });

        reflect.addItemListener((e) -> {
            doilyPanel.setReflect(!doilyPanel.getReflect());
            repaint();
        });

        eraser.addItemListener((e) -> {
            doilyPanel.setEraser(!doilyPanel.getEraser());
            repaint();
        });


        color.addActionListener((e) -> {
            Color c = JColorChooser.showDialog(null, "Choose a color", currentColor);
            if(c != null){
                color.setIcon(setButtonIcon(c));
                doilyPanel.setCurrentColor(c);
            }
        });

        undo.addActionListener((e) -> {
            doilyPanel.undo();
            repaint();
        });

        redo.addActionListener((e) -> {
            doilyPanel.redo();
            repaint();
        });

        sectors.addChangeListener((e) -> {
            doilyPanel.setLines((int)sectors.getValue());
            repaint();
        });

        save.addActionListener((e) -> {
            BufferedImage img = new BufferedImage(doilyPanel.getWidth(), doilyPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();
            doilyPanel.paint(g2d);
            galleryPanel.addImage(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            repaint();
        });

        panel.add(settingsPanel, BorderLayout.NORTH);
        panel.add(doilyPanel, BorderLayout.CENTER);
        panel.add(galleryPanel, BorderLayout.EAST);

        settingsPanel.add(clear);
        settingsPanel.add(undo);
        settingsPanel.add(redo);
        settingsPanel.add(reflect);
        settingsPanel.add(showLines);
        settingsPanel.add(eraser);
        settingsPanel.add(color);
        settingsPanel.add(sizeLabel);
        settingsPanel.add(size);
        settingsPanel.add(sectorsLabel);
        settingsPanel.add(sectors);
        settingsPanel.add(save);

        this.setVisible(true);
    }

    public DoilyFrame(){
        init();
    }
    public static void main(String[] args) {
        DoilyFrame frame = new DoilyFrame();
    }

    class ShowLinesListener implements ItemListener {
        public void itemStateChanged(ItemEvent e){
            if(e.getStateChange() == ItemEvent.SELECTED){
                doilyPanel.setShowLines(false);
                repaint();
            } else{
                doilyPanel.setShowLines(true);
                repaint();
            }
        }
    }

    public ImageIcon setButtonIcon(Color c){
        BufferedImage image = new BufferedImage(15, 15, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setColor(c);
        g.fillRect(0,0, 15, 15);

        return new ImageIcon(image);
    }
}
