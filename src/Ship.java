import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ship extends Canvas {
    double x = 250;
    double y =425;
    boolean isPressed = false;
    int lastKeyPressed;

    public Ship() {
        setBackground(Color.BLACK);
        setBounds(225, 400, 50, 50);
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case (KeyEvent.VK_LEFT):
                        isPressed = true;
                        lastKeyPressed=KeyEvent.VK_LEFT;
                        break;
                    case (KeyEvent.VK_RIGHT):
                        isPressed = true;
                        lastKeyPressed=KeyEvent.VK_RIGHT;
                        break;
                    case (KeyEvent.VK_UP):
                        System.out.println("Pow");
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case (KeyEvent.VK_LEFT):
                        isPressed = lastKeyPressed!=KeyEvent.VK_LEFT;
                        break;
                    case (KeyEvent.VK_RIGHT):
                        isPressed = lastKeyPressed!=KeyEvent.VK_RIGHT;
                        break;
                }
            }
        });
    }


    @Override
    public void paint(Graphics g) {

    }
    public void render(int deltaTime){
        if (isPressed) {
            x= lastKeyPressed==KeyEvent.VK_LEFT?x-(0.0000001*deltaTime):x+(0.0000001*deltaTime);
            setBounds((int)x - 25, (int)y - 25, 50, 50);
        }
    }
}
