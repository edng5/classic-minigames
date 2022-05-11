import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.Rectangle;
import java.awt.geom.*;
import java.util.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.event.*;
import java.awt.event.MouseEvent.*;
import java.awt.event.MouseListener.*;

public class SpaceInvaders extends JFrame implements ActionListener, KeyListener, Runnable, MouseListener{
  yDrawPanel draw1=new yDrawPanel();
  Ship ship;
  Missile missile;
  Mothership mothership;
  Boss boss;
  int numAliens = 45;
  int numMissiles = 45;
  int numBMissiles = 45;
  int numShields = 24;
  Aliens [] aliens= new Aliens [numAliens];
  alienMissile [] aMissiles= new alienMissile[numMissiles];
  alienMissile [] bMissiles= new alienMissile[numBMissiles];
  Shields [] shields=new Shields[numShields];
  Thread th = new Thread (this); 
  
  int sizeX=550;
  int sizeY=700;
  int game=0, select=0;
  int rx=100,ry=370;
  
  int goy=-100, goy_speed=0;
  
  int launch=0, checkA=8, check=2, checkAM=8, counter=0, aCounter=0, livesCount=3, score=0, wave=0,deathTimer=0,trigger=0, lastShot=0,deathTimer2=0,trigger2=0,trigger3=0,trigger4=0;
  
  int gp=0,highscore=0,hwave=0,kills=0,bosses=0,counter2=0, previousAX2=0;
  
  int hsx=450;
  
  int character=1, controls=1, difficulty=1;
  
  int aTurn=0;
  int delay=0, delay2=0, startDelay=0;
  
  int bossLvl=0, bossWave=2, healthw=400;
  
  int random = (int)(Math.random()*45);
  int random2 = (int)(Math.random()*45);
  int randomx = (int)(Math.random()*400+1);
  
  Color Yellow= new Color(255,221,15);
  
  int [] lives ={430,465,500};
  
  int shieldsDown=0;
  
  int unlock=0;//Set to 1 to unlock Mooneyship
  
  Rectangle LeftBound=new Rectangle(-10,0,10,700);
  Rectangle RightBound=new Rectangle(540,0,10,700);
  Rectangle ShipBound=new Rectangle(0,590,550,20);
  
  BufferedImage SpaceMM =null;
  BufferedImage A11 =null;
  BufferedImage A12 =null;
  BufferedImage A21 =null;
  BufferedImage A22 =null;
  BufferedImage A31 =null;
  BufferedImage A32 =null;
  BufferedImage SShip =null;
  BufferedImage MShip =null;
  BufferedImage Mooneyship =null;
  BufferedImage AlienShot =null;
  BufferedImage ShipShot =null;
  BufferedImage MMAlien1 =null;
  BufferedImage MMAlien2 =null;
  BufferedImage SpaceHS =null;
  BufferedImage SpaceOption0 =null;
  BufferedImage SpaceOption1 =null;
  BufferedImage SpaceOption2 =null;
  BufferedImage SpaceOption3 =null;
  BufferedImage Shield1 =null;
  BufferedImage Shield2 =null;
  BufferedImage Shield3 =null;
  BufferedImage LCorner =null;
  BufferedImage RCorner =null;
  BufferedImage Locked =null;
  BufferedImage SpacePaused =null;
  
  public static void main(String[ ] args) 
  {
    new SpaceInvaders ();
  }  
  
