//package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class SlideShow extends JPanel implements ActionListener {
    private List<ImageIcon> imageIcons;
    private Timer t;
    private int ImageIndex;

    public SlideShow(List<ImageIcon> imageIcons, int intval) {
        this.imageIcons = imageIcons;
        this.ImageIndex = 0;
        this.t = new Timer(intval, this);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        if (!imageIcons.isEmpty()) {
            Image image = imageIcons.get(ImageIndex).getImage();

            int h = getHeight();
            int w = getWidth();
           
            graph.drawImage(image, 0, 0, w, h, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        ImageIndex = (ImageIndex + 1) % imageIcons.size();
        repaint();
    }
}

