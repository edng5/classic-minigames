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
public class Fishy extends JFrame implements ActionListener, KeyListener, Runnable, MouseListener{ 
  
  MyDrawPanel drawpanel1;
  Thread th = new Thread (this);
  JPanel p1;
  int dx=0,dy=0,dx2=1,goY=-100,deltagoY=0,sharkdx=2,hookdy=3,hookFlag=0;
  int game=0,difficulty=1;
  int direction=1;
  int lives=1,speed=1,score=0,scorecap=9000,character=1,hooked=0;
  int whichEnemy=0;
  
  int direction2=2,mdx=0,mdy=0,whoWon=0;
  
  int gp=0,Tsa=0,sa=0,Tfa=0,fa=0,ehs=0,mhs=0,hhs=0,fishAte=0,sharkAte=0,dbh=0,p1s=0,p2s=0;
  
  //Unlock characters by setting to 1
  int unlockS=0,unlockM=0;
  
  int [][]reset= {{0,-100,-300,-275},
    {100,300,400,600},
    {150,42,300,210},
    {50,14,100,70},
    {1000,1300,930,900},
    {50,480,200,500},
    {150,42,30,210},
    {50,14,10,70},
    {370,370,370,370},
    {250,250,250,250},
    {60,60,60,60},
    {20,20,20,20},
    {100,140,180,220}};
  
  int [][]resetSharks= {{0,-100},
    {100,550},
    {150,210},
    {50,70},
    {1000,1300},
    {140,480},
    {321,420},
    {107,130},
    {210,840},
    {-400,-400}};
  
  //Enemy Fish
  int [] enemiesxR={0,-100,-300,-275};
  int [] enemiesy1={100,300,400,600};
  int [] enemiesw={150,42,300,210};
  int []enemiesh={50,14,100,70};
  int [] enemiesxL={1000,1300,930,900};
  int [] enemiesy2={50,480,200,500};
  int [] enemiesw2={150,42,30,210};
  int []enemiesh2={50,14,10,70};
  
  //Character
  int [] x={370,370,370,370};
  int [] y={250,250,250,250};
  int [] fishw={60,60,60,60};
  int [] fishh={20,20,20,20};
  int [] livesX={100,140,180,220};
  
  //Sharks
  int [] sharkxR={0,-100};
  int [] sharky1={100,550};
  int [] sharkw={150,210};
  int [] sharkh={50,70};
  int [] sharkxL={1000,1300};
  int [] sharky2={140,480};
  int [] sharkw2={321,420};
  int [] sharkh2={107,130};
  int [] hookX={210,840};
  int [] hookY={-400,-400};
  
  //Player 1 Fish
  int [] x2={250,250,250,250};
  int [] y2={250,250,250,250};
  int [] fishw2={60,60,60,60};
  int [] fishh2={20,20,20,20};
  
  //Player 2 Fish
  int [] x3={650,650,650,650};
  int [] y3={250,250,250,250};
  int [] fishw3={60,60,60,60};
  int [] fishh3={20,20,20,20};
  
  int [][]resetMulti= {{250,250,250,250},
    {250,250,250,250},
    {60,60,60,60},
    {20,20,20,20},
    {650,650,650,650},
    {250,250,250,250},
    {60,60,60,60},
    {20,20,20,20}};
  
  BufferedImage Mainmenu =null;
  BufferedImage Highscore =null;
  BufferedImage Records =null;
  BufferedImage Difficulty =null;
  BufferedImage Characters =null;
  BufferedImage Background1 =null;
  BufferedImage FishyR =null;
  BufferedImage FishyL =null;
  BufferedImage FishyUp =null;
  BufferedImage EnemyFishL =null;
  BufferedImage EnemyFishR =null;
  BufferedImage SharkCharR =null;
  BufferedImage SharkCharL =null;
  BufferedImage SharkCharUp =null;
  BufferedImage MooneyFishR =null;
  BufferedImage MooneyFishL =null;
  BufferedImage MooneyFishUp =null;
  BufferedImage GameOver =null;
  BufferedImage WinScreen =null;
  BufferedImage HowToPlay1 =null;
  BufferedImage HowToPlay2 =null;
  BufferedImage EnemySharkL =null;
  BufferedImage EnemySharkR =null;
  BufferedImage TheHook =null;
  BufferedImage UnlockShark =null;
  BufferedImage UnlockMooney =null;
  BufferedImage SelectedChar =null;
  BufferedImage livesCount =null;
  BufferedImage P1 =null;
  BufferedImage P2 =null;
  BufferedImage Fishy2R =null;
  BufferedImage Fishy2L =null;
  BufferedImage P1Win =null;
  BufferedImage P2Win =null;
  BufferedImage HSMulti =null;
  
  public static void main(String[ ] args) 
  {
    new Fishy();
  }  
  
