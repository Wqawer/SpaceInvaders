import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoubleRowEnemies implements Renderable{
    public java.util.List<Enemy> enemyList;
    public DoubleRowEnemies(int level){
        setEnemies(level);
    }
    int level;
    long cooldown= 100000;
    public void render(int deltatime){
        if(enemyOnBoudry())
            moveToNextRow();
        enemyList.forEach(enemy -> enemy.render(deltatime));
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
