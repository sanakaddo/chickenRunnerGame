package chickengame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main extends JFrame implements Runnable{
    //Assets
    Image chicken;
    
    int x, y, xDirection;
    int counterCat, counterNest, level;
    
    int mx, my;
    int n_clicks = 0;
     
    static Thread t1;
    static Main m;
    
    static boolean gameStarted;
    
     //Menu Buttons
    Rectangle startButton = new Rectangle(150, 200, 200, 40);
    Rectangle quitButton = new Rectangle(150, 300, 200, 40);
     boolean startHover, quitHover;
    
    
    private ArrayList<Egg> eggs = new ArrayList<Egg>();
    private ArrayList<Cat> cats = new ArrayList<Cat>();
    private ArrayList<Nest> nests = new ArrayList<Nest>();

    //Double Buffering
    Image dbImage;
    Graphics dbg;
    
    Rectangle r1, r2, rChicken, rCat;
    
    int GWIDTH = 500, GHEIGHT = 500;
    Dimension screenSize = new Dimension(GWIDTH, GHEIGHT);
    
    public void run(){
        
        try{
            while(true){
                move();
                
                rChicken = new Rectangle(x, y, 50, 50);
                for (int i = 0; i < eggs.size(); i++) {
                        Egg e = (Egg) eggs.get(i);
                        if (e.isVisible() == true) {
                                e.update();
                        } else {
                                eggs.remove(i);
                        }
                }
                for (int i = 0; i < cats.size(); i++) {
                        Cat c = (Cat) cats.get(i);
                        rCat = new Rectangle(c.getX(), c.getY(), 70, 100);
                        if(rCat.intersects(rChicken)){
                                //GAME OVER
                                c.setVisible(false);
                                gameEnded();
                            }
                        if (c.isVisible() == true) {
                                c.update();
                        } else {
                                cats.remove(i);
                        }
                }
                for (int i = 0; i < nests.size(); i++) {
                        Nest n = (Nest) nests.get(i);
                        if (n.isVisible() == true) {
                                n.update();
                        } else {
                                nests.remove(i);
                        }
                }
                for (int i = 0; i < eggs.size(); i++) {
                    Egg e = (Egg) eggs.get(i);
                    r1 = new Rectangle(e.getX(), e.getY(), 15, 25);
                    for (int j = 0; j < nests.size(); j++) {
                        Nest n = (Nest) nests.get(j);
                        r2 = new Rectangle(n.getX(), n.getY(), 30, 15);
                            if(r1.intersects(r2)){
                                //n.setY(n.getY() - 2 );
                                //n.setX(n.getX() );
                                n.hit();
                                e.setVisible(false);
                            }
                    }
                 }
                
                Thread.sleep(5);
                counterCat--;
                if(counterCat <= 0){
                    addCat();
                    counterCat = level * 500;
                }
                counterNest--;
                if(counterNest <= 0){
                    addNest();
                    counterNest = level * 500;
                }
            }
            
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    
    public void move(){
        
        x += xDirection;
        
        if(x>= 425){
         x = 425;   
        }
        if(x<= 0){
         x = 0;   
        }
    }
    public void shoot() {
        Egg e = new Egg(x , y);
        eggs.add(e);
    }
    public void addCat() {
        //x must be random
        Cat c = new Cat();
        cats.add(c);
    }
    public void addNest() {
        Nest n = new Nest();
        nests.add(n);
    }
     public void setXDirection(int xdir){
        xDirection = xdir;
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int keyCode = e.getKeyCode();
            if(keyCode == e.VK_LEFT){
                setXDirection(-1);
            }
            if(keyCode == e.VK_RIGHT){
               setXDirection(+1);
            }
            if(keyCode == e.VK_SPACE){
                 ImageIcon i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/chicken-fresh-eggs-illustration/Asset 1.png");
                 chicken = i.getImage();
                shoot();
            }
            
        }
        public void keyReleased(KeyEvent e){
             int keyCode = e.getKeyCode();
            if(keyCode == e.VK_LEFT){
                setXDirection(0);
            }
            if(keyCode == e.VK_RIGHT){
               setXDirection(0);
            }
            if(keyCode == e.VK_SPACE){
                ImageIcon i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/chicken-fresh-eggs-illustration/Asset 2.png");
                chicken = i.getImage();
            }
            
        }
    }
    public class MouseHandler extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e){
            mx = e.getX();
            my = e.getY();
            
            if(mx > startButton.x && mx < startButton.x+startButton.width &&
               my > startButton.y && my < startButton.y+startButton.height)
                startHover = true;
            else
                startHover = false;
            
            if(mx > quitButton.x && mx < quitButton.x+quitButton.width &&
               my > quitButton.y && my < quitButton.y+quitButton.height)
                quitHover = true;
            else
                quitHover = false;
        }
        @Override
        public void mousePressed(MouseEvent e){
            mx = e.getX();
            my = e.getY();
            
            if(mx > startButton.x && mx < startButton.x+startButton.width &&
               my > startButton.y && my < startButton.y+startButton.height)
                startGame();           
            
        }
        @Override
        public void mouseReleased(MouseEvent e){
            
        }
    }
        
    public void startGame(){
        gameStarted = true;
        t1 = new Thread(m);
        t1.start();
    }
    public void gameEnded(){
        gameStarted = false;
        
        //t1 = new Thread(m);
        //t1.suspend();
    }
    
    public Main(){
        //Load images
        ImageIcon i = new ImageIcon("E:/CodingDevelopment/JavaNetbeans/Chicken Game/chicken-fresh-eggs-illustration/Asset 2.png");
        chicken = i.getImage();
        
        
        this.setTitle("Chicken Runner");
        this.setSize(screenSize);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.addKeyListener(new AL());
        this.addMouseListener(new MouseHandler());       
        this.addMouseMotionListener(new MouseHandler()); 
        
        gameStarted = false;
        x = 220;
        y = 50;
        level = 1;
        counterCat = level * 100;
        counterNest = level * 100;
    }
    
    public static void main(String[] args) {
        m = new Main();
        //Create and start threads
        //Thread t1 = new Thread(m);
        Thread t1 = new Thread(m);
        if(gameStarted){
            t1.start();
        }
    }
    
    @Override
    public void paint(Graphics g){
            dbImage = createImage(getWidth(), getHeight());
            dbg = dbImage.getGraphics();
            draw(dbg);
            g.drawImage(dbImage, 0, 0, this);
    }
    public void draw(Graphics g){
        
        if(!gameStarted){
            //Menu drawings
            g.setFont(new Font("Arial", Font.BOLD, 40));        
            g.setColor(Color.RED);
            g.drawString("CHICKEN RUNNER", 80, 125);
            
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            g.setColor(Color.CYAN);
            g.drawString("Catch me if you can!!", 160, 160);
            
            //Start Button
            if(!startHover)
                g.setColor(Color.DARK_GRAY);
            else
                g.setColor(Color.gray);
            g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
            g.setFont(new Font("Arial", Font.BOLD, 15));        
            g.setColor(Color.WHITE);
            g.drawString("Start Game", startButton.x+65, startButton.y+25);
            //Quit Button
            if(!quitHover)
                g.setColor(Color.DARK_GRAY);
            else
                g.setColor(Color.gray);
            g.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
            g.setFont(new Font("Arial", Font.BOLD, 15));        
            g.setColor(Color.WHITE);
            g.drawString("Quit Game", quitButton.x+65, quitButton.y+25);
        }else{
            //g draw assets
            g.drawImage(chicken, x, y, this);
            //g.drawRect(x, y, 50, 50);

            for (int i = 0; i < eggs.size(); i++) {
                    Egg e = (Egg) eggs.get(i);
                    g.drawImage(e.getEggImage(), e.getX(), e.getY(), this);
                    //g.drawRect(e.getX(), e.getY(), 15, 25);
            }
            for (int i = 0; i < cats.size(); i++) {
                    Cat c = (Cat) cats.get(i);
                    g.drawImage(c.getCatImage(), c.getX(), c.getY(), this);
                    //g.drawRect(c.getX(), c.getY(), 70, 100);
            }
            for (int i = 0; i < nests.size(); i++) {
                    Nest n = (Nest) nests.get(i);
                    g.drawImage(n.getNestImage(), n.getX(), n.getY(), this);
                    //g.drawRect(n.getX(), n.getY(), 30, 15);
            }
            
        }
        repaint();
        
    }
    
}
