import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends Panel {
    Frame owner;
    Ship ship;
    long lastFrame;
    long score = 0;
    Label scoreLabel;
    int level = 1;
    int deltaTime;
    public java.util.List<Renderable> objectList;
    public DoubleRowEnemies enemies;
    public Game(Frame owner){
        this.owner = owner;
        setBackground( Color.BLUE);
        setBounds(0,owner.getInsets().top,500,500-owner.getInsets().top);
        objectList = new java.util.ArrayList<Renderable>();
        enemies = new DoubleRowEnemies(level);
        objectList.add(enemies);
        enemies.enemyList.forEach(e-> add(e));
        ship = new Ship();
        add(ship);
        objectList.add(ship);
        setVisible(true);
        ship.requestFocus();
        scoreLabel = new Label();
        scoreLabel.setBounds(450,0,50,20);
        add(scoreLabel);
        scoreLabel.setText(Long.toString(score));
        scoreLabel.setForeground(Color.WHITE);

    }
    public void paint(Graphics g){
        updatePhysic();
        objectList.forEach(obj->obj.render(deltaTime));
        deltaTime = (int)(System.nanoTime()-lastFrame);
        lastFrame=System.nanoTime();
        repaint();
    }
    private void updatePhysic(){
        if(!ship.bulletsToRegister.isEmpty()){
            ship.bulletsToRegister.forEach(bullet -> add(bullet));
        }
        List<Enemy> enemyToDel = new ArrayList<Enemy>();
        List<Bullet> bulletToDel = new ArrayList<Bullet>();
        ship.bulletsToRegister.clear();
        ship.bullets.forEach(bullet -> {
            if(bullet.y<0) {
                bulletToDel.add(bullet);
                remove(bullet);
            }
            else {
                enemies.enemyList.forEach(enemy -> {
                    if (enemy.hitbox.intersects(bullet.hitbox)) {
                        enemyToDel.add(enemy);
                        bulletToDel.add(bullet);
                        addPoints(10*level);
                        remove(enemy);
                        remove(bullet);
                    }
                });
            }
        });
        ship.bullets.removeAll(bulletToDel);
        enemies.enemyList.removeAll(enemyToDel);
        objectList.removeAll(bulletToDel);
        objectList.removeAll(enemyToDel);
        if(enemies.enemyList.isEmpty()) {
            enemies = new DoubleRowEnemies(level++);
            objectList.add(enemies);
            enemies.enemyList.forEach(e-> add(e));
        }
    }
    private void addPoints(long points){
        score+=points;
        scoreLabel.setText(Long.toString(score));
    }
}