  public SpaceInvaders (){
    
    try
    {
      SpaceMM = ImageIO.read(new File("SpaceMM.jpg"));
      A11 = ImageIO.read(new File("A11.png"));
      A12 = ImageIO.read(new File("A12.png"));
      A21 = ImageIO.read(new File("A21.png"));
      A22 = ImageIO.read(new File("A22.png"));
      A31 = ImageIO.read(new File("A31.png"));
      A32 = ImageIO.read(new File("A32.png"));
      SShip = ImageIO.read(new File("SShip.png"));
      MShip = ImageIO.read(new File("MShip.png"));
      Mooneyship = ImageIO.read(new File("Mooneyship.png"));
      AlienShot = ImageIO.read(new File("AlienShot.png"));
      ShipShot = ImageIO.read(new File("ShipShot.png"));
      MMAlien1 = ImageIO.read(new File("MMAlien1.png"));
      MMAlien2 = ImageIO.read(new File("MMAlien2.png"));
      SpaceHS = ImageIO.read(new File("SpaceHS.jpg"));
      SpaceOption0 = ImageIO.read(new File("SpaceOption0.jpg"));
      SpaceOption1 = ImageIO.read(new File("SpaceOption1.jpg"));
      SpaceOption2 = ImageIO.read(new File("SpaceOption2.jpg"));
      SpaceOption3 = ImageIO.read(new File("SpaceOption3.jpg"));
      Shield1 = ImageIO.read(new File("Shield1.png"));
      Shield2 = ImageIO.read(new File("Shield2.png"));
      Shield3 = ImageIO.read(new File("Shield3.png"));
      LCorner = ImageIO.read(new File("LCorner.png"));
      RCorner = ImageIO.read(new File("RCorner.png"));
      Locked = ImageIO.read(new File("Locked.png"));
      SpacePaused = ImageIO.read(new File("SpacePaused.png"));
    }
    catch(IOException e)
    {
      System.out.println("Error");
    } 
    
    //Object Creations
    ship=new Ship();
    ship.createS(sizeX, sizeY);
    missile=new Missile();
    missile.createM(sizeX, sizeY);
    
    mothership=new Mothership();
    mothership.createA(sizeX, sizeY);
    
    boss= new Boss();
    boss.createB(sizeX, sizeY);
    
    //Create Aliens
    int startx=0;
    int starty=0;
    for(int i=0;i<numAliens;i++)
    {
      aliens[i]=new Aliens();
      aliens[i].createA(sizeX, sizeY);
      aliens[i].x+=startx;
      aliens[i].y+=starty;
      startx+=50;
      if (i==checkA)
      {
        starty+=30;
        startx=0;
        checkA+=9;
      }
    }
    //Create Alien Missiles
    for(int i=0;i<numMissiles;i++)
    {
      random = (int)(Math.random()*45);
      aMissiles[i]=new alienMissile();
      aMissiles[i].createAM(sizeX, sizeY);
      aMissiles[i].x=aliens[random].x+20;
      aMissiles[i].y=aliens[random].y;
    }
    
    //Create Boss Missiles
    for(int i=0;i<numBMissiles;i++)
    {
      randomx = (int)(Math.random()*400+boss.x);
      bMissiles[i]=new alienMissile();
      bMissiles[i].createAM(sizeX, sizeY);
      bMissiles[i].x=randomx;
      bMissiles[i].y=150;
    }
    
    //Create Shields
    int startsx=0;
    int startsy=0;
    int shieldCount=0;
    for(int i=0;i<numShields;i++)
    {
      shields[i]= new Shields();
      shields[i].createS(sizeX, sizeY);
      shields[i].x+=startsx;
      shields[i].y+=startsy;
      startsx+=30;
      shieldCount++;
      if (shieldCount==3||shieldCount==6||shieldCount==12||shieldCount==15||shieldCount==20||shieldCount==22)
      {
        startsx+=90;
      }
      if (shieldCount==9||shieldCount==18)
      {
        startsx=0;
        startsy+=30;
      }
      if (shieldCount==19||shieldCount==21||shieldCount==23)
      {
        startsx+=30;
      }
    }
    
    repaint();
    th.start ();  
    
    this.add(draw1);
    this.setSize(sizeX,sizeY);
    this.setVisible(true);
    this.setResizable(false);
    
    addKeyListener(this);
    this.addMouseListener(this);
  }
  public void actionPerformed(ActionEvent e){}
  
