import java.awt.*;

public class MyWindow extends Window {

    public MyWindow(Frame owner) {
        super(owner);
    }

    public MyWindow(Window owner) {
        super(owner);
    }

    public MyWindow(Window owner, GraphicsConfiguration gc) {
        super(owner, gc);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        g.drawRect(1, 1, this.getWidth() - 3, this.getHeight() - 3);
    }


}
