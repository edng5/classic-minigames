import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;

public class Clouds
{
  int size=0, y_speed=0, x_speed=0, x=0, y=0, delay=0,reset=0, changesize=0;
  Rectangle boundary;
  
  public void create(int w, int h)
  {
    size=1;
    x=300;
    y=100;
    changesize=2;
  
    boundary=new Rectangle(x,y,size,size);
  }
  
  public void move()
  {
    x+=x_speed;
    y+=y_speed;
    delay++;
    if (delay==2){
    size+=changesize;
    delay=0;
    }
    
    boundary=new Rectangle(x,y,size,size);
  }
  
  
  public void checkBoundary(int w, int h)
  {
    if (y>600||y<-60)
    {
      y=(int)(Math.random()*400);
      x=(int)(Math.random()*500);
      size=1;
    }
  }
}