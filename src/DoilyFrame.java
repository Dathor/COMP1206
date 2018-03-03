import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DoilyFrame extends JFrame {

    private JPanel panel = new JPanel();
    private JPanel settingsPanel = new JPanel();
    private DoilyPanel doilyPanel = new DoilyPanel();

    public void init(){
        this.setContentPane(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,800);

        panel.setLayout(new BorderLayout());

        JButton clear = new JButton("Clear");
        JButton undo = new JButton("Undo");
        JButton redo = new JButton("Redo");
        JCheckBox reflect = new JCheckBox("Reflect");
        JCheckBox showLines = new JCheckBox("Hide sector lines");
        JButton save = new JButton("Save");
        JSpinner size = new JSpinner(new SpinnerNumberModel(20,10,100,10));

        showLines.addItemListener(new ShowLinesListener());

        size.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                doilyPanel.setCurrentSize((int)size.getValue());
            }
        });

        panel.add(settingsPanel, BorderLayout.NORTH);
        panel.add(doilyPanel, BorderLayout.CENTER);

        settingsPanel.add(clear);
        settingsPanel.add(undo);
        settingsPanel.add(redo);
        settingsPanel.add(reflect);
        settingsPanel.add(showLines);
        settingsPanel.add(size);
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
}
