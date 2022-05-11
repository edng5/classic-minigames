import java.awt.Rectangle;

public class Missile
{
  int sizeW=0, sizeH=0, y_speed=0, x=0, y=0;
  Rectangle boundaryM;
  
  public void createM(int w, int h)
  {
    sizeW=3;
    sizeH=10;
    y_speed=0;
    x=150;
    y=595;
    
    boundaryM=new Rectangle(x,y,sizeW,sizeH);
  }
  
  public void moveM()
  {
    y+=y_speed;
    boundaryM=new Rectangle(x,y,sizeW,sizeH);
  }
  
  public void checkBoundaryM(int w, int h)
  {
      y=595;
      y_speed=0;
  }
}