import java.awt.*;

public class Enemy extends Canvas implements Renderable {
    float x, y;
    boolean goingRight;
    Rectangle hitbox;
    int level;
    public Enemy(int x, int y, int level){
        this.x = x;
        this.y = y;
        this.level = level;
        goingRight = true;
        setBounds(x,y,30,30);
        setBackground(Color.GREEN);
        setVisible(true);
        hitbox = new Rectangle(x,y,30,30);
    }
    public void render(int deltaTime){
        if(goingRight)
            x+= 0.00000003*deltaTime*(1+level*0.3);
        else
            x-= 0.00000003*deltaTime*(1+level*0.3);

        setBounds((int)x, (int)y, 30, 30);
        hitbox.setBounds((int)x, (int)y, 30, 30);

    }
    public boolean isBorderReached(){
        return  (x<20&&!goingRight) || (x>450&&goingRight);
    }
    public boolean isEndReached(){return y>400;}
}
