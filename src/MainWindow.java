import java.awt.*;
import java.awt.event.*;

public class MainWindow extends Frame {
    Game game;
    Menu panel;

    public MainWindow() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setResizable(false);
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
        setLayout(null);
        setSize(500, 500);
        setVisible(true);
        panel = new Menu();
        panel.setBackground(Color.PINK);
        panel.setBounds(0, getInsets().top, 500, 495 - getInsets().top);
        add(panel);
        Button startButton = new Button("New Game");
        startButton.setBounds(200, 60, 100, 60);
        startButton.addActionListener((e) -> {
            startGame(panel);
        });
        panel.add(startButton);

    }

    public void startGame(Panel panel) {
        game = new Game(this);
        add(game);
        remove(panel);

    }
}
