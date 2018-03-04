import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Briques {
	int x,y,larg,haut, bordure,type,pv;
	int cpt = 0;
	boolean vivant;
	Rectangle zonecolli;
	Color couleurBrique, couleurBord; 
	public String score;
	public Briques(int xParam, int yParam, int largParam, int hautParam, int bordureParam, int typeParam) {
		x = xParam;
		y = yParam;
		larg = largParam;
		haut = hautParam;
		bordure = bordureParam;
		vivant = false;
		type=typeParam;
		if(type==1){
			couleurBrique = new Color(0,255,0);
			pv=1;
		}
		if(type==2){
			couleurBrique = new Color(255,255,0);
			pv=2;
		}
		couleurBord = new Color(0,0,0);
		zonecolli = new Rectangle(x,y,larg,haut);
	}
	
	public void dessiner(Graphics g) {
		if(vivant) {
			g.setColor(couleurBord);
			g.fillRect(x-bordure, y-bordure, larg+2*bordure, haut+2*bordure);
			g.setColor(couleurBrique);
			g.fill3DRect(x, y, larg, haut, true);
		}
		
	}
	public boolean collision(Rectangle r) {
		if(r.intersects(zonecolli)) {
			pv--;
			couleurBrique = Color.green;
			if(pv==0) {
				vivant=false;

			}
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	

}
