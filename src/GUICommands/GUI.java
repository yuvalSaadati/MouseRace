package GUICommands;
import game.Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import javax.swing.*;

public class GUI implements ActionListener {
    private DrawSurface surface;
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel drawingPanel;
    private JButton startButton;
    private int width;
    private int height;
    private Game game;

    public GUI (String title, int width, int height, Game game) {
        this.game = game;
        this.width = width;
        this.height = height;
        this.frame = new JFrame(title);
        this.mainPanel = new JPanel();
        this.startButton = new JButton("start");
        //this.startButton.setPreferredSize(new Dimension(200, 40));
        this.mainPanel.add(this.startButton, "Center");
        this.startButton.addActionListener(this);
        this.drawingPanel = new GUI.GUIPanel();
        this.frame.setDefaultCloseOperation(3);
        this.frame.setSize(width, height);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.setIgnoreRepaint(true);
        this.frame.setContentPane(this.mainPanel);
        this.frame.setLayout(new BorderLayout());
        this.mainPanel.add(this.drawingPanel, "Center");
        this.mainPanel.setPreferredSize(new Dimension(width, height));
        this.frame.pack();
        this.surface = new DrawSurface();
    }

    public DrawSurface getDrawSurface() {

        return new DrawSurface();
    }

    public void show(DrawSurface surface) {
        GUI.this.frame.createBufferStrategy(2);
        BufferStrategy bf = GUI.this.frame.getBufferStrategy();
        Graphics graphics = bf.getDrawGraphics();
        GUI.this.surface = surface;
        GUI.this.frame.paint(graphics);
        bf.show();
    }

    public void close() {
        this.frame.dispatchEvent(new WindowEvent(this.frame, 201));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //this.startButton.remove(0);
        this.mainPanel.remove(this.startButton);
        this.game.run();
    }

    private class GUIPanel extends JPanel {
        private GUIPanel() {
        }

        public void paint(Graphics g) {
            if (GUI.this.surface != null) {
                GUI.this.surface.paint(g);
            }

        }
    }
}
