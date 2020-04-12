package chickengame;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Cat {
    private int size, speed, x, y;
    boolean visible;
    Image cat;
    
    int GWIDTH = 500, GHEIGHT = 500;
    
    public Cat(int x, int y, int size){
        
        
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = -20;
        this.visible = true;
        
        ImageIcon i;
        if(size == 1)
            i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/cat-rolling-roof/Asset 11.png");
        else if(size == 2)
            i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/cat-rolling-roof/Asset 12.png");
        else 
            i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/cat-rolling-roof/Asset 13.png");
        cat = i.getImage();
    }
    public Cat(){
        
        //X must be random
        this.x = getRandomXPos();
        this.y = GHEIGHT;
        this.size = 1;
        this.speed = -2;
        this.visible = true;
        
        ImageIcon i;
        if(size == 1)
            i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/vector_amusing_cats_design_set_536257/Asset 1.png");
        else if(size == 2)
            i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/cat-rolling-roof/Asset 12.png");
        else 
            i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/cat-rolling-roof/Asset 13.png");
        cat = i.getImage();
    }
    public int getRandomXPos(){
            Random r = new Random();
            return r.nextInt(GWIDTH - 75);
        }
    public void update() {
        this.y += speed;
        if (y < -50){
            visible = false;
        }
    }
    public int getSize() {
        return size;
    }

    public int getSpeed() {
            return speed;
    }

    public int getX() {
            return x;
    }

    public int getY() {
            return y;
    }
    public Image getCatImage(){
        return cat;
    }
    public boolean isVisible() {
            return visible;
    }

    public void setSize(int size) {
            this.size = size;
    }

    public void setSpeed(int speedX) {
            this.speed = speedX;
    }

    public void setX(int centerX) {
            this.x = centerX;
    }
    
    public void setY(int centerY) {
            this.y = centerY;
    }
    public void setVisible(boolean visible) {
            this.visible = visible;
    }
    
}
