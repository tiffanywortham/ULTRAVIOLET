package main;

import java.awt.*;

public class Entity extends GameObject{

    public Entity(int x, int y, ID id) {
        super(x, y, id);

    }

    public void tick() {
        x += velocityX;
        y += velocityY;

    }

    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval(x, y, 32,32);

    }

    public Rectangle getBounds() {
        return null;
    }
}