  public Fishy(){
    
    try
    {
      Mainmenu = ImageIO.read(new File("Title ScreenV2.jpg"));
      Highscore = ImageIO.read(new File("Highscore.jpg"));
      Records = ImageIO.read(new File("Records.jpg"));
      Difficulty = ImageIO.read(new File("Difficulty.jpg"));
      Characters = ImageIO.read(new File("Characters.jpg"));
      Background1 = ImageIO.read(new File("Background1.png"));
      FishyR = ImageIO.read(new File("FishyCharR.png"));
      FishyL = ImageIO.read(new File("FishyCharL.png"));
      FishyUp = ImageIO.read(new File("FishyCharUp.png"));
      EnemyFishR = ImageIO.read(new File("EnemyFishR.png"));
      EnemyFishL = ImageIO.read(new File("EnemyFishL.png"));
      SharkCharR = ImageIO.read(new File("SharkCharR.png"));
      SharkCharL = ImageIO.read(new File("SharkCharL.png"));
      SharkCharUp = ImageIO.read(new File("SharkCharUp.png"));
      MooneyFishR = ImageIO.read(new File("MooneyFishR.png"));
      MooneyFishL = ImageIO.read(new File("MooneyFishL.png"));
      MooneyFishUp = ImageIO.read(new File("MooneyFishUp.png"));
      GameOver = ImageIO.read(new File("GameOverV2.jpg"));
      WinScreen = ImageIO.read(new File("WinScreen.jpg"));
      HowToPlay1 = ImageIO.read(new File("HowToPlay1.jpg"));
      HowToPlay2 = ImageIO.read(new File("HowToPlay2.jpg"));
      EnemySharkR = ImageIO.read(new File("SharkR.png"));
      EnemySharkL = ImageIO.read(new File("SharkL.png"));
      TheHook = ImageIO.read(new File("TheHook.png"));
      UnlockShark = ImageIO.read(new File("UnlockS.png"));
      UnlockMooney = ImageIO.read(new File("UnlockM.png"));
      SelectedChar = ImageIO.read(new File("SelectedChar.png"));
      livesCount = ImageIO.read(new File("livesCount.png"));
      P1 = ImageIO.read(new File("P1.png"));
      P2 = ImageIO.read(new File("P2.png"));
      Fishy2R = ImageIO.read(new File("FishyChar2R.png"));
      Fishy2L = ImageIO.read(new File("FishyChar2L.png"));
      P1Win = ImageIO.read(new File("P1Win.jpg"));
      P2Win = ImageIO.read(new File("P2Win.jpg"));
      HSMulti = ImageIO.read(new File("HSMulti.jpg"));
    }
    catch(IOException e)
    {
      System.out.println("Error");
    } 
    
    p1=new JPanel();
    
    drawpanel1=new MyDrawPanel();
    p1.setLayout(new GridLayout(1,1));
    p1.add(drawpanel1);
    
    this.add(p1);
    
    this.addMouseListener(this);
    
    this.setSize(900,700);
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
      if (mouseX>=380 && mouseX<=520 && mouseY>=200 && mouseY<230)
      {
        game=1;
      }
      if (mouseX>=380 && mouseX<=520 && mouseY>=260 && mouseY<300)
      {
        game=2;
      }
      if (mouseX>=380 && mouseX<=520 && mouseY>=330 && mouseY<360)
      {
        game=3;
      }
      if (mouseX>=380 && mouseX<=520 && mouseY>=400 && mouseY<425)
      {
        game=4;
      }
      if (mouseX>=380 && mouseX<=520 && mouseY>=470 && mouseY<500)
      {
        System.exit(0);
      }
      if (mouseX>=810 && mouseX<=860 && mouseY>=40 && mouseY<110)
      {
        game=5;
      }
    }
    else if(game==4)
    {
      if (mouseX>=810 && mouseX<=860 && mouseY>=40 && mouseY<110)
      {
        game=6;
      }
      else if (mouseX>=50 && mouseX<=300 && mouseY>=190 && mouseY<550)
      {
        difficulty=1;
      }
      else if (mouseX>=320 && mouseX<=570 && mouseY>=190 && mouseY<550)
      {
        difficulty=2;
      }
      else if (mouseX>=600 && mouseX<=850 && mouseY>=190 && mouseY<550)
      {
        difficulty=3;
      }
    }
    if(game==3||game==4||game==5)
    {
      if (mouseX>=10 && mouseX<=110 && mouseY>=40 && mouseY<100)
      {
        game=0;
      }
    }
    if(game==3)
    {
      if (mouseX>=810 && mouseX<=860 && mouseY>=40 && mouseY<110)
      {
        game=12;
      }
    }
    else if(game==12)
    {
      if (mouseX>=10 && mouseX<=110 && mouseY>=40 && mouseY<100)
      {
        game=3;
      }
      else if (mouseX>=810 && mouseX<=860 && mouseY>=40 && mouseY<110)
      {
        game=13;
      }
    }
     else if(game==13)
    {
      if (mouseX>=10 && mouseX<=110 && mouseY>=40 && mouseY<100)
      {
        game=12;
      }
     }
    if (game==6)
    {
      if (mouseX>=10 && mouseX<=110 && mouseY>=40 && mouseY<100)
      {
        game=4;
      }
      else if (mouseX>=200 && mouseX<=800 && mouseY>=290 && mouseY<320)
      {
        character=1;
        speed=1;
        lives=1;
      }
      else if (mouseX>=200 && mouseX<=800 && mouseY>=430 && mouseY<460)
      {
        if (unlockS==1)
        {
          character=2;
          speed=2;
          lives=2;
        }
      }
      else if (mouseX>=200 && mouseX<=800 && mouseY>=575 && mouseY<605)
      {
        if (unlockM==1)
        {
          character=3;
          speed=3;
          lives=3;
        }
      }
    }
    if (game==5)
    {
      if (mouseX>=10 && mouseX<=110 && mouseY>=40 && mouseY<100)
      {
        game=0;
      }
      if (mouseX>=810 && mouseX<=860 && mouseY>=40 && mouseY<110)
      {
        game=9;
      }
    }
    if (game==9)
    {
      if (mouseX>=10 && mouseX<=110 && mouseY>=40 && mouseY<100)
      {
        game=5;
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
      if (hooked==0)
      {
        if (keyCode == KeyEvent.VK_LEFT) 
        {
          direction=2;
          dx = -speed;
        }
        
        if (keyCode == KeyEvent.VK_RIGHT) 
        {
          direction=1;
          dx = speed;
        }
        
        if (keyCode == KeyEvent.VK_UP) 
        {
          dy = -speed;
        }
        
        if (keyCode == KeyEvent.VK_DOWN) 
        {
          dy = speed;
        }
      }
    }
    if (game==2)
    {
      if (keyCode == KeyEvent.VK_A) 
      {
        direction=2;
        mdx = -speed;
      }
      
      if (keyCode == KeyEvent.VK_D) 
      {
        direction=1;
        mdx = speed;
      }
      
      if (keyCode == KeyEvent.VK_W) 
      {
        mdy = -speed;
      }
      
      if (keyCode == KeyEvent.VK_S) 
      {
        mdy = speed;
      }
      
      if (keyCode == KeyEvent.VK_LEFT) 
      {
        direction2=2;
        dx = -speed;
      }
      
      if (keyCode == KeyEvent.VK_RIGHT) 
      {
        direction2=1;
        dx = speed;
      }
      
      if (keyCode == KeyEvent.VK_UP) 
      {
        dy = -speed;
      }
      
      if (keyCode == KeyEvent.VK_DOWN) 
      {
        dy = speed;
      }
    }
    if (game==7 || game==8 || game== 10 || game==11)
    {
      if (keyCode == KeyEvent.VK_ENTER) 
      {
        dx=0;
        dy=0;
        dx2=1;
        score=0;
        goY=-100;
        hooked=0;
        direction=1;
        mdx=0;
        mdy=0;
        
        if (character==1)
        {
          lives=1;
        }
        if (character==2)
        {
          lives=2;
        }
        if (character==3)
        {
          lives=3;
        }
        
        if (fishAte>fa)
        {
          fa=fishAte;
        }
        if (sharkAte>sa)
        {
          sa=sharkAte;
        }
        
        for (int h=0;h<4;h++)
        {
          enemiesxR[h]=reset[0][h];
          enemiesy1[h]=reset[1][h];
          enemiesw[h]=reset[2][h];
          enemiesh[h]=reset[3][h];
          enemiesxL[h]=reset[4][h];
          enemiesy2[h]=reset[5][h];
          enemiesw2[h]=reset[6][h];
          enemiesh2[h]=reset[7][h];
          x[h]=reset[8][h];
          y[h]=reset[9][h];
          fishw[h]=reset[10][h];
          fishh[h]=reset[11][h];
          livesX[h]=reset[12][h];
          
          x2[h]=resetMulti[0][h];
          y2[h]=resetMulti[1][h];
          fishw2[h]=resetMulti[2][h];
          fishh2[h]=resetMulti[3][h];
          x3[h]=resetMulti[4][h];
          y3[h]=resetMulti[5][h];
          fishw3[h]=resetMulti[6][h];
          fishh3[h]=resetMulti[7][h];
        }
        for (int h=0;h<2;h++)
        {
          sharkxR[h]=resetSharks[0][h];
          sharky1[h]=resetSharks[1][h];
          sharkw[h]=resetSharks[2][h];
          sharkh[h]=resetSharks[3][h];
          sharkxL[h]=resetSharks[4][h];
          sharky2[h]=resetSharks[5][h];
          sharkw2[h]=resetSharks[6][h];
          sharkh2[h]=resetSharks[7][h];
          hookX[h]=resetSharks[8][h];
          hookY[h]=resetSharks[9][h];
        }
        fishAte=0;
        sharkAte=0;
        if (whoWon==1)
        {
          p1s++;
        }
        else if (whoWon==2)
        {
          p2s++;
        }
        whoWon=0;
        gp++;
        game=0;
      }
    }
  }
  
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    //Single Player
    if (game==1)
    {
      if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) 
      {
        dy=0;
      }
      if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) 
      {
        dx=0;
      }
    }
    //Multiplayer
    if (game==2)
    {
      if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) 
      {
        dy=0;
      }
      if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) 
      {
        dx=0;
      }
      if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D)
      {
        mdx=0;
      }
      if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S) 
      {
        mdy=0;
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
      
      if (game == 1)
      { 
        for (int a=0;a<4;a++)
        {
          enemiesxR[a]+=dx2;
          enemiesxL[a]-=dx2;
          if (enemiesxR[a]>910)
          {
            enemiesxR[a]=-310;
            enemiesy1[a]=(int)(Math.random()*600) + 10;
            
            enemiesw[a]=(int)(Math.random()*300) + 40;
            enemiesh[a]=enemiesw[a]/3;
          }
          if (enemiesxL[a]<-310)
          {
            enemiesxL[a]=1000;
            enemiesy2[a]=(int)(Math.random()*600) + 10;
            enemiesw2[a]=(int)(Math.random()*210) + 30;
            enemiesh2[a]=enemiesw2[a]/3;
          }
        }
        for (int a=0;a<2;a++)
        {
          if (difficulty==2|| difficulty==3)
          {
            sharkxR[a]+=sharkdx;
            sharkxL[a]-=sharkdx;
            if (sharkxR[a]>910)
            {
              sharkxR[a]=-560;
              sharky1[a]=(int)(Math.random()*500) + 10;
              
              sharkw[a]=(int)(Math.random()*400) + 150;
              sharkh[a]=sharkw[a]/3;
            }
            if (sharkxL[a]<-560)
            {
              sharkxL[a]=1000;
              sharky2[a]=(int)(Math.random()*500) + 10;
              sharkw2[a]=(int)(Math.random()*400) + 180;
              sharkh2[a]=sharkw2[a]/3;
            }
          }
          if (difficulty == 3)
          {
            if (fishw[a]<300)
            {
              if (hookY[a]<0 && hookFlag==0)
              {
                hookdy=3;
                hookFlag=1;
              }
            }
            if (hookY[a]>0 && hookFlag==1)
            {
              hookdy=-3;
            }
            if (hookY[a]<-500)
            {
              hookFlag=0;
              hookX[a]=(int)(Math.random()*950) + -50;
            }
            hookY[a]+=hookdy;
          }
        }
      }
      //Single Player
      if (game==1)
      {
        for (int a=0;a<4;a++)
        {
          x[a]+=dx;
          y[a]+=dy;
        }
      }
      //Multiplayer
      if (game==2)
      {
        for (int a=0;a<4;a++)
        {
          x2[a]+=mdx;
          y2[a]+=mdy;
          x3[a]+=dx;
          y3[a]+=dy;
        }
        for (int a=0;a<4;a++)
        {
          enemiesxR[a]+=dx2;
          enemiesxL[a]-=dx2;
          if (enemiesxR[a]>910)
          {
            enemiesxR[a]=-310;
            enemiesy1[a]=(int)(Math.random()*600) + 10;
            
            enemiesw[a]=(int)(Math.random()*300) + 40;
            enemiesh[a]=enemiesw[a]/3;
          }
          if (enemiesxL[a]<-310)
          {
            enemiesxL[a]=1000;
            enemiesy2[a]=(int)(Math.random()*600) + 10;
            enemiesw2[a]=(int)(Math.random()*210) + 30;
            enemiesh2[a]=enemiesw2[a]/3;
          }
        }
      }
      if (game == 7)
      { 
        deltagoY=5;
        if (goY>180)
        {
          deltagoY=0;
        }
        goY+=deltagoY;
      }
      repaint();
      Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
      
    }
  }
  
  class MyDrawPanel extends JPanel {
    
    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
      
      if (character==1)
      {
        speed=1;
      }
      if (character==2)
      {
        speed=2;
      }
      if (character==3)
      {
        speed=3;
      }
      //Main Menu
      if (game==0)
      {
        g2.drawImage(Mainmenu,0,0,900,700,null);
      }
      
      //Themes
      if (game==1)
      {
        g2.drawImage(Background1,0,0,900,700,null);
      }
      
      //Game screen
      if (game==1)
      {
        for (int a=0;a<4;a++)
        {
          //Borders
          if (fishw[a]<750)
          {
            if (x[a]<0)
            {
              x[a]=900-fishw[a];
            }
            if (x[a]>900-fishw[a])
            {
              x[a]=0;
            }
            if (y[a]<0)
            {
              y[a]=0;
            }
            if (y[a]>650-fishh[a])
            {
              y[a]=650-fishh[a];
            }
          }
        }
        //draw enemies
        for (int a=0;a<4;a++)
        {
          g2.drawImage(EnemyFishR,enemiesxR[a],enemiesy1[a],enemiesw[a],enemiesh[a],null);
          g2.drawImage(EnemyFishL,enemiesxL[a],enemiesy2[a],enemiesw2[a],enemiesh2[a],null);
        }
        for (int a=0;a<2;a++)
        {
          if (difficulty==2||difficulty==3)
          {
            g2.drawImage(EnemySharkR,sharkxR[a],sharky1[a],sharkw[a],sharkh[a],null);
            g2.drawImage(EnemySharkL,sharkxL[a],sharky2[a],sharkw2[a],sharkh2[a],null);
          }
          if (difficulty==3)
          {
            //hook
            g2.drawImage(TheHook,hookX[a],hookY[a],30,400,null);
          }
        }
        for (int a=0;a<4;a++)
        {
          //Right
          if (hooked==0)
          {
            if (x[a] > enemiesxR[a] && x[a] < enemiesxR[a]+enemiesw[a] && y[a] > enemiesy1[a] +10 && y[a] < (enemiesy1[a]+enemiesh[a])+10)
            {
              if (fishw[a] < enemiesw[a])
              {
                lives--;
                for (int h=0;h<4;h++)
                {
                  x[h]=370;
                  y[h]=250;
                }
              }
            }
            else if (x[a]+fishw[a] > enemiesxR[a] && x[a]+fishw[a] < enemiesxR[a]+enemiesw[a] && y[a] > enemiesy1[a]+10 && y[a] < (enemiesy1[a]+enemiesh[a])+10)
            {
              if (fishw[a] < enemiesw[a])
              {
                lives--;
                for (int h=0;h<4;h++)
                {
                  x[h]=370;
                  y[h]=250;
                }
              }
            }
            else if (enemiesxR[a] > x[a] && enemiesxR[a] < x[a]+fishw[a] && enemiesy1[a] > y[a] && enemiesy1[a] < y[a]+fishh[a])
            {
              if (fishw[a] > enemiesw[a])
              {
                whichEnemy=a;
                enemiesxR[whichEnemy]=-310;
                enemiesy1[whichEnemy]=(int)(Math.random()*600) + 10;
                fishAte++;
                Tfa++;
                for (int h=0;h<4;h++)
                {
                  fishw[h]+=5;
                  fishh[h]=fishw[h]/3;
                }
                score+=50;
              }
            }
            else if (enemiesxR[a]+enemiesw[a] > x[a] && enemiesxR[a]+enemiesw[a] < x[a]+fishw[a] && enemiesy1[a] > y[a] && enemiesy1[a] < y[a]+fishh[a])
            {
              if (fishw[a] > enemiesw[a])
              {
                whichEnemy=a;
                enemiesxR[whichEnemy]=-310;
                enemiesy1[whichEnemy]=(int)(Math.random()*600) + 10;
                fishAte++;
                Tfa++;
                for (int h=0;h<4;h++)
                {
                  fishw[h]+=5;
                  fishh[h]=fishw[h]/3;
                }
                score+=50;
              }
            }
            
            //Left
            else if (x[a] > enemiesxL[a] && x[a] < enemiesxL[a]+enemiesw2[a] && y[a] > enemiesy2[a] +10 && y[a] < (enemiesy2[a]+enemiesh2[a])+10)
            {
              if (fishw[a] < enemiesw2[a])
              {
                lives--;
                for (int h=0;h<4;h++)
                {
                  x[h]=370;
                  y[h]=250;
                }
              }
            }
            else if (x[a]+ fishw[a] > enemiesxL[a] && x[a]+ fishw[a]  < enemiesxL[a]+enemiesw2[a] && y[a] > enemiesy2[a]+10 && y[a] < (enemiesy2[a]+enemiesh2[a])+10)
            {
              if (fishw[a] < enemiesw2[a])
              {
                lives--;
                for (int h=0;h<4;h++)
                {
                  x[h]=370;
                  y[h]=250;
                }
              }
            }
            else if (enemiesxL[a] > x[a] && enemiesxL[a] < x[a]+fishw[a] && enemiesy2[a] > y[a] && enemiesy2[a] < y[a]+fishh[a])
            {
              if (fishw[a] > enemiesw2[a])
              {
                whichEnemy=a;
                enemiesxL[whichEnemy]=910;
                enemiesy2[whichEnemy]=(int)(Math.random()*600) + 10;
                fishAte++;
                Tfa++;
                for (int h=0;h<4;h++)
                {
                  fishw[h]+=5;
                  fishh[h]=fishw[h]/3;
                }
                score+=50;
              }
            }
            else if (enemiesxL[a]+enemiesw2[a] > x[a] && enemiesxL[a]+enemiesw2[a] < x[a]+fishw[a] && enemiesy2[a] > y[a] && enemiesy2[a] < y[a]+fishh[a])
            {
              if (fishw[a] > enemiesw2[a])
              {
                whichEnemy=a;
                enemiesxR[whichEnemy]=910;
                enemiesy2[whichEnemy]=(int)(Math.random()*600) + 10;
                Tfa++;
                fishAte++;
                for (int h=0;h<4;h++)
                {
                  fishw[h]+=5;
                  fishh[h]=fishw[h]/3;
                }
                score+=50;
              }
            }
          }
        }
        //Sharks
        for (int a=0;a<2;a++)
        {
          if (difficulty==2||difficulty==3)
          {
            if( hooked==0)
            {
              //Right
              if (x[a] > sharkxR[a] -20 && x[a] < sharkxR[a]+sharkw[a] && y[a] > sharky1[a]+20 && y[a] < (sharky1[a]+sharkh[a])-20)
              {
                if (fishw[a] < sharkw[a])
                {
                  lives--;
                  for (int h=0;h<4;h++)
                  {
                    x[h]=370;
                    y[h]=250;
                  }
                }
              }
              else if (x[a]+fishw[a] > sharkxR[a] -20 && x[a]+fishw[a] < sharkxR[a]+sharkw[a] && y[a] > sharky1[a]+20 && y[a] < (sharky1[a]+sharkh[a])-20)
              {
                if (fishw[a] < sharkw[a])
                {
                  lives--;
                  for (int h=0;h<4;h++)
                  {
                    x[h]=370;
                    y[h]=250;
                  }
                }
              }
              else if (sharkxR[a] > x[a] && sharkxR[a] < x[a]+fishw[a] && sharky1[a] > y[a] && sharky1[a] < y[a]+fishh[a])
              {
                if (fishw[a] > sharkw[a])
                {
                  whichEnemy=a;
                  sharkxR[whichEnemy]=-310;
                  sharky1[whichEnemy]=(int)(Math.random()*500) + 10;
                  Tsa++;
                  sharkAte++;
                  unlockS=1;
                  for (int h=0;h<4;h++)
                  {
                    fishw[h]+=5;
                    fishh[h]=fishw[h]/3;
                  }
                  score+=50;
                }
              }
              else if (sharkxR[a]+sharkw[a] > x[a] && sharkxR[a]+sharkw[a] < x[a]+fishw[a] && sharky1[a] > y[a] && sharky1[a] < y[a]+fishh[a])
              {
                if (fishw[a] > sharkw[a])
                {
                  whichEnemy=a;
                  sharkxR[whichEnemy]=-310;
                  sharky1[whichEnemy]=(int)(Math.random()*500) + 10;
                  Tsa++;
                  sharkAte++;
                  unlockS=1;
                  for (int h=0;h<4;h++)
                  {
                    fishw[h]+=5;
                    fishh[h]=fishw[h]/3;
                  }
                  score+=50;
                }
              }
              
              //Left
              else if (x[a] > sharkxL[a]+20 && x[a] < sharkxL[a]+sharkw2[a] && y[a] > sharky2[a] +20 && y[a] < (sharky2[a]+sharkh2[a])-20)
              {
                if (fishw[a] < sharkw2[a])
                {
                  lives--;
                  for (int h=0;h<4;h++)
                  {
                    x[h]=370;
                    y[h]=250;
                  }
                }
              }
              else if (x[a]+ fishw[a] > sharkxL[a] +20 && x[a]+ fishw[a]  < sharkxL[a]+sharkw2[a] && y[a] >sharky2[a] +20 && y[a] < (sharky2[a]+sharkh2[a])-20)
              {
                if (fishw[a] < sharkw2[a])
                {
                  lives--;
                  for (int h=0;h<4;h++)
                  {
                    x[h]=370;
                    y[h]=250;
                  }
                }
              }
              else if (sharkxL[a] > x[a] && sharkxL[a] < x[a]+fishw[a] && sharky2[a] > y[a] && sharky2[a] < y[a]+fishh[a])
              {
                if (fishw[a] > sharkw2[a])
                {
                  whichEnemy=a;
                  sharkxL[whichEnemy]=910;
                  sharky2[whichEnemy]=(int)(Math.random()*500) + 10;
                  Tsa++;
                  sharkAte++;
                  unlockS=1;
                  for (int h=0;h<4;h++)
                  {
                    fishw[h]+=5;
                    fishh[h]=fishw[h]/3;
                  }
                  score+=50;
                }
              }
              else if (sharkxL[a]+sharkw2[a] > x[a] && sharkxL[a]+sharkw2[a] < x[a]+fishw[a] && sharky2[a] > y[a] && sharky2[a] < y[a]+fishh[a])
              {
                if (fishw[a] > sharkw2[a])
                {
                  whichEnemy=a;
                  sharkxR[whichEnemy]=910;
                  sharky2[whichEnemy]=(int)(Math.random()*500) + 10;
                  Tsa++;
                  sharkAte++;
                  unlockS=1;
                  for (int h=0;h<4;h++)
                  {
                    fishw[h]+=5;
                    fishh[h]=fishw[h]/3;
                  }
                  score+=50;
                }
              }
            }
          }
          
          
          //Hooks
          if (difficulty==3)
          {
            if (hookX[a] > x[a] && hookX[a] < x[a]+fishw[a] && hookY[a]+370 > y[a] && hookY[a]+370 < y[a]+fishh[a])
            {
              hookdy=-3;
              hooked=1;
              whichEnemy=a;
            }
            else if (hookX[a] + 30 > x[a] && hookX[a] + 30 < x[a]+fishw[a] && hookY[a]+400 > y[a] && hookY[a]+400 < y[a]+fishh[a])
            {
              hookdy=-3;
              hooked=1;
              whichEnemy=a;
            }
          }
          if (hooked==1)
          {
            for (int h=0;h<4;h++)
            {
              y[h]=hookY[whichEnemy]+400;
              x[h]=hookX[whichEnemy]+15;
              direction=3;
            }
            if (y[a]<0)
            {
              hooked=3;
              lives--;
              dbh++;
              direction=1;
            }
          }
          if (y[a]<0 && hooked==3)
          {
            for (int h=0;h<4;h++)
            {
              x[h]=370;
              y[h]=250;
              hooked=0;
            }
          }
        }
        for (int a=0;a<4;a++)
        {
          if (character==1)
          {
            if (direction==1)
            {
              g2.drawImage(FishyR,x[a],y[a],fishw[a],fishh[a],null);
            }
            else if (direction==2)
            {
              g2.drawImage(FishyL,x[a],y[a],fishw[a],fishh[a],null);
            }
            else if (direction==3)
            {
              g2.drawImage(FishyUp,x[a],y[a],fishh[a],fishw[a],null);
            }
          }
          
         else if (character==2)
          {
            if (direction==1)
            {
              g2.drawImage(SharkCharR,x[a],y[a],fishw[a],fishh[a],null);
            }
            else if (direction==2)
            {
              g2.drawImage(SharkCharL,x[a],y[a],fishw[a],fishh[a],null);
            }
            else if (direction==3)
            {
              g2.drawImage(SharkCharUp,x[a],y[a],fishh[a],fishw[a],null);
            }
          }
          
          else if (character==3)
          {
            if (direction==1)
            {
              g2.drawImage(MooneyFishR,x[a],y[a],fishw[a],fishh[a],null);
            }
            else if (direction==2)
            {
              g2.drawImage(MooneyFishL,x[a],y[a],fishw[a],fishh[a],null);
            }
            else if (direction==3)
            {
              g2.drawImage(MooneyFishUp,x[a],y[a],fishh[a],fishw[a],null);
            }
          }
        }
        
        //Lives and Score Output
        g2.setFont(new Font("Comic Sans MS",1,30));
        g2.setColor(Color.BLUE);
        g2.drawString("Lives:",10,660);
        g2.drawString(""+score,450,40);
        for (int a=0;a<lives;a++)
        {
          g2.drawImage(livesCount,livesX[a],630,50,40,null);
        }
        
        //Win Condition
        if (score>scorecap)
        {
          game=8;
          if (difficulty==3)
          {
            unlockM=1;
          }
        }
        //Lose Condition
        if (lives<=0)
        {
          game=7;
        }
        if (difficulty==1)
        {
          if (score>ehs)
          {
            ehs=score;
          }
        }
        if (difficulty==2)
        {
          if (score>mhs)
          {
            mhs=score;
          }
        }
        if (difficulty==3)
        {
          if (score>hhs)
          {
            hhs=score;
          }
        }
      }
      
      //Multiplayer
      if (game==2)
      {
        speed=1;
        g2.drawImage(Background1,0,0,900,700,null);
        //Draw P1 and P2
        for (int a=0;a<4;a++)
        {
          g2.drawImage(P1,x2[a]+20,y2[a]-30,50,50,null);
          g2.drawImage(P2,x3[a]+20,y3[a]-30,50,50,null);
          if (direction==1)
          {
            g2.drawImage(FishyR,x2[a],y2[a],fishw2[a],fishh2[a],null);
          }
          else if (direction==2)
          {
            g2.drawImage(FishyL,x2[a],y2[a],fishw2[a],fishh2[a],null);
          }
          if (direction2==1)
          {
            g2.drawImage(Fishy2R,x3[a],y3[a],fishw3[a],fishh3[a],null);
          }
          else if (direction2==2)
          {
            g2.drawImage(Fishy2L,x3[a],y3[a],fishw3[a],fishh3[a],null);
          }
          //Borders
          //P1
          if (fishw2[a]<750)
          {
            if (x2[a]<0)
            {
              x2[a]=900-fishw2[a];
            }
            if (x2[a]>900-fishw2[a])
            {
              x2[a]=0;
            }
            if (y2[a]<0)
            {
              y2[a]=0;
            }
            if (y2[a]>650-fishh2[a])
            {
              y2[a]=650-fishh2[a];
            }
          }
          //P2
          if (fishw3[a]<750)
          {
            if (x3[a]<0)
            {
              x3[a]=900-fishw3[a];
            }
            if (x3[a]>900-fishw3[a])
            {
              x3[a]=0;
            }
            if (y3[a]<0)
            {
              y3[a]=0;
            }
            if (y3[a]>650-fishh3[a])
            {
              y3[a]=650-fishh3[a];
            }
          }
          //Enemy Fish
          g2.drawImage(EnemyFishR,enemiesxR[a],enemiesy1[a],enemiesw[a],enemiesh[a],null);
          g2.drawImage(EnemyFishL,enemiesxL[a],enemiesy2[a],enemiesw2[a],enemiesh2[a],null);
          //P1
          if (x2[a] > enemiesxR[a] && x2[a] < enemiesxR[a]+enemiesw[a] && y2[a] > enemiesy1[a] +10 && y2[a] < (enemiesy1[a]+enemiesh[a])+10)
          {
            if (fishw2[a] < enemiesw[a])
            {
              for (int h=0;h<4;h++)
              {
                fishw2[h]=60;
                fishh2[h]=fishw2[h]/3;
                x2[h]=250;
                y2[h]=250;
              }
            }
          }
          else if (x2[a]+fishw2[a] > enemiesxR[a] && x2[a]+fishw2[a] < enemiesxR[a]+enemiesw[a] && y2[a] > enemiesy1[a]+10 && y2[a] < (enemiesy1[a]+enemiesh[a])+10)
          {
            if (fishw2[a] < enemiesw[a])
            {
              for (int h=0;h<4;h++)
              {
                fishw2[h]=60;
                fishh2[h]=fishw2[h]/3;
                x2[h]=250;
                y2[h]=250;
              }
            }
          }
          else if (enemiesxR[a] > x2[a] && enemiesxR[a] < x2[a]+fishw2[a] && enemiesy1[a] > y2[a] && enemiesy1[a] < y2[a]+fishh2[a])
          {
            if (fishw2[a] > enemiesw[a])
            {
              whichEnemy=a;
              enemiesxR[whichEnemy]=-310;
              enemiesy1[whichEnemy]=(int)(Math.random()*600) + 10;
              for (int h=0;h<4;h++)
              {
                fishw2[h]+=5;
                fishh2[h]=fishw2[h]/3;
              }
            }
          }
          else if (enemiesxR[a]+enemiesw[a] > x2[a] && enemiesxR[a]+enemiesw[a] < x2[a]+fishw2[a] && enemiesy1[a] > y2[a] && enemiesy1[a] < y2[a]+fishh2[a])
          {
            if (fishw2[a] > enemiesw[a])
            {
              whichEnemy=a;
              enemiesxR[whichEnemy]=-310;
              enemiesy1[whichEnemy]=(int)(Math.random()*600) + 10;
              for (int h=0;h<4;h++)
              {
                fishw2[h]+=5;
                fishh2[h]=fishw2[h]/3;
              }
            }
          }
          
          //Left
          else if (x2[a] > enemiesxL[a] && x2[a] < enemiesxL[a]+enemiesw2[a] && y2[a] > enemiesy2[a] +10 && y2[a] < (enemiesy2[a]+enemiesh2[a])+10)
          {
            if (fishw2[a] < enemiesw2[a])
            {
              for (int h=0;h<4;h++)
              {
                fishw2[h]=60;
                fishh2[h]=fishw2[h]/3;
                x2[h]=250;
                y2[h]=250;
              }
            }
          }
          else if (x2[a]+ fishw2[a] > enemiesxL[a] && x2[a]+ fishw2[a]  < enemiesxL[a]+enemiesw2[a] && y2[a] > enemiesy2[a]+10 && y2[a] < (enemiesy2[a]+enemiesh2[a])+10)
          {
            if (fishw2[a] < enemiesw2[a])
            {
              for (int h=0;h<4;h++)
              {
                fishw2[h]=60;
                fishh2[h]=fishw2[h]/3;
                x2[h]=250;
                y2[h]=250;
              }
            }
          }
          else if (enemiesxL[a] > x2[a] && enemiesxL[a] < x2[a]+fishw2[a] && enemiesy2[a] > y2[a] && enemiesy2[a] < y2[a]+fishh2[a])
          {
            if (fishw2[a] > enemiesw2[a])
            {
              whichEnemy=a;
              enemiesxL[whichEnemy]=910;
              enemiesy2[whichEnemy]=(int)(Math.random()*600) + 10;
              for (int h=0;h<4;h++)
              {
                fishw2[h]+=5;
                fishh2[h]=fishw2[h]/3;
              }
            }
          }
          else if (enemiesxL[a]+enemiesw2[a] > x2[a] && enemiesxL[a]+enemiesw2[a] < x2[a]+fishw2[a] && enemiesy2[a] > y2[a] && enemiesy2[a] < y2[a]+fishh2[a])
          {
            if (fishw2[a] > enemiesw2[a])
            {
              whichEnemy=a;
              enemiesxR[whichEnemy]=910;
              enemiesy2[whichEnemy]=(int)(Math.random()*600) + 10;
              for (int h=0;h<4;h++)
              {
                fishw2[h]+=5;
                fishh2[h]=fishw2[h]/3;
              }
            }
          }
          //P2
          if (x3[a] > enemiesxR[a] && x3[a] < enemiesxR[a]+enemiesw[a] && y3[a] > enemiesy1[a] +10 && y3[a] < (enemiesy1[a]+enemiesh[a])+10)
          {
            if (fishw3[a] < enemiesw[a])
            {
              for (int h=0;h<4;h++)
              {
                fishw3[h]=60;
                fishh3[h]=fishw3[h]/3;
                x3[h]=650;
                y3[h]=250;
              }
            }
          }
          else if (x3[a]+fishw3[a] > enemiesxR[a] && x3[a]+fishw3[a] < enemiesxR[a]+enemiesw[a] && y3[a] > enemiesy1[a]+10 && y3[a] < (enemiesy1[a]+enemiesh[a])+10)
          {
            if (fishw3[a] < enemiesw[a])
            {
              for (int h=0;h<4;h++)
              {
                fishw3[h]=60;
                fishh3[h]=fishw3[h]/3;
                x3[h]=650;
                y3[h]=250;
              }
            }
          }
          else if (enemiesxR[a] > x3[a] && enemiesxR[a] < x3[a]+fishw3[a] && enemiesy1[a] > y3[a] && enemiesy1[a] < y3[a]+fishh3[a])
          {
            if (fishw3[a] > enemiesw[a])
            {
              whichEnemy=a;
              enemiesxR[whichEnemy]=-310;
              enemiesy1[whichEnemy]=(int)(Math.random()*600) + 10;
              for (int h=0;h<4;h++)
              {
                fishw3[h]+=5;
                fishh3[h]=fishw3[h]/3;
              }
            }
          }
          else if (enemiesxR[a]+enemiesw[a] > x3[a] && enemiesxR[a]+enemiesw[a] < x3[a]+fishw3[a] && enemiesy1[a] > y3[a] && enemiesy1[a] < y3[a]+fishh3[a])
          {
            if (fishw3[a] > enemiesw[a])
            {
              whichEnemy=a;
              enemiesxR[whichEnemy]=-310;
              enemiesy1[whichEnemy]=(int)(Math.random()*600) + 10;
              for (int h=0;h<4;h++)
              {
                fishw3[h]+=5;
                fishh3[h]=fishw3[h]/3;
              }
            }
          }
          
          //Left
          else if (x3[a] > enemiesxL[a] && x3[a] < enemiesxL[a]+enemiesw2[a] && y3[a] > enemiesy2[a] +10 && y3[a] < (enemiesy2[a]+enemiesh2[a])+10)
          {
            if (fishw3[a] < enemiesw2[a])
            {
              for (int h=0;h<4;h++)
              {
                fishw3[h]=60;
                fishh3[h]=fishw3[h]/3;
                x3[h]=650;
                y3[h]=250;
              }
            }
          }
          else if (x3[a]+ fishw3[a] > enemiesxL[a] && x3[a]+ fishw3[a]  < enemiesxL[a]+enemiesw2[a] && y3[a] > enemiesy2[a]+10 && y3[a] < (enemiesy2[a]+enemiesh2[a])+10)
          {
            if (fishw3[a] < enemiesw2[a])
            {
              for (int h=0;h<4;h++)
              {
                fishw3[h]=60;
                fishh3[h]=fishw3[h]/3;
                x3[h]=650;
                y3[h]=250;
              }
            }
          }
          else if (enemiesxL[a] > x3[a] && enemiesxL[a] < x3[a]+fishw3[a] && enemiesy2[a] > y3[a] && enemiesy2[a] < y3[a]+fishh3[a])
          {
            if (fishw3[a] > enemiesw2[a])
            {
              whichEnemy=a;
              enemiesxL[whichEnemy]=910;
              enemiesy2[whichEnemy]=(int)(Math.random()*600) + 10;
              for (int h=0;h<4;h++)
              {
                fishw3[h]+=5;
                fishh3[h]=fishw3[h]/3;
              }
            }
          }
          else if (enemiesxL[a]+enemiesw2[a] > x3[a] && enemiesxL[a]+enemiesw2[a] < x3[a]+fishw3[a] && enemiesy2[a] > y3[a] && enemiesy2[a] < y3[a]+fishh3[a])
          {
            if (fishw3[a] > enemiesw2[a])
            {
              whichEnemy=a;
              enemiesxR[whichEnemy]=910;
              enemiesy2[whichEnemy]=(int)(Math.random()*600) + 10;
              for (int h=0;h<4;h++)
              {
                fishw3[h]+=5;
                fishh3[h]=fishw3[h]/3;
              }
            }
          }
          //Player Collisions
          if (x2[a] > x3[a] && x2[a] < x3[a]+fishw3[a] && y2[a] > y3[a] && y2[a] < (y3[a]+fishh3[a]))
          {
            if (fishw2[0] > fishw3[0])
            {
              game=10;
            }
            else if (fishw2[0] < fishw3[0])
            {
              game=11;
            }
          }
          else if (x2[a]+ fishw2[a] > x3[a] && x2[a]+ fishw2[a]  < x3[a]+fishw3[a] && y2[a] > y3[a] && y2[a] < (y3[a]+fishh3[a]))
          {
            if (fishw2[0] > fishw3[0])
            {
              game=10;
            }
            else if (fishw2[0] < fishw3[0])
            {
              game=11;
            }
          }
          if (x3[a] > x2[a] && x3[a] < x2[a]+fishw2[a] && y3[a] > y2[a] && y3[a] < (y2[a]+fishh2[a]))
          {
            if (fishw3[0] > fishw2[0])
            {
              game=11;
            }
            else if (fishw3[0] < fishw2[0])
            {
              game=10;
            }
          }
          else if (x3[a]+ fishw3[a] > x2[a] && x3[a]+ fishw3[a]  < x2[a]+fishw2[a] && y3[a] > y2[a] && y3[a] < (y2[a]+fishh2[a]))
          {
            if (fishw3[0] > fishw2[0])
            {
              game=11;
            }
            else if (fishw3[0] < fishw2[0])
            {
              game=10;
            }
          }
        g2.setFont(new Font("Comic Sans MS",1,30));
        g2.setColor(Color.BLACK);
        g2.drawImage(P1,10,630,80,80,null);
        g2.drawImage(P2,780,630,80,80,null);
        g2.drawString(""+p1s,70,660);
        g2.drawString(""+p2s,840,660);
        }
      }
      
      //Highscore screen
      if (game==3)
      {
        g2.drawImage(Highscore,0,0,900,700,null);
        
        g2.setFont(new Font("Comic Sans MS",1,24));
        g2.setColor(Color.BLACK);
        g2.drawString(""+ehs,450,260);
        g2.drawString(""+mhs,450,370);
        g2.drawString(""+hhs,450,480);
      }
      //Records Screen
      if (game==12)
      {
        g2.drawImage(Records,0,0,900,700,null);
        
        g2.setFont(new Font("Comic Sans MS",1,24));
        g2.setColor(Color.BLACK);
        g2.drawString(""+gp,450,250);
        g2.drawString(""+Tfa,450,300);
        g2.drawString(""+Tsa,450,350);
        g2.drawString(""+dbh,450,400);
        g2.drawString(""+fa,450,505);
        g2.drawString(""+sa,450,555);
      }
      //Multiplayer Record Screen
      if (game==13)
      {
        g2.drawImage(HSMulti,0,0,900,700,null);
        
        g2.setFont(new Font("Comic Sans MS",1,24));
        g2.setColor(Color.BLACK);
        g2.drawString(""+p1s,450,260);
        g2.drawString(""+p2s,450,370);
      }
      
      //Options screen
      if (game==4)
      {
        g2.drawImage(Difficulty,0,0,900,700,null);
        if (difficulty==1)
        {
          g2.drawImage(SelectedChar,150,120,45,20,null);
        }
        if (difficulty==2)
        {
          g2.drawImage(SelectedChar,440,120,45,20,null);
        }
        if (difficulty==3)
        {
          g2.drawImage(SelectedChar,730,120,45,20,null);
        }
      }
      if (game==6)
      {
        g2.drawImage(Characters,0,0,900,700,null);
        if (unlockS==0)
        {
          g2.drawImage(UnlockShark,400,310,379,130,null);
        }
        if (unlockM==0)
        {
          g2.drawImage(UnlockMooney,400,460,379,130,null);
        }
        if (character==1)
        {
          g2.drawImage(SelectedChar,25,210,45,20,null);
        }
        if (character==2)
        {
          g2.drawImage(SelectedChar,25,365,45,20,null);
        }
        if (character==3)
        {
          g2.drawImage(SelectedChar,25,515,45,20,null);
        }
      }
      //How to Play Screen
      if (game==5)
      {
        g2.drawImage(HowToPlay2,0,0,900,700,null);
      }
      if (game==9)
      {
        g2.drawImage(HowToPlay1,0,0,900,700,null);
      }
      //GameOver
      if (game==7)
      {
        g2.drawImage(GameOver,-150,0,1200,700,null);
        g2.setColor(new Color(255,118,118));
        g2.setFont(new Font("Comic Sans MS",1,50));
        g2.drawString("Game Over!",100,goY);
      }
      //WinScreen
      if (game==8)
      {
        g2.drawImage(WinScreen,0,0,900,700,null);
      }
      //Multiplayer
      //P1 Win Screen
      if (game==10)
      {
        whoWon=1;
        g2.drawImage(P1Win,0,0,1200,700,null);
      }
      //P2 Win Screen
      if (game==11)
      {
        whoWon=2;
        g2.drawImage(P2Win,0,0,1200,700,null);
      }
    }
  }
}

