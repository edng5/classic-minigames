import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;

public class EnemiesJ
{
  int sizeW=0,sizeH=0, y_speed=0, x_speed=0, x=0, y=0, delay=0,delay2=0,reset=0, changesizeW=0,changesizeH=0,stop=0,go=0,leave=0,trigger=0,deathTimer=0, range1=300, range2=100;
  Shape boundary;
  
  public void create(int w, int h)
  {
    sizeH=1;
    sizeW=3;
    x=300;
    y=100;
    changesizeW=2;
    changesizeH=1;
  
    boundary=new Rectangle(x,y,sizeW,sizeH);
  }
  
  public void move()
  {
    if (stop==0)
    {
    x+=x_speed;
    y+=y_speed;
    delay++;
    if (delay==2){
    sizeW+=changesizeW;
    sizeH+=changesizeH;
    delay=0;
    }
    }
    
    boundary=new Rectangle(x,y,sizeW,sizeH);
  }
  
  
  public void checkBoundary(int w, int h)
  {
    if (y>400||y<-100||x>600||x<-100)
    {
      y=(int)(Math.random()*range1)+range2;
      x=(int)(Math.random()*400)+50;
      sizeW=3;
      sizeH=1;
      stop=0;
      trigger=0;
      leave=0;
      go=0;
    }
    else if (sizeW>25 && go<80 && leave==0)
    {
      if(y<200 && y>50)
      {
        stop=1;
      x_speed=0;
      y_speed=0;
    }
    }
  }
}
