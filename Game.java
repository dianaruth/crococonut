import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Game extends Applet implements Runnable, KeyListener, MouseMotionListener, MouseListener {

    public static Image offscreen;
    public Graphics g2;
    public int croc;
    public int counter;
    public int score;
    public boolean ended, started, left, right, mouseInPlay, mouseInInstructions, instructions, back, playAgain;
    public BufferedImage background, crocodile, coconut, bomb, logo, explosion;
    Random rand = new Random();
    Coconut c;
    Bomb b;
    ArrayList<Coconut> cocoList = new ArrayList<Coconut>();
    ArrayList<Bomb> bombList = new ArrayList<Bomb>();

    public void run() {
        croc = 300;
        score = 0;
        ended = false;
        started = false;
        mouseInPlay = false;
        mouseInInstructions = false;
        back = false;
        playAgain = false;
        try {
        	background = ImageIO.read(getClass().getResource("img/background.png"));
        	crocodile = ImageIO.read(getClass().getResource("img/crocodile.png"));
        	coconut = ImageIO.read(getClass().getResource("img/coconut.png"));
        	bomb = ImageIO.read(getClass().getResource("img/bomb.png"));
        	logo = ImageIO.read(getClass().getResource("img/logo.png"));
        	explosion = ImageIO.read(getClass().getResource("img/explosion.png"));
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        while(true){
            /* update crocodile position */
            if (left){
                if (croc > 0)
                    croc -= 6;
            }
            if (right){
                if (croc < 680)
                    croc += 6;
            }
            /* update coconut and bomb position */
            if(started){
                if (counter == 100 || counter == 300){
                    // make new coconut
                    c = new Coconut(rand.nextInt(750), -100, rand.nextInt(3) + 1);
                    cocoList.add(c);
                }
                if (counter == 400){
                    // make new bomb
                    b = new Bomb(rand.nextInt(750), -100, rand.nextInt(3) + 1);
                    bombList.add(b);
                    counter = 0;
                }
                for (Coconut aCocoList : cocoList) {
                    aCocoList.y += aCocoList.speed;
                }
                for (Bomb aBombList : bombList) {
                    aBombList.y += aBombList.speed;
                }
                /* get rid of coconuts that are caught and update score */
                for (int i = 0; i < cocoList.size(); i++){
                    c = cocoList.get(i);
                    if (c.x >= croc - 20 && c.x + 60 <= croc + 120 && c.y >= 200 && c.y <= 250){
                        score += cocoList.get(i).speed;
                        cocoList.remove(i);
                    }
                }
                /* player loses if they catch a bomb */
                for (Bomb aBombList : bombList) {
                    b = aBombList;
                    if (b.x >= croc && b.x + 60 <= croc + 150 && b.y >= 295 && b.y <= 345) {
                        ended = true;
                    }
                }
                if (ended) {
                    cocoList.clear();
                    bombList.clear();
                }
                counter++;
            }
            repaint();
            try {
                Thread.sleep(10);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (!ended) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = true;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (!ended) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = false;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        if(e.getX() > 200 && e.getX() < 285 && e.getY() > 290 && e.getY() < 330){
            mouseInPlay = true;
        }
        else {
            mouseInPlay = false;
        }
        if (e.getX() > 420 && e.getX() < 660 && e.getY() > 290 && e.getY() < 320){
            mouseInInstructions = true;
        }
        else{
            mouseInInstructions = false;
        }
        if (instructions){
            if (e.getX() > 80 && e.getX() < 170 && e.getY() > 330 && e.getY() < 360){
                back = true;
            }
            else {
                back = false;
            }
        }
        if(ended){
            if(e.getX() > 260 && e.getX() < 460 && e.getY() > 230 && e.getY() < 270){
                playAgain = true;
            }
            else {
                playAgain = false;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if(e.getX() > 200 && e.getX() < 285 && e.getY() > 290 && e.getY() < 330){
            started = true;
        }
        if (e.getX() > 420 && e.getX() < 660 && e.getY() > 290 && e.getY() < 320){
            instructions = true;
        }
        if (instructions){
            if (e.getX() > 80 && e.getX() < 170 && e.getY() > 330 && e.getY() < 360){
                instructions = false;
            }
        }
        if(ended){
            if(e.getX() > 260 && e.getX() < 460 && e.getY() > 230 && e.getY() < 270){
                ended = false;
                score = 0;
            }
        }
    }

    public void mouseClicked(MouseEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public void mouseDragged(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
