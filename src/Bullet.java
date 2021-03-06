import java.awt.*;

public class Bullet extends Canvas implements Renderable{
    double x,y;
    Rectangle hitbox;
    public Bullet(double x, double y){
        setBackground(Color.BLACK);
        setBounds((int)x,(int)y,5,20);
        hitbox = new Rectangle((int)x,(int)y,5,20);
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(int deltaTime) {
        y=y-0.0000001*deltaTime;
        setBounds((int)x,(int)y,5,20);
        hitbox.setBounds((int)x, (int)y, 5, 20);
    }
}
