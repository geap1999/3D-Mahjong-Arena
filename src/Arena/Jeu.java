package Arena;
import java.util.Scanner;

import Tuiles.TuileEcran;

import java.util.InputMismatchException;

import GUI.Controler;
import GUI.IGMahjong;
import GUI.MahjongControler;
public class Jeu{
  public static int getTuilesEcranHauteur(int Ligne, int Colonne, TuileEcran[][][] TuilesEcran){
    int hauteurMax = -1;
    for (int i = 0; i < TuilesEcran[0][0].length; i++) {
      if (TuilesEcran[Ligne][Colonne][i] != null && TuilesEcran[Ligne][Colonne][i].getZ() > hauteurMax) {
        hauteurMax = TuilesEcran[Ligne][Colonne][i].getZ();
      }
    }
    return hauteurMax;
  }
  public static void main(String[] args){ 
    int[][] cdArena = {{0,0,0},{0,1,0},{0,2,0},{0,3,0},{0,4,0},{0,8,0},
      {0,9,0},{0,10,0},{0,11,0},{0,12,0},{1,0,0},{1,1,0},{1,2,0},
      {1,3,0},{1,5,0},{1,6,0},{1,7,0},{1,9,0},{1,10,0},{1,11,0},
      {1,12,0},{2,0,0},{2,1,0},{2,2,0},{2,6,0},{2,10,0},{2,11,0},
      {2,12,0},{3,0,0},{3,1,0},{3,5,0},{3,6,0},{3,7,0},{3,11,0},
      {3,12,0},{4,0,0},{4,1,0},{4,2,0},{4,6,0},{4,10,0},{4,11,0},
      {4,12,0},{5,0,0},{5,1,0},{5,2,0},{5,3,0},{5,5,0},{5,6,0},
      {5,7,0},{5,9,0},{5,10,0},{5,11,0},{5,12,0},{6,0,0},{6,1,0},
      {6,2,0},{6,3,0},{6,4,0},{6,8,0},{6,9,0},{6,10,0},{6,11,0},
      {6,12,0},{0,0,1},{0,1,1},{0,2,1},{0,3,1},{0,9,1},{0,10,1},
      {0,11,1},{0,12,1},{1,0,1},{1,1,1},{1,2,1},{1,6,1},{1,10,1},
      {1,11,1},{1,12,1},{2,0,1},{2,1,1},{2,11,1},{2,12,1},{3,0,1},
      {3,6,1},{3,12,1},{4,0,1},{4,1,1},{4,11,1},{4,12,1},{5,0,1},
      {5,1,1},{5,2,1},{5,6,1},{5,10,1},{5,11,1},{5,12,1},{6,0,1},
      {6,1,1},{6,2,1},{6,3,1},{6,9,1},{6,10,1},{6,11,1},{6,12,1},
      {0,0,2},{0,1,2},{0,2,2},{0,10,2},{0,11,2},{0,12,2},{1,0,2},
      {1,1,2},{1,11,2},{1,12,2},{2,0,2},{2,12,2},{4,0,2},{4,12,2},
      {5,0,2},{5,1,2},{5,11,2},{5,12,2},{6,0,2},{6,1,2},{6,2,2},
      {6,10,2},{6,11,2},{6,12,2},{0,0,3},{0,1,3},{0,11,3},{0,12,3},
      {1,0,3},{1,12,3},{5,0,3},{5,12,3},{6,0,3},{6,1,3},{6,11,3},
      {6,12,3},{0,0,4},{0,12,4},{6,0,4},{6,12,4}};
    Scanner scanner = new Scanner(System.in);
    int LigneT1 = 0, ColonneT1 = 0, LigneT2 = 0, ColonneT2 = 0;
    Disposition DispositionJeu = new Disposition(7, 13, 5, cdArena);
    IGMahjong igm = new IGMahjong(true);
    MahjongControler controler = new Controler();
    igm.setControler(controler);
    igm.ajouterBouton("Abandonnez ?");
    TuileEcran[][][] TuilesEcran = DispositionJeu.getTuilesEcran();
    for (int i = 0; i<7; i++){
      for (int j = 0; j<13; j++){
        for (int k = 0; k<5; k++){
          if (TuilesEcran[i][j][k] != null){
            igm.ajouterTuile(TuilesEcran[i][j][k].getImage(), i, j, k);
          }
        }
      }
    }
    igm.setMessage("Donnez les lignes et colonnes des tuiles qui peuvent être retirées.");
    System.out.println(DispositionJeu);
    for (int i = 0; i<72; i++){
      boolean BonNum = false;
      boolean BonMatch = false;
      int HauteurT1 = -1;
      int HauteurT2 = -1;
      do{
        do{
          try{
            System.out.println("Ligne de la Tuile1 : ");
            LigneT1 = scanner.nextInt();
            if (LigneT1 < 0 || LigneT1 > 6){
              throw new RuntimeException("Choississez une ligne entre 0 et 6.");
            }
            System.out.println("Colonne de la Tuile1 : ");
            ColonneT1 = scanner.nextInt();
            if (ColonneT1 < 0 || ColonneT1 > 12){
              throw new RuntimeException("Choississez une colonne entre 0 et 12.");
            }
            HauteurT1 = getTuilesEcranHauteur(LigneT1, ColonneT1, TuilesEcran);
            System.out.println("Ligne de la Tuile2 : ");
            LigneT2 = scanner.nextInt();
            if (LigneT2 < 0 || LigneT2 > 6){
              throw new RuntimeException("Choississez une ligne entre 0 et 6.");
            }
            System.out.println("Colonne de la Tuile2 : ");
            ColonneT2 = scanner.nextInt();
            if (ColonneT2 < 0 || ColonneT2 > 12){
              throw new RuntimeException("Choississez une colonne entre 0 et 12.");
            }
            HauteurT2 = getTuilesEcranHauteur(LigneT2, ColonneT2, TuilesEcran);
            break;
          }
          catch (InputMismatchException e){
            System.out.println("Pas un numéro, recommencez.");
            scanner.nextLine();
          }
          catch (RuntimeException e){
            System.out.println(e.getMessage() + " Recommencez.");
            break;
          }
        }while(!BonNum);
        try{
          DispositionJeu.RetirerTuiles(LigneT1, ColonneT1, LigneT2, ColonneT2);
          igm.retirerPaire(LigneT1, ColonneT1, HauteurT1, LigneT2, ColonneT2, HauteurT2);
          BonMatch = true;
        }
        catch (RuntimeException e){
          System.out.println(e.getMessage() + " Recommencez.");
          break;
        }
      }while(!BonMatch);
      System.out.println(DispositionJeu);
    }
    System.out.println("Bravo, vous avez terminé cette partie. Répondez avec 'o' pour continuer et 'n' pour arrêter.");
    String Reponse ="";
    Reponse = scanner.next();
    while(!Reponse.equalsIgnoreCase("o") && !Reponse.equalsIgnoreCase("n")){
      System.out.println("Il faut répondre avec 'o' ou 'n'.");
      Reponse = scanner.next();
    } 
    if (Reponse.equalsIgnoreCase("n")) {
      System.out.println("Merci d'avoir joué.");
    } 
    else {
       main(null);
    }
  }
}
