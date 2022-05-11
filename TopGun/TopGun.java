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
import java.awt.Toolkit;

/////////////Notes////////////////
//Barrel Roll allows you to evade all enemy bullets
//Army Base can only be hit by bullets
//To unlock all missions copy following into data file: 6,0,0,1000,0,750,0,1500,0,3000,3,0,0,0,0
//To fully upgrade copy following into data file: 24,0,2,2000,2,2500,2,6000,1,3000,0,0,0,0,0
//Arrow Keys and Enter to navigate through the menus


public class TopGun extends JFrame implements ActionListener, KeyListener, Runnable, MouseListener{
  yDrawPanel draw1=new yDrawPanel();
  Thread th = new Thread (this);
  int game=0,mission=1,sequence=0;
  int select=0, cx=200,cy=170,startDelay2=1,delay2=0,maxSelect=4;
  int gameSizeX=600, gameSizeY=555;
  
  int bx=-600, by=-650, bdx=0, bdy=0,tilt=0,dtilt=0,centerX=300,centerY=300;
  
  int sy=150,sy2=-700,sdy=0,startTimer=0;
  
  Clouds [] clouds= new Clouds[5];
  
  int numBullets=5,count=0,previousAX=0,counter=0;
  
  int px=285,fy=450,center2X=102,center2Y=460;
  
  Bullets [] bulletsR= new Bullets[numBullets];
  Bullets [] bulletsL= new Bullets[numBullets];
  
  Bullets [] eBullets= new Bullets[numBullets];
  
  Missiles missiles;
  
  int numJets=4, deathTimer=0,lastShot=0,startDelay=0,check=0;
  
  int limit=0,limit2=0,hw=300,destroy=0;;
  
  int lives=6;
  int [] lx={187,206,225,244,263,282,301,320,339,358,377,396,187,206,225,244,263,282,301,320,339,358,377,396};
  int [] ly={490,490,490,490,490,490,490,490,490,490,490,490,500,500,500,500,500,500,500,500,500,500,500,500};
  
  int fuel=8,fuelTimer=0;
  int [] f2y={479,471,463,455,447,439,431,423};
  
  int EndTimer=0,success=0, complete=0, score=0;
  
  EnemiesJ [] eJets= new EnemiesJ[numJets];
  EnemiesR [] rJets= new EnemiesR[numJets];
  
  int numShips=3,killsShip=0;
  
  Ships [] ships= new Ships[numShips];
  
  aBase aBase;
  
  int kills=0;
  
  //Upgrades
  int earned=0,money=0,health=0,hCost=1000,miss=0,mCost=750,shoot=0,sCost=1500,move=0,cCost=3000;
  int uy=0,u2y=125,u3y=125,u4y=355,u5y=355,u6y=80,u7y=80,u8y=300;
  
  int lox=0,loy=0,low=0,loh=0,target=0,lockedon=0,object=0,evade=0;
  
  int count2=0,count3=0,startCounter1=0;
  int count4=0,count5=0,startCounter2=0;
  
  int mComplete=0, mPage=0;
  
  int h1=0,h2=0,h3,gp=0;
  
  int delay3=0;
  
  int hbx=200,hby=80,hbw=190,hbh=200,random=0;
  Rectangle hitbox= new Rectangle(hbx,hby,hbw,hbh);
  
  //Data File
  String [] fields;
  BufferedReader in=null;
  PrintWriter out=null;
  String line="";
  File f=new File("TopGunFile.txt");
  File f2=new File("TopGunFile2.txt");
  
  //Images
  BufferedImage Controls=null;
  BufferedImage aControls=null;
  BufferedImage Highscores=null;
  BufferedImage TGBack1 =null;
  BufferedImage Runway =null;
  BufferedImage TakeOff =null;
  BufferedImage Landing =null;
  BufferedImage LandingBack =null;
  BufferedImage BackDrop1 =null;
  BufferedImage TGBack2 =null;
  BufferedImage TGBack3 =null;
  BufferedImage TGBack4 =null;
  BufferedImage Dashboard =null;
  BufferedImage Reticle =null;
  BufferedImage Cloud =null;
  BufferedImage TopGunMM=null;
  BufferedImage Radar1=null;
  BufferedImage CoverRadar=null;
  BufferedImage FInd=null;
  BufferedImage PInd=null;
  BufferedImage ejets=null;
  BufferedImage ejets2=null;
  BufferedImage eships=null;
  BufferedImage Base=null;
  BufferedImage Pause=null;
  BufferedImage Mission1=null;
  BufferedImage Mission2=null;
  BufferedImage Mission3a=null;
  BufferedImage Mission3b=null;
  BufferedImage Upgrade=null;
  BufferedImage hLvl2=null;
  BufferedImage hLvl3=null;
  BufferedImage mLvl2=null;
  BufferedImage mLvl3=null;
  BufferedImage sLvl2=null;
  BufferedImage sLvl3=null;
  BufferedImage bLvl2=null;
  BufferedImage PMission1=null;
  BufferedImage PMission2=null;
  BufferedImage PMission3=null;
  BufferedImage MissionLocked=null;
  BufferedImage MComplete=null;
  BufferedImage MFailed=null;
  BufferedImage GameOver=null;
  BufferedImage missile=null;
  
  Image JetMM;
  Image Boom;
  Image LandingSeq;
  
  Color Green = new Color(113,200,117);
  
  public static void main(String[ ] args) 
  {
    new TopGun ();
  }  
  