  public void mousePressed(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mouseClicked(MouseEvent e){}
  
  public void keyTyped(KeyEvent e) { }
  
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    
    //Main Menu
    if (game==0)
    {
      if (keyCode == KeyEvent.VK_UP) 
      {
        select--;
        if(select<0)
        {
          select=0;
        }
      }
      if (keyCode == KeyEvent.VK_DOWN) 
      {
        select++;
      }
      if(select>3)
      {
        select=3;
      }
      if (keyCode == KeyEvent.VK_ENTER) 
      {
        if (select==0)
        {
          game=1;
          gp++;
          wave++;
        }
        if (select==1)
        {
          game=3;
          select=0;
          startDelay=1;
        }
        if (select==2)
        {
          game=4;
          select=0;
          startDelay=1;
        }
        if (select==3)
        {
          System.exit(0);
        }
      }
    }
    
    //Highscores
    if (game==3)
    {
      if (keyCode == KeyEvent.VK_UP) 
      {
        select--;
        if(select<0)
        {
          select=0;
        }
      }
      if (keyCode == KeyEvent.VK_DOWN) 
      {
        select++;
      }
      if(select>1)
      {
        select=1;
      }
      if (delay2>5)
      {
        if (keyCode == KeyEvent.VK_ENTER) 
        {
          if (select==0)
          {
            game=0;
            delay2=0;
            startDelay=0;
          }
          if (select==1)
          {
            clearHS();
          }
        }
      }
    }
    
    //Options
    if (game==4)
    {
      if (keyCode == KeyEvent.VK_UP) 
      {
        select--;
        if(select<0)
        {
          select=0;
        }
      }
      if (keyCode == KeyEvent.VK_DOWN) 
      {
        select++;
      }
      if(select>3)
      {
        select=3;
      }
      if (delay2>5)
      {
        if (keyCode == KeyEvent.VK_ENTER) 
        {
          if (select==0)
          {
            game=0;
            delay2=0;
            startDelay=0;
          }
          if (select==1)
          {
            game=5;
            select=0;
            delay2=0;
            startDelay=1;
          }
          if (select==2)
          {
            game=6;
            select=0;
            delay2=0;
            startDelay=1;
          }
          if (select==3)
          {
            game=7;
            select=0;
            delay2=0;
            startDelay=1;
          }
        }
      }
    }
    
    //Controls
    if (game==5)
    {
      if (keyCode == KeyEvent.VK_UP) 
      {
        select--;
        if(select<0)
        {
          select=0;
        }
      }
      if (keyCode == KeyEvent.VK_DOWN) 
      {
        select++;
      }
      if(select>2)
      {
        select=2;
      }
      if (delay2>5)
      {
        if (keyCode == KeyEvent.VK_ENTER) 
        {
          if (select==0)
          {
            game=4;
            select=0;
            delay2=0;
            startDelay=1;
          }
          if (select==1)
          {
            controls=2;
          }
          if (select==2)
          {
            controls=1;
          }
        }
      }
    }
    
    //Difficulty
    if (game==6)
    {
      if (keyCode == KeyEvent.VK_UP) 
      {
        select--;
        if(select<0)
        {
          select=0;
        }
      }
      if (keyCode == KeyEvent.VK_DOWN) 
      {
        select++;
      }
      if(select>3)
      {
        select=3;
      }
      if (delay2>5)
      {
        if (keyCode == KeyEvent.VK_ENTER) 
        {
          if (select==0)
          {
            game=4;
            select=0;
            delay2=0;
            startDelay=1;
          }
          if (select==1)
          {
            difficulty=1;
          }
          if (select==2)
          {
            difficulty=2;
          }
          if (select==3)
          {
            if (shieldsDown==0)
            {
              shieldsDown=1;
            }
            else if (shieldsDown==1)
            {
              shieldsDown=0;
            }
          }
        }
      }
    }
    
    //Character Select
    if (game==7)
    {
      if (keyCode == KeyEvent.VK_UP) 
      {
        select--;
        if(select<0)
        {
          select=0;
        }
      }
      if (keyCode == KeyEvent.VK_DOWN) 
      {
        select++;
      }
      if(select>2)
      {
        select=2;
      }
      if (delay2>5)
      {
        if (keyCode == KeyEvent.VK_ENTER) 
        {
          if (select==0)
          {
            game=4;
            select=0;
            delay2=0;
            startDelay=1;
          }
          if (select==1)
          {
            character=1;
          }
          if (unlock==1)
          {
            if (select==2)
            {
              character=2;
            }
          }
        }
      }
    }
    
    //GAME
    if (game==1)
    {
      if (controls==1)
      {
        if (keyCode == KeyEvent.VK_RIGHT) 
        {
          ship.x_speed=2;
        }
        if (keyCode == KeyEvent.VK_LEFT) 
        {
          ship.x_speed=-2;
        }
      }
      if (controls==2)
      {
        if (keyCode == KeyEvent.VK_D) 
        {
          ship.x_speed=2;
        }
        if (keyCode == KeyEvent.VK_A) 
        {
          ship.x_speed=-2;
        }
      }
      if (keyCode == KeyEvent.VK_SPACE) 
      {
        missile.y_speed=-8;
        launch=1;
      }
      if (keyCode == KeyEvent.VK_SHIFT) 
      {
        game=8;
        delay2=0;
        startDelay=1;
      }
    }
    if (game==2)
    {
      if (keyCode == KeyEvent.VK_ENTER) 
      {
        game=0;
        resetGame();
      }
    }
    
    //Game Paused
    if (game==8)
    {
      if (keyCode == KeyEvent.VK_UP) 
      {
        select--;
        if(select<0)
        {
          select=0;
        }
      }
      if (keyCode == KeyEvent.VK_DOWN) 
      {
        select++;
      }
      if(select>1)
      {
        select=1;
      }
      if (delay2>5)
      {
        if (keyCode == KeyEvent.VK_ENTER) 
        {
          if (select==0)
          {
            game=1;
            delay2=0;
            startDelay=0;
          }
          if (select==1)
          {
            game=0;
            select=0;
            delay2=0;
            startDelay=0;
            resetGame();
          }
        }
      }
    }
  }
  
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    
    //GAME
    if(game==1)
    {
      if (controls==1)
      {
        if (keyCode == KeyEvent.VK_RIGHT)// && keyCode != KeyEvent.VK_SPACE ) 
        {
          ship.x_speed=0;
        }
        if (keyCode == KeyEvent.VK_LEFT)// && keyCode != KeyEvent.VK_SPACE) 
        {
          ship.x_speed=0;
        }
      }
      if (controls==2)
      {
        if (keyCode == KeyEvent.VK_D)// && keyCode != KeyEvent.VK_SPACE ) 
        {
          ship.x_speed=0;
        }
        if (keyCode == KeyEvent.VK_A)// && keyCode != KeyEvent.VK_SPACE) 
        {
          ship.x_speed=0;
        }
      }
    }
  }
  
  public void run () 
  {
    while (true) 
    {
      if (startDelay==1)
      {
        MMDelay();
      }
      
      //Animations
      if(game>=0)
      {
        Animations();
        if (delay==30)
        {
          if(aTurn==0)
          {
            aTurn=1;
            delay=0;
          }
          else if(aTurn==1)
          {
            aTurn=0;
            delay=0;
          }
        }
      }
      
      //GAME
      if(game==1)
      {
        ship.moveS();
        missile.moveM();
        mothership.moveA();
        if (bossLvl==0)
        {
          mothership.checkBoundaryA(sizeX,sizeY);
        }
        checkHits();
        gotHit();
        Timer();
        Timer2();
        ship.checkBoundaryS(sizeX, sizeY);
        MissileMechanics();
        
        for(int i=0;i<numAliens;i++)
        {
          aliens[i].moveA();
        }
        AliensMechanics ();
        aMissileMechanics() ;
        aMissileLaunch();
        for(int i=0;i<numMissiles;i++)
        {
          aMissiles[i].moveAM();
        }
        for(int i=0;i<numShields;i++)
        {
          shields[i].boundaryS.x=shields[i].x;
          shields[i].boundaryS.y=shields[i].y;
        }
        if (shieldsDown==0)
        {
          shieldsHit();
        }
        if (difficulty==1)
        {
          if (aCounter==45)
          {
            aCounter=0;
            wave++;
            newWave();
          }
        }
        if (difficulty==2)
        {
          if (aCounter==45)
          {
            wave++;
            aCounter=0;
            if (wave!=bossWave)
            {
              newWave();
            }
          }
          if (wave==bossWave)
          {
            bossLvl=1;
            boss.y_speed=2;
            boss.moveB();
            checkHitsB();
            boss.checkBoundaryB(sizeX, sizeY);
          }
          if (boss.battle==1)
          {
            for (int i=0;i<numBMissiles;i++)
            {
              bMissiles[i].moveAM();
            }
            gotHitB();
            bMissileLaunch(); 
            bMissileMechanics();
            bossMechanics();
          }
        }
      }
      
      //GAME OVER
      if(game==2)
      {
        goy_speed=10;
        goy+=goy_speed;
        if (goy>330)
        {
          goy=330;
        }
        missile.moveM();
        for(int i=0;i<numMissiles;i++)
        {
          aMissiles[i].moveAM();
        }
        AliensMechanics ();
        if (boss.battle==0)
        {
          mothership.moveA();
          mothership.checkBoundaryA(sizeX,sizeY);
        }
        if (boss.battle==1)
        {
          for (int i=0;i<numBMissiles;i++)
          {
            bMissiles[i].moveAM();
          }
          gotHitB();
          bMissileLaunch(); 
          bMissileMechanics();
        }
      }
      HS();
      sizeX=this.getWidth();
      sizeY=this.getHeight();
      
      repaint(); 
      
      try 
      {
        Thread.sleep (15); 
      } 
      catch (InterruptedException ex) 
      {
      } 
    }
  }
  
  public void Animations () 
  {
    delay++;
  }
  
  public void MMDelay () 
  {
    delay2++;
  }
  
  public void MissileMechanics () 
  {
    int previousX=missile.x;
    missile.x=ship.x+23;
    if (launch==1)
    {
      missile.x=previousX;
    }
    if (missile.y<0)
    {
      missileReturn();
    }
  }
  public void aMissileMechanics () 
  {
    for(int i=0;i<numMissiles;i++)
    {
      int previousAX=aMissiles[i].x;
      aMissiles[i].x=aliens[random].x+20;
      if(aMissiles[i].launchA==0)
      {
        aMissiles[i].y=aliens[random].y;
      }
      if (aMissiles[i].launchA==1)
      {
        aMissiles[i].x=previousAX;
      }
    }
  }
  
  public void aMissileLaunch () 
  {
    counter++;
    
    if (counter==30)
    {
      random=(int)(Math.random()*45);
      aMissiles[random].y_speed=8;
      aMissiles[random].launchA=1;
      
      for(int i=0;i<numMissiles;i++)
      {
        if (aMissiles[i].y>700)
        {
          aMissiles[i].launchA=0;
          aMissiles[i].y_speed=0;
          aMissiles[i].y=aliens[i].y;
          aMissiles[i].x=aliens[i].x+20;
        }
      }
      counter=0;
    }
  }
  
  public void bMissileMechanics () 
  {
    for(int i=0;i<numBMissiles;i++)
    {
      int previousAX=bMissiles[i].x;
      bMissiles[i].x=randomx;
      if(bMissiles[i].launchA==0)
      {
        bMissiles[i].y=150;
      }
      if (bMissiles[i].launchA==1)
      {
        bMissiles[i].x=previousAX;
      }
    }
  }
  
  public void bMissileLaunch () 
  {
    counter2++;
    if (counter2==30)
    {
      random2=(int)(Math.random()*45);
      bMissiles[random2].y_speed=8;
      bMissiles[random2].launchA=1;
      
      for(int i=0;i<numBMissiles;i++)
      {
        if (bMissiles[i].y>700)
        {
          bMissiles[i].launchA=0;
          bMissiles[i].y_speed=0;
          bMissiles[i].y=150;
          randomx=(int)(Math.random()*400+boss.x);
          bMissiles[i].x=randomx;
        }
      }
      counter2=0;
    }
  }
  
  public void bossMechanics () 
  {
    boss.boundaryB.x=boss.x;
    if (ship.x<boss.x)
    {
      boss.x_speed=-1;
    }
    else if (ship.x+ship.sizeW>boss.x+boss.sizeW)
    {
      boss.x_speed=1;
    }
    else
    {
      boss.x_speed=0;
    }
    if(boss.x>150)
    {
      boss.x=150;
    }
    if(boss.x<0)
    {
      boss.x=0;
    }
  }
  
  public void AliensMechanics () 
  {
    for(int i=0;i<numAliens;i++)
    {
      if (aliens[i].boundaryA.intersects(LeftBound))
      {
        if (check==1)
        {
          for(int a=0;a<numAliens;a++)
          {
            if (wave<5)
            {
            aliens[a].x_speed=2;
            }
            else if (wave>=5 && wave<10)
            {
              aliens[a].x_speed=3;
            }
            else if (wave>=10)
            {
              aliens[a].x_speed=4;
            }
            aliens[a].y+=5;
            check=2;
          }
        }
      }
      if (aliens[i].boundaryA.intersects(RightBound))
      {
        if (check==2)
        {
          for(int a=0;a<numAliens;a++)
          {
            if (wave<5)
            {
            aliens[a].x_speed=-2;
            }
            else if (wave>=5 && wave<10)
            {
              aliens[a].x_speed=-3;
            }
            else if (wave>=10)
            {
              aliens[a].x_speed=-4;
            }
            aliens[a].y+=5;
            check=1;
          }
        }
      }
      if (aliens[i].boundaryA.intersects(ShipBound))
      {
        game=2;
        ship.shot=1;
      }
    }
  }
  
  public void checkHits () 
  {
    for(int i=0;i<9;i++)
    {
      if (missile.boundaryM.intersects(aliens[i].boundaryA))
      {
        aliens[i].shot=1;
        trigger=1;
        missileReturn();
        aCounter++;
        score+=50;
        lastShot=i;
      }
    }
    for(int i=9;i<27;i++)
    {
      if (missile.boundaryM.intersects(aliens[i].boundaryA))
      {
        aliens[i].shot=1;
        trigger=1;
        missileReturn();
        aCounter++;
        score+=20;
        lastShot=i;
      }
    }
    for(int i=27;i<numAliens;i++)
    {
      if (missile.boundaryM.intersects(aliens[i].boundaryA))
      {
        aliens[i].shot=1;
        trigger=1;
        missileReturn();
        aCounter++;
        score+=10;
        lastShot=i;
      }
    }
    if (missile.boundaryM.intersects(mothership.boundaryA))
    {
      mothership.shot=1;
      trigger3=1;
      missileReturn();
      score+=250;
      mothership.x_speed=0;
    }
  }
  
  public void checkHitsB () 
  {
    if(boss.battle==1)
    {
      if (missile.boundaryM.intersects(boss.boundaryB))
      {
        score+=5;
        missileReturn();
        boss.health--;
        healthw-=8;
        if (boss.health==0)
        {
          if (ship.lives<3)
          {
            ship.lives++;
            livesCount++;
          }
          aCounter=45;
          boss.battle=0;
          bossLvl=0;
          boss.y_speed=0;
          bossWave+=3;
          trigger4=1;
          score+=1000;
          boss.shot=1;
          bosses++;
          unlock=1;
        }
      }
    }
  }
  
  public void Timer () 
  {
    if(trigger==1)
    {
      deathTimer++;
      if(deathTimer==20)
      {
        aliens[lastShot].deadA();
        deathTimer=0;
        trigger=0;
        kills++;
      }
    }
    if(trigger3==1)
    {
      deathTimer++;
      if(deathTimer==20)
      {
        mothership.deadA();
        deathTimer=0;
        trigger3=0;
        kills++;
        mothership.shot=0;
      }
    }
    if(trigger4==1)
    {
      deathTimer++;
      if(deathTimer==10)
      {
        boss.deadB();
        deathTimer=0;
        trigger4=0;
        healthw=400;
        kills++;
        boss.shot=0;
      }
    }
  }
  
  public void gotHit () 
  {
    for(int i=0;i<numMissiles;i++)
    {
      if (aMissiles[i].boundaryAM.intersects(ship.boundaryS))
      {
        aMissiles[i].y=aliens[i].y;
        aMissiles[i].x=aliens[i].x+20;
        aMissiles[i].launchA=0;
        aMissiles[i].y_speed=0;
        trigger2=1;
        ship.lives--;
        livesCount--;
        ship.shot=1;
        if(ship.lives==0)
        {
          game=2;
        }
      }
    }
  }
  
  public void gotHitB () 
  {
    for(int i=0;i<numBMissiles;i++)
    {
      if (bMissiles[i].boundaryAM.intersects(ship.boundaryS))
      {
        bMissiles[i].y=150;
        randomx=(int)(Math.random()*400+boss.x);
        bMissiles[i].x=randomx;
        bMissiles[i].launchA=0;
        bMissiles[i].y_speed=0;
        trigger2=1;
        ship.lives--;
        livesCount--;
        ship.shot=1;
        if(ship.lives==0)
        {
          game=2;
        }
      }
    }
  }
  
  public void Timer2 () 
  {
    if(trigger2==1)
    {
      deathTimer2++;
      if(deathTimer2==20)
      {
        ship.x=250;
        deathTimer2=0;
        trigger2=0;
        ship.shot=0;
      }
    }
  }
  
  public void missileReturn () 
  {
    launch=0;
    missile.x=ship.x+25;
    missile.checkBoundaryM(sizeX, sizeY);
  }
  
  public void shieldsHit () 
  {
    for(int i=0;i<numShields;i++)
    {
      if (missile.boundaryM.intersects(shields[i].boundaryS))
      {
        missileReturn();
        shields[i].lives--;
        if (shields[i].lives==0)
        {
          shields[i].y=750;
        }
      }
    }
    for(int i=0;i<numShields;i++)
    {
      for(int a=0;a<numMissiles;a++)
      {
        if (aMissiles[a].boundaryAM.intersects(shields[i].boundaryS))
        {
          aMissiles[a].y=aliens[a].y;
          aMissiles[a].x=aliens[a].x+20;
          aMissiles[a].launchA=0;
          aMissiles[a].y_speed=0;
          shields[i].lives--;
          if (shields[i].lives==0)
          {
            shields[i].y=750;
          }
        }
      }
    }
    for(int i=0;i<numShields;i++)
    {
      for(int a=0;a<numAliens;a++)
      {
        if (aliens[a].boundaryA.intersects(shields[i].boundaryS))
        {
          shields[i].lives--;
          if (shields[i].lives==0)
          {
            shields[i].y=750;
          }
        }
      }
    }
    for(int i=0;i<numShields;i++)
    {
      for(int a=0;a<numBMissiles;a++)
      {
        if (bMissiles[a].boundaryAM.intersects(shields[i].boundaryS))
        {
          bMissiles[a].y=150;
          randomx=(int)(Math.random()*400+boss.x);
          bMissiles[a].x=randomx;
          bMissiles[a].launchA=0;
          bMissiles[a].y_speed=0;
          shields[i].lives--;
          if (shields[i].lives==0)
          {
            shields[i].y=750;
          }
        }
      }
    }
  }
  
  public void newWave ()
  {
    trigger=0;
    trigger2=0;
    int starty=0;
    int startx=0;
    checkA=8;
    for(int a=0;a<numAliens;a++)
    {
      if (wave<5)
      {
        aliens[a].y=50;
      }
      else if (wave>=5 && wave<10)
      {
      aliens[a].y=100;
      }
      else if (wave>=10)
      {
      aliens[a].y=150;
      }
      aliens[a].x=10;
    }
    for(int i=0;i<numAliens;i++)
    {
      aliens[i].y+=starty;
      aliens[i].x+=startx;
      startx+=50;
      if (i==checkA)
      {
        startx=0;
        starty+=30;
        checkA+=9;
      }
      aliens[i].boundaryA.y=aliens[i].y;
      aliens[i].shot=0;
    }
    for(int i=0;i<numMissiles;i++)
    {
      random = (int)(Math.random()*45);
      aMissiles[i].x=aliens[random].x+20;
      aMissiles[i].y=aliens[random].y;
    }
    if (ship.lives<3)
    {
      ship.lives++;
      livesCount++;
    }
    ship.shot=0;
  }
  
  public void resetGame() 
  {
    trigger=0;
    trigger2=0;
    int starty=0;
    int startx=0;
    int startsx=0;
    int startsy=0;
    int shieldCount=0;
    
    for(int a=0;a<numShields;a++)
    {
      shields[a].y=450;
      shields[a].x=50;
    }
    
    for(int i=0;i<numShields;i++)
    {
      shields[i].lives=3;
      shields[i].x+=startsx;
      shields[i].y+=startsy;
      startsx+=30;
      shieldCount++;
      if (shieldCount==3||shieldCount==6||shieldCount==12||shieldCount==15||shieldCount==20||shieldCount==22)
      {
        startsx+=90;
      }
      if (shieldCount==9||shieldCount==18)
      {
        startsx=0;
        startsy+=30;
      }
      if (shieldCount==19||shieldCount==21||shieldCount==23)
      {
        startsx+=30;
      }
    }
    
    checkA=8;
    for(int a=0;a<numAliens;a++)
    {
      aliens[a].y=50;
      aliens[a].x=10;
    }
    for(int i=0;i<numAliens;i++)
    {
      aliens[i].y+=starty;
      aliens[i].x+=startx;
      startx+=50;
      if (i==checkA)
      {
        startx=0;
        starty+=30;
        checkA+=9;
      }
      aliens[i].boundaryA.y=aliens[i].y;
      aliens[i].shot=0;
    }
    for(int i=0;i<numMissiles;i++)
    {
      random = (int)(Math.random()*45);
      aMissiles[i].x=aliens[random].x+20;
      aMissiles[i].y=aliens[random].y;
    }
    aCounter=0;
    ship.lives=3;
    ship.x=250;
    ship.x_speed=0;
    goy=-100;
    score=0;
    livesCount=3;
    wave=0;
    ship.shot=0;
    mothership.x=-300;
    bossWave=2;
    healthw=400;
    boss.health=50;
    boss.battle=0;
    bossLvl=0;
    boss.y=-250;
    boss.x_speed=0;
    boss.x=75;
  }
  
  public void HS() 
  {
    if (highscore<score)
    {
      highscore=score;
    }
    if (hwave< wave)
    {
      hwave=wave;
    }
  }
  
  public void clearHS() 
  {
    highscore=0;
    hwave=0;
    kills=0;
    gp=0;
    bosses=0;
    hsx=450;
  }
  
  class yDrawPanel extends JPanel
  {
    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      
      //Main Menu
      if(game==0)
      {
        g2.drawImage(SpaceMM,0,0,550,700,null);
        if (select==0)
        {
          rx=100;
          ry=370;
        }
        if (select==1)
        {
          rx=70;
          ry=440;
        }
        if (select==2)
        {
          rx=120;
          ry=515;
        }
        if (select==3)
        {
          rx=100;
          ry=590;
        }
        if (aTurn==0)
        {
          g2.drawImage(MMAlien1,rx,ry,33,17,null);
        }
        if (aTurn==1)
        {
          g2.drawImage(MMAlien2,rx,ry,33,17,null);
        }
      }
      
      //GAME GRAPHICS
      if(game==1||game==2 || game==8)
      {
        //Background
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,550,700);
        
        //Draw Shields
        if (shieldsDown==0)
        {
          for(int i=0;i<numShields;i++)
          {
            if(shields[i].lives==3)
            {
              g2.drawImage(Shield3,shields[i].x, shields[i].y, shields[i].sizeW,shields[i].sizeH,null);
            }
            if(shields[i].lives==2)
            {
              g2.drawImage(Shield2,shields[i].x, shields[i].y, shields[i].sizeW,shields[i].sizeH,null);
            }
            if(shields[i].lives==1)
            {
              g2.drawImage(Shield1,shields[i].x, shields[i].y, shields[i].sizeW,shields[i].sizeH,null);
            }
          }
          for(int i=0;i<9;i++)
          {
            if (i==0||i==3||i==6)
            {
              g2.drawImage(LCorner,shields[i].x, shields[i].y, shields[i].sizeW,shields[i].sizeH,null);
            }
            if (i==2||i==5||i==8)
            {
              g2.drawImage(RCorner,shields[i].x, shields[i].y, shields[i].sizeW,shields[i].sizeH,null);
            }
          }
        }
        
        //Draw Ship
        g2.setColor(Color.GREEN);
        if (ship.shot==0)
        {
          g2.fillRect(missile.x, missile.y, missile.sizeW,missile.sizeH);
          if (character==1)
          {
            g2.drawImage(SShip,ship.x, ship.y, ship.sizeW,ship.sizeH,null);
          }
          if (character==2)
          {
            g2.drawImage(Mooneyship,ship.x, ship.y, ship.sizeW,ship.sizeH,null);
          }
        }
        if (ship.shot==1)
        {
          g2.drawImage(ShipShot,ship.x, ship.y, ship.sizeW,ship.sizeH,null);
        }
        for(int i=0;i<numMissiles;i++)
        {
          g2.setColor(Color.RED);
          g2.fillRect(aMissiles[i].x, aMissiles[i].y, aMissiles[i].sizeW,aMissiles[i].sizeH);
        }
        
        //Draw Aliens
        for(int i=0;i<9;i++)
        {
          if (aliens[i].shot==0)
          {
            if (aTurn==0)
            {
              g2.drawImage(A31,aliens[i].x, aliens[i].y, aliens[i].sizeW,aliens[i].sizeH,null);
            }
            else if (aTurn==1)
            {
              g2.drawImage(A32,aliens[i].x, aliens[i].y, aliens[i].sizeW,aliens[i].sizeH,null);
            }
          }
        }
        for(int i=9;i<27;i++)
        {
          if (aliens[i].shot==0)
          {
            if (aTurn==0)
            {
              g2.drawImage(A21,aliens[i].x, aliens[i].y, aliens[i].sizeW,aliens[i].sizeH,null);
            }
            else if (aTurn==1)
            {
              g2.drawImage(A22,aliens[i].x, aliens[i].y, aliens[i].sizeW,aliens[i].sizeH,null);
            }
          }
        }
        for(int i=27;i<numAliens;i++)
        {
          if (aliens[i].shot==0)
          {
            if(aTurn==0)
            {
              g2.drawImage(A11,aliens[i].x, aliens[i].y, aliens[i].sizeW,aliens[i].sizeH,null);
            }
            else if (aTurn==1)
            {
              g2.drawImage(A12,aliens[i].x, aliens[i].y, aliens[i].sizeW,aliens[i].sizeH,null);
            }
          }
        }
        
        for(int i=0;i<numAliens;i++)
        {
          if (aliens[i].shot==1)
          {
            g2.drawImage(AlienShot,aliens[i].x, aliens[i].y, aliens[i].sizeW,aliens[i].sizeH,null);
          }
        }
        if (mothership.shot==0)
        {
          g2.drawImage(MShip,mothership.x,mothership.y, mothership.sizeW,mothership.sizeH,null);
        }
        if (mothership.shot==1)
        {
          g2.drawImage(AlienShot,mothership.x,mothership.y, mothership.sizeW,mothership.sizeH,null);
        }
        
        //Draw Boss
        if(boss.battle==1)
        {
          g2.setColor(Color.GRAY);
          g2.fillRect(75,10,400,8);
          g2.setColor(Color.RED);
          g2.fillRect(75,10,healthw,8);
          g2.setColor(Color.WHITE);
          g2.setFont(new Font("Arial Black",1,15));
          g2.drawString("Boss",250,35);
          
          for(int i=0;i<numBMissiles;i++)
          {
            g2.setColor(Color.RED);
            g2.fillRect(bMissiles[i].x, bMissiles[i].y, bMissiles[i].sizeW,bMissiles[i].sizeH);
          }
        }
        if(boss.shot==0)
        {
          if(aTurn==0)
          {
            g2.drawImage(MMAlien1,boss.x,boss.y, boss.sizeW,boss.sizeH,null);
          }
          else if (aTurn==1)
          {
            g2.drawImage(MMAlien2,boss.x,boss.y, boss.sizeW,boss.sizeH,null);
          }
        }
        if(boss.shot==1)
        {
          g2.drawImage(AlienShot,boss.x,boss.y, boss.sizeW,boss.sizeH,null);
        }
        
        //Texts
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial Black",1,20));
        g2.drawString("Score: "+score,10,650);
        g2.drawString("Lives: ",350,650);
        for (int i=0;i<livesCount;i++)
        {
          if (character==1)
          {
            g2.drawImage(SShip,lives[i], 640, 25,10,null);
          }
          if (character==2)
          {
            g2.drawImage(Mooneyship,lives[i], 640, 25,10,null);
          }
        }
        g2.setFont(new Font("Arial Black",1,15));
        g2.setColor(Yellow);
        g2.drawString("Wave",230,640);
        g2.drawString(""+wave,250,660);
      }
      
      //GAME OVER
      if (game==2)
      {
        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial Black",1,50));
        g2.drawString("Game Over!",100,goy);
        g2.setFont(new Font("Arial Black",1,20));
        g2.drawString("Press Enter to Return to the Main Menu",40,380);
      }
      
      //Highscores
      if (game==3)
      {
        g2.drawImage(SpaceHS,0,0,550,700,null);
        if (select==0)
        {
          rx=105;
          ry=18;
        }
        if (select==1)
        {
          rx=380;
          ry=630;
        }
        if (aTurn==0)
        {
          g2.drawImage(MMAlien1,rx,ry,33,17,null);
        }
        if (aTurn==1)
        {
          g2.drawImage(MMAlien2,rx,ry,33,17,null);
        }
        g2.setColor(Yellow);
        g2.setFont(new Font("Comic Sans MS",1,30));
        g2.drawString(""+ gp,450,305);
        g2.drawString(""+ highscore,hsx,365);
        g2.drawString(""+ hwave,450,425);
        g2.drawString(""+ kills,450,485);
        g2.drawString(""+ bosses,450,545);
        if (highscore>9999)
        {
          hsx=425;
        }
      }
      
      //Options Menu
      if (game==4)
      {
        g2.drawImage(SpaceOption0,0,0,550,700,null);
        if (select==0)
        {
          rx=105;
          ry=18;
        }
        if (select==1)
        {
          rx=90;
          ry=315;
        }
        if (select==2)
        {
          rx=90;
          ry=415;
        }
        if (select==3)
        {
          rx=20;
          ry=520;
        }
        if (aTurn==0)
        {
          g2.drawImage(MMAlien1,rx,ry,33,17,null);
        }
        if (aTurn==1)
        {
          g2.drawImage(MMAlien2,rx,ry,33,17,null);
        }
      }
      
      g2.setColor(Yellow);
      
      //Controls Menu
      if (game==5)
      {
        g2.drawImage(SpaceOption1,0,0,550,700,null);
        if (select==0)
        {
          rx=105;
          ry=18;
        }
        if (select==1)
        {
          rx=20;
          ry=200;
        }
        if (select==2)
        {
          rx=250;
          ry=200;
        }
        if (aTurn==0)
        {
          g2.drawImage(MMAlien1,rx,ry,33,17,null);
        }
        if (aTurn==1)
        {
          g2.drawImage(MMAlien2,rx,ry,33,17,null);
        }
        if (controls==1)
        {
          g2.fillRect(330,215,100,3); 
        }
        if (controls==2)
        {
          g2.fillRect(100,215,100,3); 
        }
      }
      
      //Difficulty Menu
      if (game==6)
      {
        g2.drawImage(SpaceOption2,0,0,550,700,null);
        if (select==0)
        {
          rx=105;
          ry=18;
        }
        if (select==1)
        {
          rx=60;
          ry=180;
        }
        if (select==2)
        {
          rx=60;
          ry=350;
        }
        if (select==3)
        {
          rx=60;
          ry=520;
        }
        
        if (aTurn==0)
        {
          g2.drawImage(MMAlien1,rx,ry,33,17,null);
        }
        if (aTurn==1)
        {
          g2.drawImage(MMAlien2,rx,ry,33,17,null);
        }
        if (difficulty==1)
        {
          g2.fillRect(130,205,120,3); 
        }
        if (difficulty==2)
        {
          g2.fillRect(130,370,80,3); 
        }
        if (shieldsDown==1)
        {
          g2.fillRect(165,580,220,3); 
        }
      }
      
      //Character Select Menu
      if (game==7)
      {
        g2.drawImage(SpaceOption3,0,0,550,700,null);
        if (select==0)
        {
          rx=105;
          ry=18;
        }
        if (select==1)
        {
          rx=30;
          ry=270;
        }
        if (select==2)
        {
          rx=30;
          ry=480;
        }
        if (aTurn==0)
        {
          g2.drawImage(MMAlien1,rx,ry,33,17,null);
        }
        if (aTurn==1)
        {
          g2.drawImage(MMAlien2,rx,ry,33,17,null);
        }
        if (character==1)
        {
          g2.fillRect(150,300,220,3); 
        }
        if (character==2)
        {
          g2.fillRect(145,510,250,3); 
        }
        if (unlock==0)
        {
          g2.drawImage(Locked,95,477,367,137,null);
        }
      }
      
      //Pause Screen
      if (game==8)
      {
        g2.drawImage(SpacePaused,0,0,550,700,null);
        if (select==0)
        {
          rx=165;
          ry=338;
        }
        if (select==1)
        {
          rx=150;
          ry=385;
        }
        if (aTurn==0)
        {
          g2.drawImage(MMAlien1,rx,ry,33,17,null);
        }
        if (aTurn==1)
        {
          g2.drawImage(MMAlien2,rx,ry,33,17,null);
        }
      }
    }
  }
}

