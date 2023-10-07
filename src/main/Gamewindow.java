package main;
import javax.swing.JFrame;
public class Gamewindow {
    private JFrame JFrame;
    public Gamewindow(GamePanel gamepanel){
        JFrame=new JFrame();
        JFrame.setSize(400,400);
        JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.add(gamepanel);
        JFrame.setVisible(true);
    }
}
