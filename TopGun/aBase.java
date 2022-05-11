import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;

public class aBase
{
  int sizeW=0,sizeH=0, y_speed=0, x_speed=0, x=0, y=0, delay=0,delay2=0,reset=0, changesizeW=0,changesizeH=0,stop=0,go=0,leave=0,trigger=0,deathTimer=0, range1=300, range2=100,initial=-1,check=0,health=100;
  Shape boundary;
  
  public void create(int w, int h)
  {
    sizeH=4;
    sizeW=10;
    x=150;
    y=900;
    y_speed=-1;
    x_speed=-3;
    changesizeW=3;
    changesizeH=1;
  
    boundary=new Rectangle(x,y,sizeW,sizeH);
  }
  
  public void appear()
  {
    delay++;
    delay2++;
    if (delay==5){
    sizeW+=changesizeW;
    sizeH+=changesizeH;
    delay=0;
    }
    if (delay2==10){
      y+=y_speed;
      x+=x_speed;
      delay2=0;
    }
    boundary=new Rectangle(x,y,sizeW,sizeH);
  }
}
