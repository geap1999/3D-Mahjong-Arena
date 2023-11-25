package Tuiles;

import javax.swing.ImageIcon;
public class TuileNum1a9 extends Tuile{
  private static String[] Catégories = {"C", "B", "K"};
  private int Numéro;
  private String NomEntierCatégorie;
  public TuileNum1a9(String cat, int n){
    super(cat);
    Numéro = n;
    if (Catégorie == Catégories[0]){
      NomEntierCatégorie = "CERCLE";
    }
    else if (Catégorie == Catégories[1]){
      NomEntierCatégorie = "BAMBOU";
    }
    else if (Catégorie == Catégories[2]){
      NomEntierCatégorie = "CARACTERE";
    }
    else{
      throw new RuntimeException("Pas la bonne catégorie.");
    }
    if (Numéro < 1 || Numéro > 9){
      throw new IllegalArgumentException("Seulement les numéros 1 à 9 peuvent être attribués à cette tuile");
    }
    Img = new ImageIcon("images/" + getNomComplet() + ".png");
  }
  public String getNomComplet(){
    return NomEntierCatégorie + "_" + Numéro;
  }
  public int getNuméro(){
    return Numéro;
  }
  public String getType(){
    return "Tuile avec un numéro allant de 1 à 9.";
  }
  public ImageIcon getImage(){
    return Img; 
  }
  public Boolean PeutÊtreRetiré(TuileNum1a9 T2){
    if (toString().equals(T2.toString())){
      return true;
    }
    else{
      return false;
    }
  }
  public Boolean PeutÊtreRetiré(TuileDessinDiff T2){
    return false;
  }
  public Boolean PeutÊtreRetiré(TuileMemeDessin T2){
    return false;
  }
  @Override
  public String toString() {
    return Catégorie + Numéro;
  }
}
  