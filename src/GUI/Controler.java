package GUI;

public class Controler implements MahjongControler {

 @Override
 public void clic(int x, int y, int z) {
  System.out.println("La mÃ©thode clic a Ã©tÃ© appelÃ©e ("+x+","
    +y+","+z+")");
 }

 @Override
 public void buttonPressed(String identifier){
   System.exit(0);
 }
}
