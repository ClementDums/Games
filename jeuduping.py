###################################

#Tangui Robin
#Clément Dumas
#Jeu du Ping
#2015-2016


#################################

try:
    # for Python2
    from Tkinter import *   ## notice capitalized T in Tkinter 
except ImportError:
    # for Python3
    from tkinter import *   ## notice lowercase 't' in tkinter here# coding: utf8
fen1 = Tk()   #Creation de la fenetre principale
fen1.resizable(width=FALSE, height=FALSE)#Fenetre de taille inchangeable
#---------------Definitions---------------#

def quadrillage() :
    xl=100
    for i in range(3):#Boucle de creation des traits de lignes et colonnes
        can.create_line(xl, 0, xl, 400, fill ='black')
        can.create_line(0, xl, 400, xl, fill ='blue')
        xl=xl+100

def pions() :
    x=50 #initialisation du coordonées de départ 
    y=50
    r=40 #rayon d'un pion
    for i in range(4): #Double boucles afin de generer les pions de depart dans une grille
        for j in range(4):
            can.create_oval(x-r,y-r,x+r,y+r,fill='red',width=3,outline="black", tag= 0) #Canvas générant les pions
            x=x+100
        x=50
        y=y+100      

def Recommencer() :
    can.delete('all')
    initableau()
    quadrillage()
    pions()
 

def initableau(): #Creation tableau rempli de "red"
    global T
    global L
    T = []#Lignes
    L = []#Colonnes
    for ligne in range(4) :
        T.append(L)
        for colone in range(4):
            L.append("red")
        L = []

def Click(event) :
    n=0
    global T
    global L
    x = event.x #Les coordonnees du click vont dans x et y 
    y = event.y
    
    sprite_x = int(x/100) #les coordonnees sont divisés par 100
    sprite_y = int(y/100)

    if x >399 or y >399 : #Le click doit etre dans le canevas
        return
        
    if sprite_y-1 >= 0  :#Si il y a un sprite en y-1
        if T[sprite_y-1][sprite_x] =="red":#Si le sprite d'au dessus est rouge
            couleur= "blue"#La variable couleur devient bleue
        else :
            couleur = "red"#Sinon rouge...
            
        T[sprite_y-1][sprite_x] = couleur#Affection du sprite a la variable couleur
        can.create_oval(sprite_x*100+10,(sprite_y-1)*100+10, sprite_x*100+90,(sprite_y-1)*100+90, fill = couleur, width = 3)#Changement de couleur du pion

    #Repetition des changements de couleur en fonction de la position , a cote ou non d un rebord  
    if sprite_x-1 >= 0 and sprite_y-1 >= 0:
        if T[sprite_y-1][sprite_x-1] == "red":
            couleur = "blue"
        else :
            couleur = "red"
        T[sprite_y-1][sprite_x-1] = couleur
        can.create_oval((sprite_x-1)*100+10,(sprite_y-1)*100+10, (sprite_x-1)*100+90,(sprite_y-1)*100+90, fill = couleur, width = 3)        
        
    if sprite_x -1 >= 0 :
        if T[sprite_y][sprite_x-1] == "red" :
            couleur = "blue"
        else :
            couleur = "red"
        T[sprite_y][sprite_x-1] = couleur
        can.create_oval((sprite_x-1)*100+10,(sprite_y)*100+10, (sprite_x-1)*100+90,(sprite_y)*100+90, fill = couleur, width = 3)         
        
    
    if sprite_x-1 >= 0 and sprite_y +1 <= 3 :
        if T[sprite_y+1][sprite_x-1] == "red" : 
            couleur = "blue"
        else : 
            couleur = "red"
        T[sprite_y+1][sprite_x-1] = couleur
        can.create_oval((sprite_x-1)*100+10,(sprite_y+1)*100+10, (sprite_x-1)*100+90,(sprite_y+1)*100+90, fill = couleur, width = 3)            
        
    
    if sprite_y +1 <= 3 : 
        if T[sprite_y+1][sprite_x] == "red": 
            couleur = "blue"
        else : 
            couleur = "red"
        T[sprite_y+1][sprite_x] = couleur
        can.create_oval((sprite_x)*100+10,(sprite_y+1)*100+10, (sprite_x)*100+90,(sprite_y+1)*100+90, fill = couleur, width = 3)            
        
    
    if sprite_y+1 <= 3 and sprite_x +1 <= 3 :
        if T[sprite_y+1][sprite_x+1] == "red": 
            couleur = "blue"
        else : 
            couleur = "red"
        T[sprite_y+1][sprite_x+1] = couleur
        can.create_oval((sprite_x+1)*100+10,(sprite_y+1)*100+10, (sprite_x+1)*100+90,(sprite_y+1)*100+90, fill = couleur, width = 3)            
        
    if sprite_x +1 <= 3 : 
        if T[sprite_y][sprite_x+1] == "red" : 
            couleur = "blue"
            
        else : 
            couleur = "red"
        T[sprite_y][sprite_x+1] = couleur
        can.create_oval((sprite_x+1)*100+10,(sprite_y)*100+10, (sprite_x+1)*100+90,(sprite_y)*100+90, fill = couleur, width = 3)            
        
    if sprite_y -1 >= 0 and sprite_x +1 <= 3 : 
        if T[sprite_y-1][sprite_x+1] == "red":
            couleur = "blue"
            
        else : 
            couleur = "red"
        T[sprite_y-1][sprite_x+1] = couleur
        can.create_oval((sprite_x+1)*100+10,(sprite_y-1)*100+10, (sprite_x+1)*100+90,(sprite_y-1)*100+90, fill = couleur, width = 3)
        
    if T[0][0]=="blue" and T[0][1]=="blue" and T[0][2]=="blue" and T[0][3]=="blue" and T[1][0]=="blue" and T[1][1]=="blue" and T[1][2]=="blue" and T[1][3]=="blue" and T[2][0]=="blue" and T[2][1]=="blue" and T[2][2]=="blue" and T[2][3]=="blue" and T[3][0]=="blue" and T[3][1]=="blue" and T[3][2]=="blue" and T[3][3]=="blue" :
        win = Tk()
        gagne = Message(win,width=300, justify =CENTER,font ="Century",text ='Felicitation vous avez gagné!').pack()
        Button(win, text ='Quitter', command = win.destroy).pack(side=BOTTOM, padx=5, pady=5)
