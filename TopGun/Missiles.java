import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;

public class Missiles
{
  int size=0, y_speed=0, x_speed=0, x=0, y=0, delay=0, changesize=0, reset=0, shot=0,fired=0;
  Shape boundary;
  
  public void create(int w, int h)
  {
    size=30;
    y_speed=0;
    x_speed=0;
    x=280;
    y=320;
    changesize=0;
  
    boundary=new Rectangle(x,y,size,size);
  }
  
  public void move()
  {
    x+=x_speed;
    y+=y_speed;
    delay++;
    if (delay==3){
    size+=changesize;
    delay=0;
    }
    
    boundary=new Rectangle(x,y,size,size);
  }
  
  public void returnMissile()
  {
    x_speed=0;
      y_speed=0;
      y=320;
      reset=1;
      changesize=0;
      size=30;
      shot=0;
      fired=0;
  }
}