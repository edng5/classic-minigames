import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.event.*;
import java.awt.event.MouseEvent.*;
import java.awt.event.MouseListener.*;
import java.net.URL;
import java.awt.Rectangle;

public class Pong_BBV3 extends JFrame implements ActionListener, KeyListener, Runnable, MouseListener{ 
  
  MyDrawPanel drawpanel1;
  Thread th = new Thread (this);
  JPanel p1;
  int bdx=0,bdy=0;
  int b2x=260,b2y=330,b2w=20,b2h=20,b2dx=0,b2dy=0;
  int xmove=3,ymove=3,x2move=3,y2move=2;
  int goy=-100,gody=5;
  int game=0,difficulty=0,start=0;
  int direction=1;
  int score=0, highscore=0,scorex=500;
  int bscore=0,bhighscore=0;
  int mode=1;
  
  int pgp=0,pbh=0,bgp=0,bbb=0;
  
  int nx=-150,ndx=0,check=0, hit=0, counter=0;
  
  int brickx=0, bricky=35, sizeX=550, sizeY=700;
  
  int numBricks=50;
  
  Bricks [] bricks= new Bricks [numBricks];
  Ball ball;
  
  Paddle paddle;
  
  int startx=0;
  int starty=0;
  int chkpt=9;
  int delay=0;
  
  BufferedImage PongMM =null;
  BufferedImage BrickMM =null;
  BufferedImage PongHS =null;
  BufferedImage BrickHS =null;
  
  Color Blue = new Color(18,38,169);
  Color Orange = new Color(247,148,29);
  
  public static void main(String[ ] args) 
  {
    new Pong_BBV3();
  }  
  
  public Pong_BBV3(){
    
    try
    {
      PongMM = ImageIO.read(new File("PongMM.jpg"));
      BrickMM = ImageIO.read(new File("BrickMM.jpg"));
      PongHS = ImageIO.read(new File("PongHS.jpg"));
      BrickHS = ImageIO.read(new File("BrickHS.jpg"));
    }
    catch(IOException e)
    {
      System.out.println("Error");
    } 
    
    for(int i=0;i<50;i++)
    {
      bricks[i]=new Bricks();
      bricks[i].create(sizeX, sizeY);
      bricks[i].x+=startx;
      bricks[i].y+=starty;
      startx+=55;
      if (i==chkpt)
      {
        starty+=35;
        startx=0;
        chkpt+=10;
      }
    }
    
    ball= new Ball();
    ball.create(sizeX,sizeY);
    
    paddle= new Paddle();
    paddle.create(sizeX,sizeY);
    
    p1=new JPanel();
    
    drawpanel1=new MyDrawPanel();
    p1.setLayout(new GridLayout(1,1));
    p1.add(drawpanel1);
    
    this.add(p1);
    
    this.addMouseListener(this);
    
    this.setSize(sizeX,sizeY);
    this.setVisible(true);
    this.setResizable(false);
    
    addKeyListener(this);
    
    th.start ();
  } 
  
  public void actionPerformed(ActionEvent e){}
  
  public void mousePressed(MouseEvent e)
  {
    int mouseX=e.getX();
    int mouseY=e.getY();
    if (game==0)
    {
      if (mouseX>=100 && mouseX<=500 && mouseY>=290 && mouseY<=370)
      {
        game=1;
        if (mode==1)
        {
          pgp++;
        }
        if (mode==2)
        {
          bgp++;
        }
      }
      if (mouseX>=100 && mouseX<=500 && mouseY>=390 && mouseY<=480)
      {
        game=3;
      }
      if (mouseX>=100 && mouseX<=500 && mouseY>=510 && mouseY<=590)
      {
        System.exit(0);
      }
    }
    if (game==0||game==3)
    {
      if (mouseX>=220 && mouseX<=500 && mouseY>=670 && mouseY<=700)
      {
        if (mode==1)
        {
          mode=2;
          ball.x=260;
          ball.y=570;
        }
        else if(mode==2)
        {
          mode=1;
          ball.x=260;
          ball.y=330;
        }
      }
    }
    if (game==3)
    {
      if (mouseX>=20 && mouseX<=130 && mouseY>=30 && mouseY<=80)
      {
        game=0;
      }
    }
  }
  