def Aide():#Message d'aide,fond bleu , couleur de texte ivoire
    regle = Tk()
    m = Message(regle, width=1000, bg="blue",fg ="ivory",font ="Helvetica 10 bold", justify =CENTER, text ="Sur une grille carree (4x4 ou plus) sont places des"\
" pions ayant une face rouge et une face bleue.\n Initialement tous les pions presentent leur face rouge."\
" A chaque tour, le joueur choisit une case.\n  Tous les pions qui l entourent sont alors retournes. Le but"\
" du jeu est d obtenir que la totalite des pions presentent leur face rouge.").pack()
 
def delete(tag):
    can.delete(fen1,tag)
 
def Apropos():
    info = Tk()#New fenetre
    w = Message(info,width=200, justify =CENTER,font ="Century",text ='Projet ISN 2015 Jeu de Ping , ROBIN Tangui , DUMAS ClÃƒÂ©ment, Sous Python 2.7 avec Tkinter').pack()
 #Creation d'un message de taille 200, centre
#---------------Menu deroulant---------------#
mainmenu = Menu(fen1)#Creation d'un menu
 
menuJeu = Menu(mainmenu)  #Menu deroulant
menuJeu.add_command(label = "A propos" , command = Apropos)
menuJeu.add_separator()#ligne separatrice
menuJeu.add_command(label = "Aide" , command = Aide)
menuJeu.add_separator()
menuJeu.add_command(label = "Recommencer" , command = Recommencer)
menuJeu.add_command(label = "Quitter" , command = fen1.destroy)
mainmenu.add_cascade(label = "Jeu", menu = menuJeu)#Menu cascade
Button(fen1, text ='Quitter', command = fen1.destroy).pack(side=BOTTOM, padx=5, pady=5)

#Commande de fermeture  au bouton quitter
 
fen1.config(menu = mainmenu)
 
#---------------Creation du Jeu---------------#
n=0 
can = Canvas(fen1, width =400, height =400, bg ='ivory')#Creation d'un caneva dans la fenetre
can.bind("<Button-1>",Click)#Ajout de la commande du click associe a l'evenement "click"
can.pack(side =TOP, padx =5, pady =5)#Affichage du caneva dans la fenetre

quadrillage()#Appel de la definition du quadrillage
pions()#Appel de la definition des pions
initableau()#Appel de la definition du tableau
fen1.mainloop()#Boucle de la fenetre 
