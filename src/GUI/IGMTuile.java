package GUI;

import javax.swing.ImageIcon;

public interface IGMTuile {
 ImageIcon getImage();
 int getX();
 int getY();
 int getZ();
 boolean isHighlighted();
 void setHighlighted(boolean b);
}
