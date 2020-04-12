package chickengame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;


public class Egg {
    Image egg;
    
    //Global Variables
    int x, y, speed;
    boolean visible;

    
    public Egg(int x, int y){
        this.x = x +20;
        this.y = y +30;
        this.speed = 6;
        this.visible = true;
        
        ImageIcon i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/golden-easter-day-egg-collection/Asset 24.png");
        egg = i.getImage();

    }
    
    public void update(){
        y += speed;
        if (x > 500){
            visible = false;
        }
		
    }
    public int getX() {
            return x;
    }

    public int getY() {
            return y;
    }

    public int getSpeed() {
            return speed;
    }

    public boolean isVisible() {
            return visible;
    }

    public void setX(int x) {
            this.x = x;
    }

    public void setY(int y) {
            this.y = y;
    }

    public void setSpeedX(int speed) {
            this.speed = speed;
    }

    public void setVisible(boolean visible) {
            this.visible = visible;
    }
    public Image getEggImage(){
        return egg;
    }



    
}
