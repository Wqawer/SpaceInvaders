import java.awt.*;

public class Enemy extends Canvas {
    float x, y;
    boolean goingRight;
    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        goingRight = true;
        setBounds(x,y,30,30);
        setBackground(Color.GREEN);
        setVisible(true);
    }
    public void render(int deltaTime){
        if(goingRight)
            x+= 0.0000001*deltaTime;
        else
            x-= 0.0000001*deltaTime;
        if(x>450){
            x=450;
            goingRight=!goingRight;
        }
        if(x<50){
            x=50;
            goingRight=!goingRight;
        }

        setBounds((int)x - 15, (int)y - 15, 30, 30);

    }
}
