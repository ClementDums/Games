#coding: utf-8
from Tkinter import *#Importation de l'outil ,comme pour Turtle
from random import *
from time import sleep
fen1 = Tk()   #Creation de la fenetre principale
fen1.attributes('-fullscreen', 1)#Fenetre en plein écran 
#---------------Definitions---------------#

#Initialisation
def initMainTableau() :
    global T
    T = []
    L = []
    for i in range(40) :#initialisation du tableau à 2 dimensions
        T.append(L) 
        for j in range(40):
            if i == 0 or i == 40-1 or j == 0 or j == 40-1:
                L.append(0) #on remplit les bords de vides
            else :
                L.append(randint(0,1)) #On place aléatoirement des arbres ou du vide
        L = []
    return T

def initTabCendre():
    global U
    U = []
    E = []
    for i in range(40):
        U.append(E)
        for j in range (40):
            E.append(3)
        E = []


def feu():
    global T
    x=randint(0,40-1)
    y=randint(0,40-1)
    while T[y][x] != 2: #On cherche un arbre pour l'enflammer
        if T[y][x] == 1: 
            T[y][x]=2
        else: #Si ce n'est pas un arbre on cherche une autre case
            x=randint(0,40-1)
            y=randint(0,40-1)
    
    return T

#Propagation 

def temps():
    global T
    for i in range(40):
        for j in range (40): 
            if T[i][j] == 2: #On cherche les cases "feu"
                if T[i][j+1]==1:
                    T[i][j+1]=4 #on modifie les cases adjacentes
                if T[i][j-1]==1:
                    T[i][j-1]=4
                if T[i+1][j]==1:
                    T[i+1][j]=4
                if T[i-1][j]==1:
                    T[i-1][j]=4
    for i in range(40):
        for j in range (40): 
            if T[i][j] == 4:
                T[i][j] = 2
    return T


def TimCendre():
    
    for i in range(40):
        for j in range (40): 
            if T[i][j] == 2: 
                U[i][j] = U[i][j] - 1
                if U[i][j] == 0 :
                    T[i][j] = 3
    return U

def avancement() :#Boucle permettant la propagation automatique du feu
    pions()
    TimCendre()
    temps()

#Interface graphique

def quadrillage() :
    xl=0
    for i in range(40):#Boucle de creation des traits de lignes et colonnes
        can.create_line(xl, 0, xl, 800, fill ='black')
        can.create_line(0, xl, 800, xl, fill ='black')
        xl=xl+20

def pions() :
    global T
    x=0 #Initialisation du coordonées de départ 
    y=0
    n=0
    c=0
    for i in range(40): #Double boucles afin de generer les pions de depart dans une grille
        for j in range(40):
            if T[i][j] == 0 :
                can.create_rectangle(x,y,x+20,y+20,fill='white') #Canvas générant les pions
            if T[i][j] == 1 :
                can.create_rectangle(x,y,x+20,y+20,fill='green') #Canvas générant les pions
            if T[i][j] == 2 :
                can.create_rectangle(x,y,x+20,y+20,fill='red') #Canvas générant les pions
                n=n+1#Détection du nombre de cases en feu
            if T[i][j] == 3 :
                can.create_rectangle(x,y,x+20,y+20,fill='grey') #Canvas générant les pions
                c=c+1#Détection du nombre de cases en cendre
            if T[i][j] == 4 :
                can.create_rectangle(x,y,x+20,y+20,fill='red') #Canvas générant les pions    
                n=n+1
            x=x+20
        x=0
        y=y+20
    if n==0 and c>=1:#Si il n'y a plus de feu mais présence de cendre
        win()#Fonction de fin de partie
    else :
        fen1.after(500,avancement)#Sinon on continue la propagation


def win() : 
    win = Tk()
    winm = Message(win, width=200, justify =CENTER,font ="Century",text ='La simulation est terminée').pack()#Effacement de la fenêtre après 2 secondes
    fen1.after(3000,Recommencer) #La partie recommence
#Lancements 

def boutonla():#Bouton de lancement du jeu
    global bouton
    bouton = Button(fen1, text='Lancer la Partie', command = lancement)
    bouton.pack()

def lancement() :#On lance la boucle ,on supprime le bouton et on initialise les tableaux
    can.delete('all')#On supprime ce qu'il y a sur le canevas
    quadrillage()
    initMainTableau()
    initTabCendre()
    feu()
    avancement()
    bouton.destroy()
    

    

def Recommencer() :
    
    #On réinitialise le programme
    boutonla()
    
#Menus
 
def Apropos():
    info = Tk()#New fenetre
    w = Message(info,width=200, justify =CENTER,font ="Century",text ='Projet ISN 2016 Simulation Incendie , ROBIN Tangui , DUMAS Clément, Sous Python 2.7 avec Tkinter').pack()
 #Creation d'un message de taille 200, centre


#---------------Menu deroulant---------------#
mainmenu = Menu(fen1)#Creation d'un menu
 
menuJeu = Menu(mainmenu)  #Menu deroulant
menuJeu.add_command(label = "A propos" , command = Apropos)
menuJeu.add_separator()#Ligne separatrice

menuJeu.add_command(label = "Quitter" , command = fen1.destroy)
mainmenu.add_cascade(label = "Jeu", menu = menuJeu)#Menu cascade
Button(fen1, text ='Quitter', command = fen1.destroy).pack(side=BOTTOM, padx=5, pady=5)
 
fen1.config(menu = mainmenu)
 
#---------------Creation du Jeu---------------#
n=0 
can = Canvas(fen1, width =800, height =800, bg ='ivory')#Creation d'un caneva dans la fenetre
can.pack(side =TOP, padx =5, pady =5)#Affichage du caneva dans la fenetre

boutonla()#Bouton de lancement
fen1.mainloop()#Boucle de la fenetre
