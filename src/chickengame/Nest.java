package chickengame;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Nest {
    Image nestImg;
    
    //Global Variables
    int x, y, duration, currentTime;
    boolean visible;
    
     int GWIDTH = 500, GHEIGHT = 500;
    
    public Nest(int x, int y, int duration){
        this.x = x;
        this.y = y;
        this.duration = 300;
        this.visible = true;
        this.currentTime = 0;
        
        ImageIcon i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/easter_background_shiny_golden_egg_icon_decoration_6829047/Asset 1.png");
        nestImg = i.getImage();

    }
    public Nest(){
        this.x = getRandomXPos();
        this.y = getRandomYPos();
        this.duration = 300;
        this.visible = true;
        this.currentTime = 0;
        
        ImageIcon i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/easter_background_shiny_golden_egg_icon_decoration_6829047/Asset 1.png");
        nestImg = i.getImage();

    }
    public void update(){
        currentTime++;
        if(currentTime >= duration){
            this.visible = false;
        }
    }
     public int getRandomXPos(){
            Random r = new Random();
            return r.nextInt(GWIDTH - 75);
        }
     public int getRandomYPos(){
            Random r = new Random();
            int low = 250;
            int high = GHEIGHT - 100;
            return r.nextInt(high-low) + low;
        }
     public int getX() {
            return x;
    }

    public int getY() {
            return y;
    }

    public int getDuration() {
            return duration;
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

    public void setDuration(int duration) {
            this.duration = duration;
    }

    public void setVisible(boolean visible) {
            this.visible = visible;
    }
    public Image getNestImage(){
        return nestImg;
    }
    public void hit(){
    ImageIcon i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/easter_background_shiny_golden_egg_icon_decoration_6829047/Asset 2.png");
        nestImg = i.getImage();
        currentTime = duration - 100;
    }
}
