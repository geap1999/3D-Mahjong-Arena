package Tuiles;

import javax.swing.ImageIcon;

public abstract class Tuile{
  protected String Catégorie;
  protected ImageIcon Img;
  private static String[] EnsembleCatégorie = {"C", "B", "K", "S", "N", "O", "E", "V", "R", "W", "F", "P"};
  private int ligne, colonne, hauteur;
  public Tuile(String cat){
    Catégorie = cat;
    for (int i = 0; i<EnsembleCatégorie.length; i++){
      if (Catégorie == EnsembleCatégorie[i]){
        break;
      }
      if (i == EnsembleCatégorie.length-1){
        throw new IllegalArgumentException("La catégorie n'existe pas.");
      }
    }
  }
  public void AddCoordonnées(int l, int c, int h){
    ligne = l;
    colonne = c;
    hauteur = h;
  }
  public String getCatégorie(){
    return Catégorie;
  }
  public int getLigne(){
    return ligne;
  }
  public int getColonne(){
    return colonne;
  }
  public int getHauteur(){
    return hauteur;
  }
  public abstract ImageIcon getImage();
  public Boolean PeutÊtreRetiré(Tuile T2){
    if (this != T2 && toString().equals(T2.toString())){
      return true;
    }
    else{
      return false;
    }
  }
  public abstract String toString();
}


