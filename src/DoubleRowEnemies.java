import java.util.*;

public class DoubleRowEnemies implements Renderable{
    public java.util.List<Enemy> enemyList;
    public DoubleRowEnemies(int level){
        setEnemies(level);
        lastShoot= System.nanoTime();
        this.level = level;
    }
    int level;
    long lastShoot;
    List<EnemyBullet> bullets = new ArrayList<EnemyBullet>();
    List<EnemyBullet> bulletsToRegister = new ArrayList<EnemyBullet>();
    public void render(int deltatime){
        if(enemyOnBoudry())
            moveToNextRow();
        enemyList.forEach(enemy -> enemy.render(deltatime));
        bullets.forEach(bullet -> bullet.render(deltatime));
        if(System.nanoTime()-lastShoot>(5000000000L/1+(level*0.3))){
            lastShoot = System.nanoTime();
            Enemy e  = enemyList.get((int)(Math.random()*enemyList.size()));
            EnemyBullet b= new EnemyBullet(e.x,e.y);
            bulletsToRegister.add(b);
            bullets.add(b);
        }
    }
    private void setEnemies(int level){
        enemyList = new java.util.ArrayList<Enemy>();
        for(int j =0;j<2;j++){
            for(int i=0;i<8;i++){
                Enemy enemy = new Enemy(20+i*50,50+40*j,level);
                enemyList.add(enemy);
            }
        }
    }
    private boolean enemyOnBoudry(){
        int index=0;
        while (index<enemyList.size()){
            if(enemyList.get(index++).isBorderReached())
                return true;
        }
        return  false;
    }
    private void moveToNextRow(){
        enemyList.forEach(enemy -> {enemy.y+=40;enemy.goingRight=!enemy.goingRight;});
    }
}