  public TopGun (){
    
    
    JetMM = Toolkit.getDefaultToolkit().createImage("Jet.gif");
    Boom = Toolkit.getDefaultToolkit().createImage("Explosion.gif");
    LandingSeq = Toolkit.getDefaultToolkit().createImage("LandingSeq.gif");
    try
    {
      TopGunMM = ImageIO.read(new File("TGMM.jpg"));
      Controls = ImageIO.read(new File("Controls.jpg"));
      aControls = ImageIO.read(new File("aControls.jpg"));
      Highscores = ImageIO.read(new File("Highscore.jpg"));
      missile = ImageIO.read(new File("missile.png"));
      ejets = ImageIO.read(new File("eJets.png"));
      ejets2 = ImageIO.read(new File("FrontJet.png"));
      eships = ImageIO.read(new File("eShips.png"));
      Base= ImageIO.read(new File("ArmyBase.png"));
      TGBack1 = ImageIO.read(new File("TGBack1.jpg"));
      Runway = ImageIO.read(new File("Runway.jpg"));
      TakeOff = ImageIO.read(new File("TakeOff.jpg"));
      Landing = ImageIO.read(new File("Landing.jpg"));
      LandingBack = ImageIO.read(new File("LandingBack.jpg"));
      BackDrop1 = ImageIO.read(new File("BackDrop1.png"));
      TGBack2 = ImageIO.read(new File("TGBack2.jpg"));
      TGBack3 = ImageIO.read(new File("TGBack3.jpg"));
      Dashboard = ImageIO.read(new File("Dashboard.jpg"));
      Reticle = ImageIO.read(new File("Reticle.png"));
      Cloud = ImageIO.read(new File("Cloud.png"));
      Radar1 = ImageIO.read(new File("Radar1.jpg"));
      CoverRadar = ImageIO.read(new File("CoverRadar.png"));
      FInd = ImageIO.read(new File("FlightIndicator.png"));
      PInd = ImageIO.read(new File("PositionIndicator.png"));
      MComplete = ImageIO.read(new File("MComplete.jpg"));
      MFailed = ImageIO.read(new File("MFailed.jpg"));
      GameOver = ImageIO.read(new File("GameOver.jpg"));
      Pause = ImageIO.read(new File("PauseMenu.png"));
      Mission1 = ImageIO.read(new File("Mission1.png"));
      Mission2 = ImageIO.read(new File("Mission2.png"));
      Mission3a = ImageIO.read(new File("Mission3a.png"));
      Mission3b = ImageIO.read(new File("Mission3b.png"));
      Upgrade = ImageIO.read(new File("UpgradeMenu.jpg"));
      hLvl2 = ImageIO.read(new File("hLvl2.png"));
      hLvl3 = ImageIO.read(new File("hLvl3.png"));
      mLvl2 = ImageIO.read(new File("mLvl2.png"));
      mLvl3 = ImageIO.read(new File("mLvl3.png"));
      sLvl2 = ImageIO.read(new File("sLvl2.png"));
      sLvl3 = ImageIO.read(new File("sLvl3.png"));
      bLvl2 = ImageIO.read(new File("bLvl2.png"));
      PMission1= ImageIO.read(new File("PMission1.jpg"));
      PMission2= ImageIO.read(new File("PMission2.jpg"));
      PMission3=ImageIO.read(new File("PMission3.jpg"));
      MissionLocked= ImageIO.read(new File("MissionLocked.png"));
    }
    catch(IOException e)
    {
      System.out.println("Error");
    } 
    
    //Load Data File
    try
    {
      in=new BufferedReader(new FileReader(f));
    }
    catch (FileNotFoundException e1)
    {
    }
    do
    {
      try{
        line=in.readLine();
      }
      catch (IOException e1)
      {
      }
      
      if (line!=null)
      {
        fields=line.split(",");
        lives=Integer.parseInt(fields[0]);
        money=Integer.parseInt(fields[1]);
        health=Integer.parseInt(fields[2]);
        hCost=Integer.parseInt(fields[3]);
        miss=Integer.parseInt(fields[4]);
        mCost=Integer.parseInt(fields[5]);
        shoot=Integer.parseInt(fields[6]);
        sCost=Integer.parseInt(fields[7]);
        move=Integer.parseInt(fields[8]);
        cCost=Integer.parseInt(fields[9]);
        mComplete=Integer.parseInt(fields[10]);
        h1=Integer.parseInt(fields[11]);
        h2=Integer.parseInt(fields[12]);
        h3=Integer.parseInt(fields[13]);
        gp=Integer.parseInt(fields[14]);
      }
    }while (line!=null);
    
    try
    {
      in.close();
    }
    catch (IOException e1)
    {
    }
    
    //Create Bullets
    for(int i=0;i<numBullets;i++)
    {
      bulletsR[i]=new Bullets();
      bulletsR[i].create(gameSizeX, gameSizeY);
      bulletsR[i].x=430;
      bulletsR[i].y=300;
    }
    for(int i=0;i<numBullets;i++)
    {
      bulletsL[i]=new Bullets();
      bulletsL[i].create(gameSizeX, gameSizeY);
      bulletsL[i].x=150;
      bulletsL[i].y=300;
    }
    
    //Create Clouds
    for (int i=0;i<5;i++)
    {
      clouds[i]= new Clouds();
      clouds[i].create(gameSizeX, gameSizeY);
      clouds[i].x=(int)(Math.random()*500);
      clouds[i].y=(int)(Math.random()*400);
    }
    
    //Create Enemy Jets
    for (int i=0;i<numJets;i++)
    {
      eJets[i] = new EnemiesJ();
      eJets[i].create(gameSizeX,gameSizeY);
      eJets[i].x=(int)(Math.random()*300)+100;
      eJets[i].y=(int)(Math.random()*200)+100;
    }
    //Jet Bullets
    for(int i=0;i<numBullets;i++)
    {
      eBullets[i]=new Bullets();
      eBullets[i].create(gameSizeX, gameSizeY);
      random=(int)(Math.random()*numJets);
      eBullets[i].x=eJets[random].x+eJets[random].sizeW/2;
      eBullets[i].y=eJets[random].y+eJets[random].sizeH/2;;
      eBullets[i].size=5;
    }
    
    //Radar
    for (int i=0;i<numJets;i++)
    {
      rJets[i] = new EnemiesR();
      rJets[i].create(gameSizeX,gameSizeY);
      rJets[i].x=280+(int)(Math.random()*30)+1;
      rJets[i].y=800;
    }
    
    //Create Ships
    for (int i=0;i<numShips;i++)
    {
      ships[i] = new Ships();
      ships[i].create(gameSizeX,gameSizeY);
      ships[i].x=(int)(Math.random()*70)-100;
      ships[i].y=(int)(Math.random()*140)+by+800;
    }
    
    //Create Army Base
    aBase=new aBase();
    aBase.create(gameSizeX,gameSizeY);
    
    //Create Missile
    missiles= new Missiles();
    missiles.create(gameSizeX, gameSizeY);
    
    //Updates Health of Player
    HealthBar();
    
    repaint();
    th.start ();  
    
    this.add(draw1);
    this.setSize(gameSizeX,gameSizeY);
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
  public void keyTyped(KeyEvent e) {}
  
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    
    //Menu Selection
    if (game==0||game==4||game==6||game==-1||game==5||game==7)
    {
      if (game==0)
      {
        maxSelect=4;
      }
      if (game==4||game==5)
      {
        maxSelect=2;
      }
      if (game==6)
      {
        maxSelect=5;
      }
      if (game==7)
      {
        maxSelect=1;
      }
      if (game==-1)
      {
        maxSelect=3;
      }
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
      if(select>maxSelect)
      {
        select=maxSelect;
      }
    }
    if(game==3||game==-1)
    {
      if (game==3)
      {
        maxSelect=1;
      }
      if (game==-1)
      {
        maxSelect=3;
      }
      if (keyCode == KeyEvent.VK_LEFT) 
      {
        select--;
        if(select<0)
        {
          select=0;
        }
      }
      if (keyCode == KeyEvent.VK_RIGHT) 
      {
        select++;
      }
      if(select>maxSelect)
      {
        select=maxSelect;
      }
    }
    
    //Main Menu
    if (game==0)
    {
      if (keyCode == KeyEvent.VK_ENTER) 
      {
        //To Play Menu
        if (select==0) 
        {
          game=-1;
          select=1;
          startDelay2=1;
          delay2=0;
        }
        //To Highscores
        else if (select==1) 
        {
          game=5;
          select=0;
          startDelay2=1;
          delay2=0;
        }
        //To Upgrades
        else if (select==2) 
        {
          game=6;
          startDelay2=1;
          delay2=0;
          select=0;
        }
        //To Controls
        else if (select==3) 
        {
          game=7;
          select=0;
          startDelay2=1;
          delay2=0;
        }
        //Quit Game and Save Data
        else if (select==4) 
        {
          try
          {
            out=new PrintWriter(new BufferedWriter(new FileWriter(f2, false)),true); 
          }
          catch (IOException e1)
          {
          }
          line=lives+","+money+","+health+","+hCost+","+miss+","+mCost+","+shoot+","+sCost+","+move+","+cCost+","+mComplete+","+h1+","+h2+","+h3+","+gp;
          out.println(line);
          out.close();
          f.delete();
          f2.renameTo(f);
          System.exit(0);
        }
      }
    }
    
    //Highscores Menu
    if (game==5)
    {
      if (keyCode == KeyEvent.VK_ENTER) 
      {
        //Reset Highscores
        if (select==1) 
        {
          h1=0;
          h2=0;
          h3=0;
          gp=0;
        }
        //Reset Entire Game
        if (select==2) 
        {
          lives=6;
          money=0;
          health=0;
          hCost=1000;
          miss=0;
          mCost=750;
          shoot=0;
          sCost=1500;
          move=0;
          cCost=3000;
          mComplete=0;
          h1=0;
          h2=0;
          h3=0;
          gp=0;
        }
      }
    }
    
    //GAME
    if (game==1)
    {
      if (sequence==1||sequence==2||sequence==3)
      {
        //To Pause Game
        if (keyCode == KeyEvent.VK_ENTER) 
        {
          game=4;
          select=0;
          startDelay2=1;
        }
      }
      if (sequence==1)
      {
        //Movement
        if (keyCode == KeyEvent.VK_W) 
        {
          bdy=2;
        }
        if (keyCode == KeyEvent.VK_S) 
        {
          bdy=-2;
        }
        if (keyCode == KeyEvent.VK_A) 
        {
          dtilt=1;
          startCounter2=1;
          count2=0;
          count3=0;
          startCounter1=0;
          if (move==1)
          {
            if (count4==1)
            {
              dtilt=5;
              evade=1;
            }
          }
        }
        if (keyCode == KeyEvent.VK_D) 
        {
          dtilt=-1;
          startCounter1=1;
          count4=0;
          count5=0;
          startCounter2=0;
          if (move==1)
          {
            if (count2==1)
            {
              dtilt=-5;
              evade=1;
            }
          }
        }
        
        //Shoot Bullets
        if (shoot>=1)
        {
          if (keyCode == KeyEvent.VK_SPACE) 
          {
            if (count<3)
            {
              bulletsR[count].y_speed=-8;
              bulletsR[count].x_speed=-9;
              bulletsR[count].changesize=-1;
              if (shoot==2)
              {
                bulletsL[count].y_speed=-8;
                bulletsL[count].x_speed=9;
                bulletsL[count].changesize=-1;
              }
            }
            count++;
          }
        }
      }
    }
    
    //Game Over Screen
    if (game==3)
    {
      if (keyCode == KeyEvent.VK_ENTER) 
      {
        if(success==0||success==1)
        {
          if(select==0)
          {
            game=1;
            resetGame();
          }
          if(select==1)
          {
            game=0;
            resetGame();
            select=0;
          }
        }
        if(success==2)
        {
          if(select==0)
          {
            game=6;
            startDelay2=1;
            select=0;
            resetGame();
          }
          if(select==1)
          {
            game=0;
            resetGame();
            select=0;
          }
        }
      }
    }
    
    //Game Paused
    if(game==4)
    {
      if (keyCode == KeyEvent.VK_ENTER) 
      {
        if (select==0) 
        {
          if(delay2>5)
          {
            game=1;
            delay2=0;
            startDelay2=0;
          }
        }
        if (select==1)
        {
          resetGame();
          game=1;
        }
        if (select==2)
        {
          game=0;
          select=0;
          resetGame();
        }
      }
    }
    
    //Upgrades Menu
    if(game==6)
    {
      if (keyCode == KeyEvent.VK_ENTER) 
      {
        if (select==1)
        {
          if (money>=hCost && health<2)
          {
            health+=1;
            HealthBar();
            money-=hCost;
            hCost+=500;
          }
        }
        if (select==2)
        {
          if (money>=mCost && miss<2)
          {
            miss+=1;
            money-=mCost;
            mCost=2500;
          }
        }
        if (select==3)
        {
          if (money>=sCost && shoot<2)
          {
            shoot+=1;
            money-=sCost;
            sCost=sCost*2;
          }
        }
        if (select==4)
        {
          if (money>=cCost && move<1)
          {
            move+=1;
            money-=cCost;
          }
        }
        if (select==5)
        {
          game=-1;
          select=1;
          delay2=0;
          startDelay2=1;
          uy=0;
        }
      }
    }
    
    //Level Select Menu
    if(game==-1)
    {
      if (keyCode == KeyEvent.VK_ENTER) 
      {
        if(delay2>5)
        {
          if(mComplete>=select-1){
            if (select==1){
              game=1;
              mission=1;
              fuel=3;
              startDelay2=0;
              delay2=0;
            }
            if (select==2){
              game=1;
              mission=2;
              fuel=8;
              startDelay2=0;
              delay2=0;
            }
            if (select==3){
              game=1;
              mission=3;
              fuel=8;
              startDelay2=0;
              delay2=0;
            }
          }
        }
      }
    }
    
    //Back Button
    if(game==-1||game==5||game==6||game==7)
    {
      if (keyCode == KeyEvent.VK_ENTER) 
      {
        if (select==0) 
        {
          if(delay2>5)
          {
            game=0;
            delay2=0;
            startDelay2=0;
          }
        }
      }
    }
  }
  
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    
    if(game==1)
    {
      //GAME
      if (keyCode == KeyEvent.VK_W) 
      {
        bdy=0;
      }
      if (keyCode == KeyEvent.VK_S) 
      {
        bdy=0;
      }
      if (keyCode == KeyEvent.VK_A ||keyCode == KeyEvent.VK_D) 
      {
        dtilt=0;
        missiles.shot+=1;
      }
      if (keyCode == KeyEvent.VK_D) 
      {
        count2++;
        if(count2>=2 || count3>=7)
        {
          count2=0;
          count3=0;
          startCounter1=0;
        }
      }
      if (keyCode == KeyEvent.VK_A) 
      {
        count4++;
        if(count4>=2 || count5>=7)
        {
          count4=0;
          count5=0;
          startCounter2=0;
        }
      }
      if (shoot==0 && sequence==1)
      {
        if (keyCode == KeyEvent.VK_SPACE)
        {
          bulletsR[count].y_speed=-8;
          bulletsR[count].x_speed=-9;
          bulletsR[count].changesize=-1;
          count++;
        }
      }
      if (keyCode == KeyEvent.VK_SHIFT)
      {
        if(miss>0){
          if(lockedon==0){
            missiles.y_speed=-3;
            missiles.changesize=-1;
            missiles.shot=1;
            missiles.fired=1;
          }
          if(lockedon==1)
          {
            missiles.y_speed=-2;
            missiles.changesize=-1;
            missiles.fired=1;
          }
        }
      }
    }
  }
  
  public void run () 
  {
    while (true) 
    {
      if (startDelay2==1){
        delay2++;
      }
      if (startCounter1==1){
        count3++;
      }
      if (startCounter2==1){
        count5++;
      }
      
      if (game==1)
      {       
        if (sequence==0)
        {
          if(startTimer>50){
            sdy=2;
            sy+=sdy;
            sy2+=1;
          }
        }
        if (sequence==1){
          by+=bdy;
          bx+=bdx;
          
          checkMComplete();
          
          fy-=bdy/2;
          if(fy>475)
          {
            fy=475;
          }
          if(fy<435)
          {
            fy=435;
          }
          
          px-=bdx/2;
          if(px>320)
          {
            px=320;
          }
          if(px<240)
          {
            px=240;
          }
          
          tilt+=dtilt;
          if (by>-300)
          {
            by=-300;
            limit2=1;
          }
          if (by<-850)
          {
            by=-850;
            limit2=1;
          }
          if (bx>-300)
          {
            bx=-300;
            limit=1;
          }
          if (bx<-850)
          {
            bx=-850;
            limit=1;
          }
          if (bx<-300 && bx>-850)
          {
            limit=0;
          }
          if (by<-300 && by>-850)
          {
            limit2=0;
          }
          
          if (tilt<5 && tilt>-5)
          {
            bdx=0;
          }
          else if (tilt>5 && tilt<90)
          {
            bdx=2;
          }
          else if (tilt<-5 && tilt>-90)
          {
            bdx=-2;
          }
          
          if (tilt>360||tilt<-360)
          {
            tilt=0;
          }
          if (tilt<=60 && tilt>=-60)
          {
            evade=0;
          }
          
          //Bullet Movement
          for(int i=0;i<numBullets;i++){
            bulletsR[i].move();
            bulletsL[i].move();
            bulletsR[i].checkBoundary(gameSizeX,gameSizeY);
            bulletsL[i].checkBoundary(gameSizeX,gameSizeY);
            if (bulletsR[i].reset==1 || bulletsL[i].reset==1)
            {
              bulletsR[i].x=430;
              bulletsL[i].x=150;
              bulletsR[i].reset=0;
              bulletsL[i].reset=0;
              count=0;
            }
          }
          
          //Missile Movement
          if(lockedon==1 && missiles.fired>0)
          {
            if(object==1){
              if (missiles.x>eJets[target].x)
              {
                missiles.x_speed=-3;
              }
              if (missiles.x<eJets[target].x)
              {
                missiles.x_speed=3;
              }
              if (missiles.x==eJets[target].x)
              {
                missiles.x_speed=0;
              }
              if (missiles.y>eJets[target].y)
              {
                missiles.y_speed=-3;
              }
              if (missiles.y<eJets[target].y)
              {
                missiles.y_speed=3;
              }
              if (missiles.y==eJets[target].y)
              {
                missiles.y_speed=0;
              }
            }
            if(object==2){
              if (missiles.x>ships[target].x)
              {
                missiles.x_speed=-3;
              }
              if (missiles.x<ships[target].x)
              {
                missiles.x_speed=3;
              }
              if (missiles.x==ships[target].x)
              {
                missiles.x_speed=0;
              }
              if (missiles.y>ships[target].y)
              {
                missiles.y_speed=-3;
              }
              if (missiles.y<ships[target].y)
              {
                missiles.y_speed=3;
              }
              if (missiles.y==ships[target].y)
              {
                missiles.y_speed=0;
              }
            }
          }
          
          missiles.move();
          if(miss==1){
            if(missiles.y<170)
            {
              missiles.returnMissile();
            }
          }
          if(miss==2){
            if(lockedon==0 && missiles.y>170)
            {
              missiles.returnMissile();
            }
            if(missiles.size<3)
            {
              missiles.returnMissile();
              lockedon=0;
            }
          }
          
          //Cloud Movement
          for(int i=0;i<5;i++)
          {
            clouds[i].move();
            clouds[i].checkBoundary(gameSizeX,gameSizeY);
            clouds[i].x+=bdx;
            clouds[i].y+=bdy;
            
            if (clouds[i].x>300)
            {
              clouds[i].x_speed=2;
            }
            else if (clouds[i].x<300)
            {
              clouds[i].x_speed=-2;
            }
            if (clouds[i].y>150)
            {
              clouds[i].y_speed=2;
            }
            else if (clouds[i].y<150)
            {
              clouds[i].y_speed=-2;
            }
          }
          
          //Jets Movement
          for(int i=0;i<numJets;i++)
          {
            if (mission==1)
            {
              eJets[i].range1=300;
              eJets[i].range2=100;
            }
            if(mission==2||mission==3)
            {
              eJets[i].range1=100;
              eJets[i].range2=30;
            }
            eJets[i].move();
            eJets[i].checkBoundary(gameSizeX,gameSizeY);
            eJets[i].x+=bdx;
            eJets[i].y+=bdy;
            if (eJets[i].x>300)
            {
              eJets[i].x_speed=-2;
            }
            else if (eJets[i].x<300)
            {
              eJets[i].x_speed=2;
            }
            if (eJets[i].y>150)
            {
              eJets[i].y_speed=-2;
            }
            else if (eJets[i].y<150)
            {
              eJets[i].y_speed=2;
            }
          }
          rotating();
          
          //Update Jet
          for (int i=0;i<numJets;i++)
          {
            eJets[i].go++;
            
            if (eJets[i].go>80)
            {
              eJets[i].leave=1;
              eJets[i].stop=0;
            }
            if(eJets[i].leave==1)
            {
              if (eJets[i].x>300)
              {
                eJets[i].x_speed=2;
              }
              else if (eJets[i].x<300)
              {
                eJets[i].x_speed=-2;
              }
              if (eJets[i].y>150)
              {
                eJets[i].y_speed=2;
              }
              else if (eJets[i].y<150)
              {
                eJets[i].y_speed=-2;
              }
            }
          } 
          
          //Update eBullets
          if(mission==2||mission==3)
          {
            for(int i=0;i<numBullets;i++){
              eBullets[i].move();
              eBullets[i].boundary.getBounds().x=eBullets[i].x;
              eBullets[i].boundary.getBounds().y=eBullets[i].y;
            }
            eBulletsMechanics();
            eBulletsLaunch();
            gotHit();
            checkHealth();
          }
          
          //Update Ships
          if (mission==2)
          {
            for (int i=0;i<numShips;i++)
            {
              ships[i].move();
              ships[i].checkBoundary(gameSizeX,gameSizeY);
              ships[i].x+=bdx;
              ships[i].y+=bdy;
              if (ships[i].x>600)
              {
                ships[i].y=(int)(Math.random()*140)+by+800;
              }
            }
            for (int i=0;i<numShips;i++)
            {
              ships[i].boundary.getBounds().x=ships[i].x;
              ships[i].boundary.getBounds().y=ships[i].y;
            }
            hitShip();
            rotatingShips();
          }
          
          //Update Base
          if(mission==3){
            if(aBase.check<4 && limit==0)
            {
              aBase.x+=bdx;
            }
            if(aBase.check<4 && limit2==0)
            {
              aBase.y+=bdy;
            }
            if(aBase.check>=4)
            {
              aBase.x=bx+750;
              aBase.y=by+735;
              aBase.boundary.getBounds().x=aBase.x;
              aBase.boundary.getBounds().y=aBase.y;
            }
            hitBase();
            rotatingBase();
          }
          
          //Update Bounds
          for (int i=0;i<numBullets;i++)
          {
            bulletsL[i].boundary.x=bulletsL[i].x;
            bulletsL[i].boundary.y=bulletsL[i].y;
          }
          for (int i=0;i<numBullets;i++)
          {
            bulletsR[i].boundary.x=bulletsR[i].x;
            bulletsR[i].boundary.y=bulletsR[i].y;
          }
          for (int i=0;i<numJets;i++)
          {
            eJets[i].boundary.getBounds().x=eJets[i].x;
            eJets[i].boundary.getBounds().y=eJets[i].y;
            rJets[i].move();
          }
          hitJet();
          LockOn();
          radar();
          radarBoundary();
        }
        if (sequence==2||sequence==3)
        {
          for(int i=0;i<5;i++){
            clouds[i].move();
          }
          for(int i=0;i<numJets;i++){
            eJets[i].move();
          }
          if(mission==2){
            for (int i=0;i<numShips;i++)
            {
              ships[i].x=(int)(Math.random()*60)-100;
            }
          }
          for(int i=0;i<numBullets;i++){
            bulletsR[i].move();
            bulletsL[i].move();
            bulletsR[i].checkBoundary(gameSizeX,gameSizeY);
            bulletsL[i].checkBoundary(gameSizeX,gameSizeY);
          }
        }
        //Reset Tilt
        if (sequence==2)
        {
          if (tilt<0) 
          {
            tilt++;
          }
          else if (tilt>0) 
          {
            tilt--;
          }
          else
          {
            sequence=3;
            lockedon=0;
            missiles.fired=0;
          }
          if (mission==2||mission==3)
          {
            sy=-200;
          }
        }
        //Landing Sequence
        if(sequence==3)
        {
          if(mission==1)
          {
            sy-=2;
            if (sy<-330)
            {
              sequence=4;
            }
          }
          else if(mission==2){
            bdy=2;
            by+=bdy;
            sy+=2;
            if(by>-300)
            {
              sequence=4; 
            }
          }
          else if(mission==3 && complete==0){
            bdy=2;
            by+=bdy;
            sy+=2;
            if(by>-300)
            {
              sequence=4;
            }
          }
          else if(mission==3 && complete==1){
            bdy=1;
            by+=bdy;
            sy+=1;
            aBase.y=by+735;
            destroy=1;
            if(by>-300)
            {
              sequence=4;
            }
          }
        }
        Timer();
      }
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
  
  public void checkHealth()
  {
    if (lives<=0){
      game=3;
      select=0;
    }
  }
  
  public void hitJet()
  {
    for(int i=0;i<numJets;i++)
    {
      for (int a=0;a<numBullets;a++)
      {
        if (bulletsL[a].y<190)
        {
          if( bulletsL[a].boundary.intersects(eJets[i].boundary.getBounds()))
          {
            eJets[i].trigger=1;
            lastShot=i;
          }
        }
        if (bulletsR[a].y<190)
        {
          if( bulletsR[a].boundary.intersects(eJets[i].boundary.getBounds()))
          {
            eJets[i].trigger=1;
            lastShot=i;
          }
        }
      }
      if (lockedon==1 && object==1)
      {
        if (missiles.fired>0){
          if (missiles.boundary.getBounds().intersects(eJets[target].boundary.getBounds()))
          {
            eJets[target].trigger=1;
            lastShot=i;
            missiles.returnMissile();
            lockedon=0;
          }
        }
      }
      else{
        if (missiles.fired>0 && missiles.y<190){
          if (missiles.boundary.getBounds().intersects(eJets[i].boundary.getBounds()))
          {
            eJets[i].trigger=1;
            lastShot=i;
            missiles.returnMissile();
          }
        }
      }
    }
  }
  
  public void eBulletsMechanics () 
  {
    for(int i=0;i<numBullets;i++)
    {
      int previousAX=eBullets[i].x;
      eBullets[i].x=eJets[random].x+eJets[random].sizeW/2;
      if(eBullets[i].launch==0)
      {
        eBullets[i].y=eJets[random].y+eJets[random].sizeH/2;
      }
      if (eBullets[i].launch==1)
      {
        eBullets[i].x=previousAX;
      }
    }
  }
  
  public void eBulletsLaunch () 
  {
    counter++;
    
    if (counter==30)
    {
      random=(int)(Math.random()*numJets);
      eBullets[random].y_speed=4;
      eBullets[random].changesize=1;
      eBullets[random].launch=1;
      
      for(int i=0;i<numBullets;i++)
      {
        if (eBullets[i].y>400)
        {
          eBullets[i].launch=0;
          eBullets[i].y_speed=0;
          eBullets[i].changesize=0;
          eBullets[i].size=5;
          eBullets[i].y=eJets[random].y+eJets[random].sizeH/2;
          eBullets[i].x=eJets[random].x+eJets[random].sizeW/2;
        }
      }
      counter=0;
    }
  }
  
  public void hitShip()
  {
    for(int i=0;i<numShips;i++)
    {
      for (int a=0;a<numBullets;a++)
      {
        if (bulletsL[a].y<190)
        {
          if( bulletsL[a].boundary.intersects(ships[i].boundary.getBounds()))
          {
            ships[i].health-=1;
            if(ships[i].health<0)
            {
              ships[i].trigger=1;
            }
          }
        }
        if (bulletsR[a].y<190)
        {
          if( bulletsR[a].boundary.intersects(ships[i].boundary.getBounds()))
          {
            ships[i].health-=1;
            if(ships[i].health<0)
            {
              ships[i].trigger=1;
            }
          }
        }
      }
      if (lockedon==1 && object==2)
      {
        if (missiles.fired>0){
          if (missiles.boundary.getBounds().intersects(ships[target].boundary.getBounds()))
          {
            ships[target].health-=5;
            if(ships[target].health<=0)
            {
              ships[target].trigger=1;
            }
            missiles.returnMissile();
            lockedon=0;
          }
        }
      }
      else{
        if (missiles.fired>0 && missiles.y<190){
          if (missiles.boundary.getBounds().intersects(ships[i].boundary.getBounds()))
          {
            ships[i].health-=5;
            if(ships[i].health<=0)
            {
              ships[i].trigger=1;
            }
            missiles.returnMissile();
          }
        }
      }
    }
  }
  
  public void hitBase()
  {
    if(aBase.check>=4){
      for (int a=0;a<numBullets;a++)
      {
        if (bulletsL[a].y<180)
        {
          if( bulletsL[a].boundary.intersects(aBase.boundary.getBounds()))
          {
            aBase.health-=1;
            hw-=3;
            score+=750;
          }
        }
        if (bulletsR[a].y<180)
        {
          if( bulletsR[a].boundary.intersects(aBase.boundary.getBounds()))
          {
            aBase.health-=1;
            hw-=3;
            score+=750;
          }
        }
      }
    }
  }
  
  public void gotHit()
  {
    if(evade==0){
      for(int i=0;i<numBullets;i++)
      {
        if (hitbox.intersects(eBullets[i].boundary.getBounds())){
          if (eBullets[i].size>20){
            eBullets[i].launch=0;
            eBullets[i].y_speed=0;
            eBullets[i].changesize=0;
            eBullets[i].size=5;
            eBullets[i].y=eJets[random].y+eJets[random].sizeH/2;
            eBullets[i].x=eJets[random].x+eJets[random].sizeW/2;
            lives--;
          }
        }
      }
    }
  }
  
  public void HealthBar()
  {
    if (health==0)
    {
      lives=6;
    }
    else if (health==1)
    {
      lives=12; 
    }
    else if (health==2)
    {
      lives=24; 
    }
  }
  
  public void LockOn()
  {
    if (miss==2)
    {
      for(int i=0;i<numJets;i++)
      {
        if(hitbox.intersects(eJets[i].boundary.getBounds()))
        {
          target=i;
          lockedon=1;
          object=1;
          lox=eJets[target].x;
          loy=eJets[target].y;
          low=eJets[target].sizeW;
          loh=eJets[target].sizeH;
        }
      }
      for(int i=0;i<numShips;i++)
      {
        if(hitbox.intersects(ships[i].boundary.getBounds()))
        {
          target=i;
          lockedon=1;
          object=2;
          lox=ships[target].x;
          loy=ships[target].y;
          low=ships[target].sizeW;
          loh=ships[target].sizeH;
        }
      }
      if(lox<hbx || lox>hbx+hbw || loy<hby || loy>hby+hbh)
      {
        lox=800;
        if (missiles.fired==0)
        {
          lockedon=0;
        }
      }
    }
  }
  
  public void radar()
  {
    for(int i=0;i<numJets;i++)
    {
      if(eJets[i].sizeW==5)
      {
        rJets[i].y=353;
      }
      if(eJets[i].sizeW>5)
      {
        rJets[i].x=247+eJets[i].x/6;
      }
    }
  }
  
  public void radarBoundary()
  {
    for(int i=0;i<numJets;i++)
    {
      if(rJets[i].y>418)
      {
        rJets[i].y=800;
      }
    }
  }
  
  public void checkMComplete()
  {
    if(mission==1){
      if (kills>=75)
      {
        sy=200;
        sequence=2;
        success=2;
        complete=1;
        if(mComplete<1){
          mComplete=1;
        }
      }
    }
    if(mission==2){
      if (killsShip>=30)
      {
        sy=200;
        sequence=2;
        success=2;
        complete=1;
        if(mComplete<2){
          mComplete=2;
        }
      }
    }
    if(mission==3){
      if (aBase.health<=0)
      {
        sy=200;
        sequence=2;
        success=2;
        complete=1;
        if(mComplete<3){
          mComplete=3;
        }
      }
    }
  }
  
  public void rotating(){
    for (int i=0;i<numJets;i++)
    {
      AffineTransform transform = new AffineTransform();
      transform.rotate(Math.toRadians(tilt),centerX,centerY);
      eJets[i].boundary=transform.createTransformedShape(eJets[i].boundary);
    }
  }
  public void rotatingShips(){
    for (int i=0;i<numShips;i++)
    {
      AffineTransform transform = new AffineTransform();
      transform.rotate(Math.toRadians(tilt),centerX,centerY);
      ships[i].boundary=transform.createTransformedShape(ships[i].boundary);
    }
  }
  public void rotatingBase(){
    for (int i=0;i<numShips;i++)
    {
      AffineTransform transform = new AffineTransform();
      transform.rotate(Math.toRadians(tilt),centerX,centerY);
      aBase.boundary=transform.createTransformedShape(aBase.boundary);
    }
  }
  
  public void checkSize()
  {
    if (aBase.sizeW>300)
    {
      aBase.sizeW=300;
      aBase.changesizeW=0;
      aBase.check++;
    }
    if (aBase.sizeH>120)
    {
      aBase.sizeH=120;
      aBase.changesizeH=0;
      aBase.check++;
    }
    if(aBase.y<by+735)
    {
      aBase.y=by+735;
      aBase.y_speed=0;
      aBase.check++;
    }
    if(aBase.x<bx+750)
    {
      aBase.x=bx+750;
      aBase.x_speed=0;
      aBase.check++;
    }
  }
  
  public void Timer () 
  {
    if(sequence==1)
    {
      for (int i=0;i<numJets;i++)
      {
        if(eJets[i].trigger==1)
        {
          eJets[i].deathTimer++;
          if(eJets[i].deathTimer==30)
          {
            eJets[i].y=800;
            eJets[i].deathTimer=0;
            kills++;
            score+=1000;
          }
        }
      }
      if(mission==2)
      {
        for (int i=0;i<numShips;i++)
        {
          if(ships[i].trigger==1)
          {
            ships[i].deathTimer++;
            if(ships[i].deathTimer==30)
            {
              ships[i].dead();
              ships[i].y=(int)(Math.random()*140)+by+800;
              ships[i].deathTimer=0;
              killsShip++;
              score+=2500;
            }
          }
        }
      }
      if(mission==3)
      {
        if (aBase.initial==-1){
          aBase.y=900;
          aBase.x=900;
        }
        else if(aBase.initial==0)
        {
          aBase.y=by+795;
          aBase.x=bx+900;
          aBase.initial=1;
        }
        else if (aBase.initial==1)
        {
          aBase.appear();
        }
        if(fuel==5 && aBase.initial==-1)
        {
          aBase.initial=0;
        }
        checkSize();
      }
    }
    if(sequence==0)
    {
      startTimer++;
      if(startTimer==350)
      {
        sequence=1;
        startTimer=0;
      }
    }
    if(sequence==1)
    {
      fuelTimer++;
      if(fuelTimer==4000)
      {
        fuel--;
        fuelTimer=0;
      }
      if (fuel==0)
      {
        sy=200;
        sequence=2;
      }
    }
    if(sequence==4)
    {
      EndTimer++;
      if(EndTimer==200)
      {
        if (complete==0)
        {
          success=1;
        }
        game=3;
        select=0;
        startDelay2=1;
        EndTimer=0;
      }
    }
  }
  
  
  public void resetGame()
  {
    sequence=0;
    bx=-600;
    by=-650;
    bdx=0;
    bdy=0;
    tilt=0;
    dtilt=0;
    sy=150;
    sy2=-700;
    sdy=0;
    startTimer=0;
    count=0;
    px=285;
    fy=450;
    deathTimer=0;
    lastShot=0;
    startDelay=0;
    delay2=0;
    check=0;
    count2=0;
    count3=0;
    count4=0;
    count5=0;
    startCounter1=0;
    startCounter2=0;
    if (mission==1)
    {
      fuel=3; 
    }
    else if(mission!=1)
    {
      fuel=8;
    }
    fuelTimer=0;
    EndTimer=0;
    if(success==2){
      money+=300;
    }
    success=0;
    complete=0;
    score=0;
    money+=earned;
    gp++;
    earned=0;
    kills=0;
    uy=0;
    lockedon=0;
    target=0;
    
    killsShip=0;
    lox=800;
    
    if (health==0)
    {
      lives=6; 
    }
    else if (health==1)
    {
      lives=12; 
    }
    else if (health==2)
    {
      lives=24; 
    }
    
    for(int i=0;i<numJets;i++)
    {
      eJets[i].y=800;
    }
    for(int i=0;i<numShips;i++)
    {
      ships[i].x=(int)(Math.random()*60)-100;
      ships[i].y=(int)(Math.random()*140)+by+800;
    }
    for(int i=0;i<numBullets;i++)
    {
      bulletsR[i].x=430;
      bulletsR[i].y=300;
      bulletsL[i].x=150;
      bulletsL[i].y=300;
    }
    aBase.y=900;
    aBase.changesizeW=3;
    aBase.changesizeH=1;
    aBase.sizeH=4;
    aBase.sizeW=10;
    aBase.x_speed=-3;
    aBase.y_speed=-1;
    aBase.initial=-1;
    aBase.check=0;
    aBase.health=100;
    hw=300;
    destroy=0;
    LandingSeq = Toolkit.getDefaultToolkit().createImage("LandingSeq.gif");
  }
  
  class yDrawPanel extends JPanel
  {
    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      
      //Main Menu
      if (game==0)
      {
        g2.drawImage(TopGunMM,0,0,600,560,null);
        g2.drawImage(JetMM,232,352,400,203,null);
        if (select==0)
        {
          cx=200;
          cy=170;
        }
        if (select==1)
        {
          cx=240;
          cy=240;
        }
        if (select==2)
        {
          cx=240;
          cy=325;
        }
        if (select==3)
        {
          cx=240;
          cy=405;
        }
        if (select==4)
        {
          cx=170;
          cy=480;
        }
        g2.fillOval(cx,cy,15,15);
      }
      
      //GAME GRAPHICS
      if(game==1||game==4)
      {
        
        //Background
        AffineTransform old = g2.getTransform();
        g2.rotate(Math.toRadians(tilt), centerX, centerY);
        if(mission==1)
        {
          g2.drawImage(TGBack1,bx,by,1600,1600,null);
        }
        if(mission==2)
        {
          g2.drawImage(TGBack2,bx,by,1600,1600,null);
        }
        if(mission==3)
        {
          g2.drawImage(TGBack3,bx,by,1600,1600,null);
        }
        
        if (sequence==0)
        {
          if(mission==1){
            g2.drawImage(TGBack2,0,sy2,1600,1600,null);
          }
          g2.drawImage(Runway,0,sy,600,200,null);
          if(mission==1){
            g2.drawImage(BackDrop1,0,sy-600,600,300,null); 
          }
        }
        if (sequence==3)
        {
          if(mission==1){
            g2.drawImage(LandingBack,0,-600,1600,1600,null);
          }
          g2.drawImage(BackDrop1,0,sy,600,300,null); 
        }
        
        //Ships
        if(mission==2){
          for(int i=0;i<numShips;i++)
          {
            if (ships[i].trigger==0){
              g2.drawImage(eships,ships[i].x,ships[i].y,ships[i].sizeW,ships[i].sizeH,null);
            }
            else if (ships[i].trigger==1){
              g2.drawImage(Boom,ships[i].x,ships[i].y,ships[i].sizeW,ships[i].sizeH+ships[i].sizeH,null);
            }
          }
        }
        
        if (mission==3)
        {
          g2.drawImage(Base,aBase.x,aBase.y,aBase.sizeW,aBase.sizeH,null);
          if(destroy==1)
          {
            for(int i=0;i<5;i++)
            {
              int randomx=(int)(Math.random()*300+aBase.x);
              int randomy=(int)(Math.random()*120+aBase.y);
              g2.drawImage(Boom,randomx,randomy,30,30,null);
            }
          }
        }
        
        if(missiles.fired==0 && sequence == 1){
          g2.setColor(Color.RED);
          g2.drawRect(lox,loy,low,loh);
        }
        
        //Clouds
        if (sequence==1|| sequence==2)
        {
          for (int i=0;i<5;i++)
          {
            g2.drawImage(Cloud,clouds[i].x,clouds[i].y,clouds[i].size,clouds[i].size,null);
          }
          
          //Draw Enemy Bullets
          if(mission==2||mission==3)
          {
            for(int a=0;a<numBullets;a++)
            {
              g2.setColor(Color.RED);
              g2.fillOval(eBullets[a].x,eBullets[a].y,eBullets[a].size,eBullets[a].size);
            }
          }
          
          //Draw Enemies
          g2.setColor(Color.GRAY);
          for(int i=0;i<numJets;i++)
          {
            if(mission==1)
            {
              if (eJets[i].trigger==0)
              {
                g2.drawImage(ejets,eJets[i].x,eJets[i].y,eJets[i].sizeW,eJets[i].sizeH,null);
              }
            }
            else if(mission==2||mission==3)
            {
              if (eJets[i].trigger==0)
              {
                g2.drawImage(ejets2,eJets[i].x,eJets[i].y,eJets[i].sizeW,eJets[i].sizeH,null);
              }
            }
            if(eJets[i].trigger==1)
            {
              g2.drawImage(Boom,eJets[i].x,eJets[i].y,eJets[i].sizeW,eJets[i].sizeH+eJets[i].sizeH,null);
            }
          }
        }
        
        g2.setTransform(old);
        
        //Missiles
        if(missiles.fired>0){
          if (dtilt==0 && missiles.shot==1)
          {
            g2.drawImage(missile,missiles.x,missiles.y,missiles.size,missiles.size,null);
          }
          else
          {
            AffineTransform old3 = g2.getTransform();
            g2.rotate(Math.toRadians(tilt), 280, 320);
            
            g2.drawImage(missile,missiles.x,missiles.y,missiles.size,missiles.size,null);
            
            g2.setTransform(old3);
          }
        }
        
        //Draw Bullets
        if (sequence==1|| sequence==0||sequence==2||sequence==3)
        {
          g2.setColor(Color.ORANGE);
          for (int i=0;i<numBullets;i++)
          {
            g2.fillOval(bulletsR[i].x,bulletsR[i].y,bulletsR[i].size,bulletsR[i].size);
          }
          for (int i=0;i<numBullets;i++)
          {
            g2.fillOval(bulletsL[i].x,bulletsL[i].y,bulletsL[i].size,bulletsL[i].size);
          }
          
          //Draw Cockpit
          g2.drawImage(Reticle,195,80,200,200,null);
          g2.drawImage(Dashboard,0,300,600,236,null);
          
          //Radar
          g2.drawImage(Radar1,189,337,222,149,null);
          g2.setColor(Color.RED);
          for(int i=0;i<numJets;i++)
          {
            if (eJets[i].trigger==0)
            {
              g2.fillOval(rJets[i].x,rJets[i].y,rJets[i].sizeW,rJets[i].sizeH);
            }
          }
          g2.drawImage(CoverRadar,189,337,222,149,null);
          g2.drawImage(PInd,px,415,18,13,null);
        }
        if (sequence==0){
          g2.drawImage(TakeOff,189,337,222,149,null);
        }
        if (sequence==2|| sequence==3){
          g2.drawImage(Landing,189,337,222,149,null);
        }
        
        //Lives
        for (int i=0;i<lives;i++)
        {
          g2.fillRect(lx[i],ly[i],16,6);
        }
        //Fuel
        for (int i=0;i<fuel;i++)
        {
          g2.fillRect(18,f2y[i],15,6);
        }
        
        
        AffineTransform old2 = g2.getTransform();
        g2.rotate(Math.toRadians(-tilt), center2X, center2Y);
        g2.drawImage(FInd,72,fy,59,17,null);
        g2.setTransform(old2);
        
        if (sequence==4)
        {
          g2.drawImage(LandingSeq,-43,0,686,600,null);
        }
      }
      
      //Mission Objectives
      if (game==1)
      {
        if (sequence==0||sequence==1)
        {
          if (mission==1)
          {
            g2.setColor(Green);
            g2.setFont(new Font("Agency FB",1,20));
            g2.drawImage(Mission1,0,0,600,600,null);
            g2.drawString(""+kills,125,288);
          }
          if (mission==2)
          {
            g2.setColor(Green);
            g2.setFont(new Font("Agency FB",1,20));
            g2.drawImage(Mission2,0,0,600,600,null);
            g2.drawString(""+killsShip,125,288);
          }
          if(mission==3)
          {
            if (aBase.check<4){
              g2.drawImage(Mission3a,0,0,600,600,null);
            }
            if (aBase.check>=4)
            {
              g2.drawImage(Mission3b,0,0,600,600,null);
              g2.setColor(Color.GRAY);
              g2.fillRect(150,12,300,8);
              g2.setColor(Color.RED);
              g2.fillRect(150,12,hw,8);
              g2.setColor(Color.BLACK);
              g2.setFont(new Font("Arial",1,15));
              g2.drawString("Army Base",270,35);
            }
          }
        }
      }
      
      //GAME OVER
      if (game==3)
      {
        if (success==0)
        {
          g2.drawImage(GameOver,0,0,600,600,null);
        }
        if (success==1)
        {
          g2.drawImage(MFailed,0,0,600,600,null);
        }
        if (success==2)
        {
          g2.drawImage(MComplete,0,0,600,600,null);
          g2.setColor(Green);
          g2.setFont(new Font("Agency FB",1,20));
          g2.drawString("+$300",310,330);
        }
        if (select==0)
        {
          cx=80;
          cy=380;
        }
        if (select==1)
        {
          cx=340;
          cy=380;
        }
        g2.setColor(Green);
        g2.fillOval(cx,cy,15,15);
        g2.setFont(new Font("Agency FB",1,30));
        g2.drawString(""+score,240,210);
        if(success==0){
          earned=score/250;
        }
        else{
          earned=score/100;
        }
        g2.drawString("$"+earned,280,300);
        
        if(mission==1){
          if(h1<score){
            h1=score;
          }
        }
        else if(mission==2){
          if(h2<score){
            h2=score;
          }
        }
        else if(mission==3){
          if(h3<score){
            h3=score;
          }
        }
      }
      
      if (game==4)
      {
        g2.drawImage(Pause,0,0,600,600,null);
        if (select==0)
        {
          cx=200;
          cy=199;
        }
        if (select==1)
        {
          cx=200;
          cy=255;
        }
        if (select==2)
        {
          cx=200;
          cy=315;
        }
        g2.setColor(Color.GREEN);
        g2.fillOval(cx,cy,15,15);
      }
      
      //Highscores
      if(game==5)
      {
        g2.drawImage(Highscores,0,0,600,555,null);
        if(select==0){
          cx=70;
          cy=15;
        }
        if(select==1){
          cx=370;
          cy=465;
        }
        if(select==2){
          cx=400;
          cy=498;
        }
        g2.fillOval(cx,cy,15,15);
        g2.setFont(new Font("Agency FB",1,30));
        FontMetrics fontMetrics = g2.getFontMetrics();
        g.drawString(""+h1, 580 - fontMetrics.stringWidth(""+h1), 170);
        g.drawString(""+h2, 580 - fontMetrics.stringWidth(""+h2), 250);
        g.drawString(""+h3, 580 - fontMetrics.stringWidth(""+h3), 350);
        g.drawString(""+gp, 580 - fontMetrics.stringWidth(""+gp), 430);
      }
      
      //Upgrades Menu
      if (game==6)
      {
        g2.drawImage(Upgrade,0,uy,600,1110,null);
        
        if (select==0)
        {
          cx=110;
          cy=25;
        }
        if (select==1)
        {
          cx=50;
          cy=100;
        }
        if (select==2)
        {
          cx=50;
          cy=330;
          uy=0;
        }
        if (select==3)
        {
          cx=50;
          cy=55;
          uy=-555;
        }
        if (select==4)
        {
          cx=50;
          cy=285;
        }
        if (select==5)
        {
          cx=450;
          cy=490;
        }
        
        if(select<3){
          if(health<1){
            g2.drawImage(hLvl2,250,u2y,140,147,null);
          }
          if(health<2){
            g2.drawImage(hLvl3,395,u3y,140,147,null);
          }
          if(miss<1){
            g2.drawImage(mLvl2,250,u4y,140,147,null);
          }
          if(miss<2){
            g2.drawImage(mLvl3,395,u5y,140,147,null);
          }
        }
        if(select>2){
          if(shoot<1){
            g2.drawImage(sLvl2,250,u6y,140,147,null);
          }
          if(shoot<2){
            g2.drawImage(sLvl3,395,u7y,140,147,null);
          }
          if(move==0){
            g2.drawImage(bLvl2,340,u8y,140,147,null);
          }
        }
        
        g2.setColor(Green);
        g2.fillOval(cx,cy,15,15);
        g2.setFont(new Font("Agency FB",1,30));
        FontMetrics fontMetrics = g2.getFontMetrics();
        g.drawString("$"+money, 580 - fontMetrics.stringWidth("$"+money), 40);
      }
      
      if(game==-1)
      {
        if(select==0||select==1){
          g2.drawImage(PMission1,0,0,600,555,null);
        }
        if(select==2){
          g2.drawImage(PMission2,0,0,600,555,null);
        }
        if(select==3){
          g2.drawImage(PMission3,0,0,600,555,null);
        }
        if (select>0){
          if(mComplete<select-1){
            g2.drawImage(MissionLocked,0,0,600,555,null);
          }
        }
        if(select==0){
          cx=70;
          cy=15;
        }
        if(select>0){
          cx=210;
          cy=460;
        }
        g2.setColor(Color.BLACK);
        g2.fillOval(cx,cy,15,15);
      }
      
      //Controls Menu
      if(game==7)
      {
        g2.drawImage(Controls,0,0,600,555,null);
        if(select==0){
          cx=70;
          cy=15;
        }
        g2.fillOval(cx,cy,15,15);
        if(select==1){
          g2.drawImage(aControls,0,0,600,555,null);
        }
      }
    }
  }
}

