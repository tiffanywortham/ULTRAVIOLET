package main;

import java.awt.*;

public class Block extends GameObject {
    public Block(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, 20, 20);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }

}
