import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends Panel {
    MainWindow owner;
    Ship ship;
    long lastFrame;
    long score = 0;
    Label scoreLabel;
    int level = 1;
    int deltaTime;
    boolean isGameOver = false;
    public java.util.List<Renderable> objectList;
    public DoubleRowEnemies enemies;
    public Game(MainWindow owner){
        this.owner = owner;
        setBackground( Color.BLUE);
        setBounds(0,owner.getInsets().top,500,500-owner.getInsets().top);
        objectList = new java.util.ArrayList<Renderable>();
        enemies = new DoubleRowEnemies(level);
        objectList.add(enemies);
        enemies.enemyList.forEach(e-> add(e));
        setVisible(true);
        scoreLabel = new Label();
        scoreLabel.setBounds(450,0,50,20);
        add(scoreLabel);
        scoreLabel.setText(Long.toString(score));
        scoreLabel.setForeground(Color.WHITE);
        ship = new Ship();
        add(ship);
        objectList.add(ship);
        ship.requestFocus();

    }
    public void paint(Graphics g){
        if(isGameOver)
            return;
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
        if(!enemies.bulletsToRegister.isEmpty()){
            enemies.bulletsToRegister.forEach(enemyBullet -> add(enemyBullet));
        }
        List<Enemy> enemyToDel = new ArrayList<Enemy>();
        List<Bullet> bulletToDel = new ArrayList<Bullet>();
        ship.bulletsToRegister.clear();
        enemies.bulletsToRegister.clear();
        enemies.enemyList.forEach(enemy -> {if(enemy.isEndReached())isGameOver=true;});
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
        enemies.bullets.forEach(bullet->
        {
            if(bullet.hitbox.intersects(ship.hitbox))
                isGameOver = true;
            if(bullet.y>500){
                remove(bullet);
                enemies.enemyList.remove(bullet);
            }
        });
        ship.bullets.removeAll(bulletToDel);
        enemies.enemyList.removeAll(enemyToDel);
        objectList.removeAll(bulletToDel);
        objectList.removeAll(enemyToDel);
        if(enemies.enemyList.isEmpty()) {
            objectList.remove(enemies);
            enemies = new DoubleRowEnemies(level++);
            objectList.add(enemies);
            enemies.enemyList.forEach(e-> add(e));
        }
        if(isGameOver)
            gameOver();
    }
    private void addPoints(long points){
        score+=points;
        scoreLabel.setText(Long.toString(score));
    }
    private void gameOver(){
        owner.gameOverScreen(score);
    }
}
