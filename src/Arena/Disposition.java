package Arena;

import java.util.ArrayList;
import Tuiles.Tuile;
import Tuiles.TuileEcran;
import Tuiles.TuilesMahjong;

public class Disposition{
  private int Lignes;
  private int Colonnes;
  private int Hauteur;
  private TuilesMahjong EnsembleTuiles;
  private Tuile[][][] TuilesDisposition;
  private TuileEcran[][][] TuilesEcran;
  private String Board;
  public Disposition(int NbLignes, int NbColonnes, int HauteurMax, int[][] Coordonnées){
    Lignes = NbLignes;
    Colonnes = NbColonnes; 
    Hauteur = HauteurMax;
    EnsembleTuiles = new TuilesMahjong();
    ArrayList<Tuile> TuilesCd = new ArrayList<Tuile>(Coordonnées.length);
    for (int i = 0; i<Coordonnées.length; i++){
      Tuile TuileTiré = EnsembleTuiles.Tirage();
      TuilesCd.add(TuileTiré);
      TuilesCd.get(i).AddCoordonnées(Coordonnées[i][0], Coordonnées[i][1], Coordonnées[i][2]);
    }
    TuilesDisposition = new Tuile[Lignes][Colonnes][Hauteur];
    TuilesEcran = new TuileEcran[Lignes][Colonnes][Hauteur];
    for (int i = 0; i<Lignes; i++){
      for (int j = 0; j<Colonnes; j++){ 
        for (int k = 0; k<Hauteur; k++){
          for (int l = 0; l<TuilesCd.size(); l++){
            if (TuilesCd.get(l).getLigne() == i && TuilesCd.get(l).getColonne() == j){
              TuilesDisposition[i][j][k] = TuilesCd.get(l);
              TuilesEcran[i][j][k] = new TuileEcran(TuilesDisposition[i][j][k].getLigne(), TuilesDisposition[i][j][k].getColonne(),
              TuilesDisposition[i][j][k].getHauteur(), TuilesDisposition[i][j][k].getImage());
              TuilesCd.remove(l);
              break;
            }
          }
        }
      }
    }
  }
  public TuileEcran[][][] getTuilesEcran(){
    return TuilesEcran;
  }
  public boolean ConditionRetirage(int ligne, int colonne, int hauteur){
    boolean retireValide = false;
    Tuile TuileGauche = null;
    int HauteurGauche = -1;
    if(colonne != 0){
      TuileGauche = getTuileSommet(ligne, colonne -1);
      if (TuileGauche != null){
        HauteurGauche = TuileGauche.getHauteur();
      }
    }
    Tuile TuileDroite = null;
    int HauteurDroite = -1;
    if(colonne != Colonnes - 1){
      TuileDroite= getTuileSommet(ligne, colonne + 1);
      if(TuileDroite != null) {
        HauteurDroite = TuileDroite.getHauteur();
      }
    }
    if (hauteur > HauteurGauche || hauteur > HauteurDroite) {
      retireValide = true;
    }
    return retireValide;
  }
  public Tuile getTuileSommet(int ligne, int colonne) {
    int hauteurMax = -1;
    Tuile tuileSommet = null;
    for (int i = 0; i < TuilesDisposition[0][0].length; i++) {
      if (TuilesDisposition[ligne][colonne][i] != null && TuilesDisposition[ligne][colonne][i].getHauteur() > hauteurMax) {
        hauteurMax = TuilesDisposition[ligne][colonne][i].getHauteur();
        tuileSommet = TuilesDisposition[ligne][colonne][i];
      }
    }
    return tuileSommet;
  }
  public void RetirerTuiles(int LigneT1, int ColonneT1, int LigneT2, int ColonneT2){
    if (LigneT1 < 0 || LigneT1 > Lignes - 1 || ColonneT1 < 0 || ColonneT1 > Colonnes - 1) {
      throw new RuntimeException("Position inexistante pour la première tuile.");
    }
    else if (LigneT2 < 0 || LigneT2 > Lignes - 1 || ColonneT2 < 0 || ColonneT2 > Colonnes - 1){
      throw new RuntimeException("Position inexistante pour la deuxième tuile.");
    }
    if (getTuileSommet(LigneT1, ColonneT1) == null){
      throw new RuntimeException("Aucune tuile n'existe pour la première position donnée.");
    }
    else if (getTuileSommet(LigneT2, ColonneT2)== null){
      throw new RuntimeException("Aucune tuile n'existe pour la deuxième position donnée.");
    }
    Tuile Tuile1 = getTuileSommet(LigneT1, ColonneT1);
    int HauteurT1 = Tuile1.getHauteur();
    Tuile Tuile2 = getTuileSommet(LigneT2, ColonneT2);
    int HauteurT2 = Tuile2.getHauteur();
    if (Tuile1 == Tuile2){
      throw new RuntimeException("Tu ne peux pas choisir deux fois la même tuile.");
    }
    if (!ConditionRetirage(LigneT1, ColonneT1, HauteurT1)){
      throw new RuntimeException("La première tuile ne peut pas être retiré.");
    }
    else if (!ConditionRetirage(LigneT2, ColonneT2, HauteurT2)){
      throw new RuntimeException("La deuxième tuile ne peut pas être retiré.");
    }
    if (Tuile1.PeutÊtreRetiré(Tuile2)){ 
      for (int i = 0; i<Lignes; i++){
        for (int j = 0; j<Colonnes; j++){ 
          for (int k = 0; k<Hauteur; k++){
            if (TuilesDisposition[i][j][k] == Tuile1){
              TuilesDisposition[i][j][k] = null;
            }
            else if (TuilesDisposition[i][j][k] == Tuile2){
              TuilesDisposition[i][j][k] = null;
            }
          }
        }
      }
    }
    else{
      throw new RuntimeException("Mauvais match.");
    }
  }
  public void AffichageAll(){
    for (int i = 0; i<Lignes; i++){
      for (int j = 0; j<Colonnes; j++){
        for (int k = 0; k<Hauteur; k++){
          if (TuilesDisposition[i][j][k] == null){
            System.out.print("");
          }
          else{
            System.out.print(TuilesDisposition[i][j][k] + " ");
          }
        }
      }
    }
  }
  public void AffichageTuiles(){
    Tuile tuileSommet;
    for (int i = 0; i<Lignes; i++){
      for (int j = 0; j<Colonnes; j++){
        tuileSommet = getTuileSommet(i, j);
        if (tuileSommet == null){
          System.out.print("   ");
        } 
        else{
          System.out.print(tuileSommet.toString() + " ");
        }
      }
      System.out.println();
    }
  }
  public static String CompareHauteur(Tuile Tuile1, Tuile Tuile2){
    if (Tuile1 == null && Tuile2 == null){
      return " ";
    } 
    else if (Tuile1 == null && Tuile2 != null){
      return "<";
    } 
    else if (Tuile1 != null && Tuile2 == null){
      return ">";
    } 
    else if (Tuile1.getHauteur() == Tuile2.getHauteur()){
      return " ";
    } 
    else if (Tuile1.getHauteur() > Tuile2.getHauteur()){
      return ">";
    } 
    else{
      return "<";
    }
  }
  public String toString() {
    Board = " ";
    for (int i = 0; i<=9; i++){
      Board = Board + "  " + i;
    }
    Board = Board + " ";   
    for (int i = 10; i<13; i++){
      Board = Board + " " + i;
    }
    Board = Board + '\n' + 0 + " ";
    for (int i = 0; i < Lignes; i++) {
      for (int j = 0; j < Colonnes; j++) {
        Tuile tuileSommet = getTuileSommet(i, j);
        if (j == 0 && tuileSommet == null){
          Board = Board + "   ";
        }
        else if (tuileSommet == null){
          Board = Board +  "  ";
        } 
        else if (j == 0){
          Board = Board + "<" + tuileSommet.toString();
        }
        else if (j == 12){
          Board = Board + tuileSommet.toString() + ">";
        }
        else{
          Board = Board + tuileSommet.toString();
        }
        if (j != 12){
          Board = Board + CompareHauteur(tuileSommet, getTuileSommet(i, j + 1));
        }
      }
      Board = Board + '\n' + (i + 1) + " ";
    }
    Board = Board.substring(0, Board.length() - 3);
    return Board;
  }
}

