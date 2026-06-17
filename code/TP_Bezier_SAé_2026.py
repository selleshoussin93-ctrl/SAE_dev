# -*- coding: utf-8 -*-


import numpy as np  
import matplotlib.pyplot as plt
import numpy.random as alea

def visu_point(matPoint, style):
    # matPoint contient les coordonnées des points
    x = matPoint[0, :]
    y = matPoint[1, :]
    plt.plot(x, y, style)
    
def visu_segment(P1,P2,style):
    # attention P1 et P2 sont des tableaux (2,1)
    matP = np.concatenate((P1,P2),1)
    visu_point(matP,style)
    
def mat_rotation(theta):
    # si pas besoin des coordonnées homogènes
    mat = np.array([[np.cos(theta), -np.sin(theta)],
                    [np.sin(theta), np.cos(theta)]])
    return mat


def visu_BezierQuad(matPointControl, str):
    n = 50
    t = np.linspace(0, 1., n)
    mat_t = np.ones((3, n))  # que des 1
    mat_t[1, :] = t  # ligne avec les t
    mat_t[2, :] = t*t  # ligne avec les t*t
    matBezier3 = np.array([[1, 0, 0],
                           [-2, 2, 0],
                           [1, -2, 1]])
    matPointligne = (mat_t.T @ matBezier3) @ matPointControl.T
    matPoint = matPointligne.T  # on transpose

    visu_point(matPointControl, 'b:')
    visu_point(matPoint, str)
    visu_point(matPointControl, 'k.')
    
    
    
    # Définir 3 points de contrôle (chaque colonne = un point [x, y])
matPointControl = np.array([[100, 200, 350],   # coordonnées x
                                [300,  50, 300]])  # coordonnées y
    
    # Afficher la courbe                 
plt.figure()
               
visu_BezierQuad(matPointControl, 'r-')
plt.axis('equal')
plt.grid(True)
plt.title('Courbe de Bézier quadratique')
    
plt.show()