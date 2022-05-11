import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;

public class Ships
{
  int sizeW=0, sizeH=0, y_speed=0, x_speed=1, x=0, y=0, delay=0, alive=1,shot=0,health=5, trigger=0,deathTimer=0;
  Shape boundary;
  
  public void create(int w, int h)
  {
    sizeW=30;
    sizeH=15;
    x_speed=2;
//    if(y<10)//Change size field of depth
//    {
//      sizeW=20;
//      sizeH=10;
//    }
    
    boundary=new Rectangle(x,y,sizeW,sizeH);
  }
  
  public void move()
  {
    delay++;
    if (delay==2)
    {
    x+=x_speed;
    delay=0;
    }
    boundary=new Rectangle(x,y,sizeW,sizeH);
  }
  
  public void dead()
  {
    if (health<=0)
    {
      x=(int)(Math.random()*20)-100;
      health=5;
      trigger=0;
    }
  }
  
  public void checkBoundary(int w, int h)
  {
    if (x>600)
    {
      x=(int)(Math.random()*60)-100;
      health=5;
//      y=(int)(Math.random()*140)+150;
    }
  }
}