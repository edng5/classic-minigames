import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class HangManV3 extends JFrame implements ActionListener, Runnable{ 
  
  MyDrawPanel drawpanel1;
  Thread th = new Thread (this);
  JButton [] letters=new JButton[26];
  JButton [] functions=new JButton[4];
  String [] blabels={"New Game","Reset Game","Next Category","Random Category"};
  String [] alphabet={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
  String [] W={" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "};
  String [] categories={"Celebrities","Sports","Movies","Brands","Video Games"};
  String [] words={"DRAKE","BEYONCE","KOBE BRYANT","DWAYNE JOHNSON","ADELE","BASKETBALL","HOCKEY","FOOTBALL","SOCCER","BOXING","FAST AND FURIOUS","FINDING NEMO","LION KING","JURASSIC PARK","STAR WARS","NIKE","NISSAN","SUPREME","SONY","SAMSUNG","CALL OF DUTY","SUPER MARIO","POKEMON","HANGMAN","CLASH OF CLANS"};
  String s=" ";
  JLabel title;
  JPanel p1,p2,p3,p4;
  int game=0,x=150,x2=150,y=-50,y2=-50,y3=155,deltay=0,wordlength=0,wrong=0,check=0,paint=0,num=0,wflag=0,c=0,y4=150,interval=0;
  int r=(int)(Math.random()*3) + 0;
  
  Color white = new Color(255,255,255);
  Color black = new Color(50,50,50);
  
  public static void main(String[ ] args) 
  {
    new HangManV3();
  }  
  
  public HangManV3(){
    
    p1=new JPanel();
    p2=new JPanel();
    p3=new JPanel();
    p4=new JPanel();
    
    title=new JLabel("HANGMAN",JLabel.CENTER);
    title.setFont(new Font("Agency FB",1,40));
    title.setBackground(Color.BLACK);
    title.setForeground(Color.RED);
    title.setOpaque(true);
    p1.add(title);
    
    for (int i=0;i<4;i++)
    {
      functions[i] =new JButton(blabels[i]);
      functions[i].addActionListener(this);
      functions[i].setBackground(Color.GRAY);
      functions[i].setForeground(Color.WHITE);
      functions[i].setFont(new Font("Britannic",1,12));
      p4.add(functions[i]);
    }
    
    for (int i=0;i<26;i++)
    {
      letters[i]=new JButton(alphabet[i]);
      p3.add(letters[i]);
      letters[i].setFont(new Font("Comic Sans MS",1,14));
      letters[i].setForeground(Color.RED);
      letters[i].setBackground(white);
      letters[i].setOpaque(true);
      letters[i].setEnabled(false);
      letters[i].addActionListener(this);
    }
    
    p3.setBackground(Color.GRAY);
    
    drawpanel1=new MyDrawPanel();
    p2.add (drawpanel1);
    
    p2.setLayout(new GridLayout(1,1));
    p1.setLayout(new GridLayout(2,1));
    p4.setLayout(new GridLayout(1,4));
    
    this.add(p1);
    this.add(p2);
    this.add(p3);
    
    this.add(p1,BorderLayout.NORTH);
    p1.add(p4,BorderLayout.SOUTH);
    this.add(p2,BorderLayout.CENTER);
    this.add(p3,BorderLayout.SOUTH);
    p1.setPreferredSize(new Dimension(700,70));
    p3.setPreferredSize(new Dimension(700,75));
    
    this.setSize(700,450);
    this.setVisible(true);
    this.setResizable(false);
    
    th.start ();
  } 
  
  public void actionPerformed(ActionEvent e)
  {
    //Category Selection
    if (e.getSource()== functions[2])
    {
      if (c<5)
      {
        c++;
        interval+=5;
      }
      if (c==5)
      {
        c=0;
        interval=0;
      }
    }
    //Random Category
    if (e.getSource()== functions[3])
    {
      c=(int)(Math.random()*5) + 0;
      if (c==0)
      {
        interval=0;
      }
      if (c==1)
      {
        interval=5;
      }
      if (c==2)
      {
        interval=10;
      }
      if (c==3)
      {
        interval=15;
      }
      if (c==4)
      {
        interval=20;
      }
    }
    //Clear
    if (e.getSource()== functions[1])
    {
      game=0;
      wrong=0;
      wflag=0;
      for (int i=0;i<26;i++)
      {
        W[i]=" ";
      }
      for (int i=0;i<26;i++)
      {
        letters[i].setEnabled(false);
      }
      x=150;
      y=-50;
      y2=-50;
      y3=155;
      y4=150;
      paint=0;
      for (int i=0;i<4;i++)
      {
        functions[i].setEnabled(true);
      }
      repaint();
    }
    //Create New Game
    if (e.getSource()== functions[0])
    {
      r=(int)(Math.random()*5) + interval;
      game=1;
      for (int i=0;i<26;i++)
      {
        letters[i].setEnabled(true);
      }
      wordlength=words[r].length();
      for (int i=0;i<wordlength;i++)
      {
        if (s.equals(""+words[r].charAt(i)))
        {
          paint++;
        }
      }
      functions[0].setEnabled(false);
      functions[2].setEnabled(false);
      functions[3].setEnabled(false);
      repaint();
    }
    //Letter Pressed
    for (int i=0;i<26;i++)
    {
      if (e.getSource()== letters[i])
      {
        for (int a=0;a<wordlength;a++)
        {
          if (letters[i].getText().equalsIgnoreCase(""+words[r].charAt(a)))
          {
            check++;
            W[a]=letters[i].getText();
          }
        }
        //Correct
        if (check>0)
        {
          if (check>1)
          {
            paint+=(check-1);
          }
          paint+=1;
          x=150;
          check=0;
          repaint();
        }
        //Incorrect
        else if (check==0)
        {
          wrong++;
          x=150;
          repaint();
        }
        letters[i].setEnabled(false);
      }
    }
  }
  
  
  public void run ()
  { 
    while (true)
    { 
      try
      { 
        
        Thread.sleep (5);
      }
      catch (InterruptedException ex)
      { 
      }
      //GameOver Animation
      if (wflag==1)
      {
        deltay=1;
        if(y==100)
        {
          deltay=0;
        }
        y+=deltay;
      }
      //You Win Animation
      else if (wflag==2)
      {
        deltay=1;
        if(y2==100)
        {
          deltay=0;
        }
        y2+=deltay;
      }
      repaint();
    } 
  }
//  
  class MyDrawPanel extends JPanel {
    
    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
      
      //Background
      g2.setColor(black);
      g2.fillRect(0,0,700,400);
      
      //Category
      g2.setColor(Color.WHITE);
      g2.setFont(new Font("Comic Sans MS",1,15));
      g2.drawString("Category:",350,30);
      g2.setFont(new Font("Comic Sans MS",1,30));
      g2.setColor(Color.WHITE);
      g2.drawString(categories[c],350,70);
      
      //Noose
      g2.setColor(Color.WHITE);
      g2.setStroke(new BasicStroke(3));
      g2.drawLine(50,50,50,250);
      g2.drawLine(50,50,100,50);
      g2.drawLine(100,50,100,75);
      g2.drawLine(20,250,80,250);
      
      //When New Game is pressed
      if(game==1)
      {
        for (int i=0;i<wordlength;i++)
        {
          if (s.equals(""+words[r].charAt(i)))
          {
            y3+=50;
            x=150;
            g2.setColor(black);
            g2.drawRect(x,y3,20,1);
          }
          else
          {
            x+=50;
            g2.setColor(Color.WHITE);
            g2.drawRect(x,y3,20,1);
          }
        }
        y3=155;
        x=150;
        
        //When Letters are pressed
        if (paint>0)
        {
          for (int i=0;i<wordlength;i++)
          {
            if (s.equals(""+words[r].charAt(i)))
            {
              y4+=50;
              x2+=50;
              x2=150;
              g2.drawString(W[i],x2,y4);
            }
            else
            {
              x2+=50;
              g2.setColor(Color.WHITE);
              g2.drawString(W[i],x2,y4);
            }
          }
          y4=150;
          x2=150;
        }
        
        //Win condition
        if (paint==wordlength)
        {
          wflag=2;
        }
        
        //Incorrect Letter
        if (wrong==1)
        {
          g2.setColor(Color.WHITE);
          g2.drawOval(75,75,50,50);
        }
        if (wrong==2)
        {
          g2.setColor(Color.WHITE);
          g2.drawOval(75,75,50,50);
          g2.drawLine(100,125,100,185);
        }
        if (wrong==3)
        {
          g2.setColor(Color.WHITE);
          g2.drawOval(75,75,50,50);
          g2.drawLine(100,125,100,185);
          g2.drawLine(100,155,75,140);
        }
        if (wrong==4)
        {
          g2.setColor(Color.WHITE);
          g2.drawOval(75,75,50,50);
          g2.drawLine(100,125,100,185);
          g2.drawLine(100,155,75,140);
          g2.drawLine(100,155,125,140);
        }
        if (wrong==5)
        {
          g2.setColor(Color.WHITE);
          g2.drawOval(75,75,50,50);
          g2.drawLine(100,125,100,185);
          g2.drawLine(100,155,75,140);
          g2.drawLine(100,155,125,140);
          g2.drawLine(100,185,75,220);
        }
        if (wrong==6)
        {
          g2.setColor(Color.WHITE);
          g2.drawOval(75,75,50,50);
          g2.drawLine(100,125,100,185);
          g2.drawLine(100,155,75,140);
          g2.drawLine(100,155,125,140);
          g2.drawLine(100,185,75,220);
          g2.drawLine(100,185,125,220);
          wflag=1;
        }
      }
      //Game Over
      if(wflag==1)
      {
        g2.drawOval(75,75,50,50);
        g2.drawLine(100,125,100,185);
        g2.drawLine(100,155,75,140);
        g2.drawLine(100,155,125,140);
        g2.drawLine(100,185,75,220);
        g2.drawLine(100,185,125,220);
        g2.drawString("GAME OVER!",150,y);
        for (int i=0;i<26;i++)
        {
          letters[i].setEnabled(false);
        }
      }
      //Win
      if(wflag==2)
      {
        g2.drawString("YOU WIN!",150,y2);
        for (int i=0;i<26;i++)
        {
          letters[i].setEnabled(false);
        }
      }
    }
  }
}
