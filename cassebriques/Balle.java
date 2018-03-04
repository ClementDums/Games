import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Balle {
	int nbballes = 3;
	int larg, haut;
	Color CouleurBalle;
	double x,y;
	double dx=1;
	double dy=-1;
	boolean pause=true;
	boolean lost=false;
	
	public Balle(int xParam, int yParam, int largParam, int hautParam) {
		x = xParam;
		y = yParam;
		larg = largParam;
		haut = hautParam;
		
		CouleurBalle = Color.black;
	}
	
	public void bouge() {
		
		if(!pause)
		{
			
			if (x>690 || x < 10) {
				
				dx = -dx;
			 }
			if (y<110 ) {
				
				dy = -dy;
			 }
			if(y>600) {
				
				lost=true;
				
			}
			x = x + dx;
			y = y + dy; 
			
		}
	}
	
	
	
	public void dessiner(Graphics g) {
		g.setColor(CouleurBalle);
		g.fillOval((int) x, (int) y,larg,haut);

	}
	//hitbox verticale
	public Rectangle GetBounds() {
		
		return new Rectangle((int)(x+larg/4),(int)y, (int)(larg/2), haut);
	}
	//hitboxhorizontale
	public Rectangle GetBoundsx() {
		return new Rectangle((int)x,(int)(y+larg/4), larg, (int)(haut/2));
	}
}
