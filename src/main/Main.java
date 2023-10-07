package main;

import javax.swing.JFrame;

class Main {
    public static void main(String[] args) {
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setAutoRequestFocus(false);
        window.setTitle("Mining");
        GamePanel gamepanel=new GamePanel();
        window.add(gamepanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamepanel.setupGame();
        gamepanel.startgameThread();
    }
}