  public void mouseReleased(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mouseClicked(MouseEvent e){}
  
  public void keyTyped(KeyEvent e) {
  }
  
  public void keyPressed(KeyEvent e) {
    
    int keyCode = e.getKeyCode();
    if (game==1)
    {
      if (keyCode == KeyEvent.VK_LEFT) 
      {
        if (difficulty>2)
        {
          paddle.x_speed=-8;
        }
        else
        {
          paddle.x_speed=-5;
        }
      }
      if (keyCode == KeyEvent.VK_RIGHT) 
      {
        if (difficulty>2)
        {
          paddle.x_speed=8;
        }
        else
        {
          paddle.x_speed=5;
        }
      }
      if (keyCode == KeyEvent.VK_SPACE) 
      {
        bdx=-xmove;
        bdy=-ymove;
        start=1;
      }
    }
    if (game==2)
    {
      if (keyCode == KeyEvent.VK_ENTER) 
      {
        game=0;
        paddle.x=200;
        paddle.sizeW=150;
        paddle.x_speed=0;
        ball.x=280;
        ball.y=330;
        bdx=0;
        bdy=0;
        xmove=3;
        ymove=3;
        goy=-100;
        start=0;
        difficulty=0;
        b2x=280;
        b2y=330;
        b2dx=0;
        b2dy=0;
        score=0;
        nx=-100;
        bscore=0;
        check=0;
        counter=0;
        if (mode==1)
        {
          ball.x=280;
          ball.y=330;
        }
        if (mode==2)
        {
          for(int a=0;a<50;a++)
        {
          bricks[a].x=900;
        }
        startx=0;
        starty=0;
        chkpt=9;
          for(int i=0;i<50;i++)
          {
            bricks[i]=new Bricks();
            bricks[i].create(sizeX, sizeY);
            bricks[i].x+=startx;
            bricks[i].y+=starty;
            startx+=55;
            if (i==chkpt)
            {
              starty+=35;
              startx=0;
              chkpt+=10;
            }
          }
          ball.x=280;
          ball.y=570;
        }
      }
    }
  }
  
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (game==1)
    {
      if (keyCode == KeyEvent.VK_LEFT) 
      {
        paddle.x_speed=0;
      }
      if (keyCode == KeyEvent.VK_RIGHT) 
      {
        paddle.x_speed=0;
      }
    }
  }
  
  public void run ()
  { 
    while (true)
    {
      try
      { 
        
        Thread.sleep (10);
      }
      catch (InterruptedException ex)
      { 
      }
      if (game==1)
      {
        paddle.x+=paddle.x_speed;
        ball.x+=bdx;
        ball.y+=bdy;
        ball.boundary.x=ball.x;
        ball.boundary.y=ball.y;
        for (int i=0;i<50;i++)
        {
          bricks[i].boundaryB.x=bricks[i].x;
          bricks[i].boundaryB.y=bricks[i].y+28;
          bricks[i].boundaryT.x=bricks[i].x;
          bricks[i].boundaryT.y=bricks[i].y;
          bricks[i].boundaryL.x=bricks[i].x;
          bricks[i].boundaryL.y=bricks[i].y;
          bricks[i].boundaryR.x=bricks[i].x+49;
          bricks[i].boundaryR.y=bricks[i].y;
        }
        if (difficulty>=0)
        {
          b2x+=b2dx;
          b2y+=b2dy;
        }
        if (check==0)
        {
          if (score==10||score==20||score==30||score==40)
          {
            ndx=5;
            nx+=ndx;
          }
        }
      }
      if (game==2)
      {
        if (goy<350)
        {
          goy+=gody;
        }
      }
      
      repaint();
      Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    }
  }
  
