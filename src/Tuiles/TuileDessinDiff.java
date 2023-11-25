package Tuiles;

import javax.swing.ImageIcon;
public class TuileDessinDiff extends Tuile{
  private static String[] Catégories = {"F", "P"};
  private String NomEntierCatégorie;
  private String Dessin;
  private static String[] DessinsFleur = {"BAMBOU", "CHRYSANTHEME", "ORCHIDEE", "PRUNIER"};
  private static String[] DessinsSaison = {"AUTOMNE", "HIVER", "PRINTEMPS", "ETE"};
  public TuileDessinDiff(String cat, int i){
    super(cat);
    if (Catégorie == Catégories[0]){
      NomEntierCatégorie = "FLEUR";
      Dessin = DessinsFleur[i];
    }
    else if (Catégorie == Catégories[1]){
      NomEntierCatégorie = "SAISON";
      Dessin = DessinsSaison[i];
    }
    else{
      throw new RuntimeException("Pas la bonne catégorie.");
    }
    Img = new ImageIcon("images/" + getNomComplet() + ".png");
  }       
  public String getNomComplet(){
    return NomEntierCatégorie + "_" + Dessin;
  }
  public String getType(){
    return "Tuile sans numéro avec toujours un dessin différent.";
  }
  public ImageIcon getImage(){
    return Img; 
  }
  public Boolean PeutÊtreRetiré(TuileDessinDiff T2){
    if (toString().equals(T2.toString())){
      return true;
    }
    else{
      return false;
    }
  }
  public Boolean PeutÊtreRetiré(TuileMemeDessin T2){
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












