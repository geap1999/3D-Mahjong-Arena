package Tuiles;

import java.util.Random;
import java.util.ArrayList;

public class TuilesMahjong{
  private static ArrayList<Tuile> EnsembleTuiles;
  private Random tirage = new Random();
  public TuilesMahjong(){
    EnsembleTuiles = new ArrayList<>();
    for (int i = 0; i<4; i++){  
      for (int j = 1; j<10; j++){
        EnsembleTuiles.add(new TuileNum1a9("C", j));
        EnsembleTuiles.add(new TuileNum1a9("B", j));
        EnsembleTuiles.add(new TuileNum1a9("K", j));                   
      }
    }
    for (int i = 0; i<4; i++){  
      EnsembleTuiles.add(new TuileMemeDessin("S"));
      EnsembleTuiles.add(new TuileMemeDessin("N"));
      EnsembleTuiles.add(new TuileMemeDessin("O"));
      EnsembleTuiles.add(new TuileMemeDessin("E"));
      EnsembleTuiles.add(new TuileMemeDessin("V"));
      EnsembleTuiles.add(new TuileMemeDessin("R"));
      EnsembleTuiles.add(new TuileMemeDessin("W"));
      EnsembleTuiles.add(new TuileDessinDiff("F", i));
      EnsembleTuiles.add(new TuileDessinDiff("P", i));
    }
  }
  public Tuile Tirage(){
    int IndiceTuile = tirage.nextInt(EnsembleTuiles.size());
    Tuile TuileTiré = EnsembleTuiles.get(IndiceTuile);
    EnsembleTuiles.remove(IndiceTuile);
    return TuileTiré;
  }
  @Override
  public String toString(){
    return EnsembleTuiles.toString();
  }    
}
 