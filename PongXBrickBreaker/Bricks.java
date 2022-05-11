import java.awt.Rectangle;

public class Bricks
{
  int sizeW=0, sizeH=0, x=0, y=0;
  Rectangle boundaryT,boundaryB,boundaryL,boundaryR;
  
  public void create(int w, int h)
  {
    sizeW=50;
    sizeH=30;
    x=0;
    y=35;
    
    boundaryT=new Rectangle(x,y,sizeW,sizeH-28);
    boundaryB=new Rectangle(x,y+28,sizeW,sizeH-28);
    boundaryL=new Rectangle(x,y,sizeW-49,sizeH);
    boundaryR=new Rectangle(x+49,y,sizeW-49,sizeH);
    
  }
}