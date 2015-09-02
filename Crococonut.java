import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Crococonut extends Game {

    public void init(){
        setSize(800, 480);
        Thread thread = new Thread(this);
        thread.start();
        offscreen = createImage(800, 480);
        g2 = offscreen.getGraphics();
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void paint (Graphics g){
        /* draw background */
        g2.clearRect(0, 0, 800, 480);
        g2.drawImage(background, 0, 0, this);
        Color crocBlue = new Color(0x4F98D3);
        g2.setColor(crocBlue);
        g2.fillRect(0, 400, 800, 80);
        if(!started){
            g2.setFont(new Font("Tahoma", Font.BOLD, 40));
            if (instructions){
                g2.setColor(Color.black);
                g2.setFont(new Font("Tahoma", Font.BOLD, 30));
                g2.drawString("Lyle the Crocodile is hungry for coconuts.", 70, 100);
                g2.drawString("Use the left and right arrow keys to help", 70, 140);
                g2.drawString("him eat as many coconuts as he can. The faster", 70, 180);
                g2.drawString("the coconut falls, the more points it's worth.", 70, 220);
                g2.drawString("But watch out for the bombs! If he eats one of", 70, 260);
                g2.drawString("those then you lose!", 70, 300);
                g2.setFont(new Font("Tahoma", Font.BOLD, 40));
                if (back){
                    g2.setColor(Color.GRAY);
                }
                g2.drawString("back", 80, 360);

            }
            else {
                if(mouseInPlay){
                    g2.setColor(Color.GRAY);
                    g2.drawString("play", 200, 320);
                }
                else{
                    g2.setColor(Color.black);
                    g2.drawString("play", 200, 320);
                }
                if(mouseInInstructions){
                    g2.setColor(Color.GRAY);
                    g2.drawString("instructions", 420, 320);
                }
                else{
                    g2.setColor(Color.black);
                    g2.drawString("instructions", 420, 320);
                }
                if (instructions){
                    System.out.println("instructions now");
                }
                g2.drawImage(logo, 150, 70, this);
            }
        }
        else {
            for (Coconut aCocoList : cocoList) {
                g2.drawImage(coconut, aCocoList.x, aCocoList.y, this);
            }
            for (Bomb aBombList : bombList) {
                g2.drawImage(bomb, aBombList.x, aBombList.y, this);
            }
            g2.setColor(Color.black);
            g2.setFont(new Font("Tahoma", Font.BOLD, 40));
            g2.drawString("Score: " + score, 50, 50);
        }
        if (ended){
            g2.setColor(Color.red);
            g2.setFont(new Font("Tahoma", Font.BOLD, 60));
            g2.drawString("GAME OVER", 200, 200);
            g2.setColor(Color.black);
            g2.setFont(new Font("Tahoma", Font.BOLD, 40));
            if (playAgain){
                g2.setColor(Color.GRAY);
            }
            g2.drawString("play again", 260, 260);
            g2.drawImage(explosion, croc, 310, this);
        }
        else {
            g2.drawImage(crocodile, croc, 310, this);
        }
        g.drawImage(offscreen, 0, 0, this);
    }

    public void update (Graphics g){
        paint(g);
    }
}
