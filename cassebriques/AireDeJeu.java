import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// déclaration de la classe "AiredeJeu"
public class AireDeJeu {
	Raquette r1; 
	Balle ba1;
	Briques b1;
	Briques [][] tableauBriques;
	public String score;
	public int cpt;
	public int  goal = 50;
	public int briquesRestantes;
	public int x,y;
	public int larg, haut;
	public int bordure;
	public boolean begin = true;
	public Color couleurAireDeJeu, couleurBord;
	public int largeur_brique =53;
	public int hauteur_brique = 30;
	public String level[] = {"level1.txt","level2.txt"};
	public int nblevel = 0;
	public AireDeJeu(int xParam, int yParam, int largParam, int hautParam, int bordureParam) {
		x = xParam;
		y = yParam;
		larg = largParam;
		haut = hautParam;
		bordure = bordureParam;
		
		couleurAireDeJeu = Color.white;
		couleurBord = Color.black;
		
		r1 = new Raquette(350,560,100,20);
		ba1 = new Balle(150,420,20,20);
		lancement();//lancement niveau
		
		
	}
	
	public void dessiner(Graphics g) {
		// dessin du bord de l'aire de jeu, un rectange aux dimensions de l'aire + le bord
		
		g.setColor(couleurBord);
		g.fillRect(x-bordure, y-bordure, larg+2*bordure, haut+2*bordure);
		g.setColor(couleurAireDeJeu);
		g.fill3DRect(x, y, larg, haut, true);
		
		r1.dessiner(g);
		ba1.dessiner(g);
		
		for (int i= 0; i < 13; i++) {
			for (int j = 0; j < 10; j++) {
				if (tableauBriques[i][j]!= null){
					
				
				tableauBriques[i][j].dessiner(g);
				}
			}
		}
	}
	

	public void testCollision() {
		Rectangle raqudessus = r1.getBounds();
		Rectangle rballey = ba1.GetBounds();
		Rectangle rballex = ba1.GetBoundsx();
		if(rballey.intersects(raqudessus)) {
			
			ba1.dx=((ba1.x-r1.x)-r1.larg/2)*0.05;
			ba1.dy=-ba1.dy;
		}
		if(rballex.intersects(r1.getBoundsxleft())||rballex.intersects(r1.getBoundsxright())) {
			
			ba1.dx=-ba1.dx;
		}
		
		for(int i=0;i<13;i++) {
			for(int j=0;j<10;j++) {
				if(tableauBriques[i][j]!=null){
					if(tableauBriques[i][j].vivant) {
						//collision brique horizontal
						if(tableauBriques[i][j].collision(rballey)) {
							ba1.dy=-ba1.dy;
							cpt=cpt+1; 
							if (cpt==goal) {
								cpt=0;
								goal();
							}
							
						}
						//collision brique vertical
						if(tableauBriques[i][j].collision(rballex)) {
							ba1.dx=-ba1.dx;
							cpt=cpt+1; 
							if (cpt==goal) {
								cpt=0;
								goal();
							}
							
						}
					}
				}
			}
		}
	}
	
	public void lancement() {
		//lecture fichier
				String[][] lvl = new String[13][10];
				try {
					System.out.println("Chargement du niveau...");
					FileInputStream fstream = new FileInputStream(level[nblevel]);
					DataInputStream in = new DataInputStream(fstream);
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					String strLine;
					int numligne=0;			 
					 

					while ((strLine = br.readLine())!=null) {
					
						
						String[] tampon= strLine.split(";"); //saucissonnage de la ligne
						for(int i=0;i<13;i++) {
							lvl[i][numligne]=tampon[i];
						}
						
						numligne++;
						
					}
				
				in.close();
				}catch (Exception e) {
					System.err.println("Error: " + e.getMessage());
				}
				
				
				tableauBriques = new Briques[13][10];
				for (int i = 0; i < 13; i++) {
					for (int j = 0; j < 10; j++) {
						
						if(lvl[i][j].equals("O")) {
							tableauBriques[i][j] = new Briques(i*53+i,j*30+j+50, 50, 20, 0,1);
							tableauBriques[i][j].vivant=true; 
						}
						if(lvl[i][j].equals("X")){
							tableauBriques[i][j] = new Briques(i*53+i,j*30+j+50, 50, 20, 0,2);
							tableauBriques[i][j].vivant=true; 
						}
					}
				}
				System.out.println("Appuyez sur espace pour lancer le jeu ! ");
	}
	public void goal() {
		
		System.out.println("Vous avez gagné !!! Changement de niveau en cours... Bonne chance");
		//changement du niveau
		nblevel++;
		lancement();
		ba1.y=420;
		ba1.x=350;
		ba1.dx=0;
		ba1.dy=1;
		ba1.lost=false;
		begin=true;//pause
		ba1.pause=true;
		cpt=0;
	}
	
