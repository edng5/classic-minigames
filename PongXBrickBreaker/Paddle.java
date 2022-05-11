import java.awt.Rectangle;

public class Paddle
{
  int sizeW=0, sizeH=0, x=0, y=0, x_speed=0;
  Rectangle boundaryT,boundaryL,boundaryR;
  
  public void create(int w, int h)
  {
    sizeW=150;
    sizeH=15;
    x_speed=0;
    x=200;
    y=600;
    
    boundaryT=new Rectangle(x,y,sizeW,sizeH-12);
    boundaryL=new Rectangle(x,y,sizeW-149,sizeH);
    boundaryR=new Rectangle(x+149,y,sizeW-149,sizeH);
    
  }
}