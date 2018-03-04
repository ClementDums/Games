import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Raquette {
	public int x, y, larg, haut;
	public Color couleurRaquette; 
	
	public Raquette(int xParam, int yParam, int largParam, int hautParam) {
		x = xParam;
		y = yParam;
		larg = largParam;
		haut = hautParam;
		couleurRaquette = new Color(0, 149, 149);
		
	}
	//rectangle horizon
	public Rectangle getBounds() {
		return new Rectangle(x,y, larg, 2);
	}
	//rectangles cotés
	public Rectangle getBoundsxleft() {
		return new Rectangle(x,y, 2, haut-2);
	}
	public Rectangle getBoundsxright() {
		return new Rectangle(larg+x,y, 2, haut-2);
	}
	
	public void majPOS(int xSouris) {
		if(xSouris>0 && xSouris<602)
			x = xSouris;
	}
	
	
	public void dessiner(Graphics g) {
		g.setColor(couleurRaquette);
		g.fillRect(x, y, larg, haut);
		g.setColor(Color.green);
		g.fillRect(x, y, larg, 2);
		g.setColor(Color.red);
		g.fillRect(x,y+2, 2, haut-2);
		g.fillRect(larg+x,y+2, 2, haut-2);
	}
}
