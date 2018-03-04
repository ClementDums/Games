// Librairies importées

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.Rectangle;

public class MMIbric extends JFrame implements KeyListener, Runnable, MouseListener, MouseMotionListener {
	
	// déclaration des variables globales
	
	private Thread processus;
	private Image DoubleBuffer;
	private Graphics gBuffer;
	private String score;
	private String vies;
	AireDeJeu a1;

	// écran de jeu
	
	public MMIbric(){
	
		super("Mminoïd 2017-2018");
		a1 = new AireDeJeu(0,0,700,600,0);
		this.setSize(800,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		
		
		
		processus = new Thread(this);
		processus.start();
		
		DoubleBuffer = createImage(800, 600);
		gBuffer = DoubleBuffer.getGraphics();
		
	}
	
	// texte en haut

	public static void main(String[] args){
		MMIbric monJeu = new MMIbric();
	} 
	
	// fond du jeu
	
	public void paint(Graphics g) {
		gBuffer.setColor(Color.black);
		gBuffer.fillRect(0,0,800,600);
		gBuffer.setColor(Color.white);
		score =Integer.toString(a1.cpt);//int to string
		gBuffer.drawString("Score", 705, 200);
		 gBuffer.drawString(score, 735, 240);
		 gBuffer.drawString("Vies", 720, 405);
		 vies=Integer.toString(a1.ba1.nbballes);
		 gBuffer.drawString(vies, 735, 450);
		a1.dessiner(gBuffer);
		g.drawImage(DoubleBuffer,0,0,this);
		Font font = g.getFont().deriveFont( 30.0f );
	    gBuffer.setFont( font );
	    gBuffer.setColor(Color.white);
	   
	    gBuffer.drawImage(DoubleBuffer,0,0,this);
	}

	// configurations souris 
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) { // déplacement de la raquette avec la souris
		// TODO Auto-generated method stub
		int xSouris = arg0.getX();
		a1.r1.majPOS(xSouris);
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void run() { // boucle infinie pour déplacer la balle
		// TODO Auto-generated method stub
		while(true) {
			try {
				if(a1.ba1.lost==false){
					a1.testCollision();
					a1.ba1.bouge();
		
				processus.sleep(5);
				}else {
					a1.lost();
				}
			}
			catch(Exception ex) {
				
			}
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		switch(key) {
		case KeyEvent.VK_DOWN :
		System.out.println("bas");
		
		this.repaint();
		break;
		
		case KeyEvent.VK_RIGHT : 
		System.out.println("droite");
		this.repaint();
		break;
		
		case KeyEvent.VK_LEFT :
		System.out.println("gauche");
		this.repaint();
		break;
		
		case KeyEvent.VK_UP :
		System.out.println("haut");
		this.repaint();
		break;
		
		case KeyEvent.VK_SPACE : 
		if (a1.begin){
			a1.ba1.pause=!a1.ba1.pause;
			a1.begin=false;
		}
		this.repaint();
		break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}