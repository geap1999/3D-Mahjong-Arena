package Tuiles;

import javax.swing.ImageIcon;
public class TuileMemeDessin extends Tuile{
  private static String[] Catégories = {"S", "N", "O", "E", "V", "R", "W"};
  private String NomEntierCatégorie;
  public TuileMemeDessin(String cat){
    super(cat);
    if (Catégorie == Catégories[0]){
      NomEntierCatégorie = "VENT_SUD";
    }
    else if (Catégorie == Catégories[1]){
      NomEntierCatégorie = "VENT_NORD";
    }
    else if (Catégorie == Catégories[2]){
      NomEntierCatégorie = "VENT_OUEST";
    }
    else if (Catégorie == Catégories[3]){
      NomEntierCatégorie = "VENT_EST";
    }
    else if (Catégorie == Catégories[4]){
      NomEntierCatégorie = "DRAGON_VERT";
    }
    else if (Catégorie == Catégories[5]){
      NomEntierCatégorie = "DRAGON_ROUGE";
    }
    else if (Catégorie == Catégories[6]){
      NomEntierCatégorie = "DRAGON_BLANC";
    }
    else{
      throw new IllegalArgumentException("Pas la bonne catégorie.");
    }
    Img = new ImageIcon("images/" + getNomComplet() + ".png");
  }
  public String getNomComplet(){
    return NomEntierCatégorie;
  }
  public String getType(){
    return "Tuile sans numéro avec toujours le même dessin.";
  }
  public ImageIcon getImage(){
    return Img; 
  }
  public Boolean PeutÊtreRetiré(TuileMemeDessin T2){
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
  public Boolean PeutÊtreRetiré(TuileNum1a9 T2){
    return false;
  }
  @Override
  public String toString() {
    return Catégorie + 1;
  }
}
