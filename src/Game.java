import java.awt.*;

public class Game extends Panel {
    Frame owner;
    Ship ship;
    long lastFrame;
    int deltaTime;
    public java.util.List<Enemy> enemyList;
    public Game(Frame owner){
        this.owner = owner;
        setBackground( Color.BLUE);
        setBounds(0,owner.getInsets().top,500,500-owner.getInsets().top);


        ship = new Ship();
        add(ship);
        enemyList = new java.util.ArrayList<Enemy>();
        enemyList.add(new Enemy(50,50));
        add(enemyList.get(0));
        setVisible(true);
        ship.requestFocus();

    }
    public void paint(Graphics g){

        ship.render(deltaTime);
        enemyList.get(0).render(deltaTime);
        deltaTime = (int)(System.nanoTime()-lastFrame);
        lastFrame=System.nanoTime();
        repaint();
    }
}