  public void checkWin ()
  { 
    if (counter==numBricks)
    {
      ball.y=570;
      ball.x=280;
      ball.x_speed=0;
      ball.y_speed=0;
      delay++;
    }
    if (delay==50)
    {
      game=2;
      delay=0;
    }
  }
  
  class MyDrawPanel extends JPanel {
    
    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
      
      if (game==0)
      {        
        if (mode==1)
        {
          g2.drawImage(PongMM,0,0,550,700,null);
        }
        if (mode==2)
        {
          g2.drawImage(BrickMM,0,0,550,700,null);
        }
      }
      
      if (game==1 || game==2)
      {
        if (score>highscore)
        {
          highscore=score;
        }
        //paddle boundaries
        if (paddle.x<0)
        {
          paddle.x=0;
        }
        if (paddle.x>550-paddle.sizeW)
        {
          paddle.x=550-paddle.sizeW;
        }
        //Ball collsions
        if (ball.y<=0)
        {
          bdy=ymove;
        }
        if (ball.x<=0)
        {
          bdx=xmove;
        }
        if (ball.x>=550-ball.size)
        {
          bdx=-xmove;
        }
        if (ball.y>700-ball.size)
        {
          ball.y=650;
          b2y=650;
          game=2;
        }
        
        
        if (mode==1)
        {
          g2.setColor(Color.BLACK);
          g2.fillRect(0,0,550,700);
          if (difficulty==0)
          {
            xmove=3;
            ymove=3;
          }
          g2.setColor(Color.WHITE);
          g2.setFont(new Font("Arial Black",1,30));
          if (difficulty==1)
          {
            xmove=5;
            ymove=5;
            g2.drawString("FASTER!",nx,300);
          }
          if (difficulty==2)
          {
            paddle.sizeW=90;
            g2.drawString("SHRINKED!",nx,300);
          }
          if (difficulty==3)
          {
            g2.drawString("DOUBLE TROUBLE!",nx,300);
          }
          if (difficulty==3 || difficulty==4)
          {
            g2.setColor(Color.WHITE);
            g2.fillOval(b2x,b2y,b2w,b2h);
          }
          if (difficulty==4)
          {
            xmove=5;
            ymove=8;
            g2.drawString("FASTERR!!!",nx,300);
          }
          
          if (nx>700)
          {
            nx=-250;
            ndx=0;
            check=1;
            if (score>29)
            {
              nx=-400;
            }
          }
          
          if (score>9)
          {
            difficulty=1;
          }
          if (score>19)
          {
            difficulty=2;
          }
          if (score>29)
          {
            difficulty=3;
          } 
          if (score>39)
          {
            difficulty=4;
          }
          
          if (score==9||score==19||score==29||score==39)
          {
            check=0;
          }
          
          g2.setColor(Color.WHITE);
          g2.setFont(new Font("Arial Black",1,40));
          g2.drawString(""+ score,scorex,50);
          if (score>9)
          {
            scorex=480;
          }
          g2.setFont(new Font("Arial Black",1,20));
          g2.drawString("Highscore: "+highscore,375,80);
          
          //Ball Collision
          if (paddle.x-ball.size<ball.x && ball.x<paddle.x+paddle.sizeW && ball.y>=600-ball.size && ball.y<=615-ball.size)
          {
            bdy=-ymove;
            score++;
            pbh++;
            if(score==30)
            {
              b2dx=-3;
              b2dy=-2;
            }
          }
          
          //Ball 2 Collision
          if (b2y<0)
          {
            b2dy=2;
          }
          if (b2x<0)
          {
            b2dx=3;
          }
          if (b2x>550-b2w)
          {
            b2dx=-3;
          }
          if (paddle.x-b2w<b2x && b2x<paddle.x+paddle.sizeW && b2y>=600-b2h && b2y<=615-b2h)
          {
            b2dy=-2;
            score++;
            pbh++;
          }
          
          if (b2y>700-b2h)
          {
            b2y=650;
            ball.y=650;
            game=2;
          }
        }
        if (mode==2)
        {
          g2.setColor(Blue);
          g2.fillRect(0,0,550,700);
          g2.setColor(Orange);
          for (int i=0;i<50;i++)
          {
            g2.setColor(Orange);
            g2.fillRect(bricks[i].x,bricks[i].y,bricks[i].sizeW,bricks[i].sizeH);
            g2.setColor(Color.BLACK);
            g2.fillRect(bricks[i].boundaryT.x,bricks[i].boundaryT.y,50,3);
            g2.fillRect(bricks[i].boundaryB.x,bricks[i].boundaryB.y,50,3);
            g2.fillRect(bricks[i].boundaryL.x,bricks[i].boundaryL.y,3,30);
            g2.fillRect(bricks[i].boundaryR.x,bricks[i].boundaryR.y,3,30);
          }
          g2.setColor(Orange);
          for (int i=0;i<50;i++)
          {
            //Top
            if  (ball.boundary.intersects(bricks[i].boundaryT))
            {
              bdy=-ymove;
              bricks[i].x=600;
              hit=1;
            }
            if  (ball.boundary.intersects(bricks[i].boundaryB))
            {
              bdy=ymove;
              bricks[i].x=600;
              hit=1;
            }
            if  (ball.boundary.intersects(bricks[i].boundaryL))
            {
              bdx=-xmove;
              bricks[i].x=600;
              hit=1;
            }
            if  (ball.boundary.intersects(bricks[i].boundaryR))
            {
              bdx=xmove;
              bricks[i].x=600;
              hit=1;
            }
            if (hit==1)
            {
              counter++;
              bbb++;
              bscore+=10;
              hit=0;
            }
            checkWin();
          }
          
          if (bscore>bhighscore)
          {
            bhighscore=bscore; 
          }
          g2.setFont(new Font("Arial Black",1,30));
          g2.drawString(""+bscore,20,640);
          g2.setFont(new Font("Arial Black",1,15));
          g2.drawString("Highscore: "+bhighscore,20,660);
          
          if (paddle.x-ball.size<ball.x && ball.x<paddle.x+paddle.sizeW && ball.y>=600-ball.size && ball.y<=615-ball.size)
          {
            bdy=-ymove;
          }
        }
        if (start==0)
        {
          if (mode==1)
          {
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial Black",1,20));
            g2.drawString("Press Space to Play",160,380);
          }
          if (mode==2)
          {
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial Black",1,20));
            g2.drawString("Press Space to Play",160,650);
          }
        }
        
        g2.setColor(Color.WHITE);
        g2.fillRect(paddle.x,paddle.y,paddle.sizeW,paddle.sizeH);
        g2.fillOval(ball.x,ball.y,ball.size,ball.size);
        if (game==2)
        {
          g2.setFont(new Font("Arial Black",1,50));
          g2.drawString("Game Over!",100,goy);
          g2.setFont(new Font("Arial Black",1,20));
          g2.drawString("Press Enter to Return to the Main Menu",50,380);
        }
      }
      if (game==3)
      {
        if (mode==1)
        {
          g2.drawImage(PongHS,0,0,550,700,null);
          g2.setColor(Color.WHITE);
          g2.setFont(new Font("Arial",1,30));
          g2.drawString(""+highscore,400,320);
          g2.drawString(""+pgp,400,420);
          g2.drawString(""+pbh,400,540);
        }
        if (mode==2)
        {
          g2.drawImage(BrickHS,0,0,550,700,null);
          g2.setColor(Color.WHITE);
          g2.setFont(new Font("Arial",1,30));
          g2.drawString(""+bhighscore,400,320);
          g2.drawString(""+bgp,400,420);
          g2.drawString(""+bbb,400,540);
        }
      }
    }
  }
}
