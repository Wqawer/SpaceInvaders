import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Ship extends Canvas implements Renderable {
    double x = 225;
    double y =400;
    boolean isPressed = false;
    boolean shotPending = false;
    long lastShoot=0;
    Rectangle hitbox = new Rectangle(225,400,50,50);
    int lastKeyPressed;
    List<Bullet> bullets;
    List<Bullet> bulletsToRegister;

    public Ship() {
        setBackground(Color.BLACK);
        setBounds(225, 400, 50, 50);
        bullets = new ArrayList<Bullet>();
        bulletsToRegister = new ArrayList<Bullet>();
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
                        shotPending = true;
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

    public void render(int deltaTime){
        if (isPressed) {
            x= lastKeyPressed==KeyEvent.VK_LEFT?x-(0.0000001*deltaTime):x+(0.0000001*deltaTime);
            if(x<0)
                x=0;
            else if(x>450)
                x=450;
            setBounds((int)x, (int)y, 50, 50);
            hitbox.setBounds((int)x, (int)y, 50, 50);
        }
        if(shotPending){
            shotPending=false;
            shoot();
        }
        bullets.forEach(bullet -> bullet.render(deltaTime));
    }
    private void shoot(){
        if(System.nanoTime()-lastShoot<500000000L)
            return;
        lastShoot = System.nanoTime();
        Bullet b = new Bullet(x+25,y);
        bulletsToRegister.add(b);
        bullets.add(b);
    }
}
