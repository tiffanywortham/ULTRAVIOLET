package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    ObjectHandler handler;

    public KeyInput(ObjectHandler handler){

        this.handler = handler;

    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_UP) handler.setUp(true);
                if (key == KeyEvent.VK_DOWN) handler.setDown(true);
                if (key == KeyEvent.VK_RIGHT) handler.setRight(true);
                if (key == KeyEvent.VK_LEFT) handler.setLeft(true);
            }
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player){
                if (key == KeyEvent.VK_UP) handler.setUp(false);
                if (key == KeyEvent.VK_DOWN) handler.setDown(false);
                if (key == KeyEvent.VK_RIGHT) handler.setRight(false);
                if (key == KeyEvent.VK_LEFT) handler.setLeft(false);
            }
        }

    }
}