	public void lost() {
		
		ba1.nbballes--;
		System.out.println(ba1.nbballes);
		if(ba1.nbballes==0) {//si on a plus de balles
			System.out.println("Vous avez perdu...");
			lancement();
			ba1.x=350;//reinitialisation pos balle
			ba1.y=420;
			ba1.dx=0;
			ba1.dy=1;
			ba1.lost=false;
			begin=true;//pause
			ba1.pause=true;
			cpt=0;
			ba1.nbballes=3;
		}else {//si il reste des balles
			
			ba1.x=350;//reinitialisation pos balle
			ba1.y=420;
			ba1.dx=0;
			ba1.dy=1;
			ba1.lost=false;
			begin=true;//pause
			ba1.pause=true;
			System.out.println("Appuyez sur espace pour lancer le jeu ! ");
		}
	}
	//méthode chargement level à partir fichier texte
	
	/*public void levelLoad(int _numLevel, Briques[][] _tab) {
		String ligne,Fichier;
		int i,j;
		int numLevel = _numLevel;
		try {//création et ouverture d'un flux de lecture
			BufferedReader tamponFichier =new BufferedReader(new InputStreamReader(new FileInputStream("level1.txt")));
			//lecture d'une ligne
			ligne = tamponFichier.readLine();
			//passage des commentaires commençant pas "#"
			while (ligne.startsWith("#")==true) {ligne=tamponFichier.readLine();}
			//lecture du nombre de briques d'un niveau
			ligne= tamponFichier.readLine();
			System.out.println(ligne);
			//transformation de la chaine de caractère en valeur entière
			//briquesRestantes = new Byte(ligne).intValue();//permet de connaitre le nombre de brique à détruire
			//passage des commentaires
			ligne = tamponFichier.readLine();
			while (ligne.startsWith("#")==true) {
				ligne=tamponFichier.readLine();
			}
			//affichage dans la fenêtre system d'informations de debuggage
			System.out.println("Chargement du niveau");
			//passage de la ligne de commentaire annonçant le niveau
			ligne =tamponFichier.readLine();
			
			//traitement de la 1ère ligne avec tokenizer
			//séparateur : ';'
			i=0; j=0;
			char type_brique='A';
			while (ligne.startsWith(";")==false)
			{
				StringTokenizer st = new StringTokenizer(ligne,";");
				while(st.hasMoreTokens()) {
					String token_suivant = st.nextToken();
					//remplissage du tableau TBriques
					if (i>13)type_brique= (token_suivant).charAt(0);
					switch(type_brique)
					{
					case 'O':
						_tab[i][j]=null;
						break;
					case 'A':
						_tab[i][j]=new Briques(50+i*largeur_brique,50+j*hauteur_brique, 50, 20, 0,Color.blue);
						System.out.println("yo");
						break;
					case 'B':
						_tab[i][j]= new Briques(50+i*largeur_brique,50+j*hauteur_brique, 50, 20, 0, Color.white);
						break;
						
					}
				i++;
				}
				ligne=tamponFichier.readLine();
				j++;//ligne suivante
				i=0;//remise zéro compteur
			
			}
			tamponFichier.close();
			
	}
		catch(IOException e) {
			System.out.println("Erreur lecture fichier niveau");
			System.exit(0);
		}
		
	}*/
}